package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxyueketeacherfabutableDao;
import com.xwcloud.cloud.homeschool.Service.IPxyueketeacherfabutableService;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabutable;
import com.xwcloud.cloud.model.Vo.PxyueketeacherfabutableVo;
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
public class PxyueketeacherfabutableServiceImpl extends ServiceImpl<IPxyueketeacherfabutableDao, Pxyueketeacherfabutable> implements IPxyueketeacherfabutableService {

    @Override
    public Page<PxyueketeacherfabutableVo> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page,wrapper);
    }

    @Override
    public List<PxyueketeacherfabutableVo> getJoinList( Wrapper wrapper) {
        return this.baseMapper.getJoinList(wrapper);
    }

    @Override
    public List<HashMap<String, String>> getKechengList(Wrapper wrapper) {
        return this.baseMapper.getKechengList(wrapper);
    }
}
