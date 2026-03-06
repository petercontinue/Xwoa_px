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
 * @since 2021-05-20
 */
@Data
@Accessors(chain = true)
@TableName("wsc_addresstype")
public class WscAddresstype extends Model<WscAddresstype> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 地址标签名称：（家、公司 等）
     */
    @TableField("addtype")
    private String addtype;
    /**
     * 添加的微商城用户ID
     */
    @TableField("wscUserID")
    private Long wscUserID;
    @TableField("qiyeID")
    private Long qiyeID;
    @TableField("type")
    private int type;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WscAddresstype{" +
                ", id=" + id +
                ", addtype=" + addtype +
                ", wscUserID=" + wscUserID +
                ", qiyeID=" + qiyeID +
                ", type=" + type +
                "}";
    }
}
