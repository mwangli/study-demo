package online.mwang.mockdata;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import online.mwang.mockdata.bean.ColumnBean;
import online.mwang.mockdata.mapper.DataXMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class MockDataApplicationTests {

    @Autowired
    private DataXMapper dataXMapper;

    @Test
    void contextLoads() {
    }

    private String jobJson = "{\n" +
            "    \"job\":{\n" +
            "        \"setting\":{\n" +
            "            \"speed\":{\n" +
            "                \"channel\":20\n" +
            "            }\n" +
            "        },\n" +
            "        \"content\":[\n" +
            "            {\n" +
            "                \"reader\":{\n" +
            "                    \"name\":\"mysqlreader\",\n" +
            "                    \"parameter\":{\n" +
            "                        \"username\":\"root\",\n" +
            "                        \"password\":\"Root.123456\",\n" +
            "                        \"connection\":[\n" +
            "                            {\n" +
            "                                \"querySql\":[\n" +
            "                                    \"select id, attr_name, category_id, category_level from base_attr_info;\"\n" +
            "                                ],\n" +
            "                                \"jdbcUrl\":[\n" +
            "                                    \"jdbc:mysql://test1:3306/bigdata\"\n" +
            "                                ]\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    }\n" +
            "                },\n" +
            "                \"writer\":{\n" +
            "                    \"name\":\"hdfswriter\",\n" +
            "                    \"parameter\":{\n" +
            "                        \"defaultFS\":\"hdfs://mycluster\",\n" +
            "                        \"hadoopConfig\":{\n" +
            "                            \"dfs.nameservices\":\"mycluster\",\n" +
            "                            \"dfs.ha.namenodes.mycluster\":\"nn1,nn2\",\n" +
            "                            \"dfs.namenode.rpc-address.mycluster.nn1\":\"node1:8020\",\n" +
            "                            \"dfs.namenode.rpc-address.mycluster.nn2\":\"node2:8020\",\n" +
            "                            \"dfs.client.failover.proxy.provider.mycluster\":\"org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider\",\n" +
            "                            \"dfs.client.use.datanode.hostname\":\"true\",\n" +
            "                            \"dfs.data.transfer.protection\":\"authentication\"\n" +
            "                        },\n" +
            "                        \"haveKerberos\":\"true\",\n" +
            "                        \"kerberosKeytabFilePath\":\"/etc/security/hadoop.keytab\",\n" +
            "                        \"kerberosPrincipal\":\"hadoop/node1\",\n" +
            "                        \"encoding\":\"UTF-8\",\n" +
            "                        \"fileType\":\"text\",\n" +
            "                        \"fileName\":\"base_attr_info\",\n" +
            "                        \"path\":\"/hive/warehouse/origin/sql-data/base_attr_info/2022-07-05\",\n" +
            "                        \"column\":[\n" +
            "                            {\n" +
            "                                \"name\":\"id\",\n" +
            "                                \"type\":\"bigint\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"name\":\"attr_name\",\n" +
            "                                \"type\":\"varchar\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"name\":\"category_id\",\n" +
            "                                \"type\":\"bigint\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"name\":\"category_level\",\n" +
            "                                \"type\":\"int\"\n" +
            "                            }\n" +
            "                        ],\n" +
            "                        \"writeMode\":\"append\",\n" +
            "                        \"fieldDelimiter\":\"\\t\"\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    @Test
    public void showTables() {
        List<String> tables = dataXMapper.showTables();
        System.out.println(tables);
    }


    @Test
    public void selectColumns() {
        List<ColumnBean> columns = dataXMapper.selectColumns("activity_info");
        columns.forEach(c -> c.setType(getType(c.getType())));
        System.out.println(columns);
    }

    @Test
    public void generateJobJson() {
        List<String> tables = dataXMapper.showTables();
        tables.forEach(t -> {
            JSONObject jsonObject = JSONObject.parseObject(jobJson);
            // 修改查询
            JSONArray querySql = jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0).getJSONObject("reader").getJSONObject("parameter").getJSONArray("connection").getJSONObject(0).getJSONArray("querySql");
            List<ColumnBean> columns = dataXMapper.selectColumns(t);
            columns.forEach(c -> c.setType(getType(c.getType())));
            String columnList = columns.stream().map(ColumnBean::getName).collect(Collectors.joining(","));
            querySql.set(0, "select " + columnList + " from " + t);
            // 修改路径
            JSONObject writerParams = jsonObject.getJSONObject("job").getJSONArray("content").getJSONObject(0).getJSONObject("writer").getJSONObject("parameter");
            writerParams.put("path", "/hive/warehouse/origin/sql-data/" + t + "/2022-07-05");
            writerParams.put("fileName", t);
            writerParams.put("column", JSONObject.parseArray(JSONArray.toJSONString(columns)));
//            System.out.println(writerParams);
            BufferedWriter bufferedWriter;
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(t + ".json")));
                System.out.println(JSONObject.toJSONString(jsonObject));
                bufferedWriter.write(JSONObject.toJSONString(jsonObject));
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String getType(String type) {
        if (type.startsWith("int")) return "int";
        if (type.startsWith("bigint")) return "bigint";
        if (type.startsWith("varchar")) return "varchar";
        if (type.startsWith("date")) return "timestamp";
        if (type.startsWith("decimal")) return "double";
        return type;
    }

    public static void main(String[] args) {
        ColumnBean columnBean = new ColumnBean();
        columnBean.setName("xx");
        columnBean.setType("yy");
        System.out.println(JSONObject.toJSONString(columnBean));
    }
}

