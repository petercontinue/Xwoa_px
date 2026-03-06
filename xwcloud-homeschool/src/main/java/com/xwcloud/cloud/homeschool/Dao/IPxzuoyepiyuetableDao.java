package com.xwcloud.cloud.homeschool.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxzuoyepiyuetable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxzuoyepiyuetableDao extends BaseMapper<Pxzuoyepiyuetable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "zuoyeStujiaoID", property = "zuoyeStujiaoID"),
            @Result(column = "piyueTeacherID", property = "piyueTeacherID"),
            @Result(column = "piyueContent", property = "piyueContent"),
            @Result(column = "piyueDatetime", property = "piyueDatetime"),
            @Result(column = "piyueImg", property = "piyueImg"),
            @Result(column = "piyueMp3", property = "piyueMp3"),
            @Result(column = "piyueVedio", property = "piyueVedio"),
            @Result(column = "piyueOtherFile", property = "piyueOtherFile"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  pxzuoyepiyuetable"
        + "</script>")
List<Pxzuoyepiyuetable> getAllList();
}