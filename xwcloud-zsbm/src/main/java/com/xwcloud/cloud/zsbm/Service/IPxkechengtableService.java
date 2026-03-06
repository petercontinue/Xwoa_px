package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.bixikechengxialaVO;
import com.xwcloud.cloud.model.Vo.kechengListVo;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
public interface IPxkechengtableService extends IService<Pxkechengtable> {
    public Page<kechengListVo> getAllKechengPages(Page page, QueryWrapper wrapper);

    int UpdateKechengState(Long Id, boolean State);

    List<kechengListVo> getAllKechengList(@Param("ew") Wrapper wrapper);

    Pxkechengtable GetKechengById(Long Id);

    List<bixikechengxialaVO> GetBuxikechengByCampusID(long campusID, long qiyeID, Integer jifeiStyleID);

    List<Pxkechengtable> getYxChabanKc(QueryWrapper<Pxkechengtable> wrapper);

}
