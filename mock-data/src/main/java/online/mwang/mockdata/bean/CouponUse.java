package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * coupon_use
 * @author 
 */
@Data
public class CouponUse implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 购物券ID
     */
    private Long couponId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 购物券状态（1：未使用 2：已使用）
     */
    private String couponStatus;

    /**
     * 获取时间
     */
    private Date getTime;

    /**
     * 使用时间
     */
    private Date usingTime;

    /**
     * 支付时间
     */
    private Date usedTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    private static final long serialVersionUID = 1L;
}