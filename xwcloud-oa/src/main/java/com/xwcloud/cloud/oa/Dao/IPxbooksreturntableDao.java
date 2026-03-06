package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxbooksreturntable;
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
public interface IPxbooksreturntableDao extends BaseMapper<Pxbooksreturntable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "booksID", property = "booksID"),
                @Result(column = "people", property = "people"),
                @Result(column = "role", property = "role"),
                @Result(column = "returnnum", property = "returnnum"),
                @Result(column = "returnDate", property = "returnDate"),
                @Result(column = "dostaffID", property = "dostaffID"),
                @Result(column = "doDate", property = "doDate"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbooksreturntable"
            + "</script>")
    List<Pxbooksreturntable> getAllList();
}