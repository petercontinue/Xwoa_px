package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.pkxk.Dao.IPxpaiketableDao;
import com.xwcloud.cloud.pkxk.Service.IPxpaiketableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class PxpaiketableServiceImpl extends ServiceImpl<IPxpaiketableDao, Pxpaiketable> implements IPxpaiketableService {
    @Autowired
    IPxpaiketableDao iPxpaiketableDao;

    @Override
    public List<Pxpaiketable> selectpaike(QueryWrapper queryWrapper) {
        return iPxpaiketableDao.selectpaike(queryWrapper);
    }

    @Override
    public List<paikelistVo> getPaikeList(Long stuID, Long paikeID, Long qiyeID) {
        return iPxpaiketableDao.getPaikeList(stuID, paikeID, qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getclassStuCount(Long paikeID, Long qiyeID) {
        return iPxpaiketableDao.getclassStuCount(paikeID, qiyeID);
    }

    @Override
    public List<Pxbuxikechengtable> getbk(Long stuID, Long paikeID, Long qiyeID) {
        return iPxpaiketableDao.getbk(stuID, paikeID, qiyeID);
    }

    @Override
    public List<shuakaxkgetPKVo> getshuakaxkgetPKList(Long qiyeID, String haveClassDate, String cardNumber, String kaishi, String jiesu, String zhong) {
        return iPxpaiketableDao.getshuakaxkgetPKList(qiyeID, haveClassDate, cardNumber, kaishi, jiesu, zhong);
    }

    @Override
    public Page<rengongxiaokeVo> getrengongxiaokePage(Page page, QueryWrapper queryWrapper) {
        return iPxpaiketableDao.getrengongxiaokePage(page, queryWrapper);
    }

    @Override
    public List<rengongxiaokeVo> Exportrengongxiaoke(QueryWrapper queryWrapper) {
        return iPxpaiketableDao.Exportrengongxiaoke(queryWrapper);
    }

    @Override
    public List<paikeroomVo> getclassRoomList(String haveClassDate, String starTime, String endTime, Long qiyeID) {
        return iPxpaiketableDao.getclassRoomList(haveClassDate, starTime, endTime, qiyeID);
    }

    @Override
    public List<teacherIDVo> getTea(String haveClassDate, String starTime, String endTime, Long qiyeID) {
        return iPxpaiketableDao.getTeaList(haveClassDate, starTime, endTime, qiyeID);
    }

    @Override
    public List<Pxpaiketable> getstuXuankeList(Date haveClassDate, Long stuID, Long qiyeID) {
        return iPxpaiketableDao.getstuXuankeList(haveClassDate, stuID, qiyeID);
    }

    @Override
    public List<Pxpaiketable> getbupkTea(Long teacherID, Date haveclassDate, Long qiyeID) {
        return iPxpaiketableDao.getbupkTea(teacherID, haveclassDate, qiyeID);
    }

    @Override
    public List<Pxpaiketable> editbupkTea(Long teacherID, Date haveclassDate, Long paikeID, Long qiyeID) {
        return iPxpaiketableDao.editbupkTea(teacherID, haveclassDate, paikeID, qiyeID);
    }

    @Override
    public List<paikeShowVo> getpaikeShowList(String haveClassDate, QueryWrapper queryWrapper) {
        return iPxpaiketableDao.getpaikeShowList(haveClassDate, queryWrapper);
    }

    @Override
    public Page<daykebiaoVo> getDaykebiaopage(Page page, QueryWrapper queryWrapper) {
        return iPxpaiketableDao.getDaykebiaopage(page, queryWrapper);
    }

    @Override
    public List<daykebiaoVo> ExportdaykebiaoList(QueryWrapper queryWrapper) {
        return iPxpaiketableDao.ExportdaykebiaoList(queryWrapper);
    }

    @Override
    public List<jiaoshikebiaoVO> getjiaoshikebiaoList(String haveClassDate, Long classRoomID, Long qiyeID) {
        return iPxpaiketableDao.getjiaoshikebiaoList(haveClassDate, classRoomID, qiyeID);
    }

    @Override
    public List<jiaoshikebiaoVO> ExportjiaoshikebiaoList(String haveClassDate, Long classRoomID, Long qiyeID) {
        return iPxpaiketableDao.ExportjiaoshikebiaoList(haveClassDate, classRoomID, qiyeID);
    }

    @Override
    public List<stukebiaoVO> getstukebiaoList(String haveClassDate, Long stuID, Long qiyeID) {
        return iPxpaiketableDao.getstukebiaoList(haveClassDate, stuID, qiyeID);
    }

    @Override
    public List<stukebiaoVO> ExportstukebiaoList(String haveClassDate, Long stuID, Long qiyeID) {
        return iPxpaiketableDao.ExportstukebiaoList(haveClassDate, stuID, qiyeID);
    }

    @Override
    public List<teacherkebiaoVo> getTeacherkebiaoList(String haveClassDate, String teacherIDs, Long qiyeID) {
        return iPxpaiketableDao.getTeacherkebiaoList(haveClassDate, teacherIDs, qiyeID);
    }

    @Override
    public List<teacherkebiaoVo> ExportTeacherkebiaoList(String haveClassDate, String teacherIDs, Long qiyeID) {
        return iPxpaiketableDao.ExportTeacherkebiaoList(haveClassDate, teacherIDs, qiyeID);
    }

    @Override
    public List<subjectkebiaoVo> getsubjectkebiaoList(String haveClassDate, Long subjectID, Long qiyeID) {
        return iPxpaiketableDao.getsubjectkebiaoList(haveClassDate, subjectID, qiyeID);
    }

    @Override
    public List<subjectkebiaoVo> ExportsubjectkebiaoList(String haveClassDate, Long subjectID, Long qiyeID) {
        return iPxpaiketableDao.ExportsubjectkebiaoList(haveClassDate, subjectID, qiyeID);
    }

    @Override
    public List<Pxpaiketable> getPaikelistByDate(String startDate, String endDate, String starTime, String endTime, Long qiyeID) {
        return iPxpaiketableDao.getPaikelistByDate(startDate, endDate, starTime, endTime, qiyeID);
    }

    @Override
    public List<Pxkeshistutable> GetKeshiList(long classID, Date haveClassDate, Date startLessonDateTime, Date endLessonDateTime, Long qiyeID) {
        return iPxpaiketableDao.GetKeshiList(classID, haveClassDate, startLessonDateTime, endLessonDateTime, qiyeID);
    }

    @Override
    public List<Pxpaiketable> GetAllPaikeNeedUpdateTeacher(String startDate, String endDate, String startLessonDateTime, String endLessonDateTime, long qiyeID) {
        return iPxpaiketableDao.GetAllPaikeNeedUpdateTeacher(startDate, endDate, startLessonDateTime, endLessonDateTime, qiyeID);
    }

    @Override
    public List<paikelistVo> getpaikeListInfo(long Id) {
        return iPxpaiketableDao.getpaikeListInfo(Id);
    }

    @Override
    public List<Pxpaiketable> GetChongpaiPiciList(String tags, long qiyeID) {
        return iPxpaiketableDao.GetChongpaiPiciList(tags, qiyeID);
    }

    @Override
    public List<subjectkebiaoVo> GetPrintSubjectKebiaoList(long qiyeID, String startDate, String endDate, long subjectID) {
        return iPxpaiketableDao.GetPrintSubjectKebiaoList(qiyeID, startDate, endDate, subjectID);
    }

    @Override
    public Page<qjpaikeVo> getstupaiketoqjInfo(Page page, Long stuID, QueryWrapper queryWrapper) {
        return iPxpaiketableDao.getstupaiketoqjInfo(page, stuID, queryWrapper);
    }

    @Override
    public Page<qjInfoVo> getstuqingjiaInfo(Page page, QueryWrapper queryWrapper) {
        return iPxpaiketableDao.getstuqingjiaInfo(page, queryWrapper);
    }

    @Override
    public Page<paikeShowVo> GetPaikeShowPages(Page page, QueryWrapper queryWrapper) {
        return iPxpaiketableDao.GetPaikeShowPages(page, queryWrapper);
    }

    @Override
    public List<shitingstuVO> GetAllShitingJoinstuInfo(QueryWrapper queryWrapper) {
        return iPxpaiketableDao.GetAllShitingJoinstuInfo(queryWrapper);
    }


}
