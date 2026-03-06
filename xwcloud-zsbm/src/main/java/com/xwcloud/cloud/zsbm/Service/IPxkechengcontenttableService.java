package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.kechengContentVo;
import com.xwcloud.cloud.model.entity.Pxkechengcontenttable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxkechengcontenttableService extends IService<Pxkechengcontenttable> {
    Page<kechengContentVo> getKechengContentPages(Page page, QueryWrapper wrapper);

    List<Pxkechengcontenttable> getKechengcontentBykcidandpx(long kechengID, Integer contentPaixu);
}
