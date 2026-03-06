package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
 * @since 2020-11-17
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

    //根据排课ID删除
    @Select("<script>" + "DELETE FROM pxpaiketeachertable WHERE paikeID=#{paikeID}" + "</script>")
    int DeletePaikeTeacherByPaikeID(Long paikeID);


    //------------------------意向学员------------------------
    /**
     * 通过排课ID删除教师排课信息
     * @param paikeID
     * @return
     */
    @Select("<script>"+"DELETE FROM pxpaiketeachertable WHERE paikeID=#{paikeID}"+"</script>")
    List<Pxpaiketeachertable> DeleteAllPaikeTeacherBypaikeID(long paikeID);

    /**
     * 通过paikeID查询教师排课信息
     * @param paikeID
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxpaiketeachertable WHERE paikeID = #{paikeID}"+"</script>")
    List<Pxpaiketeachertable> getPaiketeacherByPaikeID(long paikeID);
}