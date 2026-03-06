package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-05-26
 */
@Data
@Accessors(chain = true)
@TableName("wsc_collect")
public class WscCollect extends Model<WscCollect> {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏表id
     */
    @TableId("id")
    private Long id;
    /**
     * 收藏的商品ID
     */
    @TableField("goodsID")
    private Long goodsID;
    /**
     * 商城用户ID
     */
    @TableField("wscUserID")
    private Long wscUserID;
    /**
     * 添加时间
     */
    @TableField("addDate")
    private Date addDate;
    @TableField("qiyeID")
    private Long qiyeID;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WscCollect{" +
                ", id=" + id +
                ", goodsID=" + goodsID +
                ", wscUserID=" + wscUserID +
                ", addDate=" + addDate +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
