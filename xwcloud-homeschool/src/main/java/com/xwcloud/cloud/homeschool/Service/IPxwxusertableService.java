package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.PxwxusertableVo;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxwxusertable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxwxusertableService extends IService<Pxwxusertable> {
	Page<PxwxusertableVo> getPage(Page page, Wrapper wrapper);
	List<PxwxusertableVo> getJoinList(Wrapper wrapper);
	Pxstutable getStuById(long id, long qiyeID);
	boolean changeActivity(Pxstutable stu);
	Integer UpdateStuParentTel(long id,String newTel);
	Integer UpdateStuWechatPassword(String newpasswd,long stuId,long qiyeID);
	Integer DeleteWxuser(String tel,long qiyeID);
}
