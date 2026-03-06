package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.yuekeTeacherVO;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabutable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-15
 */
public interface IPxyueketeacherfabutableService extends IService<Pxyueketeacherfabutable> {
    Page<yuekeTeacherVO> GetYuekTeacherFabuPages(Page page, QueryWrapper wrapper);

    List<yuekeTeacherVO> GetYuekDetailInfo(QueryWrapper wrapper);
}
