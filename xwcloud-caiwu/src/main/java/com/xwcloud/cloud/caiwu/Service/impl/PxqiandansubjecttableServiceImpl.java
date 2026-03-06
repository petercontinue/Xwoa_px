package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxqiandansubjecttableDao;
import com.xwcloud.cloud.caiwu.Service.IPxqiandansubjecttableService;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;
import org.apache.commons.lang.StringUtils;
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
public class PxqiandansubjecttableServiceImpl extends ServiceImpl<IPxqiandansubjecttableDao, Pxqiandansubjecttable> implements IPxqiandansubjecttableService {

    @Override
    public Page<HashMap<String, String>> getKemushoufeiPage(Page page, QueryWrapper queryWrapper) {
        return this.baseMapper.getKemushoufeiPage(page, queryWrapper);
    }

    @Override
    public Page<HashMap<String, String>> getKemukehaoPage(Page page, QueryWrapper queryWrapper) {
        return this.baseMapper.getKemukehaoPage(page, queryWrapper);
    }

    @Override
    public Page<HashMap<String, String>> getKemuyufeePage(Page page, QueryWrapper queryWrapper) {
        return this.baseMapper.getKemuyufeePage(page, queryWrapper);
    }

    @Override
    public List<HashMap<String, Object>> getKemushoufeiList(String campusID, String kemuName, String startDate, String endDate, String qiyeID) {
        return this.baseMapper.getKemushoufeiList(campusID, kemuName, startDate, endDate, qiyeID);
    }

    @Override
    public List<HashMap<String, Object>> getKemukehaoList(String campusID, String kemuName, String startDate, String endDate, String qiyeID) {
        return this.baseMapper.getKemukehaoList(campusID, kemuName, startDate, endDate, qiyeID);
    }

    @Override
    public List<HashMap<String, Object>> getKemuyufeeList(String campusID, String kemuName, String startDate, String endDate, String qiyeID) {
        return this.baseMapper.getKemuyufeeList(campusID, kemuName, startDate, endDate, qiyeID);
    }

    @Override
    public List<HashMap<String, String>> getKumuStu(String startDate, String endDate, long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("qiandansub.qiandandate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("qiandansub.qiandandate", endDate);
        }
        queryWrapper.eq("qiandansub.qiyeID", qiyeID);
        return this.baseMapper.getKumuStu(queryWrapper);
    }

    @Override
    public List<HashMap<String, String>> getKumuXinqian(String startDate, String endDate, long qiyeID, int... moneyStyle) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("qiandansub.qiandandate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("qiandansub.qiandandate", endDate);
        }
        queryWrapper.in("qiandan.moneyStyle", moneyStyle);
        queryWrapper.eq("qiandansub.qiyeID", qiyeID);
        return this.baseMapper.getKumuXinqian(queryWrapper);
    }

    @Override
    public List<HashMap<String, String>> getKumuKeshi(String startDate, String endDate, long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("qiandansub.qiandandate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("qiandansub.qiandandate", endDate);
        }
        queryWrapper.eq("qiandansub.qiyeID", qiyeID);
        return this.baseMapper.getKumuKeshi(queryWrapper);
    }

    @Override
    public List<HashMap<String, String>> getKumukexiao(String startDate, String endDate, long qiyeID) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(startDate)) {
            queryWrapper.ge("keshi.haveClassDate", startDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            queryWrapper.le("keshi.haveClassDate", endDate);
        }
        queryWrapper.eq("keshi.qiyeID", qiyeID);
        return this.baseMapper.getKumukexiao(queryWrapper);
    }

    @Override
    public Page<HashMap<String, String>> getTingkeStu(Page page, Wrapper wrapper) {
        return this.baseMapper.getTingkeStu(page, wrapper);
    }

    @Override
    public List<HashMap<String, Object>> getTingkeStuList(Wrapper wrapper) {
        return this.baseMapper.getTingkeStuList(wrapper);
    }

    @Override
    public Page<HashMap<String, Object>> getKemuShoufeiTongji(Page page, QueryWrapper wrapper) {
        return this.baseMapper.getKemuShoufeiTongji(page, wrapper);
    }

    @Override
    public Page<HashMap<String, Object>> getSubjectYuETongji(Page page, QueryWrapper wrapper) {
        return this.baseMapper.getSubjectYuETongji(page, wrapper);
    }

    @Override
    public Page<HashMap<String, Object>> getSubjectBmByCampusAndSubject(Page page, QueryWrapper<HashMap<String, Object>> wrapper) {
        return this.baseMapper.getSubjectBmByCampusAndSubject(page, wrapper);
    }
}
