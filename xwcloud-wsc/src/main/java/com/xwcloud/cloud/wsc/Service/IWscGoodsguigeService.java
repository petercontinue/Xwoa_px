package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.goodsInfoVO;
import com.xwcloud.cloud.model.entity.WscGoodsguige;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscGoodsguigeService extends IService<WscGoodsguige> {
    List<goodsInfoVO> GetgoodsGuigeList(long qiyeID, long goodsID);

    List<goodsInfoVO> SearchAllGuigeList(long qiyeID,long goodsID);
}
