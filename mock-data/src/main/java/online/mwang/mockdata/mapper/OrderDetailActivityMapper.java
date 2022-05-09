package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.OrderDetailActivity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailActivityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetailActivity record);

    int insertSelective(OrderDetailActivity record);

    OrderDetailActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetailActivity record);

    int updateByPrimaryKey(OrderDetailActivity record);
}