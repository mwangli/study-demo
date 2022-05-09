package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * order_status_log
 * @author 
 */
@Data
public class OrderStatusLog implements Serializable {
    private Long id;

    private Long orderId;

    private String orderStatus;

    private Date operateTime;

    private static final long serialVersionUID = 1L;
}