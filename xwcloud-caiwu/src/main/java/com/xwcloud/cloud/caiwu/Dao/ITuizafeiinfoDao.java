package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Tuizafeiinfo;
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
 * @since 2021-04-08
 */
@Repository
public interface ITuizafeiinfoDao extends BaseMapper<Tuizafeiinfo> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandanInfo2ID", property = "qiandanInfo2ID"),
            @Result(column = "qiandanOtherID", property = "qiandanOtherID"),
            @Result(column = "tuizfmoney", property = "tuizfmoney"),
            @Result(column = "adduser", property = "adduser"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "tuifeispID", property = "tuifeispID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  tuizafeiinfo"
            + "</script>")
    List<Tuizafeiinfo> getAllList();
}