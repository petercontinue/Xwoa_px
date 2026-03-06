package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WhdChoujiangHuodong;
import com.xwcloud.cloud.wsc.Dao.IWhdChoujiangHuodongDao;
import com.xwcloud.cloud.wsc.Service.IWhdChoujiangHuodongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Service
public class WhdChoujiangHuodongServiceImpl extends ServiceImpl<IWhdChoujiangHuodongDao, WhdChoujiangHuodong> implements IWhdChoujiangHuodongService {

    @Autowired
    IWhdChoujiangHuodongDao iWhdChoujiangHuodongDao;

    @Override
    public Page<HashMap<String, Object>> getcjhuodongPages(Page page, QueryWrapper queryWrapper) {
        return iWhdChoujiangHuodongDao.getcjhuodongPages(page, queryWrapper);
    }
}
