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
 * @since 2021-05-19
 */
@Data
@Accessors(chain = true)
@TableName("wsc_user_address")
public class WscUserAddress extends Model<WscUserAddress> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("UserID")
    private Long userID;
    /**
     * 地址
     */
    @TableField("address")
    private String address;
    @TableField("addUserName")
    private String addUserName;
    /**
     * 联系电话
     */
    @TableField("tel")
    private String tel;
    /**
     * 是否是默认地址 true：是   false：否
     */
    @TableField("isMoren")
    private Boolean isMoren;
    @TableField("qiyeID")
    private Long qiyeID;
    @TableField("addresstype")
    private Long addresstype;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WscUserAddress{" +
                ", id=" + id +
                ", userID=" + userID +
                ", address=" + address +
                ", tel=" + tel +
                ", isMoren=" + isMoren +
                ", qiyeID=" + qiyeID +
                ", addresstype" + addresstype +
                ", addUserName" + addUserName +
                "}";
    }
}
