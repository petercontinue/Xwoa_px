package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.Qiandanapppaysubject;
import com.xwcloud.cloud.wsc.Dao.IQiandanapppaysubjectDao;
import com.xwcloud.cloud.wsc.Service.IQiandanapppaysubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
@Service
public class QiandanapppaysubjectServiceImpl extends ServiceImpl<IQiandanapppaysubjectDao, Qiandanapppaysubject> implements IQiandanapppaysubjectService {
	@Autowired
    IQiandanapppaysubjectDao iQiandanapppaysubjectDao;
}
