package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WhdToupiaoCansaistu;

import com.xwcloud.cloud.model.Vo.WhdToupiaoCansaiStuVo;
import com.xwcloud.cloud.wsc.Dao.IWhdToupiaoCansaistuDao;
import com.xwcloud.cloud.wsc.Service.IWhdToupiaoCansaistuService;
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
public class WhdToupiaoCansaistuServiceImpl extends ServiceImpl<IWhdToupiaoCansaistuDao, WhdToupiaoCansaistu> implements IWhdToupiaoCansaistuService {

    @Autowired
    private IWhdToupiaoCansaistuDao whdToupiaoCansaistuDao;

    @Override
    public Page<WhdToupiaoCansaiStuVo> getWhdToupiaoCansaiStuPage(Page page, QueryWrapper Wrapper) {
        return whdToupiaoCansaistuDao.getWhdToupiaoCansaiStuPage(page, Wrapper);
    }
}
