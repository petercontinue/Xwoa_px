package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxliushuizhangtableDao;
import com.xwcloud.cloud.caiwu.Service.IPxliushuizhangtableService;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxliushuizhangtableServiceImpl extends ServiceImpl<IPxliushuizhangtableDao, Pxliushuizhangtable> implements IPxliushuizhangtableService {

    @Override
    public Page<HashMap<String, String>> getCampusYearPage(Page page, String yearStr, Long qiyeID) {
        String yearstr = yearStr;
        if (StringUtils.isBlank(yearStr)) {
            yearstr = DateUtil.getNowYear();
        }
        return this.baseMapper.getCampusYearPage(page, yearstr, qiyeID);
    }

    @Override
    public Page<HashMap<String, String>> getCampusMonthPage(Page page, String yearStr, String monthStr, Long qiyeID) {
        String yearstr = yearStr;
        String monthstr = monthStr;
        if (StringUtils.isBlank(yearStr)) {
            yearstr = DateUtil.getNowYear();
        }
        if (StringUtils.isBlank(monthStr)) {
            monthstr = DateUtil.getMonth() + 1 + "";
        }
        return this.baseMapper.getCampusMonthPage(page, yearstr, monthstr, qiyeID);
    }

    @Override
    public Page<HashMap<String, String>> getGerenMonthDetailPage(Page page, String year, String month, String staffID, String campusID, Long qiyeID) {
        return this.baseMapper.getGerenMonthDetailPage(page, year, month, staffID, campusID, qiyeID);
    }

    @Override
    public Page<HashMap<String, String>> getDetailedIncomeDetailsPage(Page page, String liushuiID, Long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("liushui.id", liushuiID);
        queryWrapper.eq("liushui.qiyeID", qiyeID);
        return this.baseMapper.getDetailedIncomeDetailsPage(page, queryWrapper);
    }

    @Override
    public Page<HashMap<String, String>> getKucunxuefei(Page page, Long qiyeID) {
        return this.baseMapper.getKucunxuefei(page, qiyeID);
    }


    @Override
    public Page<HashMap<String, String>> getLiushuiPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<HashMap<String, String>> getLiushuiDay(String Ym, Long qiyeID) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = null;
        try {
            dt = sdf.parse(Ym);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, 1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return this.baseMapper.getLiushuiDay(Ym, reStr, qiyeID);
    }

    @Override
    public List<HashMap<String, String>> getShouruDay(String Ym, Long qiyeID) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = null;
        try {
            dt = sdf.parse(Ym);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, 1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        List<Pxpaymoneystyletable> paystyleList = this.baseMapper.getPaystyleList(queryWrapper);
        for (Integer i = 0; i < 2; i++) {
            Pxpaymoneystyletable pxpaymoneystyletable = new Pxpaymoneystyletable();
            if (i == 0) {
                pxpaymoneystyletable.setMoneystyleName("余额支付");
                pxpaymoneystyletable.setId(Long.valueOf(-2));
            }
            if (i == 1) {
                pxpaymoneystyletable.setMoneystyleName("小程序支付");
                pxpaymoneystyletable.setId(Long.valueOf(-1));
            }
            paystyleList.add(pxpaymoneystyletable);
        }
        return this.baseMapper.getShouruDay(Ym, reStr, paystyleList, qiyeID);
    }

    @Override
    public List<HashMap<String, Object>> getYinkuiList(String year, String campusID, Long qiyeID) {
        List<HashMap<String, Object>> allList = new ArrayList<>();
        List<HashMap<String, Object>> xinqianandxvqianList = this.baseMapper.getxinqianandxvqianList(year, campusID, qiyeID);
        allList.addAll(xinqianandxvqianList);
        List<HashMap<String, Object>> xinqianandxvqianListSum = this.baseMapper.getxinqianandxvqianListSum(year, campusID, qiyeID);
        allList.addAll(xinqianandxvqianListSum);
        List<HashMap<String, Object>> xinqianandxvqianListOther = this.baseMapper.getxinqianandxvqianListother(year, campusID, qiyeID);
        allList.addAll(xinqianandxvqianListOther);
        List<HashMap<String, Object>> shourudahejiList = this.baseMapper.getShourudahejiList(year, campusID, qiyeID);
        allList.addAll(shourudahejiList);
        List<HashMap<String, Object>> zhichuList = this.baseMapper.getZhichuList(year, campusID, qiyeID);
        allList.addAll(zhichuList);
        List<HashMap<String, Object>> zhichudahejiList = this.baseMapper.getZhichudaheji(year, campusID, qiyeID);
        allList.addAll(zhichudahejiList);
        List<HashMap<String, Object>> keshiList = this.baseMapper.getKeshishouru(year, campusID, qiyeID);
        allList.addAll(keshiList);
        List<HashMap<String, Object>> xianjinliuList = this.baseMapper.getxianjinliu(year, campusID, qiyeID);
        allList.addAll(xianjinliuList);
        List<HashMap<String, Object>> lirunList = this.baseMapper.getLirun(year, campusID, qiyeID);
        allList.addAll(lirunList);
        return allList;
    }

    @Override
    public Page<HashMap<String, String>> getDetailedZafeiDetailsPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getDetailedZafeiDetailsPage(page, wrapper);
    }

    @Override
    public Page<HashMap<String, String>> getDetailedShangpingDetailsPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getDetailedShangpingDetailsPage(page, wrapper);
    }

    @Override
    public List<HashMap<String, Object>> getGerenMonthDetailList(String year, String month, String staffID, String campusID, Long qiyeID) {
        return this.baseMapper.getGerenMonthDetailList(year, month, staffID, campusID, qiyeID);
    }

    @Override
    public List<HashMap<String, String>> getShouzhiStyleList(Long qiyeID, String shouzhistyle) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        if (StringUtils.isNotBlank(shouzhistyle)) {
            queryWrapper.eq("isshouOrzhichu", shouzhistyle);
        }
        return this.baseMapper.getShouzhistyleList(queryWrapper);
    }

    @Override
    public List<Pxpaymoneystyletable> getPayMoneyStyleList(Long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", qiyeID);
        return this.baseMapper.getPaystyleList(queryWrapper);
    }

    @Override
    public HashMap<String, String> getLiushui(String id, Long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("liushui.id", id);
        queryWrapper.eq("liushui.qiyeID", qiyeID);
        return this.baseMapper.getLiushui(queryWrapper);
    }

    @Override
    public List<HashMap<String, Object>> getLiushuiList(Wrapper wrapper) {
        return this.baseMapper.getLiushuiList(wrapper);
    }

    @Override
    public List<listVo> GetAllSearchshouzhiStyleList(long qiyeID) {
        return this.baseMapper.GetAllSearchshouzhiStyleList(qiyeID);
    }

    @Override
    public BigDecimal GetLiushuizhangMoney(QueryWrapper wrapper) {
        return this.baseMapper.GetLiushuizhangMoney(wrapper);
    }
}
