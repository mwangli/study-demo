package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * order_refund_info
 * @author 
 */
@Data
public class OrderRefundInfo implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * skuid
     */
    private Long skuId;

    /**
     * 退款类型
     */
    private String refundType;

    /**
     * 退货件数
     */
    private Long refundNum;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 原因类型
     */
    private String refundReasonType;

    /**
     * 原因内容
     */
    private String refundReasonTxt;

    /**
     * 退款状态（0：待审批 1：已退款）
     */
    private String refundStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}