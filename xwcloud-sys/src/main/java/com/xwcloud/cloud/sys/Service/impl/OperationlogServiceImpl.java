package com.xwcloud.cloud.sys.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.log.Operationlog;
import com.xwcloud.cloud.sys.Dao.IOperationlogDao;
import com.xwcloud.cloud.sys.Service.IOperationlogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-03-11
 */
@Service
public class OperationlogServiceImpl extends ServiceImpl<IOperationlogDao, Operationlog> implements IOperationlogService {
	
}
