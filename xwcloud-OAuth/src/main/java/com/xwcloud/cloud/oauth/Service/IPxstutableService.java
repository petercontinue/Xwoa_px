package com.xwcloud.cloud.oauth.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstutable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-04
 */
public interface IPxstutableService extends IService<Pxstutable> {
    Pxstutable getStuInfoData(QueryWrapper wrapper);
}
