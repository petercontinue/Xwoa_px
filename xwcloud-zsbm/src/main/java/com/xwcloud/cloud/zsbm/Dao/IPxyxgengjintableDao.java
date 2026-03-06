package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.genjinInfoVo;
import com.xwcloud.cloud.model.Vo.genjinliushuiVo;
import com.xwcloud.cloud.model.entity.Pxyxgengjintable;
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
 * @since 2021-04-15
 */
@Repository
public interface IPxyxgengjintableDao extends BaseMapper<Pxyxgengjintable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "gengjinText", property = "gengjinText"),
            @Result(column = "gengjinTime", property = "gengjinTime"),
            @Result(column = "adduser", property = "adduser"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "isRead", property = "isRead"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxgengjintable"
            + "</script>")
    List<Pxyxgengjintable> getAllList();

    @Select("<script>" + "SELECT * FROM pxyxgengjintable WHERE stuID= #{stuID}" + "</script>")
    List<Pxyxgengjintable> GetAllYixiangGenjinByStuID(long stuID);

    //分页查询学生跟进记录
    @Select("<script>" +
            "SELECT a.id,a.gengjinText,a.gengjinTime,b.staffName addStaffName,c.nextGenjinTime,c.yxLevelID,d.telLevelName \n" +
            "FROM pxyxgengjintable AS a \n" +
            "LEFT JOIN pxstafftable AS b ON a.adduser = b.id \n " +
            "left join pxstutable c on a.stuID=c.id " +
            "left join pxyxtelleveltable d on c.yxLevelID=d.id "+
            "WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<genjinInfoVo> GetgenjinInfoPages(Page page, @Param("ew") QueryWrapper queryWrapper);


    //根据学生ID删除学生跟进记录
    @Select("<script>" + "DELETE FROM pxyxgengjintable WHERE stuID = #{stuID}" + "</script>")
    int DeleteGenjinRecordsBystuID(long stuID);

    //查询跟进流水
    @Select("<script>" +
            "SELECT a.id, c.campusName, b.stuName, a.gengjinText, a.gengjinTime, d.staffName, e.staffName fuzeStaffName, a.addTime  \n" +
            "FROM pxyxgengjintable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID=c.id\n" +
            "LEFT JOIN pxstafftable AS d ON a.adduser=d.id\n" +
            "LEFT JOIN pxstafftable AS e ON b.yxfenpeistaffID=e.id\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<genjinliushuiVo> GegenjinLiushuiPages(Page page, @Param("ew") Wrapper wrapper);

    //查询导出的跟进流水
    @Select("<script>" +
            "SELECT a.id,c.campusName,b.stuName,a.gengjinText,a.gengjinTime,d.staffName,a.addTime,e.staffName AS fuzeStaffName \n" +
            "FROM pxyxgengjintable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID=c.id\n" +
            "LEFT JOIN pxstafftable AS d ON a.adduser=d.id\n" +
            "LEFT JOIN pxstafftable AS e ON b.yxfenpeistaffID=e.id " +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<genjinliushuiVo> GetExportGenjinliushui(@Param("ew") Wrapper wrapper);
}