package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxkeshizengsongtable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
public interface IPxkeshizengsongtableDao extends BaseMapper<Pxkeshizengsongtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengPrice", property = "kechengPrice"),
            @Result(column = "keshiShu", property = "keshiShu"),
            @Result(column = "caozuoStaffId", property = "caozuoStaffId"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "songYangyin", property = "songYangyin"),
            @Result(column = "JifeiStyle", property = "JifeiStyle"),
            @Result(column = "qiandanInfoID", property = "qiandanInfoID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshizengsongtable"
            + "</script>")
    List<Pxkeshizengsongtable> getAllList();

    @Select("<script>"+"SELECT * FROM pxkeshizengsongtable WHERE 1=1" +"<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxkeshizengsongtable> GetZengsongRecords(@Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT * FROM pxkeshizengsongtable WHERE qiandanInfoID=#{qiandanInfoID} AND kechengId=#{kechengId} AND kechengPrice=#{kechengPrice}  ORDER BY id LIMIT 0,1"+"</script>")
    Pxkeshizengsongtable GetZongSongInfo(Long qiandanInfoID, Long kechengId, BigDecimal kechengPrice);

    @Select("<script>"+"DELETE FROM pxkeshizengsongtable where stuID=#{stuID} AND qiyeID=#{qiyeID} "+"</script>")
    Integer DeleteKeshizengsongByStuId(Long stuID,long qiyeID);

    @Select("<script>"+"SELECT * FROM pxkeshizengsongtable WHERE qiandanInfoID=#{qiandanInfoID}"+"</script>")
    List<Pxkeshizengsongtable> GetZengsongkeshiByQiandanID(Long qiandanInfoID);

    @Select("<script>"+"DELETE FROM pxkeshizengsongtable where qiandanInfoID=#{qiandanInfoID} AND qiyeID=#{qiyeID}"+"</script>")
    Integer DeleteKeshizengsongByqiandanInfoID(Long qiandanInfoID,long qiyeID);
}