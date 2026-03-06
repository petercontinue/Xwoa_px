package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Tuifeishenpi;
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
public interface ITuifeishenpiDao extends BaseMapper<Tuifeishenpi> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "tuiqiandanID", property = "tuiqiandanID"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "spqiandanID", property = "spqiandanID"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "type", property = "type"),
                @Result(column = "yingtuiMoney", property = "yingtuiMoney"),
                @Result(column = "sqtuiMoney", property = "sqtuiMoney"),
                @Result(column = "youhuiMoney", property = "youhuiMoney"),
                @Result(column = "daijinquanMoney", property = "daijinquanMoney"),
                @Result(column = "tuifeibanlidate", property = "tuifeibanlidate"),
                @Result(column = "tuifeishuoming", property = "tuifeishuoming"),
                @Result(column = "sppayMoneystyle", property = "sppayMoneystyle"),
                @Result(column = "yejiren", property = "yejiren"),
                @Result(column = "chuliTime", property = "chuliTime"),
                @Result(column = "spUser", property = "spUser"),
                @Result(column = "spfinish", property = "spfinish"),
                @Result(column = "spshuoming", property = "spshuoming"),
                @Result(column = "adduser", property = "adduser"),
                @Result(column = "addTiem", property = "addTiem"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  tuifeishenpi"
            + "</script>")
    List<Tuifeishenpi> getAllList();
}