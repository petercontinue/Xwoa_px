package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.yxqiandanVo;
import com.xwcloud.cloud.model.entity.Pxyxqiandantable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
public interface IPxyxqiandantableService extends IService<Pxyxqiandantable> {
    Page<yxqiandanVo> GetAllLiuyanyixiangqiandanPages(Page page, QueryWrapper wrapper);

    List<yxqiandanVo> GetyixiangqiandanList(QueryWrapper wrapper);
}
