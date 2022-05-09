package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * favor_info
 * @author 
 */
@Data
public class FavorInfo implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名称
     */
    private Long userId;

    /**
     * skuid
     */
    private Long skuId;

    /**
     * 商品id
     */
    private Long spuId;

    /**
     * 是否已取消 0 正常 1 已取消
     */
    private String isCancel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date cancelTime;

    private static final long serialVersionUID = 1L;
}