package com.xwcloud.cloud.sys.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.sys.quartz.entity.JobEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-15
 */
@Repository
public interface IJobEntityDao extends BaseMapper<JobEntity> {
    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "name", property = "name"),
                @Result(column = "job_group", property = "jobGroup"),
                @Result(column = "cron", property = "cron"),
                @Result(column = "parameter", property = "parameter"),
                @Result(column = "description", property = "description"),
                @Result(column = "vm_param", property = "vmParam"),
                @Result(column = "jar_path", property = "jarPath"),
                @Result(column = "status", property = "status"),
    })
    @Select("<script>" +
            "SELECT * from  job_entity"
            + "</script>")
    List<JobEntity> getAllList();
}