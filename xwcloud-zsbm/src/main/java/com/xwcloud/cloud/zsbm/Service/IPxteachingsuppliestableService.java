package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.qiandanwpVO;
import com.xwcloud.cloud.model.Vo.teachingSuppliesVo;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliestable;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
public interface IPxteachingsuppliestableService extends IService<Pxteachingsuppliestable> {
    Pxteachingsuppliestable GetTeachingSuppliesByName(String Name);

    int UpdateteachingsuppliesKucun(Long ID, BigDecimal kucun);

    List<Pxteachingsuppliestable> getTeachingSuppliesByTiaoma(String changpinTiaoma);

    Page<teachingSuppliesVo> GetTeachingSuppliesPages(Page page, QueryWrapper wrapper);

    Pxteachingsuppliestable getTeachingSupplies(QueryWrapper wrapper);

    List<teachingSuppliesVo> GetTeachingSuppliesList(QueryWrapper wrapper);

    List<qiandanwpVO> getAllWupingList(long campusID, long qiyeID);
}
