package com.xwcloud.cloud.sys.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.asstestyleVO;
import com.xwcloud.cloud.model.Vo.staffBirthVo;
import com.xwcloud.cloud.model.Vo.staffinfoVo;
import com.xwcloud.cloud.model.Vo.stafftelVo;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import com.xwcloud.cloud.sys.Dao.IPxstafftableDao;
import com.xwcloud.cloud.sys.Service.IPxstafftableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yinqi
 * @since 2020-10-18
 */
@Service
public class PxstafftableServiceImpl extends ServiceImpl<IPxstafftableDao, Pxstafftable> implements IPxstafftableService {

    @Autowired
    IPxstafftableDao iPxstafftableDao;

    @Override
    public int updateStaffState(int staffState, Long id) {
        return iPxstafftableDao.updateStaffState(staffState, id);
    }

    @Override
    public Page<stafftelVo> getstaffTel(Page page, QueryWrapper wrapper) {
        return iPxstafftableDao.getstaffTel(page, wrapper);
    }

    @Override
    public int UpdatetaffTel(String staffTel, Long id) {
        return iPxstafftableDao.UpdatetaffTel(staffTel, id);
    }

    @Override
    public Page<staffBirthVo> getStaffBirth(Page page, QueryWrapper wrapper) {
        return iPxstafftableDao.getStaffBirth(page, wrapper);
    }

    @Override
    public int UpdateStaffBirthday(String staffBirthday, Long id) {
        return iPxstafftableDao.UpdateStaffBirthday(staffBirthday, id);
    }

    @Override
    public List<staffBirthVo> GetStaffBirthList(QueryWrapper wrapper) {
        return iPxstafftableDao.GetStaffBirthList(wrapper);
    }

    @Override
    public Page<staffinfoVo> GetstaffInfoPages(Page page, QueryWrapper wrapper) {
        return iPxstafftableDao.GetstaffInfoPages(page, wrapper);
    }

    @Override
    public List<Pxstafftable> GetStaffListByCampusID(Long campusID) {
        return iPxstafftableDao.GetStaffListByCampusID(campusID);
    }

    @Override
    public List<stafftelVo> GetstafftelList(QueryWrapper wrapper) {
        return iPxstafftableDao.GetstafftelList(wrapper);
    }

    @Override
    public List<Pxstafftable> GetstaffInfoBycampusidAndstaffpostID(Long campusID, Long staffPostID) {
        return iPxstafftableDao.GetstaffInfoBycampusidAndstaffpostID(campusID, staffPostID);
    }

    @Override
    public List<asstestyleVO> GetAllStaffList(Long qiyeID) {
        return iPxstafftableDao.GetAllStaffList(qiyeID);
    }

    @Override
    public List<String> GetStaffInfoDetail(long qiyeID, long staffPostID) {
        return iPxstafftableDao.GetStaffInfoDetail(qiyeID, staffPostID);
    }

    @Override
    public List<HashMap<String, String>> getnowxTeaList(QueryWrapper queryWrapper) {
        return iPxstafftableDao.getnowxTeaList(queryWrapper);
    }
}
