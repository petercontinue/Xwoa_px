package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxclasstimestyletable;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-20
 */
public interface IPxclasstimestyletableService extends IService<Pxclasstimestyletable> {
    List<Pxclasstimestyletable> selectclassTime(QueryWrapper queryWrapper);
    List<listVo> getAllClassTimeList(Long qiyeID);
}
