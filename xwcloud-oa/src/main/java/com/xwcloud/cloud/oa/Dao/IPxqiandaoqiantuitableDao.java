package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqiandaoqiantuitable;
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
public interface IPxqiandaoqiantuitableDao extends BaseMapper<Pxqiandaoqiantuitable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "qiandaoOrqiantui", property = "qiandaoOrqiantui"),
                @Result(column = "qianDatetime", property = "qianDatetime"),
                @Result(column = "qianStyle", property = "qianStyle"),
                @Result(column = "tsState", property = "tsState"),
                @Result(column = "paikeID", property = "paikeID"),
                @Result(column = "addstaffID", property = "addstaffID"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandaoqiantuitable"
            + "</script>")
    List<Pxqiandaoqiantuitable> getAllList();
}