package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
@Data
@Accessors(chain = true)
@TableName("wsc_user")
public class WscUser extends Model<WscUser> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("nickName")
    private String nickName;
    @TableField("openid")
    private String openid;
    @TableField("unionid")
    private String unionid;
    /**
     * 手机号码
     */
    @TableField("phoneNumber")
    private String phoneNumber;
    /**
     * 头像
     */
    @TableField("headImage")
    private String headImage;
    /**
     * 性别
     */
    @TableField("sex")
    private String sex;
    /**
     * 地区
     */
    @TableField("diqu")
    private String diqu;
    /**
     * 地址
     */
    @TableField("addr")
    private String addr;
    /**
     * 用户类型，1微信用户，2抖音用户
     */
    @TableField("userType")
    private Integer userType;
    /**
     * 添加时间
     */
    @TableField("addTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    /**
     * 是否被冻结；  0未冻结，1冻结，默认0
     */
    @TableField("isdongjie")
    private Integer isdongjie;
    /**
     * 是否是课程用户，1不是，2是，默认1
     */
    @TableField("isKcUser")
    private Integer isKcUser;
    /**
     * 推客级别ID,0表示普通用户，默认值0
     */
    @TableField("tuikeLevelID")
    private Long tuikeLevelID;
    /**
     * 真实姓名，成为推客必需填姓名
     */
    @TableField("realName")
    private String realName;
    /**
     * 银行卡号，提现时使用，成为推客必需填银行卡号
     */
    @TableField("bankCard")
    private String bankCard;
    /**
     * 开户行，成为推客必需填开户行
     */
    @TableField("bankName")
    private String bankName;
    /**
     * 父ID
     */
    @TableField("fid")
    private Long fid;
    /**
     * 祖父ID
     */
    @TableField("gfid")
    private Long gfid;
    /**
     * 商城余额。默认值0
     */
    @TableField("scRemainMoney")
    private BigDecimal scRemainMoney;
    /**
     * 商城积分。默认值0
     */
    @TableField("scJifen")
    private BigDecimal scJifen;
    /**
     * 商城剩余佣金。默认值0
     */
    @TableField("scRemainyongjin")
    private BigDecimal scRemainyongjin;
    /**
     * 商城未结佣金。默认值0
     */
    @TableField("scWeijieYongjin")
    private BigDecimal scWeijieYongjin;
    /**
     * 商城已结佣金。默认值0
     */
    @TableField("scYijieYongjin")
    private BigDecimal scYijieYongjin;
    /**
     * 短信剩余条数，默认值0
     */
    @TableField("smsRemain")
    private Integer smsRemain;
    /**
     * 企业ID
     */
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WscUser{" +
                ", id=" + id +
                ", nickName=" + nickName +
                ", openid=" + openid +
                ", unionid=" + unionid +
                ", phoneNumber=" + phoneNumber +
                ", headImage=" + headImage +
                ", sex=" + sex +
                ", diqu=" + diqu +
                ", addr=" + addr +
                ", userType=" + userType +
                ", addTime=" + addTime +
                ", isdongjie=" + isdongjie +
                ", isKcUser=" + isKcUser +
                ", tuikeLevelID=" + tuikeLevelID +
                ", realName=" + realName +
                ", bankCard=" + bankCard +
                ", bankName=" + bankName +
                ", fid=" + fid +
                ", gfid=" + gfid +
                ", scRemainMoney=" + scRemainMoney +
                ", scJifen=" + scJifen +
                ", scRemainyongjin=" + scRemainyongjin +
                ", scWeijieYongjin=" + scWeijieYongjin +
                ", scYijieYongjin=" + scYijieYongjin +
                ", smsRemain=" + smsRemain +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
