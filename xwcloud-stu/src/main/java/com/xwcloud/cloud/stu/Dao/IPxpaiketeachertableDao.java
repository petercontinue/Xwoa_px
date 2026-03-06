package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.paiketeacherVo;
import com.xwcloud.cloud.model.entity.Pxpaiketeachertable;
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
 * @since 2020-11-16
 */
@Repository
public interface IPxpaiketeachertableDao extends BaseMapper<Pxpaiketeachertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "paikeID", property = "paikeID"),
            @Result(column = "teacherID", property = "teacherID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxpaiketeachertable"
            + "</script>")
    List<Pxpaiketeachertable> getAllList();

    //获取paiketeacherID
    @Select("<script>" +
            "select GROUP_CONCAT(id) as IDTS from pxpaiketeachertable  where paikeID=#{paikeID} and qiyeID=#{qiyeID}" +
            "</script>")
    paiketeacherVo getPtID(Long paikeID, Long qiyeID);

    /**
     * 按条件获取一个教师排课ID集合
     */
    @Select("<script>" + "select GROUP_CONCAT(id)as IDTS from pxpaiketeachertable where paikeID=#{paikeID} and qiyeID=#{qiyeID}" + "</script>")
    paiketeacherVo getTkTpaike(Long paikeID, Long qiyeID);

    @Select("<script>" + "select * IDTS from pxpaiketeachertable where paikeID=#{paikeID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxpaiketeachertable> getBypaikeID(Long paikeID, Long qiyeID);
}
