package com.xwcloud.cloud.homeschool.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.PxxiangcetableVo;
import com.xwcloud.cloud.model.entity.Pxxiangcetable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxxiangcetableService extends IService<Pxxiangcetable> {

    Page<PxxiangcetableVo> getPage(Page page, Wrapper wrapper);
    List<PxxiangcetableVo> getJoinList(Wrapper wrapper);
}
