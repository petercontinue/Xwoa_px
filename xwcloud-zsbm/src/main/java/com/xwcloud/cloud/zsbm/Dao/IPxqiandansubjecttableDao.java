package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.buyKechengVo;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;

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

    //通过签单ID查询签单总额
    @Select("<script>" + "SELECT SUM(zongjia) FROM pxqiandansubjecttable WHERE qianDanInfoID=#{qianDanInfoID}" + "</script>")
    BigDecimal GetQiandanKechengzongjia(Long qianDanInfoID);

    @Select("<script>" + "SELECT * FROM pxqiandansubjecttable" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Pxqiandansubjecttable GetQiandansubjectZidingyi(@Param("ew") Wrapper wrapper);

    @Select("<script>" + "DELETE FROM pxqiandansubjecttable WHERE stuID=#{stuID} AND qiyeID=#{qiyeID}" + "</script>")
    Integer DeleteQiandanSubjectByStuID(Long stuID, Long qiyeID);

    @Select("<script>" + "SELECT * FROM pxqiandansubjecttable WHERE qianDanInfoID=#{qianDanInfoID}" + "</script>")
    List<Pxqiandansubjecttable> GetQiandanSubjectByQianDanID(Long qianDanInfoID);

    @Select("<script>" + "DELETE * FROM pxqiandansubjecttable WHERE qianDanInfoID=#{qianDanInfoID} AND qiyeID=#{qiyeID}" + "</script>")
    Integer DeleteQiandansubjectByQdID(Long qianDanInfoID, long qiyeID);

    /**
     * 查询签单对应的购买课程信息
     * @param qiandanID
     * @return
     */
    @Select("<script>" + "SELECT a.id,d.id AS buxiID,d.isShow AS isShow,c.kechengName AS kcName,e.buxiStyleName AS pxStyleName,(SELECT subjectName FROM pxsubjecttable WHERE id = c.subjectID)AS kmName,c.id AS kechengID, \n" +
            "c.buxiStyleID AS buxistykeID,c.id AS KCID,a.originalPrice AS YDJ,a.kechengprice AS DJ,a.buykeshiNum AS KS,(case when (SELECT keshiShu FROM pxkeshizengsongtable WHERE qiandanInfoID= a.qianDanInfoID AND kechengID= a.kechengID)is NULL then 0 else (SELECT keshiShu FROM pxkeshizengsongtable WHERE qiandanInfoID= a.qianDanInfoID AND kechengID= a.kechengID) end) AS ZKS,d.remainkeshi AS RKS,a.zongjia AS ZJ,a.discount AS ZK,a.startDate AS startDate,a.endDate AS endDate,c.jifeiStyleID AS jifeistyle,a.id AS qdsID\n" +
            "FROM pxqiandansubjecttable AS a \n" +
            "LEFT JOIN pxqiandaninfotable AS b ON a.qianDanInfoID = b.id \n" +
            "LEFT JOIN pxkechengtable AS c ON a.kechengID = c.id\n" +
            "LEFT JOIN pxbuxikechengtable AS d ON a.id = d.qianDanSubjectID\n" +
            "LEFT JOIN pxbuxistyletable AS e ON c.buxiStyleID = e.id WHERE a.qianDanInfoID = #{qiandanID}" + "</script>")
    List<buyKechengVo> GetQiandanKechengList(long qiandanID);
}