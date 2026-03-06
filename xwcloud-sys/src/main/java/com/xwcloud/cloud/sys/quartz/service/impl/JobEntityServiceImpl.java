package com.xwcloud.cloud.sys.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.sys.quartz.config.DynamicJob;
import com.xwcloud.cloud.sys.quartz.entity.JobEntity;
import com.xwcloud.cloud.sys.quartz.mapper.IJobEntityDao;
import com.xwcloud.cloud.sys.quartz.service.IJobEntityService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-15
 */
@Service
public class JobEntityServiceImpl extends ServiceImpl<IJobEntityDao, JobEntity> implements IJobEntityService {

    @Autowired
    private IJobEntityDao iJobEntityDao;

    //通过Id获取Job
    public JobEntity getJobEntityById(Integer id) {
        return iJobEntityDao.selectById(id);
    }

    //从数据库中加载获取到所有Job
    public List<JobEntity> loadJobs() {
        return iJobEntityDao.getAllList();
    }

    //获取JobDataMap.(Job参数对象)
    public JobDataMap getJobDataMap(JobEntity job) {
        JobDataMap map = new JobDataMap();
        map.put("name", job.getName());
        map.put("jobGroup", job.getJobGroup());
        map.put("cronExpression", job.getCron());
        map.put("parameter", job.getParameter());
        map.put("jobDescription", job.getDescription());
        map.put("vmParam", job.getVmParam());
        map.put("jarPath", job.getJarPath());
        map.put("status", job.getStatus());
        return map;
    }

    //获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map) {
        return JobBuilder.newJob(DynamicJob.class)
                .withIdentity(jobKey)
                .withDescription(description)
                .setJobData(map)
                .storeDurably()
                .build();
    }

    //获取Trigger (Job的触发器,执行规则)
    public Trigger getTrigger(JobEntity job) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getName(), job.getJobGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()))
                .build();
    }

    //获取JobKey,包含Name和Group
    public JobKey getJobKey(JobEntity job) {
        return JobKey.jobKey(job.getName(), job.getJobGroup());
    }


}
