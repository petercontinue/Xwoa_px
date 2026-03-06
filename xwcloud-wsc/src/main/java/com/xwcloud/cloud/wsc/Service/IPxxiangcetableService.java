package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxxiangcetableVo;
import com.xwcloud.cloud.model.entity.Pxxiangcetable;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-13
 */
public interface IPxxiangcetableService extends IService<Pxxiangcetable> {
    Page<PxxiangcetableVo> getPage(Page page, @Param("ew") Wrapper wrapper);
}
