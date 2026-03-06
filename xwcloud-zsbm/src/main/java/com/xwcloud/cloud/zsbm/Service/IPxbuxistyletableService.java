package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxbuxistyletable;

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
    List<searchVO> getbuxistyleList(Long qiyeID);
}
