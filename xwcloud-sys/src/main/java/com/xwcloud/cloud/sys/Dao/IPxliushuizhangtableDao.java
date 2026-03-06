package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.yejidataVO;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
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
 * @since 2021-07-01
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
            @Result(column = "orderNumber", property = "orderNumber"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "luruTime", property = "luruTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxliushuizhangtable"
            + "</script>")
    List<Pxliushuizhangtable> getAllList();

    /**
     * 查询校区业绩信息
     * @return
     */
    @Select("<script>" + "select b.campusName,(SUM(liushui.shouruMoney)-SUM(liushui.zhichuMoney)) as yejiMoney from pxliushuizhangtable AS liushui\n" +
            "LEFT JOIN pxcampustable AS b ON liushui.campusID = b.id \n" +
            "LEFT JOIN pxshouzhistyletable AS c ON liushui.shouzhiStyleID = c.id\n" +
            "WHERE c.shouzhiStyle LIKE(\"新签\") OR c.shouzhiStyle LIKE (\"续费\") OR c.shouzhiStyle LIKE (\"尾款\") OR c.shouzhiStyle LIKE (\"充值\")\n" +
            "GROUP BY liushui.campusID\n" + "</script>")
    List<yejidataVO> GetCampusYejiList();

    @Select("<script>"+"select SUM(a.shouruMoney-zhichuMoney) AS yeji from pxliushuizhangtable AS a LEFT JOIN pxshouzhistyletable AS b ON a.shouzhiStyleID = b.id \n" +
            "where  a.campusID = #{campusID} AND (b.shouzhiStyle LIKE('新签') OR b.shouzhiStyle LIKE ('续费') OR b.shouzhiStyle LIKE ('尾款') OR b.shouzhiStyle LIKE ('充值')) \n" +
            "AND YEAR(a.liushuiDatetime) = YEAR(#{date}) AND MONTH(a.liushuiDateTime) = MONTH(#{date}) AND DAY(a.liushuiDateTime) = DAY(#{date})\n" +
            "AND a.qiyeID= #{qiyeID}"+"</script>")
    BigDecimal GetDayYejiMoney(long qiyeID, long campusID, String date);

    @Select("<script>"+"SELECT SUM(keshistu.keshiNum*keshistu.kechengPrice) FROM pxkeshistutable AS keshistu LEFT JOIN pxcampustable AS campus ON keshistu.campusID = campus.id\n" +
            "WHERE keshistu.campusID=#{campusID} AND YEAR(keshistu.haveClassDate) = YEAR(#{date}) AND MONTH(keshistu.haveClassDate) = MONTH(#{date}) AND DAY(keshistu.haveClassDate) = DAY(#{date}) AND keshistu.qiyeID=#{qiyeID}"+"</script>")
    BigDecimal GetKehaoMoney(long qiyeID,long campusID,String date);
}