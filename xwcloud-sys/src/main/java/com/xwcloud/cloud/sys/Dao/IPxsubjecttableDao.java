package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxsubjecttable;
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
 * @since 2020-11-10
 */
@Repository
public interface IPxsubjecttableDao extends BaseMapper<Pxsubjecttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "subjectName", property = "subjectName"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxsubjecttable"
            + "</script>")
    List<Pxsubjecttable> getAllList();

    @Select("<script>" +
            "select * from pxsubjecttable a left JOIN pxteachsubjecttable b on a.id=b.teachSubjectID where b.id is NULL " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY a.id "
            + "</script>")
    List<Pxsubjecttable> getNoTeakemu(@Param("ew") QueryWrapper queryWrapper);

}