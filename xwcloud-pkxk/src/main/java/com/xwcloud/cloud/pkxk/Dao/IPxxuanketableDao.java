package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.paikexiaoekestuVO;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
import com.xwcloud.cloud.model.entity.Pxxuanketable;

import com.xwcloud.cloud.model.Vo.teacherIDVo;
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
 * @since 2020-11-16
 */
@Repository
public interface IPxxuanketableDao extends BaseMapper<Pxxuanketable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "paikeID", property = "paikeID"),
            @Result(column = "recordDate", property = "recordDate"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "type", property = "type"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxxuanketable"
            + "</script>")
    List<Pxxuanketable> getAllList();


    @Select("<script>" +
            "SELECT GROUP_CONCAT(stuID) from pxxuanketable where paikeID=#{paikeID} "
            + "</script>")
    List<teacherIDVo> gexkStuBypkID(Long paikeID);

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxxuanketable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxxuanketable> selectxuanke(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 按排课消课，把班级学生及考勤返回
     *
     * @param
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT  a.stuID id,c.stuName stuName,\n" +
            "(case WHEN d.remainkeshi>(SELECT defaultValue from pxsysparamdefaulttable WHERE id=11) THEN '' ELSE  concat('低课时',':', d.remainkeshi) END)  yujing,\n" +
            "(SELECT  (case WHEN kaoqingStyle is null THEN 0 ELSE kaoqingStyle END)\n" +
            "FROM pxstukaoqingtable w WHERE  w.classID=b.classID and w.haveclassDate=b.haveclassDate and w.startClassDateTime=b.startLessonDateTime \n" +
            "and w.endClassDateTime=b.endLessonDateTime and w.stuID=a.stuID and w.teacherIDs=b.teacherIDs limit 1) kaoqingStyle,\n" +
            "d.isShow isShow ,d.remainkeshi remainkeshi,f.classTimeStyleName classTimeStyleName\n" +
            "from pxxuanketable a \n" +
            "LEFT JOIN pxpaiketable b on a.paikeID=b.id\n" +
            "LEFT JOIN pxstutable c on a.stuID=c.id\n" +
            "LEFT JOIN pxbuxikechengtable d on a.buxiID=d.id\n" +
            "LEFT JOIN pxkechengtable e on d.kechengID=e.id\n" +
            "LEFT JOIN pxclasstimestyletable f on e.classTimeStyleID=f.id\n" +
            "WHERE 1=1 and c.id=d.stuID and (c.buxiStateID=1 or c.buxiStateID=2) \n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<paikexiaoekestuVO> getpaikexiaoekestuList(@Param("ew") QueryWrapper queryWrapper);


    //获取排课没课耗学员
    @Select("<script>" +
            "select b.id,b.haveClassDate,b.startLessonDateTime,b.endLessonDateTime,b.classID,\n" +
            "(SELECT count(id) from pxkeshistutable where haveClassDate=b.haveClassDate and startLessonDateTime=b.startLessonDateTime and endLessonDateTime =b.endLessonDateTime and classID=b.classID and\n" +
            "buxikechengID=a.buxiID ) keshi\n" +
            "from pxxuanketable a\n" +
            "LEFT JOIN pxpaiketable b on a.paikeID=b.id\n" +
            "LEFT JOIN pxbuxikechengtable c on a.buxiID=c.id\n" +
            "where c.kechengID=#{kechengID} and c.stuID=#{stuID} and a.qiyeID=#{qiyeID} and b.dakaoqin is null and \n" +
            " (SELECT count(id) from pxkeshistutable where haveClassDate=b.haveClassDate and startLessonDateTime=b.startLessonDateTime and endLessonDateTime =b.endLessonDateTime and classID=b.classID and\n" +
            "buxikechengID=a.buxiID )=0\n " +
            "</script>")
    List<Pxpaiketable> getNokehaoStutoPk(Long kechengID , Long stuID, Long qiyeID);
}