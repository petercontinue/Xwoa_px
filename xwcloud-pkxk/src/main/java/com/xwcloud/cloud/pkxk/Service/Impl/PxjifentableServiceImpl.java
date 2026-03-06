package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.Pxjifentable;
import com.xwcloud.cloud.pkxk.Dao.IPxjifentableDao;
import com.xwcloud.cloud.pkxk.Service.IPxjifentableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-19
 */
@Service
public class PxjifentableServiceImpl extends ServiceImpl<IPxjifentableDao, Pxjifentable> implements IPxjifentableService {
	@Autowired
    IPxjifentableDao iPxjifentableDao;
}
