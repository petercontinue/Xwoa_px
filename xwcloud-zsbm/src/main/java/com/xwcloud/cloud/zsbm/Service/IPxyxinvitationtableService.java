package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.yaoyueVo;
import com.xwcloud.cloud.model.Vo.yaoyuedaofangVo;
import com.xwcloud.cloud.model.entity.Pxyxinvitationtable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxyxinvitationtableService extends IService<Pxyxinvitationtable> {
    List<Pxyxinvitationtable> GetInvitationListByStuID(long stuID);

    Page<yaoyueVo> GetinvitationByStuIDPages(Page<yaoyueVo> page, QueryWrapper<yaoyueVo> wrapper);

    Page<yaoyuedaofangVo> GetyaoyueDaofangPages(Page page, QueryWrapper wrapper);

    List<yaoyuedaofangVo> getyaoyuedaofangList(QueryWrapper wrapper);
}
