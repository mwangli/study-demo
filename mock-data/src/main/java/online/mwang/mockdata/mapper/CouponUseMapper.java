package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.CouponUse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponUseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CouponUse record);

    int insertSelective(CouponUse record);

    CouponUse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CouponUse record);

    int updateByPrimaryKey(CouponUse record);
}