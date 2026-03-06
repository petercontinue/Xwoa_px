package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstucardtable;
import com.xwcloud.cloud.zsbm.Dao.IPxstucardtableDao;
import com.xwcloud.cloud.zsbm.Service.IPxstucardtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-18
 */
@Service
public class PxstucardtableServiceImpl extends ServiceImpl<IPxstucardtableDao, Pxstucardtable> implements IPxstucardtableService {

    @Autowired
    IPxstucardtableDao iPxstucardtableDao;

    @Override
    public Integer deleteStuCardRecordsByStuID(Long stuID,Long qiyeID) {
        return iPxstucardtableDao.deleteStuCardRecordsByStuID(stuID,qiyeID);
    }
}
