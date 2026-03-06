package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshizhuansongtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxkeshizhuansongtableService extends IService<Pxkeshizhuansongtable> {
    List<Pxkeshizhuansongtable> GetStuzhuansongInfo(Long songstuID, Long shoustuID);
}
