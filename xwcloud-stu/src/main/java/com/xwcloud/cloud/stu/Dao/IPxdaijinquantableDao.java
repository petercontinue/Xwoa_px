package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.djqSumVo;
import com.xwcloud.cloud.model.entity.Pxdaijinquantable;
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
 * @since 2020-11-23
 */
@Repository
public interface IPxdaijinquantableDao extends BaseMapper<Pxdaijinquantable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "money", property = "money"),
            @Result(column = "creatTime", property = "creatTime"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxdaijinquantable"
            + "</script>")
    List<Pxdaijinquantable> getAllList();

    //获取学员代金券
    @Select("<script>" + "SELECT * from  pxdaijinquantable where stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxdaijinquantable> getstudjq(Long stuID,Long qiyeID);

    //获取学员的代金券总金额
    @Select("<script>" +
            "SELECT  ( CASE WHEN sum(money) IS NULL THEN 0 ELSE sum(money) END ) as SMoney " +
            "from  pxdaijinquantable where stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<djqSumVo> getstudjqSum(Long stuID,Long qiyeID);
}