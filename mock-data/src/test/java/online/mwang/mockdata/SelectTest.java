package online.mwang.mockdata;

import com.alibaba.fastjson.JSONObject;
import online.mwang.mockdata.mapper.DataXMapper;
import online.mwang.mockdata.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author mwangli
 * @date 2022/7/12 11:04
 */
@SpringBootTest
public class SelectTest {

    @Autowired
    private TestMapper testMapper;

    @Test
    void contextLoads() {
        String s = testMapper.selectTest();
        System.out.println(s);

        List<Map<String, Object>> maps = testMapper.selectCompany();
        System.out.println(maps);
    }
}
