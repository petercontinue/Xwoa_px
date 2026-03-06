package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
@Repository
public interface IPxpaiketableDao extends BaseMapper<Pxpaiketable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "classRoomID", property = "classRoomID"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "MaxStuNum", property = "MaxStuNum"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "dakaoqin", property = "dakaoqin"),
            @Result(column = "tags", property = "tags"),
            @Result(column = "canqingjiaBeforeHours", property = "canqingjiaBeforeHours"),
            @Result(column = "shuakaTimeArea", property = "shuakaTimeArea"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "huodongImg", property = "huodongImg"),
            @Result(column = "huodongTitle", property = "huodongTitle"),
            @Result(column = "huodongshuoming", property = "huodongshuoming"),
            @Result(column = "liulangtime", property = "liulangtime"),
            @Result(column = "fenxiangtime", property = "fenxiangtime"),
            @Result(column = "zixunphone", property = "zixunphone")
    })
    @Select("<script>" +
            "SELECT * from  pxpaiketable"
            + "</script>")
    List<Pxpaiketable> getAllList();


    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxpaiketable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxpaiketable> selectpaike(@Param("ew") QueryWrapper queryWrapper);


    /**
     * 按时间查询空闲教师
     *
     * @param haveClassDate
     * @param starTime
     * @param endTime
     * @return
     */
    @Select("<script>" +
            "SELECT GROUP_CONCAT(DISTINCT t.teacherID) teaID\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id \n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxpaiketeachertable t on a.id=t.paikeID\n" +
            "WHERE a.haveClassDate =#{haveClassDate} and a.startLessonDateTime &gt;=#{starTime} and a.endLessonDateTime &lt;=#{endTime} and a.qiyeID=#{qiyeID}" +
            "</script>")
    List<teacherIDVo> getTeaList(String haveClassDate, String starTime, String endTime, Long qiyeID);


    /**
     * 按时间查询空闲教室
     *
     * @param haveClassDate
     * @param starTime
     * @param endTime
     * @return
     */
    @Select("<script>" +
            "SELECT GROUP_CONCAT(DISTINCT b.id) classRoomID\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclassroomtable b on a.classRoomID=b.id\n" +
            "LEFT JOIN pxcampustable c on b.campusID=c.id\n" +
            "WHERE c.isOpen!=2 and \n" +
            " a.haveClassDate =#{haveClassDate} and a.startLessonDateTime &gt;=#{starTime} and a.endLessonDateTime &lt;=#{endTime} and a.qiyeID=#{qiyeID}" +
            "</script>")
    List<paikeroomVo> getclassRoomList(String haveClassDate, String starTime, String endTime, Long qiyeID);


    /**
     * 排课时检测和老师时间是否冲突
     *
     * @param teacherID
     * @param haveclassDate
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxpaiketable a \n" +
            "LEFT JOIN pxpaiketeachertable b on a.id=b.paikeID\n" +
            "WHERE b.teacherID=#{teacherID} and a.haveClassDate=#{haveclassDate} and a.qiyeID=#{qiyeID} "
            + "</script>")
    List<Pxpaiketable> getbupkTea(Long teacherID, Date haveclassDate, Long qiyeID);


    @Select("<script>" +
            "SELECT * from pxpaiketable a \n" +
            "LEFT JOIN pxpaiketeachertable b on a.id=b.paikeID\n" +
            "WHERE b.teacherID=#{teacherID} and a.haveClassDate=#{haveclassDate} and a.id !=#{paikeID} and a.qiyeID=#{qiyeID}"
            + "</script>")
    List<Pxpaiketable> editbupkTea(Long teacherID, Date haveclassDate, Long paikeID, Long qiyeID);

    /**
     * 检测和班里的学生已有的排课时间是否有冲突
     *
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxpaiketable a \n" +
            "LEFT JOIN pxxuanketable b on a.id =b.paikeID\n" +
            "WHERE a.haveClassDate =#{haveClassDate} and b.stuID=#{stuID} and a.qiyeID=#{qiyeID}"
            + "</script>")
    List<Pxpaiketable> getstuXuankeList(Date haveClassDate, Long stuID, Long qiyeID);

    /**
     * 排课显示
     *
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,a.kechengContent kechengContent,b.id as classID,\n" +
            "e.subjectName subjectName,b.className className,d.id kechengID,d.kechengName kechengName,a.classRoomID classRoomID,c.classRoomName classRoomName,a.MaxStuNum MaxStuNum,\n" +
            "a.teacherIDs teacherIDs,a.teacherNames teacherNames,a.tags tags,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,a.huodongImg,a.huodongTitle,a.liulangtime,a.fenxiangtime,a.zixunphone,\n" +
            "(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID= a.id and (y.buxiStateID=1 or y.buxiStateID=2)) xkstuName\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxsubjecttable e on d.subjectID=e.id\n" +
            "WHERE ((SELECT YEAR(a.haveClassDate))=(SELECT YEAR(#{haveClassDate}))) and  ((SELECT MONth(a.haveClassDate))=(SELECT MONth(#{haveClassDate}))) AND a.paikeType = 1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<paikeShowVo> getpaikeShowList(String haveClassDate, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 分页查询试听课信息
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,a.kechengContent kechengContent,b.id as classID,\n" +
            "e.subjectName subjectName,b.className className,d.id kechengID,d.kechengName kechengName,a.classRoomID classRoomID,c.classRoomName classRoomName,a.MaxStuNum MaxStuNum,\n" +
            "a.teacherIDs teacherIDs,a.teacherNames teacherNames,a.tags tags,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,a.huodongImg,a.huodongTitle,a.liulangtime,a.fenxiangtime,a.zixunphone," +
            "a.shitingprice,d.kechengImg,d.kechengcontent as kechengInfo,a.huodongshuoming,\n" +
            "(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID= a.id and (y.buxiStateID=1 or y.buxiStateID=2)) xkstuName," +
            "(SELECT COUNT(z.id) from pxxuanketable z LEFT JOIN pxstutable s on z.stuID=s.id where z.paikeID=a.id and s.buxiStateID=1) xkNum \n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxsubjecttable e on d.subjectID=e.id\n" +
            "WHERE a.paikeType = 2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<paikeShowVo> GetPaikeShowPages(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 查询排课详情
     *
     * @param Id
     * @return
     */
    @Select("<script>" + "SELECT a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,a.kechengContent kechengContent,\n" +
            "e.subjectName subjectName,b.className className,d.id kechengID,d.kechengName kechengName,a.classRoomID classRoomID,c.classRoomName classRoomName,a.MaxStuNum MaxStuNum,\n" +
            "a.teacherIDs teacherIDs,a.teacherNames teacherNames,a.classID,a.tags AS tags,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,a.huodongImg,a.huodongTitle,a.liulangtime,a.fenxiangtime,a.zixunphone," +
            "a.shitingprice,d.kechengImg,d.kechengcontent as kechengInfo,a.huodongshuoming,\n" +
            "(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID= a.id and (y.buxiStateID=1 or y.buxiStateID=2)) xkstuName,a.zhujiaoID,a.istuisongTixingMsg\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxsubjecttable e on d.subjectID=e.id\n" +
            "WHERE a.id=#{Id}" + "</script>")
    List<paikelistVo> getpaikeListInfo(long Id);

    /**
     * 科目课表
     *
     * @param haveClassDate
     * @param subjectID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,\n" +
            "a.classID classID,b.className className,d.id kechengID,d.kechengName kechengName,a.kechengContent kechengContent,a.classRoomID classRoomID,\n" +
            "c.classRoomName classRoomName,a.MaxStuNum MaxStuNum,a.teacherNames teacherNames,a.teacherIDs teacherIDs,a.tags tags,e.subjectName subjectName,\n" +
            "e.id subjectID,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,\n" +
            "(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID=a.id) xkstuName\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxsubjecttable e on d.subjectID=e.id\n" +
            "WHERE ((SELECT YEAR(a.haveClassDate))=(SELECT YEAR(#{haveClassDate} ))) and ((SELECT MONth(a.haveClassDate))=(SELECT MONth(#{haveClassDate} )))\n" +
            "and d.subjectID=#{subjectID} and a.qiyeID=#{qiyeID} \n" +
            "ORDER BY a.haveClassDate,a.startLessonDateTime\n"
            + "</script>")
    List<subjectkebiaoVo> getsubjectkebiaoList(String haveClassDate, Long subjectID, Long qiyeID);


    /**
     * 导出科目课表
     */
    @Select("<script>" +
            "SELECT a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,\n" +
            "a.classID classID,b.className className,d.id kechengID,d.kechengName kechengName,a.kechengContent kechengContent,a.classRoomID classRoomID,\n" +
            "c.classRoomName classRoomName,a.MaxStuNum MaxStuNum,a.teacherNames teacherNames,a.teacherIDs teacherIDs,a.tags tags,e.subjectName subjectName,\n" +
            "e.id subjectID,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,\n" +
            "(SELECT COUNT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID=a.id) xkstuSum," +
            "(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID=a.id) xkstuName\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxsubjecttable e on d.subjectID=e.id\n" +
            "WHERE ((SELECT YEAR(a.haveClassDate))=(SELECT YEAR(#{haveClassDate} ))) and ((SELECT MONth(a.haveClassDate))=(SELECT MONth(#{haveClassDate} )))\n" +
            "and d.subjectID=#{subjectID} and a.qiyeID=#{qiyeID} \n" +
            "ORDER BY a.haveClassDate,a.startLessonDateTime\n"
            + "</script>")
    List<subjectkebiaoVo> ExportsubjectkebiaoList(String haveClassDate, Long subjectID, Long qiyeID);


    /**
     * 教师课表
     *
     * @param haveClassDate
     * @param teacherIDs
     * @return
     */
    @Select("<script>" +
            "  SELECT a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,a.classID classID,\n" +
            "            b.className className,a.classRoomID classRoomID,c.classRoomName classRoomName,d.id kechengID,a.kechengContent kechengContent,a.MaxStuNum MaxStuNum,a.teacherNames teacherNames,\n" +
            "            a.teacherIDs teacherIDs,a.tags tags,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,e.id AS subjectID,e.subjectName AS subjectName,\n" +
            "\t\t\t\t\t\t(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID=a.id) xkstuName\n" +
            "            from pxpaiketable a\n" +
            "            LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "            LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "           LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "            LEFT JOIN pxsubjecttable e on d.subjectID=e.id " +
            "WHERE ((SELECT YEAR(a.haveClassDate))=(SELECT YEAR(#{haveClassDate}))) and ((SELECT MONth(a.haveClassDate))=(SELECT MONth(#{haveClassDate})))\n" +
            "and find_in_set(#{teacherIDs}, a.teacherIDs) " +
            "and a.qiyeID=#{qiyeID} \n" +
            "ORDER BY a.haveClassDate,a.startLessonDateTime"
            + "</script>")
    List<teacherkebiaoVo> getTeacherkebiaoList(String haveClassDate, String teacherIDs, Long qiyeID);


    /**
     * 导出教师课表
     *
     * @param haveClassDate
     * @param teacherIDs
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,a.classID classID,\n" +
            "b.className className,a.classRoomID classRoomID,c.classRoomName classRoomName,d.id kechengID,a.kechengContent kechengContent,a.MaxStuNum MaxStuNum,a.teacherNames teacherNames,\n" +
            "a.teacherIDs teacherIDs,a.tags tags,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,e.id AS subjectID,e.subjectName AS subjectName,\n" +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE \n" +
            "(SELECT COUNT(u.id)\n" +
            "from pxbuxikechengtable u \n" +
            "LEFT JOIN pxstuclasstable v on u.id=v.buxiID \n" +
            "LEFT JOIN pxstutable w on u.stuID =w.id \n" +
            "WHERE v.classID = a.classID  and u.isShow=1 and (w.buxiStateID=1 or w.buxiStateID=2))  END) from pxstuclasstable WHERE classID = a.classID) ysSum \n" +
            "from pxpaiketable a\n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n " +
            "LEFT JOIN pxsubjecttable e on d.subjectID=e.id " +
            "WHERE ((SELECT YEAR(a.haveClassDate))=(SELECT YEAR(#{haveClassDate}))) and ((SELECT MONth(a.haveClassDate))=(SELECT MONth(#{haveClassDate}))) \n" +
            "and a.teacherIDs =#{teacherIDs} and a.qiyeID=#{qiyeID} \n" +
            "ORDER BY a.haveClassDate,a.startLessonDateTime"
            + "</script>")
    List<teacherkebiaoVo> ExportTeacherkebiaoList(String haveClassDate, String teacherIDs, Long qiyeID);


    /**
     * 学员课表
     *
     * @param haveClassDate
     * @param stuID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id ,f.campusID campusID,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,\n" +
            "a.weekN weekN,a.classID classID,d.kechengName kechengName,u.subjectName subjectName,b.className className,\n" +
            "a.kechengContent kechengContent,a.teacherNames teacherNames,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,\t(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID=a.id) xkstuName\n" +
            "from pxpaiketable a\n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxsubjecttable u on d.subjectID=u.id\n" +
            "LEFT JOIN pxxuanketable x on a.id=x.paikeID\n" +
            "LEFT JOIN pxbuxikechengtable e on x.buxiID=e.id\n" +
            "LEFT JOIN pxstutable f on x.stuID=f.id\n" +
            "LEFT JOIN pxpaiketeachertable t on a.id=t.paikeID\n" +
            "WHERE 1=1 and ((SELECT YEAR(a.haveClassDate))=(SELECT YEAR(#{haveClassDate}))) and ((SELECT MONth(a.haveClassDate))=(SELECT MONth(#{haveClassDate} ))) \n" +
            "and f.id=#{stuID} and a.qiyeID=#{qiyeID} " +
            "ORDER BY a.haveClassDate,a.startLessonDateTime"
            + "</script>")
    List<stukebiaoVO> getstukebiaoList(String haveClassDate, Long stuID, Long qiyeID);


    /**
     * 导出学员课表
     *
     * @param haveClassDate
     * @param stuID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id ,f.campusID campusID,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,\n" +
            "a.weekN weekN,a.classID classID,d.kechengName kechengName,u.subjectName subjectName,b.className className,f.stuName stuName,\n" +
            "a.kechengContent kechengContent,a.teacherNames teacherNames,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor \n" +
            "from pxpaiketable a\n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxsubjecttable u on d.subjectID=u.id\n" +
            "LEFT JOIN pxxuanketable x on a.id=x.paikeID\n" +
            "LEFT JOIN pxbuxikechengtable e on x.buxiID=e.id\n" +
            "LEFT JOIN pxstutable f on x.stuID=f.id\n" +
            "LEFT JOIN pxpaiketeachertable t on a.id=t.paikeID\n" +
            "WHERE e.stuID=f.id and ((SELECT YEAR(a.haveClassDate))=(SELECT YEAR(#{haveClassDate}))) and ((SELECT MONth(a.haveClassDate))=(SELECT MONth(#{haveClassDate} ))) \n" +
            "and f.id=#{stuID}  and a.qiyeID=#{qiyeID} " +
            "ORDER BY a.haveClassDate,a.startLessonDateTime"
            + "</script>")
    List<stukebiaoVO> ExportstukebiaoList(String haveClassDate, Long stuID, Long qiyeID);

    /**
     * 教室课表
     */
    @Select("<script>" +
            "select a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,\n" +
            "a.classID classID ,b.className className,a.classRoomID classRoomID,c.classRoomName classRoomName,d.kechengName kechengName,e.subjectName subjectName,a.teacherNames teacherNames,\n" +
            "a.kechengContent kechengContent,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID=a.id) xkstuName\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID =d.id\n" +
            "LEFT JOIN pxsubjecttable e on d.subjectID=e.id\n" +
            "WHERE ((SELECT YEAR(a.haveClassDate))=(SELECT YEAR(#{haveClassDate}))) and  ((SELECT MONth(a.haveClassDate))=(SELECT MONth(#{haveClassDate})))\n " +
            "AND a.classRoomID=#{classRoomID} and a.qiyeID=#{qiyeID}  " +
            "ORDER BY a.haveClassDate,a.startLessonDateTime "
            + "</script>")
    List<jiaoshikebiaoVO> getjiaoshikebiaoList(String haveClassDate, Long classRoomID, Long qiyeID);


    /**
     * 导出教室课表
     *
     * @param haveClassDate
     * @param classRoomID
     * @return
     */
    @Select("<script>" +
            "select a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,\n" +
            "a.classID classID ,b.className className,a.classRoomID classRoomID,c.classRoomName classRoomName,d.kechengName kechengName,e.subjectName subjectName,a.teacherNames teacherNames,\n" +
            "a.kechengContent kechengContent,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID =d.id\n" +
            "LEFT JOIN pxsubjecttable e on d.subjectID=e.id\n" +
            "WHERE ((SELECT YEAR(a.haveClassDate))=(SELECT YEAR(#{haveClassDate}))) and  ((SELECT MONth(a.haveClassDate))=(SELECT MONth(#{haveClassDate})))\n " +
            "AND a.classRoomID=#{classRoomID}  and a.qiyeID=#{qiyeID} " +
            "ORDER BY a.haveClassDate,a.startLessonDateTime "
            + "</script>")
    List<jiaoshikebiaoVO> ExportjiaoshikebiaoList(String haveClassDate, Long classRoomID, Long qiyeID);


    /**
     * 天课表
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,f.campusName campusName,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,a.classID classID,a.kechengID kechengID,\n" +
            "d.kechengName kechengName,a.haveClassDate haveClassDate,f.campusName xiaoqu,b.className className,b.campusID campusID,c.classRoomName classRoomName,e.classTimeStyleName classTimeStyleName,\n" +
            "(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxbuxikechengtable z LEFT JOIN pxstutable y on z.stuID=y.id LEFT JOIN pxxuanketable s on z.stuID = s.stuID WHERE s.paikeID = a.id ) stuName,\n" +
            "(SELECT COUNT(DISTINCT s.id) from pxbuxikechengtable u LEFT JOIN pxstutable v on u.stuID=v.id LEFT JOIN pxxuanketable s on v.id = s.stuID where s.paikeID = a.id and u.id = s.buxiID and (v.buxiStateID=2 or v.buxiStateID=1 ) ) yshangcount,\n" +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE COUNT(id) END)\n" +
            "from pxstukaoqingtable w WHERE w.classID=a.classID and w.haveclassDate=a.haveClassDate and w.startClassDateTime=a.startLessonDateTime and w.endClassDateTime=a.endLessonDateTime and w.teacherIDs=a.teacherIDs and ((SELECT buxiStateID from pxstutable WHERE id=w.stuID)=2 or (SELECT buxiStateID from pxstutable WHERE id=w.stuID)=1)   ) havekaoqingStuNum,\n" +
            "a.teacherIDs teacherIDs,a.teacherNames teacherNames,a.dakaoqin dakaoqin\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID = c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxclasstimestyletable e on d.classTimeStyleID=e.id\n" +
            "LEFT JOIN pxcampustable f on c.campusID=f.id\n" +
            "WHERE f.isOpen !=2 and d.isShow=1\n " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<daykebiaoVo> getDaykebiaopage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 导出天课表
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,a.classID classID,a.kechengID kechengID,\n" +
            "d.kechengName kechengName,a.haveClassDate haveClassDate,f.campusName xiaoqu,b.className className,b.campusID campusID,f.campusName campusName,c.classRoomName classRoomName,e.classTimeStyleName classTimeStyleName,\n" +
            "(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxbuxikechengtable z LEFT JOIN pxstutable y on z.stuID=y.id LEFT JOIN pxxuanketable s on z.stuID = s.stuID WHERE s.paikeID = a.id ) stuName,\n" +
            "(SELECT COUNT(DISTINCT s.id) from pxbuxikechengtable u LEFT JOIN pxstutable v on u.stuID=v.id LEFT JOIN pxxuanketable s on v.id = s.stuID where s.paikeID = a.id and u.id = s.buxiID and (v.buxiStateID=2 or v.buxiStateID=1 ) ) yshangcount,\n" +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE COUNT(id) END)\n" +
            "from pxstukaoqingtable w WHERE w.classID=a.classID and w.haveclassDate=a.haveClassDate and w.startClassDateTime=a.startLessonDateTime and w.endClassDateTime=a.endLessonDateTime and w.teacherIDs=a.teacherIDs and ((SELECT buxiStateID from pxstutable WHERE id=w.stuID)=2 or (SELECT buxiStateID from pxstutable WHERE id=w.stuID)=1)   ) havekaoqingStuNum,\n" +
            "a.teacherIDs teacherIDs,a.teacherNames teacherNames,a.dakaoqin dakaoqin\n" +
            "from pxpaiketable a \n" +
            "LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable c on a.classRoomID = c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxclasstimestyletable e on d.classTimeStyleID=e.id\n" +
            "LEFT JOIN pxcampustable f on c.campusID=f.id\n" +
            "WHERE f.isOpen !=2 and d.isShow=1\n " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<daykebiaoVo> ExportdaykebiaoList(@Param("ew") QueryWrapper queryWrapper);


    //region 人工消课


    /**
     * 分页获取人工消课
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,a.startLessonDateTime as startLessonDateTime,a.endLessonDateTime as endLessonDateTime," +
            "a.weekN as weekN,a.classID as classID,a.haveClassDate as haveClassDate,a.kechengID kechengID,\n" +
            "e.campusName as campusName,b.className as className,b.is1v1Class is1v1Class,d.classRoomName as classRoomName," +
            "f.classTimeStyleName as classTimeStyleName,a.teacherIDs as teacherIDs,\n" +
            "a.teacherNames as teacherNames,\n" +
            "(SELECT  GROUP_CONCAT(stuName)\n" +
            "FROM pxbuxikechengtable as x \n" +
            "LEFT JOIN pxstutable as y on x.stuID=y.id\n" +
            "LEFT JOIN pxxuanketable as w on x.id=w.buxiID\n" +
            "WHERE w.paikeID =a.id \n" +
            ") as stuName,\n" +
            "(SELECT COUNT(x.id)\n" +
            "FROM pxbuxikechengtable as x \n" +
            "LEFT JOIN pxstutable as y on x.stuID=y.id\n" +
            "LEFT JOIN pxxuanketable as w on y.id=w.stuID\n" +
            "WHERE w.paikeID =a.id and x.id=w.buxiID and (y.buxiStateID =2 or y.buxiStateID=1)\n" +
            ") as yshangcount,\n" +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE (SELECT COUNT(z.id) FROM pxstukaoqingtable as z where " +
            "z.classID=a.classID and z.haveclassDate=a.haveclassDate and z.startClassDateTime=a.startLessonDateTime " +
            " and z.endClassDateTime=a.endLessonDateTime " +
            " and ((SELECT buxiStateID from pxstutable WHERE id=w.stuID limit 1)=2 " +
            " or (SELECT buxiStateID from pxstutable WHERE id=w.stuID limit 1)=1) ) END)  \n" +
            " FROM pxstukaoqingtable as w where w.classID=a.classID and w.haveclassDate=a.haveclassDate " +
            " and w.startClassDateTime=a.startLessonDateTime and w.endClassDateTime=a.endLessonDateTime ) as havekaoqingStuNum," +
            "(case WHEN a.dakaoqin=TRUE THEN '已完成考勤' ELSE '未完成考勤' END) as dakaoqin\n" +
            "from pxpaiketable as a \n" +
            "LEFT JOIN pxclasstable as b on a.classID=b.id\n" +
            "LEFT JOIN pxkechengtable as c on a.kechengID=c.id\n" +
            "LEFT JOIN pxclassroomtable as d on a.classRoomID=d.id\n" +
            "LEFT JOIN pxcampustable as e on b.campusID=e.id\n" +
            "LEFT JOIN pxclasstimestyletable as f on c.classTimeStyleID=f.id\n" +
            "where e.isOpen !=2 and c.isShow=1\n " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            " order by a.haveClassDate asc, a.startLessonDateTime asc "+
            "</script>")
    Page<rengongxiaokeVo> getrengongxiaokePage(Page page, @Param("ew") QueryWrapper queryWrapper);


    /**
     * 导出人工消课
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,a.startLessonDateTime as startLessonDateTime,a.endLessonDateTime as endLessonDateTime,a.weekN as weekN,a.classID as classID,a.haveClassDate as haveClassDate,\n" +
            "e.campusName as campusName,b.className as className,d.classRoomName as classRoomName,f.classTimeStyleName as classTimeStyleName,a.teacherIDs as teacherIDs,\n" +
            "a.teacherNames as teacherNames,\n" +
            "(SELECT  GROUP_CONCAT(stuName)\n" +
            "FROM pxbuxikechengtable as x \n" +
            "LEFT JOIN pxstutable as y on x.stuID=y.id\n" +
            "LEFT JOIN pxxuanketable as w on x.id=w.buxiID\n" +
            "WHERE w.paikeID =a.id \n" +
            ") as stuName,\n" +
            "(SELECT COUNT(y.stuName)\n" +
            "FROM pxbuxikechengtable as x \n" +
            "LEFT JOIN pxstutable as y on x.stuID=y.id\n" +
            "LEFT JOIN pxxuanketable as w on y.id=w.stuID\n" +
            "WHERE w.paikeID =a.id and x.id=w.buxiID and (y.buxiStateID =2 or y.buxiStateID=1)\n" +
            ") as yshangcount,\n" +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE (SELECT COUNT(z.id) FROM pxstukaoqingtable as z where z.classID=a.classID and z.haveclassDate=a.haveclassDate and z.startClassDateTime=a.startLessonDateTime and z.endClassDateTime=a.endLessonDateTime and w.teacherIDs=a.teacherIDs and ((SELECT buxiStateID from pxstutable WHERE id=w.stuID limit 1)=2 or (SELECT buxiStateID from pxstutable WHERE id=w.stuID limit 1)=1) ) END)  \n" +
            "FROM pxstukaoqingtable as w where w.classID=a.classID and w.haveclassDate=a.haveclassDate and w.startClassDateTime=a.startLessonDateTime and w.endClassDateTime=a.endLessonDateTime ) as havekaoqingStuNum,(case WHEN a.dakaoqin=TRUE THEN '已完成考勤' ELSE '未完成考勤' END) as dakaoqin\n" +
            "from pxpaiketable as a \n" +
            "LEFT JOIN pxclasstable as b on a.classID=b.id\n" +
            "LEFT JOIN pxkechengtable as c on a.kechengID=c.id\n" +
            "LEFT JOIN pxclassroomtable as d on a.classRoomID=d.id\n" +
            "LEFT JOIN pxcampustable as e on b.campusID=e.id\n" +
            "LEFT JOIN pxclasstimestyletable as f on c.classTimeStyleID=f.id\n" +
            "where e.isOpen !=2 and c.isShow=1\n " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<rengongxiaokeVo> Exportrengongxiaoke(@Param("ew") QueryWrapper queryWrapper);

    //endregion

    //region 人工签到

    /**
     * 人工签到，按排课消课 签到签退(事件)
     *
     * @param stuID
     * @param paikeID
     * @return paike记录
     */
    @Select("<script>" +
            "SELECT a.id as id,a.startLessonDateTime as startLessonDateTime, a.endLessonDateTime as endLessonDateTime,a.haveClassDate as haveClassDate,a.weekN as weekN,a.classID as classID,\n" +
            "d.buxiStyleID as buxiStyleID,d.classTimeStyleID as classTimeStyleID, h.classTimeStyleName as classTimeStyleName,e.kechengprice as kechengPrice,a.teacherIDs as teacherIDs,a.teacherNames as teacherNames,\n" +
            "s.buxiID as buxiID,(SELECT className from pxclasstable where id=a.classID) as className,a.classRoomID as classRoomID,c.classRoomName as classRoomName,a.kechengID as kechengID \n" +
            "from pxpaiketable as a  \n" +
            "LEFT JOIN pxclasstable as b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable as c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxxuanketable as s on a.id=s.paikeID\n" +
            "LEFT JOIN pxbuxikechengtable as e on s.buxiID=e.ID\n" +
            "LEFT JOIN pxkechengtable as d on e.kechengID=d.id\n" +
            "LEFT JOIN pxstutable as f on e.stuID=f.ID\n" +
            "LEFT JOIN pxclasstimestyletable as h on d.classTimeStyleID=h.id\n" +
            "where a.qiyeID=#{qiyeID} and f.ID =#{stuID} and a.id =#{paikeID} and d.isShow =1 and a.dakaoqin != TRUE\n" +
            "GROUP BY a.id, a.classID, a.classRoomID, a.weekN, a.startLessonDateTime, a.endLessonDateTime, a.haveClassDate, c.classRoomName, a.kechengID, d.buxiStyleID, d.classTimeStyleID, e.ID, e.kechengprice, a.teacherIDs "
            + "</script>")
    List<paikelistVo> getPaikeList(Long stuID, Long paikeID, Long qiyeID);


    /**
     * 获取班级人数
     *
     * @param paikeID
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxbuxikechengtable as a \n" +
            "LEFT JOIN pxstutable as b on a.stuID=b.id\n" +
            "LEFT JOIN pxxuanketable as c on a.id=c.buxiID\n" +
            "WHERE a.isShow=1 and b.id=c.stuID and (b.buxiStateID=2 or b.buxiStateID=1) and c.paikeID=#{paikeID} and a.qiyeID=#{qiyeID}" +
            "</script>")
    List<Pxbuxikechengtable> getclassStuCount(Long paikeID, Long qiyeID);


    /**
     * 获取补课
     *
     * @param stuID
     * @param paikeID
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxbuxikechengtable as a \n" +
            "LEFT JOIN pxxuanketable as b on a.id=b.buxiID\n" +
            "where a.stuID=#{stuID} and b.paikeID=#{paikeID} and a.qiyeID=#{qiyeID} and a.isShow=1" +
            "</script>")
    List<Pxbuxikechengtable> getbk(Long stuID, Long paikeID, Long qiyeID);

    //endregion


    /**
     * 刷卡消课获取排课
     *
     * @param haveClassDate
     * @param cardNumber
     * @param kaishi
     * @param jiesu
     * @param zhong
     * @return
     */
    @Select("<script>" +
            "SELECT  a.id as id,a.startLessonDateTime as startLessonDateTime,a.endLessonDateTime as endLessonDateTime,a.weekN as weekN,a.classID as classID,\n" +
            "a.haveClassDate as haveClassDate,f.buxiStyleID as buxiStyleID,f.classTimeStyleID as classTimeStyleID,i.classTimeStyleName as classTimeStyleName,\n" +
            "e.id as buxiID,f.kechengprice as kechengprice,\n" +
            "a.teacherIDs as teacherIDs,a.teacherNames as teacherNames,f.id as kechengID,a.classRoomID as classRoomID,c.classRoomName as classRoomName\n" +
            "from pxpaiketable as a \n" +
            "LEFT JOIN pxclasstable as b on a.classID=b.id\n" +
            "LEFT JOIN pxclassroomtable as c on a.classRoomID=c.id\n" +
            "LEFT JOIN pxxuanketable as d on a.id=d.paikeID\n" +
            "LEFT JOIN pxbuxikechengtable as e on d.buxiID=e.id\n" +
            "LEFT JOIN pxkechengtable as f on e.kechengID=f.id\n" +
            "LEFT JOIN pxstutable as g on e.stuID=g.id\n" +
            "LEFT JOIN pxstucardtable as h on g.id=h.stuID\n" +
            "LEFT JOIN pxclasstimestyletable as i on f.classTimeStyleID=i.id\n" +
            "WHERE f.isShow=1 and a.qiyeID=#{qiyeID} and a.dakaoqin !=TRUE " +
            "and a.haveClassDate=#{haveClassDate} and h.cardNumber=#{cardNumber} \n" +
            "and ((a.shuakaTimeArea=1 and a.startLessonDateTime &gt;=#{kaishi} and a.startLessonDateTime &lt;=#{jiesu} ) or " +
            "(a.shuakaTimeArea=2 and a.startLessonDateTime &lt;=#{zhong} and a.endLessonDateTime &gt;=#{zhong} ) or " +
            "(a.shuakaTimeArea=3 and a.endLessonDateTime &gt;=#{kaishi} and a.endLessonDateTime &lt;=#{zhong}))\n" +
            "GROUP BY a.id,a.classID,a.classRoomID,a.weekN,a.startLessonDateTime,a.endLessonDateTime,a.haveClassDate,\n" +
            "c.classRoomName,a.kechengID,f.buxiStyleID,f.classTimeStyleID,e.id,e.kechengprice,a.teacherIDs" +
            "</script>")
    List<shuakaxkgetPKVo> getshuakaxkgetPKList(Long qiyeID, String haveClassDate, String cardNumber, String kaishi, String jiesu, String zhong);

    /**
     * 根据日期查询排课
     *
     * @param startDate
     * @param endDate
     * @param starTime
     * @param endTime
     * @return
     */
    @Select("<script>" + "SELECT * FROM pxpaiketable AS a WHERE a.haveClassDate  &gt;=#{startDate} AND a.haveClassDate &lt;=#{endDate}  " +
            "and a.startLessonDateTime &gt;=#{starTime} and a.endLessonDateTime &lt;=#{endTime} and a.qiyeID=#{qiyeID}" + "</script>")
    List<Pxpaiketable> getPaikelistByDate(String startDate, String endDate, String starTime, String endTime, Long qiyeID);

    @Select("<script>" + "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tpxkeshistutable AS a \n" +
            "WHERE\n" +
            "\ta.classID = #{classID} AND a.haveClassDate = #{haveClassDate} AND a.startLessonDateTime=#{startLessonDateTime} AND a.endLessonDateTime=#{endLessonDateTime} AND a.qiyeID =#{qiyeID}" + "</script>")
    List<Pxkeshistutable> GetKeshiList(long classID, Date haveClassDate, Date startLessonDateTime, Date endLessonDateTime, Long qiyeID);

    @Select("<script>" + "SELECT\n" +
            "\t* \n" +
            "FROM\n" +
            "\tpxpaiketable AS a \n" +
            "WHERE\n" +
            "\ta.haveClassDate &gt;= #{startDate} AND a.haveClassDate &lt;=#{endDate} AND a.startLessonDateTime &gt;=#{startLessonDateTime} AND a.endLessonDateTime&lt;=#{endLessonDateTime} AND a.qiyeID=#{qiyeID}" + "</script>")
    List<Pxpaiketable> GetAllPaikeNeedUpdateTeacher(String startDate, String endDate, String startLessonDateTime, String endLessonDateTime, long qiyeID);

    /**
     * 查询重排所有的对应批次的排课信息
     *
     * @return
     */
    @Select("<script>" + "SELECT * FROM( SELECT a.*,(SELECT count(*) FROM pxkeshistutable WHERE classID = a.classID AND haveClassDate = a.haveClassDate AND " +
            "startLessonDateTime = a.startLessonDateTime AND  endLessonDateTime =a.endLessonDateTime) AS countkeshi FROM pxpaiketable AS a) AS c where" +
            " c.countkeshi=0 AND c.tags=#{tags} AND c.qiyeID=#{qiyeID}" + "</script>")
    List<Pxpaiketable> GetChongpaiPiciList(String tags, long qiyeID);

    /**
     * 查询要打印的科目课表信息
     *
     * @param qiyeID
     * @param startDate
     * @param endDate
     * @param subjectID
     * @return
     */
    @Select("<script>" + "  SELECT a.id id,a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.weekN weekN,a.classID classID,\n" +
            "            b.className className,a.classRoomID classRoomID,c.classRoomName classRoomName,d.id kechengID,a.kechengContent kechengContent,a.MaxStuNum MaxStuNum,a.teacherNames teacherNames,\n" +
            "            a.teacherIDs teacherIDs,a.tags tags,(case WHEN d.bgColor is null THEN '' ELSE d.bgColor END) bgColor,\n" +
            "\t\t\t\t\t\t(SELECT GROUP_CONCAT(DISTINCT y.stuName) from pxxuanketable z LEFT JOIN pxstutable y on z.stuID=y.id where z.paikeID=a.id) xkstuName\n" +
            "            from pxpaiketable a\n" +
            "            LEFT JOIN pxclasstable b on a.classID=b.id\n" +
            "            LEFT JOIN pxclassroomtable c on a.classRoomID=c.id\n" +
            "           LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "            LEFT JOIN pxsubjecttable e on d.subjectID=e.id \n" +
            "            WHERE a.qiyeID=#{qiyeID} AND d.subjectID=#{subjectID}  AND a.haveClassDate between #{startDate} and #{endDate}\n" +
            "            ORDER BY a.haveClassDate,a.startLessonDateTime" + "</script>")
    List<subjectkebiaoVo> GetPrintSubjectKebiaoList(long qiyeID, String startDate, String endDate, long subjectID);

    /*
     *请假获取排课信息
     */
    @Select("<script>" +
            "SELECT paike.id,stu.stuName,paike.haveClassDate,paike.weekN,paike.startLessonDateTime,\n" +
            "paike.endLessonDateTime,cla.classRoomName,k.kechengName,paike.kechengID kechengID,cla.id classID,c.className,sub.id sunjectID,sub.subjectName,staff.staffName \n" +
            "FROM pxpaiketable AS paike \n" +
            "LEFT JOIN pxclasstable AS c ON paike.classID = c.id\n" +
            "LEFT JOIN pxkechengtable AS k ON paike.kechengID = k.id\n" +
            "LEFT JOIN pxsubjecttable AS sub ON k.subjectID = sub.id\n" +
            "LEFT JOIN pxclassroomtable AS cla ON paike.classRoomID = cla.id\n" +
            "LEFT JOIN pxxuanketable AS xuan ON paike.id=xuan.paikeID\n" +
            "LEFT JOIN pxbuxikechengtable AS bx ON xuan.buxiID = bx.id\n" +
            "LEFT JOIN pxstutable AS stu ON xuan.stuID = stu.id\n" +
            "LEFT JOIN pxpaiketeachertable AS pt ON paike.id = pt.paikeID\n" +
            "LEFT JOIN pxstafftable AS staff ON pt.teacherID = staff.id  \n" +
            "WHERE paike.dakaoqin !=true and \n" +
            " (SELECT count(id) from pxqingjiatable where paikeID =paike.id and stuid=#{stuID})=0 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<qjpaikeVo> getstupaiketoqjInfo(Page page, Long stuID, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 学员请假详情
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxqingjiatable a \n" +
            "LEFT JOIN pxkechengtable b on a.KechengID=b.id\n" +
            "where 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<qjInfoVo> getstuqingjiaInfo(Page page, @Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" + "SELECT stu.id AS stuID,stu.stuName ,a.recordDate AS joinDateTime from  pxxuanketable AS a LEFT JOIN pxstutable AS stu ON a.stuID = stu.id" + " where 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<shitingstuVO> GetAllShitingJoinstuInfo(@Param("ew") QueryWrapper queryWrapper);

}