package com.xwcloud.cloud.homeschool.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxzuoyestujiaotable;
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
public interface IPxzuoyestujiaotableDao extends BaseMapper<Pxzuoyestujiaotable> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "zuoyeID", property = "zuoyeID"),
            @Result(column = "jiaozuoyeDateTime", property = "jiaozuoyeDateTime"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "zuoyeImg", property = "zuoyeImg"),
            @Result(column = "zuoyeMp3", property = "zuoyeMp3"),
            @Result(column = "zuoyeVideo", property = "zuoyeVideo"),
            @Result(column = "otherFile", property = "otherFile"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  pxzuoyestujiaotable"
        + "</script>")
List<Pxzuoyestujiaotable> getAllList();
}