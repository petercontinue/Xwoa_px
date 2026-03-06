package com.xwcloud.cloud.stu.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxclasstable;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import com.xwcloud.cloud.stu.Dao.IPxclasstableDao;
import com.xwcloud.cloud.stu.Service.IPxclasstableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
@Service
public class PxclasstableServiceImpl extends ServiceImpl<IPxclasstableDao, Pxclasstable> implements IPxclasstableService {

    @Autowired
    IPxclasstableDao iPxclasstableDao;

    @Override
    public List<listVo> getAllClass(QueryWrapper queryWrapper) {
        return iPxclasstableDao.getAllClass(queryWrapper);
    }

    @Override
    public List<Pxclasstable> selectclass(QueryWrapper queryWrapper) {
        return iPxclasstableDao.selectclass(queryWrapper);
    }

    @Override
    public List<Pxclasstable> updatestuOyOclass(String oldstuName, String newstuName, Long qiyeID) {
        return iPxclasstableDao.updatestuOyOclass(oldstuName, newstuName, qiyeID);
    }

    @Override
    public Page<LookstuClassVo> getstuclassPage(Page page, Long stuID, Long qiyeID) {
        return iPxclasstableDao.getstuclassPage(page, stuID, qiyeID);
    }

    //获取学员班级
    @Override
    public Page<classTyleVo> getStuClass(Page page, QueryWrapper queryWrapper) {
        return iPxclasstableDao.getStuClass(page, queryWrapper);
    }

    //查询学员班级
    @Override
    public Page<stuClassVo> getClassToStu(Page page, QueryWrapper queryWrapper) {
        return iPxclasstableDao.getClassToStu(page, queryWrapper);
    }

    //查重
    @Override
    public List<Pxclasstable> getOtOName(Long id, String className, Long qiyeID) {
        return iPxclasstableDao.getOtOName(id, className, qiyeID);
    }

    //修改查重
    @Override
    public List<Pxclasstable> getClassName(String className, Long qiyeID) {
        return iPxclasstableDao.getClassName(className, qiyeID);
    }

    @Override
    public List<Pxclasstable> getupdateClassName(String className, Long qiyeID, Long id) {
        return iPxclasstableDao.getupdateClassName(className, qiyeID, id);
    }

    @Override
    public List<Pxclasstable> getClasszdID(String zdID, Long qiyeID) {
        return iPxclasstableDao.getClasszdID(zdID, qiyeID);
    }

    @Override
    public List<Pxclasstable> getCfzdID(String zdID, Long qiyeID) {
        return iPxclasstableDao.getCfzdID(zdID, qiyeID);
    }

    @Override
    public List<Pxclasstable> getupdateClasszdID(String zdID, Long qiyeID, Long id) {
        return iPxclasstableDao.getupdateClasszdID(zdID, qiyeID, id);
    }

    @Override
    public List<listVo> getallbanke(Long qiyeID,String campusID) {
        return iPxclasstableDao.getallbanke(qiyeID,campusID);
    }

    //查看启用状态
    @Override
    public Pxclasstable getShow(int Type, Long classID, Long qiyeID) {
        return iPxclasstableDao.getShow(Type, classID, qiyeID);
    }

    //导出学员一对一班级
    @Override
    public List<ExportClassVo> getClassInfoOtO(QueryWrapper queryWrapper) {
        return iPxclasstableDao.getClassInfoOtO(queryWrapper);
    }

    //复课去重
    @Override
    public List<Pxclasstable> FkClassName(String className, Long qiyeID) {
        return iPxclasstableDao.FkClassName(className, qiyeID);
    }

    //删除组
    @Override
    public List<Pxkeshistutable> getkeHao(Long classID, Long qiyeID) {
        return iPxclasstableDao.getKeHao(classID, qiyeID);
    }

    @Override
    public paikeVo getPaiKe(Long classID, Long qiyeID) {
        return iPxclasstableDao.getPaiKe(classID, qiyeID);
    }

    @Override
    public List<listVo> getkxqclass(Long campusID, Long qiyeID) {
        return iPxclasstableDao.getkxqclass(campusID, qiyeID);
    }


}
