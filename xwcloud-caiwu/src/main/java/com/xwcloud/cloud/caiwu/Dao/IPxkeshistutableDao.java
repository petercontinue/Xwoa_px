package com.xwcloud.cloud.caiwu.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.LiushilvStuVo;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
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
public interface IPxkeshistutableDao extends BaseMapper<Pxkeshistutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "StuGradeID", property = "StuGradeID"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "buxiStyleID", property = "buxiStyleID"),
            @Result(column = "classTimeStyleID", property = "classTimeStyleID"),
            @Result(column = "kechengPrice", property = "kechengPrice"),
            @Result(column = "stuKaoqingStyle", property = "stuKaoqingStyle"),
            @Result(column = "dakaoqingStyle", property = "dakaoqingStyle"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "adduser", property = "adduser"),
            @Result(column = "addtime", property = "addtime"),
            @Result(column = "zhujiao", property = "zhujiao"),
            @Result(column = "buxikechengID", property = "buxikechengID"),
            @Result(column = "banzhurenStaffID", property = "banzhurenStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshistutable"
            + "</script>")
    List<Pxkeshistutable> getAllList();

    @Select("<script>" +
            "SELECT campus.id as campusID,campus.campusName,staff.staffName,grade.stuGradeName," +
            "SUM(keshi.keshiNum) keshiNum," +
            "SUM(keshi.kechengPrice*keshi.keshiNum) kehao " +
            "FROM pxkeshistutable keshi \n" +
            "LEFT JOIN pxcampustable campus ON keshi.campusID = campus.id\n" +
            "LEFT JOIN pxstafftable staff ON find_in_set(staff.id,keshi.teacherIDs)\n" +
            "LEFT JOIN pxstugradetable grade ON keshi.StuGradeID = grade.id\n" +
            "Where 1=1 " +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY campus.id,staff.id,grade.id"
            + "</script>")
    Page<HashMap<String, Object>> getNainjiPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT SUM(tmp.kehao) sumkehao FROM(\n" +
            "SELECT campus.id as campusID,campus.campusName,staff.staffName,grade.stuGradeName,SUM(keshi.keshiNum) keshiNum,\n" +
            "            SUM(keshi.kechengPrice*keshi.keshiNum) kehao \n" +
            "            FROM pxkeshistutable keshi \n" +
            "            LEFT JOIN pxcampustable campus ON keshi.campusID = campus.id\n" +
            "            LEFT JOIN pxstafftable staff ON find_in_set(staff.id,keshi.teacherIDs)\n" +
            "            LEFT JOIN pxstugradetable grade ON keshi.StuGradeID = grade.id\n" +
            "            Where 1=1\n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "            GROUP BY campus.id,staff.id,grade.id) tmp"
            + "</script>")
    String getNainjiSum(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT  campus.id as campusID,campus.campusName,staff.staffName,classT.className,SUM(keshi.keshiNum) keshiNum,SUM(keshi.kechengPrice*keshi.keshiNum) kehao FROM pxkeshistutable keshi \n" +
            "LEFT JOIN pxcampustable campus ON keshi.campusID = campus.id\n" +
            "LEFT JOIN pxstafftable staff ON find_in_set(staff.id,keshi.teacherIDs)\n" +
            "LEFT JOIN pxstugradetable grade ON keshi.StuGradeID = grade.id\n" +
            "LEFT JOIN pxclasstable classT ON classT.id = keshi.classID\n" +
            "Where 1=1 " +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY campus.id,staff.id,classT.id"
            + "</script>")
    Page<HashMap<String, Object>> getBanjiPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT  FORMAT(SUM(tmp.kehao),2) sumkehao FROM(\n" +
            "SELECT  campus.id as campusID,campus.campusName,staff.staffName,classT.className,\n" +
            "FORMAT(SUM(keshi.keshiNum),2) keshiNum,FORMAT(SUM(keshi.kechengPrice*keshi.keshiNum),2) kehao \n" +
            "FROM pxkeshistutable keshi \n" +
            "LEFT JOIN pxcampustable campus ON keshi.campusID = campus.id\n" +
            "            LEFT JOIN pxstafftable staff ON find_in_set(staff.id,keshi.teacherIDs)\n" +
            "            LEFT JOIN pxstugradetable grade ON keshi.StuGradeID = grade.id\n" +
            "            LEFT JOIN pxclasstable classT ON classT.id = keshi.classID\n" +
            "            Where 1=1 \n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "            GROUP BY campus.id,staff.id,classT.id) tmp"
            + "</script>")
    String getBanjiSum(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT  campus.id as campusID,campus.campusName,staff.staffName,buxistyle.buxiStyleName,SUM(keshi.keshiNum) keshiNum,SUM(keshi.kechengPrice*keshi.keshiNum) kehao FROM pxkeshistutable keshi \n" +
            "LEFT JOIN pxcampustable campus ON keshi.campusID = campus.id\n" +
            "LEFT JOIN pxstafftable staff ON find_in_set(staff.id,keshi.teacherIDs)\n" +
            "LEFT JOIN pxstugradetable grade ON keshi.StuGradeID = grade.id\n" +
            "LEFT JOIN pxclasstable classT ON classT.id = keshi.classID\n" +
            "LEFT JOIN pxbuxistyletable buxistyle ON buxistyle.id = keshi.buxiStyleID\n" +
            "Where 1=1 " +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY campus.id,staff.id,buxistyle.id"
            + "</script>")
    Page<HashMap<String, Object>> getBuxiStylePage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT  FORMAT(SUM(tmp.kehao),2) sumkehao FROM(\n" +
            "SELECT  campus.id as campusID,campus.campusName,staff.staffName,buxistyle.buxiStyleName,FORMAT(SUM(keshi.keshiNum),2) keshiNum,FORMAT(SUM(keshi.kechengPrice*keshi.keshiNum),2) kehao \n" +
            "FROM pxkeshistutable keshi \n" +
            "            LEFT JOIN pxcampustable campus ON keshi.campusID = campus.id\n" +
            "            LEFT JOIN pxstafftable staff ON find_in_set(staff.id,keshi.teacherIDs)\n" +
            "            LEFT JOIN pxstugradetable grade ON keshi.StuGradeID = grade.id\n" +
            "            LEFT JOIN pxclasstable classT ON classT.id = keshi.classID\n" +
            "            LEFT JOIN pxbuxistyletable buxistyle ON buxistyle.id = keshi.buxiStyleID\n" +
            "            Where 1=1 \n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "            GROUP BY campus.id,staff.id,buxistyle.id) tmp"
            + "</script>")
    String getBuxiStyleSum(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT campus.id as campusID,campus.campusName,staff.staffName,sub.subjectName,SUM(keshi.keshiNum) keshiNum,SUM(keshi.kechengPrice*keshi.keshiNum) kehao FROM pxkeshistutable keshi \n" +
            "LEFT JOIN pxcampustable campus ON keshi.campusID = campus.id\n" +
            "LEFT JOIN pxstafftable staff ON find_in_set(staff.id,keshi.teacherIDs)\n" +
            "LEFT JOIN pxstugradetable grade ON keshi.StuGradeID = grade.id\n" +
            "LEFT JOIN pxclasstable classT ON classT.id = keshi.classID\n" +
            "LEFT JOIN pxbuxistyletable buxistyle ON buxistyle.id = keshi.buxiStyleID\n" +
            "LEFT JOIN pxkechengtable kecheng ON kecheng.id = keshi.kechengID\n" +
            "LEFT JOIN pxsubjecttable sub ON sub.id = kecheng.subjectID\n" +
            "Where 1=1 " +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY campus.id,staff.id,sub.id"
            + "</script>")
    Page<HashMap<String, Object>> getKemuPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT FORMAT(SUM(tmp.kehao),2) sumkehao FROM(\n" +
            "SELECT campus.id as campusID,campus.campusName,staff.staffName,sub.subjectName,FORMAT(SUM(keshi.keshiNum),2) keshiNum,FORMAT(SUM(keshi.kechengPrice*keshi.keshiNum),2) kehao FROM pxkeshistutable keshi \n" +
            "            LEFT JOIN pxcampustable campus ON keshi.campusID = campus.id\n" +
            "            LEFT JOIN pxstafftable staff ON find_in_set(staff.id,keshi.teacherIDs)\n" +
            "            LEFT JOIN pxstugradetable grade ON keshi.StuGradeID = grade.id\n" +
            "            LEFT JOIN pxclasstable classT ON classT.id = keshi.classID\n" +
            "            LEFT JOIN pxbuxistyletable buxistyle ON buxistyle.id = keshi.buxiStyleID\n" +
            "            LEFT JOIN pxkechengtable kecheng ON kecheng.id = keshi.kechengID\n" +
            "            LEFT JOIN pxsubjecttable sub ON sub.id = kecheng.subjectID\n" +
            "            Where 1=1 \n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "            GROUP BY campus.id,staff.id,sub.id)tmp"
            + "</script>")
    String getKemuSum(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT *,FORMAT((tmpsum.1v1kehaoNum+tmpsum.kehaoNum),2) as total FROM(\n" +
            "SELECT campus.id,campus.campusName,staff.id as staffID,staff.staffName,COUNT(tmp.stu) as stuNum,\n" +
            "            COUNT(if(tmp.is1v1KC=1,true,null)) AS 1v1kcNum,\n" +
            "            COUNT(if(tmp.is1v1KC=0,true,null)) AS bankeNum,\n" +
            "           FORMAT(SUM(case when tmp.is1v1KC = 0 then tmp.keshiNum else 0 end),2) as 1v1keshiNum,\n" +
            "            FORMAT(SUM(case when tmp.is1v1KC = 1 then tmp.keshiNum else 0 end),2) as keshiNum,\n" +
            "            FORMAT(SUM(case when tmp.is1v1KC = 0 then tmp.kechengPrice*tmp.keshiNum else 0 end),2) as 1v1kehaoNum,\n" +
            "            FORMAT(SUM(case when tmp.is1v1KC = 1 then tmp.kechengPrice*tmp.keshiNum else 0 end),2) as kehaoNum\n" +
            "            FROM \n" +
            "            (SELECT keshi.*,kecheng.kechengName,kecheng.is1v1KC,stu.id as stu,\n" +
            "            case when keshi.banzhurenStaffID IS NULL then stu.banzhurenTeacherID else keshi.banzhurenStaffID end AS staffid \n" +
            "             FROM pxkeshistutable keshi LEFT JOIN pxstutable stu ON keshi.stuID = stu.id \n" +
            "            LEFT JOIN pxkechengtable kecheng ON keshi.kechengID=kecheng.id ) tmp \n" +
            "            LEFT JOIN pxcampustable campus ON campus.id = tmp.campusID \n" +
            "            LEFT JOIN pxstafftable staff ON tmp.staffid = staff.id\n" +
            "             WHERE (tmp.staffid IS NOT NULL and tmp.staffid !=0) \n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "             GROUP BY tmp.campusID,tmp.staffid ) tmpsum"
            + "</script>")
    Page<HashMap<String, String>> getBanzhurenPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT *,(tmpsum.1v1kehaoNum+tmpsum.kehaoNum) as total FROM(\n" +
            "SELECT campus.id,campus.campusName,staff.id as staffID,staff.staffName,COUNT(tmp.stu) as stuNum,\n" +
            "            COUNT(if(tmp.is1v1KC=1,true,null)) AS 1v1kcNum,\n" +
            "            COUNT(if(tmp.is1v1KC=0,true,null)) AS bankeNum,\n" +
            "            SUM(case when tmp.is1v1KC = 0 then tmp.keshiNum else 0 end) as 1v1keshiNum,\n" +
            "            SUM(case when tmp.is1v1KC = 1 then tmp.keshiNum else 0 end) as keshiNum,\n" +
            "            SUM(case when tmp.is1v1KC = 0 then tmp.kechengPrice*tmp.keshiNum else 0 end) as 1v1kehaoNum,\n" +
            "            SUM(case when tmp.is1v1KC = 1 then tmp.kechengPrice*tmp.keshiNum else 0 end) as kehaoNum\n" +
            "            FROM \n" +
            "            (SELECT keshi.*,kecheng.kechengName,kecheng.is1v1KC,stu.id as stu,\n" +
            "            case keshi.banzhurenStaffID IS NULL when TRUE then stu.banzhurenTeacherID else keshi.banzhurenStaffID end AS staffid \n" +
            "             FROM pxkeshistutable keshi LEFT JOIN pxstutable stu ON keshi.stuID = stu.id \n" +
            "            LEFT JOIN pxkechengtable kecheng ON keshi.kechengID=kecheng.id ) tmp \n" +
            "            LEFT JOIN pxcampustable campus ON campus.id = tmp.campusID \n" +
            "            LEFT JOIN pxstafftable staff ON tmp.staffid = staff.id\n" +
            "             WHERE (tmp.staffid IS NOT NULL and tmp.staffid !=0) \n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "             GROUP BY tmp.campusID,tmp.staffid ) tmpsum"
            + "</script>")
    List<HashMap<String, Object>> getBanzhurenList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT SUM(totaltmp.total) total FROM(\n" +
            "SELECT *,(tmpsum.1v1kehaoNum+tmpsum.kehaoNum) as total FROM(\n" +
            "SELECT campus.id,campus.campusName,staff.id as staffID,staff.staffName,COUNT(tmp.stu) as stuNum,\n" +
            "            COUNT(if(tmp.is1v1KC=1,true,null)) AS 1v1kcNum,\n" +
            "            COUNT(if(tmp.is1v1KC=0,true,null)) AS bankeNum,\n" +
            "            SUM(case when tmp.is1v1KC = 0 then tmp.keshiNum else 0 end) as 1v1keshiNum,\n" +
            "            SUM(case when tmp.is1v1KC = 1 then tmp.keshiNum else 0 end) as keshiNum,\n" +
            "            SUM(case when tmp.is1v1KC = 0 then tmp.kechengPrice*tmp.keshiNum else 0 end) as 1v1kehaoNum,\n" +
            "            SUM(case when tmp.is1v1KC = 1 then tmp.kechengPrice*tmp.keshiNum else 0 end) as kehaoNum\n" +
            "            FROM \n" +
            "            (SELECT keshi.*,kecheng.kechengName,kecheng.is1v1KC,stu.id as stu,\n" +
            "            case keshi.banzhurenStaffID IS NULL when TRUE then stu.banzhurenTeacherID else keshi.banzhurenStaffID end AS staffid \n" +
            "             FROM pxkeshistutable keshi LEFT JOIN pxstutable stu ON keshi.stuID = stu.id \n" +
            "            LEFT JOIN pxkechengtable kecheng ON keshi.kechengID=kecheng.id ) tmp \n" +
            "            LEFT JOIN pxcampustable campus ON campus.id = tmp.campusID \n" +
            "            LEFT JOIN pxstafftable staff ON tmp.staffid = staff.id\n" +
            "             WHERE (tmp.staffid IS NOT NULL and tmp.staffid !=0)\n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "             GROUP BY tmp.campusID,tmp.staffid ) tmpsum) totaltmp"
            + "</script>")
    String getBanzhurenSum(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT *,( tmp.TkeshiNum / tmp.classNum ) classkehao,( tmp.keshiNum / tmp.stuNum ) stuyuejun \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tb.campusName,\n" +
            "\t\tDATE_FORMAT( a.haveClassDate, '%Y-%m' ) AS Ym,\n" +
            "\t\tcount( DISTINCT a.stuID ) AS stuNum,\n" +
            "\t\tcount( DISTINCT a.classID ) AS classNum,\n" +
            "\t\tSUM( a.keshiNum ) AS keshiNum,\n" +
            "\t\tSUM( a.kechengPrice * a.keshiNum ) AS keshishouru,\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tSUM( x.keshiNum ) \n" +
            "\t\tFROM\n" +
            "\t\t\tpxkeshiteachertable x \n" +
            "\t\tWHERE\n" +
            "\t\t\tDATE_FORMAT( x.haveClassDate, '%m' ) =(\n" +
            "\t\t\tDATE_FORMAT( a.haveClassDate, '%m' ))) TkeshiNum \n" +
            "\tFROM\n" +
            "\t\tpxkeshistutable a\n" +
            "\t\tLEFT JOIN pxcampustable b ON a.campusID = b.id\n" +
            "\t\tLEFT JOIN pxkeshistuteachertable c ON a.id = c.keshiStuTableID \n" +
            "\tWHERE\n" +
            "\t\t1 = 1 \n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "\tGROUP BY\n" +
            "\t\tb.id) tmp\n" +
            "</script>")
    Page<HashMap<String, String>> getYuejunkehaoPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT\n" +
            "( CASE WHEN SUM( a.keshiNum * a.kechengPrice ) IS NULL THEN 0 ELSE SUM( a.keshiNum * a.kechengPrice ) END ) \n" +
            "FROM\n" +
            "\tpxkeshistutable a\n" +
            "JOIN pxcampustable b ON a.campusID = b.id \n" +
            "WHERE\n" +
            "\tb.isOpen !=2 " +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    String getYuejunkehaoSum(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT *,( temp.tkeshiNum / temp.stuNum ) classyuejunkuhao \n" +
            "FROM\n" +
            "\t(\n" +
            "\tSELECT\n" +
            "\t\tDATE_FORMAT( a.haveClassDate, '%Y-%m' ) AS Ym,\n" +
            "\t\tb.teacherID,\n" +
            "\t\tc.staffName,\n" +
            "\t\ta.kechengPrice,\n" +
            "\t\ta.keshiNum,\n" +
            "\t\ta.stuID,\n" +
            "\t\tc.campusID,\n" +
            "\t\td.campusName,\n" +
            "\t\ta.classID,\n" +
            "\t\tcount( DISTINCT a.stuID ) AS stuNum,\n" +
            "\t\tcount( DISTINCT a.classID ) AS classNum,\n" +
            "\t\t(\n" +
            "\t\tSELECT\n" +
            "\t\t\tSUM( x.keshiNum ) \n" +
            "\t\tFROM\n" +
            "\t\t\tpxkeshiteachertable x \n" +
            "\t\tWHERE\n" +
            "\t\t\tDATE_FORMAT( x.haveClassDate, '%m' ) =(\n" +
            "\t\t\tDATE_FORMAT( a.haveClassDate, '%m' )) \n" +
            "\t\t\tAND x.teacherID = b.teacherID \n" +
            "\t\t) tkeshiNum,\n" +
            "\t\tsum( a.kechengPrice * a.keshiNum ) keshishouru \n" +
            "\tFROM\n" +
            "\t\tpxkeshistutable a\n" +
            "\t\tJOIN pxkeshistuteachertable b ON a.id = b.keshiStuTableID\n" +
            "\t\tJOIN pxstafftable c ON b.teacherID = c.id\n" +
            "\t\tJOIN pxcampustable d ON c.campusID = d.id \n" +
            "\tWHERE\n" +
            "\t\td.isOpen != 2 \n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "\tGROUP BY\n" +
            "\t\tb.teacherID,\n" +
            "\tc.campusID \n" +
            "\t) temp"
            + "</script>")
    Page<HashMap<String, Object>> getJiaoshiyuejunkehaoPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT SUM(tmp.keshishouru) keshiSum FROM(\n" +
            "SELECT campus.campusName,staff.staffName,DATE_FORMAT(tkeshi.haveClassDate,'%Y-%m') as Ym,\n" +
            "            COUNT(DISTINCT tkeshi.classID) as classNum,\n" +
            "            COUNT(DISTINCT tkeshi.stuID) as stuNum,\n" +
            "            SUM(keshi.keshiNum) as keshiNum,\n" +
            "            SUM(tkeshi.keshiNum) as tkeshiNum,\n" +
            "\t\t\t\t\t\t(SUM(tkeshi.keshiNum)/COUNT(DISTINCT tkeshi.classID)) classyuejunkuhao,\n" +
            "\t\t\t\t\t\t(SUM(keshi.kechengPrice*keshi.keshiNum)) as keshishouru\n" +
            "            FROM pxkeshiteachertable tkeshi \n" +
            "            LEFT JOIN pxkeshistuteachertable keshistuteacher ON tkeshi.teacherID = keshistuteacher.teacherID\n" +
            "            LEFT JOIN pxkeshistutable keshi ON keshi.id = keshistuteacher.keshiStuTableID\n" +
            "            LEFT JOIN pxstafftable staff ON tkeshi.teacherID = staff.id\n" +
            "            LEFT JOIN pxcampustable campus ON campus.id = staff.campusID\n" +
            "            Where 1=1 \n" +
            "<if test='ew != null '>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "            GROUP BY campus.id,staff.id,DATE_FORMAT(tkeshi.haveClassDate,'%Y-%m')) tmp"
            + "</script>")
    String getJiaoshiyuejunkehaoSum(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT \n" +
            "tmp.tyear,\n" +
            "SUM(tmp.Jan) AS JAN,\n" +
            "SUM(tmp.Feb) AS FEB,\n" +
            "SUM(tmp.Mar) AS MAR,\n" +
            "SUM(tmp.Apr) AS APR,\n" +
            "SUM(tmp.May) AS MAY,\n" +
            "SUM(tmp.Jun) AS JUN,\n" +
            "SUM(tmp.Jul) AS JUL,\n" +
            "SUM(tmp.Aug) AS AUG,\n" +
            "SUM(tmp.Sep) AS SEP,\n" +
            "SUM(tmp.Oct) AS OCT,\n" +
            "SUM(tmp.Nov) AS NOV,\n" +
            "SUM(tmp.Dece) AS DECE,\n" +
            "tmp.campusID,\n" +
            "campus.campusName\n" +
            "FROM \n" +
            "(\n" +
            "SELECT YEAR(haveClassDate) as tyear,\n" +
            "case MONTH(haveClassDate) when '1' then SUM(kechengPrice*keshiNum) else 0 end AS Jan ,\n" +
            "case MONTH(haveClassDate) when '2' then SUM(kechengPrice*keshiNum) else 0 end AS Feb,\n" +
            "case MONTH(haveClassDate) when '3' then SUM(kechengPrice*keshiNum) else 0 end as Mar,\n" +
            "case MONTH(haveClassDate) when '4' then SUM(kechengPrice*keshiNum) else 0 end as Apr,\n" +
            "case MONTH(haveClassDate) when '5' then SUM(kechengPrice*keshiNum) else 0 end as May ,\n" +
            "case MONTH(haveClassDate) when '6' then SUM(kechengPrice*keshiNum) else 0 end as Jun ,\n" +
            "case MONTH(haveClassDate) when '7' then SUM(kechengPrice*keshiNum) else 0 end as Jul,\n" +
            "case MONTH(haveClassDate) when '8' then SUM(kechengPrice*keshiNum) else 0 end as Aug,\n" +
            "case MONTH(haveClassDate) when '9' then SUM(kechengPrice*keshiNum) else 0 end as Sep,\n" +
            "case MONTH(haveClassDate) when '10' then SUM(kechengPrice*keshiNum) else 0 end as Oct,\n" +
            "case MONTH(haveClassDate) when '11' then SUM(kechengPrice*keshiNum) else 0 end as Nov,\n" +
            "case MONTH(haveClassDate) when '12' then SUM(kechengPrice*keshiNum) else 0 end as Dece,\n" +
            "campusID\t\n" +
            "FROM pxkeshistutable \n" +
            "WHERE 1=1  " +
            "<if test=\"qiyeID != null and qiyeID!='' \">" +
            " AND qiyeID = #{qiyeID} " +
            "</if>" +
            "GROUP BY YEAR(haveClassDate),MONTH(haveClassDate),campusID ORDER BY haveClassDate DESC) as tmp \n" +
            "LEFT JOIN pxcampustable campus ON tmp.campusID = campus.id\n" +
            "WHERE 1=1 \n" +
            "<if test=\"campusID != null and campusID != '' \">" +
            " AND tmp.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"startYear != null and startYear!='' and endYear != null and endYear!='' \" >" +
            "<![CDATA[" +
            " AND (tmp.tyear = #{startYear} or tmp.tyear = #{endYear}) " +
            "]]>" +
            "</if>" +
//            "<if test=\"endYear != null and endYear!='' \" >" +
//            "<![CDATA[" +
//            " AND tmp.tyear <= #{endYear} " +
//            "]]>" +
//            "</if>" +
            "GROUP BY tmp.tyear\n" +
            "<if test=\"campusID != null and campusID != '' \">" +
            ",tmp.campusID" +
            "</if>" +
            "ORDER BY tmp.tyear DESC"
            + "</script>")
    List<HashMap<String, String>> getKehaotongbihuanbi(@Param("campusID") long campusID,
                                                       @Param("startYear") Integer startYear,
                                                       @Param("endYear") Integer endYear,
                                                       @Param("qiyeID") long qiyeID);

    /**
     * 分页获取学员人数
     * @param page
     * @return
     */
    @Select("<script>" +
            "select a.id,b.stuGradeName,a.stuGradeID,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID in(2,3) and stuGradeID=b.id) zaiting,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID =2 and stuGradeID=b.id) zaidu,\n" +
            "(SELECT COUNT(DISTINCT id) from pxkeshistutable where stuGradeID=b.id\n" +
            "and YEAR(haveClassDate)=YEAR((SELECT now())) and MONTH(haveClassDate)=MONTH((select now())) " +
            "<if test='ew2 != null'>" +
            " AND ${ew2.SqlSegment}" +
            "</if>" +
            " ) huoyue,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID =3 and stuGradeID=b.id and (pxstutable.tingkeTime) &gt;= (SELECT DATE_ADD(NOW(), INTERVAL - 2 MONTH)) ) tingke1,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID =3 and stuGradeID=b.id and (pxstutable.tingkeTime) &lt; (SELECT DATE_ADD(NOW(), INTERVAL - 2 MONTH)) and (pxstutable.tingkeTime) &gt;= (SELECT DATE_ADD(NOW(), INTERVAL - 5 MONTH)) ) tingke2,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID =3 and stuGradeID=b.id and (pxstutable.tingkeTime) &lt; (SELECT DATE_ADD(NOW(), INTERVAL - 6 MONTH))) tingke3\n" +
            "from pxstutable a\n" +
            "join pxstugradetable b on a.StuGradeID=b.id\n" +
            "join pxcampustable c on a.campusID=c.id\n" +
            "where (a.buxiStateID!=1 and a.buxiStateID!=7) and c.isOpen!=2\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY b.id "
            + "</script>")
    Page<HashMap<String, Object>> getXuesherenshu(Page page, @Param("ew") QueryWrapper queryWrapper,@Param("ew2") QueryWrapper queryWrapper2);

    /**
     * 导出学员人数
     * @return
     */
    @Select("<script>" +
            "select a.id,b.stuGradeName,a.stuGradeID,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID in(2,3) and stuGradeID=b.id) zaiting,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID =2 and stuGradeID=b.id) zaidu,\n" +
            "(SELECT COUNT(DISTINCT id) from pxkeshistutable where stuGradeID=b.id\n" +
            "and YEAR(haveClassDate)=YEAR((SELECT now())) and MONTH(haveClassDate)=MONTH((select now())) " +
            "<if test='ew2 != null'>" +
            " AND ${ew2.SqlSegment}" +
            "</if>" +
            " ) huoyue,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID =3 and stuGradeID=b.id and (pxstutable.tingkeTime) &gt;= (SELECT DATE_ADD(NOW(), INTERVAL - 2 MONTH)) ) tingke1,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID =3 and stuGradeID=b.id and (pxstutable.tingkeTime) &lt; (SELECT DATE_ADD(NOW(), INTERVAL - 2 MONTH)) and (pxstutable.tingkeTime) &gt;= (SELECT DATE_ADD(NOW(), INTERVAL - 5 MONTH)) ) tingke2,\n" +
            "(SELECT COUNT(id) from pxstutable where buxiStateID =3 and stuGradeID=b.id and (pxstutable.tingkeTime) &lt; (SELECT DATE_ADD(NOW(), INTERVAL - 6 MONTH))) tingke3\n" +
            "from pxstutable a\n" +
            "join pxstugradetable b on a.StuGradeID=b.id\n" +
            "join pxcampustable c on a.campusID=c.id\n" +
            "where (a.buxiStateID!=1 and a.buxiStateID!=7) and c.isOpen!=2\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY b.id "
            + "</script>")
    List<HashMap<String,Object>> exportxueyuanrenshu( @Param("ew") QueryWrapper queryWrapper,@Param("ew2") QueryWrapper queryWrapper2);


    @Select("<script>" +
            "SELECT  campus.id,campus.campusName, paikeT.teacherID,staff.staffName, COUNT(DISTINCT buxikecheng.stuID) as stuNum \n" +
            "FROM pxpaiketeachertable paikeT \n" +
            "LEFT JOIN pxpaiketable paike ON paikeT.paikeID = paike.id \n" +
            "LEFT JOIN pxstuclasstable stuclass ON paike.classID = stuclass.classID\n" +
            "LEFT JOIN pxbuxikechengtable buxikecheng ON stuclass.buxiID = buxikecheng.id\n" +
            "LEFT JOIN pxstafftable staff ON paikeT.teacherID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON campus.id= staff.campusID\n" +
            "WHERE 1=1 \n" +
            "<if test=\"campusID != null and campusID!='' \">" +
            " AND campus.id = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and qiyeID!='' \">" +
            " AND paikeT.qiyeID = #{qiyeID} " +
            "</if>" +
            "<if test=\"teacherName != null and teacherName!='' \">" +
            "<![CDATA[" +
            " AND staff.staffName like '%${teacherName}%' " +
            "]]>" +
            "</if>" +
            "GROUP BY paikeT.teacherID,campus.id"
            + "</script>")
    Page<HashMap<String, String>> getTeacherStu(Page page, @Param("campusID") long campusID, @Param("teacherName") String teacherName, @Param("qiyeID") long qiyeID);

    @Select("<script>" +
            "SELECT  DISTINCT(stu.id),campus.campusName,stu.zidingyiStuID,stu.stuName,grade.stuGradeName,class.className,kecheng.kechengName\n" +
            "FROM pxpaiketeachertable paikeT \n" +
            "LEFT JOIN pxpaiketable paike ON paikeT.paikeID = paike.id \n" +
            "LEFT JOIN pxstuclasstable stuclass ON paike.classID = stuclass.classID\n" +
            "LEFT JOIN pxbuxikechengtable buxikecheng ON stuclass.buxiID = buxikecheng.id\n" +
            "LEFT JOIN pxstafftable staff ON paikeT.teacherID = staff.id\n" +
            "LEFT JOIN pxcampustable campus ON campus.id= staff.campusID\n" +
            "LEFT JOIN pxstutable stu ON stu.id = buxikecheng.stuID\n" +
            "LEFT JOIN pxstugradetable grade ON stu.stuGradeID=grade.id\n" +
            "LEFT JOIN pxclasstable class ON stuclass.classID=class.id\n" +
            "LEFT JOIN pxkechengtable kecheng ON buxikecheng.kechengID=kecheng.id " +
            "WHERE 1=1 AND stu.id IS NOT NULL \n" +
            "<if test=\"campusID != null and campusID!='' \" >" +
            "<![CDATA[" +
            " AND campus.id = #{campusID} " +
            "]]>" +
            "</if>" +
            "<if test=\"qiyeID != null and qiyeID!='' \" >" +
            "<![CDATA[" +
            " AND paikeT.qiyeID = #{qiyeID} " +
            "]]>" +
            "</if>" +
            "<if test=\"teacherID != null and teacherID!='' \" >" +
            "<![CDATA[" +
            " AND staff.id = #{teacherID} " +
            "]]>" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getTeacherStuDetaile(Page page, @Param("campusID") long campusID, @Param("teacherID") long teacherID, @Param("qiyeID") long qiyeID);

    //    @Select("<script>" +
//            "SELECT campus.id,campus.campusName,staff.staffName,COUNT(DISTINCT stu.id) stuNum\n" +
//            "FROM pxstutable stu \n" +
//            "LEFT JOIN pxstafftable staff ON stu.banzhurenTeacherID = staff.id \n" +
//            "LEFT JOIN pxcampustable campus ON campus.id=stu.campusID\n" +
//            "WHERE stu.banzhurenTeacherID is NOT NULL\n" +
//            "<if test=\"campusID != null and campusID!='' \" >" +
//            "<![CDATA[" +
//            " AND campus.id = #{campusID} " +
//            "]]>" +
//            "</if>" +
//            "<if test=\"teacherName != null and teacherName!='' \" >" +
//            "<![CDATA[" +
//            " AND staff.staffName like '%${teacherName}%' " +
//            "]]>" +
//            "</if>" +
//            "<if test=\"qiyeID != null and qiyeID!='' \" >" +
//            "<![CDATA[" +
//            " AND stu.qiyeID = #{qiyeID} " +
//            "]]>" +
//            "</if>" +
//            "GROUP BY campus.id,stu.banzhurenTeacherID "
//            + "</script>")
    @Select("<script>" +
            "SELECT a.id staffID,a.campusID,a.staffName,b.campusName,\n" +
            "(SELECT COUNT(*) FROM pxstutable aa where aa.banzhurenTeacherID=a.id AND aa.buxiStateID NOT IN (4,5)) stuNum\n" +
            "FROM pxstafftable a\n" +
            "JOIN pxcampustable b ON a.campusID=b.id\n" +
            "WHERE b.isOpen!=2 \n" +
            "<if test=\"campusID != null and campusID!='' \" >" +
            " AND b.id = #{campusID} " +
            "</if>" +
            "<if test=\"teacherName != null and teacherName!='' \" >" +
            " AND a.staffName like concat('%',#{teacherName},'%') " +
            "</if>" +
            "<if test=\"qiyeID != null and qiyeID!='' \" >" +
            " AND a.qiyeID = #{qiyeID} " +
            "</if>" +
            "GROUP BY a.id \n" +
            "HAVING stuNum>0" +
            "</script>")
    Page<HashMap<String, String>> getBanzhurenStu(Page page, @Param("campusID") long campusID, @Param("teacherName") String teacherName, @Param("qiyeID") long qiyeID);

    @Select("<script>" +
            "SELECT campus.id,campus.campusName,staff.staffName,stu.zidingyiStuID,stu.stuName,grade.stuGradeName\n" +
            "FROM pxstutable stu \n" +
            "LEFT JOIN pxstafftable staff ON stu.banzhurenTeacherID = staff.id \n" +
            "LEFT JOIN pxcampustable campus ON campus.id=stu.campusID\n" +
            "LEFT JOIN pxstugradetable grade ON grade.id = stu.stuGradeID " +
            "WHERE stu.buxiStateID NOT IN (4,5) \n" +
            "<if test=\"campusID != null and campusID!='' \" >" +
            "<![CDATA[" +
            " AND campus.id = #{campusID} " +
            "]]>" +
            "</if>" +
            "<if test=\"teacherID != null and teacherID!='' \" >" +
            "<![CDATA[" +
            " AND staff.id = #{teacherID} " +
            "]]>" +
            "</if>" +
            "<if test=\"qiyeID != null and qiyeID!='' \" >" +
            "<![CDATA[" +
            " AND stu.qiyeID = #{qiyeID} " +
            "]]>" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getBanzhurenStuDetaile(Page page, @Param("campusID") long campusID, @Param("teacherID") long teacherID, @Param("qiyeID") long qiyeID);

    @Select("<script>" +
//            "SELECT\n" +
//            "\tDISTINCT a.*,\n" +
//            "\t(SELECT COUNT(*) FROM \n" +
//            "\t(SELECT a.stuID, a.teacherIDs, a.haveClassDate FROM pxkeshistutable a WHERE a.teacherIDs IS NOT NULL ) b \n" +
//            "\tWHERE FIND_IN_SET(a.staffID, b.teacherIDs) AND b.haveClassDate > NOW() GROUP BY b.stuID) zaistunum,\n" +
//            "\t(SELECT COUNT(*) FROM\n" +
//            "\t(SELECT a.stuID, a.teacherIDs, a.haveClassDate FROM pxkeshistutable a WHERE a.teacherIDs IS NOT NULL ) b \n" +
//            "\tWHERE FIND_IN_SET(a.staffID, b.teacherIDs) GROUP BY b.stuID ) allstunum \n" +
//            "FROM\n" +
//            "\t(SELECT a.id staffID,a.staffName,b.id campusID,b.campusName,a.qiyeID FROM pxstafftable a JOIN pxcampustable b ON a.campusID = b.id and b.isOpen != 2) a,\n" +
//            "\t(SELECT a.stuID, a.teacherIDs, a.haveClassDate FROM pxkeshistutable a WHERE a.teacherIDs IS NOT NULL ) b" +

            "SELECT \n" +
            "a.id staffID,\n" +
            "a.staffName staffName,\n" +
            "b.campusName,\n" +
            "a.campusID,\n" +
            "(SELECT \n" +
            "\t\tCOUNT(DISTINCT c.stuID )  from pxkeshistutable c  where c.teacherIDs is not null and c.qiyeID=a.qiyeID and  FIND_IN_SET(a.id,c.teacherIDs) " +
            "and c.haveClassDate >=(\n" +
            "\t\tDATE_FORMAT( DATE_ADD( NOW(), INTERVAL - 1 MONTH ), '%Y-%m-%d' )) " +
            "<if test='ew2 != null'>" +
            " AND ${ew2.SqlSegment}" +
            "</if>" +
            " ) zaistunum,\n" +
            "(SELECT DISTINCT\n" +
            "\t\tCOUNT(DISTINCT c.stuID )  from pxkeshistutable c  where c.teacherIDs is not null and c.qiyeID=a.qiyeID and  FIND_IN_SET(a.id,c.teacherIDs)" +
            "<if test='ew2 != null'>" +
            " AND ${ew2.SqlSegment}" +
            "</if>" +
            ") " +
            "allstunum\n" +
            "FROM pxstafftable a \n" +
            "LEFT JOIN pxcampustable  b on a.campusID=b.id\n" +
            "where b.isOpen!=2 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<LiushilvStuVo> getTeaStuLiushiPage(Page page, @Param("ew") Wrapper wrapper,@Param("ew2") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT\n" +
            "\tDISTINCT a.*,\n" +
            "\t(SELECT COUNT(*) FROM \n" +
            "\t(SELECT a.stuID, b.banzhurenTeacherID, a.haveClassDate FROM pxkeshistutable a JOIN pxstutable b ON a.stuID=b.id WHERE b.banzhurenTeacherID IS NOT NULL ) b \n" +
            "\tWHERE a.staffID=b.banzhurenTeacherID AND b.haveClassDate > NOW() GROUP BY b.stuID) zaistunum,\n" +
            "\t(SELECT COUNT(*) FROM\n" +
            "\t(SELECT a.stuID, b.banzhurenTeacherID, a.haveClassDate FROM pxkeshistutable a JOIN pxstutable b ON a.stuID=b.id WHERE b.banzhurenTeacherID IS NOT NULL ) b \n" +
            "\tWHERE a.staffID=b.banzhurenTeacherID GROUP BY b.stuID ) allstunum \n" +
            "FROM\n" +
            "\t(SELECT a.id staffID,a.staffName,b.id campusID,b.campusName,a.qiyeID FROM pxstafftable a JOIN pxcampustable b ON a.campusID = b.id and b.isOpen != 2) a,\n" +
            "\t(SELECT a.stuID, b.banzhurenTeacherID, a.haveClassDate FROM pxkeshistutable a JOIN pxstutable b ON a.stuID=b.id WHERE b.banzhurenTeacherID IS NOT NULL ) b" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<LiushilvStuVo> getBanzhurenStuLiushiPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
//            "SELECT DISTINCT a.*,\n" +
//            "\t(SELECT COUNT(*) FROM (SELECT a.stuID,a.haveClassDate,a.campusID FROM pxkeshistutable a) b \n" +
//            "\tWHERE a.campusID=b.campusID AND b.haveClassDate > NOW() GROUP BY b.stuID)zaistunum,\n" +
//            "\t(SELECT COUNT(*) FROM (SELECT a.stuID,a.haveClassDate,a.campusID FROM pxkeshistutable a) b \n" +
//            "\tWHERE a.campusID=b.campusID GROUP BY b.stuID)allstunum\n" +
//            "FROM (SELECT a.id campusID,a.campusName,a.qiyeID FROM pxcampustable a WHERE a.isOpen != 2) a,\n" +
//            "\t(SELECT a.stuID,a.haveClassDate,a.campusID FROM pxkeshistutable a) b" +

            "SELECT\n" +
            "\ta.id campusID,\n" +
            "\ta.campusName campusName,\n" +
            "\t(\n" +
            "\tSELECT DISTINCT\n" +
            "\t\tCOUNT( c.stuID ) \n" +
            "\tFROM\n" +
            "\t\tpxkeshistutable c \n" +
            "\tWHERE\n" +
            "\t\t(\n" +
            "\t\t\t(\n" +
            "\t\t\t\t#{yue} = 1 \n" +
            "\t\t\tAND DATE_FORMAT( c.addtime, '%Y-%m' )= DATE_FORMAT( NOW(), '%Y-%m' )) \n" +
            "\t\t\tOR (\n" +
            "\t\t\t\t#{yue} = 2 \n" +
            "\t\t\tAND DATE_FORMAT( c.addtime, '%Y-%m' )= DATE_FORMAT( DATE_ADD( NOW(), INTERVAL + 1 MONTH ), '%Y-%m' )) \n" +
            "\t\t\tOR ( #{yue} = 0 ) \n" +
            "\t\t) \n" +
            "\t\tAND c.qiyeID = a.qiyeID \n" +
            "\t\tAND c.campusID = a.id \n" +
            "<if test='ew2 != null'>" +
            " AND ${ew2.SqlSegment}" +
            "</if>" +
            "\t) allstunum,\n" +
            "\t(\n" +
            "\tSELECT DISTINCT\n" +
            "\t\tCOUNT( c.stuID ) \n" +
            "\tFROM\n" +
            "\t\tpxkeshistutable c \n" +
            "\tWHERE\n" +
            "\t\t(\n" +
            "\t\t\t(\n" +
            "\t\t\t\t #{yue} = 1 \n" +
            "\t\t\tAND DATE_FORMAT( c.addtime, '%Y-%m' )= DATE_FORMAT( NOW(), '%Y-%m' )) \n" +
            "\t\t\tOR (\n" +
            "\t\t\t\t #{yue} = 2 \n" +
            "\t\t\tAND DATE_FORMAT( c.addtime, '%Y-%m' )= DATE_FORMAT( DATE_ADD( NOW(), INTERVAL + 1 MONTH ), '%Y-%m' )) \n" +
            "\t\t\tOR ( #{yue} = 0 ) \n" +
            "\t\t) \n" +
            "\t\tAND c.qiyeID = a.qiyeID \n" +
            "\t\tAND c.campusID = a.id \n" +
            "\t\tAND c.haveClassDate &gt;=(\n" +
            "\t\tDATE_FORMAT( DATE_ADD( NOW(), INTERVAL - 1 MONTH ), '%Y-%m-%d' )) \n" +
            "<if test='ew2 != null'>" +
            " AND ${ew2.SqlSegment}" +
            "</if>" +
            "\t) zaistunum \n" +
            "FROM\n" +
            "\tpxcampustable a \n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<LiushilvStuVo> getCampusStuLiushiPage(Page page, @Param("ew") Wrapper wrapper,int yue,@Param("ew2") QueryWrapper queryWrapper);


    @Select("<script>" +
            "SELECT price.className,price.campusName,DATE_FORMAT(MAX(price.haveClassDate),'%Y-%m-%d') endDate,SUM(price.kechengPrice) priceSum,price.campusID,price.classID,price.qiyeID\n" +
            "FROM \n" +
            "(SELECT a.campusID,a.classID,a.kechengPrice,a.haveClassDate,a.qiyeID,b.className,c.campusName\n" +
            "FROM pxkeshistutable a\n" +
            "JOIN pxclasstable b ON a.classID=b.id\n" +
            "JOIN pxcampustable c ON a.campusID=c.id\n" +
            "where c.isOpen != 2) price\n" +
            "GROUP BY price.campusID,price.classID,price.className,price.campusName" +
            "<if test='ew != null'>" +
            " HAVING ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<HashMap<String, Object>> getClassProfit(Page page, @Param("ew") Wrapper wrapper);
}