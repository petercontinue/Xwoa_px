package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.LogxjbVo;
import com.xwcloud.cloud.model.log.Logxjbtable;
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
 * @since 2020-10-25
 */
@Repository
public interface ILogxjbtableDao extends BaseMapper<Logxjbtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "systemContent", property = "systemContent"),
            @Result(column = "funcName", property = "funcName"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "logType", property = "logType"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  logxjbtable"
            + "</script>")
    List<Logxjbtable> getAllList();

    /**
     * 查询日志信息（分页）
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.addTime addTime, c.campusName campusName,a.funcName funcName," +
            "a.logType logType,b.staffName staffName ,a.systemContent systemContent " +
            " FROM logxjbtable as a " +
            "LEFT JOIN pxstafftable as b on a.staffID = b.id " +
            "LEFT JOIN pxcampustable as c on b.campusID = c.id" +
            " WHERE 1=1" + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<LogxjbVo> getLogxjbInfo(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 查询日志信息，不分页
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT\n" +
            "\ta.addTime addTime,\n" +
            "\tc.campusName campusName,\n" +
            "\ta.funcName funcName,\n" +
            "\t( CASE WHEN a.logType = 1 THEN '员工日志' \n" +
            "\tWHEN a.logType = 2 THEN '学员日志'\n" +
            "\tELSE '系统自动产生的日志' END ) logType,\n" +
            "\tb.staffName staffName,\n" +
            "\ta.systemContent systemContent \n" +
            "FROM\n" +
            "\tlogxjbtable AS a\n" +
            "\tLEFT JOIN pxstafftable AS b ON a.staffID = b.id\n" +
            "\tLEFT JOIN pxcampustable AS c ON b.campusID = c.id  "
            + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<LogxjbVo> getLogxjbInfolist(@Param("ew") Wrapper wrapper);
}