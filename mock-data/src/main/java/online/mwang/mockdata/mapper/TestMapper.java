package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.ColumnBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    @Select("select 1")
    String selectTest();

    @Select(" SELECT * FROM oVW_Company_VW")
    List<Map<String,Object>> selectCompany();
}