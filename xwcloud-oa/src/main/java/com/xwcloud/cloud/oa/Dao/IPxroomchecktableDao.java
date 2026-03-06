package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxroomchecktable;
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
public interface IPxroomchecktableDao extends BaseMapper<Pxroomchecktable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "RoomID", property = "roomID"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "stuState", property = "stuState"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "checkTime", property = "checkTime"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxroomchecktable"
            + "</script>")
    List<Pxroomchecktable> getAllList();
}