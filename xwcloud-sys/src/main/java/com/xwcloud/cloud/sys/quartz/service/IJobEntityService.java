package com.xwcloud.cloud.sys.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.sys.quartz.entity.JobEntity;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-15
 */
public interface IJobEntityService extends IService<JobEntity> {

    public JobEntity getJobEntityById(Integer id);

    //从数据库中加载获取到所有Job
    public List<JobEntity> loadJobs();

    //获取JobDataMap.(Job参数对象)
    public JobDataMap getJobDataMap(JobEntity job);

    //获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map);

    //获取Trigger (Job的触发器,执行规则)
    public Trigger getTrigger(JobEntity job);

    //获取JobKey,包含Name和Group
    public JobKey getJobKey(JobEntity job);
}
