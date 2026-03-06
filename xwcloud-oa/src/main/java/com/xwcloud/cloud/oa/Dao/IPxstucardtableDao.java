package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstucardtable;
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
public interface IPxstucardtableDao extends BaseMapper<Pxstucardtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "cardNumber", property = "cardNumber"),
                @Result(column = "addDate", property = "addDate"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstucardtable"
            + "</script>")
    List<Pxstucardtable> getAllList();
}