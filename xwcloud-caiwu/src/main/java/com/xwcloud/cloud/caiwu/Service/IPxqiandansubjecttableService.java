package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxqiandansubjecttableService extends IService<Pxqiandansubjecttable> {
	Page<HashMap<String,String>> getKemushoufeiPage(Page page,QueryWrapper queryWrapper);
	Page<HashMap<String,String>>getKemukehaoPage(Page page,QueryWrapper queryWrapper);
	Page<HashMap<String,String>>getKemuyufeePage(Page page, QueryWrapper queryWrapper);
	List<HashMap<String,Object>> getKemushoufeiList(String campusID, String kemuName,String startDate,String endDate, String qiyeID);
	List<HashMap<String,Object>> getKemukehaoList(String campusID, String kemuName,String startDate,String endDate, String qiyeID);
	List<HashMap<String,Object>> getKemuyufeeList(String campusID, String kemuName,String startDate,String endDate, String qiyeID);

	List<HashMap<String,String>> getKumuStu(String startDate,String endDate,long qiyeID);
	List<HashMap<String,String>> getKumuXinqian(String startDate,String endDate,long qiyeID,int ...moneyStyle);
	List<HashMap<String,String>> getKumuKeshi(String startDate,String endDate,long qiyeID);
	List<HashMap<String,String>> getKumukexiao(String startDate,String endDate,long qiyeID);
	Page<HashMap<String,String>> getTingkeStu(Page page, Wrapper wrapper);
	List<HashMap<String,Object>> getTingkeStuList(Wrapper wrapper);

	Page<HashMap<String, Object>> getKemuShoufeiTongji(Page page, QueryWrapper wrapper);

	Page<HashMap<String, Object>> getSubjectYuETongji(Page page, QueryWrapper wrapper);

	Page<HashMap<String, Object>>  getSubjectBmByCampusAndSubject(Page page, QueryWrapper<HashMap<String, Object>> wrapper);
}
