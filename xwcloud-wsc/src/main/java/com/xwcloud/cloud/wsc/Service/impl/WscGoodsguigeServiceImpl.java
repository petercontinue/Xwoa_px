package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.model.Vo.goodsInfoVO;
import com.xwcloud.cloud.model.entity.WscGoodsguige;
import com.xwcloud.cloud.wsc.Dao.IWscGoodsDao;
import com.xwcloud.cloud.wsc.Dao.IWscGoodsguigeDao;
import com.xwcloud.cloud.wsc.Dao.IWscGoodsshuxinglistDao;
import com.xwcloud.cloud.wsc.Service.IWscGoodsguigeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Service
public class WscGoodsguigeServiceImpl extends ServiceImpl<IWscGoodsguigeDao, WscGoodsguige> implements IWscGoodsguigeService {

    @Autowired
    IWscGoodsguigeDao wscGoodsguigeDao;

    @Autowired
    IWscGoodsDao wscGoodsDao;

    @Autowired
    IWscGoodsshuxinglistDao wscGoodsshuxinglistDao;
    @Override
    public List<goodsInfoVO> GetgoodsGuigeList(long qiyeID, long goodsID) {
        return wscGoodsguigeDao.GetgoodsGuigeList(qiyeID, goodsID);
    }

    @Override
    public List<goodsInfoVO> SearchAllGuigeList(long qiyeID, long goodsID) {
        List<goodsInfoVO> data= wscGoodsguigeDao.GetAllGoodsGuigeInfo(qiyeID,goodsID);
        for (goodsInfoVO item:data){
            QueryWrapper queryWrapper1=new QueryWrapper();
            queryWrapper1.eq("goodsID",goodsID);
            queryWrapper1.eq("goodsGuigeTypeID",item.getId());
            item.setChildren(wscGoodsshuxinglistDao.GetGuigeNameList(goodsID,item.getId(),qiyeID));
        }
        return data;
    }
}
