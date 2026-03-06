package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxclasstimestyletable;
import com.xwcloud.cloud.pkxk.Dao.IPxclasstimestyletableDao;
import com.xwcloud.cloud.pkxk.Service.IPxclasstimestyletableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-20
 */
@Service
public class PxclasstimestyletableServiceImpl extends ServiceImpl<IPxclasstimestyletableDao, Pxclasstimestyletable> implements IPxclasstimestyletableService {
	@Autowired
    IPxclasstimestyletableDao iPxclasstimestyletableDao;
}
