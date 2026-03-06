package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.staffyichangkaoqinVO;
import com.xwcloud.cloud.model.entity.Pxyichangkaoqintable;
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
 * @since 2020-12-18
 */
@Repository
public interface IPxyichangkaoqintableDao extends BaseMapper<Pxyichangkaoqintable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "type", property = "type"),
            @Result(column = "riqi", property = "riqi"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "addstaffID", property = "addstaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyichangkaoqintable"
            + "</script>")
    List<Pxyichangkaoqintable> getAllList();


    @Select("<script>" +
            "SELECT a.*,c.id campusID from  pxyichangkaoqintable a \n" +
            "LEFT JOIN pxstafftable b on a.staffID=b.id\n" +
            "LEFT JOIN pxcampustable c on b.campusID=c.id\n" +
            "where c.isOpen !=2 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String,String>> getyichangstaffbandCampusID(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 分页查询异常考勤信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+"SELECT a.id,c.campusName,d.staffpostName,b.staffName,a.addDate,a.type,a.shuoMing,a.riqi,e.staffName AS lururen " +
            "FROM pxyichangkaoqintable AS a \n" +
            "LEFT JOIN pxstafftable AS b ON a.staffID = b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID =c.id\n" +
            "LEFT JOIN pxstaffposttable AS d ON b.staffPostID = d.id\n" +
            "LEFT JOIN pxstafftable AS e ON a.addStaffID = e.id\n" +
            "WHERE c.isOpen !=2"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<staffyichangkaoqinVO> GetyichangkaoqinPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT a.id,c.campusName,d.staffpostName,b.staffName,a.addDate,a.type,a.shuoMing,a.riqi,e.staffName AS lururen FROM pxyichangkaoqintable AS a \n" +
            "LEFT JOIN pxstafftable AS b ON a.staffID = b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID =c.id\n" +
            "LEFT JOIN pxstaffposttable AS d ON b.staffPostID = d.id\n" +
            "LEFT JOIN pxstafftable AS e ON a.addStaffID = e.id\n" +
            "WHERE c.isOpen !=2"+ "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<staffyichangkaoqinVO> getyichangkaoqingList(@Param("ew") Wrapper wrapper);
}