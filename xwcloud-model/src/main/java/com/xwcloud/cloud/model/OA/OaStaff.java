package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Data
@Accessors(chain = true)
@TableName("oa_staff")
public class OaStaff extends Model<OaStaff> {

    private static final long serialVersionUID = 1L;

    /**
     * staffID即员工ID
     */
    @TableId("id")
    private Long id;
    /**
     * 添加时间，即入职时间
     */
    @TableField("addtime")
    private Date addtime;
    /**
     * 离职时间
     */
    @TableField("offtime")
    private Date offtime;
    /**
     * 密码
     */
    @TableField("passwd")
    private String passwd;
    /**
     * 头像img
     */
    @TableField("stafflogoimg")
    private String stafflogoimg;
    /**
     * 员工姓名
     */
    @TableField("staffName")
    private String staffName;
    /**
     * 岗位ID，-1表示是客户的员工岗位ID
     */
    @TableField("staffpostID")
    private Long staffpostID;
    /**
     * 岗位名称
     */
    @TableField("staffpostName")
    private String staffpostName;
    /**
     * 员工生日
     */
    @TableField("staffBirthday")
    private Date staffBirthday;
    /**
     * 员工状态，1正常，2冻结，3离职
     */
    @TableField("staffstate")
    private Integer staffstate;
    /**
     * 员工电话，即登录账号
     */
    @TableField("stafftel")
    private String stafftel;
    /**
     * 工作昵称
     */
    @TableField("workName")
    private String workName;
    /**
     * 工作电话
     */
    @TableField("worktel")
    private String worktel;
    /**
     * 企业ID,-1 枭为科技
     */
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 备注说明
     */
    @TableField("shuoming")
    private String shuoming;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaStaff{" +
                ", id=" + id +
                ", addtime=" + addtime +
                ", offtime=" + offtime +
                ", passwd=" + passwd +
                ", stafflogoimg=" + stafflogoimg +
                ", staffName=" + staffName +
                ", staffpostID=" + staffpostID +
                ", staffpostName=" + staffpostName +
                ", staffBirthday=" + staffBirthday +
                ", staffstate=" + staffstate +
                ", stafftel=" + stafftel +
                ", workName=" + workName +
                ", worktel=" + worktel +
                ", qiyeID=" + qiyeID +
                ", shuoming=" + shuoming +
                "}";
    }
}
