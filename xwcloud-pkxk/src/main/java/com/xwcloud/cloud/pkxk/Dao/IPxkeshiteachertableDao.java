package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.TeaKehaoVo;
import com.xwcloud.cloud.model.Vo.teakehaoCountVo;
import com.xwcloud.cloud.model.entity.Pxkeshiteachertable;
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
 * @since 2020-12-04
 */
@Repository
public interface IPxkeshiteachertableDao extends BaseMapper<Pxkeshiteachertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "teacherID", property = "teacherID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "buxiStyleID", property = "buxiStyleID"),
            @Result(column = "classTimeStyleID", property = "classTimeStyleID"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "ysStuNum", property = "ysStuNum"),
            @Result(column = "ssStuNum", property = "ssStuNum"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "teacherkaoqing", property = "teacherkaoqing"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "zhujiao", property = "zhujiao"),
            @Result(column = "allstuNianji", property = "allstuNianji"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshiteachertable"
            + "</script>")
    List<Pxkeshiteachertable> getAllList();

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxkeshiteachertable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxkeshiteachertable> selectTeakehao(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 教师上课记录
     */
    @Select("<script>" +
            "select a.id as id,e.campusName as campusName,b.staffName as TeachName," +
            "(SELECT staffName FROM pxstafftable where id = a.zhujiao) as zhujiao,a.allstuNianji as grade,d.kechengName as kechengName," +
            "f.buxiStyleName as buxiStyleName,c.className as className,a.haveClassDate as haveClassDate,a.startLessonDateTime as startLessonDateTime," +
            "a.endLessonDateTime as endLessonDateTime,a.keshiNum as keshiNum,a.ysStuNum as ysStuNum,a.ssStuNum as ssStuNum,a.shuoMing as shuoMing " +
            "from pxkeshiteachertable as a " +
            "LEFT JOIN pxstafftable as b on a.teacherID=b.id " +
            "LEFT JOIN pxclasstable as c ON a.classID= c.id " +
            "LEFT JOIN pxkechengtable as d on a.kechengID=d.id " +
            "LEFT JOIN pxcampustable as e on c.campusID=e.id " +
            "LEFT JOIN pxbuxistyletable as f on d.buxiStyleID = f.id" +
            " WHERE 1=1 and e.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<TeaKehaoVo> getTeakehaoPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 导出教师上课流水
     */
    @Select("<script>" +
            "select a.id as id,e.campusName as campusName,b.staffName as TeachName," +
            "(SELECT staffName FROM pxstafftable where id = a.zhujiao) as zhujiao,a.allstuNianji as grade,d.kechengName as kechengName," +
            "f.buxiStyleName as buxiStyleName,c.className as className,a.haveClassDate as haveClassDate,a.startLessonDateTime as startLessonDateTime," +
            "a.endLessonDateTime as endLessonDateTime,a.keshiNum as keshiNum,a.ysStuNum as ysStuNum,a.ssStuNum as ssStuNum,a.shuoMing as shuoMing " +
            "from pxkeshiteachertable as a " +
            "LEFT JOIN pxstafftable as b on a.teacherID=b.id " +
            "LEFT JOIN pxclasstable as c ON a.classID= c.id " +
            "LEFT JOIN pxkechengtable as d on a.kechengID=d.id " +
            "LEFT JOIN pxcampustable as e on c.campusID=e.id " +
            "LEFT JOIN pxbuxistyletable as f on d.buxiStyleID = f.id" +
            " WHERE 1=1 and e.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<TeaKehaoVo> ExportTeakehao(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT a.id id,e.campusName campusName,b.staffName staffName,a.zhujiao zhujiao,f.classTimeStyleName shichang,a.allstuNianji allNianji,c.className className,\n" +
            "a.keshiNum keshiNum,a.haveClassDate haveClassDate,a.shuoMing shuoMing\n" +
            "from pxkeshiteachertable a \n" +
            "LEFT JOIN pxstafftable b on a.teacherID=b.id\n" +
            "LEFT JOIN pxclasstable c on a.classID=c.id\n" +
            "LEFT JOIN pxkechengtable d on a.kechengID=d.id\n" +
            "LEFT JOIN pxcampustable e on c.campusID = e.id\n" +
            "LEFT JOIN pxclasstimestyletable f on d.classTimeStyleID=f.id" +
            " WHERE 1=1 and e.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<teakehaoCountVo> ExportTeakehaoCount(@Param("ew") QueryWrapper queryWrapper);

}
