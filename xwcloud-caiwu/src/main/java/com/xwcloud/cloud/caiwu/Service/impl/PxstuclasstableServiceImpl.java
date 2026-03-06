package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxstuclasstableDao;
import com.xwcloud.cloud.caiwu.Service.IPxstuclasstableService;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Service
public class PxstuclasstableServiceImpl extends ServiceImpl<IPxstuclasstableDao, Pxstuclasstable> implements IPxstuclasstableService {
    @Autowired
    IPxstuclasstableDao iPxstuclasstableDao;
}
