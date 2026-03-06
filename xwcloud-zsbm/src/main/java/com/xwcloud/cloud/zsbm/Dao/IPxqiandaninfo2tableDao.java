package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.zaffeiListVo;
import com.xwcloud.cloud.model.entity.Pxqiandaninfo2table;
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
public interface IPxqiandaninfo2tableDao extends BaseMapper<Pxqiandaninfo2table> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qianDanOtherMoneyID", property = "qianDanOtherMoneyID"),
            @Result(column = "jiaoxueYonpingID", property = "jiaoxueYonpingID"),
            @Result(column = "onePrice", property = "onePrice"),
            @Result(column = "nums", property = "nums"),
            @Result(column = "zongMoney", property = "zongMoney"),
            @Result(column = "qianInfoTabID", property = "qianInfoTabID"),
            @Result(column = "type", property = "type"),
            @Result(column = "tuiMoney", property = "tuiMoney"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandaninfo2table"
            + "</script>")
    List<Pxqiandaninfo2table> getAllList();

    @Select("<script>" + "SELECT * FROM pxqiandaninfo2table WHERE qianInfoTabID = #{qianInfoTabID} AND qianDanOtherMoneyID=#{qianDanOtherMoneyID}" + "</script>")
    Pxqiandaninfo2table GetQiandanInfoByQiandanID(Long qianInfoTabID, Long qianDanOtherMoneyID);

    @Select("<script>" + "DELETE FROM pxqiandaninfo2table WHERE qianInfoTabID=#{qianInfoTabID}" + "</script>")
    Integer deleteQiandanInfo2ByQiandanID(Long qianInfoTabID);

    /**
     * 分页查询签单杂费流水
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT d.campusName,b.stuID as stuID,c.zidingyiStuID,c.stuName,e.stuGradeName,f.otherMoneyName,a.zongMoney,b.qiandandate " +
            "FROM pxqiandaninfo2table AS a \n" +
            "LEFT JOIN pxqiandaninfotable AS b ON a.qianInfoTabID=b.id\n" +
            "LEFT JOIN pxstutable AS c ON b.stuID=c.id\n" +
            "LEFT JOIN pxcampustable AS d ON c.campusID=d.id\n" +
            "LEFT JOIN pxstugradetable AS e ON c.stuGradeID = e.id\n" +
            "LEFT JOIN pxqiandanothermoneytable AS f ON a.qianDanOtherMoneyID = f.id" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<zaffeiListVo> GetQiandanOtherMoneyPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT d.campusName,b.stuID as stuID,c.zidingyiStuID,c.stuName,e.stuGradeName,f.otherMoneyName,a.zongMoney,b.qiandandate FROM pxqiandaninfo2table AS a \n" +
            "LEFT JOIN pxqiandaninfotable AS b ON a.qianInfoTabID=b.id\n" +
            "LEFT JOIN pxstutable AS c ON b.stuID=c.id\n" +
            "LEFT JOIN pxcampustable AS d ON c.campusID=d.id\n" +
            "LEFT JOIN pxstugradetable AS e ON c.stuGradeID = e.id\n" +
            "LEFT JOIN pxqiandanothermoneytable AS f ON a.qianDanOtherMoneyID = f.id" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" + "</script>")
    List<zaffeiListVo> GetQiandanOtherMoneyList(@Param("ew") Wrapper wrapper);


    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(a.zongMoney) is not null THEN SUM(a.zongMoney) ELSE 0 END )  from  pxqiandaninfo2table a " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    String getzf(@Param("ew") Wrapper wrapper);
}