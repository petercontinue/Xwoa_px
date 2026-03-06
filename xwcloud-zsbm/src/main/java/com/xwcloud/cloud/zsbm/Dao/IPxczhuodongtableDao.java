package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxczhuodongtable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
@Repository
public interface IPxczhuodongtableDao extends BaseMapper<Pxczhuodongtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zongmoney", property = "zongmoney"),
            @Result(column = "huodongmoney", property = "huodongmoney"),
            @Result(column = "type", property = "type"),
            @Result(column = "Sdate", property = "Sdate"),
            @Result(column = "Edate", property = "Edate"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxczhuodongtable"
            + "</script>")
    List<Pxczhuodongtable> getAllList();

    /**
     * 查询可以使用的充值优惠政策
     * @param Date
     * @param qiyeID
     * @return
     */
    @Select("<script>"+"SELECT * FROM  pxczhuodongtable AS a  WHERE a.Sdate &lt;= #{Date} AND a.Edate &gt;= #{Date} AND a.type = 1 AND a.qiyeID =#{qiyeID}"+"</script>")
    List<Pxczhuodongtable> GetChongzhiuhuodongByDate(String Date,long qiyeID);



}