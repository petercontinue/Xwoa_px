package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.buyWpVo;
import com.xwcloud.cloud.model.Vo.shangpinliushuiVo;
import com.xwcloud.cloud.model.entity.Pxqiandansupplies;
import org.apache.ibatis.annotations.Param;
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
 * @since 2020-11-11
 */
@Repository
public interface IPxqiandansuppliesDao extends BaseMapper<Pxqiandansupplies> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "TeachingSuppliesID", property = "TeachingSuppliesID"),
            @Result(column = "Name", property = "Name"),
            @Result(column = "BuyPrice", property = "BuyPrice"),
            @Result(column = "BuySum", property = "BuySum"),
            @Result(column = "QiandaninfoID", property = "QiandaninfoID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "SumMoney", property = "SumMoney"),
            @Result(column = "IsTuiFei", property = "IsTuiFei"),
            @Result(column = "TuiMoney", property = "TuiMoney"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandansupplies"
            + "</script>")
    List<Pxqiandansupplies> getAllList();

    @Select("<script>" + "SELECT * FROM pxqiandansupplies WHERE QiandaninfoID=#{QiandaninfoID} AND TeachingSuppliesID=#{TeachingSuppliesID}" + "</script>")
    Pxqiandansupplies GetQiandanSuppliesByqdIDandSupID(Long QiandaninfoID, Long TeachingSuppliesID);

    @Select("<script>" + "DELETE * FROM pxqiandansupplies WHERE QiandaninfoID=#{QiandaninfoID}" + "</script>")
    int deleteqiandansuppliesByQiandanID(Long QiandaninfoID);

    /**
     * 分页查询签单商品流水
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "\n" +
            "SELECT d.campusName,b.stuID stuID,c.zidingyiStuID,c.stuName,e.stuGradeName,a.Name,a.BuySum,a.BuyPrice," +
            "a.SumMoney,b.qiandandate AS qiandandate FROM pxqiandansupplies AS a\n" +
            "LEFT JOIN pxqiandaninfotable AS b ON a.QiandaninfoID = b.id\n" +
            "LEFT JOIN pxstutable as c ON b.stuID = c.id\n" +
            "LEFT JOIN pxcampustable AS d ON c.campusID=d.id\n" +
            "LEFT JOIN pxstugradetable AS e ON  c.stuGradeID = e.id" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<shangpinliushuiVo> GetQiandanSuppliesPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" + "\n" +
            "SELECT d.campusName,c.zidingyiStuID,c.stuName,e.stuGradeName,a.Name,a.BuySum,a.BuyPrice,a.SumMoney,b.qiandandate FROM pxqiandansupplies AS a\n" +
            "JOIN pxqiandaninfotable AS b ON a.QiandaninfoID = b.id\n" +
            "JOIN pxstutable as c ON b.stuID = c.id\n" +
            "LEFT JOIN pxcampustable AS d ON c.campusID=d.id\n" +
            "LEFT JOIN pxstugradetable AS e ON  c.stuGradeID = e.id" + "</script>")
    List<shangpinliushuiVo> GetQiandanSuppliesList(@Param("ew") Wrapper wrapper);

    /**
     * 查询签单对应的签单物品信息
     * @param qiandanID
     * @return
     */
    @Select("<script>"+"SELECT a.TeachingSuppliesID AS id,a.Name AS wpName,a.BuyPrice AS wpDanjia,a.BuySum AS wpShuliang" +
            ",a.SumMoney AS wpZongjia,a.BuyPrice AS wpChushouJia from  pxqiandansupplies AS a WHERE a.QiandaninfoID =#{qiandanID}"+"</script>")
    List<buyWpVo> GetAllWupingList(long qiandanID);


    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(a.SumMoney) is not null THEN SUM(a.SumMoney) ELSE 0 END )  from  pxqiandansupplies a " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getWP(@Param("ew") Wrapper wrapper);
}