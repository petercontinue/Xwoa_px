package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxoldschoolteachertableDao;
import com.xwcloud.cloud.caiwu.Service.IPxoldschoolteachertableService;
import com.xwcloud.cloud.model.entity.Pxoldschoolteachertable;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-02
 */
@Service
public class PxoldschoolteachertableServiceImpl extends ServiceImpl<IPxoldschoolteachertableDao, Pxoldschoolteachertable> implements IPxoldschoolteachertableService {

    @Override
    public Page<HashMap<String, String>> getOldteacherPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getOldteacherPage(page, wrapper);
    }

    @Override
    public List<HashMap<String, String>> getOldteacherBili(Wrapper wrapper) {
        return this.baseMapper.getOldteacherBili(wrapper);
    }
}
