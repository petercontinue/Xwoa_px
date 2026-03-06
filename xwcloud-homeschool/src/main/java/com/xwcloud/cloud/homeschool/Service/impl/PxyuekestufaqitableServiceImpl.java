package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxyuekestufaqitableDao;
import com.xwcloud.cloud.homeschool.Service.IPxyuekestufaqitableService;
import com.xwcloud.cloud.model.entity.Pxyuekestufaqitable;
import com.xwcloud.cloud.model.Vo.PxyuekestufaqitableVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
@Service
public class PxyuekestufaqitableServiceImpl extends ServiceImpl<IPxyuekestufaqitableDao, Pxyuekestufaqitable> implements IPxyuekestufaqitableService {

    @Override
    public Page<PxyuekestufaqitableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxyuekestufaqitableVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getClassRoomList(Wrapper wrapper) {
        return this.baseMapper.getClassRoomList(wrapper);
    }
}
