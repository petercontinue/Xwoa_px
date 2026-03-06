package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxxuanketable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
public interface IPxxuanketableDao extends BaseMapper<Pxxuanketable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "paikeID", property = "paikeID"),
            @Result(column = "recordDate", property = "recordDate"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "type", property = "type"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxxuanketable"
            + "</script>")
    List<Pxxuanketable> getAllList();

    @Select("<script>" + "SELECT * FROM pxxuanketable WHERE stuID=#{stuID} AND paikeID = #{paikeID}" + "</script>")
    List<Pxxuanketable> GetXuankeByBuxiIDAndPaikeId(Long stuID, Long paikeID);

    @Delete("<script>" + "DELETE FROM pxxuanketable WHERE paikeID=#{paikeID}" + "</script>")
    Integer deleteXuanKeTable(Long paikeID);

    @Select("<script>" + "DELETE FROM pxxuanketable WHERE buxiID=#{bxID} AND stuID=#{stuID}" + "</script>")
    Integer deleteXuankeByBxIDAndStuID(Long bxID, Long stuID);

    @Select("<script>" + "SELECT * FROM pxxuanketable WHERE paikeID=#{paikeID}" + "</script>")
    List<Pxxuanketable> getAllXuankeByPaikeID(Long paikeID);

    @Select("<script>" + "SELECT * FROM pxxuanketable WHERE paikeID=#{paikeID} AND stuID=#{stuID}" + "</script>")
    List<Pxxuanketable> GetAllXuankeBypkIDAndStuIDList(Long paikeID, Long stuID);

    @Select("<script>" + "DELETE FROM pxxuanketable WHERE paikeID=#{paikeID} AND stuID=#{stuID}" + "</script>")
    Integer DeleteXuankeReords(Long paikeID, Long stuID);

    @Select("<script>" + "SELECT * FROM pxxuanketable WHERE buxiID=#{buxiID} " + "</script>")
    List<Pxxuanketable> GetXuankeByKehcnegID(Long kechengID);

    @Select("<script>" + "SELECT * FROM pxxuanketable WHERE paikeID = #{paikeID} " + "</script>")
    List<Pxxuanketable> GetXuankeByPaikeID(Long PaikeID);



}