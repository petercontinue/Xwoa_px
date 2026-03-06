package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdChoujiangHuodong;
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
public interface IWhdChoujiangHuodongDao extends BaseMapper<WhdChoujiangHuodong> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "choujiangHuodongName", property = "choujiangHuodongName"),
                @Result(column = "shuoming", property = "shuoming"),
                @Result(column = "jigouJianjie", property = "jigouJianjie"),
                @Result(column = "startTime", property = "startTime"),
                @Result(column = "endTime", property = "endTime"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "choujiangStyle", property = "choujiangStyle"),
                @Result(column = "cishu", property = "cishu"),
                @Result(column = "isUp", property = "isUp"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_choujiang_huodong"
            + "</script>")
    List<WhdChoujiangHuodong> getAllList();
}