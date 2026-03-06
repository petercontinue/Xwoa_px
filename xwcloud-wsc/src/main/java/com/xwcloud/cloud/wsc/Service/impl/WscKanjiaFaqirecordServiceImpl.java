package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WscKanjiaFaqiRecordVo;
import com.xwcloud.cloud.model.Vo.faqikanjiaVO;
import com.xwcloud.cloud.model.entity.WscKanjiaFaqirecord;
import com.xwcloud.cloud.wsc.Dao.IWscKanjiaFaqirecordDao;
import com.xwcloud.cloud.wsc.Service.IWscKanjiaFaqirecordService;
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
public class WscKanjiaFaqirecordServiceImpl extends ServiceImpl<IWscKanjiaFaqirecordDao, WscKanjiaFaqirecord> implements IWscKanjiaFaqirecordService {

    @Autowired
    private IWscKanjiaFaqirecordDao wscKanjiaFaqirecordDao;

    @Override
    public Page<WscKanjiaFaqiRecordVo> getWscKanjiaFaqiRecordPage(Page page, QueryWrapper wrapper) {
        return wscKanjiaFaqirecordDao.getWscKanjiaFaqiRecordPage(page, wrapper);
    }
    @Override
    public List<WscKanjiaFaqirecord> GetfaqiKanjiaInfoByGoodsID(long kanjiaGoodsID) {
        return wscKanjiaFaqirecordDao.GetfaqiKanjiaInfoByGoodsID(kanjiaGoodsID);
    }

    @Override
    public List<WscKanjiaFaqirecord> GetfaqiKanjiaSuccessInfoByGoodsID(long kanjiaGoodsID) {
        return wscKanjiaFaqirecordDao.GetfaqiKanjiaSuccessInfoByGoodsID(kanjiaGoodsID);
    }

    @Override
    public List<faqikanjiaVO> panduanDangqianweixinyonghu(long wscUserID, long goodsID) {
        return wscKanjiaFaqirecordDao.panduanDangqianweixinyonghu(wscUserID, goodsID);
    }
}
