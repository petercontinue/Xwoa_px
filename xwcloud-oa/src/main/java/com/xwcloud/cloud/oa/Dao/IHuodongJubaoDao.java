package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.HuodongJubao;
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
public interface IHuodongJubaoDao extends BaseMapper<HuodongJubao> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "huodongType", property = "huodongType"),
                @Result(column = "shuoming", property = "shuoming"),
                @Result(column = "huodongtitle", property = "huodongtitle"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "huodongID", property = "huodongID"),
                @Result(column = "addUserID", property = "addUserID"),
                @Result(column = "ischuli", property = "ischuli"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  huodong_jubao"
            + "</script>")
    List<HuodongJubao> getAllList();
}