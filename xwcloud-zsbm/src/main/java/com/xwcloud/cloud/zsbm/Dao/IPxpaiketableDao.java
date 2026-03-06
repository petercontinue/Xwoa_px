package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.pkchongtuVo;
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
 * @since 2020-11-14
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
            @Result(column = "tongke1v1KechengID", property = "tongke1v1KechengID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "dakaoqin", property = "dakaoqin"),
            @Result(column = "tags", property = "tags"),
            @Result(column = "canqingjiaStartDate", property = "canqingjiaStartDate"),
            @Result(column = "canqingjiaEndTime", property = "canqingjiaEndTime"),
            @Result(column = "canqingjiaTimes", property = "canqingjiaTimes"),
            @Result(column = "canqingjiaBeforeHours", property = "canqingjiaBeforeHours"),
            @Result(column = "shuakaTimeArea", property = "shuakaTimeArea"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxpaiketable"
            + "</script>")
    List<Pxpaiketable> getAllList();

    @Select("<script>"+"SELECT * FROM pxpaiketable WHERE classID =#{classID} AND dakaoqin !='true'"+"</script>")
    List<Pxpaiketable> GetPaikebyClassID(Long classID);





    /**
     * 通过班级查询排课信息
     * @param classID
     * @return
     */
    @Select("<script>" + "SELECT * FROM pxpaiketable WHERE classID=#{classID}" + "</script>")
    List<Pxpaiketable> GetAllPaikeByClassID(long classID);

    //--------------------------------意向学员----------------------------------

    /**
     * 通过班级ID删除对应班级的排课
     * @param classID
     * @return
     */
    @Select("<script>" + "DELETE FROM pxpaiketable WHERE classID=#{classID}" + "</script>")
    int DeletePaikeByClassID(long classID);

    /**
     * 通过班级ID上课日期查询排课
     */
    @Select("<script>" +
            "SELECT * " +
            "FROM pxpaiketable " +
            "WHERE classID =#{classID} " +
            "AND haveClassDate >#{haveClassDate}" +
            "</script>")
    List<Pxpaiketable> GetPaikeListbyClassID(long classID, Date haveClassDate);

    /**
     * 按照教室和排课时间查询排课信息
     * @param classRooID
     * @param haveClassDate
     * @return
     */
    @Select("<script>" + "SELECT * FROM pxpaiketable WHERE classRooID=#{classRooID} AND haveClassDate=#{haveClassDate}" + "</script>")
    List<Pxpaiketable> GetPaikeByclassroomIDandDate(long classRooID, Date haveClassDate);

    /**
     * 通过任课教师ID和上课日期查询排课
     * @param teacherID
     * @param haveClassDate
     * @return
     */
    @Select("<script>"+
            "SELECT * FROM pxpaiketable AS a " +
            "LEFT JOIN pxpaiketeachertable AS b ON a.id = b.paikeID " +
            "WHERE  b.teacherID == #{teacherID} and a.haveClassDate == #{haveClassDate}"+
            "</script>")
    List<pkchongtuVo> GetKechengChongtuList(long teacherID, Date haveClassDate);


    @Select("<script>"+
            "SELECT a.*\n" +
            "FROM pxpaiketable a\n" +
            "JOIN pxpaiketeachertable b ON a.id = b.paikeID\n" +
            "where 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxpaiketable> getTeacherTimeCT(@Param("ew") QueryWrapper<Pxpaiketable> wrapper);
}