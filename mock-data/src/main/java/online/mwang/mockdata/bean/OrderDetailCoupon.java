package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * order_detail_coupon
 * @author 
 */
@Data
public class OrderDetailCoupon implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单明细id
     */
    private Long orderDetailId;

    /**
     * 购物券ID
     */
    private Long couponId;

    /**
     * 购物券领用id
     */
    private Long couponUseId;

    /**
     * skuID
     */
    private Long skuId;

    /**
     * 获取时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}