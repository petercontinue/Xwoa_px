package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.OA.OaStaffpost;
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
 * @since 2021-06-29
 */
@Repository
public interface IOaStaffpostDao extends BaseMapper<OaStaffpost> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffpostName", property = "staffpostName"),
    })
    @Select("<script>" +
            "SELECT * from  oa_staffpost"
            + "</script>")
    List<OaStaffpost> getAllList();


    @Select("<script>" +
            "select id,staffpostName\n" +
            "from oa_staffpost\n " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<OaStaffpost> getAllStaffpostPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * from  oa_staffpost where id=#{id}"
            + "</script>")
    OaStaffpost getOneStaffpostByID_Dao(long id);
}