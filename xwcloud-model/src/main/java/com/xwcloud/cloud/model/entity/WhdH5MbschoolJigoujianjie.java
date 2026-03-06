package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
@Data
@Accessors(chain = true)
@TableName("whd_h5_mbschool_jigoujianjie")
public class WhdH5MbschoolJigoujianjie extends Model<WhdH5MbschoolJigoujianjie> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 简介内容
     */
    @TableField("mbSchoolName")
    private String mbSchoolName;
    /**
     * 简介类型：0文字，1图片，2视频链接
     */
    @TableField("mbLianxifangshi")
    private String mbLianxifangshi;
    /**
     * 排序
     */
    @TableField("mbLianxifangshiUrl")
    private String mbLianxifangshiUrl;
    /**
     * 机构信息模板id,对应whd_h5_mbschool表的id
     */
    @TableField("mbschoolTel")
    private String mbschoolTel;

    @TableField("jigoujianjie")
    private String jigoujianjie;

    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WhdH5MbschoolJigoujianjie{" +
                ", id=" + id +
                ", mbSchoolName=" + mbSchoolName +
                ", mbLianxifangshi=" + mbLianxifangshi +
                ", mbLianxifangshiUrl=" + mbLianxifangshiUrl +
                ", mbschoolTel=" + mbschoolTel +
                ",jigoujianjie=" + jigoujianjie +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
