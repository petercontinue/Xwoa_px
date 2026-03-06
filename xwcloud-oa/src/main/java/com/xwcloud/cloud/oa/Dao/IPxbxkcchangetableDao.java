package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxbxkcchangetable;
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
public interface IPxbxkcchangetableDao extends BaseMapper<Pxbxkcchangetable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "oldStuID", property = "oldStuID"),
                @Result(column = "oldbxkcID", property = "oldbxkcID"),
                @Result(column = "oldbxkcName", property = "oldbxkcName"),
                @Result(column = "oldkcID", property = "oldkcID"),
                @Result(column = "oldprice", property = "oldprice"),
                @Result(column = "oldrkeshi", property = "oldrkeshi"),
                @Result(column = "oldzongjia", property = "oldzongjia"),
                @Result(column = "oldstartDate", property = "oldstartDate"),
                @Result(column = "oldendDate", property = "oldendDate"),
                @Result(column = "oldqiandanID", property = "oldqiandanID"),
                @Result(column = "newStuID", property = "newStuID"),
                @Result(column = "newbxkcID", property = "newbxkcID"),
                @Result(column = "newbxkcName", property = "newbxkcName"),
                @Result(column = "newkcID", property = "newkcID"),
                @Result(column = "newprice", property = "newprice"),
                @Result(column = "newrkeshi", property = "newrkeshi"),
                @Result(column = "newzongjia", property = "newzongjia"),
                @Result(column = "newstartDate", property = "newstartDate"),
                @Result(column = "newendDate", property = "newendDate"),
                @Result(column = "newqiandanID", property = "newqiandanID"),
                @Result(column = "type", property = "type"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addDate", property = "addDate"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "shuoming", property = "shuoming"),
    })
    @Select("<script>" +
            "SELECT * from  pxbxkcchangetable"
            + "</script>")
    List<Pxbxkcchangetable> getAllList();
}