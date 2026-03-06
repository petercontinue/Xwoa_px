package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.buyZafeiVo;
import com.xwcloud.cloud.model.entity.Pxqiandanothermoneytable;

import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.zsbm.Dao.IPxqiandanothermoneytableDao;
import com.xwcloud.cloud.zsbm.Service.IPxqiandanothermoneytableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
@Service
public class PxqiandanothermoneytableServiceImpl extends ServiceImpl<IPxqiandanothermoneytableDao, Pxqiandanothermoneytable> implements IPxqiandanothermoneytableService {

    @Autowired
    IPxqiandanothermoneytableDao iPxqiandanothermoneytableDao;

    @Override
    public List<searchVO> GetQiandanOtherMoneyList(Long qiyeID) {
        return iPxqiandanothermoneytableDao.GetQiandanOtherMoneyList(qiyeID);
    }

    @Override
    public List<buyZafeiVo> GetQiandanZafeiList(long qiandanID) {
        return iPxqiandanothermoneytableDao.GetQiandanZafeiList(qiandanID);
    }
}
