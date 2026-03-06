package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscHuodongOthers;
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
 * @since 2021-06-03
 */
@Repository
public interface IWscHuodongOthersDao extends BaseMapper<WscHuodongOthers> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "huodongID", property = "huodongID"),
            @Result(column = "huodongtitle", property = "huodongtitle"),
            @Result(column = "huodongImg", property = "huodongImg"),
            @Result(column = "huodongshuoming", property = "huodongshuoming"),
            @Result(column = "startDatetime", property = "startDatetime"),
            @Result(column = "endDatetime", property = "endDatetime"),
            @Result(column = "huodongState", property = "huodongState"),
            @Result(column = "liulantimes", property = "liulantimes"),
            @Result(column = "fenxiangtimes", property = "fenxiangtimes"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_huodong_others"
            + "</script>")
    List<WscHuodongOthers> getAllList();
}