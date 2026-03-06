package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxyouhuizhengcetableDao;
import com.xwcloud.cloud.caiwu.Service.IPxyouhuizhengcetableService;
import com.xwcloud.cloud.model.entity.Pxyouhuizhengcetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-12
 */
@Service
public class PxyouhuizhengcetableServiceImpl extends ServiceImpl<IPxyouhuizhengcetableDao, Pxyouhuizhengcetable> implements IPxyouhuizhengcetableService {
    @Autowired
    IPxyouhuizhengcetableDao iPxyouhuizhengcetableDao;
}
