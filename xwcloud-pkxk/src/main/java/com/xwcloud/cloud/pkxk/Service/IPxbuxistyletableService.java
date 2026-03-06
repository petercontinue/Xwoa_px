package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;
import com.xwcloud.cloud.model.pkxk.Vo.buxistyleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-20
 */
public interface IPxbuxistyletableService extends IService<Pxbuxistyletable> {
    List<buxistyleVo> getbuxiStyleList(Long qiyeID);
}
