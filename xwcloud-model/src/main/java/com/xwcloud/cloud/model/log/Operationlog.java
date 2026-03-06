package com.xwcloud.cloud.model.log;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-03-11
 */
@Data
@Accessors(chain = true)
@TableName("operationlog")
public class Operationlog extends Model<Operationlog> {

    private static final long serialVersionUID = 1L;

	@TableField("id")
	private Long id;
	@TableField("oper_modul")
	private String operModul;
	@TableField("oper_type")
	private String operType;
	@TableField("oper_desc")
	private String operDesc;
	@TableField("oper_requ_param")
	private String operRequParam;
	@TableField("oper_resp_param")
	private String operRespParam;
	@TableField("oper_user_id")
	private String operUserId;
	@TableField("oper_user_name")
	private String operUserName;
	@TableField("oper_method")
	private String operMethod;
	@TableField("oper_url")
	private String operUrl;
	@TableField("oper_ip")
	private String operIp;
	@TableField("oper_create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operCreateTime;
	@TableField("oper_ver")
	private String operVer;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Operationlog{" +
			", id=" + id +
			", operModul=" + operModul +
			", operType=" + operType +
			", operDesc=" + operDesc +
			", operRequParam=" + operRequParam +
			", operRespParam=" + operRespParam +
			", operUserId=" + operUserId +
			", operUserName=" + operUserName +
			", operMethod=" + operMethod +
			", operUrl=" + operUrl +
			", operIp=" + operIp +
			", operCreateTime=" + operCreateTime +
			", operVer=" + operVer +
			"}";
	}
}
