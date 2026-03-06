package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxqiandaoqiantuitable;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-04
 */
@Repository
public interface IPxqiandaoqiantuitableDao extends BaseMapper<Pxqiandaoqiantuitable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandaoOrqiantui", property = "qiandaoOrqiantui"),
            @Result(column = "qianDatetime", property = "qianDatetime"),
            @Result(column = "qianStyle", property = "qianStyle"),
            @Result(column = "tsState", property = "tsState"),
            @Result(column = "paikeID", property = "paikeID"),
            @Result(column = "addstaffID", property = "addstaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandaoqiantuitable"
            + "</script>")
    List<Pxqiandaoqiantuitable> getAllList();

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxqiandaoqiantuitable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxqiandaoqiantuitable> selectqiaodao(@Param("ew") QueryWrapper queryWrapper);

    //region 刷卡消课
    @Select("<script>" +
            "SELECT a.id as id,a.stuID as stuID,b.zidingyiStuID as zidingyiStuID,d.cardNumber as cardNumber,c.campusName as campusName,b.stuName as stuName,\n" +
            "a.paikeID as paikeID,g.kechengName as kechengName,f.teacherNames as teacherNames,f.teacherIDs as teacherIDs,i.buxiStyleName as buxiStyleName,\n" +
            "TIMESTAMPDIFF(MINUTE,f.startLessonDateTime,f.endLessonDateTime)/j.classTimeStyleName as keshi,a.qiandaoOrqiantui qiandaoOrqiantui,\n" +
            "f.haveClassDate as haveClassDate,f.startLessonDateTime as startLessonDateTime,f.endLessonDateTime as endLessonDateTime,a.qianDatetime as qianDatetime, \n " +
            "(select staffName from pxstafftable where id=b.banzhurenTeacherID ) banzhuren " +
            "FROM pxqiandaoqiantuitable as a \n" +
            "LEFT JOIN pxstutable as b on a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable as c on b.campusID=c.id\n" +
            "LEFT JOIN pxstucardtable as d on b.id=d.stuID\n" +
            "left JOIN pxstafftable as e on a.addstaffID=e.id\n" +
            "LEFT JOIN pxpaiketable as f on a.paikeID=f.id\n" +
            "LEFT JOIN pxkechengtable as g on f.kechengID=g.id\n" +
            "LEFT JOIN pxbuxistyletable as i on g.buxiStyleID=i.id\n" +
            "LEFT JOIN pxclasstimestyletable as j on g.classTimeStyleID=j.id\n" +
            "WHERE c.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<shuakaxiaokeVo> getshuakaxiaokePage(Page page, @Param("ew") QueryWrapper queryWrapper);


    /**
     * 导出刷卡消课
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,(case WHEN b.zidingyiStuID is null THEN a.stuID ELSE b.zidingyiStuID END) as stuID,\n" +
            "d.cardNumber as cardNumber,c.campusName as campusName,b.stuName as stuName,\n" +
            "a.paikeID as paikeID,g.kechengName as kechengName,f.teacherNames as teacherNames,f.teacherIDs as teacherIDs,i.buxiStyleName as buxiStyleName,\n" +
            "TIMESTAMPDIFF(MINUTE,f.startLessonDateTime,f.endLessonDateTime)/j.classTimeStyleName as keshi,\n" +
            "f.haveClassDate as haveClassDate,f.startLessonDateTime as startLessonDateTime,f.endLessonDateTime as endLessonDateTime,a.qianDatetime as qianDatetime \n" +
            "FROM pxqiandaoqiantuitable as a \n" +
            "LEFT JOIN pxstutable as b on a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable as c on b.campusID=c.id\n" +
            "LEFT JOIN pxstucardtable as d on b.id=d.stuID\n" +
            "left JOIN pxstafftable as e on a.addstaffID=e.id\n" +
            "LEFT JOIN pxpaiketable as f on a.paikeID=f.id\n" +
            "LEFT JOIN pxkechengtable as g on f.kechengID=g.id\n" +
            "LEFT JOIN pxbuxistyletable as i on g.buxiStyleID=i.id\n" +
            "LEFT JOIN pxclasstimestyletable as j on g.classTimeStyleID=j.id\n" +
            "WHERE (a.paikeID is not null ) and a.paikeID !=''  and c.isOpen !=2 and a.qianStyle=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<shuakaxiaokeVo> Exportshuakaxiaoke(@Param("ew") QueryWrapper queryWrapper);
    //endregion

    //region 刷卡签到签退
    @Select("<script>" +
            "SELECT a.id  as id,c.campusName as campusName,d.stuGradeName as stuGradeName," +
            "a.stuID stuID,b.zidingyiStuID zidingyiStuID,b.stuName as stuName,a.qianDatetime as qianDatetime " +
            "from pxqiandaoqiantuitable as a " +
            "left JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxcampustable as c on b.campusID=c.id " +
            "LEFT JOIN pxstugradetable as d on b.stuGradeID=d.id " +
            "where c.isOpen !=2  and a.qiandaoOrqiantui=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<shuakaVo> getshuakaPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 导出刷卡签到签退
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id  as id,c.campusName as campusName,d.stuGradeName as stuGradeName,(case WHEN b.zidingyiStuID is null  THEN a.stuID ELSE b.zidingyiStuID END) as stuID,b.stuName as stuName,a.qianDatetime as qianDatetime " +
            "from pxqiandaoqiantuitable as a " +
            "left JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxcampustable as c on b.campusID=c.id " +
            "LEFT JOIN pxstugradetable as d on b.stuGradeID=d.id " +
            "where c.isOpen !=2  and a.qiandaoOrqiantui=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<shuakaVo> ExportshuakaPage(@Param("ew") QueryWrapper queryWrapper);

    //endregion

    //region 人工签到签退

    //region 排课签到签退

    /**
     * 考勤签到签退流水
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,d.stuName as stuName,\n" +
            "(case WHEN a.qiandaoOrqiantui=1 THEN '签到' ELSE '签退' END) as qiandao,\n" +
            "c.haveClassDate haveClassDate,(case WHEN a.qianStyle=1 THEN '刷卡' WHEN a.qianStyle=2 THEN '微信' ELSE'人工' END) as qianStyle,\n" +
            "c.startLessonDateTime startLessonDateTime, (case WHEN a.tsState=TRUE THEN '成功' ELSE '失败' END) as TStype,\n" +
            "a.qianDatetime as qianDatetime,b.staffName as staffName,c.endLessonDateTime endLessonDateTime,a.stuID stuID,\n" +
            "d.zidingyiStuID zidingyiStuID \n" +
            "from pxqiandaoqiantuitable as a\n" +
            "LEFT JOIN pxstafftable as b on a.addstaffID=b.id \n" +
            "LEFT JOIN pxpaiketable as c on a.paikeID=c.id \n" +
            "LEFT JOIN pxstutable as d on a.stuID=d.id \n" +
            "LEFT JOIN pxcampustable as e on d.campusID=e.id \n" +
            "where e.isOpen!=2 and a.paikeID is not null \n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<qiandaoLiushuiVo> getqiandaoliushuiPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 人工签到签退统计
     */
    @Select("<script>" +
            "SELECT b.stuName as stuName,(case WHEN b.zidingyiStuID is NULL THEN b.id ELSE b.zidingyiStuID END) as stuID,\n" +
            "(SELECT COUNT(id) from pxqiandaoqiantuitable where stuID=a.stuID and qiandaoOrqiantui=1 and qianStyle=3) as qdNum,\n" +
            "(SELECT COUNT(id) from pxqiandaoqiantuitable where stuID=a.stuID and qiandaoOrqiantui=2 and qianStyle=3) as qtNum \n" +
            "from pxqiandaoqiantuitable as a \n" +
            "LEFT JOIN pxstutable as b on a.stuID=b.id  \n" +
            "where a.qiyeID =#{qiyeID} " +
            "GROUP BY b.id"
            + "</script>")
    List<ExportQianCountVo> ExportQianCount(Long qiyeID);

    /**
     * 人工签到签退详情
     */
    @Select("<script>" +
            "SELECT b.stuName as stuName,(case WHEN a.qiandaoOrqiantui=1 THEN '签到' ELSE '签退' END) as DorT,\n" +
            "((case WHEN a.qianStyle=1 THEN '刷卡' ELSE (case WHEN a.qianStyle=2 THEN '微信' ELSE '人工' END) END)) as qianStyle,\n" +
            "((case WHEN a.tsState=TRUE THEN '成功' ELSE '失败' END)) as tsState,\n" +
            "concat(concat(d.haveClassDate ,'  ', d.startLessonDateTime),'~',d.endLessonDateTime) as haveClassDate ,a.qianDatetime as qianDatetime,c.staffName as addStaffName \n" +
            "FROM pxqiandaoqiantuitable as a \n" +
            "LEFT JOIN pxstutable as b on a.stuID =b.id\n" +
            "LEFT JOIN pxstafftable as c on a.addstaffID=c.id\n" +
            "LEFT JOIN pxpaiketable as d on a.paikeID=d.id where a.qiyeID=#{qiyeID} \n"
            + "</script>")
    List<ExportQianInfoVo> ExportQianInfo(Long qiyeID);

    //endregion

    //region 自由签到签退

    /**
     * 人工签到签退页面
     * 自由签到签退 分页获取
     */
    @Select("<script>" +
            "SELECT c.campusName as campusName,b.className as className,a.classID classID,\n" +
            "(select COUNT(id) from pxstuclasstable where classID = a.classID) as yingQianDaoCount,\n" +
            "(SELECT COUNT(id) from pxstuclasstable as aa WHERE aa.buxiID in (SELECT id from pxbuxikechengtable as bb where bb.stuID in (SELECT stuID from pxqiandaoqiantuitable where qiandaoOrqiantui=1) ) and classID =a.classID)  as yiQianDaoCount,\n" +
            "(SELECT COUNT(id) from pxstuclasstable as aa WHERE aa.buxiID in (SELECT id from pxbuxikechengtable as bb where bb.stuID in (SELECT stuID from pxqiandaoqiantuitable where qiandaoOrqiantui=2) ) and classID =a.classID)  as yituiDaoCount\n" +
            "from pxstuclasstable as a \n" +
            "LEFT JOIN pxclasstable as b on a.classID= b.id\n" +
            "LEFT JOIN pxcampustable as c on b.campusID=c.id\n" +
            "WHERE c.isOpen!=2\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY a.classID"
            + "</script>")
    Page<ziyouqiandaoVo> getziyouqiandaoPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 人工签到签退页面
     * 自由签到签退
     * 已签到人数获取
     */
//    @Select("<script>" +
//            "SELECT b.classID,a.stuID as stuID,c.zidingyiStuID as zidingyiStuID ,c.stuName as stuName,COUNT(a.id) as qdNum\n" +
//            "FROM pxqiandaoqiantuitable as a \n" +
//            "LEFT JOIN pxpaiketable as b on a.paikeID=b.id\n" +
//            "LEFT JOIN pxstutable as c on a.stuID=c.id\n" +
//            "where qiandaoOrqiantui=1 and qianStyle=3 and a.stuID in(SELECT stuID from pxbuxikechengtable where id in (SELECT buxiID from pxxuanketable where paikeID=a.paikeID)) and b.classID=#{classID} " +
//            "GROUP BY a.stuID" +
//            "</script>")
    @Select("<script>" +
            "SELECT s.id stuID,s.zidingyiStuID zidingyiStuID,s.stuName as stuName," +
            "(SELECT COUNT(id) from pxqiandaoqiantuitable where stuID=s.id and qiandaoOrqiantui=1) as nums " +
            "from pxstutable as s" +
            " where id in (SELECT stuID from pxbuxikechengtable where id in((SELECT buxiID from pxstuclasstable as c WHERE c.buxiID in(SELECT id from pxbuxikechengtable WHERE stuID in(SELECT stuID from pxqiandaoqiantuitable where qiandaoOrqiantui=1 GROUP BY stuID) GROUP BY id) " +
            " and c.classID=#{classID} and s.qiyeID=#{qiyeID} )))" +
            "</script>")
    Page<ziyouqiandaostuNumVo> getziyouqiandaostuNumPage(Page page, Long classID, Long qiyeID);


    /**
     * 人工签到签退页面
     * 自由签到签退
     * 已签退人数获取
     */
//    @Select("<script>" +
//            "SELECT b.classID,a.stuID as stuID,c.zidingyiStuID as zidingyiStuID ,c.stuName as stuName,COUNT(a.id) as qtNum\n" +
//            "FROM pxqiandaoqiantuitable as a \n" +
//            "LEFT JOIN pxpaiketable as b on a.paikeID=b.id\n" +
//            "LEFT JOIN pxstutable as c on a.stuID=c.id\n" +
//            "where qiandaoOrqiantui=2 and qianStyle=3 and a.stuID in(SELECT stuID from pxbuxikechengtable where id in (SELECT buxiID from pxxuanketable where paikeID=a.paikeID)) and b.classID=#{classID} " +
//            "GROUP BY a.stuID" +
//            "</script>")
    @Select("<script>" +
            "SELECT s.id stuID,s.zidingyiStuID zidingyiStuID,s.stuName as stuName,(SELECT COUNT(id) from pxqiandaoqiantuitable where stuID=s.id and qiandaoOrqiantui=2) as nums from pxstutable as s where id in (SELECT stuID from pxbuxikechengtable where id in((SELECT buxiID from pxstuclasstable as c WHERE c.buxiID in(SELECT id from pxbuxikechengtable WHERE stuID in(SELECT stuID from pxqiandaoqiantuitable where qiandaoOrqiantui=1 GROUP BY stuID) GROUP BY id)  and c.classID=#{classID} and s.qiyeID=#{qiyeID} )))" +
            "</script>")
    Page<ziyouqiandaostuNumVo> getziyouqiantuistuNumPage(Page page, Long classID, Long qiyeID);

    /**
     * 人工签到签退页面
     * 自由签到签退
     * 已签退人数获取
     * 指定学员签到过几次
     */
    @Select("<script>" +
            "SELECT (case WHEN a.qianStyle=1 THEN '刷卡' ELSE (case WHEN a.qianStyle=2 THEN '微信' ELSE '人工' END) END) as qType,a.qianDatetime as qianDatetime,(case WHEN a.tsState=TRUE THEN '成功' ELSE '失败' END) as TStype,c.staffName as staffName " +
            "from pxqiandaoqiantuitable as a " +
            "LEFT JOIN pxstutable  as b on a.stuID= b.id " +
            "LEFT JOIN pxstafftable as c on a.addstaffID=c.id " +
            "where a.qiyeID=#{qiyeID} and qiandaoOrqiantui=1 and stuID=#{stuID} ORDER BY a.qianDatetime desc " +
            "</script>")
    Page<stuqiaoDaoNumVo> getstuqiaoDaoNumPage(Page page, Long qiyeID, Long stuID);


    /**
     * 人工签到签退页面
     * 自由签到签退
     * 已签退人数获取
     * 指定学员签退过几次
     */
    @Select("<script>" +
            "SELECT (case WHEN a.qianStyle=1 THEN '刷卡' ELSE (case WHEN a.qianStyle=2 THEN '微信' ELSE '人工' END) END) as qType,a.qianDatetime as qianDatetime,(case WHEN a.tsState=TRUE THEN '成功' ELSE '失败' END) as TStype,c.staffName as staffName " +
            "from pxqiandaoqiantuitable as a " +
            "LEFT JOIN pxstutable  as b on a.stuID= b.id " +
            "LEFT JOIN pxstafftable as c on a.addstaffID=c.id " +
            "where  a.qiyeID=#{qiyeID} and  qiandaoOrqiantui=2 and stuID=#{stuID} ORDER BY a.qianDatetime desc " +
            "</script>")
    Page<stuqiaoDaoNumVo> getstuqiaoTuiNumPage(Page page, Long qiyeID, Long stuID);
    //endregion

    //endregion

    //region 刷脸消课

    /**
     * 分页查询刷脸消课记录
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>"+"SELECT  keshi.stuID as stuID,stu.zidingyiStuID as zidingyiStuID,stu.stuName,\n" +
            "                            stu.stuGradeID,\n" +
            "                            staff.staffName,\n" +
            "                            keshi.teacherNames AS teacherName,\n" +
            "                            keshi.endLessonDateTime,\n" +
            "                           keshi.haveClassDate,\n" +
            "                            keshi.startLessonDateTime,\n" +
            "                            keshi.keshiNum,\n" +
            "                            keshi.addtime,\n" +
            "                            kecheng.kechengName,\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\tkeshi.dakaoqingStyle,\n" +
            "                            campus.campusName,\n" +
            "                            campus.id AS campusID,\n" +
            "(select staffName from pxstafftable where id=stu.banzhurenTeacherID ) AS banzhuren ," +
            "                            buxistyle.buxiStyleName FROM pxkeshistutable AS keshi \n" +
            "LEFT JOIN pxstutable AS stu ON keshi.stuID = stu.id\n" +
            "LEFT JOIN pxkechengtable AS kecheng ON keshi.kechengID = kecheng.id\n" +
            "LEFT JOIN pxbuxistyletable AS buxistyle ON kecheng.buxiStyleID = buxistyle.id\n" +
            "LEFT JOIN pxcampustable AS campus ON stu.campusID = campus.id\n" +
            "LEFT JOIN pxstafftable AS staff ON keshi.adduser = staff.id\n" +
            "WHERE campus.isOpen!=2"
            + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, Object>> GetshualianxiaokeInfoPages(Page page, @Param("ew") QueryWrapper queryWrapper);
    //endregion
}