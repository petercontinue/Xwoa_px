package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.teachSubjectVo;
import com.xwcloud.cloud.model.entity.Pxteachsubjecttable;
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
 * @since 2020-12-08
 */
@Repository
public interface IPxteachsubjecttableDao extends BaseMapper<Pxteachsubjecttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "teachSubjectID", property = "teachSubjectID"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachsubjecttable"
            + "</script>")
    List<Pxteachsubjecttable> getAllList();

    /**
     * 分页查询任教科目信息
     * @param page
     * @param wrapper
     * @param staffID
     * @return
     */
    @Select("<script>"+"SELECT b.id,a.staffName,c.campusName AS staffCampusName ,d.subjectName,b.shuoming,e.campusName AS teachCampusName FROM pxstafftable AS a LEFT JOIN pxteachsubjecttable AS b ON a.id = b .staffID\n" +
            "            LEFT JOIN pxcampustable AS c ON a.campusID = c.id \n" +
            "            LEFT JOIN pxsubjecttable AS d ON b.teachSubjectID=d.id\n" +
            "LEFT JOIN pxcampustable AS e ON e.id = d.campusID"+  " WHERE b.staffID=#{staffID} AND c.isOpen!=2 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<teachSubjectVo> GetTeacheSubjectPages(Page page, @Param("ew") Wrapper wrapper,String staffID);
}