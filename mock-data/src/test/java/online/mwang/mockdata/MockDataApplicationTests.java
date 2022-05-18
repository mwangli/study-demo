package online.mwang.mockdata;

import online.mwang.mockdata.bean.CommentInfo;
import online.mwang.mockdata.mapper.CommentInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MockDataApplicationTests {

    @Autowired
    private CommentInfoMapper commentInfoMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        CommentInfo commentInfo = commentInfoMapper.selectByPrimaryKey(1L);
        System.out.println(commentInfo);
    }

}
