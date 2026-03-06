package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxtuifeiteachsuppliestable;
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
public interface IPxtuifeiteachsuppliestableDao extends BaseMapper<Pxtuifeiteachsuppliestable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "qdsupID", property = "qdsupID"),
                @Result(column = "teachSuppliesID", property = "teachSuppliesID"),
                @Result(column = "teachSuppliesName", property = "teachSuppliesName"),
                @Result(column = "guige", property = "guige"),
                @Result(column = "tfqianRemainNums", property = "tfqianRemainNums"),
                @Result(column = "tuiNums", property = "tuiNums"),
                @Result(column = "tfhouRemainNums", property = "tfhouRemainNums"),
                @Result(column = "danwei", property = "danwei"),
                @Result(column = "price", property = "price"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "tuifeiID", property = "tuifeiID"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtuifeiteachsuppliestable"
            + "</script>")
    List<Pxtuifeiteachsuppliestable> getAllList();
}