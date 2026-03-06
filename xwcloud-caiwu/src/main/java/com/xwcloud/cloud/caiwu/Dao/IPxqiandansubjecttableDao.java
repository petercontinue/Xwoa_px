package com.xwcloud.cloud.caiwu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Pxqiandansubjecttable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
public interface IPxqiandansubjecttableDao extends BaseMapper<Pxqiandansubjecttable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandandate", property = "qiandandate"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengprice", property = "kechengprice"),
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

    @Select("<script>" +
            "SELECT SUM(tmp2.onevone) as ONEVONE , SUM(tmp2.noonevone) AS NOONEVONE,(SUM(tmp2.onevone)+SUM(tmp2.noonevone)) kehaozonge ,tmp2.campusName,tmp2.subjectName,tmp2.campusID \n" +
            "FROM\n" +
            "  (SELECT \n" +
            "    case tmp.is1v1 when 1 then SUM(tmp.Totalprice) else 0 end AS onevone ,\n" +
            "    case tmp.is1v1 when 0 then SUM(tmp.Totalprice) else 0 end AS noonevone,\n" +
            "    tmp.campusName,tmp.subjectName,tmp.subID,tmp.campusID\n" +
            "   FROM\n" +
            "    (SELECT \n" +
            "     SUM((keshistu.kechengPrice*keshistu.keshiNum)) as Totalprice," +
            "buxistyle.is1v1 as is1v1,\n" +
            "     campus.campusName,sub.subjectName,sub.id as subID,campus.id as campusID \n" +
            "     FROM pxkeshistutable keshistu \n" +
            "     LEFT JOIN pxbuxistyletable buxistyle ON keshistu.buxiStyleID = buxistyle.id \n" +
            "     LEFT JOIN pxcampustable campus ON keshistu.campusID = campus.id \n" +
            "     LEFT JOIN pxkechengtable kecheng ON keshistu.kechengID = kecheng.id \n" +
            "     LEFT JOIN pxsubjecttable sub ON kecheng.subjectID = sub.id\n" +
            "     where 1=1 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "     GROUP BY keshistu.campusID,sub.id,buxistyle.is1v1\n" +
            "    ) as tmp\n" +
            "  GROUP BY tmp.subID,tmp.is1v1\n" +
            "  ) as tmp2\n" +
            "GROUP BY tmp2.campusID,tmp2.subID"
            + "</script>")
    Page<HashMap<String,String>> getKemukehaoPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT SUM(tmp2.onevone) as ONEVONE , SUM(tmp2.noonevone) AS NOONEVONE,(SUM(tmp2.onevone)+SUM(tmp2.noonevone)) kehaozonge ,tmp2.campusName,tmp2.subjectName,tmp2.campusID \n" +
            "FROM\n" +
            "  (SELECT \n" +
            "    case tmp.is1v1 when 1 then SUM(tmp.Totalprice) else 0 end AS onevone ,\n" +
            "    case tmp.is1v1 when 0 then SUM(tmp.Totalprice) else 0 end AS noonevone,\n" +
            "    tmp.campusName,tmp.subjectName,tmp.subID,tmp.campusID\n" +
            "   FROM\n" +
            "    (SELECT \n" +
            "     SUM((keshistu.kechengPrice*keshistu.keshiNum)) as Totalprice,buxistyle.is1v1 as is1v1,\n" +
            "     campus.campusName,sub.subjectName,sub.id as subID,campus.id as campusID \n" +
            "     FROM pxkeshistutable keshistu \n" +
            "     LEFT JOIN pxbuxistyletable buxistyle ON keshistu.buxiStyleID = buxistyle.id \n" +
            "     LEFT JOIN pxcampustable campus ON keshistu.campusID = campus.id \n" +
            "     LEFT JOIN pxkechengtable kecheng ON keshistu.kechengID = kecheng.id \n" +
            "     LEFT JOIN pxsubjecttable sub ON kecheng.subjectID = sub.id\n" +
            "     where 1=1 "+
            "<if test=\"qiyeID != null and qiyeID != '' \">"+
            " AND keshistu.qiyeID = #{qiyeID} " +
            "</if>"+
            "<if test=\"campusID != null and campusID != '' \">"+
            " AND campus.id = #{campusID} " +
            "</if>"+
            "<if test=\"kemuName != null and kemuName != '' \">"+
            " AND sub.subjectName like '%#{kemuName}%' " +
            "</if>"+
            "<if test=\"startDate != null and startDate!='' \" >"+
            "<![CDATA["+
            " AND keshistu.haveClassDate >= #{startDate} " +
            "]]>"+
            "</if>"+
            "<if test=\"endDate != null and endDate!='' \" >"+
            "<![CDATA["+
            " AND keshistu.haveClassDate <= #{endDate} " +
            "]]>"+
            "</if>"+
            "     GROUP BY keshistu.campusID,sub.id,buxistyle.is1v1\n" +
            "    ) as tmp\n" +
            "  GROUP BY tmp.subID,tmp.is1v1\n" +
            "  ) as tmp2\n" +
            "GROUP BY tmp2.campusID,tmp2.subID"
            + "</script>")
    List<HashMap<String,Object>> getKemukehaoList(@Param("campusID") String campusID,
                                                  @Param("kemuName") String kemuName,
                                                  @Param("startDate") String startDate,
                                                  @Param("endDate") String endDate,
                                                  @Param("qiyeID") String qiyeID
    );

    @Select("<script>" +
            "SELECT SUM(tmp2.onevone) as ONEVONE , SUM(tmp2.noonevone) AS NOONEVONE,\n" +
            "tmp2.campusName,tmp2.campusID,tmp2.subID,tmp2.subName,tmp2.zongjia\n" +
            "FROM\n" +
            "(SELECT \n" +
            "case tmp.is1v1KC when 1 then SUM(tmp.zongjia) else 0 end AS onevone,\n" +
            "case tmp.is1v1KC when 0 then SUM(tmp.zongjia) else 0 end AS noonevone,\n" +
            "tmp.campusName,tmp.campusID,tmp.subID,tmp.subName,tmp.zongjia,tmp.is1v1KC\n" +
            "FROM\n" +
            "(SELECT \n" +
            "campus.campusName,\n" +
            "campus.id as campusID,\n" +
            "sub.subjectName as subName,\n" +
            "sub.id as subID,\n" +
            "kecheng.is1v1KC,\n" +
            "SUM(qdsub.zongjia) as zongjia\n" +
            "FROM pxqiandansubjecttable qdsub \n" +
            "LEFT JOIN pxkechengtable kecheng ON qdsub.kechengID = kecheng.id \n" +
            "LEFT JOIN pxsubjecttable sub ON kecheng.subjectID= sub.id \n" +
            "LEFT JOIN pxcampustable campus ON kecheng.campusID = campus.id\n" +
            " where 1=1 "+

            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"+

            "GROUP BY campus.id,sub.id,kecheng.is1v1KC) as tmp \n" +
            "GROUP BY tmp.subID,tmp.is1v1KC) as tmp2\n" +
            "GROUP BY tmp2.campusID,tmp2.subID"
            + "</script>")
    Page<HashMap<String,String>>getKemushoufeiPage(Page page,@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT SUM(tmp2.onevone) as ONEVONE , SUM(tmp2.noonevone) AS NOONEVONE,\n" +
            "tmp2.campusName,tmp2.campusID,tmp2.subID,tmp2.subName,tmp2.zongjia\n" +
            "FROM\n" +
            "(SELECT \n" +
            "case tmp.is1v1KC when 1 then SUM(tmp.zongjia) else 0 end AS onevone,\n" +
            "case tmp.is1v1KC when 0 then SUM(tmp.zongjia) else 0 end AS noonevone,\n" +
            "tmp.campusName,tmp.campusID,tmp.subID,tmp.subName,tmp.zongjia,tmp.is1v1KC\n" +
            "FROM\n" +
            "(SELECT \n" +
            "campus.campusName,\n" +
            "campus.id as campusID,\n" +
            "sub.subjectName as subName,\n" +
            "sub.id as subID,\n" +
            "kecheng.is1v1KC,\n" +
            "SUM(qdsub.zongjia) as zongjia\n" +
            "FROM pxqiandansubjecttable qdsub \n" +
            "LEFT JOIN pxkechengtable kecheng ON qdsub.kechengID = kecheng.id \n" +
            "LEFT JOIN pxsubjecttable sub ON kecheng.subjectID= sub.id \n" +
            "LEFT JOIN pxcampustable campus ON kecheng.campusID = campus.id\n" +
            "     where 1=1 "+
            "<if test=\"qiyeID != null and qiyeID != '' \">"+
            " AND qdsub.qiyeID = #{qiyeID} " +
            "</if>"+
            "<if test=\"campusID != null and campusID != '' \">"+
            " AND campus.id = #{campusID} " +
            "</if>"+
            "<if test=\"kemuName != null and kemuName != '' \">"+
            " AND sub.subjectName like '%${kemuName}%' " +
            "</if>"+
            "<if test=\"startDate != null and startDate != '' \">"+
            "<![CDATA["+
            " AND qdsub.qiandandate >= #{startDate} " +
            "]]>"+
            "</if>"+
            "<if test=\"endDate != null and endDate != '' \">"+
            "<![CDATA["+
            " AND qdsub.qiandandate <= #{endDate} " +
            "]]>"+
            "</if>"+
            "GROUP BY campus.id,sub.id,kecheng.is1v1KC) as tmp \n" +
            "GROUP BY tmp.subID,tmp.is1v1KC) as tmp2\n" +
            "GROUP BY tmp2.campusID,tmp2.subID"
            + "</script>")
    List<HashMap<String,Object>>getKemushoufeiList(@Param("campusID") String campusID,@Param("kemuName") String kemuName,
                                                   @Param("startDate") String startDate,@Param("endDate") String endDate,
                                                   @Param("qiyeID") String qiyeID);

    @Select("<script>" +
            "SELECT tmp2.campusName,tmp2.campusID,tmp2.subName,tmp2.subID,SUM(tmp2.qzrq) as qzrq,SUM(tmp2.noqzrq) as noqzrq,(SUM(tmp2.qzrq)+SUM(tmp2.noqzrq)) as zonge\n" +
            "FROM\n" +
            "(SELECT \n" +
            "case tmp.jifeiStyleID when 3 then ROUND((tmp.zongjia/DATEDIFF(tmp.qdendDate,tmp.qdstartDate)),2)*(DATEDIFF(NOW(),tmp.qdstartDate))  else 0 end AS qzrq,\n" +
            "case tmp.jifeiStyleID when 3 then 0  else tmp.remainkeshi*tmp.kechengprice end AS noqzrq,\n" +
            "tmp.campusName,tmp.subName,tmp.jifeiStyleID,tmp.campusID,tmp.subID\n" +
            "FROM\n" +
            "(SELECT \n" +
            "buxikecheng.jifeiStyleID,\n" +
            "campus.campusName,\n" +
            "campus.id as campusID,\n" +
            "sub.subjectName as subName,\n" +
            "sub.id as subID,\n" +
            "buxikecheng.remainkeshi AS remainkeshi,\n" +
            "buxikecheng.kechengprice as kechengprice,\n" +
            "qdsub.startDate as qdstartDate,\n" +
            "qdsub.endDate as qdendDate,\n" +
            "qdsub.zongjia as zongjia\n" +
            "FROM pxbuxikechengtable buxikecheng \n" +
            "LEFT JOIN pxkechengtable kecheng ON buxikecheng.kechengID = kecheng.id\n" +
            "LEFT JOIN pxsubjecttable sub ON kecheng.subjectID = sub.id\n" +
            "LEFT JOIN pxcampustable campus ON campus.id = kecheng.campusID\n" +
            "LEFT JOIN pxqiandansubjecttable qdsub ON qdsub.id = buxikecheng.qianDanSubjectID\n" +
            "     where 1=1 "+

            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"+

            ") as tmp\n" +
            ") as tmp2\n" +
            "GROUP BY tmp2.campusID,tmp2.subID"
            + "</script>")
    Page<HashMap<String,String>> getKemuyufeePage(Page page,@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT tmp2.campusName,tmp2.campusID,tmp2.subName,tmp2.subID,SUM(tmp2.qzrq) as qzrq,SUM(tmp2.noqzrq) as noqzrq,(SUM(tmp2.qzrq)+SUM(tmp2.noqzrq)) as zonge\n" +
            "FROM\n" +
            "(SELECT \n" +
            "case tmp.jifeiStyleID when 3 then ROUND((tmp.zongjia/DATEDIFF(tmp.qdendDate,tmp.qdstartDate)),2)*(DATEDIFF(NOW(),tmp.qdstartDate))  else 0 end AS qzrq,\n" +
            "case tmp.jifeiStyleID when 3 then 0  else tmp.remainkeshi*tmp.kechengprice end AS noqzrq,\n" +
            "tmp.campusName,tmp.subName,tmp.jifeiStyleID,tmp.campusID,tmp.subID\n" +
            "FROM\n" +
            "(SELECT \n" +
            "buxikecheng.jifeiStyleID,\n" +
            "campus.campusName,\n" +
            "campus.id as campusID,\n" +
            "sub.subjectName as subName,\n" +
            "sub.id as subID,\n" +
            "buxikecheng.remainkeshi AS remainkeshi,\n" +
            "buxikecheng.kechengprice as kechengprice,\n" +
            "qdsub.startDate as qdstartDate,\n" +
            "qdsub.endDate as qdendDate,\n" +
            "qdsub.zongjia as zongjia\n" +
            "FROM pxbuxikechengtable buxikecheng \n" +
            "LEFT JOIN pxkechengtable kecheng ON buxikecheng.kechengID = kecheng.id\n" +
            "LEFT JOIN pxsubjecttable sub ON kecheng.subjectID = sub.id\n" +
            "LEFT JOIN pxcampustable campus ON campus.id = kecheng.campusID\n" +
            "LEFT JOIN pxqiandansubjecttable qdsub ON qdsub.id = buxikecheng.qianDanSubjectID\n" +
            "     where 1=1 "+
            "<if test=\"qiyeID != null and qiyeID != '' \">"+
            " AND buxikecheng.qiyeID = #{qiyeID} " +
            "</if>"+
            "<if test=\"campusID != null and campusID != '' \">"+
            " AND campus.id = #{campusID} " +
            "</if>"+
            "<if test=\"kemuName != null and kemuName != '' \">"+
            " AND sub.subjectName like '%#{kemuName}%' " +
            "</if>"+
            "<if test=\"startDate != null and startDate != '' \">"+
            "<![CDATA["+
            " AND qdsub.qiandandate >= #{startDate} " +
            "]]>"+
            "</if>"+
            "<if test=\"endDate != null and endDate != '' \">"+
            "<![CDATA["+
            " AND qdsub.qiandandate <= #{endDate} " +
            "]]>"+
            "</if>"+
            ") as tmp\n" +
            ") as tmp2\n" +
            "GROUP BY tmp2.campusID,tmp2.subID"
            + "</script>")
    List<HashMap<String,Object>> getKemuyufeeList(@Param("campusID") String campusID,@Param("kemuName") String kemuName,
                                                  @Param("startDate") String startDate,@Param("endDate") String endDate,
                                                  @Param("qiyeID") String qiyeID);


    @Select("<script>" +
            "SELECT sub.subjectName, COUNT(*) as subNUM,campus.campusName  FROM pxqiandansubjecttable qiandansub \n" +
            "LEFT JOIN pxkechengtable kecheng ON qiandansub.kechengID = kecheng.id\n" +
            "LEFT JOIN pxsubjecttable sub ON sub.id=kecheng.subjectID " +
            "LEFT JOIN pxcampustable AS campus ON kecheng.campusID= campus.id\n"+
            "WHERE 1=1 "+
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY sub.id"
            + "</script>")
    List<HashMap<String, String>> getKumuStu(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT sub.subjectName, SUM(qiandan.shishouTotalMoney) as subSUM,campus.campusName  FROM pxqiandansubjecttable qiandansub \n" +
            "LEFT JOIN pxkechengtable kecheng ON qiandansub.kechengID = kecheng.id\n" +
            "LEFT JOIN pxsubjecttable sub ON sub.id=kecheng.subjectID\n" +
            "LEFT JOIN pxqiandaninfotable qiandan ON qiandansub.qianDanInfoID = qiandan.id\n" +
            "\t\t\tLEFT JOIN pxcampustable AS campus ON kecheng.campusID=campus.id\n" +
            "WHERE 1 = 1 " +
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY sub.id"
            + "</script>")
    List<HashMap<String, String>> getKumuXinqian(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT sub.subjectName, SUM(qiandansub.buykeshiNum) as subSUM ,campus.campusName  FROM pxqiandansubjecttable qiandansub \n" +
            "LEFT JOIN pxkechengtable kecheng ON qiandansub.kechengID = kecheng.id\n" +
            "LEFT JOIN pxsubjecttable sub ON sub.id=kecheng.subjectID " +
            "\t\t\t\t\t\tLEFT JOIN pxcampustable AS campus ON kecheng.campusID = campus.id\n" +
            "WHERE 1 = 1 " +
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY sub.id"
            + "</script>")
    List<HashMap<String, String>> getKumuKeshi(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT kemu.subjectName,SUM(keshi.keshiNum) AS keshiNum ,campus.campusName\n" +
            "FROM pxsubjecttable kemu \n" +
            "JOIN pxkechengtable kecheng ON kemu.id=kecheng.subjectID\n" +
            "JOIN pxkeshistutable keshi ON keshi.kechengID = kecheng.id\n" +
            "LEFT JOIN pxcampustable AS campus ON  kecheng.campusID = campus.id\n" +
            "WHERE 1 = 1 " +
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY kemu.id"
            + "</script>")
    List<HashMap<String, String>> getKumukexiao(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT tt.* FROM (SELECT stu.*,campus.campusName,stugrade.stuGradeName,MAX(keshi.haveClassDate) maxhave,datediff(now(),keshi.haveClassDate) tingke,staff.staffName \n" +
            "            FROM pxstutable stu \n" +
            "            LEFT JOIN pxkeshistutable keshi ON stu.id = keshi.stuID \n" +
            "            LEFT JOIN pxstafftable staff ON staff.id=stu.banzhurenTeacherID\n" +
            "            LEFT JOIN pxcampustable campus ON stu.campusID=campus.id\n" +
            "            LEFT JOIN pxstugradetable stugrade ON stugrade.id=stu.stuGradeID\n" +
            " GROUP BY stu.id) AS tt  WHERE tt.buxiStateID = 3 \n" +
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<HashMap<String, String>> getTingkeStu(Page page,@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT campus.campusName,stu.stuName,stu.zidingyiStuID,stu.stuGradeID,stugrade.stuGradeName," +
            "MAX(keshi.haveClassDate) maxhave,datediff(now(),keshi.haveClassDate) tingke,staff.staffName \n" +
            "            FROM pxstutable stu \n" +
            "            LEFT JOIN pxkeshistutable keshi ON stu.id = keshi.stuID \n" +
            "            LEFT JOIN pxstafftable staff ON staff.id=stu.banzhurenTeacherID\n" +
            "            LEFT JOIN pxcampustable campus ON stu.campusID=campus.id\n" +
            "            LEFT JOIN pxstugradetable stugrade ON stugrade.id=stu.stuGradeID\n" +
            " GROUP BY stu.id "+
            "            WHERE stu.buxiStateID = 3 \n" +
            "<if test='ew != null '>"+
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<HashMap<String, Object>> getTingkeStuList(@Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT d.subjectName,f.campusName,c.buxiStyleName,SUM(IF(c.buxiStyleName='一对一',a.zongjia,0)) money1v1,SUM(IF(c.buxiStyleName!='一对一',a.zongjia,0)) moneyNvN,SUM(a.zongjia) moneyCount\n" +
            "FROM pxqiandansubjecttable a\n" +
            "JOIN pxkechengtable b ON a.kechengID=b.id\n" +
            "JOIN pxbuxistyletable c on b.buxiStyleID=c.id\n" +
            "JOIN pxsubjecttable d on b.subjectID=d.id\n" +
            "JOIN pxstutable e ON a.stuID=e.id\n" +
            "JOIN pxcampustable f ON e.campusID=f.id\n" +
            "WHERE f.isOpen != 2\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY b.subjectID,e.campusID\n" +
            "</script>")
    Page<HashMap<String, Object>> getKemuShoufeiTongji(Page page, @Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            "<![CDATA[" +
            "SELECT DISTINCT a.id,c.subjectName,e.campusName,\n" +
            "SUM(IF(a.jifeiStyleID!=3&&a.zongjia!=0,a.kechengprice*a.remainkeshi,0))moneynodate,\n" +
            "SUM(IF(a.jifeiStyleID=3&&a.zongjia!=0&&TIMESTAMPDIFF(DAY,a.startDate,a.endDate)>0,\n" +
            "a.zongjia-(a.zongjia/TIMESTAMPDIFF(DAY,a.startDate,a.endDate)*\n" +
            "IFNULL((SELECT SUM(aa.keshiNum)\n" +
            "FROM pxkeshistutable aa\n" +
            "JOIN pxkechengtable bb ON aa.kechengID=bb.id\n" +
            "JOIN pxsubjecttable cc ON bb.subjectID=cc.id\n" +
            "JOIN pxstutable dd ON aa.stuID=dd.id\n" +
            "JOIN pxcampustable ee ON dd.campusID=ee.id\n" +
            "WHERE a.qiyeID=aa.qiyeID and aa.buxikechengID=a.id and ee.isOpen!=2),0)\n" +
            "),0))moneydate\n" +
            "FROM pxbuxikechengtable a\n" +
            "JOIN pxkechengtable b ON a.kechengID=b.id\n" +
            "JOIN pxsubjecttable c ON b.subjectID=c.id\n" +
            "JOIN pxstutable d ON a.stuID=d.id\n" +
            "JOIN pxcampustable e ON d.campusID=e.id\n" +
            "JOIN pxqiandansubjecttable f ON d.id=f.stuID\n" +
            "WHERE e.isOpen!=2 AND f.kechengID=a.kechengID\n" +
            "]]>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY b.subjectID,d.campusID" +
            "</script>")
    Page<HashMap<String, Object>> getSubjectYuETongji(Page page, @Param("ew") QueryWrapper wrapper);

    @Select("<script>" +
            "SELECT d.id,d.stuName,c.kechengName,a.zongjia\n" +
            "FROM pxqiandansubjecttable a\n" +
            "JOIN pxqiandaninfotable b ON a.qianDanInfoID=b.id\n" +
            "JOIN pxkechengtable c ON a.kechengID=c.id\n" +
            "JOIN pxstutable d ON a.stuID=d.id\n" +
            "JOIN pxsubjecttable e ON c.subjectID=e.id\n" +
            "WHERE b.moneyStyle=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<HashMap<String, Object>> getSubjectBmByCampusAndSubject(Page page, @Param("ew") QueryWrapper wrapper);
}