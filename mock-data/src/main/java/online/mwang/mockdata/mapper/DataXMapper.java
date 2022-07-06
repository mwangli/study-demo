package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.ColumnBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataXMapper {

    @Select("show tables")
    List<String> showTables();

    @Select("select column_name as name, column_type as type from information_schema.columns where table_name = #{tableName} order by ordinal_position")
    List<ColumnBean> selectColumns(String tableName);
}