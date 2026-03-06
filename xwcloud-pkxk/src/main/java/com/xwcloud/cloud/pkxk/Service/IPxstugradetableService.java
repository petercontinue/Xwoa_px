package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstugradetable;

import com.xwcloud.cloud.model.pkxk.Vo.stugradeVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-20
 */
public interface IPxstugradetableService extends IService<Pxstugradetable> {
    List<stugradeVo> getgradeList(Long qiyeID);
}
