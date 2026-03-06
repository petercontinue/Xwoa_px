package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaQiandanSp;
import com.xwcloud.cloud.model.OA.Vo.QiandanSpVo;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-21
 */
public interface IOaQiandanSpService extends IService<OaQiandanSp> {

    //获取所有的签单审批信息
    Page<QiandanSpVo> getAllQiandanSpInfo(Page<QiandanSpVo> page, Wrapper wrapper);

}
