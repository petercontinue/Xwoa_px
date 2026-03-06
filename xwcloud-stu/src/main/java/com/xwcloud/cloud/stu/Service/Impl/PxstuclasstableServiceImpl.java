package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.AsClassTOStuVo;
import com.xwcloud.cloud.model.Vo.exportclassstuVo;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;
import com.xwcloud.cloud.stu.Dao.IPxstuclasstableDao;
import com.xwcloud.cloud.stu.Service.IPxstuclasstableService;
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
public class PxstuclasstableServiceImpl extends ServiceImpl<IPxstuclasstableDao, Pxstuclasstable> implements IPxstuclasstableService {

    @Autowired
    IPxstuclasstableDao iPxstuclasstableDao;

    @Override
    public List<Pxstuclasstable> getBybxID(Long buxiID, Long qiyeID) {
        return iPxstuclasstableDao.getBybxID(buxiID, qiyeID);
    }

    @Override
    public List<Pxstuclasstable> getstuclass(Long classID, Long qiyeID) {
        return iPxstuclasstableDao.getstuclass(classID, qiyeID);
    }

    @Override
    public List<Pxstuclasstable> getBybxAndclassID(Long buxiID, Long classID, Long qiyeID) {
        return iPxstuclasstableDao.getBybxAndclassID(buxiID, classID, qiyeID);
    }

    @Override
    public List<Pxstuclasstable> selectstuclass(QueryWrapper queryWrapper) {
        return iPxstuclasstableDao.selectstuclass(queryWrapper);
    }

    //按照班级获取学员
    @Override
    public Page<AsClassTOStuVo> AsClassTOStuPage(Page page, QueryWrapper queryWrapper) {
        return iPxstuclasstableDao.AsClassTOStuPage(page, queryWrapper);
    }

    @Override
    public List<exportclassstuVo> getClassStu(QueryWrapper queryWrapper) {
        return iPxstuclasstableDao.getClassStu(queryWrapper);
    }
}
