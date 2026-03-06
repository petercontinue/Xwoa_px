package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaSmsBuyrecords;
import com.xwcloud.cloud.model.OA.Vo.SmsBuyrecordsVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-03
 */
public interface IOaSmsBuyrecordsService extends IService<OaSmsBuyrecords> {

    //获取所有的短信服务购买记录
    IPage<SmsBuyrecordsVo> getAllSmsBuyrecordsInfo(Page<SmsBuyrecordsVo> page, @Param("ew") Wrapper wrapper);

}
