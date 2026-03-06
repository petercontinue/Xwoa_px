package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.minimumchargeVo;
import com.xwcloud.cloud.model.entity.Pxminimumchargetable;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-22
 */
public interface IPxminimumchargetableService extends IService<Pxminimumchargetable> {
    public Page<minimumchargeVo> GetShoufeiBiaozhunPages(Page page, @Param("ew") Wrapper wrapper);
}
