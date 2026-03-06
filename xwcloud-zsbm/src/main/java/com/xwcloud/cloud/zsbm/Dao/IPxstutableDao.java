package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstutable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
public interface IPxstutableDao extends BaseMapper<Pxstutable> {

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
            @Result(column = "IDImage", property = "iDImage"),
            @Result(column = "IDnumber", property = "iDnumber"),
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
            @Result(column = "nextGenjinTime", property = "nextGenjinTime"),
            @Result(column = "tingkeTime", property = "tingkeTime"),
            @Result(column = "dengjiTeacherID", property = "dengjiTeacherID"),
            @Result(column = "dengjiTime", property = "dengjiTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxstutable"
            + "</script>")
    List<Pxstutable> getAllList();

    @Select("<script>" + "SELECT * FROM pxstutable WHERE id=#{Id} ORDER BY id LIMIT 0,1" + "</script>")
    Pxstutable GetYixiangStuByID(Long Id);

    @Update("<script>" + "UPDATE pxstutable SET remainChongzhi=#{remainChongzhi} WHERE id = #{stuID}" + "</script>")
    int UpdateStuRemainChongzhi(BigDecimal remainChongzhi, Long stuID);

    //查询学生余额信息
    @Select("<script>" + "SELECT a.id,b.campusName,a.stuName,a.parentTel,a.remainChongzhi FROM pxstutable AS a LEFT JOIN pxcampustable as b ON a.campusID = b.id" + " WHERE a.remainChongzhi>0 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<StuYueInfoVo> getAllChongzhiList(@Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT * FROM pxstutable" + " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstutable> getStuByZidingyi(@Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT b.campusName,a.stuName,a.parentTel,a.remainChongzhi FROM pxstutable AS a \n" +
            "LEFT JOIN pxcampustable AS b ON a.campusID=b.id\n" +
            "WHERE (SELECT COUNT(*) FROM pxchongzhitable WHERE stuID=a.id)>0" + "</script>")
    List<StuyueVo> getAllStuyueList();

    /**
     * 查询续费下拉选择学生信息
     *
     * @return
     */
    @Select("<script>" + "SELECT a.id,a.stuName AS stuName,a.parentTel parentTel,a.parentTelRelation,c.id AS stuGradeID,a.campusID AS campusID FROM pxstutable AS a LEFT JOIN pxcampustable AS b ON a.campusID = b.id " +
            "LEFT JOIN pxstugradetable AS c ON a.stuGradeID = c.id" +
            " WHERE (a.buxiStateID = 1 OR a.buxiStateID = 2 OR a.buxiStateID = 3 OR a.buxiStateID = 6) AND b.isOpen!=2" + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<xufeistuVO> GetAllXufeistuList(@Param("ew") Wrapper wrapper);


//    qdkeshiVo

    @Select("<script>" +
            "select \n" +
            "( CASE WHEN a.zidingyiStuID IS NOT NULL THEN a.zidingyiStuID ELSE a.id END ) stuID,\n" +
            "a.stuName,b.originalprice originalprice,b.kechengprice kechengprice,b.buykeshiNum buykeshiNum,\n" +
            "d.kechengName,e.buxiStyleName,g.stuGradeName,f.subjectName,c.qiandandate,h.campusName,\n" +
            "(SELECT GROUP_CONCAT(DISTINCT staff.staffName)\n" +
            "FROM pxqiandanstafftable AS qds\n" +
            "LEFT JOIN pxstafftable AS staff ON qds.staffID=staff.id WHERE qiandanID = c.ID) AS yejistaffName,\n" +
            "( CASE b.kechengStyle WHEN 1 THEN '购买课时' WHEN 2 THEN '赠送课时' WHEN 3 THEN '送出的课时' WHEN 4 THEN '退费' WHEN 5 THEN '换课换出' ELSE '换课得到' END ) keshichangeType\n" +
            "from pxstutable a \n" +
            "LEFT JOIN pxqiandansubjecttable b on a.id=b.stuID\n" +
            "left join pxqiandaninfotable c on b.qianDanInfoID=c.id\n" +
            "LEFT JOIN pxkechengtable d on b.kechengID=d.id\n" +
            "LEFT JOIN pxbuxistyletable e on d.buxiStyleID=e.id\n" +
            "LEFT JOIN pxsubjecttable f on d.subjectID=f.id\n" +
            "LEFT JOIN pxstugradetable g on a.stuGradeID=g.id\n" +
            "LEFT JOIN pxcampustable h on a.campusID=h.id\n" +
            "where a.id=b.stuID and h.isOpen !=2  " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<qdkeshiVo> getqiandankeshi(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "select b.campusName,a.stuName,(CASE WHEN a.zidingyiStuID IS NOT NULL THEN a.zidingyiStuID ELSE a.id END ) stuID,\n" +
            "a.parentTel,d.stuGradeName,c.HetongMoney,c.shishouTotalMoney,\n" +
            "(SELECT SUM(zongMoney) FROM pxqiandaninfo2table WHERE qianInfoTabID = c.id) AS SumotherMoney ,\n" +
            "(SELECT SUM(money) FROM pxdaijinquantable WHERE qiandanID = c.id) AS daijinquanMoney,\n" +
            "(SELECT SUM(zongjia) FROM pxqiandansubjecttable where qianDanInfoID = a.id) AS kechengMoney,\n" +
            "(c.HetongMoney - c.shishouTotalMoney) AS weikuan,\n" +
            "c.youhuijine\n" +
            "from pxstutable a \n" +
            "LEFT JOIN pxcampustable b on a.campusID=b.id\n" +
            "LEFT JOIN pxqiandaninfotable c on a.id=c.stuID\n" +
            "LEFT JOIN pxstugradetable d on a.stuGradeID=d.id\n" +
            "where b.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<qdCountVo> getqiandanCountlist(@Param("ew") QueryWrapper queryWrapper);


    //------------------------------意向学员-------------------------------


    //删除跟进记录时去掉下次跟进时间、意向程度
    @Select("<script>"+
            "UPDATE pxstutable set yxLevelID=NULL,nextGenjinTime=NULL  where id=#{id} and qiyeID=#{qiyeID}"+
            "</script>")
    Pxstutable updateyxstu(Long id ,Long qiyeID);

    /**
     * 分页查询意向学员信息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.*\n" +
            "FROM (SELECT \n" +
            "a.*,\n" +
            "c.stuGradeName,\n" +
            "b.campusName,\n" +
            "d.telFromName,\n" +
            "(select GROUP_CONCAT(subjectName) from pxsubjecttable where find_in_set(id, a.yixiangkemu)) yixiangkecheng,\n" +
            "e.telLevelName,\n" +
            "(SELECT COUNT(*) FROM pxyxgengjintable AS gj WHERE gj.stuID=a.id)  AS genjinSum ,\n" +
            "(SELECT COUNT(*) FROM pxyxinvitationtable AS inv WHERE inv.stuID = a.id) AS yaoyueSum,\n" +
            "(SELECT COUNT(*) FROM pxyxinvitedaofangtable AS df WHERE df.inviteID in(SELECT id FROM pxyxinvitationtable inv where inv.stuID = a.id)) as daofangSum,\n" +
            "(SELECT COUNT(*) FROM pxshitingrecordtable AS st WHERE st.yxStuID = a.id) AS shitingSum" +
            ",dengji.staffName AS dengjiStaffName," +
            "f.staffName AS shichangStaffName,\n" +
            "fuze.staffName AS fuzeStaffName, \n" +
            "(SELECT genjin.gengjinTime FROM pxyxgengjintable genjin WHERE a.id=genjin.stuID ORDER BY genjin.gengjinTime DESC LIMIT 0,1) gengjinTime,\n " +
            "(select staffName from pxstafftable where id=a.yxshichangTeacherID) yxshichangTeaName " +
            "FROM pxstutable AS a \n" +
            "LEFT JOIN pxcampustable AS b ON a.campusID = b.id\n" +
            "LEFT JOIN pxstugradetable AS c ON a.stuGradeID = c.id\n" +
            "LEFT JOIN pxyxtelfromtable AS d ON a.yxFromID = d.id\n" +
            "LEFT JOIN pxyxtelleveltable AS e ON a.yxLevelID = e.id\n" +
            "LEFT JOIN pxstafftable AS f ON a.yxshichangTeacherID = f.id\n" +
            "LEFT JOIN pxstafftable AS fuze ON a.yxfenpeistaffID = fuze.id\n" +
            "LEFT JOIN pxstafftable AS dengji ON a.dengjiTeacherID = dengji.id \n" +
            "WHERE a.buxiStateID =1) a" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<yixiangStuVo> getYixiangstuPages(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 分页查询意向学员跟进提醒
     */
    @Select("<script>" +
            "SELECT a.id,a.zidingyiStuID,a.stuName,e.stuGradeName,a.dengjiTime," +
            "(SELECT COUNT(*) FROM pxyxgengjintable WHERE stuID = a.id) AS genjinCount," +
            "a.nextGenjinTime,b.staffName,d.campusName,c.staffpostName \n" +
            "FROM pxstutable AS a\n" +
            "LEFT JOIN pxstafftable AS b ON a.yxfenpeistaffID = b.id\n" +
            "LEFT JOIN pxstaffposttable AS c ON b.staffPostID = c.id\n" +
            "LEFT JOIN pxcampustable AS d ON a.campusID = d.id\n" +
            "LEFT JOIN pxstugradetable AS e ON a.stuGradeID = e.id\n" +
            "WHERE a.buxiStateID=1 and d.isopen!=2 and a.nextGenjinTime is not null " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<genjinTixingVo> GetYixiangStuTixingPages(Page page, @Param("ew") Wrapper wrapper);


    @Select("<script>" +
            "SELECT a.stuName,ifnull(a.stuSex,'')stuSex,a.stubirth,(select GROUP_CONCAT(subjectName) from pxsubjecttable where find_in_set(id, a.yixiangkemu)) yxSubjects,a.parentTel,a.dengjiTime,a.lastFollowDate," +
            "(case a.parentTelRelation " +
            "when 1 then '本人' " +
            "when 2 then '爸爸' " +
            "when 3 then '妈妈' " +
            "when 4 then '爷爷' " +
            "when 5 then '奶奶' " +
            "when 6 then '外公' " +
            "when 7 then '外婆' " +
            "when 8 then '保姆' " +
            "else '其他' end)parentTelRelationValue, " +
            "ifnull(b.stuGradeName,'')stuGradeName, ifnull(c.campusName,'')campusName, ifnull(d.telFromName,'')telFromName, " +
            "ifnull(e.telLevelName,'')telLevelName, f.staffName dengjiTeacherName,\n" +
            "ifnull(h.staffName,'') yxShichangTeacherName,\n" +
            "ifnull(i.staffName,'') yxFenpeiName \n" +
            "FROM pxstutable a\n" +
            "left JOIN pxstugradetable b ON a.stuGradeID=b.id\n" +
            "left JOIN pxcampustable c ON a.campusID=c.id\n" +
            "left JOIN pxyxtelfromtable d ON a.yxFromID=d.id\n" +
            "left JOIN pxyxtelleveltable e ON a.yxLevelID=e.id\n" +
            "left JOIN pxstafftable f ON a.dengjiTeacherID=f.id\n" +
            "left JOIN pxstafftable h ON a.yxshichangTeacherID = h.id \n" +
            "left JOIN pxstafftable i ON a.yxfenpeistaffID = i.id " +
            "<where>" +
            "buxiStateID=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<PxStuTableVo> getExportYxStuList(@Param("ew") QueryWrapper wrapper);


}