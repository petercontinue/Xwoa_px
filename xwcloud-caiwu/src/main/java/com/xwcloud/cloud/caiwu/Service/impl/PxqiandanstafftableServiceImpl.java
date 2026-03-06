package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xwcloud.cloud.caiwu.Dao.IPxqiandanstafftableDao;
import com.xwcloud.cloud.caiwu.Service.IPxqiandanstafftableService;
import com.xwcloud.cloud.common.DateUtil;
import com.xwcloud.cloud.model.entity.Pxqiandanstafftable;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-26
 */
@Service
public class PxqiandanstafftableServiceImpl extends ServiceImpl<IPxqiandanstafftableDao, Pxqiandanstafftable> implements IPxqiandanstafftableService {

    @Override
    public Page<HashMap<String, String>> getGerenYearPage(Page page, String year, String campusID,long qiyeID) {
        if (StringUtils.isBlank(year)){
            year= DateUtil.getNowYear();
        }
        return this.baseMapper.getGerenYearPage(page,year,campusID,qiyeID);
    }

    @Override
    public Page<HashMap<String, String>> getGerenMonthPage(Page page,String year,String month,String campusID,long qiyeID) {
        if (StringUtils.isBlank(year)){
            year=DateUtil.getNowYear();
        }
        if (StringUtils.isBlank(month)){
            month=DateUtil.getMonth()+1+"";
        }
        return this.baseMapper.getGerenMonthPage(page,year,month,campusID,qiyeID);
    }
}
