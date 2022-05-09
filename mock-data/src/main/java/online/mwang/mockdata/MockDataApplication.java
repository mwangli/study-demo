package online.mwang.mockdata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.mwang.mockdata.bean.CartInfo;
import online.mwang.mockdata.mapper.*;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class MockDataApplication {

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

    public static void main(String[] args) {
      SpringApplication.run(MockDataApplication.class, args);

    }




}
