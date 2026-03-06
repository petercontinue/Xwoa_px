package com.xwcloud.cloud.zsbm.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxkechengcontenttable;

import com.xwcloud.cloud.model.Vo.kechengContentVo;
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
public interface IPxkechengcontenttableDao extends BaseMapper<Pxkechengcontenttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "contentPaixu", property = "contentPaixu"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addStaffID", property = "addStaffID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkechengcontenttable"
            + "</script>")
    List<Pxkechengcontenttable> getAllList();

    //分页查询课程内容信息
    @Select("<script>" + "SELECT * FROM pxkechengcontenttable as a LEFT JOIN pxkechengtable as b ON b.id = a.kechengID\n" +
            "LEFT JOIN pxbuxistyletable as c ON c.id = b.buxiStyleID\n" +
            "LEFT JOIN pxclasstimestyletable as d ON d.id = b.classTimeStyleID" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<kechengContentVo> getKechengContentPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT * FROM pxkechengcontenttable WHERE kechengID=#{kechengID} AND contentPaixu=#{contentPaixu}"+"</script>")
    List<Pxkechengcontenttable> getKechengcontentBykcidandpx(long kechengID,Integer contentPaixu);

}