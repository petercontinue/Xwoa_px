package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.classInfoListVO;
import com.xwcloud.cloud.model.Vo.exporttuisongVO;
import com.xwcloud.cloud.model.Vo.wchatmessageVO;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-06
 */
public interface IPxtuisongtableService extends IService<Pxtuisongtable> {
    List<Pxstutable> getStuList(String IDs, long qiyeID);
    List<Pxstutable> getStuByClass(String classIds,long qiyeID);
	List<Pxstutable> getStuByCampus(String campusIds,long qiyeID);
    Page<wchatmessageVO> GetStuWechatMessagePages(Page page, QueryWrapper wrapper);
    List<searchVO> getTuisongTypeList();
    List<exporttuisongVO> SearchTuisongMessageList(QueryWrapper wrapper);
    List<classInfoListVO> GetAllClassListInfo(QueryWrapper wrapper);
}
