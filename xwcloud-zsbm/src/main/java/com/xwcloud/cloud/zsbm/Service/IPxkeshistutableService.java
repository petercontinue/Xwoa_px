package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
public interface IPxkeshistutableService extends IService<Pxkeshistutable> {
    List<Pxkeshistutable> getAllStuKeshiByStuID(Long stuID);

    List<Pxkeshistutable> GetKehaostuByBuxikechengID(Long buxikechengID);

    List<Pxkeshistutable> GetKeshiStuList(long stuID);

}
