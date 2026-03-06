package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.WhdToupiaoRecordVo;
import com.xwcloud.cloud.model.entity.WhdToupiaoTprecord;
import com.xwcloud.cloud.wsc.Dao.IWhdToupiaoTprecordDao;
import com.xwcloud.cloud.wsc.Service.IWhdToupiaoTprecordService;
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
public class WhdToupiaoTprecordServiceImpl extends ServiceImpl<IWhdToupiaoTprecordDao, WhdToupiaoTprecord> implements IWhdToupiaoTprecordService {

    @Autowired
    private IWhdToupiaoTprecordDao whdToupiaoTprecordDao;

    @Override
    public Page<WhdToupiaoRecordVo> getWhdToupiaoRecordPage(Page page, QueryWrapper wrapper) {
        return whdToupiaoTprecordDao.getWhdToupiaoRecordPage(page, wrapper);
    }
}
