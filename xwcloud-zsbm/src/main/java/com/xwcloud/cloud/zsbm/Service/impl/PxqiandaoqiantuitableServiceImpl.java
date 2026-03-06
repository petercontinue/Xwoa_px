package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqiandaoqiantuitable;
import com.xwcloud.cloud.zsbm.Dao.IPxqiandaoqiantuitableDao;
import com.xwcloud.cloud.zsbm.Service.IPxqiandaoqiantuitableService;
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
public class PxqiandaoqiantuitableServiceImpl extends ServiceImpl<IPxqiandaoqiantuitableDao, Pxqiandaoqiantuitable> implements IPxqiandaoqiantuitableService {

    @Autowired
    IPxqiandaoqiantuitableDao iPxqiandaoqiantuitableDao;
    @Override
    public Integer deleteRecordsbyStuID(Long stuID,Long qiyeID) {
        return iPxqiandaoqiantuitableDao.deleteRecordsbyStuID(stuID,qiyeID);
    }
}
