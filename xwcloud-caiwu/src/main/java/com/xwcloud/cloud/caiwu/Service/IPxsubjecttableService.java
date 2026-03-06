package com.xwcloud.cloud.caiwu.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-19
 */
public interface IPxsubjecttableService extends IService<Pxsubjecttable> {
    List<searchVO> getSubject(Long qiyeID);

    Page<HashMap<String, Object>> getSubjectBmTongji(Page page, QueryWrapper wrapper);
}
