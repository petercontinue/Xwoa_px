package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxclasstimestyletable;
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
 * @since 2020-12-20
 */
@Repository
public interface IPxclasstimestyletableDao extends BaseMapper<Pxclasstimestyletable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "classTimeStyleName", property = "classTimeStyleName"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxclasstimestyletable"
            + "</script>")
    List<Pxclasstimestyletable> getAllList();

    @Select("<script>" +
            "SELECT\n" +
            "\ta.id id,(\n" +
            "\tCASE\n" +
            "\t\t\tWHEN a.classTimeStyleName =- 1 THEN\n" +
            "\t\t\t'一次' \n" +
            "\t\t\tWHEN a.classTimeStyleName =- 2 THEN\n" +
            "\t\t\t'一天' ELSE concat( a.classTimeStyleName,'', '分钟' ) \n" +
            "\t\tEND \n" +
            "\t\t) NAME \n" +
            "\tFROM\n" +
            "\t\tpxclasstimestyletable a \n" +
            "WHERE\n" +
            "\ta.qiyeID =1 "
            + "</script>")
    List<listVo> getAllClassTimeList(Long qiyeID);

    @Select("<script>" +
            "SELECT * from  pxclasstimestyletable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxclasstimestyletable> selectclassTime(@Param("ew") QueryWrapper queryWrapper);
}