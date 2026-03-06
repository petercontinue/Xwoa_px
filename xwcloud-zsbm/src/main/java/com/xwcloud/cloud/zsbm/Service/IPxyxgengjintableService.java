package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.genjinInfoVo;
import com.xwcloud.cloud.model.Vo.genjinliushuiVo;
import com.xwcloud.cloud.model.entity.Pxyxgengjintable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
public interface IPxyxgengjintableService extends IService<Pxyxgengjintable> {
    List<Pxyxgengjintable> GetAllYixiangGenjinByStuID(long stuID);

    Page<genjinInfoVo> GetgenjinInfoPages(Page page, @Param("ew") QueryWrapper queryWrapper);

    int DeleteGenjinRecordsBystuID(long stuID);

    Page<genjinliushuiVo> GegenjinLiushuiPages(Page page, QueryWrapper wrapper);

    List<genjinliushuiVo> GetExportGenjinliushui(QueryWrapper wrapper);
}
