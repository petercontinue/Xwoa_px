package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxbooksouttable;
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
public interface IPxbooksouttableDao extends BaseMapper<Pxbooksouttable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "booksID", property = "booksID"),
                @Result(column = "outnum", property = "outnum"),
                @Result(column = "outstaffID", property = "outstaffID"),
                @Result(column = "outDate", property = "outDate"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbooksouttable"
            + "</script>")
    List<Pxbooksouttable> getAllList();
}