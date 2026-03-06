package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaTuifeirecord;
import com.xwcloud.cloud.model.OA.Vo.TuifeirecordVo;
import com.xwcloud.cloud.oa.Dao.IOaTuifeirecordDao;
import com.xwcloud.cloud.oa.service.IOaTuifeirecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-01
 */
@Service
public class OaTuifeirecordServiceImpl extends ServiceImpl<IOaTuifeirecordDao, OaTuifeirecord> implements IOaTuifeirecordService {

    @Autowired
    private IOaTuifeirecordDao iOaTuifeirecordDao;

    //往退费记录表里插入一条记录
    public boolean addTuifeiReecord(OaTuifeirecord oaTuifeirecord) {
        int insert = baseMapper.insert(oaTuifeirecord);
        return insert > 0 ? true : false;
    }

    //分页查询所有的退费记录
    @Override
    public IPage<TuifeirecordVo> getAllTuifeirecordInfo(Page<TuifeirecordVo> page, @Param("ew") Wrapper wrapper) {
        return iOaTuifeirecordDao.getAllTuifeirecordInfo(page, wrapper);
    }

    //根据id查询一条退费记录
    @Override
    public TuifeirecordVo getOneTuifeirecordById(Long id) {
        return iOaTuifeirecordDao.getOneTuifeirecordById(id);
    }
}

