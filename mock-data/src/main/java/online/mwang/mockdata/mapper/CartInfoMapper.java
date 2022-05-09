package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.CartInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CartInfo record);

    int insertSelective(CartInfo record);

    CartInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CartInfo record);

    int updateByPrimaryKey(CartInfo record);
}