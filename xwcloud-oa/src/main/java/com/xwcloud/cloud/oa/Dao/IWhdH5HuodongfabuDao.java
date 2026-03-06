package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdH5Huodongfabu;
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
public interface IWhdH5HuodongfabuDao extends BaseMapper<WhdH5Huodongfabu> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "mobanID", property = "mobanID"),
                @Result(column = "mbTypeID", property = "mbTypeID"),
                @Result(column = "zixunEwm", property = "zixunEwm"),
                @Result(column = "huodongTitle", property = "huodongTitle"),
                @Result(column = "huodongImage", property = "huodongImage"),
                @Result(column = "jigouName", property = "jigouName"),
                @Result(column = "huodongStartDateTime", property = "huodongStartDateTime"),
                @Result(column = "huodongEndDateTime", property = "huodongEndDateTime"),
                @Result(column = "maxStuNum", property = "maxStuNum"),
                @Result(column = "jigouTel", property = "jigouTel"),
                @Result(column = "musicID", property = "musicID"),
                @Result(column = "haveStuSex", property = "haveStuSex"),
                @Result(column = "haveAge", property = "haveAge"),
                @Result(column = "haveBirthday", property = "haveBirthday"),
                @Result(column = "haveSchool", property = "haveSchool"),
                @Result(column = "haveGrade", property = "haveGrade"),
                @Result(column = "haveYxkecheng", property = "haveYxkecheng"),
                @Result(column = "huodongShuoMing", property = "huodongShuoMing"),
                @Result(column = "lookNum", property = "lookNum"),
                @Result(column = "baomingStuNum", property = "baomingStuNum"),
                @Result(column = "fenxiangtimes", property = "fenxiangtimes"),
                @Result(column = "isfabu", property = "isfabu"),
                @Result(column = "fabuTime", property = "fabuTime"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "addUser", property = "addUser"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  whd_h5_huodongfabu"
            + "</script>")
    List<WhdH5Huodongfabu> getAllList();
}