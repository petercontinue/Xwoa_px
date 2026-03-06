package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.listVo;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
public interface IPxqiandaninfotableDao extends BaseMapper<Pxqiandaninfotable> {


    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandandate", property = "qiandandate"),
            @Result(column = "shishouTotalMoney", property = "shishouTotalMoney"),
            @Result(column = "HetongMoney", property = "hetongMoney"),
            @Result(column = "jiazhangDemand", property = "jiazhangDemand"),
            @Result(column = "zhuanjieshaoID", property = "zhuanjieshaoID"),
            @Result(column = "moneyStyle", property = "moneyStyle"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qianDanStaffID", property = "qianDanStaffID"),
            @Result(column = "recordInStaffID", property = "recordInStaffID"),
            @Result(column = "recordInTime", property = "recordInTime"),
            @Result(column = "PayMoneyStyle", property = "payMoneyStyle"),
            @Result(column = "zhuansongID", property = "zhuansongID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "fromType", property = "fromType"),
            @Result(column = "qiandanType", property = "qiandanType"),
            @Result(column = "hetong", property = "hetong"),
            @Result(column = "youhuiID", property = "youhuiID"),
            @Result(column = "youhuijine", property = "youhuijine"),
            @Result(column = "youhuishuoming", property = "youhuishuoming"),
            @Result(column = "isdingjing", property = "isdingjing"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandaninfotable"
            + "</script>")
    List<Pxqiandaninfotable> getAllList();

    @Select("<script>" +
            "SELECT a.id id ,\t( CASE WHEN a.moneyStyle=1 THEN (SELECT CONCAT(\"新签-\",a.qiandandate) ) ELSE (SELECT CONCAT(\"续费-\",a.qiandandate) ) END ) name \n" +
            "from pxqiandaninfotable a  where a.stuID=#{stuID} and (a.moneyStyle=1 or a.moneyStyle=2) and a.qiyeID=#{qiyeID} "
            + "</script>")
    List<listVo> getstuQiandan(Long stuID, Long qiyeID);

    @Select("<script>" +
            "SELECT \n" +
            "tmp.tyear,\n" +
            "SUM(tmp.Jan) AS JAN,\n" +
            "SUM(tmp.Feb) AS FEB,\n" +
            "SUM(tmp.Mar) AS MAR,\n" +
            "SUM(tmp.Apr) AS APR,\n" +
            "SUM(tmp.May) AS MAY,\n" +
            "SUM(tmp.Jun) AS JUN,\n" +
            "SUM(tmp.Jul) AS JUL,\n" +
            "SUM(tmp.Aug) AS AUG,\n" +
            "SUM(tmp.Sep) AS SEP,\n" +
            "SUM(tmp.Oct) AS OCT,\n" +
            "SUM(tmp.Nov) AS NOV,\n" +
            "SUM(tmp.Dece) AS DECE,\n" +
            "tmp.campusID,\n" +
            "campus.campusName\n" +
            "FROM \n" +
            "(SELECT \n" +
            "YEAR(qiandandate) as tyear,\n" +
            "case MONTH(qiandandate) when '1' then SUM(shishouTotalMoney) else 0 end AS Jan ,\n" +
            "case MONTH(qiandandate) when '2' then SUM(shishouTotalMoney) else 0 end AS Feb,\n" +
            "case MONTH(qiandandate) when '3' then SUM(shishouTotalMoney) else 0 end as Mar,\n" +
            "case MONTH(qiandandate) when '4' then SUM(shishouTotalMoney) else 0 end as Apr,\n" +
            "case MONTH(qiandandate) when '5' then SUM(shishouTotalMoney) else 0 end as May ,\n" +
            "case MONTH(qiandandate) when '6' then SUM(shishouTotalMoney) else 0 end as Jun ,\n" +
            "case MONTH(qiandandate) when '7' then SUM(shishouTotalMoney) else 0 end as Jul,\n" +
            "case MONTH(qiandandate) when '8' then SUM(shishouTotalMoney) else 0 end as Aug,\n" +
            "case MONTH(qiandandate) when '9' then SUM(shishouTotalMoney) else 0 end as Sep,\n" +
            "case MONTH(qiandandate) when '10' then SUM(shishouTotalMoney) else 0 end as Oct,\n" +
            "case MONTH(qiandandate) when '11' then SUM(shishouTotalMoney) else 0 end as Nov,\n" +
            "case MONTH(qiandandate) when '12' then SUM(shishouTotalMoney) else 0 end as Dece,\n" +
            "campusID FROM pxqiandaninfotable  WHERE 1=1  " +
            "<if test=\"qiyeID != null and qiyeID!='' \" >" +
            "<![CDATA[" +
            " AND qiyeID = #{qiyeID} " +
            "]]>" +
            "</if>" +
            "<if test=\"moneyStyle != null and moneyStyle != '' \">" +
            " AND moneyStyle IN " +
            "<foreach collection='moneyStyle' item='style' index='index' open='(' close=')' separator=','>" +
            "#{style}" +
            "</foreach>" +
            "</if>" +
            "GROUP BY YEAR(qiandandate),MONTH(qiandandate),campusID ORDER BY qiandandate DESC) as tmp\n" +
            "LEFT JOIN pxcampustable campus ON tmp.campusID = campus.id\n" +
            "WHERE 1=1 " +
            "<if test=\"startYear != null and startYear!='' \" >" +
            "<![CDATA[" +
            " AND tmp.tyear >= #{startYear} " +
            "]]>" +
            "</if>" +
            "<if test=\"endYear != null and endYear!='' \" >" +
            "<![CDATA[" +
            " AND tmp.tyear <= #{endYear} " +
            "]]>" +
            "</if>" +
            "<if test=\"campusID != null and campusID != '' \">" +
            "<![CDATA[" +
            " AND tmp.campusID = #{campusID} " +
            "]]>" +
            "</if>" +
            "GROUP BY tmp.tyear" +
            "<if test=\"campusID != null and campusID != '' \">" +
            ",tmp.campusID" +
            "</if>" +
            "ORDER BY tmp.tyear DESC"
            + "</script>")
    List<HashMap<String, String>> getYejitongbihuanbiList(@Param("moneyStyle") String[] moneyStyle,
                                                          @Param("campusID") String campusID,
                                                          @Param("startYear") String startYear,
                                                          @Param("endYear") String endYear,
                                                          @Param("qiyeID") Long qiyeID

    );

    @Select("<script>" +
            "SELECT a.id,a.campusName,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='1' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Jan,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='2' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Feb,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='3' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Mar,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='4' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Apr,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='5' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS May,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='6' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Jun,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='7' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Jul,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='8' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Aug,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='9' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Sep,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='10' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Oct,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='11' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Nov,\n" +
            "(SELECT (case when MONTH(b.qiandandate) ='12' then SUM(b.shishouTotalMoney) else 0 end) from pxqiandaninfotable b where a.id=b.campusID and b.moneyStyle=3 and YEAR(b.qiandandate)=#{year} ) AS Dece\n" +
            "from pxcampustable a \n" +
            "where a.isOpen!=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<HashMap<String, String>> gettuifeitongji(@Param("ew") QueryWrapper queryWrapper, String year);


    @Select("<script>" +
            "SELECT campus.id as campusID , campus.campusName," +
            " classT.id as classID,classT.className," +
            "SUM(buxikecheng.zongjia) AS zongjia," +
            "SUM(stu.remainXuefei) AS remainXuefei," +
            "SUM(buxikecheng.remainkeshi) as remainkeshi," +
            "SUM((keshi.kechengPrice*keshi.keshiNum)) as totalkehao," +
            "SUM(keshi.keshiNum) as keshi  " +
            "FROM pxclasstable classT \n" +
            "LEFT JOIN pxcampustable campus ON classT.campusID = campus.id \n" +
            "LEFT JOIN pxstuclasstable stuclass ON classT.id = stuclass.classID\n" +
            "LEFT JOIN pxbuxikechengtable buxikecheng ON buxikecheng.id = stuclass.buxiID\n" +
            "LEFT JOIN pxstutable stu ON buxikecheng.stuID = stu.id\n" +
            "LEFT JOIN pxkeshistutable keshi ON keshi.classID=classT.id\n" +
            "WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY campus.id,classT.id"
            + "</script>")
    Page<HashMap<String, String>> getBanjishoufeiPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT campus.id as campusID , campus.campusName," +
            " classT.id as classID,classT.className," +
            "SUM(buxikecheng.zongjia) AS zongjia," +
            "SUM(stu.remainXuefei) AS remainXuefei," +
            "SUM(buxikecheng.remainkeshi) as remainkeshi," +
            "SUM((keshi.kechengPrice*keshi.keshiNum)) as totalkehao," +
            "SUM(keshi.keshiNum) as keshi  " +
            "FROM pxclasstable classT \n" +
            "LEFT JOIN pxcampustable campus ON classT.campusID = campus.id \n" +
            "LEFT JOIN pxstuclasstable stuclass ON classT.id = stuclass.classID\n" +
            "LEFT JOIN pxbuxikechengtable buxikecheng ON buxikecheng.id = stuclass.buxiID\n" +
            "LEFT JOIN pxstutable stu ON buxikecheng.stuID = stu.id\n" +
            "LEFT JOIN pxkeshistutable keshi ON keshi.classID=classT.id\n" +
            "WHERE 1=1 " +
            "<if test=\"qiyeID != null and qiyeID!='' \" >" +
            "<![CDATA[" +
            " AND classT.qiyeID = #{qiyeID} " +
            "]]>" +
            "</if>" +
            "<if test=\"campusID != null and campusID!='' \" >" +
            "<![CDATA[" +
            " AND campus.id = #{campusID} " +
            "]]>" +
            "</if>" +
            "<if test=\"classID != null and classID!='' \" >" +
            "<![CDATA[" +
            " AND classT.className like concat('%',#{classID},'%') " +
            "]]>" +
            "</if>" +
            "GROUP BY campus.id,classT.id"
            + "</script>")
    List<HashMap<String, Object>> getBanjishoufeiList(@Param("campusID") String campusID, @Param("classID") String classID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT campus.id as campusID,class.id as classID,campus.campusName,stu.stuName,class.className,SUM(buxikecheng.zongjia) as zongjia,(SELECT remainXuefei FROM pxstutable WHERE id=stu.id) as remainXuefei,SUM(keshi.keshiNum) as xiaohaokeshi,SUM(buxikecheng.remainkeshi)  as  remainkeshi FROM pxstutable stu \n" +
            "LEFT JOIN pxcampustable campus ON stu.campusID = campus.id\n" +
            "LEFT JOIN pxbuxikechengtable buxikecheng ON buxikecheng.stuID = stu.id\n" +
            "LEFT JOIN pxstuclasstable stuclass ON stuclass.buxiID = buxikecheng.id\n" +
            "LEFT JOIN pxclasstable class ON class.id = stuclass.classID\n" +
            "LEFT JOIN pxkeshistutable keshi ON keshi.stuID=stu.id\n" +
            "WHERE 1=1\n" +
            "<if test=\"qiyeID != null and qiyeID!='' \" >" +
            "<![CDATA[" +
            " AND stu.qiyeID = #{qiyeID} " +
            "]]>" +
            "</if>" +
            "<if test=\"campusID != null and campusID!='' \" >" +
            "<![CDATA[" +
            " AND campus.id = #{campusID} " +
            "]]>" +
            "</if>" +
            "<if test=\"classID != null and classID!='' \" >" +
            "<![CDATA[" +
            " AND class.id = #{classID} " +
            "]]>" +
            "</if>" +
            "GROUP BY campus.id,class.id,stu.id"
            + "</script>")
    Page<HashMap<String, String>> getShoufeiDetail(Page page, @Param("campusID") String campusID, @Param("classID") String classID, @Param("qiyeID") Long qiyeID);

    @Select("<script>" +
            "SELECT campus.id as campusID,class.id as classID,campus.campusName,stu.stuName,class.className,SUM(buxikecheng.zongjia) as zongjia,(SELECT remainXuefei FROM pxstutable WHERE id=stu.id) as remainXuefei,SUM(keshi.keshiNum) as xiaohaokeshi,SUM(buxikecheng.remainkeshi)  as  remainkeshi FROM pxstutable stu \n" +
            "LEFT JOIN pxcampustable campus ON stu.campusID = campus.id\n" +
            "LEFT JOIN pxbuxikechengtable buxikecheng ON buxikecheng.stuID = stu.id\n" +
            "LEFT JOIN pxstuclasstable stuclass ON stuclass.buxiID = buxikecheng.id\n" +
            "LEFT JOIN pxclasstable class ON class.id = stuclass.classID\n" +
            "LEFT JOIN pxkeshistutable keshi ON keshi.stuID=stu.id\n" +
            "WHERE 1=1\n" +
            "<if test=\"qiyeID != null and qiyeID!='' \" >" +
            "<![CDATA[" +
            " AND stu.qiyeID = #{qiyeID} " +
            "]]>" +
            "</if>" +
            "<if test=\"campusID != null and campusID!='' \" >" +
            "<![CDATA[" +
            " AND campus.id = #{campusID} " +
            "]]>" +
            "</if>" +
            "<if test=\"classID != null and classID!='' \" >" +
            "<![CDATA[" +
            " AND class.id = #{classID} " +
            "]]>" +
            "</if>" +
            "GROUP BY campus.id,class.id,stu.id"
            + "</script>")
    List<HashMap<String, Object>> getShoufeiDetailList(@Param("campusID") String campusID, @Param("classID") String classID, @Param("qiyeID") Long qiyeID);
}