package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscUserjiaoyi;
import com.xwcloud.cloud.wsc.Dao.IWscUserjiaoyiDao;
import com.xwcloud.cloud.wsc.Service.IWscUserjiaoyiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-24
 */
@Service
public class WscUserjiaoyiServiceImpl extends ServiceImpl<IWscUserjiaoyiDao, WscUserjiaoyi> implements IWscUserjiaoyiService {

    @Autowired
    IWscUserjiaoyiDao wscUserjiaoyiDao;

    @Override
    public Page<WscUserjiaoyi> GetwechatChongziPages(Page page, QueryWrapper wrapper) {
        return wscUserjiaoyiDao.GetwechatChongziPages(page, wrapper);
    }

    @Override
    public List<HashMap<String, Object>> Getchongzhiliushui(QueryWrapper wrapper) {
        return wscUserjiaoyiDao.Getchongzhiliushui(wrapper);
    }
}
