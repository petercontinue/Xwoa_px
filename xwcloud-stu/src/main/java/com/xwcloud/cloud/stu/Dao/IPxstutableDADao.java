package com.xwcloud.cloud.stu.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.zafeiInfoVo;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstutable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPxstutableDADao extends BaseMapper<Pxstutable> {
    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "zidingyiStuID", property = "zidingyiStuID"),
            @Result(column = "stuName", property = "stuName"),
            @Result(column = "parentTel", property = "parentTel"),
            @Result(column = "passwd", property = "passwd"),
            @Result(column = "activity", property = "activity"),
            @Result(column = "stuSex", property = "stuSex"),
            @Result(column = "stuTel", property = "stuTel"),
            @Result(column = "parentTelRelation", property = "parentTelRelation"),
            @Result(column = "buxiStateID", property = "buxiStateID"),
            @Result(column = "stuGradeID", property = "stuGradeID"),
            @Result(column = "stuXuexi", property = "stuXuexi"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "banzhurenTeacherID", property = "banzhurenTeacherID"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unionid", property = "unionid"),
            @Result(column = "remainXuefei", property = "remainXuefei"),
            @Result(column = "remainChongzhi", property = "remainChongzhi"),
            @Result(column = "stubirth", property = "stubirth"),
            @Result(column = "jifenNum", property = "jifenNum"),
            @Result(column = "IDImage", property = "IDImage"),
            @Result(column = "IDnumber", property = "IDnumber"),
            @Result(column = "roomid", property = "roomid"),
            @Result(column = "oldSchoolTeacher", property = "oldSchoolTeacher"),
            @Result(column = "oldSchool", property = "oldSchool"),
            @Result(column = "ruxuechengji", property = "ruxuechengji"),
            @Result(column = "laoshiyaoqiu", property = "laoshiyaoqiu"),
            @Result(column = "jijixing", property = "jijixing"),
            @Result(column = "xingge", property = "xingge"),
            @Result(column = "lastHuifangTime", property = "lastHuifangTime"),
            @Result(column = "nextHuifangTime", property = "nextHuifangTime"),
            @Result(column = "stuPhoto", property = "stuPhoto"),
            @Result(column = "yxFromID", property = "yxFromID"),
            @Result(column = "yxLevelID", property = "yxLevelID"),
            @Result(column = "yixiangkemu", property = "yixiangkemu"),
            @Result(column = "yxshichangTeacherID", property = "yxshichangTeacherID"),
            @Result(column = "yxfenpeistaffID", property = "yxfenpeistaffID"),
            @Result(column = "yxfenpeiDate", property = "yxfenpeiDate"),
            @Result(column = "daoruDate", property = "daoruDate"),
            @Result(column = "lastFollowDate", property = "lastFollowDate"),
            @Result(column = "luruType", property = "luruType"),
            @Result(column = "dengjiTeacherID", property = "dengjiTeacherID"),
            @Result(column = "dengjiTime", property = "dengjiTime"),
            @Result(column = "nextGenjinTime", property = "nextGenjinTime"),
            @Result(column = "tingkeTime", property = "tingkeTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxstutable"
            + "</script>")
    List<Pxstutable> getAllList();


    @Select("<script>" +
            "SELECT * from  pxstutable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstutable> selectstu(@Param("ew") QueryWrapper queryWrapper);


    @Select("<script>" +
            "SELECT id id ,stuGradeName name from  pxstugradetable where qiyeID=#{qiyeID} " +
            "</script>")
    List<listVo> Getnianji(Long qiyeID);


    /**
     * 获取所有学生（带权限）
     * 带年级。校区
     *
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.stuName name,b.id stuGradeID,b.stuGradeName stuGradeName,a.campusID campusID,a.parentTel " +
            "from pxstutable a\n" +
            "LEFT JOIN pxstugradetable b on a.stuGradeID=b.id\n" +
            "LEFT JOIN pxcampustable c on a.campusID=c.id\n" +
            "WHERE c.isOpen !=2 and (a.buxiStateID=1 or a.buxiStateID=2 or a.buxiStateID=3 or a.buxiStateID=6)"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<allstuVo> GetAllSelectedStuNames(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT a.id id ,a.stuName name " +
            "from pxstutable a \n" +
            "LEFT JOIN pxstugradetable b on a.stuGradeID=b.id\n" +
            "LEFT JOIN pxcampustable c on a.campusID=c.id\n" +
            "WHERE c.isOpen !=2 and (a.buxiStateID=1 or a.buxiStateID=2 or a.buxiStateID=3 or a.buxiStateID=6)"+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<listVo> getallstu(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 学员档案
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select a.id as stuID,a.stuName stuName,a.zidingyiStuID zidingyiStuID,a.parentTel parentTel," +
            "a.buxiStateID buxiStateID,\n" +
            "b.campusName as campusName,c.stuGradeName as stuGradeName,d.staffName as banzhuren,\n" +
            "(SELECT (case WHEN  COUNT(id)&gt; 0 THEN  COUNT(id) ELSE 0 END) from pxstukaoqingtable where stuID=a.id) ZongKaoQing,\n" +
            "(SELECT (case WHEN  COUNT(id)&gt; 0 THEN  COUNT(id) ELSE 0 END) from pxstukaoqingtable where stuID=a.id and kaoqingStyle=1) ZhangChangkaoqin,\n" +
            "(SELECT (case WHEN  COUNT(id)&gt; 0 THEN  COUNT(id) ELSE 0 END) from pxstukaoqingtable where stuID=a.id and kaoqingStyle=3) Kuangke,\n" +
            "(SELECT (case WHEN  COUNT(id)&gt; 0 THEN  COUNT(id) ELSE 0 END) from pxqiandaninfotable where stuID=a.id) qdSum,\n" +
            //"(SELECT (case WHEN  COUNT(j.id)&gt; 0 THEN  COUNT(j.id) ELSE 0 END) from pxkeshistutable j LEFT JOIN pxkechengtable k on j.kechengID=k.id where j.stuID=a.id GROUP BY j.kechengID,k.subjectID) ShangkeCount,a.jifenNum,\n" +
            "(SELECT (case WHEN  COUNT(j.id)&gt; 0 THEN  COUNT(j.id) ELSE 0 END) from pxkeshistutable j  where j.stuID=a.id ) ShangkeCount,a.jifenNum,\n" +
            "(SELECT (case WHEN COUNT(id)&gt;0 THEN SUM(money) ELSE 0 END) from pxdaijinquantable where stuID=a.id) daijingquan,\n" +
            "(SELECT (case WHEN  COUNT(id)&gt; 0 THEN  COUNT(id) ELSE 0 END) from pxkeshistutable where stuID=a.id) kehaoSum,\n" +
            "(SELECT (case WHEN  COUNT(id)&gt; 0 THEN  COUNT(id) ELSE 0 END) from pxmanyidutable where stuID=a.id) ManyiduCount,\n" +
            "(SELECT (case WHEN  COUNT(id)&gt; 0 THEN  COUNT(id) ELSE 0 END) from pxstuhuifangtable where stuID=a.id) huifangCount \n" +
            "from pxstutable as a \n" +
            "left join pxcampustable b on a.campusID=b.id \n" +
            "left join pxstugradetable c  on a.stuGradeID=c.id \n" +
            "left join pxstafftable d on a.banzhurenTeacherID = d.id \n" +
            "WHERE 1=1 and b.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<stuCampusVo> getStuCampusList(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 导出学员档案数据表集
     *
     * @return
     */

    @Select("<script>" +
            "SELECT\n" +
            "\tc.campusName,\n" +
            "\t(case WHEN a.zidingyiStuID is not null THEN a.zidingyiStuID ELSE a.id END) stuID,\n" +
            "\ta.stuName,\n" +
            "\tb.stuGradeName,\n" +
            "\t( SELECT (case WHEN COUNT(id) &gt; 0 THEN staffName ELSE '未分配' END)\tFROM pxstafftable WHERE id = a.banzhurenTeacherID ) AS banzhuren,\n" +
            "\t(case WHEN a.buxiStateID=1 THEN '意向' ELSE (case WHEN  a.buxiStateID=2 THEN '在读' ELSE (case WHEN a.buxiStateID=3 THEN '停课' ELSE (case WHEN a.buxiStateID=4 THEN '结课' ELSE  (case WHEN a.buxiStateID=5 THEN '退费' ELSE '休眠' END) END) END) END) END) stustate,\n" +
            "\td.staffName,\n" +
            "\ta.dengjiTime,\n" +
            "\ta.parentTel,\n" +
            "\t(case WHEN e.oldSchoolTeacherName is not null THEN e.oldSchoolTeacherName ELSE '' END) oldTeacher,\n" +
            "\t(case WHEN f.oldSchoolName is not null THEN f.oldSchoolName ELSE '' END) oldschoolName,\n" +
            "\ta.stubirth,\n" +
            "\t\t(SELECT TIMESTAMPDIFF(YEAR,a.stubirth,now())) as age,\n" +
            "( CASE WHEN a.stuTel IS NOT NULL THEN a.stuTel ELSE '' END ) stuTel,\n" +
            "( CASE WHEN a.stuXuexi IS NOT NULL THEN a.stuXuexi ELSE '' END ) beizhu,\n" +
            "( SELECT GROUP_CONCAT(DISTINCT classID) FROM pxstuclasstable z LEFT JOIN pxbuxikechengtable x ON z.buxiID = x.id WHERE x.stuID=a.id ) classID \n" +
            "FROM\n" +
            "\tpxstutable AS a\n" +
            "\tLEFT JOIN pxstugradetable AS b ON a.stuGradeID = b.id\n" +
            "\tLEFT JOIN pxcampustable AS c ON a.campusID = c.id\n" +
            "\tLEFT JOIN pxstafftable AS d ON a.dengjiTeacherID = d.id\n" +
            "\tLEFT JOIN pxoldschoolteachertable AS e ON a.oldSchoolTeacher = e.oldSchoolTeacherID\n" +
            "\tLEFT JOIN pxoldschooltable AS f ON a.oldSchool = f.oldSchoolID\n" +
            "WHERE\n" +
            "(( #{type}=1 and ( SELECT find_in_set(#{classID},GROUP_CONCAT(DISTINCT classID))  FROM pxstuclasstable z LEFT JOIN pxbuxikechengtable x ON z.buxiID = x.id WHERE x.stuID=a.id ) ) or (\n" +
            "#{type}=2))" +
            " and c.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<stufilesVo> ExportstuFiles(int type, String classID, @Param("ew") QueryWrapper queryWrapper);


    @Select("<script>" +
            "SELECT\n" +
            "\tc.campusName,\n" +
            "\t(case WHEN b.zidingyiStuID is not null THEN b.zidingyiStuID ELSE b.id END) stuID,\n" +
            "\tb.stuName,\n" +
            "\td.stuGradeName,\n" +
            "\tb.parentTel,\n" +
            "\tb.stuSex,\n" +
            "\t(case WHEN a.beizhu is not null THEN a.beizhu ELSE '' END) beizhu,\n" +
            "\t(case WHEN (b.roomid &gt; 0) THEN '是' ELSE '否' END) roomids,\n" +
            "\t(case WHEN b.stuTel is not null THEN b.stuTel ELSE '' END) stuTel,\n" +
            "\ta.HetongMoney,\n" +
            "\ta.shishouTotalMoney,\n" +
            "\t(SELECT (case WHEN COUNT(id) &gt; 0 THEN sum(zongMoney) ELSE 0 END) from pxqiandaninfo2table WHERE qianInfoTabID=a.id) zaiMoney,\n" +
            "\t(SELECT (case WHEN COUNT(z.id) &gt; 0 THEN money ELSE 0 END) from pxdaijinquantable z WHERE z.qiandanID=a.id and z.stuID=b.id) money ,\n" +
            "\ta.qiandandate,\n" +
            "\t(case WHEN a.zhuanjieshaoID is not null AND a.zhuanjieshaoID&gt;0 THEN '是' ELSE '否' END) zhuanjieshao,\n" +
            "\te.staffName,\n" +
            "\t(case WHEN a.moneyStyle=1 THEN '新签' ELSE (case WHEN a.moneyStyle=2 THEN '续费' ELSE '-' END) END) moneyStyle,\n" +
            "\t(SELECT (case WHEN COUNT(x.id) &gt; 0 THEN GROUP_CONCAT(DISTINCT c.moneystyleName) ELSE '' END)  from pxqiandanpaymoney x LEFT JOIN pxpaymoneystyletable c on x.paymoneyStyleID=c.id  WHERE x.qiandanID=a.id ) moneystyleName\n" +
            "FROM\n" +
            "\tpxqiandaninfotable AS a\n" +
            "\tLEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "\tLEFT JOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "\tLEFT JOIN pxstugradetable AS d ON b.stuGradeID = d.id\n" +
            "\tLEFT JOIN pxstafftable e on a.recordInStaffID = e.id" +
            " WHERE c.isOpen!=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<exportstuQdVo> ExportstuQD(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT\n" +
            "\tf.campusName,\n" +
            "\t(case WHEN b.zidingyiStuID is not null THEN b.zidingyiStuID ELSE b.id END) stuID,\n" +
            "\tb.stuName,\n" +
            "\t\t(case WHEN b.buxiStateID=1 THEN '意向' ELSE (case WHEN  b.buxiStateID=2 THEN '在读' ELSE (case WHEN b.buxiStateID=3 THEN '停课' ELSE (case WHEN b.buxiStateID=4 THEN '结课' ELSE  (case WHEN b.buxiStateID=5 THEN '退费' ELSE '休眠' END) END) END) END) END) stustate,\n" +
            "\td.subjectName,\n" +
            "\tg.buxiStyleName,\n" +
            "\tc.kechengName,\n" +
            "\t(case WHEN h.classTimeStyleName=-1 THEN '一次' ELSE (case WHEN  h.classTimeStyleName=-2 THEN '一天' ELSE  h.classTimeStyleName END) END) classTimeStyleName,\n" +
            "\t(case WHEN c.jifeiStyleID=1 THEN '按课时计费' ELSE (case WHEN c.jifeiStyleID=2 THEN '按课时包计费' ELSE '按起止日期计费' END) END) jifeiStyle,\n" +
            "\ta.startDate,\n" +
            "\ta.endDate,\n" +
            "\ta.originalprice,\n" +
            "\ta.kechengprice,\n" +
            "\te.staffName \n" +
            "FROM\n" +
            "\tpxqiandansubjecttable AS a\n" +
            "\tLEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "\tLEFT JOIN pxkechengtable AS c ON a.kechengID = c.id\n" +
            "\tLEFT JOIN pxsubjecttable AS d ON c.subjectID = d.id\n" +
            "\tLEFT JOIN pxstafftable AS e ON b.dengjiTeacherID = e.id\n" +
            "\tLEFT JOIN pxcampustable AS f ON b.campusID = f.id\n" +
            "\tLEFT JOIN pxbuxistyletable AS g ON c.buxiStyleID = g.id\n" +
            "\tLEFT JOIN pxclasstimestyletable h ON c.classTimeStyleID = h.id" +
            " WHERE f.isOpen!=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<ExportKcVo> Exportstukc(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT\n" +
            "\tb.campusName,\n" +
            "\t( CASE WHEN a.zidingyiClassID IS NOT NULL THEN a.zidingyiClassID ELSE a.id END ) classID,\n" +
            "\ta.className,\n" +
            "\t( CASE WHEN a.isShow = 0 THEN '启用' ELSE '不启用' END ) isSHow,\n" +
            "\t( CASE WHEN a.is1v1Class = 1 THEN '是' ELSE '否' END ) is1v1Class,\n" +
            "\ta.addTime,\n" +
            "\t( SELECT COUNT( id ) FROM pxstuclasstable WHERE classID = a.id ) numbs,\n" +
            "\tc.staffName \n" +
            "FROM\n" +
            "\tpxclasstable AS a\n" +
            "\tLEFT JOIN pxcampustable AS b ON a.campusID = b.id\n" +
            "\tLEFT JOIN pxstafftable AS c ON a.addStaffID = c.id" +
            " WHERE b.isOpen!=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<ExportstuClassVo> ExportstuClass(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT\n" +
            "\tg.campusName stucampusName,\n" +
            "\t( CASE WHEN c.zidingyiStuID IS NOT NULL THEN c.zidingyiStuID ELSE a.stuID END ) stuID,\n" +
            "\tc.stuName,\n" +
            "\tc.parentTel,\n" +
            "\tc.stuTel,\n" +
            "\tf.stuGradeName,\n" +
            "\th.kechengName,\n" +
            "\t(SELECT campusName from pxcampustable where id=e.campusID and isOpen!=2) classCampusName,\n" +
            "\t( CASE WHEN e.zidingyiClassID IS NOT NULL THEN e.zidingyiClassID ELSE a.id END ) classID,\n" +
            "\te.className className,\n" +
            "\t( SELECT COUNT( id ) FROM pxstuclasstable WHERE classID = d.classID ) numbs,\n" +
            "\te.addTime ,\n" +
            "\t(SELECT staffName from pxstafftable where id=e.addStaffID) jinbanren\n" +
            "FROM\n" +
            "\tpxxuanketable AS a\n" +
            "\tLEFT JOIN pxbuxikechengtable AS b ON a.buxiID = b.id\n" +
            "\tLEFT JOIN pxstutable AS c ON a.stuID = c.id\n" +
            "\tLEFT JOIN pxpaiketable AS d ON a.paikeID = d.id\n" +
            "\tLEFT JOIN pxclasstable AS e ON d.classID = e.id\n" +
            "\tLEFT JOIN pxstugradetable AS f ON c.stuGradeID = f.id\n" +
            "\tLEFT JOIN pxcampustable AS g ON c.campusID = g.id\n" +
            "\tLEFT JOIN pxkechengtable AS h ON d.kechengID = h.id" +
            " WHERE g.isOpen!=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<ClassMesVo> ExportClassMes(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT\n" +
            "\te.campusName,\n" +
            "\t( CASE WHEN b.zidingyiStuID IS NOT NULL THEN b.zidingyiStuID ELSE b.id END ) stuID,\n" +
            "\tb.stuName,\n" +
            "\tf.subjectName,\n" +
            "\tg.buxiStyleName,\n" +
            "\tc.kechengName,\n" +
            "\t(case WHEN d.classTimeStyleName=-1 THEN '一次' ELSE (case WHEN  d.classTimeStyleName=-2 THEN '一天' ELSE  d.classTimeStyleName END) END) classTimeStyleName,\n" +
            "\ta.startDate,\n" +
            "\ta.endDate,\n" +
            "\ta.originalprice,\n" +
            "\ta.kechengprice,\n" +
            "\ta.keshiNum,\n" +
            "\ta.remainkeshi \n" +
            "FROM\n" +
            "\tpxbuxikechengtable AS a\n" +
            "\tLEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "\tLEFT JOIN pxkechengtable AS c ON a.kechengID = c.id\n" +
            "\tLEFT JOIN pxclasstimestyletable AS d ON c.classTimeStyleID = d.id\n" +
            "\tLEFT JOIN pxcampustable AS e ON b.campusID = e.id\n" +
            "\tLEFT JOIN pxsubjecttable AS f ON c.subjectID = f.id\n" +
            "\tLEFT JOIN pxbuxistyletable AS g ON c.buxiStyleID = g.id\n" +
            " WHERE e.isOpen!=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<remainkeshiVo> ExportRekeshi(@Param("ew") QueryWrapper queryWrapper);


    /**
     * 学员积分详情
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id ,b.stuName stuName,a.integral integral,a.type type,a.oldIntegral oldIntegral,a.createTime createTime,a.stuID,b.jifenNum shengyujifen,c.staffName staffName,a.remark remark,\n" +
            "(select staffName from pxstafftable where id = b.banzhurenTeacherID) banzhuren " +
            "from pxjifentable a \n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxstafftable c on a.staffID=c.id\n" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<stuIntegerVo> getstuIntegraInfoPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 代金券详情
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.money money,a.creatTime creatTime,c.staffName staffName\n" +
            "from pxdaijinquantable a \n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxstafftable c on a.staffID =c.id " +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<daijinquanVo> getdaijinquanInfoPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT a.id id ,b.kechengName kechengName,c.subjectName subjectName,a.kechengPrice kechengPrice,a.keshiNum keshiNum,(a.kechengPrice * a.keshiNum) Payxuefei\n" +
            "from pxkeshistutable a \n" +
            "LEFT JOIN pxkechengtable b on a.kechengID =b.id\n" +
            "LEFT JOIN pxsubjecttable c on b.subjectID =c.id " +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<kehaoInfoVo> getkehaoInfoPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 学员上课信息
     *
     * @param stuID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT c.id subjectID,a.kechengID kechengID,b.kechengName kechengName,c.subjectName subjectNamem,\n" +
            "(SELECT stuName from pxstutable where id=#{stuID} and qiyeID=#{qiyeID} ) stuName,\n" +
            "(SELECT sum(keshiNum) from pxkeshistutable WHERE stuID=#{stuID} and qiyeID=#{qiyeID} and kechengID=a.kechengID ) keshiCount ,\n" +
            "(SELECT haveClassDate from pxkeshistutable where stuID=#{stuID} and qiyeID=#{qiyeID} ORDER BY haveClassDate limit 1) firstDate,\n" +
            "(SELECT haveClassDate from pxkeshistutable where stuID=#{stuID} and qiyeID=#{qiyeID} ORDER BY haveClassDate DESC limit 1) lastDate,\n" +
            "(SELECT COUNT(id) from pxbuxikechengtable where stuID=#{stuID} and qiyeID=#{qiyeID} ) haveke \n" +
            "from pxkeshistutable a \n" +
            "LEFT JOIN pxkechengtable b on a.kechengID =b.id\n" +
            "LEFT JOIN pxsubjecttable c on b.subjectID =c.id\n" +
            "WHERE 1=1 \n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"+
            "GROUP BY a.kechengID,b.subjectID,a.stuID,b.kechengName,c.subjectName"
            + "</script>")
    Page<stuskInfoVo> getstuskInfoPage(Page page, Long stuID, Long qiyeID,@Param("ew") QueryWrapper queryWrapper);

    /**
     * 学员信息
     *
     * @param stuID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.*,a.campusID campusID,b.campusName campusName,a.stuGradeID stuGradeID,c.stuGradeName stuGradeName,d.oldSchoolName oldSchoolName,e.oldSchoolTeacherName oldSchoolTeacherName,a.buxiStateID buxiStateID,a.remainXuefei remainXuefei,a.remainChongzhi remainChongzhi,a.jifenNum jifenNum,\n" +
            "(SELECT staffName from pxstafftable where id=a.dengjiTeacherID) dengjiTeachers, a.dengjiTeacherID,a.parentTelRelation, \n" +
            "(SELECT zhuanjieshaoID from pxqiandaninfotable WHERE stuID=a.id and moneyStyle=1 LIMIT 1) iszhuanjieshao\n" +
            "from pxstutable a \n" +
            "LEFT JOIN pxcampustable b on a.campusID=b.id\n" +
            "LEFT JOIN pxstugradetable c on a.stuGradeID=c.id\n" +
            "LEFT JOIN pxoldschooltable d on a.oldSchool=d.oldSchoolID\n" +
            "LEFT JOIN pxoldschoolteachertable e on a.oldSchoolTeacher=e.oldSchoolTeacherID\n" +
            "WHERE a.id=#{stuID} and a.qiyeID=#{qiyeID} and b.isOpen !=2"
            + "</script>")
    List<stuInfoVo> getstuInfoPage(Long stuID, Long qiyeID);

    /**
     * 学员详情获取签单信息
     *
     * @return
     */
    @Select("<script>" +
            "SELECT\n" +
            "\tsum( a.HetongMoney ) allhtmoney,\n" +
            "\tsum( yejiMoney ) allyeji,\n" +
            "\t( CASE WHEN GROUP_CONCAT( a.youhuiID ) IS NULL THEN 0 ELSE SUM( a.youhuijine ) END ) allyouhui,\n" +
            "\t(\n" +
            "\tGROUP_CONCAT( DISTINCT staffName )) yejistaff,\n" +
            "\tCOUNT( a.id ) qiandanNum,\n" +
            "\t(\n" +
            "\tGROUP_CONCAT( DISTINCT e.moneystyleName )) yejitype \n" +
            "FROM\n" +
            "\tpxqiandaninfotable a\n" +
            "\tLEFT JOIN pxqiandanstafftable b ON a.id = b.qiandanID\n" +
            "\tLEFT JOIN pxstafftable c ON b.staffID = c.id\n" +
            "\tJOIN pxqiandanpaymoney d on a.id=d.qiandanID\n" +
            "\tLEFT JOIN pxpaymoneystyletable e ON d.paymoneyStyleID = e.id  " +
            "WHERE 1=1 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<stuInfogetqiandanVo> getstuInfoqiandan(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 学员签单详情
     *
     * @param page
     * @return
     */
    @Select("<script>" +
            "SELECT a.id stuID,a.stuName,a.remainXuefei,b.id qdID,b.*,(select staffName from pxstafftable where id=b.recordInStaffID) staffNames,c.campusName,\n" +
            "(SELECT (case WHEN COUNT(id)&gt;0 THEN money ELSE 0 END) from pxdaijinquantable where qiandanID=b.id) daijinquan,\n" +
            "(b.shishouTotalMoney +(SELECT (case WHEN COUNT(id)&gt;0 THEN money ELSE 0 END) from pxdaijinquantable where qiandanID=b.id)-(SELECT (case WHEN COUNT(id)&gt;0 THEN Sum(zongMoney) ELSE 0 END) from pxqiandaninfo2table where qianInfoTabID=b.id)-(SELECT (case WHEN COUNT(id)&gt;0 THEN Sum(SumMoney) ELSE 0 END) from pxqiandansupplies where QiandaninfoID=b.id)) kechengMoney\n" +
            "from pxstutable a\n" +
            "LEFT JOIN pxqiandaninfotable b on a.id=b.stuID\n" +
            "LEFT JOIN pxcampustable c on a.campusID=c.id " +
            "where 1=1 "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<stuqiandanInfoVo> getstuQiandanInfoPage(Page page,@Param("ew") QueryWrapper queryWrapper);

    /**
     * 课程详情
     *
     * @param page
     * @param stuID
     * @param qdID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.kechengprice kechengprice,a.zongjia zongjia,b.kechengName kechengName,c.buxiStyleName buxiStyleName,d.subjectName subjectName,\n" +
            "(SELECT remainkeshi from pxbuxikechengtable WHERE kechengID=a.kechengID and kechengprice=a.kechengprice and stuID=#{stuID}) remainkeshi,\n" +
            "(SELECT startDate from pxbuxikechengtable WHERE kechengID=a.kechengID and kechengprice=a.kechengprice and stuID=#{stuID}) startDate,\n" +
            "(SELECT endDate from pxbuxikechengtable WHERE kechengID=a.kechengID and kechengprice=a.kechengprice and stuID=#{stuID}) endDate,\n" +
            "(SELECT jifeiStyleID from pxbuxikechengtable WHERE kechengID=a.kechengID and kechengprice=a.kechengprice and stuID=#{stuID}) JifeiStyle,\n" +
            "(SELECT (case WHEN discount is null THEN 10 ELSE discount END)  from pxqiandansubjecttable where stuID=#{stuID} and qianDanInfoID=#{qdID} ) zhekou\n" +
            "from pxqiandansubjecttable a \n" +
            "LEFT JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "LEFT JOIN pxbuxistyletable c on b.buxiStyleID=c.id\n" +
            "LEFT JOIN pxsubjecttable d on b.subjectID=d.id\n" +
            "WHERE a.stuID=#{stuID} and a.qianDanInfoID=#{qdID} and a.qiyeID=#{qiyeID}"
            + "</script>")
    Page<kcInfoLookVo> getkechengInfoPage(Page page, Long stuID, Long qdID, Long qiyeID);

    /**
     * 杂费详情
     *
     * @param page
     * @param qdID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.zongMoney zongMoney,a.type,b.otherMoneyName \n" +
            "from pxqiandaninfo2table a \n" +
            "LEFT JOIN pxqiandanothermoneytable b on a.qianDanOtherMoneyID=b.id\n" +
            "WHERE a.qianInfoTabID=#{qdID} and a.qiyeID=#{qiyeID} and a.zongMoney &gt; 0" +
            "</script>")
    Page<zafeiInfoVo> getzafeiInfoPage(Page page, Long qdID, Long qiyeID);

    /**
     * 转校区
     *
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.campusName name FROM pxcampustable a where a.id !=  (SELECT campusID from pxstutable WHERE id=#{stuID} ) and qiyeID=#{qiyeID}" +
            "</script>")
    List<listVo> getzxqcampusList(Long stuID, Long qiyeID);

    @Select("<script>" +
            "SELECT a.id id,a.jifeiStyleID jifeiStyleID,(case WHEN a.jifeiStyleID=1 THEN '课时' ELSE (case WHEN a.jifeiStyleID=2 THEN '课时包' ELSE '起止日期' END) END) jfName,\n" +
            "a.kechengprice kechengprice,a.keshiNum keshiNum,a.remainkeshi remainkeshi,a.zongjia zongjia,\n" +
            "a.startDate startDate,a.endDate endDate,b.kechengName kechengName,a.kechengID kechengID,c.buxiStyleName buxiStyleName,b.buxiStyleID buxiStyleID,\n" +
            "(a.remainkeshi * a.kechengprice) remainjine\n" +
            "from pxbuxikechengtable a \n" +
            "LEFT JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "LEFT JOIN pxbuxistyletable c on b.buxiStyleID=c.id " +
            "WHERE a.stuID=#{stuID} and a.qiyeID=#{qiyeID}" +
            "</script>")
    List<zxqstubuxiVo> getzxqbuxiList(Long stuID, Long qiyeID);

    /**
     * 积分页面获取课程
     *
     * @param buxiStyleName
     * @param JifeiStyle
     * @param campusID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.kechengName as name from pxkechengtable a \n" +
            "LEFT JOIN pxbuxistyletable b on a.buxiStyleID=b.id \n" +
            "LEFT JOIN pxsubjecttable c on a.subjectID=c.id \n" +
            "LEFT JOIN pxcampustable d on c.campusID=d.id \n" +
            "WHERE d.isOpen!=2 and a.isShow=TRUE and ((#{buxiStyleName}='一对一' and b.buxiStyleName=#{buxiStyleName} ) or (#{buxiStyleName}!='一对一')) " +
            "and ((#{JifeiStyle}=3 and a.jifeiStyleID=3) or (#{JifeiStyle}!=3 and a.jifeiStyleID!=3)) and c.campusID=#{campusID} and a.qiyeID=#{qiyeID}" +
            "</script>")
    List<listVo> getallJFkcshuList(String buxiStyleName, int JifeiStyle, Long campusID, Long qiyeID);

    //region 班主任分配

    /**
     * 班主任分配
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select a.stuName as stuName,a.id as stuID,a.zidingyiStuID zidingyiStuID,pxstafftable.staffName as Banzhuren,pxcampustable.campusName," +
            "(SELECT GROUP_CONCAT(aa.className) from pxclasstable as aa LEFT JOIN pxstuclasstable as bb on aa.id=bb.classID LEFT JOIN pxbuxikechengtable as cc on bb.buxiID=cc.id where cc.stuID=a.id) as className " +
            "from pxstutable as a " +
            "left join pxstafftable on a.banzhurenTeacherID = pxstafftable.id " +
            "left join pxstugradetable on a.stuGradeID = pxstugradetable.stuGradeName " +
            "left join pxcampustable on a.campusID = pxcampustable.id " +
            " WHERE pxcampustable.isOpen !=2 and (a.buxiStateID =2 or a.buxiStateID =3 or a.buxiStateID =6) " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<stuTearchVo> getStuTearch(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 导出班主任分配
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select a.stuName as stuName,a.id as stuID,pxstafftable.staffName as Banzhuren,pxcampustable.campusName,a.yxfenpeiDate as yxfenpeiDate," +
            "(select staffName from pxstafftable where id = a.yxfenpeistaffID) as yxfenpeistaffID " +
            "from pxstutable as a " +
            "left join pxstafftable on a.banzhurenTeacherID = pxstafftable.id " +
            "left join pxstugradetable on a.stuGradeID = pxstugradetable.stuGradeName " +
            "left join pxcampustable on a.campusID = pxcampustable.id " +
            " WHERE 1=1 and pxcampustable.isOpen !=2 and (a.buxiStateID =2 or a.buxiStateID =3 or a.buxiStateID =6)" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<stuTearchVo> ExportstuTeacher(@Param("ew") QueryWrapper queryWrapper);
    //endregion

    //region 学员卡

    /**
     * 分页获取学员卡
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select a.stuName as stuName,a.id as stuID,a.zidingyiStuID zidingyiStuID,pxcampustable.campusName as campusName,pxstucardtable.cardNumber as cardID,pxstugradetable.stuGradeName as stuGradeName " +
            "from pxstutable as a " +
            "left join pxstucardtable on a.id = pxstucardtable.stuID " +
            "left join pxstugradetable on a.stuGradeID = pxstugradetable.id " +
            "left join pxcampustable on a.campusID = pxcampustable.id " +
            " WHERE pxcampustable.isOpen !=2 and (a.buxiStateID =2 or a.buxiStateID =3 or a.buxiStateID =6) " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<stuCardVo> getStuCard(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 导出学员卡
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select a.stuName as stuName,a.id as stuID,pxcampustable.campusName as campusName,(case WHEN pxstucardtable.cardNumber is null THEN '无' ELSE pxstucardtable.cardNumber END) cardID,pxstugradetable.stuGradeName as stuGradeName from pxstutable as a " +
            "left join pxstucardtable on a.id = pxstucardtable.stuID " +
            "left join pxstugradetable on a.stuGradeID = pxstugradetable.id " +
            "left join pxcampustable on a.campusID = pxcampustable.id " +
            " WHERE pxcampustable.isOpen !=2 and (a.buxiStateID =2 or a.buxiStateID =3 or a.buxiStateID =6)" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<stuCardVo> ExportStuCard(@Param("ew") Wrapper wrapper);

    //endregion

    //region 学员积分

    /**
     * 分页获取学员积分
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select evalua.*,pxstutable.id as stuID,pxstutable.stuName,pxcampustable.campusName as campusName," +
            "pxstugradetable.stuGradeName as stuGradeName,pxstafftable.staffName as jingbanStaff ," +
            "(select staffName from pxstafftable where id=pxstutable.banzhurenTeacherID) banzhuren, " +
            "pxstutable.zidingyiStuID zidingyiStuID " +
            "from pxjifentable as evalua " +
            "left join pxstutable on evalua.stuID=pxstutable.id " +
            "left join pxcampustable on pxstutable.campusID = pxcampustable.id  " +
            "left join pxstugradetable on pxstutable.stuGradeID=pxstugradetable.id " +
            "left join pxstafftable on evalua.staffID=pxstafftable.id " +
            " WHERE 1=1 and pxcampustable.isOpen !=2 and (pxstutable.buxiStateID =2 or pxstutable.buxiStateID =3 or pxstutable.buxiStateID =6)" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<stuIntegralVo> getStuIntegral(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 分页获取积分排名
     *
     * @param page
     * @return
     */
    @Select("<script>" +
            "select pxcampustable.campusName as campusName,pxstutable.id as stuID,pxstutable.zidingyiStuID zidingyiStuID ,pxstutable.stuName as stuName,pxstafftable.staffName as banzhuren,pxstugradetable.stuGradeName as stuGradeName,pxstutable.jifenNum as jifenNum " +
            "from pxstutable " +
            "LEFT JOIN pxcampustable on pxstutable.campusID=pxcampustable.id " +
            "LEFT JOIN pxstugradetable on pxstutable.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxstafftable on pxstutable.banzhurenTeacherID=pxstafftable.id " +
            "WHERE pxcampustable.isOpen !=2 and (pxstutable.buxiStateID =2 or pxstutable.buxiStateID =3 or pxstutable.buxiStateID =6)" +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "ORDER BY jifenNum DESC" +
            "</script>")
    Page<JFRankVo> getJFpaiming(Page page,@Param("ew") QueryWrapper queryWrapper);


    //endregion学员积分

    //region 年级|年龄段升级

    /**
     * 分页获取年级|年龄段升级
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select evalua.*,evalua.id as stuID,pxcampustable.campusName as campusName,pxstugradetable.stuGradeName as stuGradeName " +
            "from pxstutable as evalua " +
            "left join pxcampustable on evalua.campusID=pxcampustable.id " +
            "left join pxstugradetable on evalua.stuGradeID=pxstugradetable.id " +
            "WHERE 1=1 and pxcampustable.isOpen !=2 and (evalua.buxiStateID =2 or evalua.buxiStateID =3 or evalua.buxiStateID =6)" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<stuGradeVo> getStuGradeList(Page page, @Param("ew") Wrapper wrapper);

    //region 调级记录

    /**
     * 分页获取调级记录
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT jl.id as id,pxcampustable.campusName as campusName,jl.stuID as stuID,pxstutable.stuName as stuName,nj.stuGradeName as oldGrade," +
            "(SELECT stuGradeName from pxstugradetable where id=nowgrade)as newGrade,(select staffName from pxstafftable where id=jl.addStaffID) as jingbanren," +
            "jl.addDate as addDate,pxstutable.zidingyiStuID zidingyiStuID " +
            "FROM pxgradeupdatetable as jl " +
            "LEFT JOIN pxstugradetable as nj on jl.oldgrade=nj.id " +
            "LEFT JOIN pxstutable on jl.stuID=pxstutable.id " +
            "LEFT JOIN pxcampustable on pxstutable.campusID=pxcampustable.id " +
            "WHERE pxcampustable.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<gradeNotesVo> getGradeJiLu(Page page, @Param("ew") Wrapper wrapper);
    //endregion

    //endregion

    //region 学员生日

    /**
     * 分页获取学员生日
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select a.id as stuID,a.stuName as stuName,a.id as stuID,(case WHEN a.stubirth is null THEN '无' ELSE a.stubirth END) stuBirth,pxcampustable.campusName as campusName," +
            "pxstugradetable.stuGradeName as stuGradeName,pxstafftable.staffName as banzhuren,a.stuSex stuSex,a.zidingyiStuID zidingyiStuID " +
            " from pxstutable as a " +
            "left join pxcampustable on a.campusID=pxcampustable.id " +
            "left join pxstugradetable on a.stuGradeID=pxstugradetable.id " +
            "left join pxstafftable on a.banzhurenTeacherID = pxstafftable.id " +
            " WHERE 1=1 and pxcampustable.isOpen !=2 and (a.buxiStateID =2 or a.buxiStateID =3 or a.buxiStateID =6)" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<stuBirthVo> getStuBirth(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 导出学员生日
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select a.id as stuID,a.zidingyiStuID zidingyiStuID,a.stuName as stuName,a.id as stuID,a.stubirth as stubirth,pxcampustable.campusName as campusName," +
            "pxstugradetable.stuGradeName as stuGradeName,pxstafftable.staffName as banzhuren from pxstutable as a " +
            "left join pxcampustable on a.campusID=pxcampustable.id " +
            "left join pxstugradetable on a.stuGradeID=pxstugradetable.id " +
            "left join pxstafftable on a.banzhurenTeacherID = pxstafftable.id " +
            " WHERE pxcampustable.isOpen !=2 and (a.buxiStateID =2 or a.buxiStateID =3 or a.buxiStateID =6)" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<stuBirthVo> ExportStuBirth(@Param("ew") Wrapper wrapper);
    //endregion

    //region 住宿

    /**
     * 分页获取学员住宿
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "select evalua.*,evalua.id as stuID,pxroomtable.number as roomName,pxcampustable.campusName as campusName,pxstugradetable.stuGradeName as stuGradeName,pxstafftable.staffName as banzhuren from pxstutable as evalua " +
            "left join pxroomtable on evalua.roomid=pxroomtable.id " +
            "left join pxstafftable on evalua.banzhurenTeacherID = pxstafftable.id " +
            "left join pxcampustable on evalua.campusID=pxcampustable.id " +
            "left join pxstugradetable on evalua.stuGradeID=pxstugradetable.id " +
            " WHERE 1=1 and pxcampustable.isOpen !=2  and (evalua.buxiStateID =2 or evalua.buxiStateID =3 or evalua.buxiStateID =6)" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<stuStayVo> getStuStay(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" + "select * from pxstutable where roomid=#{roomid} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxstutable> getnewNum(Long roomid, Long qiyeID);

    @Select("<script>" + "select GROUP_CONCAT(id) as idS from pxstutable where roomid=#{roomid} and qiyeID=#{qiyeID}" + "</script>")
    stuInRoomVo getStuInRoomVo(Long roomid, Long qiyeID);
    //endregion


}
