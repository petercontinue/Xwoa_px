package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.buyKechengVo;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;

import org.apache.ibatis.annotations.Param;
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
 * @since 2020-11-11
 */
@Repository
public interface IPxbuxikechengtableDao extends BaseMapper<Pxbuxikechengtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "originalprice", property = "originalprice"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "kechengpriceYHQ", property = "kechengpriceYHQ"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "zongjia", property = "zongjia"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "buykeshiDateTime", property = "buykeshiDateTime"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "jifeiStyleID", property = "jifeiStyleID"),
            @Result(column = "type", property = "type"),
            @Result(column = "qianDanSubjectID", property = "qianDanSubjectID"),
            @Result(column = "qianDanInfoID", property = "qianDanInfoID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "autoKcQiehuanTags", property = "autoKcQiehuanTags"),
    })
    @Select("<script>" +
            "SELECT * from  pxbuxikechengtable"
            + "</script>")
    List<Pxbuxikechengtable> getAllList();


    @Select("<script>"+"SELECT * FROM pxbuxikechengtable "+ " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Pxbuxikechengtable GetZidingYiKecheng( @Param("ew") Wrapper wrapper);

    @Select("<script>"+"SELECT * FROM pxbuxikechengtable WHERE stuID=#{stuID} AND qiyeID=#{qiyeID} ORDER BY id LIMIT 0,1"+"</script>")
    List<Pxbuxikechengtable> GetBuxikchengByStuID(Long stuID,long qiyeID);

    @Select("<script>"+"SELECT * FROM pxbuxikechengtable WHERE stuID=#{stuID} AND kechengID=#{kechengID} AND qiyeID=#{qiyeID}  AND isShow = 1"+"</script>")
    List<Pxbuxikechengtable> GetStuBuxikechengList(Long stuID, Long kechengID,long qiyeID);

    @Select("<script>"+"SELECT * FROM pxbuxikechengtable WHERE stuID=#{stuID} AND kechengID=#{kechengID} AND kechengprice=#{kechengprice} AND qiyeID=#{qiyeID} AND type=2 ORDER BY id LIMIT 0,1"+"</script>")
    Pxbuxikechengtable GetBuxikecheng(Long stuID, Long kechengID, BigDecimal kechengprice,long qiyeID);

    @Select("<script>"+"SELECT * FROM pxbuxikechengtable WHERE stuID=#{stuID} AND kechengID=#{kechengID} AND kechengprice=#{kechengprice} AND qiyeID=#{qiyeID} AND type!=2 ORDER BY id LIMIT 0,1"+"</script>")
    Pxbuxikechengtable GetBuyBuxikecheng(Long stuID, Long kechengID, BigDecimal kechengprice,long qiyeID);

    @Select("<script>"+"DELETE FROM pxbuxikechengtable WHERE stuID=#{stuID} AND qiyeID=#{qiyeID}"+"</script>")
    Integer deleteBuxikechengByStuID(Long stuID,long qiyeID);

    @Select("<script>"+"SELECT * FROM pxbuxikechengtable WHERE stuID=#{stuID} AND id !=#{buxiID} AND qiyeID=#{qiyeID}"+"</script>")
    List<Pxbuxikechengtable> getbuxikechenglist(Long stuID,Long buxiID,long qiyeID);

    /**
     * 查询学生的所有课程信息（续费）
     * @param stuID
     * @return
     */
    @Select("<script>"+"SELECT a.id,d.id AS buxiID,d.isShow AS isShow,c.kechengName AS kcName,e.buxiStyleName AS pxStyleName,\n" +
            "(SELECT subjectName FROM pxsubjecttable WHERE id = c.subjectID)AS kmName,\n" +
            "c.id AS kechengID,c.buxiStyleID AS buxistykeID,c.id AS KCID,a.originalPrice AS YDJ,a.kechengprice AS DJ,0 AS KS,0 AS ZKS,\n" +
            "0 AS RKS,0 AS ZJ,a.discount AS ZK,a.startDate AS startDate,a.endDate AS endDate,c.jifeiStyleID AS jifeistyle,a.id AS qdsID\n" +
            "            FROM pxqiandansubjecttable AS a\n" +
            "            JOIN pxqiandaninfotable AS b ON a.qianDanInfoID = b.id\n" +
            "            JOIN pxkechengtable AS c ON a.kechengID = c.id\n" +
            "            JOIN pxbuxikechengtable AS d ON a.id = d.qianDanSubjectID\n" +
            "            JOIN pxbuxistyletable AS e ON c.buxiStyleID = e.id  WHERE a.stuID = #{stuID} AND a.qiyeID=#{qiyeID}"+"</script>")
    List<buyKechengVo> GetAllStukechengInfoList(long stuID, long qiyeID);


    /**
     * -------------------------------------意向学员------------------------------------------
     */

    /**
     * 查询对应学生的所有补习课程
     * @param stuID
     * @return
     */
    @Select("<script>" + "SELECT * FROM pxbuxikechengtable WHERE stuID=#{stuID}" + "</script>")
    List<Pxbuxikechengtable> getAllBuxikechengByStuID(Long stuID);

    /**
     * 通过学生id课程ID查询补习课程
     * @param stuID
     * @param kechnegID
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxbuxikechengtable WHERE stuID=#{stuID} AND kechengID=#{kechengID}"+"</script>")
    Pxbuxikechengtable getBuxikechengByStuIDAndkechengID(Long stuID,Long kechnegID);
}