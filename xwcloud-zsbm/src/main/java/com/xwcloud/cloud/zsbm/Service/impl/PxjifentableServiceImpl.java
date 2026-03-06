package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxjifentable;
import com.xwcloud.cloud.zsbm.Dao.IPxjifentableDao;
import com.xwcloud.cloud.zsbm.Service.IPxjifentableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
@Service
public class PxjifentableServiceImpl extends ServiceImpl<IPxjifentableDao, Pxjifentable> implements IPxjifentableService {

    @Autowired
    IPxjifentableDao iPxjifentableDao;
    @Override
    public Integer deleteJiifenByStuID(Long stuID,long qiyeID) {
        return iPxjifentableDao.deleteJiifenByStuID(stuID,qiyeID);
    }
}
