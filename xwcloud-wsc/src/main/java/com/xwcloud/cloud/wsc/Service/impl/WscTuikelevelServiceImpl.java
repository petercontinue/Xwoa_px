package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.WscTuikelevel;
import com.xwcloud.cloud.wsc.Dao.IWscTuikelevelDao;
import com.xwcloud.cloud.wsc.Service.IWscTuikelevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-19
 */
@Service
public class WscTuikelevelServiceImpl extends ServiceImpl<IWscTuikelevelDao, WscTuikelevel> implements IWscTuikelevelService {
    @Autowired
    IWscTuikelevelDao iWscTuikelevelDao;

    @Override
    public List<listVo> getAlltklvList(QueryWrapper queryWrapper) {
        return iWscTuikelevelDao.getAlltklvList(queryWrapper);
    }
}
