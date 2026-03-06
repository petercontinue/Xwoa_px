package com.xwcloud.cloud.homeschool.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.homeschool.Dao.IPxstuhuifangtableDao;
import com.xwcloud.cloud.homeschool.Dao.IPxstutableDao;
import com.xwcloud.cloud.homeschool.Service.IPxstuhuifangtableService;
import com.xwcloud.cloud.model.Vo.PxstuHuifang;
import com.xwcloud.cloud.model.Vo.oldstuhuifangVO;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxstuhuifangtable;
import com.xwcloud.cloud.model.Vo.PxstuhuifangVo;
import com.xwcloud.cloud.model.Vo.PxstutablekechengVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
@Service
public class PxstuhuifangtableServiceImpl extends ServiceImpl<IPxstuhuifangtableDao, Pxstuhuifangtable> implements IPxstuhuifangtableService {

    @Autowired
    IPxstutableDao iPxstutableDao;

    @Override
    public Page<PxstuhuifangVo> getStuPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getStuPage(page, wrapper);
    }

    @Override
    public Page<PxstuhuifangVo> getStuPageByClass(Page page, Wrapper wrapper) {
        return this.baseMapper.getStuPageByClass(page, wrapper);
    }

    @Override
    public Page<oldstuhuifangVO> getPage(Page page, Wrapper wrapper) {
        return this.baseMapper.getPage(page, wrapper);
    }

    @Override
    public List<PxstuhuifangVo> getJoinList(Wrapper wrapper) {
        return this.baseMapper.getStuhuifangList(wrapper);
    }

    @Override
    public List<PxstuhuifangVo> getStuJoinList(Wrapper wrapper) {
        return this.baseMapper.getStuList(wrapper);
    }

    @Override
    public PxstuHuifang getStu(long stuID,long qiyeID) {
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("stu.id",stuID);
        queryWrapper.eq("stu.qiyeID",qiyeID);
        return this.baseMapper.getStu(queryWrapper);
    }

    @Override
    public List<PxstutablekechengVo> getStuKechengList(long stuID,long qiyeID) {
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("stu.id",stuID);
        queryWrapper.eq("stu.qiyeID",qiyeID);
        return this.baseMapper.getStuKechengList(queryWrapper);
    }

    @Override
    public int editStu(Pxstutable pxstutable) {
        return iPxstutableDao.updateById(pxstutable);
    }
}
