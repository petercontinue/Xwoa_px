package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdToupiaoCansaistu;
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
public interface IWhdToupiaoCansaistuDao extends BaseMapper<WhdToupiaoCansaistu> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuName", property = "stuName"),
                @Result(column = "xuanYan", property = "xuanYan"),
                @Result(column = "introduction", property = "introduction"),
                @Result(column = "logo", property = "logo"),
                @Result(column = "lookTimes", property = "lookTimes"),
                @Result(column = "piaoshu", property = "piaoshu"),
                @Result(column = "bianhao", property = "bianhao"),
                @Result(column = "toupiaoHuodongID", property = "toupiaoHuodongID"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_toupiao_cansaistu"
            + "</script>")
    List<WhdToupiaoCansaistu> getAllList();
}