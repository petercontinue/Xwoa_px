package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
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
 * @since 2020-11-16
 */
@Repository
public interface IPxbuxikechengtableDao extends BaseMapper<Pxbuxikechengtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "originalprice", property = "originalprice"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "remainkeshi", property = "remainkeshi"),
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

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxbuxikechengtable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxbuxikechengtable> selectbuxikecheng(@Param("ew") QueryWrapper queryWrapper);


    /**
     * 不排课消课获取补习课程
     *
     * @param stuID
     * @param classID
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxbuxikechengtable as a\n" +
            "LEFT JOIN pxstuclasstable b on a.id=b.buxiID\n" +
            "WHERE a.stuID=#{stuID} and b.classID=#{classID} and a.isShow=1 "
            + "</script>")
    List<Pxbuxikechengtable> getbuxiBystuAcla(Long stuID, Long classID);

    /**
     * 人工消课
     * 班级考勤详情
     *
     * @return
     */
    @Select("<script>" +
            "SELECT  a.id ID,c.campusName campusName,a.stuID stuID,b.zidingyiStuID zidingyiStuID,b.stuName stuName,d.stuGradeName stuGradeName,\n" +
            "(case WHEN f.isShow=1 THEN concat('(已启用)',':', f.kechengName) ELSE concat('(未启用)',':', f.kechengName) END) kechengName,\n" +
            "(SELECT (case WHEN kaoqingStyle=1 THEN '正常' \n" +
            " WHEN kaoqingStyle=2 THEN '请假'\n" +
            " WHEN kaoqingStyle=3 THEN '旷课'\n" +
            " WHEN kaoqingStyle=4 THEN '迟到' \n" +
            " WHEN kaoqingStyle=5 THEN '早退' ELSE '补课' END)\n from pxstukaoqingtable WHERE stuID=a.stuID and classID=#{classID} \n" +
            "and haveclassDate=(SELECT haveClassDate from pxpaiketable WHERE id=#{paikeID} ) \n" +
            "and startClassDateTime=(SELECT startLessonDateTime from pxpaiketable where id=#{paikeID} )\n" +
            "and endClassDateTime=(SELECT endLessonDateTime from pxpaiketable WHERE id =#{paikeID} )) as kaoqing\n" +
            "from pxbuxikechengtable a\n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable c on b.campusID=c.id\n" +
            "LEFT JOIN pxstugradetable d on b.stuGradeID=d.id\n" +
            "LEFT JOIN pxxuanketable e on a.id=e.buxiID\n" +
            "LEFT JOIN pxkechengtable f on a.kechengID=f.id\n" +
            "WHERE (b.buxiStateID=1 or b.buxiStateID=2) and c.isOpen !=2 and e.paikeID=#{paikeID} " +
            "</script>")
    Page<classkaoqingVo> getclasskaoqingPage(Page page, Long paikeID, Long classID);

    /**
     * 按学员ID、班级ID 获取补习课程
     * @param stuID
     * @param classID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxbuxikechengtable  a \n" +
            "LEFT JOIN pxstuclasstable b on a.id=b.buxiID\n" +
            "WHERE a.stuID=#{stuID} and b.classID=#{classID} and a.qiyeID=#{qiyeID} and a.isShow =1 " +
            "</script>")
    List<Pxbuxikechengtable> getBxByStuAndClass(Long stuID, Long classID, Long qiyeID);


    /**
     * 班级总人数
     * (人工消课)
     *
     * @param paike
     * @return
     */
    @Select("<script>" +
            "SELECT COUNT(a.id) as stuCount \n" +
            "from pxbuxikechengtable as a \n" +
            "LEFT JOIN pxxuanketable as b on a.id=b.buxiID \n" +
            "LEFT JOIN pxstutable as c on a.stuID=c.id \n" +
            "where a.isShow=1 and (c.buxiStateID=2 or c.buxiStateID=1) and b.paikeID=#{paike}" +
            "</script>")
    List<classstuCountVo> getrengongstuCount(Long paike);

    /**
     * 学员班级获取
     *
     * @param stuID
     * @param classID
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxbuxikechengtable a\n" +
            "LEFT JOIN pxstuclasstable b on a.id=b.buxiID\n" +
            "where a.stuID=#{stuID} and b.classID=#{classID} and a.isShow=1"
            + "</script>")
    List<Pxbuxikechengtable> getbxbystuclass(Long stuID, Long classID);


    @Select("<script>" +
            "SELECT a.stuID id ,a.jifeiStyleID jifeiStyleID,d.stuName stuName,c.classTimeStyleName classTimeStyleName,\n" +
            "((case WHEN d.remainXuefei &gt; (SELECT defaultValue from pxsysparamdefaulttable where id =11) THEN '' ELSE concat('低学费',':', d.remainXuefei) END) ) yujing \n" +
            "from pxbuxikechengtable a \n" +
            "LEFT JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "LEFT JOIN pxclasstimestyletable c on b.classTimeStyleID=c.id\n" +
            "LEFT JOIN pxstutable d on a.stuID =d.id \n" +
            "where a.stuID=#{stuID} and a.id=#{buxiID} and a.isShow=1 "
            + "</script>")
    List<nopaikegetStuVo> getaddstuList(Long stuID, Long buxiID);

    /**
     * 不排课消课学员
     *
     * @param haveclassDate
     * @param startClassDateTime
     * @param endClassDateTime
     * @param classID
     * @return
     */
    @Select("<script>" +
            "SELECT f.id id,a.id bxID,e.id classID,f.stuName stuName,c.classTimeStyleName classTimeStyleName,\n" +
            "((case WHEN f.remainXuefei &gt; (SELECT defaultValue from pxsysparamdefaulttable where id =11) THEN '' ELSE concat('低学费',':', f.remainXuefei) END) ) yujing,\n " +
            "a.remainkeshi remainkeshi,a.jifeiStyleID jifeiStyleID,(SELECT (case WHEN kaoqingStyle is NULL THEN '' ELSE kaoqingStyle END) from pxstukaoqingtable where kechengID=a.kechengID and haveclassDate=#{haveclassDate} and startClassDateTime=#{startClassDateTime} and endClassDateTime=#{endClassDateTime} and stuID=a.stuID ) kaoqingStyle\n" +
            "from pxbuxikechengtable a \n" +
            "LEFT JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "LEFT JOIN pxclasstimestyletable c on b.classTimeStyleID=c.id\n" +
            "LEFT JOIN pxstuclasstable d on a.id=d.buxiID\n" +
            "LEFT JOIN pxclasstable e on d.classID=e.id\n" +
            "LEFT JOIN pxstutable f on a.stuID=f.id\n" +
            "WHERE f.buxiStateID=2 and a.stuID=f.id and e.classState=0 and e.id=#{classID} and a.isShow=1\n"
            +   "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<nopaikegetStuVo> getStuNoPaike(String haveclassDate, String startClassDateTime, String endClassDateTime, Long classID,@Param("ew") QueryWrapper queryWrapper);

    /**
     * 导出剩余课时
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT e.campusName as campusName,b.stuName as stuName,d.subjectName as subjectName,c.kechengName as kechengName, " +
            "(select (case WHEN className is null THEN '-' ELSE className END)  from pxclasstable where id=(select classID from pxstuclasstable where buxiID = a.id)) as className, " +
            "(select staffName from pxstafftable where id=(select teacherID from pxpaiketeachertable where paikeID=(SELECT id from pxpaiketable where classID=(select classID from pxstuclasstable where buxiID = a.id LIMIT 1) ORDER BY haveClassDate LIMIT 1))) as skTea," +
            "a.kechengprice as kechengprice,a.remainkeshi as remainkeshi," +
            "(case WHEN a.isShow=1 THEN '启用' ELSE '未启用' END) as isShow " +
            "from pxbuxikechengtable as a " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxkechengtable as c on a.kechengID=c.id " +
            "LEFT JOIN pxsubjecttable as d on c.subjectID=d.id " +
            "LEFT JOIN pxcampustable as e on b.campusID=e.id " +
            " WHERE 1=1 and e.isOpen != 2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<ExportReKeshiVo> ExportRekeshi(@Param("ew") QueryWrapper queryWrapper);


    /**
     * 分页获取剩余课时详情
     */
    @Select("<script>" +
            "select a.id as id,b.id as stuID,b.zidingyiStuID as zidingyiStuID,b.stuName as stuName,c.kechengName as kechengName,g.className as className,d.subjectName subjectName, " +
            "a.jifeiStyleID as JifeiStyleName,e.campusName campusName, " +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE sum(remainkeshi) END) " +
            " from pxbuxikechengtable where kechengID = a.kechengID and stuID = a.stuID) as allremainkeshi, " +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE SUM(buykeshiNum) END) " +
            "from pxqiandansubjecttable where stuID=a.stuID and kechengID=a.kechengID and kechengStyle =1) as buyN, " +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE SUM(keshiShu) END) " +
            "from pxkeshizengsongtable where stuID=a.stuID and kechengID=a.kechengID) as beGiven, " +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE SUM(buykeshiNum) END) " +
            "from pxqiandansubjecttable where stuID=a.stuID and kechengID=a.kechengID and kechengStyle =2) as forwarded, " +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE SUM(buykeshiNum) END) " +
            "from pxqiandansubjecttable where stuID=a.stuID and kechengID=a.kechengID and kechengStyle =3) as give, " +
            "(select (case WHEN COUNT(id)=0 THEN 0 ELSE SUM(keshiNum) END) " +
            "from pxkeshistutable where stuID=a.stuID and kechengID=a.kechengID) as userd, " +
            "(SELECT (case WHEN COUNT(id)=0 THEN 0 ELSE SUM(buykeshiNum) END) " +
            "from pxqiandansubjecttable where stuID=a.stuID and kechengID=a.kechengID and kechengStyle =4) as refund, " +
            "(case WHEN (select sum(remainkeshi) from pxbuxikechengtable where kechengID=a.kechengID and stuID =a.stuID) &lt; (SELECT defaultValue from pxsysparamdefaulttable WHERE id =11) THEN '课时预警' ELSE'' END) as yujing " +
            "from pxbuxikechengtable as a  " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxkechengtable as c on a.kechengID= c.id " +
            "LEFT JOIN pxsubjecttable as d on c.subjectID=d.id " +
            "LEFT JOIN pxcampustable as e on b.campusID=e.id " +
            "LEFT JOIN pxstuclasstable as f on a.id=f.buxiID " +
            "LEFT JOIN pxclasstable as g on f.classID=g.id " +
            "where 1=1 and e.isOpen !=2 and a.jifeiStyleID != 3  and (b.buxiStateID = 2 or b.buxiStateID = 3 or b.buxiStateID = 6) " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "GROUP BY stuID,a.kechengID ORDER BY stuID DESC "

            + "</script>")
    Page<RemainkeshiDetailsVo> getRemainkeshiDetailsPage(Page page, @Param("ew") QueryWrapper queryWrapper);


    /**
     * 分页获取课程剩余天
     */
    @Select("<script>" +
            "SELECT a.id as id,a.stuID as stuID,b.zidingyiStuID as zidingyiStuID,b.stuName as stuName,d.subjectName as subjectName,c.kechengName as kechengName, &apos;按起止日期计费&apos; as JifeiStyleName,e.campusName campusName, " +
            "(SELECT staffName from pxstafftable where id=(select teacherID from pxpaiketeachertable where paikeID=h.id LIMIT 1) LIMIT 1 ) as staffName, " +
            "TIMESTAMPDIFF(DAY,now(),a.endDate)+2 as DAYs, " +
            "g.className as className,a.startDate as startDate,a.endDate as endDate, " +
            "(case WHEN TIMESTAMPDIFF(DAY,now(),a.endDate)+2 &lt; (SELECT defaultValue from pxsysparamdefaulttable where id=12) THEN '课时预警' ELSE'' END) as isKsyj, " +
            "(case WHEN a.isShow=1 THEN '启用' ELSE'不启用' END) as isShow  " +
            "from pxbuxikechengtable as a " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxkechengtable as c on a.kechengID=c.id " +
            "LEFT JOIN pxsubjecttable as d on c.subjectID=d.id " +
            "LEFT JOIN pxcampustable as e on b.campusID=e.id " +
            "LEFT JOIN pxstuclasstable as f on a.id=f.buxiID " +
            "LEFT JOIN pxclasstable as g on f.classID=g.id " +
            "LEFT JOIN pxpaiketable AS h ON h.classID = g.id\n" +
            "where  1=1 and a.jifeiStyleID=3 AND e.isOpen !=2 and (b.buxiStateID = 2 or b.buxiStateID = 3 or b.buxiStateID = 6) " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<RemainDaysVo> getRemainDaysPage(Page page, @Param("ew") QueryWrapper queryWrapper);


    /**
     * 获取要修改的学员剩余课时|学费，分页显示
     */
    @Select("<script>" +
            "select a.id as id,e.campusName as campusName,a.stuID as stuID,d.stuName as stuName,d.zidingyiStuID as zidingyiStuID,c.subjectName as subjectName, " +
            "b.kechengName as kechengName,a.originalprice as OldPrice,a.kechengprice as kechengprice,a.remainkeshi as remainkeshi,d.remainXuefei as remainXuefei " +
            "from pxbuxikechengtable AS a " +
            "LEFT JOIN pxkechengtable as b on a.kechengID=b.id " +
            "LEFT JOIN pxsubjecttable as c on b.subjectID=c.id " +
            "LEFT JOIN pxstutable as d on a.stuID= d.id " +
            "LEFT JOIN pxcampustable as e on d.campusID=e.id " +
            "where e.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<UpdatekeshiAndXFVo> getUpdatekeshiAndXFPage(Page page, @Param("ew") QueryWrapper queryWrapper);


    /**
     * 人工扣课时时
     * 获取学员课程的全部课时
     *
     * @return
     */
    @Select("<script>" +
            "SELECT sum(remainkeshi) as SumR from pxbuxikechengtable where stuID=#{stuID} and kechengID=#{kechengID} and qiyeID=#{qiyeID} "
            + "</script>")
    List<SumbxRemainVo> getSumRks(Long stuID, Long kechengID, Long qiyeID);


    /**
     * 人工消课
     * 家长推送孩子的剩余课时
     *
     * @param stuID
     * @return
     */
    @Select("<script>" +
            "SELECT sum(remainkeshi) as SumR from pxbuxikechengtable where stuID=#{stuID} "
            + "</script>")
    List<SumbxRemainVo> getSumzongRks(Long stuID);

    /**
     * 自动消课
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,e.campusName as campusName,b.stuName as stuName,g.staffName as banzhuren,d.subjectName as subjectName,\n" +
            "c.kechengName as kechengName,h.className as className,f.id as autoID,f.teaIDs as teaIDs,a.stuID stuID,b.zidingyiStuID zidingyiStuID,\n" +
            "f.teaNames as teaNames,f.keshiNum as keshiNum,a.startDate as startDate,a.endDate as endDate,\n" +
            "f.state as state\n" +
            "FROM pxbuxikechengtable as a \n" +
            "LEFT JOIN pxstutable as b on a.stuID=b.id\n" +
            "LEFT JOIN pxkechengtable as c on a.kechengID=c.id\n" +
            "LEFT JOIN pxsubjecttable as d on c.subjectID=d.id\n" +
            "LEFT JOIN pxcampustable as e on b.campusID=e.id\n" +
            "LEFT JOIN pxautoxiaoketable as f on a.id =f.buxiID\n" +
            "LEFT JOIN pxstafftable as g on b.banzhurenTeacherID=g.id\n" +
            "LEFT JOIN pxclasstable as h on f.classID=h.id\n" +
            "WHERE a.jifeiStyleID=3 and e.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<AutoxiaokeVO> getAutoPage(Page page, @Param("ew") QueryWrapper queryWrapper);


    /**
     * 导出自动消课
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,e.campusName as campusName,b.stuName as stuName,g.staffName as banzhuren,d.subjectName as subjectName,\n" +
            "c.kechengName as kechengName,h.className as className,f.id as autoID,f.teaIDs as teaIDs,\n" +
            "f.teaNames as teaNames,f.keshiNum as keshiNum,a.startDate as startDate,a.endDate as endDate,\n" +
            "(case WHEN f.state=1 THEN '未设置' ELSE '已设置' END) as state\n" +
            "FROM pxbuxikechengtable as a \n" +
            "LEFT JOIN pxstutable as b on a.stuID=b.id\n" +
            "LEFT JOIN pxkechengtable as c on a.kechengID=c.id\n" +
            "LEFT JOIN pxsubjecttable as d on c.subjectID=d.id\n" +
            "LEFT JOIN pxcampustable as e on b.campusID=e.id\n" +
            "LEFT JOIN pxautoxiaoketable as f on a.id =f.buxiID\n" +
            "LEFT JOIN pxstafftable as g on b.banzhurenTeacherID=g.id\n" +
            "LEFT JOIN pxclasstable as h on f.classID=h.id\n" +
            "WHERE a.jifeiStyleID=3 and e.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<AutoxiaokeVO> ExportAutoPage(@Param("ew") QueryWrapper queryWrapper);


    /**
     * 刷卡消课时
     * 获取班级总人数
     *
     * @param paike
     * @return
     */
    @Select("<script>" +
            "SELECT COUNT(a.id) as stuCount \n" +
            "from pxbuxikechengtable as a \n" +
            "LEFT JOIN pxxuanketable as b on a.id=b.buxiID \n" +
            "LEFT JOIN pxstutable as c on a.stuID=c.id \n" +
            "where a.isShow=1 and c.id=b.stuID and (c.buxiStateID=2 or c.buxiStateID=1) and b.paikeID=#{paike}" +
            "</script>")
    List<classstuCountVo> getstuCount(Long paike);


    /**
     * 获取补习课程
     *
     * @param stuID
     * @param paikeID
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxbuxikechengtable as a \n" +
            "LEFT JOIN pxxuanketable as b on a.id=b.buxiID\n" +
            "WHERE a.stuID=#{stuID} and b.paikeID=#{paikeID} and a.isShow=1 and a.qiyeID=#{qiyeID} " +
            "</script>")
    List<Pxbuxikechengtable> getbuxi(Long stuID, Long paikeID, Long qiyeID);


    @Select("<script>" +
            "SELECT ( CASE WHEN SUM(a.remainkeshi) is not null THEN SUM(a.remainkeshi) ELSE 0 END )  FROM pxbuxikechengtable a " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " and ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    String getbxremainkeshi(@Param("ew") Wrapper wrapper);
}