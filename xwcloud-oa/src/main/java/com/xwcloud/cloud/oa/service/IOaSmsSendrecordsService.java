package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaSmsSendrecords;
import com.xwcloud.cloud.model.OA.Vo.SmsSendrecordsVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
public interface IOaSmsSendrecordsService extends IService<OaSmsSendrecords> {

    //获取所有短信服务发送记录
    IPage<SmsSendrecordsVo> getAllSmsBuyrecordsVoInfo(Page<SmsSendrecordsVo> page, Wrapper wrapper);

}
