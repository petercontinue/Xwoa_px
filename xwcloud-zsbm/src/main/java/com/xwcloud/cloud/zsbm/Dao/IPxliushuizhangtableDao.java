package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.xinqianliushuiVo;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
@Repository
public interface IPxliushuizhangtableDao extends BaseMapper<Pxliushuizhangtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "liushuiDateTime", property = "liushuiDateTime"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "liushuiZaiyao", property = "liushuiZaiyao"),
            @Result(column = "payMoneyStyle", property = "payMoneyStyle"),
            @Result(column = "shouruMoney", property = "shouruMoney"),
            @Result(column = "zhichuMoney", property = "zhichuMoney"),
            @Result(column = "shouzhiStyleID", property = "shouzhiStyleID"),
            @Result(column = "jinbanRen", property = "jinbanRen"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "luruTime", property = "luruTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxliushuizhangtable"
            + "</script>")
    List<Pxliushuizhangtable> getAllList();

    @Select("<script>" + "SELECT * FROM pxliushuizhangtable WHERE qiandanID=#{qiandanID}" + "</script>")
    List<Pxliushuizhangtable> GetQiandanLiushuiList(Long qiandanID);

    @Delete("<script>" + "DELETE FROM pxliushuizhangtable WHERE qiandanID=#{qiandanID} AND qiyeID=#{qiyeID}" + "</script>")
    Integer DeleteLiushuiByQiandanID(Long qiandanID, long qiyeID);

    @Select("<script>" + "SELECT * FROM pxliushuizhangtable WHERE qiandanID=#{qiandanID} AND payMoneyStyle=#{payMoneyStyle}" + "</script>")
    Pxliushuizhangtable GetLiushuiByQdidAndPst(Long qiandanID, Long payMoneyStyle);

    @Select("<script>" + "DELETE FROM pxliushuizhangtable WHERE stuID=#{stuID} AND qiyeID=#{qiyeID}" + "</script>")
    Integer DeleteliushuiBystuID(Long stuID, Long qiyeID);

    @Select("<script>" + "DELETE * FROM pxliushuizhangtable WHERE stuID=#{stuID} AND qiandanID=#{qiandanID} AND qiyeID=#{qiyeID}" + "</script>")
    Integer deleteLiushuiByStuIdAndqiandanID(Long stuID, Long qiandanID, long qiyeID);

    @Select("<script>" +
            "select d.campusName,( CASE WHEN c.zidingyiStuID IS NOT NULL THEN c.zidingyiStuID ELSE c.id END ) stuID,\n" +
            "c.stuName,a.id liushuiID,f.moneystyleName,a.shouruMoney,a.liushuiZaiyao,a.liushuiDateTime\n" +
            "from pxliushuizhangtable a \n" +
            "LEFT JOIN pxqiandaninfotable b on a.qiandanID=b.id\n" +
            "LEFT JOIN pxstutable c on b.stuID=c.id\n" +
            "LEFT JOIN pxcampustable d on c.campusID=d.id\n" +
            "LEFT JOIN pxstafftable e on b.recordInStaffID=e.id\n" +
            "LEFT JOIN pxpaymoneystyletable f on a.payMoneyStyle=f.id\n" +
            "LEFT JOIN pxshouzhistyletable g on a.shouzhiStyleID=g.id\n" +
            "where d.isOpen !=2 and a.shouruMoney &gt; 0 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<xinqianliushuiVo> getqdliushuiList(@Param("ew") QueryWrapper queryWrapper);
}