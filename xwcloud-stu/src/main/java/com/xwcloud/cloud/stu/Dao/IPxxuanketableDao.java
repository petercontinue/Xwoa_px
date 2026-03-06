package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.ReclassVo;
import com.xwcloud.cloud.model.Vo.allxuankeVo;
import com.xwcloud.cloud.model.Vo.tuixiankeVo;
import com.xwcloud.cloud.model.Vo.zbInPaikeVo;
import com.xwcloud.cloud.model.entity.Pxxuanketable;
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

    /**
     * 按条件获取
     */
    @Select("<script>" + "select * from pxxuanketable where paikeID=#{paikeID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxxuanketable> allxuankebypkID(Long paikeID, Long qiyeID);

    @Select("<script>" + "select * from pxxuanketable where paikeID=#{paikeID} and stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxxuanketable> xuankebypkstu(Long paikeID, Long stuID, Long qiyeID);

    @Select("<script>" + "select * from pxxuanketable where paikeID=#{paikeID} and buxiID=#{bxID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxxuanketable> xxkbypkbx(Long paikeID, Long bxID, Long qiyeID);

    @Select("<script>" + "select * from pxxuanketable where paikeID=#{paikeID} and stuID=#{stuID} and buxiID=#{bxID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxxuanketable> getxkByPkStuBx(Long paikeID, Long stuID, Long bxID, Long qiyeID);

    @Select("<script>" +
            "select GROUP_CONCAT(pxxuanketable.id) as classIDs from pxxuanketable " +
            "LEFT JOIN pxpaiketable on pxxuanketable.paikeID=pxpaiketable.id " +
            "where classID=#{classID} and pxxuanketable.qiyeID=#{qiyeID}" +
            "</script>")
    List<ReclassVo> getJoinPaikeL(Long classID, Long qiyeID);

    //排课学员
    @Select("<script>" +
            "select GROUP_CONCAT(pxxuanketable.id) as classIDs from pxxuanketable " +
            "LEFT JOIN pxpaiketable on pxxuanketable.paikeID=pxpaiketable.id " +
            "where classID=#{classID} and stuID=#{stuID} and pxxuanketable.qiyeID=#{qiyeID}" +
            "</script>")
    List<ReclassVo> getJoinPaikeStuL(Long classID, Long stuID, Long qiyeID);

    @Select("<script>" + "select * from pxxuanketable where buxiID=#{bxID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxxuanketable> getBybxID(Long bxID, Long qiyeID);

    /**
     * 排课的总人数（有效人数）
     *
     * @param paikeID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxxuanketable a \n" +
            "LEFT JOIN pxbuxikechengtable b on a.buxiID=b.id\n" +
            "where b.isShow =1 and a.paikeID=#{paikeID} and a.qiyeID=#{qiyeID}" +
            "</script>")
    List<Pxxuanketable> getallxuankeTable(Long paikeID, Long qiyeID);


    @Select("<script>" +
            "select b.id paikeID,b.classID,b.startLessonDateTime,b.endLessonDateTime,\n" +
            "(SELECT COUNT(id) from pxkeshistutable where haveClassDate=b.haveClassDate and startLessonDateTime=b.startLessonDateTime and endLessonDateTime=b.endLessonDateTime and classID=b.classID and buxikechengID=a.buxiID) keshi\n" +
            "from pxxuanketable a \n" +
            "LEFT JOIN pxpaiketable b on a.paikeID=b.id\n" +
            "WHERE b.dakaoqin!=true and (SELECT COUNT(id) from pxkeshistutable where haveClassDate=b.haveClassDate and startLessonDateTime=b.startLessonDateTime and endLessonDateTime=b.endLessonDateTime and classID=b.classID and buxikechengID=a.buxiID)=0 and a.buxiID=#{buxiID} and a.qiyeID=#{qiyeID}" +
            "</script>")
    List<allxuankeVo> getallxuanke(Long buxiID, Long qiyeID);

    //获取学员的排课
    @Select("<script>" + "select * from pxxuanketable where paikeID=#{paikeID} and stuID=#{stuID} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxxuanketable> getBypkandStu(Long paikeID, Long stuID, Long qiyeID);

    //获取排课没课耗学员
    @Select("<script>" +
            "select a.*,(SELECT count(id) from pxkeshistutable where haveClassDate=b.haveClassDate and startLessonDateTime=b.startLessonDateTime and endLessonDateTime =b.endLessonDateTime and classID=b.classID and\n" +
            "buxikechengID=a.buxiID ) keshi \n" +
            "from pxxuanketable a \n" +
            "LEFT JOIN pxpaiketable b on a.paikeID=b.id\n" +
            "where a.buxiID = #{buxiID} and a.qiyeID=#{qiyeID} and  (SELECT count(id) from pxkeshistutable where haveClassDate=b.haveClassDate and startLessonDateTime=b.startLessonDateTime and endLessonDateTime =b.endLessonDateTime and classID=b.classID and\n" +
            "buxikechengID=a.buxiID )=0" +
            "</script>")
    List<Pxxuanketable> getNokehaoStu(Long buxiID, Long qiyeID);


    //按班级和补习ID查找已加入的排课
    @Select("<script>" +
            "select b.id paikeid,b.haveClassDate haveClassDate,b.startLessonDateTime startLessonDateTime,b.endLessonDateTime endLessonDateTime " +
            " from pxxuanketable a \n" +
            "LEFT JOIN pxpaiketable b on a.paikeID=b.id \n" +
            "where a.buxiID=#{buxiID} and b.classID=#{classID} and a.qiyeiD=#{qiyeID} \n" +
            "ORDER BY b.haveClassDate " +
            "</script>")
    List<zbInPaikeVo> getInPaikeByClassandBuxi(String buxiID, String classID, Long qiyeID);


    @Select("<script>" +
            "select a.id id,b.id paikeid,b.haveClassDate haveClassDate,b.endLessonDateTime endLessonDateTime,b.startLessonDateTime startLessonDateTime  " +
            " from pxxuanketable a \n" +
            "LEFT JOIN pxpaiketable b on a.paikeID=b.id \n" +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "ORDER BY b.haveClassDate " +
            "</script>")
    List<tuixiankeVo> tuixuankePaike(@Param("ew") QueryWrapper queryWrapper);


}