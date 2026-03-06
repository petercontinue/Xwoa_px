package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WscHuodongValueVo;
import com.xwcloud.cloud.model.entity.WscHuodongValue;
import com.xwcloud.cloud.wsc.Dao.IWscHuodongValueDao;
import com.xwcloud.cloud.wsc.Service.IWscHuodongValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Service
public class WscHuodongValueServiceImpl extends ServiceImpl<IWscHuodongValueDao, WscHuodongValue> implements IWscHuodongValueService {
    @Autowired
    private IWscHuodongValueDao wscHuodongValueDao;
    @Override
    public Page<WscHuodongValueVo> getWscHuodongValPage(Page page, QueryWrapper wrapper) {
        return wscHuodongValueDao.getWscHuodongValPage(page, wrapper);
    }
}
