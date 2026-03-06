package com.xwcloud.cloud.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Data
@Accessors(chain = true)
@TableName("pxyxinvitedaofangtable")
public class Pxyxinvitedaofangtable extends Model<Pxyxinvitedaofangtable> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("inviteID")
    private Long inviteID;
    @TableField("daofangDatetime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date daofangDatetime;
    @TableField("adddaofanftime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date adddaofanftime;
    @TableField("daofangtext")
    private String daofangtext;
    @TableField("adddanfangren")
    private Long adddanfangren;
    @TableField("qiyeID")
    private Long qiyeID;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Pxyxinvitedaofangtable{" +
                ", id=" + id +
                ", inviteID=" + inviteID +
                ", daofangDatetime=" + daofangDatetime +
                ", adddaofanftime=" + adddaofanftime +
                ", adddanfangren=" + adddanfangren +
                ", qiyeID=" + qiyeID +
                "}";
    }
}
