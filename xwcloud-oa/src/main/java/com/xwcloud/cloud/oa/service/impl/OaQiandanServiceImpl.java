package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaQiandan;
import com.xwcloud.cloud.model.OA.Vo.QiandanInfoVo;
import com.xwcloud.cloud.oa.Dao.IOaQiandanDao;
import com.xwcloud.cloud.oa.service.IOaQiandanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class OaQiandanServiceImpl extends ServiceImpl<IOaQiandanDao, OaQiandan> implements IOaQiandanService {


    @Autowired
    private IOaQiandanDao iOaQiandanDao;

    //查询出已签单的签单信息
    public List<OaQiandan> getAllYiqiandanInfo() {
        QueryWrapper<OaQiandan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiandanstate", 1);
        List<OaQiandan> oaQiandanList = baseMapper.selectList(queryWrapper);
        return oaQiandanList;
    }

    //检查客户是否已进行签单
    public boolean checkKehuYiqiandan(Long qiyeID) {
        QueryWrapper<OaQiandan> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qiandanstate", 1);
        queryWrapper.eq("qiyeID", qiyeID);
        OaQiandan oaQiandan = baseMapper.selectOne(queryWrapper);
        if (oaQiandan != null) {
            return true;
        }
        return false;
    }

    //分页获取所有的签单信息
    @Override
    public IPage<QiandanInfoVo> getAllQiandanInfo(Page<QiandanInfoVo> page, Wrapper queryWrapper) {
        return iOaQiandanDao.getAllQiandanInfo(page, queryWrapper);
    }

    @Override
    public QiandanInfoVo getOneQiandanInfo(Long id) {
        return iOaQiandanDao.getOneQiandanInfo(id);
    }

}
