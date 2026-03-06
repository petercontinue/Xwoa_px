package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxqiandaninfotableDao;
import com.xwcloud.cloud.caiwu.Service.IPxqiandaninfotableService;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Service
public class PxqiandaninfotableServiceImpl extends ServiceImpl<IPxqiandaninfotableDao, Pxqiandaninfotable> implements IPxqiandaninfotableService {
    @Autowired
    IPxqiandaninfotableDao iPxqiandaninfotableDao;

    @Override
    public List<HashMap<String, String>> getYejitongbihuanbiList(String[] moneyStyle, String campusID, String startYear, String endYear, Long qiyeID) {
        return this.baseMapper.getYejitongbihuanbiList(moneyStyle, campusID, startYear, endYear, qiyeID);
    }

    @Override
    public List<listVo> getstuQiandan(Long stuID, Long qiyeID) {
        return iPxqiandaninfotableDao.getstuQiandan(stuID, qiyeID);
    }

    @Override
    public Page<HashMap<String, String>> getBanjitongjiPage(Page page,QueryWrapper queryWrapper) {
        return this.baseMapper.getBanjishoufeiPage(page, queryWrapper);
    }

    @Override
    public Page<HashMap<String, String>> getShoufeiDetail(Page page, String campusID, String classID, Long qiyeID) {
        return this.baseMapper.getShoufeiDetail(page, campusID, classID, qiyeID);
    }

    @Override
    public List<HashMap<String, Object>> getBanjitongjiList(String campusID, String classID, Long qiyeID) {
        return this.baseMapper.getBanjishoufeiList(campusID, classID, qiyeID);
    }

    @Override
    public List<HashMap<String, Object>> getShoufeiDetailList(String campusID, String classID, Long qiyeID) {
        return this.baseMapper.getShoufeiDetailList(campusID, classID, qiyeID);
    }

    @Override
    public List<HashMap<String, String>> gettuifeitongji(QueryWrapper queryWrapper, String year) {
        return this.baseMapper.gettuifeitongji(queryWrapper, year);
    }
}
