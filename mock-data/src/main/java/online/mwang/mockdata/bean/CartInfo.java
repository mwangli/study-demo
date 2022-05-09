package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * cart_info
 * @author 
 */
@Data
public class CartInfo implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * skuid
     */
    private Long skuId;

    /**
     * 放入购物车时价格
     */
    private BigDecimal cartPrice;

    /**
     * 数量
     */
    private Integer skuNum;

    /**
     * 图片文件
     */
    private String imgUrl;

    /**
     * sku名称 (冗余)
     */
    private String skuName;

    private Integer isChecked;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date operateTime;

    /**
     * 是否已经下单
     */
    private Long isOrdered;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 来源类型
     */
    private String sourceType;

    /**
     * 来源编号
     */
    private Long sourceId;

    private static final long serialVersionUID = 1L;
}