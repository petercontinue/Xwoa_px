package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkechengcontenttable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
 */
@Repository
public interface IPxkechengcontenttableDao extends BaseMapper<Pxkechengcontenttable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "kechengContent", property = "kechengContent"),
                @Result(column = "contentPaixu", property = "contentPaixu"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "addStaffID", property = "addStaffID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkechengcontenttable"
            + "</script>")
    List<Pxkechengcontenttable> getAllList();
}