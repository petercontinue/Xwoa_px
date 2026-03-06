package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxsalarytable;
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
public interface IPxsalarytableDao extends BaseMapper<Pxsalarytable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "salaryMoney", property = "salaryMoney"),
                @Result(column = "shenheState", property = "shenheState"),
                @Result(column = "staffID", property = "staffID"),
                @Result(column = "shenheNopassReason", property = "shenheNopassReason"),
                @Result(column = "shengHeTime", property = "shengHeTime"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "salaryDate", property = "salaryDate"),
                @Result(column = "salaryEndDate", property = "salaryEndDate"),
                @Result(column = "lururen", property = "lururen"),
                @Result(column = "lurudatetime", property = "lurudatetime"),
                @Result(column = "shengheren", property = "shengheren"),
                @Result(column = "fafangzhuangtai", property = "fafangzhuangtai"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxsalarytable"
            + "</script>")
    List<Pxsalarytable> getAllList();
}