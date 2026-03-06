package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
import com.xwcloud.cloud.model.entity.Pxpaymoneystyletable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
@Repository
public interface IPxliushuizhangtableDao extends BaseMapper<Pxliushuizhangtable> {

    @Select("<script>" +
            "SELECT \n" +
            "            shouru.shouzhiStyleID,\n" +
            "            shouru.shouzhiStyle,\n" +
            "            shouru.campusID,\n" +
            "            shouru.tyear,\n" +
            "            SUM(shouru.JAN) AS JAN,\n" +
            "            SUM(shouru.FEB) AS FEB,\n" +
            "            SUM(shouru.MAR) AS MAR,\n" +
            "            SUM(shouru.APR) AS APR,\n" +
            "            SUM(shouru.MAY) AS MAY,\n" +
            "            SUM(shouru.JUN) AS JUN,\n" +
            "            SUM(shouru.JUL) AS JUL,\n" +
            "            SUM(shouru.AUG) AS AUG,\n" +
            "            SUM(shouru.SEP) AS SEP,\n" +
            "            SUM(shouru.OCT) AS OCT,\n" +
            "            SUM(shouru.NOV) AS NOV,\n" +
            "            SUM(shouru.DECE) AS DECE FROM(\n" +
            "SELECT \n" +
            "            tmp.shouzhiStyleID,\n" +
            "            tmp.shouzhiStyle,\n" +
            "            tmp.campusID,\n" +
            "            tmp.tyear,\n" +
            "            SUM(tmp.Jan) AS JAN,\n" +
            "            SUM(tmp.Feb) AS FEB,\n" +
            "            SUM(tmp.Mar) AS MAR,\n" +
            "            SUM(tmp.Apr) AS APR,\n" +
            "            SUM(tmp.May) AS MAY,\n" +
            "            SUM(tmp.Jun) AS JUN,\n" +
            "            SUM(tmp.Jul) AS JUL,\n" +
            "            SUM(tmp.Aug) AS AUG,\n" +
            "            SUM(tmp.Sep) AS SEP,\n" +
            "            SUM(tmp.Oct) AS OCT,\n" +
            "            SUM(tmp.Nov) AS NOV,\n" +
            "            SUM(tmp.Dece) AS DECE\n" +
            "            FROM \n" +
            "            (SELECT \n" +
            "            YEAR(liushui.liushuiDateTime) as tyear,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '1' then SUM(liushui.shouruMoney) else 0 end AS Jan ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '2' then SUM(liushui.shouruMoney) else 0 end AS Feb,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '3' then SUM(liushui.shouruMoney) else 0 end as Mar,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '4' then SUM(liushui.shouruMoney) else 0 end as Apr,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '5' then SUM(liushui.shouruMoney) else 0 end as May ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '6' then SUM(liushui.shouruMoney) else 0 end as Jun ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '7' then SUM(liushui.shouruMoney) else 0 end as Jul,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '8' then SUM(liushui.shouruMoney) else 0 end as Aug,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '9' then SUM(liushui.shouruMoney) else 0 end as Sep,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '10' then SUM(liushui.shouruMoney) else 0 end as Oct,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '11' then SUM(liushui.shouruMoney) else 0 end as Nov,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '12' then SUM(liushui.shouruMoney) else 0 end as Dece,\n" +
            "            liushui.shouzhiStyleID,\n" +
            "            shouzhistyle.shouzhiStyle,\n" +
            "            liushui.campusID \n" +
            "            FROM pxliushuizhangtable liushui \n" +
            "            LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID=shouzhistyle.id\n" +
            "            WHERE 1=1 AND shouzhistyle.isshouOrzhichu = '1'\n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND liushui.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(liushui.liushuiDateTime),MONTH(liushui.liushuiDateTime),liushui.shouzhiStyleID,liushui.campusID) as tmp \n" +
            "            GROUP BY tmp.tyear,tmp.shouzhiStyleID,tmp.campusID\n" +
            "            ORDER BY tmp.shouzhiStyleID ASC) shouru\n" +
            "WHERE shouru.shouzhiStyleID IN ('1','2')\n" +
            "            GROUP BY shouru.tyear,shouru.shouzhiStyleID"
            + "</script>")
    List<HashMap<String, Object>> getxinqianandxvqianList(@Param("year") String year, @Param("campusID") String campusID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT \n" +
            "            shouru.shouzhiStyleID,\n" +
            "            '新签加续签' as shouzhiStyle,\n" +
            "            shouru.campusID,\n" +
            "            shouru.tyear,\n" +
            "            SUM(shouru.JAN) AS JAN,\n" +
            "            SUM(shouru.FEB) AS FEB,\n" +
            "            SUM(shouru.MAR) AS MAR,\n" +
            "            SUM(shouru.APR) AS APR,\n" +
            "            SUM(shouru.MAY) AS MAY,\n" +
            "            SUM(shouru.JUN) AS JUN,\n" +
            "            SUM(shouru.JUL) AS JUL,\n" +
            "            SUM(shouru.AUG) AS AUG,\n" +
            "            SUM(shouru.SEP) AS SEP,\n" +
            "            SUM(shouru.OCT) AS OCT,\n" +
            "            SUM(shouru.NOV) AS NOV,\n" +
            "            SUM(shouru.DECE) AS DECE\n" +
            "            FROM\n" +
            "            (SELECT \n" +
            "            tmp.shouzhiStyleID,\n" +
            "            tmp.shouzhiStyle,\n" +
            "            tmp.campusID,\n" +
            "            tmp.tyear,\n" +
            "\t\t\tSUM(tmp.Jan) AS JAN,\n" +
            "            SUM(tmp.Feb) AS FEB,\n" +
            "            SUM(tmp.Mar) AS MAR,\n" +
            "            SUM(tmp.Apr) AS APR,\n" +
            "            SUM(tmp.May) AS MAY,\n" +
            "            SUM(tmp.Jun) AS JUN,\n" +
            "            SUM(tmp.Jul) AS JUL,\n" +
            "            SUM(tmp.Aug) AS AUG,\n" +
            "            SUM(tmp.Sep) AS SEP,\n" +
            "            SUM(tmp.Oct) AS OCT,\n" +
            "            SUM(tmp.Nov) AS NOV,\n" +
            "            SUM(tmp.Dece) AS DECE\n" +
            "            FROM \n" +
            "            (SELECT \n" +
            "            YEAR(liushui.liushuiDateTime) as tyear,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '1' then SUM(liushui.shouruMoney) else 0 end AS Jan ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '2' then SUM(liushui.shouruMoney) else 0 end AS Feb,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '3' then SUM(liushui.shouruMoney) else 0 end as Mar,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '4' then SUM(liushui.shouruMoney) else 0 end as Apr,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '5' then SUM(liushui.shouruMoney) else 0 end as May ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '6' then SUM(liushui.shouruMoney) else 0 end as Jun ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '7' then SUM(liushui.shouruMoney) else 0 end as Jul,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '8' then SUM(liushui.shouruMoney) else 0 end as Aug,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '9' then SUM(liushui.shouruMoney) else 0 end as Sep,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '10' then SUM(liushui.shouruMoney) else 0 end as Oct,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '11' then SUM(liushui.shouruMoney) else 0 end as Nov,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '12' then SUM(liushui.shouruMoney) else 0 end as Dece,\n" +
            "            liushui.shouzhiStyleID,\n" +
            "            shouzhistyle.shouzhiStyle,\n" +
            "            liushui.campusID\n" +
            "            FROM pxliushuizhangtable liushui \n" +
            "            LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID=shouzhistyle.id\n" +
            "            WHERE 1=1 AND shouzhistyle.isshouOrzhichu = '1'\n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND liushui.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(liushui.liushuiDateTime),MONTH(liushui.liushuiDateTime),liushui.shouzhiStyleID,liushui.campusID) as tmp \n" +
            "            GROUP BY tmp.tyear,tmp.shouzhiStyleID,tmp.campusID\n" +
            "            ORDER BY tmp.shouzhiStyleID ASC) shouru\n" +
            "            WHERE shouru.shouzhiStyleID IN ('1','2')\n" +
            "            GROUP BY shouru.tyear"
            + "</script>")
    List<HashMap<String, Object>> getxinqianandxvqianListSum(@Param("year") String year, @Param("campusID") String campusID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT \n" +
            "            shouru.shouzhiStyleID,\n" +
            "            shouru.shouzhiStyle,\n" +
            "            shouru.campusID,\n" +
            "            shouru.tyear,\n" +
            "            SUM(shouru.JAN) AS JAN,\n" +
            "            SUM(shouru.FEB) AS FEB,\n" +
            "            SUM(shouru.MAR) AS MAR,\n" +
            "            SUM(shouru.APR) AS APR,\n" +
            "            SUM(shouru.MAY) AS MAY,\n" +
            "            SUM(shouru.JUN) AS JUN,\n" +
            "            SUM(shouru.JUL) AS JUL,\n" +
            "            SUM(shouru.AUG) AS AUG,\n" +
            "            SUM(shouru.SEP) AS SEP,\n" +
            "            SUM(shouru.OCT) AS OCT,\n" +
            "            SUM(shouru.NOV) AS NOV,\n" +
            "            SUM(shouru.DECE) AS DECE\n" +
            "            FROM (SELECT \n" +
            "            tmp.shouzhiStyleID,\n" +
            "            tmp.shouzhiStyle,\n" +
            "            tmp.campusID,\n" +
            "            tmp.tyear,\n" +
            "\t\t\tSUM(tmp.Jan) AS JAN,\n" +
            "            SUM(tmp.Feb) AS FEB,\n" +
            "            SUM(tmp.Mar) AS MAR,\n" +
            "            SUM(tmp.Apr) AS APR,\n" +
            "            SUM(tmp.May) AS MAY,\n" +
            "            SUM(tmp.Jun) AS JUN,\n" +
            "            SUM(tmp.Jul) AS JUL,\n" +
            "            SUM(tmp.Aug) AS AUG,\n" +
            "            SUM(tmp.Sep) AS SEP,\n" +
            "            SUM(tmp.Oct) AS OCT,\n" +
            "            SUM(tmp.Nov) AS NOV,\n" +
            "            SUM(tmp.Dece) AS DECE\n" +
            "            FROM \n" +
            "            (SELECT \n" +
            "            YEAR(liushui.liushuiDateTime) as tyear,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '1' then SUM(liushui.shouruMoney) else 0 end AS Jan ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '2' then SUM(liushui.shouruMoney) else 0 end AS Feb,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '3' then SUM(liushui.shouruMoney) else 0 end as Mar,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '4' then SUM(liushui.shouruMoney) else 0 end as Apr,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '5' then SUM(liushui.shouruMoney) else 0 end as May ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '6' then SUM(liushui.shouruMoney) else 0 end as Jun ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '7' then SUM(liushui.shouruMoney) else 0 end as Jul,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '8' then SUM(liushui.shouruMoney) else 0 end as Aug,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '9' then SUM(liushui.shouruMoney) else 0 end as Sep,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '10' then SUM(liushui.shouruMoney) else 0 end as Oct,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '11' then SUM(liushui.shouruMoney) else 0 end as Nov,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '12' then SUM(liushui.shouruMoney) else 0 end as Dece,\n" +
            "            liushui.shouzhiStyleID,\n" +
            "            shouzhistyle.shouzhiStyle,\n" +
            "            liushui.campusID\n" +
            "            FROM pxliushuizhangtable liushui \n" +
            "            LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID=shouzhistyle.id\n" +
            "            WHERE 1=1 AND shouzhistyle.isshouOrzhichu = '1'\n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND liushui.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(liushui.liushuiDateTime),MONTH(liushui.liushuiDateTime),liushui.shouzhiStyleID,liushui.campusID) as tmp \n" +
            "            GROUP BY tmp.tyear,tmp.shouzhiStyleID,tmp.campusID\n" +
            "            ORDER BY tmp.shouzhiStyleID ASC) shouru\n" +
            "            WHERE shouru.shouzhiStyleID NOT IN ('1','2')\n" +
            "            GROUP BY shouru.tyear,shouru.shouzhiStyleID"
            + "</script>")
    List<HashMap<String, Object>> getxinqianandxvqianListother(@Param("year") String year, @Param("campusID") String campusID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT \n" +
            "            shouru.shouzhiStyleID,\n" +
            "            '收入大合计' as shouzhiStyle,\n" +
            "            shouru.campusID,\n" +
            "            shouru.tyear,\n" +
            "            SUM(shouru.JAN) AS JAN,\n" +
            "            SUM(shouru.FEB) AS FEB,\n" +
            "            SUM(shouru.MAR) AS MAR,\n" +
            "            SUM(shouru.APR) AS APR,\n" +
            "            SUM(shouru.MAY) AS MAY,\n" +
            "            SUM(shouru.JUN) AS JUN,\n" +
            "            SUM(shouru.JUL) AS JUL,\n" +
            "            SUM(shouru.AUG) AS AUG,\n" +
            "            SUM(shouru.SEP) AS SEP,\n" +
            "            SUM(shouru.OCT) AS OCT,\n" +
            "            SUM(shouru.NOV) AS NOV,\n" +
            "            SUM(shouru.DECE) AS DECE\n" +
            "            FROM (SELECT \n" +
            "            tmp.shouzhiStyleID,\n" +
            "            tmp.shouzhiStyle,\n" +
            "            tmp.campusID,\n" +
            "            tmp.tyear,\n" +
            "\t\t\tSUM(tmp.Jan) AS JAN,\n" +
            "            SUM(tmp.Feb) AS FEB,\n" +
            "            SUM(tmp.Mar) AS MAR,\n" +
            "            SUM(tmp.Apr) AS APR,\n" +
            "            SUM(tmp.May) AS MAY,\n" +
            "            SUM(tmp.Jun) AS JUN,\n" +
            "            SUM(tmp.Jul) AS JUL,\n" +
            "            SUM(tmp.Aug) AS AUG,\n" +
            "            SUM(tmp.Sep) AS SEP,\n" +
            "            SUM(tmp.Oct) AS OCT,\n" +
            "            SUM(tmp.Nov) AS NOV,\n" +
            "            SUM(tmp.Dece) AS DECE\n" +
            "            FROM \n" +
            "            (SELECT \n" +
            "            YEAR(liushui.liushuiDateTime) as tyear,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '1' then SUM(liushui.shouruMoney) else 0 end AS Jan ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '2' then SUM(liushui.shouruMoney) else 0 end AS Feb,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '3' then SUM(liushui.shouruMoney) else 0 end as Mar,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '4' then SUM(liushui.shouruMoney) else 0 end as Apr,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '5' then SUM(liushui.shouruMoney) else 0 end as May ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '6' then SUM(liushui.shouruMoney) else 0 end as Jun ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '7' then SUM(liushui.shouruMoney) else 0 end as Jul,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '8' then SUM(liushui.shouruMoney) else 0 end as Aug,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '9' then SUM(liushui.shouruMoney) else 0 end as Sep,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '10' then SUM(liushui.shouruMoney) else 0 end as Oct,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '11' then SUM(liushui.shouruMoney) else 0 end as Nov,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '12' then SUM(liushui.shouruMoney) else 0 end as Dece,\n" +
            "            liushui.shouzhiStyleID,\n" +
            "            shouzhistyle.shouzhiStyle,\n" +
            "            liushui.campusID\n" +
            "            FROM pxliushuizhangtable liushui \n" +
            "            LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID=shouzhistyle.id\n" +
            "            WHERE 1=1 AND shouzhistyle.isshouOrzhichu = '1'\n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND liushui.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(liushui.liushuiDateTime),MONTH(liushui.liushuiDateTime),liushui.shouzhiStyleID,liushui.campusID) as tmp \n" +
            "            GROUP BY tmp.tyear,tmp.shouzhiStyleID,tmp.campusID\n" +
            "            ORDER BY tmp.shouzhiStyleID ASC) shouru\n" +
            "            GROUP BY shouru.tyear"
            + "</script>")
    List<HashMap<String, Object>> getShourudahejiList(@Param("year") String year, @Param("campusID") String campusID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT \n" +
            "            zhichu.shouzhiStyleID,\n" +
            "            zhichu.shouzhiStyle,\n" +
            "            zhichu.campusID,\n" +
            "            zhichu.tyear,\n" +
            "            SUM(zhichu.JAN) AS JAN,\n" +
            "            SUM(zhichu.FEB) AS FEB,\n" +
            "            SUM(zhichu.MAR) AS MAR,\n" +
            "            SUM(zhichu.APR) AS APR,\n" +
            "            SUM(zhichu.MAY) AS MAY,\n" +
            "            SUM(zhichu.JUN) AS JUN,\n" +
            "            SUM(zhichu.JUL) AS JUL,\n" +
            "            SUM(zhichu.AUG) AS AUG,\n" +
            "            SUM(zhichu.SEP) AS SEP,\n" +
            "            SUM(zhichu.OCT) AS OCT,\n" +
            "            SUM(zhichu.NOV) AS NOV,\n" +
            "            SUM(zhichu.DECE) AS DECE\n" +
            "            FROM (SELECT \n" +
            "            tmp.shouzhiStyleID,\n" +
            "            tmp.shouzhiStyle,\n" +
            "            tmp.campusID,\n" +
            "            tmp.tyear,\n" +
            "            SUM(tmp.Jan) AS JAN,\n" +
            "            SUM(tmp.Feb) AS FEB,\n" +
            "            SUM(tmp.Mar) AS MAR,\n" +
            "            SUM(tmp.Apr) AS APR,\n" +
            "            SUM(tmp.May) AS MAY,\n" +
            "            SUM(tmp.Jun) AS JUN,\n" +
            "            SUM(tmp.Jul) AS JUL,\n" +
            "\t\t\tSUM(tmp.Aug) AS AUG,\n" +
            "            SUM(tmp.Sep) AS SEP,\n" +
            "            SUM(tmp.Oct) AS OCT,\n" +
            "            SUM(tmp.Nov) AS NOV,\n" +
            "            SUM(tmp.Dece) AS DECE\n" +
            "            FROM \n" +
            "            (SELECT \n" +
            "            YEAR(liushui.liushuiDateTime) as tyear,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '1' then SUM(liushui.zhichuMoney) else 0 end AS Jan ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '2' then SUM(liushui.zhichuMoney) else 0 end AS Feb,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '3' then SUM(liushui.zhichuMoney) else 0 end as Mar,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '4' then SUM(liushui.zhichuMoney) else 0 end as Apr,\n" +
            "\t\t\tcase MONTH(liushui.liushuiDateTime) when '5' then SUM(liushui.zhichuMoney) else 0 end as May ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '6' then SUM(liushui.zhichuMoney) else 0 end as Jun ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '7' then SUM(liushui.zhichuMoney) else 0 end as Jul,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '8' then SUM(liushui.zhichuMoney) else 0 end as Aug,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '9' then SUM(liushui.zhichuMoney) else 0 end as Sep,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '10' then SUM(liushui.zhichuMoney) else 0 end as Oct,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '11' then SUM(liushui.zhichuMoney) else 0 end as Nov,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '12' then SUM(liushui.zhichuMoney) else 0 end as Dece,\n" +
            "            liushui.shouzhiStyleID,\n" +
            "            shouzhistyle.shouzhiStyle,\n" +
            "            liushui.campusID\n" +
            "            FROM pxliushuizhangtable liushui \n" +
            "            LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID=shouzhistyle.id\n" +
            "            WHERE 1=1 AND shouzhistyle.isshouOrzhichu = '2'\n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND liushui.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(liushui.liushuiDateTime),MONTH(liushui.liushuiDateTime),liushui.shouzhiStyleID,liushui.campusID) as tmp \n" +
            "            GROUP BY tmp.tyear,tmp.shouzhiStyleID,tmp.campusID\n" +
            "            ORDER BY tmp.shouzhiStyleID ASC) zhichu\n" +
            "            GROUP BY zhichu.tyear,zhichu.shouzhiStyleID"
            + "</script>")
    List<HashMap<String, Object>> getZhichuList(@Param("year") String year, @Param("campusID") String campusID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "            SELECT \n" +
            "            zhichu.shouzhiStyleID,\n" +
            "            \"支出大合计\" as shouzhiStyle,\n" +
            "            zhichu.campusID,\n" +
            "            zhichu.tyear,\n" +
            "            SUM(zhichu.JAN) AS JAN,\n" +
            "            SUM(zhichu.FEB) AS FEB,\n" +
            "            SUM(zhichu.MAR) AS MAR,\n" +
            "            SUM(zhichu.APR) AS APR,\n" +
            "            SUM(zhichu.MAY) AS MAY,\n" +
            "            SUM(zhichu.JUN) AS JUN,\n" +
            "            SUM(zhichu.JUL) AS JUL,\n" +
            "            SUM(zhichu.AUG) AS AUG,\n" +
            "            SUM(zhichu.SEP) AS SEP,\n" +
            "            SUM(zhichu.OCT) AS OCT,\n" +
            "            SUM(zhichu.NOV) AS NOV,\n" +
            "            SUM(zhichu.DECE) AS DECE\n" +
            "            FROM\n" +
            "            (SELECT \n" +
            "            tmp.shouzhiStyleID,\n" +
            "            tmp.shouzhiStyle,\n" +
            "            tmp.campusID,\n" +
            "            tmp.tyear,\n" +
            "            SUM(tmp.Jan) AS JAN,\n" +
            "            SUM(tmp.Feb) AS FEB,\n" +
            "            SUM(tmp.Mar) AS MAR,\n" +
            "            SUM(tmp.Apr) AS APR,\n" +
            "            SUM(tmp.May) AS MAY,\n" +
            "            SUM(tmp.Jun) AS JUN,\n" +
            "            SUM(tmp.Jul) AS JUL,\n" +
            "\t\t\tSUM(tmp.Aug) AS AUG,\n" +
            "            SUM(tmp.Sep) AS SEP,\n" +
            "            SUM(tmp.Oct) AS OCT,\n" +
            "            SUM(tmp.Nov) AS NOV,\n" +
            "            SUM(tmp.Dece) AS DECE\n" +
            "            FROM \n" +
            "            (SELECT \n" +
            "            YEAR(liushui.liushuiDateTime) as tyear,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '1' then SUM(liushui.zhichuMoney) else 0 end AS Jan ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '2' then SUM(liushui.zhichuMoney) else 0 end AS Feb,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '3' then SUM(liushui.zhichuMoney) else 0 end as Mar,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '4' then SUM(liushui.zhichuMoney) else 0 end as Apr,\n" +
            "\t\t\tcase MONTH(liushui.liushuiDateTime) when '5' then SUM(liushui.zhichuMoney) else 0 end as May ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '6' then SUM(liushui.zhichuMoney) else 0 end as Jun ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '7' then SUM(liushui.zhichuMoney) else 0 end as Jul,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '8' then SUM(liushui.zhichuMoney) else 0 end as Aug,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '9' then SUM(liushui.zhichuMoney) else 0 end as Sep,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '10' then SUM(liushui.zhichuMoney) else 0 end as Oct,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '11' then SUM(liushui.zhichuMoney) else 0 end as Nov,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '12' then SUM(liushui.zhichuMoney) else 0 end as Dece,\n" +
            "            liushui.shouzhiStyleID,\n" +
            "            shouzhistyle.shouzhiStyle,\n" +
            "            liushui.campusID\n" +
            "            FROM pxliushuizhangtable liushui \n" +
            "            LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID=shouzhistyle.id\n" +
            "            WHERE 1=1 AND shouzhistyle.isshouOrzhichu = '2'\n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND liushui.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(liushui.liushuiDateTime),MONTH(liushui.liushuiDateTime),liushui.shouzhiStyleID,liushui.campusID) as tmp \n" +
            "            GROUP BY tmp.tyear,tmp.shouzhiStyleID,tmp.campusID\n" +
            "            ORDER BY tmp.shouzhiStyleID ASC) zhichu\n" +
            "            GROUP BY zhichu.tyear"
            + "</script>")
    List<HashMap<String, Object>> getZhichudaheji(@Param("year") String year, @Param("campusID") String campusID, @Param("qiyeID") Long qiyeID);


    @Select("<script>" +
            "SELECT \n" +
            "            \"1\" AS shouzhiStyleID,\n" +
            "            \"课时收入\" as shouzhiStyle,\n" +
            "            keshi.campusID,\n" +
            "            keshi.tyear,\n" +
            "            SUM(keshi.JAN) AS JAN,\n" +
            "            SUM(keshi.FEB) AS FEB,\n" +
            "            SUM(keshi.MAR) AS MAR,\n" +
            "            SUM(keshi.APR) AS APR,\n" +
            "            SUM(keshi.MAY) AS MAY,\n" +
            "            SUM(keshi.JUN) AS JUN,\n" +
            "            SUM(keshi.JUL) AS JUL,\n" +
            "            SUM(keshi.AUG) AS AUG,\n" +
            "            SUM(keshi.SEP) AS SEP,\n" +
            "            SUM(keshi.OCT) AS OCT,\n" +
            "            SUM(keshi.NOV) AS NOV,\n" +
            "            SUM(keshi.DECE) AS DECE\n" +
            "            FROM\n" +
            "            (SELECT \n" +
            "            YEAR(haveClassDate) as tyear,\n" +
            "            campusID,\n" +
            "            case MONTH(haveClassDate) when '1' then SUM((keshiNum*kechengPrice)) else 0 end AS Jan ,\n" +
            "            case MONTH(haveClassDate) when '2' then SUM((keshiNum*kechengPrice)) else 0 end AS Feb,\n" +
            "            case MONTH(haveClassDate) when '3' then SUM((keshiNum*kechengPrice)) else 0 end as Mar,\n" +
            "            case MONTH(haveClassDate) when '4' then SUM((keshiNum*kechengPrice)) else 0 end as Apr,\n" +
            "            case MONTH(haveClassDate) when '5' then SUM((keshiNum*kechengPrice)) else 0 end as May ,\n" +
            "            case MONTH(haveClassDate) when '6' then SUM((keshiNum*kechengPrice)) else 0 end as Jun ,\n" +
            "            case MONTH(haveClassDate) when '7' then SUM((keshiNum*kechengPrice)) else 0 end as Jul,\n" +
            "            case MONTH(haveClassDate) when '8' then SUM((keshiNum*kechengPrice)) else 0 end as Aug,\n" +
            "            case MONTH(haveClassDate) when '9' then SUM((keshiNum*kechengPrice)) else 0 end as Sep,\n" +
            "            case MONTH(haveClassDate) when '10' then SUM((keshiNum*kechengPrice)) else 0 end as Oct,\n" +
            "            case MONTH(haveClassDate) when '11' then SUM((keshiNum*kechengPrice)) else 0 end as Nov,\n" +
            "            case MONTH(haveClassDate) when '12' then SUM((keshiNum*kechengPrice)) else 0 end as Dece\n" +
            "            FROM pxkeshistutable\n" +
            "            WHERE 1=1 \n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(haveClassDate) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(haveClassDate),MONTH(haveClassDate),campusID) keshi\n" +
            "            GROUP BY keshi.tyear"
            + "</script>")
    List<HashMap<String, Object>> getKeshishouru(@Param("year") String year, @Param("campusID") String campusID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT tmpzhichu.shouzhiStyleID,\n" +
            "            tmpzhichu.shouzhiStyle,\n" +
            "            tmpzhichu.campusID,\n" +
            "            tmpzhichu.tyear,\n" +
            "            tmpshouru.JAN-tmpzhichu.JAN AS JAN,\n" +
            "            tmpshouru.FEB-tmpzhichu.FEB AS FEB,\n" +
            "            tmpshouru.MAR-tmpzhichu.MAR AS MAR,\n" +
            "            tmpshouru.APR-tmpzhichu.APR AS APR,\n" +
            "            tmpshouru.MAY-tmpzhichu.MAY AS MAY,\n" +
            "            tmpshouru.JUN-tmpzhichu.JUN AS JUN,\n" +
            "            tmpshouru.JUL-tmpzhichu.JUL AS JUL,\n" +
            "            tmpshouru.AUG-tmpzhichu.AUG AS AUG,\n" +
            "            tmpshouru.SEP-tmpzhichu.SEP AS SEP,\n" +
            "            tmpshouru.OCT-tmpzhichu.OCT AS OCT,\n" +
            "            tmpshouru.NOV-tmpzhichu.NOV AS NOV,\n" +
            "            tmpshouru.DECE-tmpzhichu.DECE AS DECE FROM\n" +
            "            (SELECT \n" +
            "            \"1\" AS shouzhiStyleID,\n" +
            "            \"现金流\" as shouzhiStyle,\n" +
            "            zhichu.campusID,\n" +
            "            zhichu.tyear,\n" +
            "            SUM(zhichu.JAN) AS JAN,\n" +
            "            SUM(zhichu.FEB) AS FEB,\n" +
            "            SUM(zhichu.MAR) AS MAR,\n" +
            "            SUM(zhichu.APR) AS APR,\n" +
            "            SUM(zhichu.MAY) AS MAY,\n" +
            "            SUM(zhichu.JUN) AS JUN,\n" +
            "            SUM(zhichu.JUL) AS JUL,\n" +
            "            SUM(zhichu.AUG) AS AUG,\n" +
            "            SUM(zhichu.SEP) AS SEP,\n" +
            "            SUM(zhichu.OCT) AS OCT,\n" +
            "            SUM(zhichu.NOV) AS NOV,\n" +
            "            SUM(zhichu.DECE) AS DECE\n" +
            "            FROM\n" +
            "            (SELECT \n" +
            "            tmp.shouzhiStyleID,\n" +
            "            tmp.shouzhiStyle,\n" +
            "            tmp.campusID,\n" +
            "            tmp.tyear,\n" +
            "            SUM(tmp.Jan) AS JAN,\n" +
            "            SUM(tmp.Feb) AS FEB,\n" +
            "            SUM(tmp.Mar) AS MAR,\n" +
            "            SUM(tmp.Apr) AS APR,\n" +
            "            SUM(tmp.May) AS MAY,\n" +
            "            SUM(tmp.Jun) AS JUN,\n" +
            "            SUM(tmp.Jul) AS JUL,\n" +
            "\t\t\tSUM(tmp.Aug) AS AUG,\n" +
            "            SUM(tmp.Sep) AS SEP,\n" +
            "            SUM(tmp.Oct) AS OCT,\n" +
            "            SUM(tmp.Nov) AS NOV,\n" +
            "            SUM(tmp.Dece) AS DECE\n" +
            "            FROM \n" +
            "            (SELECT \n" +
            "            YEAR(liushui.liushuiDateTime) as tyear,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '1' then SUM(liushui.zhichuMoney) else 0 end AS Jan ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '2' then SUM(liushui.zhichuMoney) else 0 end AS Feb,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '3' then SUM(liushui.zhichuMoney) else 0 end as Mar,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '4' then SUM(liushui.zhichuMoney) else 0 end as Apr,\n" +
            "\t\t\tcase MONTH(liushui.liushuiDateTime) when '5' then SUM(liushui.zhichuMoney) else 0 end as May ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '6' then SUM(liushui.zhichuMoney) else 0 end as Jun ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '7' then SUM(liushui.zhichuMoney) else 0 end as Jul,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '8' then SUM(liushui.zhichuMoney) else 0 end as Aug,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '9' then SUM(liushui.zhichuMoney) else 0 end as Sep,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '10' then SUM(liushui.zhichuMoney) else 0 end as Oct,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '11' then SUM(liushui.zhichuMoney) else 0 end as Nov,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '12' then SUM(liushui.zhichuMoney) else 0 end as Dece,\n" +
            "            liushui.shouzhiStyleID,\n" +
            "            shouzhistyle.shouzhiStyle,\n" +
            "            liushui.campusID\n" +
            "            FROM pxliushuizhangtable liushui \n" +
            "            LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID=shouzhistyle.id\n" +
            "            WHERE 1=1 AND shouzhistyle.isshouOrzhichu = '2'\n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND liushui.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(liushui.liushuiDateTime),MONTH(liushui.liushuiDateTime),liushui.shouzhiStyleID,liushui.campusID) as tmp \n" +
            "            GROUP BY tmp.tyear,tmp.shouzhiStyleID,tmp.campusID\n" +
            "            ORDER BY tmp.shouzhiStyleID ASC) zhichu\n" +
            "            GROUP BY zhichu.tyear\n" +
            "            ) AS tmpzhichu\n" +
            "            LEFT JOIN (SELECT \n" +
            "            \"1\" AS shouzhiStyleID,\n" +
            "            \"现金流\" as shouzhiStyle,\n" +
            "            shouru.campusID,\n" +
            "            shouru.tyear,\n" +
            "            SUM(shouru.JAN) AS JAN,\n" +
            "            SUM(shouru.FEB) AS FEB,\n" +
            "            SUM(shouru.MAR) AS MAR,\n" +
            "            SUM(shouru.APR) AS APR,\n" +
            "            SUM(shouru.MAY) AS MAY,\n" +
            "            SUM(shouru.JUN) AS JUN,\n" +
            "            SUM(shouru.JUL) AS JUL,\n" +
            "            SUM(shouru.AUG) AS AUG,\n" +
            "            SUM(shouru.SEP) AS SEP,\n" +
            "            SUM(shouru.OCT) AS OCT,\n" +
            "            SUM(shouru.NOV) AS NOV,\n" +
            "            SUM(shouru.DECE) AS DECE\n" +
            "            FROM\n" +
            "            (SELECT \n" +
            "            tmp.shouzhiStyleID,\n" +
            "            tmp.shouzhiStyle,\n" +
            "            tmp.campusID,\n" +
            "            tmp.tyear,\n" +
            "\t\t\tSUM(tmp.Jan) AS JAN,\n" +
            "            SUM(tmp.Feb) AS FEB,\n" +
            "            SUM(tmp.Mar) AS MAR,\n" +
            "            SUM(tmp.Apr) AS APR,\n" +
            "            SUM(tmp.May) AS MAY,\n" +
            "            SUM(tmp.Jun) AS JUN,\n" +
            "            SUM(tmp.Jul) AS JUL,\n" +
            "            SUM(tmp.Aug) AS AUG,\n" +
            "            SUM(tmp.Sep) AS SEP,\n" +
            "            SUM(tmp.Oct) AS OCT,\n" +
            "            SUM(tmp.Nov) AS NOV,\n" +
            "            SUM(tmp.Dece) AS DECE\n" +
            "            FROM \n" +
            "            (SELECT \n" +
            "            YEAR(liushui.liushuiDateTime) as tyear,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '1' then SUM(liushui.shouruMoney) else 0 end AS Jan ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '2' then SUM(liushui.shouruMoney) else 0 end AS Feb,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '3' then SUM(liushui.shouruMoney) else 0 end as Mar,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '4' then SUM(liushui.shouruMoney) else 0 end as Apr,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '5' then SUM(liushui.shouruMoney) else 0 end as May ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '6' then SUM(liushui.shouruMoney) else 0 end as Jun ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '7' then SUM(liushui.shouruMoney) else 0 end as Jul,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '8' then SUM(liushui.shouruMoney) else 0 end as Aug,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '9' then SUM(liushui.shouruMoney) else 0 end as Sep,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '10' then SUM(liushui.shouruMoney) else 0 end as Oct,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '11' then SUM(liushui.shouruMoney) else 0 end as Nov,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '12' then SUM(liushui.shouruMoney) else 0 end as Dece,\n" +
            "            liushui.shouzhiStyleID,\n" +
            "            shouzhistyle.shouzhiStyle,\n" +
            "            liushui.campusID\n" +
            "            FROM pxliushuizhangtable liushui \n" +
            "            LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID=shouzhistyle.id\n" +
            "            WHERE 1=1 AND shouzhistyle.isshouOrzhichu = '1'\n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND liushui.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(liushui.liushuiDateTime),MONTH(liushui.liushuiDateTime),liushui.shouzhiStyleID,liushui.campusID) as tmp \n" +
            "            GROUP BY tmp.tyear,tmp.shouzhiStyleID,tmp.campusID\n" +
            "            ORDER BY tmp.shouzhiStyleID ASC) shouru\n" +
            "            GROUP BY shouru.tyear\n" +
            "            ) as tmpshouru ON tmpzhichu.tyear=tmpshouru.tyear\n" +
            "            GROUP BY tmpzhichu.tyear"
            + "</script>")
    List<HashMap<String, Object>> getxianjinliu(@Param("year") String year, @Param("campusID") String campusID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT tmpzhichu.shouzhiStyleID,\n" +
            "            \"利润\" as shouzhiStyle,\n" +
            "            tmpzhichu.campusID,\n" +
            "            tmpzhichu.tyear,\n" +
            "            keshishouru.JAN-tmpzhichu.JAN AS JAN,\n" +
            "            keshishouru.FEB-tmpzhichu.FEB AS FEB,\n" +
            "            keshishouru.MAR-tmpzhichu.MAR AS MAR,\n" +
            "            keshishouru.APR-tmpzhichu.APR AS APR,\n" +
            "            keshishouru.MAY-tmpzhichu.MAY AS MAY,\n" +
            "            keshishouru.JUN-tmpzhichu.JUN AS JUN,\n" +
            "            keshishouru.JUL-tmpzhichu.JUL AS JUL,\n" +
            "            keshishouru.AUG-tmpzhichu.AUG AS AUG,\n" +
            "            keshishouru.SEP-tmpzhichu.SEP AS SEP,\n" +
            "            keshishouru.OCT-tmpzhichu.OCT AS OCT,\n" +
            "            keshishouru.NOV-tmpzhichu.NOV AS NOV,\n" +
            "            keshishouru.DECE-tmpzhichu.DECE AS DECE\n" +
            "FROM (\n" +
            "SELECT \n" +
            "            \"1\" AS shouzhiStyleID,\n" +
            "            \"课时收入\" as shouzhiStyle,\n" +
            "            keshi.campusID,\n" +
            "            keshi.tyear,\n" +
            "            SUM(keshi.JAN) AS JAN,\n" +
            "            SUM(keshi.FEB) AS FEB,\n" +
            "            SUM(keshi.MAR) AS MAR,\n" +
            "            SUM(keshi.APR) AS APR,\n" +
            "            SUM(keshi.MAY) AS MAY,\n" +
            "            SUM(keshi.JUN) AS JUN,\n" +
            "            SUM(keshi.JUL) AS JUL,\n" +
            "            SUM(keshi.AUG) AS AUG,\n" +
            "            SUM(keshi.SEP) AS SEP,\n" +
            "            SUM(keshi.OCT) AS OCT,\n" +
            "            SUM(keshi.NOV) AS NOV,\n" +
            "            SUM(keshi.DECE) AS DECE\n" +
            "            FROM\n" +
            "            (SELECT \n" +
            "            YEAR(haveClassDate) as tyear,\n" +
            "            campusID,\n" +
            "            case MONTH(haveClassDate) when '1' then SUM((keshiNum*kechengPrice)) else 0 end AS Jan ,\n" +
            "            case MONTH(haveClassDate) when '2' then SUM((keshiNum*kechengPrice)) else 0 end AS Feb,\n" +
            "            case MONTH(haveClassDate) when '3' then SUM((keshiNum*kechengPrice)) else 0 end as Mar,\n" +
            "            case MONTH(haveClassDate) when '4' then SUM((keshiNum*kechengPrice)) else 0 end as Apr,\n" +
            "            case MONTH(haveClassDate) when '5' then SUM((keshiNum*kechengPrice)) else 0 end as May ,\n" +
            "            case MONTH(haveClassDate) when '6' then SUM((keshiNum*kechengPrice)) else 0 end as Jun ,\n" +
            "            case MONTH(haveClassDate) when '7' then SUM((keshiNum*kechengPrice)) else 0 end as Jul,\n" +
            "            case MONTH(haveClassDate) when '8' then SUM((keshiNum*kechengPrice)) else 0 end as Aug,\n" +
            "            case MONTH(haveClassDate) when '9' then SUM((keshiNum*kechengPrice)) else 0 end as Sep,\n" +
            "            case MONTH(haveClassDate) when '10' then SUM((keshiNum*kechengPrice)) else 0 end as Oct,\n" +
            "            case MONTH(haveClassDate) when '11' then SUM((keshiNum*kechengPrice)) else 0 end as Nov,\n" +
            "            case MONTH(haveClassDate) when '12' then SUM((keshiNum*kechengPrice)) else 0 end as Dece\n" +
            "            FROM pxkeshistutable\n" +
            "            WHERE 1=1 \n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(haveClassDate) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(haveClassDate),MONTH(haveClassDate),campusID) keshi\n" +
            "            GROUP BY keshi.tyear) keshishouru,\n" +
            "(SELECT \n" +
            "            zhichu.shouzhiStyleID,\n" +
            "            \"支出大合计\" as shouzhiStyle,\n" +
            "            zhichu.campusID,\n" +
            "            zhichu.tyear,\n" +
            "            SUM(zhichu.JAN) AS JAN,\n" +
            "            SUM(zhichu.FEB) AS FEB,\n" +
            "            SUM(zhichu.MAR) AS MAR,\n" +
            "            SUM(zhichu.APR) AS APR,\n" +
            "            SUM(zhichu.MAY) AS MAY,\n" +
            "            SUM(zhichu.JUN) AS JUN,\n" +
            "            SUM(zhichu.JUL) AS JUL,\n" +
            "            SUM(zhichu.AUG) AS AUG,\n" +
            "            SUM(zhichu.SEP) AS SEP,\n" +
            "            SUM(zhichu.OCT) AS OCT,\n" +
            "            SUM(zhichu.NOV) AS NOV,\n" +
            "            SUM(zhichu.DECE) AS DECE\n" +
            "            FROM\n" +
            "            (SELECT \n" +
            "            tmp.shouzhiStyleID,\n" +
            "            tmp.shouzhiStyle,\n" +
            "            tmp.campusID,\n" +
            "            tmp.tyear,\n" +
            "            SUM(tmp.Jan) AS JAN,\n" +
            "            SUM(tmp.Feb) AS FEB,\n" +
            "            SUM(tmp.Mar) AS MAR,\n" +
            "            SUM(tmp.Apr) AS APR,\n" +
            "            SUM(tmp.May) AS MAY,\n" +
            "            SUM(tmp.Jun) AS JUN,\n" +
            "            SUM(tmp.Jul) AS JUL,\n" +
            "\t\t\tSUM(tmp.Aug) AS AUG,\n" +
            "            SUM(tmp.Sep) AS SEP,\n" +
            "            SUM(tmp.Oct) AS OCT,\n" +
            "            SUM(tmp.Nov) AS NOV,\n" +
            "            SUM(tmp.Dece) AS DECE\n" +
            "            FROM \n" +
            "            (SELECT \n" +
            "            YEAR(liushui.liushuiDateTime) as tyear,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '1' then SUM(liushui.zhichuMoney) else 0 end AS Jan ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '2' then SUM(liushui.zhichuMoney) else 0 end AS Feb,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '3' then SUM(liushui.zhichuMoney) else 0 end as Mar,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '4' then SUM(liushui.zhichuMoney) else 0 end as Apr,\n" +
            "\t\t\tcase MONTH(liushui.liushuiDateTime) when '5' then SUM(liushui.zhichuMoney) else 0 end as May ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '6' then SUM(liushui.zhichuMoney) else 0 end as Jun ,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '7' then SUM(liushui.zhichuMoney) else 0 end as Jul,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '8' then SUM(liushui.zhichuMoney) else 0 end as Aug,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '9' then SUM(liushui.zhichuMoney) else 0 end as Sep,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '10' then SUM(liushui.zhichuMoney) else 0 end as Oct,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '11' then SUM(liushui.zhichuMoney) else 0 end as Nov,\n" +
            "            case MONTH(liushui.liushuiDateTime) when '12' then SUM(liushui.zhichuMoney) else 0 end as Dece,\n" +
            "            liushui.shouzhiStyleID,\n" +
            "            shouzhistyle.shouzhiStyle,\n" +
            "            liushui.campusID\n" +
            "            FROM pxliushuizhangtable liushui \n" +
            "            LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID=shouzhistyle.id\n" +
            "            WHERE 1=1 AND shouzhistyle.isshouOrzhichu = '2'\n" +
            "<if test=\"year != null and  year != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            " AND liushui.campusID = #{campusID} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "            GROUP BY YEAR(liushui.liushuiDateTime),MONTH(liushui.liushuiDateTime),liushui.shouzhiStyleID,liushui.campusID) as tmp \n" +
            "            GROUP BY tmp.tyear,tmp.shouzhiStyleID,tmp.campusID\n" +
            "            ORDER BY tmp.shouzhiStyleID ASC) zhichu\n" +
            "            GROUP BY zhichu.tyear) tmpzhichu"
            + "</script>")
    List<HashMap<String, Object>> getLirun(@Param("year") String year, @Param("campusID") String campusID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT liushui.campusID,campus.campusName,(SUM(liushui.shouruMoney)-SUM(liushui.zhichuMoney)) as yeji," +
            "YEAR(liushui.liushuiDateTime) as yearstr,liushui.shouzhiStyleID " +
            "FROM pxliushuizhangtable liushui " +
            "LEFT JOIN pxcampustable campus ON campus.id=liushui.campusID  " +
            "WHERE 1=1" +
            "<if test=\"yearstr != null and  yearstr != '' \">" +
            " AND YEAR(liushui.liushuiDateTime) = #{yearstr} " +
            "</if>" +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "</if>" +
            "AND liushui.shouzhiStyleID IN('1','2','3','7') " +
            "GROUP BY liushui.campusID " +
            "ORDER BY yeji DESC"
            + "</script>")
    Page<HashMap<String, String>> getCampusYearPage(Page page, @Param("yearstr") String yearstr, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT liushui.campusID,campus.campusName,(SUM(liushui.shouruMoney)-SUM(liushui.zhichuMoney)) as yeji," +
            "DATE_FORMAT(liushui.liushuiDateTime,'%Y-%m') as liushuidate,liushui.shouzhiStyleID " +
            "FROM pxliushuizhangtable liushui " +
            "LEFT JOIN pxcampustable campus ON campus.id=liushui.campusID  " +
            "WHERE 1=1 " +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            "<![CDATA[" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "]]>" +
            "</if>" +
            "<if test=\"yearstr != null and  yearstr != '' \">" +
            "<![CDATA[" +
            " AND YEAR(liushui.liushuiDateTime) = #{yearstr} " +
            "]]>" +
            "</if>" +
            "<if test=\"monthstr != null and  monthstr != '' \">" +
            "<![CDATA[" +
            "AND MONTH(liushui.liushuiDateTime) = #{monthstr} " +
            "]]>" +
            "</if>" +
            "AND liushui.shouzhiStyleID IN('1','2','3','7') " +
            "GROUP BY liushui.campusID " +
            "ORDER BY yeji DESC"
            + "</script>")
    Page<HashMap<String, String>> getCampusMonthPage(Page page, @Param("yearstr") String yearstr, @Param("monthstr") String monthstr, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT liushui.*,DATE_FORMAT(liushui.liushuiDateTime,'%Y-%m-%d %H:%i:%S') as liushuidate,DATE_FORMAT(liushui.luruTime,'%Y-%m-%d %H:%i:%S') as luruTimea,paystyle.moneystyleName,shouzhistyle.shouzhiStyle FROM pxliushuizhangtable liushui \n" +
            "LEFT JOIN pxpaymoneystyletable paystyle ON liushui.PayMoneyStyle=paystyle.id\n" +
            "LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID = shouzhistyle.id\n" +
            "LEFT JOIN pxqiandanstafftable qdstaff ON qdstaff.qiandanID = liushui.qiandanID\n" +
            " WHERE 1=1 " +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            "<![CDATA[" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "]]>" +
            "</if>" +
            "<if test=\"year != null and  year != '' \">" +
            "<![CDATA[" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "]]>" +
            "</if>" +
            "<if test=\"month != null and  month != '' \">" +
            "<![CDATA[" +
            " AND MONTH(liushui.liushuiDateTime) = #{month} " +
            "]]>" +
            "</if>" +
            "<if test=\"staffID != null and  staffID != '' \">" +
            "<![CDATA[" +
            " AND qdstaff.staffID = #{staffID}\n" +
            "]]>" +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            "<![CDATA[" +
            " AND liushui.campusID = #{campusID}" +
            "]]>" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getGerenMonthDetailPage(Page page, @Param("year") String year, @Param("month") String month,
                                                          @Param("staffID") String staffID, @Param("campusID") String campusID,
                                                          @Param("qiyeID") Long qiyeID
    );

    @Select("<script>" +
            "SELECT liushui.*,DATE_FORMAT(liushui.liushuiDateTime,'%Y-%m-%d %H:%i:%S') as liushuidate," +
            "DATE_FORMAT(liushui.luruTime,'%Y-%m-%d %H:%i:%S') as luruTimea," +
            "paystyle.moneystyleName,shouzhistyle.shouzhiStyle,campus.campusName," +
            "stu.stuName,stu.zidingyiStuID,grade.stuGradeName, staff.staffName as yejirenname\n" +
            "FROM pxliushuizhangtable liushui \n" +
            "LEFT JOIN pxpaymoneystyletable paystyle ON liushui.PayMoneyStyle=paystyle.id \n" +
            "LEFT JOIN pxshouzhistyletable shouzhistyle ON liushui.shouzhiStyleID = shouzhistyle.id \n" +
            "LEFT JOIN pxqiandanstafftable qdstaff ON qdstaff.qiandanID = liushui.qiandanID \n" +
            "LEFT JOIN pxcampustable campus ON campus.id=liushui.campusID\n" +
            "LEFT JOIN pxstutable stu ON stu.id= liushui.stuID\n" +
            "LEFT JOIN pxstugradetable grade ON grade.id=stu.stuGradeID\n" +
            "LEFT JOIN pxstafftable staff ON qdstaff.staffID=staff.id " +
            " WHERE 1=1 " +
            "<if test=\"qiyeID != null and  qiyeID != '' \">" +
            "<![CDATA[" +
            " AND liushui.qiyeID = #{qiyeID} " +
            "]]>" +
            "</if>" +
            "<if test=\"year != null and  year != '' \">" +
            "<![CDATA[" +
            " AND YEAR(liushui.liushuiDateTime) = #{year} " +
            "]]>" +
            "</if>" +
            "<if test=\"month != null and  month != '' \">" +
            "<![CDATA[" +
            " AND MONTH(liushui.liushuiDateTime) = #{month} " +
            "]]>" +
            "</if>" +
            "<if test=\"staffID != null and  staffID != '' \">" +
            "<![CDATA[" +
            " AND qdstaff.staffID = #{staffID}\n" +
            "]]>" +
            "</if>" +
            "<if test=\"campusID != null and  campusID != '' \">" +
            "<![CDATA[" +
            " AND liushui.campusID = #{campusID}" +
            "]]>" +
            "</if>"
            + "</script>")
    List<HashMap<String, Object>> getGerenMonthDetailList(@Param("year") String year, @Param("month") String month,
                                                          @Param("staffID") String staffID, @Param("campusID") String campusID,
                                                          @Param("qiyeID") Long qiyeID
    );

    @Select("<script>" +
            "SELECT qdsub.*,campus.campusName,stu.stuName,kecheng.kechengName,sub.subjectName " +
            "FROM pxliushuizhangtable liushui \n" +
            "LEFT JOIN pxqiandansubjecttable qdsub ON liushui.qiandanID = qdsub.qianDanInfoID \n" +
            "LEFT JOIN pxcampustable campus ON liushui.campusID = campus.id \n" +
            "LEFT JOIN pxstutable stu ON qdsub.stuID = stu.id \n" +
            "LEFT JOIN pxkechengtable kecheng ON qdsub.kechengID=kecheng.id\n" +
            "LEFT JOIN pxsubjecttable sub ON kecheng.subjectID = sub.id" +
            "<if test='ew != null'>" +
            " where 1=1 AND " +
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getDetailedIncomeDetailsPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT qdinfo2.*,other.otherMoneyName\n" +
            "FROM pxliushuizhangtable liushui\n" +
            "LEFT JOIN pxqiandaninfo2table qdinfo2 ON liushui.qiandanID = qdinfo2.qianInfoTabID \n" +
            "LEFT JOIN pxqiandanothermoneytable other ON qdinfo2.qianDanOtherMoneyID = other.id" +
            "<if test='ew != null'>" +
            " where 1=1 AND " +
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getDetailedZafeiDetailsPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT qdsupplies.*\n" +
            "FROM pxliushuizhangtable liushui\n" +
            "LEFT JOIN pxqiandansupplies qdsupplies ON liushui.qiandanID = qdsupplies.QiandaninfoID " +
            "<if test='ew != null'>" +
            " where 1=1 AND " +
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getDetailedShangpingDetailsPage(Page page, @Param("ew") Wrapper wrapper);


    @Select("<script>" +
            "SELECT tmp.campusName,SUM(tmp.zaidu) as zaidu,SUM(tmp.ergy) AS ergy,SUM(tmp.swgy) AS swgy,SUM(tmp.liugy) AS liugy," +
            "SUM(tmp.remainXuefei) as remainXuefei\n" +
            "FROM \n" +
            "(SELECT\n" +
            "campus.campusName,\n" +
            "campus.id as campusID,\n" +
            "<![CDATA[" +
            "case stu.buxiStateID when 2 then stu.remainXuefei else 0 end AS zaidu,\n" +
            "IF(stu.buxiStateID=3 AND ((PERIOD_DIFF(DATE_FORMAT(NOW(),'%Y%m') ,DATE_FORMAT(stu.tingkeTime,'%Y%m'))+1)>0 AND (PERIOD_DIFF(DATE_FORMAT(NOW(),'%Y%m') ,DATE_FORMAT(stu.tingkeTime,'%Y%m'))+1)<=2),stu.remainXuefei,0) as ergy,\n" +
            "IF(stu.buxiStateID=3 AND ((PERIOD_DIFF(DATE_FORMAT(NOW(),'%Y%m') ,DATE_FORMAT(stu.tingkeTime,'%Y%m'))+1)>2 AND (PERIOD_DIFF(DATE_FORMAT(NOW(),'%Y%m') ,DATE_FORMAT(stu.tingkeTime,'%Y%m'))+1)<=5),stu.remainXuefei,0) as swgy,\n" +
            "IF(stu.buxiStateID=3 AND ((PERIOD_DIFF(DATE_FORMAT(NOW(),'%Y%m') ,DATE_FORMAT(stu.tingkeTime,'%Y%m'))+1)>5 ),stu.remainXuefei,0) as liugy,\n" +
            "stu.remainXuefei\n" +
            "]]>" +
            "FROM pxstutable stu\n" +
            "JOIN pxcampustable campus ON stu.campusID=campus.id WHERE 1=1 AND stu.qiyeID = #{qiyeID} ) AS tmp\n" +
            "GROUP BY tmp.campusID"
            + "</script>")
    Page<HashMap<String, String>> getKucunxuefei(Page page, @Param("qiyeID") Long qiyeID);


    @Select("<script>" +
            "SELECT liushui.*,DATE_FORMAT(liushui.liushuiDateTime,'%Y-%m-%d %H:%i:%s') as liushuiDateTimef," +
            "DATE_FORMAT(liushui.luruTime,'%Y-%m-%d %H:%i:%s') as luruTimef," +
            "campus.campusName,liushui.payMoneyStyle,paystyle.moneystyleName,shouzhi.shouzhiStyle," +
            "staff.staffName, jinbanrenstaff.staffName as jinbanrenName " +
            "FROM pxliushuizhangtable liushui \n" +
            "LEFT JOIN pxcampustable campus ON campus.id = liushui.campusID\n" +
            "LEFT JOIN pxpaymoneystyletable paystyle ON liushui.payMoneyStyle = paystyle.id\n" +
            "LEFT JOIN pxshouzhistyletable shouzhi ON liushui.shouzhiStyleID = shouzhi.id\n" +
            "LEFT JOIN pxstafftable staff ON liushui.addStaffID = staff.id " +
            "LEFT JOIN pxstafftable jinbanrenstaff ON liushui.jinbanRen = jinbanrenstaff.id" +
            "<if test='ew != null'>" +
            " where 1=1 AND " +
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getPage(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT liushui.*,DATE_FORMAT(liushui.liushuiDateTime,'%Y-%m-%d %H:%i:%s') as liushuiDateTimef," +
            "DATE_FORMAT(liushui.luruTime,'%Y-%m-%d %H:%i:%s') as luruTimef," +
            "campus.campusName,paystyle.moneystyleName,shouzhi.shouzhiStyle," +
            "staff.staffName, jinbanrenstaff.staffName as jinbanrenName " +
            "FROM pxliushuizhangtable liushui \n" +
            "LEFT JOIN pxcampustable campus ON campus.id = liushui.campusID\n" +
            "LEFT JOIN pxpaymoneystyletable paystyle ON liushui.payMoneyStyle = paystyle.id\n" +
            "LEFT JOIN pxshouzhistyletable shouzhi ON liushui.shouzhiStyleID = shouzhi.id\n" +
            "LEFT JOIN pxstafftable staff ON liushui.addStaffID = staff.id " +
            "LEFT JOIN pxstafftable jinbanrenstaff ON liushui.jinbanRen = jinbanrenstaff.id" +
            "<if test='ew != null'>" +
            " where 1=1 AND " +
            " ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, Object>> getLiushuiList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT * FROM (\n" +
            "SELECT * FROM (SELECT ('${ym}-01' - INTERVAL DAY('${eYm}-01') DAY) + INTERVAL s DAY AS days FROM(" +
            "SELECT 31 AS s UNION ALL SELECT 30 UNION ALL SELECT 29 UNION ALL SELECT 28 UNION ALL SELECT 27 UNION ALL " +
            "SELECT 26 UNION ALL SELECT 25 UNION ALL SELECT 24 UNION ALL " +
            "SELECT 23 UNION ALL SELECT 22 UNION ALL SELECT 21 UNION ALL " +
            "SELECT 20 UNION ALL SELECT 19 UNION ALL SELECT 18 UNION ALL " +
            "SELECT 17 UNION ALL SELECT 16 UNION ALL SELECT 15 UNION ALL " +
            "SELECT 14 UNION ALL SELECT 13 UNION ALL SELECT 12 UNION ALL " +
            "SELECT 11 UNION ALL SELECT 10 UNION ALL SELECT 09 UNION ALL " +
            "SELECT 08 UNION ALL SELECT 07 UNION ALL SELECT 06 UNION ALL " +
            "SELECT 05 UNION ALL SELECT 04 UNION ALL SELECT 03 UNION ALL " +
            "SELECT 02 UNION ALL SELECT 01) daytable) as dayt " +
            "LEFT JOIN (SELECT liushui2.liushuiDate,SUM(liushui2.zhichuMoney) as zhichuMoney,SUM(liushui2.shouruMoney) as shouruMoney " +
            "FROM ( SELECT DATE_FORMAT(liushui.liushuiDateTime,'%Y-%m-%d') as liushuiDate,zhichuMoney,shouruMoney " +
            "FROM pxliushuizhangtable liushui where liushui.qiyeID = #{qiyeID} ) as liushui2 GROUP BY liushui2.liushuiDate) AS liushuizhang " +
            "ON dayt.days=liushuizhang.liushuiDate) as daytable WHERE DATE_FORMAT(daytable.days,'%Y-%m') = '${ym}' ORDER BY daytable.days asc"
            + "</script>")
    List<HashMap<String, String>> getLiushuiDay(@Param("ym") String Ym, @Param("eYm") String eYm, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT * FROM (" +
            "SELECT * FROM \n" +
            "(SELECT ('${ym}-01' - INTERVAL DAY('${eYm}-01') DAY) + INTERVAL s DAY AS days \n" +
            "            FROM(SELECT 31 AS s UNION ALL SELECT 30 UNION ALL SELECT 29 UNION ALL SELECT 28  \n" +
            "            UNION ALL SELECT 27 UNION ALL SELECT 26 UNION ALL SELECT 25 UNION ALL SELECT 24 \n" +
            "            UNION ALL SELECT 23 UNION ALL SELECT 22 UNION ALL SELECT 21 UNION ALL SELECT 20 \n" +
            "            UNION ALL SELECT 19 UNION ALL SELECT 18 UNION ALL SELECT 17 UNION ALL SELECT 16 \n" +
            "            UNION ALL SELECT 15 UNION ALL SELECT 14 UNION ALL SELECT 13 UNION ALL SELECT 12 \n" +
            "            UNION ALL SELECT 11 UNION ALL SELECT 10 UNION ALL SELECT 09 UNION ALL SELECT 08 \n" +
            "            UNION ALL SELECT 07 UNION ALL SELECT 06 UNION ALL SELECT 05 UNION ALL SELECT 04 \n" +
            "            UNION ALL SELECT 03 UNION ALL SELECT 02 UNION ALL SELECT 01) daytable) as dayT\n" +
            "LEFT JOIN\n" +
            "(SELECT \n" +
            " DATE_FORMAT(liushuiDateTime,'%Y-%m-%d') as liushuiday,\n" +
            "<foreach collection='paystyleList' item='style' index='index' open='' close='' separator=','>" +
            " case payMoneyStyle when '${style.id}' then SUM(shouruMoney) else 0 end as #{style.moneystyleName} " +
            "</foreach>" +
            "FROM pxliushuizhangtable where qiyeID = #{qiyeID} \n" +
            "GROUP BY payMoneyStyle,DATE_FORMAT(liushuiDateTime,'%Y-%m-%d')) AS liushui\n" +
            "ON\n" +
            "dayT.days=liushui.liushuiday" + ") daytable WHERE DATE_FORMAT(daytable.days,'%Y-%m') = '${ym}' ORDER BY daytable.days asc"
            + "</script>")
    List<HashMap<String, String>> getShouruDay(@Param("ym") String Ym, @Param("eYm") String eYm,
                                               @Param("paystyleList") List<Pxpaymoneystyletable> paystyleList,
                                               @Param("qiyeID") Long qiyeID
    );

    @Select("<script>" +
            "SELECT *" +
            "FROM pxpaymoneystyletable " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxpaymoneystyletable> getPaystyleList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT *" +
            "FROM pxshouzhistyletable " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, String>> getShouzhistyleList(@Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT id,shouzhiStyle AS `name` FROM pxshouzhistyletable WHERE qiyeID = #{qiyeID}" + "</script>")
    List<listVo> GetAllSearchshouzhiStyleList(long qiyeID);

    @Select("<script>" +
            "SELECT liushui.*,shouzhi.*,staff.staffName,campus.campusName,paystayle.moneystyleName," +
            "DATE_FORMAT(liushui.liushuiDateTime,'%Y-%m-%d %H:%i:%s') as liushuiDateTimef, " +
            "DATE_FORMAT(liushui.luruTime,'%Y-%m-%d %H:%i:%s') as luruTimef " +
            "FROM pxliushuizhangtable liushui " +
            "LEFT JOIN pxshouzhistyletable shouzhi ON liushui.shouzhiStyleID= shouzhi.id " +
            "LEFT JOIN pxcampustable campus ON liushui.campusID= campus.id " +
            "LEFT JOIN pxpaymoneystyletable paystayle ON liushui.payMoneyStyle= paystayle.id " +
            "LEFT JOIN pxstafftable staff ON liushui.jinbanRen= staff.id " +
            "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    HashMap<String, String> getLiushui(@Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT (CASE WHEN SUM(shouruMoney-zhichuMoney) IS NULL\n" +
            "\t  THEN\n" +
            "\t\t0\n" +
            "\tELSE\n" +
            "\t\tSUM(shouruMoney-zhichuMoney)\n" +
            "END) AS Money\n" +
            "  from pxliushuizhangtable  " + "<if test='ew != null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    BigDecimal GetLiushuizhangMoney(@Param("ew") Wrapper wrapper);
}