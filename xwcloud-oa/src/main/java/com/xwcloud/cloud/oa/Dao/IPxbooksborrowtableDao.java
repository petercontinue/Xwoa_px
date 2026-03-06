package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxbooksborrowtable;
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
public interface IPxbooksborrowtableDao extends BaseMapper<Pxbooksborrowtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "booksID", property = "booksID"),
                @Result(column = "people", property = "people"),
                @Result(column = "role", property = "role"),
                @Result(column = "borrownum", property = "borrownum"),
                @Result(column = "endDate", property = "endDate"),
                @Result(column = "dostaffID", property = "dostaffID"),
                @Result(column = "doDate", property = "doDate"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbooksborrowtable"
            + "</script>")
    List<Pxbooksborrowtable> getAllList();
}