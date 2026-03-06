package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxdaijinquantable;
import com.xwcloud.cloud.model.zsbm.Vo.daijinquanVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
public interface IPxdaijinquantableDao extends BaseMapper<Pxdaijinquantable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "money", property = "money"),
            @Result(column = "creatTime", property = "creatTime"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxdaijinquantable"
            + "</script>")
    List<Pxdaijinquantable> getAllList();

    @Select("<script>" + "SELECT * FROM pxdaijinquantable WHERE qiandanID=#{qiandanID}" + "</script>")
    Pxdaijinquantable GetDaijinquanByID(Long qiandanID);

    @Select("<script>" + "SELECT * FROM pxdaijinquantable WHERE stuID=#{stuID}" + "</script>")
    List<Pxdaijinquantable> GetDaijinquanListByStuID(Long stuID);

    @Select("<script>" + "DELETE FROM pxdaijinquantable WHERE stuID=#{stuID}" + "</script>")
    Integer DeleteDaijinquanByStuID(Long stuID);

    /**
     * 分页查询代金券流水信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.id,d.campusName,b.zidingyiStuID AS stuNO,b.stuName,e.stuGradeName,a.money,a.creatTime AS createTime,c.staffName,f.staffName as banzhuren FROM pxdaijinquantable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "LEFT JOIN pxstafftable AS c ON a.staffID = c.id\n" +
            "LEFT JOIN pxcampustable AS d ON b.campusID = d.id\n" +
            "LEFT JOIN pxstugradetable AS e ON b.stuGradeID = e.id\n" +
            "LEFT JOIN pxstafftable AS f ON b.banzhurenTeacherID = f.id" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<daijinquanVo> GetDaijinquanLiushuiPages(Page page, @Param("ew") Wrapper wrapper);


    @Select("<script>" + "SELECT b.campusID,d.campusName,b.zidingyiStuID AS stuNO,b.stuName,e.stuGradeName,a.money,a.creatTime AS createTime,c.staffName,f.staffName as banzhuren FROM pxdaijinquantable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "LEFT JOIN pxstafftable AS c ON a.staffID = c.id\n" +
            "LEFT JOIN pxcampustable AS d ON b.campusID = d.id\n" +
            "LEFT JOIN pxstugradetable AS e ON b.stuGradeID = e.id\n" +
            "LEFT JOIN pxstafftable AS f ON b.banzhurenTeacherID = f.id" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<daijinquanVo> GetDaijinquanLiushuiList(@Param("ew") Wrapper wrapper);
}