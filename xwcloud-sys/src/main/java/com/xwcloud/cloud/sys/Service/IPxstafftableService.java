package com.xwcloud.cloud.sys.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.asstestyleVO;
import com.xwcloud.cloud.model.Vo.staffBirthVo;
import com.xwcloud.cloud.model.Vo.staffinfoVo;
import com.xwcloud.cloud.model.Vo.stafftelVo;
import com.xwcloud.cloud.model.entity.Pxstafftable;

import java.util.HashMap;
import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinqi
 * @since 2020-10-18
 */
public interface IPxstafftableService extends IService<Pxstafftable> {
	public int updateStaffState(int staffState,Long id);

	Page<stafftelVo> getstaffTel(Page page, QueryWrapper wrapper);

	public int UpdatetaffTel(String staffTel,Long id );

	public  Page<staffBirthVo> getStaffBirth(Page page, QueryWrapper wrapper);

	public int UpdateStaffBirthday(String staffBirthday,Long id);

	List<staffBirthVo> GetStaffBirthList(QueryWrapper wrapper);

	Page<staffinfoVo> GetstaffInfoPages(Page page, QueryWrapper wrapper);


	List<Pxstafftable> GetStaffListByCampusID(Long campusID);

	List<stafftelVo> GetstafftelList( QueryWrapper wrapper);

	List<Pxstafftable> GetstaffInfoBycampusidAndstaffpostID(Long campusID,Long staffPostID);

	List<asstestyleVO> GetAllStaffList(Long qiyeID);

	List<String> GetStaffInfoDetail(long qiyeID, long staffPostID);

	List<HashMap<String, String>> getnowxTeaList(QueryWrapper queryWrapper);
}
