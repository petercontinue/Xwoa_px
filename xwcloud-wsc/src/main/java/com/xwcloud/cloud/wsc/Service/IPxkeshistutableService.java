package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.stuKehaoVo;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-05
 */
public interface IPxkeshistutableService extends IService<Pxkeshistutable> {
    Page<stuKehaoVo> getkeshiStu(Page page, QueryWrapper queryWrapper);
}
