package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
@Repository
public interface IPxqiandansubjecttableDao extends BaseMapper<Pxqiandansubjecttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandandate", property = "qiandandate"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "kechengpriceYHQ", property = "kechengpriceYHQ"),
            @Result(column = "originalprice", property = "originalprice"),
            @Result(column = "buykeshiNum", property = "buykeshiNum"),
            @Result(column = "zongjia", property = "zongjia"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "qianDanInfoID", property = "qianDanInfoID"),
            @Result(column = "kechengStyle", property = "kechengStyle"),
            @Result(column = "discount", property = "discount"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandansubjecttable"
            + "</script>")
    List<Pxqiandansubjecttable> getAllList();

    /**
     * 获取学生签单科目
     */
    @Select("<script>" + "select * from pxqiandansubjecttable where stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxqiandansubjecttable> getqdSubject(Long stuID,Long qiyeID);

    //删除转送时获取签单科目
    @Select("<script>" + "select * pxqiandansubjecttable from where kechengStyle=#{kcSty} and stuID=#{stuID} and kechengID=#{kechengID} and buykeshiNum=#{buykeshiNum} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxqiandansubjecttable> getdelzsQdSub(int kcSty,Long stuID, Long kechengID, BigDecimal buykeshiNum,Long qiyeID);
}