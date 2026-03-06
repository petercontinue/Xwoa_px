package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.PxxiangcetableVo;
import com.xwcloud.cloud.model.entity.Pxxiangcetable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-13
 */
@Repository
public interface IPxxiangcetableDao extends BaseMapper<Pxxiangcetable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "title", property = "title"),
                @Result(column = "miaoshu", property = "miaoshu"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "typeparmID", property = "typeparmID"),
                @Result(column = "type", property = "type"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxxiangcetable"
            + "</script>")
    List<Pxxiangcetable> getAllList();


    @Select("<script>" +
            "SELECT xiangce.*,staff.staffName as addStaffName,\n" +
            "CASE type\n" +
            "WHEN 1 THEN\n" +
            "(SELECT stuName FROM pxstutable WHERE id=xiangce.stuID)\n" +
            "WHEN 2 THEN\n" +
            "(SELECT campusName FROM pxcampustable WHERE id=xiangce.typeparmID)\n" +
            "WHEN 3 THEN\n" +
            "(SELECT className FROM pxclasstable WHERE id=xiangce.typeparmID)\n" +
            "ELSE '未知' \n" +
            " END AS stuName,\n" +
            " (select image FROM pxxiangceimagetable where xiangceid = xiangce.id limit 0,1) as imgurl\n" +
            " FROM pxxiangcetable xiangce  \n" +
            " LEFT JOIN pxstafftable staff ON staff.id= xiangce.addStaffID "+
            " WHERE 1=1 " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<PxxiangcetableVo> getPage(Page page, @Param("ew") Wrapper wrapper);
}