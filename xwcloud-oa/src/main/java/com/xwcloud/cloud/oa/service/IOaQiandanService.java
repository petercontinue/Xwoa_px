package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaQiandan;
import com.xwcloud.cloud.model.OA.Vo.QiandanInfoVo;


import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
public interface IOaQiandanService extends IService<OaQiandan> {

    //查询出已签单的签单信息
    List<OaQiandan> getAllYiqiandanInfo();

    //检查客户是否已进行签单
    public boolean checkKehuYiqiandan(Long qiyeID);

    //分页获取所有的签单信息
    IPage<QiandanInfoVo> getAllQiandanInfo(Page<QiandanInfoVo> page, Wrapper queryWrapper);

    QiandanInfoVo getOneQiandanInfo(Long id);

}
