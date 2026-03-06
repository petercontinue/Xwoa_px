package com.xwcloud.cloud.oauth.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaStaff;
import com.xwcloud.cloud.model.OA.Vo.StaffVo;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
public interface IOaStaffService extends IService<OaStaff> {

    //获取所以的职员信息
    IPage<StaffVo> getAllStaffInfo(Page<StaffVo> page, QueryWrapper queryWrapper);
    OaStaff selectUserByName(String username);
}
