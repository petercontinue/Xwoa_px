package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-14
 */
public interface IPxyxtelfromtableService extends IService<Pxyxtelfromtable> {
    List<Pxyxtelfromtable> GetAllTelFromList(long qiyeID);

    List<searchVO> getYxSearchtelFrom(Long qiyeID);

}
