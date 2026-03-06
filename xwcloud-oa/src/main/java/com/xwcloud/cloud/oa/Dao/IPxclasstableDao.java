package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxclasstable;
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
public interface IPxclasstableDao extends BaseMapper<Pxclasstable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "zidingyiClassID", property = "zidingyiClassID"),
                @Result(column = "className", property = "className"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "maxStuNum", property = "maxStuNum"),
                @Result(column = "is1v1Class", property = "is1v1Class"),
                @Result(column = "isdelete", property = "isdelete"),
                @Result(column = "isShow", property = "isShow"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "classState", property = "classState"),
    })
    @Select("<script>" +
            "SELECT * from  pxclasstable"
            + "</script>")
    List<Pxclasstable> getAllList();
}