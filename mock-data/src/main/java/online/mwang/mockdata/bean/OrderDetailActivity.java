package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * order_detail_activity
 * @author 
 */
@Data
public class OrderDetailActivity implements Serializable {
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
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动规则
     */
    private Long activityRuleId;

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