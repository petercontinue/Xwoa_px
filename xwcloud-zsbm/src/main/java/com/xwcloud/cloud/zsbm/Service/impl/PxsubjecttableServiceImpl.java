package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.Vo.subjectVO;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;
import com.xwcloud.cloud.zsbm.Dao.IPxsubjecttableDao;
import com.xwcloud.cloud.zsbm.Service.IPxsubjecttableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
@Service
public class PxsubjecttableServiceImpl extends ServiceImpl<IPxsubjecttableDao, Pxsubjecttable> implements IPxsubjecttableService {

    @Autowired
    IPxsubjecttableDao iPxsubjecttableDao;

    @Override
    public Pxsubjecttable GetSubjectById(Long Id) {
        return iPxsubjecttableDao.GetSubjectById(Id);
    }

    @Override
    public List<searchVO> GetAllSubjectList(Long qiyeID) {
        return iPxsubjecttableDao.GetAllSubjectList(qiyeID);
    }

    @Override
    public List<searchVO> GetAllSubjectListnocampus(Long qiyeID) {
        return iPxsubjecttableDao.GetAllSubjectListnocampus(qiyeID);
    }

    @Override
    public List<searchVO> GetAllSubjectByxqIDAndqiyeID(Long qiyeID, Long campusID) {
        return iPxsubjecttableDao.GetAllSubjectByxqIDAndqiyeID(qiyeID, campusID);
    }

    @Override
    public Page<subjectVO> getAllsubjectPages(Page page, QueryWrapper wrapper) {
        return iPxsubjecttableDao.getAllsubjectPages(page, wrapper);
    }

    @Override
    public List<Pxsubjecttable> GetsubjectList(Long campusID, String subjectName) {
        return iPxsubjecttableDao.GetsubjectList(campusID, subjectName);
    }
}
