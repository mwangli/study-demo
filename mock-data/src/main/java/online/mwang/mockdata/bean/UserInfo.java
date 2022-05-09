package online.mwang.mockdata.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_info
 * @author 
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名称
     */
    private String loginName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户密码
     */
    private String passwd;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 用户级别
     */
    private String userLevel;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 性别 M男,F女
     */
    private String gender;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date operateTime;

    /**
     * 状态
     */
    private String status;

    private static final long serialVersionUID = 1L;
}