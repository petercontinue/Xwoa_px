package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Dao.IWhdJizanFaqimyjizanDao;
import com.xwcloud.cloud.wsc.Service.IWhdJizanFaqimyjizanService;

import com.xwcloud.cloud.model.Vo.jizanfaqiVO;
import com.xwcloud.cloud.model.entity.WhdJizanFaqimyjizan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-24
 */
@Service
public class WhdJizanFaqimyjizanServiceImpl extends ServiceImpl<IWhdJizanFaqimyjizanDao, WhdJizanFaqimyjizan> implements IWhdJizanFaqimyjizanService {

    @Autowired
    IWhdJizanFaqimyjizanDao iWhdJizanFaqimyjizanDao;

    @Override
    public List<jizanfaqiVO> GetjizanFaqiList(long huodongID) {
        return iWhdJizanFaqimyjizanDao.GetjizanFaqiList(huodongID);
    }
}
