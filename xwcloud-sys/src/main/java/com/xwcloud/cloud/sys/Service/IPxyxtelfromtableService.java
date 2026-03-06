package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
public interface IPxyxtelfromtableService extends IService<Pxyxtelfromtable> {
    List<Pxstutable> GetstuBytelFromID(String yxFromID);
}
