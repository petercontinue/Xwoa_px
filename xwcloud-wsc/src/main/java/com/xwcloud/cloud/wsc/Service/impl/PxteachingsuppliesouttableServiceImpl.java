package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.Pxteachingsuppliesouttable;
import com.xwcloud.cloud.wsc.Dao.IPxteachingsuppliesouttableDao;
import com.xwcloud.cloud.wsc.Service.IPxteachingsuppliesouttableService;
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
public class PxteachingsuppliesouttableServiceImpl extends ServiceImpl<IPxteachingsuppliesouttableDao, Pxteachingsuppliesouttable> implements IPxteachingsuppliesouttableService {
    @Autowired
    IPxteachingsuppliesouttableDao iPxteachingsuppliesouttableDao;
}
