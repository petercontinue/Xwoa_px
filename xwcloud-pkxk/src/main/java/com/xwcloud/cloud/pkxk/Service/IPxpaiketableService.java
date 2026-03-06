package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import com.xwcloud.cloud.model.entity.Pxpaiketable;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxpaiketableService extends IService<Pxpaiketable> {
    List<Pxpaiketable> selectpaike(QueryWrapper queryWrapper);

    List<paikelistVo> getPaikeList(Long stuID, Long paikeID, Long qiyeID);

    List<Pxbuxikechengtable> getclassStuCount(Long paikeID, Long qiyeID);

    List<Pxbuxikechengtable> getbk(Long stuID, Long paikeID, Long qiyeID);

    List<shuakaxkgetPKVo> getshuakaxkgetPKList(Long qiyeID, String haveClassDate, String cardNumber, String kaishi, String jiesu, String zhong);

    Page<rengongxiaokeVo> getrengongxiaokePage(Page page, QueryWrapper queryWrapper);

    List<rengongxiaokeVo> Exportrengongxiaoke(QueryWrapper queryWrapper);

    List<paikeroomVo> getclassRoomList(String haveClassDate, String starTime, String endTime, Long qiyeID);

    List<teacherIDVo> getTea(String haveClassDate, String starTime, String endTime, Long qiyeID);

    List<Pxpaiketable> getstuXuankeList(Date haveClassDate, Long stuID, Long qiyeID);

    List<Pxpaiketable> getbupkTea(Long teacherID, Date haveclassDate, Long qiyeID);

    List<Pxpaiketable> editbupkTea(Long teacherID, Date haveclassDate, Long paikeID, Long qiyeID);

    List<paikeShowVo> getpaikeShowList(String haveClassDate, QueryWrapper queryWrapper);

    Page<daykebiaoVo> getDaykebiaopage(Page page, QueryWrapper queryWrapper);

    List<daykebiaoVo> ExportdaykebiaoList(QueryWrapper queryWrapper);

    List<jiaoshikebiaoVO> getjiaoshikebiaoList(String haveClassDate, Long classRoomID, Long qiyeID);

    List<jiaoshikebiaoVO> ExportjiaoshikebiaoList(String haveClassDate, Long classRoomID, Long qiyeID);

    List<stukebiaoVO> getstukebiaoList(String haveClassDate, Long stuID, Long qiyeID);

    List<stukebiaoVO> ExportstukebiaoList(String haveClassDate, Long stuID, Long qiyeID);

    List<teacherkebiaoVo> getTeacherkebiaoList(String haveClassDate, String teacherIDs, Long qiyeID);

    List<teacherkebiaoVo> ExportTeacherkebiaoList(String haveClassDate, String teacherIDs, Long qiyeID);

    List<subjectkebiaoVo> getsubjectkebiaoList(String haveClassDate, Long subjectID, Long qiyeID);

    List<subjectkebiaoVo> ExportsubjectkebiaoList(String haveClassDate, Long subjectID, Long qiyeID);

    List<Pxpaiketable> getPaikelistByDate(String startDate, String endDate, String starTime, String endTime, Long qiyeID);

    List<Pxkeshistutable> GetKeshiList(long classID, Date haveClassDate, Date startLessonDateTime, Date endLessonDateTime, Long qiyeID);

    List<Pxpaiketable> GetAllPaikeNeedUpdateTeacher(String startDate, String endDate, String startLessonDateTime, String endLessonDateTime, long qiyeID);

    List<paikelistVo> getpaikeListInfo(long Id);

    List<Pxpaiketable> GetChongpaiPiciList(String tags, long qiyeID);

    List<subjectkebiaoVo> GetPrintSubjectKebiaoList(long qiyeID, String startDate, String endDate, long subjectID);

    Page<qjpaikeVo> getstupaiketoqjInfo(Page page, Long stuID, QueryWrapper queryWrapper);

    Page<qjInfoVo> getstuqingjiaInfo(Page page, QueryWrapper queryWrapper);

    Page<paikeShowVo> GetPaikeShowPages(Page page,QueryWrapper queryWrapper);

    List<shitingstuVO> GetAllShitingJoinstuInfo(QueryWrapper queryWrapper);
}
