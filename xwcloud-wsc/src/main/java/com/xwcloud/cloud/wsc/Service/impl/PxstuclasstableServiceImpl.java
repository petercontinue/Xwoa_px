package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;
import com.xwcloud.cloud.wsc.Dao.IPxstuclasstableDao;
import com.xwcloud.cloud.wsc.Service.IPxstuclasstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
@Service
public class PxstuclasstableServiceImpl extends ServiceImpl<IPxstuclasstableDao, Pxstuclasstable> implements IPxstuclasstableService {
    @Autowired
    IPxstuclasstableDao iPxstuclasstableDao;
}
