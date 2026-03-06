package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-21
 */
@Data
@Accessors(chain = true)
@TableName("oa_kehu")
public class OaKehu extends Model<OaKehu> {

    private static final long serialVersionUID = 1L;

    /**
     * 企业ID
     */
    @TableId("id")
    private Long id;
    /**
     * 地区ID
     */
    @TableField("areaid")
    private Long areaid;
    /**
     * 地区名称
     */
    @TableField("areaname")
    private String areaname;
    /**
     * 城市ID
     */
    @TableField("cityid")
    private Long cityid;
    /**
     * 省ID
     */
    @TableField("provinceid")
    private Long provinceid;
    /**
     * 售后员工ID
     */
    @TableField("aftersalestaffID")
    private Long aftersalestaffID;
    /**
     * 客户校区数
     */
    @TableField("campusNum")
    private Integer campusNum;
    @TableField("email")
    private String email;
    /**
     * 首次签单时间
     */
    @TableField("firstqiandandatetime")
    private Date firstqiandandatetime;
    /**
     * 合同
     */
    @TableField("hetong")
    private String hetong;
    /**
     * 代金券剩余总额，默认值0
     */
    @TableField("djqRemain")
    private BigDecimal djqRemain;
    /**
     * 短信剩余条数，默认值0
     */
    @TableField("smsRemain")
    private Integer smsRemain;

    /**
     * 枭为合同记录的客户机构名称
     */
    @TableField("kehucompanyname")
    private String kehucompanyname;
    /**
     * 枭为合同记录的客户联系人
     */
    @TableField("kehucontractname")
    private String kehucontractname;
    /**
     * 客户系统最大学员人数，默认值0表示人数不限
     */
    @TableField("maxStuNum")
    private Integer maxStuNum;
    /**
     * 客户系统里看见机构名称
     */
    @TableField("khShowJigouName")
    private String khShowJigouName;
    /**
     * 客户信息备注
     */
    @TableField("kehuinfobeizhu")
    private String kehuinfobeizhu;
    /**
     * 客户其他联系电话
     */
    @TableField("kehuothertel")
    private String kehuothertel;
    /**
     * 客户类别：1意向客户，2已购买客户(仍有效)，3已退费客户，4没续费了
     */
    @TableField("kehuType")
    private Integer kehuType;
    /**
     * 客户使用状态：1使用中，2未使用。默认值1
     */
    @TableField("kehuUseState")
    private Integer kehuUseState;
    /**
     * 套餐ID，对应oa_taocantype表ID
     */
    @TableField("taocanID")
    private Long taocanID;
    /**
     * 客户联系电话
     */
    @TableField("kehutelphone")
    private String kehutelphone;
    /**
     * 满意度ID
     */
    @TableField("manyiduID")
    private Long manyiduID;
    /**
     * 下次付款时间
     */
    @TableField("nextpaydatetime")
    private Date nextpaydatetime;
    /**
     * 页面描述
     */
    @TableField("pagediscription")
    private String pagediscription;
    /**
     * 页面关键字
     */
    @TableField("pagekewords")
    private String pagekewords;
    /**
     * 页面标题
     */
    @TableField("pagetitle")
    private String pagetitle;
    /**
     * 培训类别ID，正式客户必填；
     */
    @TableField("peixuntypeID")
    private long peixuntypeID;

    @TableField("qq")
    private String qq;
    /**
     * 销售ID，正式客户必填
     */
    @TableField("salestaffID")
    private String salestaffID;
    /**
     * 合伙人ID，对应oa_hehuoren表的ID
     */
    @TableField("hehuorenID")
    private String hehuorenID;
    /**
     * 站点banner图，移动端
     */
    @TableField("sitebanner")
    private String sitebanner;
    /**
     * 站点banner-PC
     */
    @TableField("sitebannerpc")
    private String sitebannerpc;
    /**
     * 站点联系人信息
     */
    @TableField("sitecontracttelinfo")
    private String sitecontracttelinfo;
    /**
     * 联系地址
     */
    @TableField("sitejigouaddress")
    private String sitejigouaddress;
    /**
     * 抖音号
     */
    @TableField("sitejigoudouyinhao")
    private String sitejigoudouyinhao;
    /**
     * 机构简介
     */
    @TableField("sitejigouintroduce")
    private String sitejigouintroduce;
    /**
     * 站点名称
     */
    @TableField("sitename")
    private String sitename;
    /**
     * 微信公众号
     */
    @TableField("siteweixingzh")
    private String siteweixingzh;
    /**
     * 站点LOGO
     */
    @TableField("sitelogo")
    private String sitelogo;
    /**
     * 机构认证状态,0未认证，1已认证，默认值0
     */
    @TableField("verifystatus")
    private Integer verifystatus;
    /**
     * 机构认证未通过的原因说明
     */
    @TableField("weitongguoshuoming")
    private String weitongguoshuoming;
    /**
     * 微信号
     */
    @TableField("weixin")
    private String weixin;
    /**
     * 意向程度ID
     */
    @TableField("yixiangTypeID")
    private String yixiangTypeID;
    /**
     * 意向来源
     */
    @TableField("yxfromID")
    private String yxfromID;
    /**
     * 意向客户下次跟进时间
     */
    @TableField("yxnextgenjindatetime")
    private Date yxnextgenjindatetime;
    /**
     * 添加人，注意该表里还有销售ID和售后ID
     */
    @TableField("addStaffID")
    private String addStaffID;

    @TableField("addTime")
    private Date addTime;

    @TableField("isShow")
    private Integer isShow;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
