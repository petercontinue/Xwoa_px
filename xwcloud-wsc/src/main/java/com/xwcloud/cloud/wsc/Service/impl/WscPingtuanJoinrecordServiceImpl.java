package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WscPingtuanJoinRecordVo;
import com.xwcloud.cloud.model.entity.WscPingtuanJoinrecord;
import com.xwcloud.cloud.wsc.Dao.IWscPingtuanJoinrecordDao;
import com.xwcloud.cloud.wsc.Service.IWscPingtuanJoinrecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Service
public class WscPingtuanJoinrecordServiceImpl extends ServiceImpl<IWscPingtuanJoinrecordDao, WscPingtuanJoinrecord> implements IWscPingtuanJoinrecordService {

    @Autowired
    private IWscPingtuanJoinrecordDao wscPingtuanJoinrecordDao;

    @Override
    public Page<WscPingtuanJoinRecordVo> getWscPingtuanJoinRecordPage(Page page, QueryWrapper wrapper) {
        return wscPingtuanJoinrecordDao.getWscPingtuanJoinRecordPage(page, wrapper);
    }

    @Override
    public List<WscPingtuanJoinrecord> GetpingtuanJoinList(long wscgoodsID) {
        return wscPingtuanJoinrecordDao.GetpingtuanJoinList(wscgoodsID);
    }
}
