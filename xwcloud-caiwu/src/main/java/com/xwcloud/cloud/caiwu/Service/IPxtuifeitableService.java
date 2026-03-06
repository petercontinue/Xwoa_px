package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxtuifeitableVo;
import com.xwcloud.cloud.model.Vo.alltuizfVo;
import com.xwcloud.cloud.model.entity.Pxtuifeitable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxtuifeitableService extends IService<Pxtuifeitable> {
    Page<PxtuifeitableVo> getPage(Page page, Wrapper wrapper);

    List<PxtuifeitableVo> getJoinList(Wrapper wrapper);

    // 课程数据
    String getSumKechengFee(QueryWrapper wrapper, String qiyeID);

    String getKeshiXiaohaoSum(QueryWrapper wrapper, String qiyeID);

    String getJieshouKeshiSum(QueryWrapper wrapper, String stuID, String qiyeID);

    String getZhuansongKeshiSum(QueryWrapper wrapper, String stuID, String qiyeID);

    // 充值数据
    String getJiaofei(String stuID, String qiyeID);

    String getZengsong(String stuID, String qiyeID);

    String getXiaohaoFee(String stuID, String qiyeID);

    String getYuFee(String stuID, String qiyeID);

    // 商品数据
    String getCommodityFee(QueryWrapper wrapper, String qiyeID);

    // 杂费
    String getOtherFee(QueryWrapper wrapper, String qiyeID);

    // 代金券
    String getDaijinquan(QueryWrapper wrapper, String qiyeID);

    // 优惠金额
    String getYouhuiFee(QueryWrapper wrapper, String qiyeID);

    // 课程详细列表
    List<HashMap<String, String>> getKechengfeiDetail(QueryWrapper wrapper);

    // 充值详细表
    List<HashMap<String, String>> getChongzhiDetail(QueryWrapper wrapper, Long qiyeID);

    // 商品详细
    List<HashMap<String, String>> getShangpinDetail(QueryWrapper wrapper);

    // 杂费详细
    List<HashMap<String, String>> getZafeiDetail(QueryWrapper wrapper);

    // 课时消耗详细
    List<HashMap<String, String>> getKeshiDetail(QueryWrapper wrapper);

    // 接受他人赠送课时详细
    List<HashMap<String, String>> getTransferDetail(QueryWrapper wrapper);

    // 获取课程列表
    List<HashMap<String, String>> getKechengList(QueryWrapper wrapper);

    // 获取商品列表
    List<HashMap<String, String>> getCommodityList(QueryWrapper wrapper);

    // 获取杂费列表
    List<HashMap<String, String>> getZafeiList(QueryWrapper wrapper);

    List<alltuizfVo> getalltuizf(Wrapper wrapper);

}
