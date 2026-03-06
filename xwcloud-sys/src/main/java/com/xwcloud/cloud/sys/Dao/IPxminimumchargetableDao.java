package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.minimumchargeVo;
import com.xwcloud.cloud.model.entity.Pxminimumchargetable;
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
 * @since 2020-10-22
 */
@Repository
public interface IPxminimumchargetableDao extends BaseMapper<Pxminimumchargetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buxiStyleId", property = "buxiStyleId"),
            @Result(column = "stuGradeId", property = "stuGradeId"),
            @Result(column = "MinimumCharge", property = "MinimumCharge"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addStaffID", property = "addStaffID"),
    })
    @Select("<script>" +
            "SELECT * from  pxminimumchargetable"
            + "</script>")
    List<Pxminimumchargetable> getAllList();

    /**
     * 分页查询最低收费标准
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+
            "SELECT * FROM pxminimumchargetable as a \n" +
            "LEFT JOIN pxbuxistyletable as b on a.buxiStyleId=b.id \n" +
            "LEFT JOIN pxstugradetable as c on a.stuGradeId = c.id \n" +
            "LEFT JOIN pxstafftable as d on a.addStaffID=d.id"+  " WHERE 1=1"+
            "<if test='ew != null and ew.sqlSelect != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<minimumchargeVo> GetShoufeiBiaozhunPages(Page page, @Param("ew") Wrapper wrapper);
}