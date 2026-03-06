package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.haveTimeCrVO;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-24
 */
public interface IPxclassroomtableService extends IService<Pxclassroomtable> {
    Page<haveTimeCrVO> gethavetimeclassRoomList(Page page, QueryWrapper queryWrapper);
}
