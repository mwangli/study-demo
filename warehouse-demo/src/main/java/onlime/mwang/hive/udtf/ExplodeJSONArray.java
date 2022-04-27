package onlime.mwang.hive.udtf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;

import java.util.ArrayList;

/**
 * @author mwangli
 * @date 2022/4/27 14:25
 */
public class ExplodeJSONArray extends GenericUDTF {

    private PrimitiveObjectInspector primitiveOI;

    @Override
    public void close() {
    }

    @Override
    public StructObjectInspector initialize(ObjectInspector[] argOIs) throws UDFArgumentException {

        if (argOIs.length != 1) throw new UDFArgumentException("explode_json_array函数必须有一个参数");

        ObjectInspector.Category category = argOIs[0].getCategory();
        if (!category.equals(ObjectInspector.Category.PRIMITIVE)) {
            throw new UDFArgumentException("explode_json_array函数只能接收基本数据类型参数");
        }

        PrimitiveObjectInspector primitiveOI = (PrimitiveObjectInspector) argOIs[0];
        this.primitiveOI = primitiveOI;

        PrimitiveObjectInspector.PrimitiveCategory primitiveCategory = primitiveOI.getPrimitiveCategory();
        if (!primitiveCategory.equals(PrimitiveObjectInspector.PrimitiveCategory.STRING)) {
            throw new UDFArgumentException("explode_json_array函数只能接收STRING数据类型参数");
        }

        ArrayList<String> fieldNames = new ArrayList<>();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<>();
        fieldNames.add("item");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        Object arg = args[0];
        String jsonString = PrimitiveObjectInspectorUtils.getString(arg, primitiveOI);
        JSONArray jsonArray = JSON.parseArray(jsonString);
        jsonArray.forEach(item -> {
            String itemString = JSON.toJSONString(item);
            Object[] result = {itemString};
            try {
                forward(result);
            } catch (HiveException e) {
                e.printStackTrace();
            }
        });
    }

}
