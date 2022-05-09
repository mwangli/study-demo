package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.OrderDetailCoupon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderDetailCoupon record);

    int insertSelective(OrderDetailCoupon record);

    OrderDetailCoupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetailCoupon record);

    int updateByPrimaryKey(OrderDetailCoupon record);
}