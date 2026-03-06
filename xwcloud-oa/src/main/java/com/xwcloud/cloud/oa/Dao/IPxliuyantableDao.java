package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxliuyantable;
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
public interface IPxliuyantableDao extends BaseMapper<Pxliuyantable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "userName", property = "userName"),
                @Result(column = "tel", property = "tel"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "campusNames", property = "campusNames"),
                @Result(column = "yixiangText", property = "yixiangText"),
                @Result(column = "addDate", property = "addDate"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxliuyantable"
            + "</script>")
    List<Pxliuyantable> getAllList();
}