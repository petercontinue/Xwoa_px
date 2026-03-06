package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-05
 */
@Data
@Accessors(chain = true)
@TableName("pxstutable")
public class Pxstutable extends Model<Pxstutable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 自定义学号
     */
    @TableField("zidingyiStuID")
    private String zidingyiStuID;
    /**
     * 学员姓名
     */
    @TableField("stuName")
    private String stuName;
    /**
     * 家长电话，即登录账号
     */
    @TableField("parentTel")
    private String parentTel;
    @TableField("passwd")
    private String passwd;
    /**
     * 学生账号登录状态，1.冻结2.正常
     */
    @TableField("activity")
    private Integer activity;
    @TableField("stuSex")
    private String stuSex;
    @TableField("stuTel")
    private String stuTel;
    /**
     * 家长电话关系：1本人，2爸爸，3妈妈，4爷爷，5奶奶，6外公，7外婆，8保姆，9其他
     */
    @TableField("parentTelRelation")
    private String parentTelRelation;
    /**
     * 补习状态,1意向，2在读，3停课，4结课，5退费，6休眠
     */
    @TableField("buxiStateID")
    private Integer buxiStateID;
    /**
     * 年级ID
     */
    @TableField("stuGradeID")
    private Long stuGradeID;
    @TableField("stuXuexi")
    private String stuXuexi;
    @TableField("campusID")
    private Long campusID;
    @TableField("qiyeID")
    private Long qiyeID;
    /**
     * 班主任老师ID
     */
    @TableField("banzhurenTeacherID")
    private Long banzhurenTeacherID;
    @TableField("openid")
    private String openid;
    @TableField("unionid")
    private String unionid;
    /**
     * 剩余学费，默认值0
     */
    @TableField("remainXuefei")
    private BigDecimal remainXuefei;
    /**
     * 充值余额
     */
    @TableField("remainChongzhi")
    private BigDecimal remainChongzhi;
    /**
     * 学员生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("stubirth")
    private Date stubirth;

    /**
     * 积分数，默认值0
     */
    @TableField("jifenNum")
    private BigDecimal jifenNum;
    /**
     * 证件照图片
     */
    @TableField("IDImage")
    private String IDImage;
    /**
     * 证件号
     */
    @TableField("IDnumber")
    private String IDnumber;
    /**
     * 宿舍ID
     */
    @TableField("roomid")
    private Long roomid;
    /**
     * 原学校联系老师
     */
    @TableField("oldSchoolTeacher")
    private Long oldSchoolTeacher;
    /**
     * 原学校
     */
    @TableField("oldSchool")
    private Long oldSchool;
    /**
     * 入学成绩
     */
    @TableField("ruxuechengji")
    private String ruxuechengji;
    /**
     * 对老师的要求
     */
    @TableField("laoshiyaoqiu")
    private String laoshiyaoqiu;
    /**
     * 学员学习积极性
     */
    @TableField("jijixing")
    private String jijixing;
    /**
     * 学员性格
     */
    @TableField("xingge")
    private String xingge;
    /**
     * 最后一次回访时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("lastHuifangTime")
    private Date lastHuifangTime;
    /**
     * 下次回访时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("nextHuifangTime")
    private Date nextHuifangTime;
    @TableField("stuPhoto")
    private String stuPhoto;

    @TableField("stuxiaokeImage")
    private String stuxiaokeImage;
    /**
     * 意向来源ID
     */
    @TableField("yxFromID")
    private Long yxFromID;
    /**
     * 意向程度ID
     */
    @TableField("yxLevelID")
    private Long yxLevelID;
    /**
     * 意向科目
     */
    @TableField("yixiangkemu")
    private String yixiangkemu;
    /**
     * 意向的市场老师ID
     */
    @TableField("yxshichangTeacherID")
    private Long yxshichangTeacherID;
    /**
     * 意向的负责人老师ID
     */
    @TableField("yxfenpeistaffID")
    private Long yxfenpeistaffID;
    /**
     * 分配负责人的日期时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("yxfenpeiDate")
    private Date yxfenpeiDate;
    /**
     * 导入时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("daoruDate")
    private Date daoruDate;
    /**
     * 最后一次跟进时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("lastFollowDate")
    private Date lastFollowDate;
    /**
     * 学员数据录入的方式：1意向录入，2表格导出，3电脑后台报名签单录入，4微信端支付报名录入
     */
    @TableField("luruType")
    private Integer luruType;

    @TableField("nextGenjinTime")
    private Date nextGenjinTime;
    /**
     * 停课时间
     */
    @TableField("tingkeTime")
    private Date tingkeTime;
    /**
     * 登记老师ID（录入老师ID）
     */
    @TableField("dengjiTeacherID")
    private Long dengjiTeacherID;
    /**
     * 登录时间（录入时间）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("dengjiTime")
    private Date dengjiTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxstutable{" +
                ", id=" + id +
                ", zidingyiStuID=" + zidingyiStuID +
                ", stuName=" + stuName +
                ", parentTel=" + parentTel +
                ", passwd=" + passwd +
                ", activity=" + activity +
                ", stuSex=" + stuSex +
                ", stuTel=" + stuTel +
                ", parentTelRelation=" + parentTelRelation +
                ", buxiStateID=" + buxiStateID +
                ", stuGradeID=" + stuGradeID +
                ", stuXuexi=" + stuXuexi +
                ", campusID=" + campusID +
                ", qiyeID=" + qiyeID +
                ", banzhurenTeacherID=" + banzhurenTeacherID +
                ", openid=" + openid +
                ", unionid=" + unionid +
                ", remainXuefei=" + remainXuefei +
                ", remainChongzhi=" + remainChongzhi +
                ", stubirth=" + stubirth +
                ", jifenNum=" + jifenNum +
                ", IDImage=" + IDImage +
                ", IDnumber=" + IDnumber +
                ", roomid=" + roomid +
                ", oldSchoolTeacher=" +  oldSchoolTeacher +
                ", oldSchool=" + oldSchool +
                ", ruxuechengji=" + ruxuechengji +
                ", laoshiyaoqiu=" + laoshiyaoqiu +
                ", jijixing=" + jijixing +
                ", xingge=" + xingge +
                ", lastHuifangTime=" + lastHuifangTime +
                ", nextHuifangTime=" + nextHuifangTime +
                ", stuPhoto=" + stuPhoto +
                ",stuxiaokeImage=" + stuxiaokeImage +
                ", yxFromID=" + yxFromID +
                ", yxLevelID=" + yxLevelID +
                ", yixiangkemu=" + yixiangkemu +
                ", yxshichangTeacherID=" + yxshichangTeacherID +
                ", yxfenpeistaffID=" + yxfenpeistaffID +
                ", yxfenpeiDate=" + yxfenpeiDate +
                ", daoruDate=" + daoruDate +
                ", lastFollowDate=" + lastFollowDate +
                ", luruType=" + luruType +
                ", nextGenjinTime=" + nextGenjinTime +
                ", tingkeTime=" + tingkeTime +
                ", dengjiTeacherID=" + dengjiTeacherID +
                ", dengjiTime=" + dengjiTime +
                "}";
    }
}
