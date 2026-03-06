package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaQiandanSp;
import com.xwcloud.cloud.model.OA.Vo.QiandanSpVo;
import com.xwcloud.cloud.oa.Dao.IOaQiandanSpDao;
import com.xwcloud.cloud.oa.service.IOaQiandanSpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-21
 */
@Service
public class OaQiandanSpServiceImpl extends ServiceImpl<IOaQiandanSpDao, OaQiandanSp> implements IOaQiandanSpService {

    @Autowired
    private IOaQiandanSpDao iOaQiandanSpDao;

    //获取所有的签单审批信息
    @Override
    public Page<QiandanSpVo> getAllQiandanSpInfo(Page<QiandanSpVo> page, Wrapper wrapper) {
        return iOaQiandanSpDao.getAllQiandanSpInfo(page, wrapper);
    }

}
