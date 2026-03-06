package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxchongzhitable;
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
public interface IPxchongzhitableDao extends BaseMapper<Pxchongzhitable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "shijiChongzhiMoney", property = "shijiChongzhiMoney"),
                @Result(column = "songMoney", property = "songMoney"),
                @Result(column = "shideTotalMoney", property = "shideTotalMoney"),
                @Result(column = "shuoming", property = "shuoming"),
                @Result(column = "yejiStaffID", property = "yejiStaffID"),
                @Result(column = "chongzhiDatetime", property = "chongzhiDatetime"),
                @Result(column = "payDatetime", property = "payDatetime"),
                @Result(column = "payMoneyStyle", property = "payMoneyStyle"),
                @Result(column = "payState", property = "payState"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxchongzhitable"
            + "</script>")
    List<Pxchongzhitable> getAllList();
}