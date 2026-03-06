package com.xwcloud.cloud.wsc.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WhdChoujiangJiangpinVo;
import com.xwcloud.cloud.model.Vo.allgailvVo;
import com.xwcloud.cloud.model.entity.WhdChoujiangJiangping;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWhdChoujiangJiangpingService extends IService<WhdChoujiangJiangping> {
    Page<WhdChoujiangJiangpinVo> getWhdChoujiangJiangpinPage(Page page, QueryWrapper wrapper);

    allgailvVo getallgailv(Long huodongID, Long qiyeID);
}
