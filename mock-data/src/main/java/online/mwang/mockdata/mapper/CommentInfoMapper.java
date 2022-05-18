package online.mwang.mockdata.mapper;

import online.mwang.mockdata.bean.CommentInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommentInfo record);

    int insertSelective(CommentInfo record);

    CommentInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentInfo record);

    int updateByPrimaryKey(CommentInfo record);
}