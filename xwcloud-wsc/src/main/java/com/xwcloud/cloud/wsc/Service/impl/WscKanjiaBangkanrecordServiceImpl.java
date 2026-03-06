package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WscKanHelpRecordVo;
import com.xwcloud.cloud.model.entity.WscKanjiaBangkanrecord;
import com.xwcloud.cloud.wsc.Dao.IWscKanjiaBangkanrecordDao;
import com.xwcloud.cloud.wsc.Service.IWscKanjiaBangkanrecordService;
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
public class WscKanjiaBangkanrecordServiceImpl extends ServiceImpl<IWscKanjiaBangkanrecordDao, WscKanjiaBangkanrecord> implements IWscKanjiaBangkanrecordService {

    @Autowired
    private IWscKanjiaBangkanrecordDao wscKanjiaBangkanrecordDao;

    @Override
    public Page<WscKanHelpRecordVo> getWscKanHelpRecordPage(Page page, QueryWrapper wrapper) {
        return wscKanjiaBangkanrecordDao.getWscKanHelpRecordPage(page, wrapper);
    }
    @Override
    public List<WscKanjiaBangkanrecord> GetBangkanRecords(long kanjiaGoodsID) {
        return wscKanjiaBangkanrecordDao.GetBangkanRecords(kanjiaGoodsID);
    }
}
