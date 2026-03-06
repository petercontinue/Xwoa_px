package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WhdChoujiangJiangpinVo;
import com.xwcloud.cloud.model.Vo.allgailvVo;
import com.xwcloud.cloud.model.entity.WhdChoujiangJiangping;
import com.xwcloud.cloud.wsc.Dao.IWhdChoujiangJiangpingDao;
import com.xwcloud.cloud.wsc.Service.IWhdChoujiangJiangpingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Service
public class WhdChoujiangJiangpingServiceImpl extends ServiceImpl<IWhdChoujiangJiangpingDao, WhdChoujiangJiangping> implements IWhdChoujiangJiangpingService {

    @Autowired
    private IWhdChoujiangJiangpingDao whdChoujiangJiangpingDao;

    @Override
    public Page<WhdChoujiangJiangpinVo> getWhdChoujiangJiangpinPage(Page page, QueryWrapper wrapper) {
        return whdChoujiangJiangpingDao.getWhdChoujiangJiangpinPage(page, wrapper);
    }

    @Override
    public allgailvVo getallgailv(Long huodongID, Long qiyeID) {
        return whdChoujiangJiangpingDao.getallgailv(huodongID, qiyeID);
    }
}
