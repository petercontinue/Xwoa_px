package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.OA.Vo.KehuSmsVo;
import com.xwcloud.cloud.model.OA.Vo.KehuVo;
import com.xwcloud.cloud.model.OA.Vo.YiqiandanKehuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
public interface IOaKehuService extends IService<OaKehu> {

    //根据电话或企业名称查询客户
    List<OaKehu> getKehuByTelPhoneOrCompanyname(String telPhone, String companyName);

    //检查电话或企业名称是否重复 true：可添加或修改 false：不可添加或修改
    boolean checkTelPhoneOrCompanyname(String telPhone, String companyName);

    boolean checkEditTelPhoneOrCompanyname(String telPhone, String companyName);

    //根据已签单客户的id 查出已签单的客户
    List<OaKehu> getAllYiQianDanKehuInfo();

    //返回已签单客户的kehuID
    List<Long> getAllYiQianDanKehuID();


    //分页获取所有的客户信息
    IPage<KehuVo> getAllKehuInfo(Page<KehuVo> page, QueryWrapper queryWrapper);

    //根据id获取一个客户信息
    KehuVo getOneKehuById(@Param("ew") Wrapper wrapper);

    //获取所有已签单客户
    IPage<YiqiandanKehuVo> getAllYiqiandanKehuInfo(Page<YiqiandanKehuVo> page, @Param("ew") Wrapper wrapper);

    //续费
    void xufei(Long id, int y);

    //根据id获取已签单客户
    YiqiandanKehuVo getOneYiqiandanKehuInfo(Long qiyeID);

    List<OaKehu> getAllYQDKehukehucompanyname();

    //获取客户培训次数
    Integer getKehuPeixunCount(Long qiyeID);


    IPage<KehuSmsVo> getAllKehusms(Page<KehuSmsVo> page, @Param("ew") Wrapper wrapper);
}
