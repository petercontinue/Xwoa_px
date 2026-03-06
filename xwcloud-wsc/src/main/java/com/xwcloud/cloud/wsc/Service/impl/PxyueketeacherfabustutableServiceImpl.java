package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Dao.IPxyueketeacherfabustutableDao;
import com.xwcloud.cloud.wsc.Service.IPxyueketeacherfabustutableService;

import com.xwcloud.cloud.model.Vo.joinyuekeInfoVO;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabustutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-17
 */
@Service
public class PxyueketeacherfabustutableServiceImpl extends ServiceImpl<IPxyueketeacherfabustutableDao, Pxyueketeacherfabustutable> implements IPxyueketeacherfabustutableService {

    @Autowired
    IPxyueketeacherfabustutableDao iPxyueketeacherfabustutableDao;

    @Override
    public List<joinyuekeInfoVO> getJoinyuekeStuInfos(long yuekeID, long qiyeID) {
        return iPxyueketeacherfabustutableDao.getJoinyuekeStuInfos(yuekeID, qiyeID);
    }
}
