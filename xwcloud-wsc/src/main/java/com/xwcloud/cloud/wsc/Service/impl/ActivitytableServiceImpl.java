package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Activitytable;
import com.xwcloud.cloud.wsc.Dao.IActivitytableDao;
import com.xwcloud.cloud.wsc.Service.IActivitytableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-09
 */
@Service
public class ActivitytableServiceImpl extends ServiceImpl<IActivitytableDao, Activitytable> implements IActivitytableService {
    @Autowired
    IActivitytableDao iActivitytableDao;
}
