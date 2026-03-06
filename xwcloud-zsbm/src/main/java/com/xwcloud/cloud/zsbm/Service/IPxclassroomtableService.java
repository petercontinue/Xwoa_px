package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-07
 */
public interface IPxclassroomtableService extends IService<Pxclassroomtable> {
	List<Pxclassroomtable> getClassRoom(QueryWrapper<Pxclassroomtable> wrapper);
}
