package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.OA.OaQiandan;
import com.xwcloud.cloud.model.OA.Vo.KehuSmsVo;
import com.xwcloud.cloud.model.OA.Vo.KehuVo;
import com.xwcloud.cloud.model.OA.Vo.YiqiandanKehuVo;
import com.xwcloud.cloud.oa.Dao.IOaKehuDao;
import com.xwcloud.cloud.oa.service.IOaKehuService;
import com.xwcloud.cloud.oa.service.IOaQiandanService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Service
public class OaKehuServiceImpl extends ServiceImpl<IOaKehuDao, OaKehu> implements IOaKehuService {

    @Autowired
    private IOaQiandanService iOaQiandanService;

    @Autowired
    private IOaKehuDao iOaKehuDao;

    //根据电话或企业名称查询客户
    @Override
    public List<OaKehu> getKehuByTelPhoneOrCompanyname(String telPhone, String companyName) {
        QueryWrapper<OaKehu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("kehutelphone", telPhone).or().eq("kehucompanyname", companyName);
        List<OaKehu> kehuList = baseMapper.selectList(queryWrapper);
        return kehuList;
    }

    //检查电话或企业名称是否重复 true：可添加或修改 false：不可添加或修改
    @Override
    public boolean checkTelPhoneOrCompanyname(String telPhone, String companyName) {
        List<OaKehu> kehuList = this.getKehuByTelPhoneOrCompanyname(telPhone, companyName);
        //企业名称和电话不重复 返回true  重复返回false
        if (kehuList.size() == 0) {
            return true;
        }
        return false;
    }

    //检查电话或企业名称是否重复 true：可添加或修改 false：不可添加或修改
    @Override
    public boolean checkEditTelPhoneOrCompanyname(String telPhone, String companyName) {
        List<OaKehu> kehuList = this.getKehuByTelPhoneOrCompanyname(telPhone, companyName);
        //企业名称和电话不重复 返回true  重复返回false
        if (kehuList.size() == 1 || kehuList.size() == 0) {
            return true;
        }
        return false;
    }


    //根据已签单客户的id 查出已签单的客户
    public List<OaKehu> getAllYiQianDanKehuInfo() {
        //查出已签单客户的信息
        List<OaQiandan> oaQiandanList = iOaQiandanService.getAllYiqiandanInfo();
        List<Long> ids = new ArrayList<>();
        for (OaQiandan oaQiandan : oaQiandanList) {
            ids.add(oaQiandan.getQiyeID());
        }
        return baseMapper.selectBatchIds(ids);
    }

    //返回已签单客户的kehuID
    public List<Long> getAllYiQianDanKehuID() {
        //查出已签单客户的信息
        List<OaQiandan> oaQiandanList = iOaQiandanService.getAllYiqiandanInfo();
        List<Long> ids = new ArrayList<>();
        for (OaQiandan oaQiandan : oaQiandanList) {
            ids.add(oaQiandan.getQiyeID());
        }
        return ids;
    }

    //分页获取所有的客户信息
    @Override
    public IPage<KehuVo> getAllKehuInfo(Page<KehuVo> page, QueryWrapper queryWrapper) {
        return iOaKehuDao.getAllKehuInfo(page, queryWrapper);
    }

    //根据id获取一个客户信息
    @Override
    public KehuVo getOneKehuById(@Param("ew") Wrapper wrapper) {
        return iOaKehuDao.getOneKehuById(wrapper);
    }

    @Override
    public IPage<YiqiandanKehuVo> getAllYiqiandanKehuInfo(Page<YiqiandanKehuVo> page, Wrapper wrapper) {
        return iOaKehuDao.getAllYiqiandanKehuInfo(page, wrapper);
    }

    //续费
    @Override
    public void xufei(Long id, int y) {
        iOaKehuDao.xufei(id, y);
    }

    @Override
    public YiqiandanKehuVo getOneYiqiandanKehuInfo(Long qiyeID) {
        return iOaKehuDao.getOneYiqiandanKehuInfo(qiyeID);
    }

    @Override
    public List<OaKehu> getAllYQDKehukehucompanyname() {
        return iOaKehuDao.getAllYQDKehukehucompanyname();
    }

    @Override
    public Integer getKehuPeixunCount(Long qiyeID) {
        return iOaKehuDao.getKehuPeixunCount(qiyeID);
    }

    @Override
    public IPage<KehuSmsVo> getAllKehusms(Page<KehuSmsVo> page, Wrapper wrapper) {
        return iOaKehuDao.getAllKehusms(page,wrapper);
    }

}
