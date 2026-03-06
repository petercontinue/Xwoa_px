package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.buyWpVo;
import com.xwcloud.cloud.model.entity.Pxqiandansupplies;

import com.xwcloud.cloud.model.Vo.shangpinliushuiVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxqiandansuppliesService extends IService<Pxqiandansupplies> {
    Pxqiandansupplies GetQiandanSuppliesByqdIDandSupID(Long QiandaninfoID, Long TeachingSuppliesID);

    int deleteqiandansuppliesByQiandanID(Long QiandaninfoID);

    Page<shangpinliushuiVo> GetQiandanSuppliesPages(Page page, QueryWrapper wrapper);

    List<shangpinliushuiVo> GetQiandanSuppliesList(QueryWrapper wrapper);

    List<buyWpVo> GetAllWupingList(long qiandanID);

    String getWP(@Param("ew") Wrapper wrapper);
}
