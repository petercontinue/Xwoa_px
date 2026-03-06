package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
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
 * @since 2020-12-19
 */
@Repository
public interface IPxpaiketableDao extends BaseMapper<Pxpaiketable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "startLessonDateTime", property = "startlessondatetime"),
            @Result(column = "endLessonDateTime", property = "endlessondatetime"),
            @Result(column = "haveClassDate", property = "haveclassdate"),
            @Result(column = "teacherIDs", property = "teacherids"),
            @Result(column = "teacherNames", property = "teachernames"),
            @Result(column = "classID", property = "classid"),
            @Result(column = "classRoomID", property = "classroomid"),
            @Result(column = "weekN", property = "weekn"),
            @Result(column = "MaxStuNum", property = "maxstunum"),
            @Result(column = "tongke1v1KechengID", property = "tongke1v1kechengid"),
            @Result(column = "kechengID", property = "kechengid"),
            @Result(column = "kechengContent", property = "kechengcontent"),
            @Result(column = "dakaoqin", property = "dakaoqin"),
            @Result(column = "tags", property = "tags"),
            @Result(column = "canqingjiaBeforeHours", property = "canqingjiabeforehours"),
            @Result(column = "shuakaTimeArea", property = "shuakatimearea"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  pxpaiketable"
            + "</script>")
    List<Pxpaiketable> getAllList();

    @Select("<script>" +
            "SELECT *,(tmp.paikeNum/tmp.keshiNum) xiaokelv FROM (\n" +
            "SELECT campus.campusName,grade.stuGradeName,stu.stuName,\n" +
            "            SUM(CASE kecheng.jifeiStyleID\n" +
            "            WHEN 3 THEN\n" +
            "                 1 \n" +
            "             ELSE \n" +
            "            TIMESTAMPDIFF(MINUTE ,paike.startLessonDateTime,paike.endLessonDateTime)/CONVERT(classtime.classTimeStyleName,SIGNED) \n" +
            "            END) AS paikeNum,\n" +
            "            SUM(keshi.keshiNum) as keshiNum\n" +
            "            FROM pxpaiketable paike\n" +
            "            LEFT JOIN pxxuanketable xuanke ON paike.id=xuanke.buxiID\n" +
            "            LEFT JOIN pxbuxikechengtable buxikecheng ON xuanke.buxiID=buxikecheng.id\n" +
            "            LEFT JOIN pxkechengtable kecheng ON kecheng.id = buxikecheng.kechengID\n" +
            "            LEFT JOIN pxclasstimestyletable classtime ON kecheng.classTimeStyleID=classtime.id\n" +
            "            LEFT JOIN pxstutable stu ON stu.id=buxikecheng.stuID\n" +
            "            LEFT JOIN pxcampustable campus ON stu.campusID=campus.id\n" +
            "            LEFT JOIN pxstugradetable grade ON stu.stuGradeID = grade.id\n" +
            "            LEFT JOIN pxkeshistutable keshi ON stu.id=keshi.stuID\n" +
            "            WHERE 1=1 \n" +
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "            GROUP BY campus.id,grade.id,stu.id HAVING stu.stuName IS NOT NULL) tmp"
            + "</script>")
    Page<HashMap<String,String>> getStupaikePage(Page page,@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT campus.campusName,staff.staffName,\n" +
            "SUM(CASE kecheng.jifeiStyleID \n" +
            "WHEN 3 THEN 1 \n" +
            "\t\t\tELSE TIMESTAMPDIFF(MINUTE ,paike.startLessonDateTime,paike.endLessonDateTime)/CONVERT(classtime.classTimeStyleName,SIGNED)   \n" +
            "            END) AS paikeNum\n" +
            "FROM pxpaiketeachertable paiketeacher \n" +
            "LEFT JOIN pxpaiketable paike ON paiketeacher.paikeID= paike.id\n" +
            "LEFT JOIN pxstafftable staff ON paiketeacher.teacherID=staff.id\n" +
            "LEFT JOIN pxcampustable campus ON campus.id=staff.campusID\n" +
            "LEFT JOIN pxxuanketable xuanke ON paike.id=xuanke.paikeID\n" +
            "LEFT JOIN pxbuxikechengtable buxikecheng ON xuanke.buxiID=buxikecheng.id\n" +
            "LEFT JOIN pxkechengtable kecheng ON buxikecheng.kechengID=kecheng.id\n" +
            "LEFT JOIN pxclasstimestyletable classtime ON kecheng.classTimeStyleID=classtime.id\n" +
            "WHERE 1=1 "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY campus.id,staff.id\n"
            + "</script>")
    Page<HashMap<String,String>> getTeacherpaikePage(Page page,@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT campus.campusName,\n" +
            " SUM(CASE kecheng.jifeiStyleID\n" +
            "  WHEN 3 THEN \n" +
            "   1\n" +
            "  ELSE \n" +
            "   TIMESTAMPDIFF(MINUTE ,paike.startLessonDateTime,paike.endLessonDateTime)/CONVERT(classtime.classTimeStyleName,SIGNED) \n" +
            "  END) AS paikeNum\n" +
            "FROM pxpaiketable paike\n" +
            "LEFT JOIN pxxuanketable xuanke ON paike.id=xuanke.buxiID\n" +
            "LEFT JOIN pxbuxikechengtable buxikecheng ON xuanke.buxiID=buxikecheng.id\n" +
            "LEFT JOIN pxkechengtable kecheng ON kecheng.id = buxikecheng.kechengID\n" +
            "LEFT JOIN pxclasstimestyletable classtime ON kecheng.classTimeStyleID=classtime.id\n" +
            "LEFT JOIN pxstutable stu ON stu.id=buxikecheng.stuID\n" +
            "LEFT JOIN pxcampustable campus ON stu.campusID=campus.id\n" +
            "LEFT JOIN pxkeshistutable keshi ON stu.id=keshi.stuID\n" +
            "WHERE 1=1 "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY campus.id HAVING campus.campusName IS NOT NULL\n"
            + "</script>")
    Page<HashMap<String,String>> getCampuspaikePage(Page page,@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT campus.campusName,keshi.campusID,keshi.classID,classT.className," +
            "SUM(keshi.kechengPrice) kechengPrice,MAX(keshi.haveClassDate) zuihou " +
            "FROM pxkeshistutable keshi\n" +
            "LEFT JOIN pxcampustable campus ON keshi.campusID=campus.id\n" +
            "LEFT JOIN pxclasstable classT ON keshi.classID=classT.id\n" +
            "WHERE 1=1 "+
            "<if test='ew != null'>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY campus.id,classT.id"
            + "</script>")
    Page<HashMap<String,String>> getClasspaikePage(Page page,@Param("ew") Wrapper wrapper);
}