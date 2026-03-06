package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.wechatMessageVO;
import com.xwcloud.cloud.model.entity.Pxtuisongtable;
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
 * @since 2020-12-15
 */
@Repository
public interface IPxtuisongtableDao extends BaseMapper<Pxtuisongtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "note", property = "note"),
            @Result(column = "tuisongTypeName", property = "tuisongTypeName"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "role", property = "role"),
            @Result(column = "wxstate", property = "wxstate"),
            @Result(column = "appread", property = "appread"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtuisongtable"
            + "</script>")
    List<Pxtuisongtable> getAllList();

    /**
     * 分页查询员工推送消息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.id,a.note,b.staffName,d.tuisongType,c.campusName,a.addTime" +
            " FROM pxtuisongtable AS a" +
            " LEFT JOIN pxstafftable AS b ON a.staffID = b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "LEFT JOIN pxtuisongtypetable AS d ON a.tuisongTypeName = d.id\n" +
            "WHERE a.role = 2 AND c.isOpen!=2\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<wechatMessageVO> GetyuangongWechatMessagesPages(Page page, @Param("ew") Wrapper wrapper);
}