package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.log.Logxjbtable;
import com.xwcloud.cloud.pkxk.Dao.ILogxjbtableDao;
import com.xwcloud.cloud.pkxk.Service.ILogxjbtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
 */
@Service
public class LogxjbtableServiceImpl extends ServiceImpl<ILogxjbtableDao, Logxjbtable> implements ILogxjbtableService {
	@Autowired
    ILogxjbtableDao iLogxjbtableDao;
}
