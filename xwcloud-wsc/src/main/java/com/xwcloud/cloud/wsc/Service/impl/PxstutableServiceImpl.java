package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.wsc.Dao.IPxstutableDao;
import com.xwcloud.cloud.wsc.Service.IPxstutableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-07
 */
@Service
public class PxstutableServiceImpl extends ServiceImpl<IPxstutableDao, Pxstutable> implements IPxstutableService {
    @Autowired
    IPxstutableDao iPxstutableDao;

    @Override
    public List<baomingrecordsVO> GetAllbaomingRecords(QueryWrapper wrapper) {
        return iPxstutableDao.GetAllbaomingRecords(wrapper);
    }

    @Override
    public List<remainkeshiWscVO> GetStuAllRemainkeshi(QueryWrapper wrapper) {
        return iPxstutableDao.GetStuAllRemainkeshi(wrapper);
    }

    @Override
    public List<chongzhirecordsVO> GetStuChongzhjiList(QueryWrapper wrapper) {
        return iPxstutableDao.GetStuChongzhjiList(wrapper);
    }

    @Override
    public List<Pxstutable> GetAllStuListLoginPhone(QueryWrapper wrapper) {
        return iPxstutableDao.GetAllStuListLoginPhone(wrapper);
    }

    @Override
    public Page<stuIntegerVo> getstuIntegraInfoPage(Page page, QueryWrapper queryWrapper) {
        return iPxstutableDao.getstuIntegraInfoPage(page, queryWrapper);
    }

    @Override
    public List<shengrizhushouVO> GetAllteacherAndStuBirthday(long qiyeID) {
        return iPxstutableDao.GetAllteacherAndStuBirthday(qiyeID);
    }
}
