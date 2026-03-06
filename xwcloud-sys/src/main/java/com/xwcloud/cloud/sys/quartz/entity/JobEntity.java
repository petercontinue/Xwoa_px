package com.xwcloud.cloud.sys.quartz.entity;

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
 * @since 2020-12-15
 */
@Data
@Accessors(chain = true)
@TableName("job_entity")
public class JobEntity extends Model<JobEntity> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id")
	private long id;

	@TableField("name")
	private String name;       //job名称

	@TableField("job_group")
	private String jobGroup;   //job组名

	@TableField("cron")
	private String cron;      //执行的cron表达式

	@TableField("parameter")
	private String parameter;

	@TableField("description")
	private String description;

	@TableField("vm_param")
	private String vmParam;

	@TableField("jar_path")
	private String jarPath;

	@TableField("status")
	private String status;   //job的执行状态,这里我设置为OPEN/CLOSE且只有该值为OPEN才会执行该Job


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "JobEntity{" +
			", id=" + id +
			", name=" + name +
			", jobGroup=" + jobGroup +
			", cron=" + cron +
			", parameter=" + parameter +
			", description=" + description +
			", vmParam=" + vmParam +
			", jarPath=" + jarPath +
			", status=" + status +
			"}";
	}
}
