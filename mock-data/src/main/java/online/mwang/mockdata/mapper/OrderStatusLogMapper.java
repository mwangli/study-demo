package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.OrderStatusLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderStatusLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderStatusLog record);

    int insertSelective(OrderStatusLog record);

    OrderStatusLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderStatusLog record);

    int updateByPrimaryKey(OrderStatusLog record);
}