package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdToupiaoHuodong;
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
public interface IWhdToupiaoHuodongDao extends BaseMapper<WhdToupiaoHuodong> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "toupiaoHuodongName", property = "toupiaoHuodongName"),
                @Result(column = "miaoshu", property = "miaoshu"),
                @Result(column = "logo", property = "logo"),
                @Result(column = "toupiaoStyle", property = "toupiaoStyle"),
                @Result(column = "jiangpin", property = "jiangpin"),
                @Result(column = "rules", property = "rules"),
                @Result(column = "jigouJianjie", property = "jigouJianjie"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "startTime", property = "startTime"),
                @Result(column = "endTime", property = "endTime"),
                @Result(column = "isUp", property = "isUp"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_toupiao_huodong"
            + "</script>")
    List<WhdToupiaoHuodong> getAllList();
}