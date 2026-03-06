package com.xwcloud.cloud.oauth.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaStaff;
import com.xwcloud.cloud.model.OA.Vo.StaffVo;
import com.xwcloud.cloud.oauth.Dao.IOaStaffDao;
import com.xwcloud.cloud.oauth.Service.IOaStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Service
public class OaStaffServiceImpl extends ServiceImpl<IOaStaffDao, OaStaff> implements IOaStaffService {
    @Override
    public OaStaff selectUserByName(String username) {
        OaStaff user = baseMapper.selectOne(new QueryWrapper<OaStaff>().eq("stafftel", username).last("limit 1"));
        return user;
    }

    @Autowired
    private IOaStaffDao iOaStaffDao;

    @Override
    public IPage<StaffVo> getAllStaffInfo(Page<StaffVo> page, QueryWrapper queryWrapper) {
        return iOaStaffDao.getAllStaffInfo(page,queryWrapper);
    }
}
