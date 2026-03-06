package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.youhuizhengceVO;
import com.xwcloud.cloud.model.Vo.youhuizhengcexuanzeVO;
import com.xwcloud.cloud.model.entity.Pxyouhuizhengcetable;
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
 * @since 2020-11-11
 */
@Repository
public interface IPxyouhuizhengcetableDao extends BaseMapper<Pxyouhuizhengcetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "youhuiType", property = "youhuiType"),
            @Result(column = "xianzhijine", property = "xianzhijine"),
            @Result(column = "youhui", property = "youhui"),
            @Result(column = "startDateTime", property = "startDateTime"),
            @Result(column = "endDatetime", property = "endDatetime"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "stuGradeIDs", property = "stuGradeIDs"),
            @Result(column = "useTimes", property = "useTimes"),
            @Result(column = "addTime",property = "addTime")
    })
    @Select("<script>" +
            "SELECT * from  pxyouhuizhengcetable"
            + "</script>")
    List<Pxyouhuizhengcetable> getAllList();

    //通过优惠政策ID查询优惠政策信息
    @Select("<script>" + "SELECT * FROM pxyouhuizhengcetable WHERE id = #{Id} ORDER BY id LIMIT 0,1" + "</script>")
    Pxyouhuizhengcetable getYouhuizhengceById(Long Id);

    /**
     * 分页查询优惠政策
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT\n" +
            "\ta.*,\n" +
            "\tb.campusName,\n" +
            "\t(\n" +
            "\tCASE\n" +
            "\t\t\tWHEN a.stuGradeIDs =- 1 THEN\n" +
            "\t\t\t'不限年级' ELSE ( SELECT GROUP_CONCAT( stuGradeName ) FROM pxstugradetable c WHERE FIND_IN_SET( c.id, a.stuGradeIDs ) ) \n" +
            "\t\tEND \n" +
            "\t\t) AS stugradeNams \n" +
            "\tFROM\n" +
            "\tpxyouhuizhengcetable AS a\n" +
            "\tLEFT JOIN pxcampustable AS b ON a.campusID = b.id " +
            " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<youhuizhengceVO> GetYouhuizhengcePages(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 查询优惠政策
     *
     * @param stuGradeID
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select a.*," +
            "(case when a.youhuiType=1 then CONCAT('满',a.xianzhijine,'元，打',a.youhui,'折') when a.youhuiType = 2  then CONCAT('满',a.xianzhijine,'元，减',a.youhui,'元')\n" +
            "end) AS name " +
            "from pxyouhuizhengcetable AS a where (FIND_IN_SET(#{stuGradeID},a.stuGradeIDs) or a.stuGradeIDs=-1) " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<youhuizhengcexuanzeVO> youhuizhengceListBystuGrade(String stuGradeID, @Param("ew") Wrapper wrapper);

}