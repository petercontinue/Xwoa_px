package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxsalaryxiangxitable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
 */
@Repository
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
}