package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstucardtable;
import com.xwcloud.cloud.stu.Dao.IPxstucardtableDao;
import com.xwcloud.cloud.stu.Service.IPxstucardtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-19
 */
@Service
public class PxstucardtableServiceImpl extends ServiceImpl<IPxstucardtableDao, Pxstucardtable> implements IPxstucardtableService {
    @Autowired
    IPxstucardtableDao iPxstucardtableDao;
    //去重
    @Override
    public List<Pxstucardtable> getCard(Long stuID, String cardNumber,Long qiyeID) {
        return iPxstucardtableDao.getCard(stuID,cardNumber,qiyeID);
    }
    //判断修改还是添加
    @Override
    public List<Pxstucardtable> addUpdateCard(Long stuID,Long qiyeID) {
        return iPxstucardtableDao.addUpdateCard(stuID,qiyeID);
    }

    @Override
    public List<Pxstucardtable> getcfCard(QueryWrapper queryWrapper) {
        return iPxstucardtableDao.getcfCard(queryWrapper);
    }


}
