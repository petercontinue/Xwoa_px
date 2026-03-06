package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxsubjecttableDao;
import com.xwcloud.cloud.caiwu.Service.IPxsubjecttableService;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-19
 */
@Service
public class PxsubjecttableServiceImpl extends ServiceImpl<IPxsubjecttableDao, Pxsubjecttable> implements IPxsubjecttableService {
    @Override
    public List<searchVO> getSubject(Long qiyeID) {
        return this.baseMapper.getSubject(qiyeID);
    }

    @Override
    public Page<HashMap<String, Object>> getSubjectBmTongji(Page page, QueryWrapper wrapper) {
        return this.baseMapper.getSubjectBmTongji(page, wrapper);
    }
}
