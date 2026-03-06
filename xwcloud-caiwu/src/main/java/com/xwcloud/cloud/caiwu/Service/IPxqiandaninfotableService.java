package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;

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

public interface IPxqiandaninfotableService extends IService<Pxqiandaninfotable> {
	List<HashMap<String,String>>getYejitongbihuanbiList( String[] moneyStyle, // 1新签；2续费；3 退费 4 转送 5 换课换出 6 换课得到
                                                         String campusID,
                                                         String startYear,
                                                         String endYear,
														 Long qiyeID
	);

	List<listVo> getstuQiandan(Long stuID, Long qiyeID);
	Page<HashMap<String,String>> getBanjitongjiPage(Page page,QueryWrapper queryWrapper);
	Page<HashMap<String,String>> getShoufeiDetail(Page page,String campusID,String classID,Long qiyeID);

	List<HashMap<String,Object>> getBanjitongjiList(String campusID,String classID,Long qiyeID);
	List<HashMap<String,Object>> getShoufeiDetailList(String campusID,String classID,Long qiyeID);
	List<HashMap<String, String>> gettuifeitongji(QueryWrapper queryWrapper, String year);
}
