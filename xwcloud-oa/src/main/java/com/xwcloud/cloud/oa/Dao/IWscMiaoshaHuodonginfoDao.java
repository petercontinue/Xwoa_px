package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscMiaoshaHuodonginfo;
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
public interface IWscMiaoshaHuodonginfoDao extends BaseMapper<WscMiaoshaHuodonginfo> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "huodongtitle", property = "huodongtitle"),
                @Result(column = "huodongImg", property = "huodongImg"),
                @Result(column = "huodongshuoming", property = "huodongshuoming"),
                @Result(column = "liulanTimes", property = "liulanTimes"),
                @Result(column = "fenxiangTimes", property = "fenxiangTimes"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_miaosha_huodonginfo"
            + "</script>")
    List<WscMiaoshaHuodonginfo> getAllList();
}