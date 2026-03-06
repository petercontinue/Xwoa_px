package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.WhdChongzhiRecordVo;
import com.xwcloud.cloud.model.entity.WhdChongzhiRecord;
import com.xwcloud.cloud.wsc.Dao.IWhdChongzhiRecordDao;
import com.xwcloud.cloud.wsc.Service.IWhdChongzhiRecordService;
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
public class WhdChongzhiRecordServiceImpl extends ServiceImpl<IWhdChongzhiRecordDao, WhdChongzhiRecord> implements IWhdChongzhiRecordService {

    @Autowired
    private IWhdChongzhiRecordDao whdChongzhiRecordDao;

    @Override
    public Page<WhdChongzhiRecordVo> getWhdChongzhiRecordPage(Page page, QueryWrapper wrapper) {
        return whdChongzhiRecordDao.getWhdChongzhiRecordPage(page, wrapper);
    }
}
