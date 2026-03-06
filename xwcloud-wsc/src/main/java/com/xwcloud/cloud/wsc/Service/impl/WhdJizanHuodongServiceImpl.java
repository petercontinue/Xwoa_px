package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.jizanfaqiVO;
import com.xwcloud.cloud.model.entity.WhdJizanHuodong;
import com.xwcloud.cloud.wsc.Dao.IWhdJizanHuodongDao;
import com.xwcloud.cloud.wsc.Service.IWhdJizanHuodongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-03
 */
@Service
public class WhdJizanHuodongServiceImpl extends ServiceImpl<IWhdJizanHuodongDao, WhdJizanHuodong> implements IWhdJizanHuodongService {
    @Autowired
    IWhdJizanHuodongDao iWhdJizanHuodongDao;

    @Override
    public List<WhdJizanHuodong> GetAlljizanList(QueryWrapper wrapper) {
        return iWhdJizanHuodongDao.GetAlljizanList(wrapper);
    }

    @Override
    public List<jizanfaqiVO> GetjizanfaqiInfo(long huodongID, long faqirenID) {
        return iWhdJizanHuodongDao.GetjizanfaqiInfo(huodongID,faqirenID);
    }
}
