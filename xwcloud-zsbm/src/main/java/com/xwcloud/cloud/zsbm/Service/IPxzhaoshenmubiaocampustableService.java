package com.xwcloud.cloud.zsbm.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.zhaoshengStaffMubaoVo;
import com.xwcloud.cloud.model.Vo.zhaoshengmubiaoVo;
import com.xwcloud.cloud.model.entity.Pxzhaoshenmubiaocampustable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-26
 */
public interface IPxzhaoshenmubiaocampustableService extends IService<Pxzhaoshenmubiaocampustable> {
    Page<zhaoshengmubiaoVo> GetZhaoshengmubiaoCampusPages(Page page, QueryWrapper wrapper);

    List<Pxzhaoshenmubiaocampustable> GetListcampusMubiao(Long campusID, String yearMonth);

    Page<zhaoshengStaffMubaoVo> getStaffZhaoshengmubiaoPages(Page<zhaoshengStaffMubaoVo> page, QueryWrapper<zhaoshengStaffMubaoVo> wrapper);
}
