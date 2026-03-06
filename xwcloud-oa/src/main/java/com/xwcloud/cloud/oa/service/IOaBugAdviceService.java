package com.xwcloud.cloud.oa.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.OA.OaBugAdvice;
import com.xwcloud.cloud.model.OA.Vo.BugOrAdviceInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-12
 */
public interface IOaBugAdviceService extends IService<OaBugAdvice> {

    //分页获取所有的功能建议和bug信息
    IPage<BugOrAdviceInfo> getAllBugOrAdviceInfo(Page<BugOrAdviceInfo> page, @Param("ew") Wrapper wrapper);

    //根据id查询一个
    BugOrAdviceInfo getOneBugOrAdviceInfo(Long id);
	
}
