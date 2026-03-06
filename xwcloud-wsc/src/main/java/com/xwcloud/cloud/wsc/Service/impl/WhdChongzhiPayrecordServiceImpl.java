package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.WhdChongzhiPayrecordVo;
import com.xwcloud.cloud.model.entity.WhdChongzhiPayrecord;
import com.xwcloud.cloud.wsc.Dao.IWhdChongzhiPayrecordDao;
import com.xwcloud.cloud.wsc.Service.IWhdChongzhiPayrecordService;
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
public class WhdChongzhiPayrecordServiceImpl extends ServiceImpl<IWhdChongzhiPayrecordDao, WhdChongzhiPayrecord> implements IWhdChongzhiPayrecordService {

    @Autowired
    private IWhdChongzhiPayrecordDao whdChongzhiPayrecordDao;

    @Override
    public Page<WhdChongzhiPayrecordVo> getWhdChongzhiPayRecordPage(Page Page, QueryWrapper wrapper) {
        return whdChongzhiPayrecordDao.getWhdChongzhiPayRecordPage(Page, wrapper);
    }
}
