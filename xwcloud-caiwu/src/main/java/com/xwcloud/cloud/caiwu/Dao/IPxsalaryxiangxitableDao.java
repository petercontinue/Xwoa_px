package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.PxsalaryxiangxitableVo;
import com.xwcloud.cloud.model.entity.Pxsalaryxiangxitable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
public interface IPxsalaryxiangxitableDao extends BaseMapper<Pxsalaryxiangxitable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "salarystyleID", property = "salarystyleID"),
            @Result(column = "salarymoney", property = "salarymoney"),
            @Result(column = "salaryID", property = "salaryID"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxsalaryxiangxitable"
            + "</script>")
    List<Pxsalaryxiangxitable> getAllList();

    @Select("<script>" +
            "SELECT xiangxi.*,style.salaryStyle,style.isJiaOrJianOrQiuhe FROM pxsalaryxiangxitable xiangxi " +
            "LEFT JOIN pxsalarystyletable style ON xiangxi.salarystyleID = style.id"+
            "<if test='ew != null'>"+
            " where 1=1 AND "+
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<PxsalaryxiangxitableVo> getxiangxiList(@Param("ew") Wrapper wrapper);
}