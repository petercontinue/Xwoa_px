package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.ReclassVo;
import com.xwcloud.cloud.model.Vo.allxuankeVo;
import com.xwcloud.cloud.model.Vo.tuixiankeVo;
import com.xwcloud.cloud.model.Vo.zbInPaikeVo;
import com.xwcloud.cloud.model.entity.Pxxuanketable;
import com.xwcloud.cloud.stu.Dao.IPxxuanketableDao;
import com.xwcloud.cloud.stu.Service.IPxxuanketableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Service
public class PxxuanketableServiceImpl extends ServiceImpl<IPxxuanketableDao, Pxxuanketable> implements IPxxuanketableService {
    @Autowired
    IPxxuanketableDao iPxxuanketableDao;

    @Override
    public List<Pxxuanketable> allxuankebypkID(Long paikeID, Long qiyeID) {
        return iPxxuanketableDao.allxuankebypkID(paikeID, qiyeID);
    }

    @Override
    public List<Pxxuanketable> xuankebypkstu(Long paikeID, Long stuID, Long qiyeID) {
        return iPxxuanketableDao.xuankebypkstu(paikeID, stuID, qiyeID);
    }

    @Override
    public List<Pxxuanketable> xxkbypkbx(Long paikeID, Long bxID, Long qiyeID) {
        return iPxxuanketableDao.xxkbypkbx(paikeID, bxID, qiyeID);
    }

    @Override
    public List<Pxxuanketable> getxkByPkStuBx(Long paikeID, Long stuID, Long bxID, Long qiyeID) {
        return iPxxuanketableDao.getxkByPkStuBx(paikeID, stuID, bxID, qiyeID);
    }

    @Override
    public List<ReclassVo> getJoinPaikeL(Long classID, Long qiyeID) {
        return iPxxuanketableDao.getJoinPaikeL(classID, qiyeID);
    }

    @Override
    public List<ReclassVo> getJoinPaikeStuL(Long classID, Long stuID, Long qiyeID) {
        return iPxxuanketableDao.getJoinPaikeStuL(classID, stuID, qiyeID);
    }

    @Override
    public List<Pxxuanketable> getBybxID(Long bxID, Long qiyeID) {
        return iPxxuanketableDao.getBybxID(bxID, qiyeID);
    }

    @Override
    public List<Pxxuanketable> getallxuankeTable(Long paikeID, Long qiyeID) {
        return iPxxuanketableDao.getallxuankeTable(paikeID, qiyeID);
    }

    @Override
    public List<allxuankeVo> getallxuanke(Long buxiID, Long qiyeID) {
        return iPxxuanketableDao.getallxuanke(buxiID, qiyeID);
    }

    @Override
    public List<Pxxuanketable> getNokehaoStu(Long buxiID, Long qiyeID) {
        return iPxxuanketableDao.getNokehaoStu(buxiID, qiyeID);
    }

    @Override
    public List<zbInPaikeVo> getInPaikeByClassandBuxi(String buxiID, String classID, Long qiyeID) {
        return iPxxuanketableDao.getInPaikeByClassandBuxi(buxiID, classID, qiyeID);
    }

    @Override
    public List<tuixiankeVo> tuixuankePaike(QueryWrapper queryWrapper) {
        return iPxxuanketableDao.tuixuankePaike(queryWrapper);
    }


}
