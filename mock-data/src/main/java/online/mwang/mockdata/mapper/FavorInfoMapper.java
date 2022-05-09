package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.FavorInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavorInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FavorInfo record);

    int insertSelective(FavorInfo record);

    FavorInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FavorInfo record);

    int updateByPrimaryKey(FavorInfo record);
}