package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxsysparamdefaulttable;
import com.xwcloud.cloud.stu.Dao.IPxsysparamdefaulttableDao;
import com.xwcloud.cloud.stu.Service.IPxsysparamdefaulttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-04
 */
@Service
public class PxsysparamdefaulttableServiceImpl extends ServiceImpl<IPxsysparamdefaulttableDao, Pxsysparamdefaulttable> implements IPxsysparamdefaulttableService {
	@Autowired
    IPxsysparamdefaulttableDao iPxsysparamdefaulttableDao;
}
