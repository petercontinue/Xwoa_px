package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxbxkcchangetable;

import com.xwcloud.cloud.stu.Dao.IPxbxkcchangetableDao;
import com.xwcloud.cloud.stu.Service.IPxbxkcchangetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
@Service
public class PxbxkcchangetableServiceImpl extends ServiceImpl<IPxbxkcchangetableDao, Pxbxkcchangetable> implements IPxbxkcchangetableService {
	@Autowired
    IPxbxkcchangetableDao iPxbxkcchangetableDao;
}
