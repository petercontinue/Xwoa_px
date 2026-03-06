package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxclasstimestyletable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-11
 */
public interface IPxclasstimestyletableService extends IService<Pxclasstimestyletable> {
    List<searchVO> GetAllclasstimestyleList(long qiyeID);
}
