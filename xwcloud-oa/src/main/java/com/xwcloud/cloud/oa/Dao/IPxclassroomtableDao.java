package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxclassroomtable;
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
public interface IPxclassroomtableDao extends BaseMapper<Pxclassroomtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "classRoomName", property = "classRoomName"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "recordInStaffID", property = "recordInStaffID"),
                @Result(column = "recordInTime", property = "recordInTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "ischongtu", property = "ischongtu"),
    })
    @Select("<script>" +
            "SELECT * from  pxclassroomtable"
            + "</script>")
    List<Pxclassroomtable> getAllList();
}