package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxyxtelleveltable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
public interface IPxyxtelleveltableService extends IService<Pxyxtelleveltable> {
    List<Pxstutable> GetStuBytelLevelID(String telLevelID);
}
