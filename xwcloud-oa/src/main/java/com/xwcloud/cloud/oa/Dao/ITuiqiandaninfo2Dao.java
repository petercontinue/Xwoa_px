package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Tuiqiandaninfo2;
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
public interface ITuiqiandaninfo2Dao extends BaseMapper<Tuiqiandaninfo2> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "tuifeiID", property = "tuifeiID"),
                @Result(column = "tuiqianInfo2ID", property = "tuiqianInfo2ID"),
                @Result(column = "beforetuiMoney", property = "beforetuiMoney"),
                @Result(column = "aftertuiMoney", property = "aftertuiMoney"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  tuiqiandaninfo2"
            + "</script>")
    List<Tuiqiandaninfo2> getAllList();
}