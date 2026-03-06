package com.xwcloud.cloud.model.OA;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-08
 */
@Data
@Accessors(chain = true)
@TableName("oa_manyidu")
public class OaManyidu extends Model<OaManyidu> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    @TableField("manyiduName")
    private String manyiduName;
    @TableField("color")
    private String color;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OaManyidu{" +
                ", id=" + id +
                ", manyiduName=" + manyiduName +
                "}";
    }
}
