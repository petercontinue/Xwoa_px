package com.xwcloud.cloud.wsc.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.Vo.joinyuekeInfoVO;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabustutable;
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
 * @since 2021-05-17
 */
@Repository
public interface IPxyueketeacherfabustutableDao extends BaseMapper<Pxyueketeacherfabustutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "wxUserID", property = "wxUserID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "stuName", property = "stuName"),
            @Result(column = "telphone", property = "telphone"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "yuekeTeachFabuID", property = "yuekeTeachFabuID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "paystate", property = "paystate"),
            @Result(column = "paymoney", property = "paymoney"),
            @Result(column = "paytime", property = "paytime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyueketeacherfabustutable"
            + "</script>")
    List<Pxyueketeacherfabustutable> getAllList();

    @Select("<script>"+"SELECT * FROM pxyueketeacherfabustutable AS a LEFT JOIN pxstutable AS stu ON a.stuID = stu.id\n" +
            "LEFT JOIN pxwxusertable AS b ON a.wxUserID = b.id\n" +
            "WHERE a.yuekeTeachFabuID = #{yuekeID} AND a.qiyeID = #{qiyeID}"+"</script>")
    List<joinyuekeInfoVO> getJoinyuekeStuInfos(long yuekeID, long qiyeID);
}