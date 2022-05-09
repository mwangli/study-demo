package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.RefundPayment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefundPaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RefundPayment record);

    int insertSelective(RefundPayment record);

    RefundPayment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RefundPayment record);

    int updateByPrimaryKey(RefundPayment record);
}