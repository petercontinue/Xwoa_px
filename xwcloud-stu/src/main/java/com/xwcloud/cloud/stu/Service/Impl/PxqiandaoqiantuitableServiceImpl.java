package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqiandaoqiantuitable;
import com.xwcloud.cloud.stu.Dao.IPxqiandaoqiantuitableDao;
import com.xwcloud.cloud.stu.Service.IPxqiandaoqiantuitableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-18
 */
@Service
public class PxqiandaoqiantuitableServiceImpl extends ServiceImpl<IPxqiandaoqiantuitableDao, Pxqiandaoqiantuitable> implements IPxqiandaoqiantuitableService {
    @Autowired
    IPxqiandaoqiantuitableDao iPxqiandaoqiantuitableDao;
}
