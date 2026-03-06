package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliestable;
import com.xwcloud.cloud.wsc.Dao.IPxteachingsuppliestableDao;
import com.xwcloud.cloud.wsc.Service.IPxteachingsuppliestableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
@Service
public class PxteachingsuppliestableServiceImpl extends ServiceImpl<IPxteachingsuppliestableDao, Pxteachingsuppliestable> implements IPxteachingsuppliestableService {
	@Autowired
    IPxteachingsuppliestableDao iPxteachingsuppliestableDao;

    @Override
    public int UpdateteachingsuppliesKucun(Long ID, BigDecimal kucun,Long qiyeID) {
        return iPxteachingsuppliestableDao.UpdateteachingsuppliesKucun(ID, kucun,qiyeID);
    }
}
