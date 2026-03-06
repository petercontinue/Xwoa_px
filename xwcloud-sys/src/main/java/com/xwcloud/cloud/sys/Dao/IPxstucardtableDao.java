package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstucardtable;
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
 * @since 2021-07-29
 */
@Repository
public interface IPxstucardtableDao extends BaseMapper<Pxstucardtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "cardNumber", property = "cardNumber"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstucardtable"
            + "</script>")
    List<Pxstucardtable> getAllList();


    @Select("<script>" +
            "select * from pxstutable a left join pxstucardtable b on a.id=b.stuID\n" +
            "where a.buxiStateID!=1 and a.buxiStateID!=7 and b.id is null " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"+
            " GROUP BY a.id"
            + "</script>")
    List<Pxstucardtable> getNostuCardlist(@Param("ew") QueryWrapper queryWrapper);
}