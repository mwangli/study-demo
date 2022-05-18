package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * comment_info
 * @author 
 */
@Data
public class CommentInfo implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    private String headImg;

    /**
     * skuid
     */
    private Long skuId;

    /**
     * 商品id
     */
    private Long spuId;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 评价 1 好评 2 中评 3 差评
     */
    private String appraise;

    /**
     * 评价内容
     */
    private String commentTxt;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date operateTime;

    private static final long serialVersionUID = 1L;
}