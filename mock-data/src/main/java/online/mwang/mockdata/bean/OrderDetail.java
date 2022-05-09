package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * order_detail
 * @author 
 */
@Data
public class OrderDetail implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * sku名称（冗余)
     */
    private String skuName;

    /**
     * 图片名称（冗余)
     */
    private String imgUrl;

    /**
     * 购买价格(下单时sku价格）
     */
    private BigDecimal orderPrice;

    /**
     * 购买个数
     */
    private String skuNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 来源类型
     */
    private String sourceType;

    /**
     * 来源编号
     */
    private Long sourceId;

    private BigDecimal splitTotalAmount;

    private BigDecimal splitActivityAmount;

    private BigDecimal splitCouponAmount;

    private static final long serialVersionUID = 1L;
}