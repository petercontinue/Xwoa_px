package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.assetAddVO;
import com.xwcloud.cloud.model.entity.Pxassetsaddtable;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-25
 */
public interface IPxassetsaddtableService extends IService<Pxassetsaddtable> {
    Page<assetAddVO> GetassetsAddPages(Page page, Wrapper wrapper);
}
