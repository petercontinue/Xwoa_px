package com.xwcloud.cloud.oa.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaQiandanXufeiSp;
import com.xwcloud.cloud.oa.Dao.IOaQiandanXufeiSpDao;
import com.xwcloud.cloud.oa.service.IOaQiandanXufeiSpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
 */
@Service
public class OaQiandanXufeiSpServiceImpl extends ServiceImpl<IOaQiandanXufeiSpDao, OaQiandanXufeiSp> implements IOaQiandanXufeiSpService {
    @Autowired
    IOaQiandanXufeiSpDao iOaQiandanXufeiSpDao;


    @Override
    public Page<HashMap<String, Object>> getxfQiandanSpInfo(Page page, Wrapper wrapper) {
        return iOaQiandanXufeiSpDao.getxfQiandanSpInfo(page, wrapper);
    }
}
