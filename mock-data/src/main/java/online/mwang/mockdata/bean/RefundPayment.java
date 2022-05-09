package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * refund_payment
 * @author 
 */
@Data
public class RefundPayment implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 对外业务编号
     */
    private String outTradeNo;

    /**
     * 订单编号
     */
    private Long orderId;

    private Long skuId;

    /**
     * 支付类型（微信 支付宝）
     */
    private String paymentType;

    /**
     * 交易编号
     */
    private String tradeNo;

    /**
     * 退款金额
     */
    private BigDecimal totalAmount;

    /**
     * 交易内容
     */
    private String subject;

    /**
     * 退款状态
     */
    private String refundStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 回调时间
     */
    private Date callbackTime;

    /**
     * 回调信息
     */
    private String callbackContent;

    private static final long serialVersionUID = 1L;
}