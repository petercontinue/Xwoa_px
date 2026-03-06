package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxqiandanstafftable;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-26
 */
public interface IPxqiandanstafftableService extends IService<Pxqiandanstafftable> {
    Page<HashMap<String,String>> getGerenYearPage(Page page,String year,String campusID,long qiyeID);
    Page<HashMap<String,String>> getGerenMonthPage(Page page,String year,String month,String campusID,long qiyeID);
}
