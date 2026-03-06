package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxstukxqtable;

import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.stu.Dao.IPxstukxqtableDao;
import com.xwcloud.cloud.stu.Service.IPxstukxqtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-29
 */
@Service
public class PxstukxqtableServiceImpl extends ServiceImpl<IPxstukxqtableDao, Pxstukxqtable> implements IPxstukxqtableService {
    @Autowired
    IPxstukxqtableDao iPxstukxqtableDao;

    @Override
    public List<Pxstukxqtable> getBystubx(Long stuID, Long bxkcID, Long kxqID, Long qiyeID) {
        return iPxstukxqtableDao.getBystubx(stuID, bxkcID, kxqID, qiyeID);
    }

    @Override
    public List<listVo> Getkxqstu(Long qiyeID) {
        return iPxstukxqtableDao.Getkxqstu(qiyeID);
    }

    @Override
    public List<listVo> Getkxqbxkecheng(Long stuID, Long qiyeID) {
        return iPxstukxqtableDao.Getkxqbxkecheng(stuID, qiyeID);
    }

    @Override
    public List<listVo> GetKxqCampus(Long buxiID, Long qiyeID) {
        return iPxstukxqtableDao.GetKxqCampus(buxiID, qiyeID);
    }
}
