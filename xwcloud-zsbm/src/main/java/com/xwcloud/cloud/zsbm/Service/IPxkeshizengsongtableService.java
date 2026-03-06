package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshizengsongtable;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
public interface IPxkeshizengsongtableService extends IService<Pxkeshizengsongtable> {
    List<Pxkeshizengsongtable> GetZengsongRecords(QueryWrapper wrapper);

    Pxkeshizengsongtable GetZongSongInfo(Long qiandanInfoID, Long kechengId, BigDecimal kechengPrice);

    Integer DeleteKeshizengsongByStuId(Long stuID,Long qiyeID);

    List<Pxkeshizengsongtable> GetZengsongkeshiByQiandanID(Long qiandanInfoID);

    Integer DeleteKeshizengsongByqiandanInfoID(Long qiandanInfoID,Long qiyeID);
}
