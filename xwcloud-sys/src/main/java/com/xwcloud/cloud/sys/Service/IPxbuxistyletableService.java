package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;
import com.xwcloud.cloud.model.entity.Pxkechengtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinqi
 * @since 2020-10-20
 */
public interface IPxbuxistyletableService extends IService<Pxbuxistyletable> {
    List<Pxkechengtable> GetKechengByBuxistyleID(String buxiStyleID);
}
