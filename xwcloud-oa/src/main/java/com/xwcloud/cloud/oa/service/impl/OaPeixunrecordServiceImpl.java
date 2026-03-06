package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaPeixunrecord;
import com.xwcloud.cloud.model.OA.Vo.PeixunrecordVo;
import com.xwcloud.cloud.oa.Dao.IOaPeixunrecordDao;
import com.xwcloud.cloud.oa.service.IOaPeixunrecordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-02
 */
@Service
public class OaPeixunrecordServiceImpl extends ServiceImpl<IOaPeixunrecordDao, OaPeixunrecord> implements IOaPeixunrecordService {

    @Autowired
    private IOaPeixunrecordDao iOaPeixunrecordDao;

    //分页获取所有的培训记录信息
    @Override
    public IPage<PeixunrecordVo> getAllPeixunrecordInfo(Page<PeixunrecordVo> page, @Param("ew") Wrapper wrapper) {
        return iOaPeixunrecordDao.getAllPeixunrecordInfo(page, wrapper);
    }

    //根据id获取一条培训记录信息
    @Override
    public PeixunrecordVo getOnePeixunrecordById(Long id) {
        return iOaPeixunrecordDao.getOnePeixunrecordById(id);
    }
}
