package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.wechatMessageVO;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-15
 */
public interface IPxtuisongtableService extends IService<Pxtuisongtable> {
    Page<wechatMessageVO> GetyuangongWechatMessagesPages(Page page, QueryWrapper wrapper);
}
