package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxevaluationtableVo;
import com.xwcloud.cloud.model.entity.*;


import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxevaluationtableService extends IService<Pxevaluationtable> {
	Page<HashMap<String,String>> getPage(Page<HashMap<String,String>> page, QueryWrapper<HashMap<String,String>> queryWrapper);
	List<PxevaluationtableVo> getJoinList(QueryWrapper<Pxevaluationtable> queryWrapper);
	List<Pxcampustable> getCampusList(Wrapper wrapper);
	List<Pxclasstable> getClassList(Wrapper wrapper);
	List<Pxstutable> getStuList(Wrapper wrapper);
	List<HashMap<String,String>>getPeriodList(Wrapper wrapper);
	List<Pxstafftable> getStaffList(Wrapper wrapper);
	Pxkeshiteachertable getTeacherkeshi(String campusID, String classID, String teacherID, String haveClassDate, String startLessonDateTime, String endLessonDateTime,Long qiyeID);
	List<HashMap<String,String>> getPublicStaffList(Wrapper wrapper);
	List<HashMap<String,String>>getClassRoomList(Wrapper wrapper);
	List<HashMap<String,String>> getPublicStuList(Wrapper wrapper);
	List<HashMap<String,String>>getPublicStaffPostList(Wrapper wrapper);
	List<HashMap<String,String>> getPublicKemuList(Wrapper wrapper);
	List<HashMap<String,String>> getStugradeList(Wrapper wrapper);
}
