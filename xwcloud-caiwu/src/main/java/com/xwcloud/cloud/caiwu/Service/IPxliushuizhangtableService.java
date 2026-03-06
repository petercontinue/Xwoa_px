package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxliushuizhangtableService extends IService<Pxliushuizhangtable> {
    Page<HashMap<String, String>> getCampusYearPage(Page page, String year, Long qiyeID);

    Page<HashMap<String, String>> getCampusMonthPage(Page page, String year, String month, Long qiyeID);

    Page<HashMap<String, String>> getGerenMonthDetailPage(Page page, String year, String month, String staffID, String campusID, Long qiyeID);

    Page<HashMap<String, String>> getDetailedIncomeDetailsPage(Page page, String liushuiID, Long qiyeID);

    Page<HashMap<String, String>> getKucunxuefei(Page page, Long qiyeID);

    // 财务流水
    Page<HashMap<String, String>> getLiushuiPage(Page page, Wrapper wrapper);

    // 日统计
    List<HashMap<String, String>> getLiushuiDay(String Ym, Long qiyeID);

    // 日收入对账
    List<HashMap<String, String>> getShouruDay(String Ym, Long qiyeID);

    // 盈亏总账
    List<HashMap<String, Object>> getYinkuiList(String year, String campusID, Long qiyeID);

    // 杂费详细
    Page<HashMap<String, String>> getDetailedZafeiDetailsPage(Page page, Wrapper wrapper);

    // 商品详细
    Page<HashMap<String, String>> getDetailedShangpingDetailsPage(Page page, Wrapper wrapper);

    List<HashMap<String, Object>> getGerenMonthDetailList(String year, String month, String staffID, String campusID, Long qiyeID);

    List<HashMap<String, String>> getShouzhiStyleList(Long qiyeID, String shouzhistyle);

    List<Pxpaymoneystyletable> getPayMoneyStyleList(Long qiyeID);

    HashMap<String, String> getLiushui(String id, Long qiyeID);

    List<HashMap<String, Object>> getLiushuiList(Wrapper wrapper);

    List<listVo> GetAllSearchshouzhiStyleList(long qiyeID);

    //查询流水总金额
    BigDecimal GetLiushuizhangMoney(QueryWrapper wrapper);
}
