package com.xwcloud.cloud.oa.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaBugAdvice;
import com.xwcloud.cloud.model.OA.Vo.BugOrAdviceInfo;
import com.xwcloud.cloud.oa.Dao.IOaBugAdviceDao;
import com.xwcloud.cloud.oa.service.IOaBugAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-12
 */
@Service
public class OaBugAdviceServiceImpl extends ServiceImpl<IOaBugAdviceDao, OaBugAdvice> implements IOaBugAdviceService {

    @Autowired
    private IOaBugAdviceDao iOaBugAdviceDao;

    //分页获取所有的功能建议和bug信息
    @Override
    public IPage<BugOrAdviceInfo> getAllBugOrAdviceInfo(Page<BugOrAdviceInfo> page, Wrapper wrapper) {
        return iOaBugAdviceDao.getAllBugOrAdviceInfo(page, wrapper);
    }

    //根据id查询一个
    @Override
    public BugOrAdviceInfo getOneBugOrAdviceInfo(Long id) {
        return iOaBugAdviceDao.getOneBugOrAdviceInfo(id);
    }

}
