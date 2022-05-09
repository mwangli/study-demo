package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.OrderRefundInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderRefundInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderRefundInfo record);

    int insertSelective(OrderRefundInfo record);

    OrderRefundInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderRefundInfo record);

    int updateByPrimaryKey(OrderRefundInfo record);
}