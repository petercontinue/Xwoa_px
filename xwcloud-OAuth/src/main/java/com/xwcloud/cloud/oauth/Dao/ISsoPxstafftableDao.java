package com.xwcloud.cloud.oauth.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISsoPxstafftableDao extends BaseMapper<Pxstafftable> {
    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffName", property = "staffName"),
            @Result(column = "staffTel", property = "staffTel"),
            @Result(column = "password", property = "password"),
            @Result(column = "staffSex", property = "staffSex"),
            @Result(column = "staffBirthday", property = "staffBirthday"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "staffPostID", property = "staffPostID"),
            @Result(column = "staffState", property = "staffState"),
            @Result(column = "photo", property = "photo"),
            @Result(column = "QQ", property = "qq"),
            @Result(column = "email", property = "email"),
            @Result(column = "wx", property = "wx"),
            @Result(column = "douyin", property = "douyin"),
            @Result(column = "joinTime", property = "joinTime"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unionid", property = "unionid"),
            @Result(column = "phoneMac", property = "phoneMac"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstafftable"
            + "</script>")
    List<Pxstafftable> getAllList();
}
