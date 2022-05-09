package online.mwang.mockdata;

import online.mwang.mockdata.bean.UserInfo;
import online.mwang.mockdata.mapper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mwangli
 * @date 2022/5/9 17:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MockDataTest {

    @Autowired
    private static CartInfoMapper cartInfoMapper;
    private static CommentInfoMapper commentInfoMapper;
    private static CouponUseMapper couponUseMapper;
    private static FavorInfoMapper favorInfoMapper;
    private static OrderDetailActivityMapper orderDetailActivityMapper;
    private static OrderDetailCouponMapper orderDetailCouponMapper;
    private static OrderDetailMapper orderDetailMapper;
    private static OrderInfoMapper orderInfoMapper;
    private static OrderRefundInfoMapper orderRefundInfoMapper;
    private static OrderStatusLogMapper orderStatusLogMapper;
    private static PaymentInfoMapper paymentInfoMapper;
    private static RefundPaymentMapper refundPaymentMapper;
    @Autowired
    private  UserInfoMapper userInfoMapper;

    @Test
    public void test(){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1L);
        System.out.println(userInfo);
    }


}
