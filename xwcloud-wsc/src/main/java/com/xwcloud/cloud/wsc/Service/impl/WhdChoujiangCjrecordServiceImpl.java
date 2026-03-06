package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.entity.WhdChoujiangCjrecord;
import com.xwcloud.cloud.wsc.Dao.IWhdChoujiangCjrecordDao;
import com.xwcloud.cloud.wsc.Service.IWhdChoujiangCjrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Service
public class WhdChoujiangCjrecordServiceImpl extends ServiceImpl<IWhdChoujiangCjrecordDao, WhdChoujiangCjrecord> implements IWhdChoujiangCjrecordService {

    @Autowired
    private IWhdChoujiangCjrecordDao whdChoujiangCjrecordDao;

    @Override
    public Page<HashMap<String, Object>>getWhdChoujiangRecordPage(Page page, QueryWrapper wrapper) {
        return whdChoujiangCjrecordDao.getWhdChoujiangRecordPage(page, wrapper);
    }
}
