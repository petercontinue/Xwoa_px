package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.daikebiaoVO;
import com.xwcloud.cloud.model.Vo.stupaikeVO;
import com.xwcloud.cloud.model.Vo.teapaikeVO;
import com.xwcloud.cloud.model.Vo.xiaokeVO;
import com.xwcloud.cloud.model.entity.Pxpaiketable;
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
 * @since 2021-05-31
 */
@Repository
public interface IPxpaiketableDao extends BaseMapper<Pxpaiketable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "zhujiaoID", property = "zhujiaoID"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "classRoomID", property = "classRoomID"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "MaxStuNum", property = "maxStuNum"),
            @Result(column = "tongke1v1KechengID", property = "tongke1v1KechengID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "dakaoqin", property = "dakaoqin"),
            @Result(column = "tags", property = "tags"),
            @Result(column = "canqingjiaBeforeHours", property = "canqingjiaBeforeHours"),
            @Result(column = "istuisongTixingMsg", property = "istuisongTixingMsg"),
            @Result(column = "shuakaTimeArea", property = "shuakaTimeArea"),
            @Result(column = "paikeType", property = "paikeType"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "huodongImg", property = "huodongImg"),
            @Result(column = "huodongTitle", property = "huodongTitle"),
            @Result(column = "huodongshuoming", property = "huodongshuoming"),
            @Result(column = "liulangtime", property = "liulangtime"),
            @Result(column = "fenxiangtime", property = "fenxiangtime"),
            @Result(column = "zixunphone", property = "zixunphone"),
            @Result(column = "shitingprice", property = "shitingprice"),
    })
    @Select("<script>" +
            "SELECT * from  pxpaiketable"
            + "</script>")
    List<Pxpaiketable> getAllList();

    /**
     * 分页查询学生排课信息（小程序端）
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT paike.id,stu.stuName,paike.haveClassDate,paike.weekN\n" +
            ",paike.startLessonDateTime,paike.endLessonDateTime,cla.classRoomName,k.kechengName,c.className,sub.subjectName,staff.staffName\n" +
            "FROM pxpaiketable AS paike \n" +
            "JOIN pxclasstable AS c ON paike.classID = c.id\n" +
            "JOIN pxkechengtable AS k ON paike.kechengID = k.id\n" +
            "JOIN pxsubjecttable AS sub ON k.subjectID = sub.id\n" +
            "JOIN pxclassroomtable AS cla ON paike.classRoomID = cla.id\n" +
            "JOIN pxxuanketable AS xuan ON paike.id=xuan.paikeID\n" +
            "JOIN pxbuxikechengtable AS bx ON xuan.buxiID = bx.id\n" +
            "JOIN pxstutable AS stu ON xuan.stuID = stu.id\n" +
            "JOIN pxpaiketeachertable AS pt ON paike.id = pt.paikeID\n" +
            "JOIN pxstafftable AS staff ON pt.teacherID = staff.id WHERE 1=1 " + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<stupaikeVO> GetStupaikePages(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 分页查询教师排课信息（小程序端）
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT pai.classID,pai.id,pai.teacherNames, sub.subjectName,pai.haveClassDate,\n" +
            "pai.weekN,pai.startLessonDateTime,pai.endLessonDateTime,room.classRoomName,cam.campusName,ke.kechengName,cla.className,\n" +
            "(select GROUP_CONCAT(stu.stuName)FROM pxxuanketable AS xuan LEFT JOIN pxbuxikechengtable AS bx ON xuan.buxiID = bx.id LEFT JOIN pxstutable AS stu ON xuan.stuID = stu.id WHERE xuan.paikeID = pai.id) AS stuNames\n" +
            "FROM pxpaiketable AS pai\n" +
            "LEFT JOIN pxclasstable AS cla ON pai.classID = cla.id\n" +
            "LEFT JOIN pxkechengtable AS ke ON pai.kechengID = ke.id\n" +
            "LEFT JOIN pxsubjecttable AS sub ON ke.subjectID = sub.id\n" +
            "LEFT JOIN pxclassroomtable AS room ON pai.classRoomID = room.id\n" +
            "LEFT JOIN pxpaiketeachertable AS t ON pai.id = t.paikeID\n" +
            "LEFT JOIN pxcampustable AS cam ON ke.campusID = cam.id WHERE 1=1 " + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<teapaikeVO> GetTeacherPaikePages(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 分页查询天课表信息（小程序端）
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT \n" +
            "pai.id,ke.kechengName,pai.haveClassDate,pai.startLessonDateTime,pai.endLessonDateTime,pai.weekN,pai.classID,cla.className,pai.classRoomID,room.classRoomName,room.campusID,pt.teacherID,campus.campusName,staff.staffName\n" +
            "FROM pxpaiketable AS pai LEFT JOIN pxclasstable AS cla ON pai.classID = cla.id\n" +
            "LEFT JOIN pxkechengtable AS ke ON pai.kechengID = ke.id\n" +
            "LEFT JOIN pxclassroomtable AS room ON pai.classRoomID = room.id\n" +
            "LEFT JOIN pxpaiketeachertable AS pt ON pai.id = pt.paikeID\n" +
            "LEFT JOIN pxstafftable AS staff ON pt.teacherID = staff.id\n" +
            "LEFT JOIN pxcampustable AS campus ON room.campusID = campus.id \nWHERE 1=1 " + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<daikebiaoVO> GetDayKebiaoPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT pai.id AS paykeID, pai.haveClassDate,pai.startLessonDateTime,pai.endLessonDateTime,kecheng.kechengName,staff.staffName,room.classRoomName,cla.className,\n" +
            "(select COUNt(*) from pxxuanketable AS xuan LEFT JOIN pxstutable AS stu ON xuan.stuID = stu.id WHERE xuan.paikeID=pai.id AND (stu.buxiStateID = 1 OR stu.buxiStateID = 2)) AS xuanstuNum,\n" +
            "(SELECT COUNT(*) FROM pxkeshistutable WHERE classID = pai.classID AND haveClassDate = pai.haveClassDate AND startLessonDateTime = pai.startLessonDateTime AND endLessonDateTime = pai.endLessonDateTime AND teacherIDs = pai.teacherIDs ) AS kaoqinstuNum,pai.dakaoqin\n" +
            "FROM pxpaiketable AS pai LEFT JOIN pxclassroomtable AS room ON pai.classRoomID= room.id\n" +
            "LEFT JOIN pxclasstable AS cla ON pai.classID = cla.id\n" +
            "LEFT JOIN pxpaiketeachertable AS pt ON pai.id = pt.paikeID\n" +
            "LEFT JOIN pxstafftable AS staff ON pt.teacherID = staff.id\n" +
            "LEFT JOIN pxkechengtable AS kecheng ON pai.kechengID = kecheng.id\n" +
            "LEFT JOIN pxcampustable AS xq ON cla.campusID = xq.id\nWHERE 1=1 " + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<xiaokeVO> GetAllxiaokeDataPage(Page page, @Param("ew") Wrapper wrapper);
}