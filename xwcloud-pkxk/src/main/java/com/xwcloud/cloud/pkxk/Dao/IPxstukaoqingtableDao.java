package com.xwcloud.cloud.pkxk.Dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.KaoqingCountVo;
import com.xwcloud.cloud.model.Vo.KaoqingliushuiVo;
import com.xwcloud.cloud.model.Vo.nokaoqingStuVo;
import com.xwcloud.cloud.model.entity.Pxstukaoqingtable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Repository
public interface IPxstukaoqingtableDao extends BaseMapper<Pxstukaoqingtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "haveclassDate", property = "haveclassDate"),
            @Result(column = "startClassDateTime", property = "startClassDateTime"),
            @Result(column = "endClassDateTime", property = "endClassDateTime"),
            @Result(column = "kaoqingStyle", property = "kaoqingStyle"),
            @Result(column = "kaoqingBeizhu", property = "kaoqingBeizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstukaoqingtable"
            + "</script>")
    List<Pxstukaoqingtable> getAllList();

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxstukaoqingtable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxstukaoqingtable> selectstukaoqing(@Param("ew") QueryWrapper queryWrapper);


    /**
     * 缺勤学员
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT b.stuName stuName,b.stuSex,b.id stuID,b.zidingyiStuID zidingyiStuID,a.kaoqingStyle kaoqingStyle,c.stuGradeName stuGradeName \n" +
            "from pxstukaoqingtable a\n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxstugradetable c on b.stuGradeID=c.id\n" +
            "LEFT JOIN pxcampustable d on b.campusID= d.id " +
            "WHERE (a.kaoqingStyle=2 or a.kaoqingStyle=3) and d.isOpen != 2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<nokaoqingStuVo> NokaoqingstuPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 考勤统计
     */
    @Select("<script>" +
            "select a.id as id,c.campusName as campusName,a.stuID as stuID,b.zidingyiStuID as zidingyiStuID, b.stuName as stuName,d.staffName as banzhuren, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID ) as allN, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID and kaoqingStyle=2) as qingjia, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID and kaoqingStyle=3) as kuangke, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID and kaoqingStyle=4) as chidao, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID and kaoqingStyle=1 ) as zhengchang  " +
            "from pxstukaoqingtable as a " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxcampustable as c on b.campusID= c.id " +
            "LEFT JOIN pxstafftable as d on b.banzhurenTeacherID=d.id " +
            " WHERE  c.isOpen != 2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY stuID" +
            "</script>")
    Page<KaoqingCountVo> getKaoqingCountPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /***
     * 导出考勤统计
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select a.id as id,c.campusName as campusName," +
            "(case WHEN b.zidingyiStuID is NULL THEN a.stuID ELSE b.zidingyiStuID END)as stuID,b.stuName as stuName,d.staffName as banzhuren, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID ) as allN, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID and kaoqingStyle=2) as qingjia, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID and kaoqingStyle=3) as kuangke, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID and kaoqingStyle=4) as chidao, " +
            "(SELECT COUNT(id) from pxstukaoqingtable where stuID=a.stuID and kaoqingStyle=1 ) as zhengchang  " +
            "from pxstukaoqingtable as a " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxcampustable as c on b.campusID= c.id " +
            "LEFT JOIN pxstafftable as d on b.banzhurenTeacherID=d.id " +
            " WHERE 1=1 and c.isOpen != 2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY stuID" +
            "</script>")
    List<KaoqingCountVo> ExportKaoqingCount(@Param("ew") QueryWrapper queryWrapper);


    /**
     * 考勤流水
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select a.id as id,f.campusName as campusName,e.zidingyiStuID as zidingyiStuID,a.stuID as stuID,e.stuName as stuName,(SELECT staffName from pxstafftable where id =e.banzhurenTeacherID) as banzhuren, " +
            "d.kechengName as kechengName,c.staffName as tearch,a.haveclassDate as haveclassDate,a.startClassDateTime as startClassDateTime,a.endClassDateTime as endClassDateTime,a.kaoqingStyle as kaoqingStyle  " +
            "from pxstukaoqingtable as a " +
            "LEFT JOIN pxstukaoqingteachertable as b on a.id=b.stukaoqingTabID " +
            "LEFT JOIN pxstafftable as c on b.teacherID=c.id " +
            "LEFT JOIN pxkechengtable as d on a.kechengID=d.id " +
            "LEFT JOIN pxstutable as e on a.stuID=e.id " +
            "LEFT JOIN pxcampustable as f on e.campusID=f.id" +
            " WHERE 1=1 and f.isOpen != 2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<KaoqingliushuiVo> getKaoqingliushuiPage(Page page, @Param("ew") QueryWrapper queryWrapper);


    @Select("<script>" +
            "select a.id as id,(case WHEN e.zidingyiStuID is NULL THEN a.stuID ELSE e.zidingyiStuID END) as stuID,f.campusName campusName, \n" +
            "e.stuName as stuName,(SELECT staffName from pxstafftable where id = e.banzhurenTeacherID) as banzhuren,d.kechengName as kechengName,\n" +
            "c.staffName as Tearch,a.haveclassDate as haveclassDate,a.startClassDateTime as startClassDateTime,a.endClassDateTime as endClassDateTime,\n" +
            "(case WHEN a.kaoqingStyle=1 THEN '正常' ELSE (case WHEN a.kaoqingStyle=2 THEN '请假' ELSE (case WHEN a.kaoqingStyle=3 THEN '旷课' ELSE (case WHEN a.kaoqingStyle=4 THEN '迟到' ELSE (case WHEN a.kaoqingStyle=5 THEN '早退' ELSE '补课' END) END) END) END) END) as kaoqingStyle \n" +
            "from pxstukaoqingtable as a \n" +
            "LEFT JOIN pxstukaoqingteachertable as b on a.id=b.stukaoqingTabID\n" +
            "LEFT JOIN pxstafftable as c on b.teacherID=c.id\n" +
            "LEFT JOIN pxkechengtable as d on a.kechengID=d.id\n" +
            "LEFT JOIN pxstutable as e on a.stuID=e.id\n" +
            "LEFT JOIN pxcampustable as f on e.campusID=f.id " +
            " WHERE 1=1 and f.isOpen != 2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<KaoqingliushuiVo> ExportKaoqingliushui(@Param("ew") QueryWrapper queryWrapper);
}