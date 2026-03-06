package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstuzxqrecordtable;

import com.xwcloud.cloud.stu.Dao.IPxstuzxqrecordtableDao;
import com.xwcloud.cloud.stu.Service.IPxstuzxqrecordtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxstuzxqrecordtableServiceImpl extends ServiceImpl<IPxstuzxqrecordtableDao, Pxstuzxqrecordtable> implements IPxstuzxqrecordtableService {
	@Autowired
    IPxstuzxqrecordtableDao iPxstuzxqrecordtableDao;
}
