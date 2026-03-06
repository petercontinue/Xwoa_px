package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-20
 */
public interface IPxbuxistyletableService extends IService<Pxbuxistyletable> {
    Pxbuxistyletable getOne(Long qiyeID);
    List<Pxbuxistyletable> selectbxstyle(QueryWrapper queryWrapper);
    List<listVo> getAllbuxiStyleList(Long qiyeID);
}
