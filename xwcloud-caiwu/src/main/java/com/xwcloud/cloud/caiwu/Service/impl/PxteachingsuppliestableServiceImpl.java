package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxteachingsuppliestableDao;
import com.xwcloud.cloud.caiwu.Service.IPxteachingsuppliestableService;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliestable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-14
 */
@Service
public class PxteachingsuppliestableServiceImpl extends ServiceImpl<IPxteachingsuppliestableDao, Pxteachingsuppliestable> implements IPxteachingsuppliestableService {
    @Autowired
    IPxteachingsuppliestableDao iPxteachingsuppliestableDao;

}
