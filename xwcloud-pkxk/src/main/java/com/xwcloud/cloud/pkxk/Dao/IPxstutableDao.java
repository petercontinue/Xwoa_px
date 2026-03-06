package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxstutable;
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
 * @since 2020-11-05
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
    })
    @Select("<script>" +
            "SELECT * from  pxstutable"
            + "</script>")
    List<Pxstutable> getAllList();


    /**
     * 获取校区学员
     *
     * @return
     */
    @Select("<script>" +
            "SELECT id id ,stuName name from  pxstutable where qiyeID=#{qiyeID}"
            + "</script>")
    List<getclassVo> GetcampusStuName(Long qiyeID);

    /**
     * 按照学员获取补习课程
     *
     * @param stuID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,b.kechengName name from pxbuxikechengtable a \n" +
            "LEFT JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "LEFT JOIN pxbuxistyletable c on b.buxiStyleID=c.id\n" +
            "WHERE a.isShow=1 and c.buxiStyleName !='一对一' and a.stuID=#{stuID} and a.qiyeID=#{qiyeID}"
            + "</script>")
    List<getclassVo> getxkStu(Long stuID, Long qiyeID);

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from pxstutable " +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxstutable> selectstu(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 条件查询
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT id,stuName name from pxstutable " +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<getstuVo> getstu(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 分页获取剩余课时
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select a.id as stuID,a.stuName as stuName,a.zidingyiStuID as zidingyiStuID,b.campusName as campusName,a.buxiStateID as buxiStateID,a.remainXuefei as remainXuefei, " +
            "c.stuGradeName as stuGradeName,d.staffName as banzhuren, " +
            "(SELECT (case WHEN SUM(remainkeshi) &lt; (SELECT defaultValue from pxsysparamdefaulttable where id = 11) THEN '课时预警' ELSE '' END) from pxbuxikechengtable where stuID=a.id and jifeiStyleID !=3 ) as shuoming, " +
            "(SELECT (case WHEN (select COUNT(id) from pxqiandaninfotable where stuID = a.id and HetongMoney != shishouTotalMoney and (moneyStyle =1 or moneyStyle=2)) &gt; 0 THEN SUM(HetongMoney-shishouTotalMoney) ELSE 0 END) from pxqiandaninfotable where stuID = a.id and HetongMoney != shishouTotalMoney and (moneyStyle =1 or moneyStyle=2) ) as weikuan " +
            "from pxstutable as a " +
            "LEFT JOIN pxcampustable as b on a.campusID=b.id " +
            "LEFT JOIN pxstugradetable as c on a.stuGradeID=c.id " +
            "LEFT JOIN pxstafftable as d on a.banzhurenTeacherID=d.id " +
            " WHERE 1=1 and b.isOpen != 2 and (a.buxiStateID=2 or a.buxiStateID=3 or a.buxiStateID=6) " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<stuRemainVo> remainkeshishowPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 导出剩余学费
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select b.campusName as campusName,c.stuGradeName as stuGradeName,a.stuName as stuName," +
            "(case WHEN buxiStateID=1 THEN '意向学员' ELSE (case WHEN buxiStateID=2 THEN '在读' ELSE (case WHEN buxiStateID=3 THEN '停课' ELSE (case WHEN buxiStateID=4 THEN '结课' ELSE (case WHEN buxiStateID=5 THEN '退费' ELSE '休眠' END) END) END) END) END) as stuState, " +
            "a.remainXuefei as remainXuefei " +
            "from pxstutable as a " +
            "LEFT JOIN pxcampustable as b on a.campusID = b.id " +
            "LEFT JOIN pxstugradetable as c on a.stuGradeID= c.id" +
            " WHERE 1=1 and b.isOpen != 2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<ExportReMoneyVo> ExportReMoney(@Param("ew") QueryWrapper queryWrapper);


    /**
     * （剩余课时）学员详情获取签单信息
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as stuID,a.zidingyiStuID zidingyiStuID,b.campusName as campusName,e.stuGradeName as stuGradeName,a.stuName as stuName," +
            "(case WHEN c.shishouTotalMoney  is NULL THEN 0 ELSE c.shishouTotalMoney  END)as shiMoney, " +
            "(case WHEN d.money is NULL THEN 0 ELSE d.money END)as daijiMoney, " +
            "(case WHEN f.moneystyleName  is NULL THEN '其他' ELSE f.moneystyleName END)as moneystyleName, " +
            "c.moneyStyle as moneyStyle,c.qiandandate as qiandandate,g.staffName as jingbanren " +
            "from pxstutable as a " +
            "LEFT JOIN pxcampustable as b on a.campusID=b.id " +
            "LEFT JOIN pxqiandaninfotable as c on a.id =c.stuID " +
            "LEFT JOIN pxdaijinquantable as d on c.id=d.qiandanID " +
            "LEFT JOIN pxstugradetable as e on a.stuGradeID=e.id " +
            "LEFT JOIN pxpaymoneystyletable as f on c.PayMoneyStyle=f.id " +
            "LEFT JOIN pxstafftable as g on c.qianDanStaffID=g.id " +
            " WHERE 1=1 and b.isOpen != 2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<qiandanstuVo> getqiandanstuShowPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 人工签到签退页面
     * 排课签到签退 分页获取
     */
    @Select("<script>" +
            "select d.campusName as campusName,a.id as id,a.stuName as stuName,f.kechengName as kechengName,e.className as className,c.haveClassDate as haveClassDate,c.startLessonDateTime as startLessonDateTime,\n" +
            "c.endLessonDateTime as endLessonDateTime,c.id as paikeID,a.id stuID,a.zidingyiStuID zidingyiStuID,\n" +
            "(case WHEN (SELECT COUNT(id) from pxqiandaoqiantuitable where qiandaoOrqiantui=1 and qianStyle=3 and stuID=a.id and paikeID=c.id) =0 THEN FALSE ELSE TRUE END)as qdpd,\n" +
            "(case WHEN (SELECT COUNT(id) from pxqiandaoqiantuitable where qiandaoOrqiantui=2 and qianStyle=3 and stuID=a.id and paikeID=c.id) =0 THEN FALSE ELSE TRUE END)as qtpd \n" +
            "from pxstutable as a \n" +
            "LEFT JOIN pxxuanketable as b on a.ID=b.stuID\n" +
            "LEFT JOIN pxpaiketable as c on b.paikeID=c.id\n" +
            "LEFT JOIN pxcampustable as d on a.campusID=d.id\n" +
            "LEFT JOIN pxclasstable as e on  c.classID=e.id\n" +
            "LEFT JOIN pxkechengtable as f on c.kechengID =f.id\n" +
            "WHERE d.isOpen !=2 \n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<ArtificialQiandaoVo> getPaikeQiandaoPage(Page page, @Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" + "SELECT stu.id,stu.id as stuID,stu.zidingyiStuID as zidingyiStuID,stu.stuName,stugra.stuGradeName,campus.campusName,stu.stuxiaokeImage FROM pxstutable AS stu LEFT JOIN pxcampustable AS campus ON stu.campusID = campus.id\n" +
            "LEFT JOIN pxstugradetable AS stugra ON stu.stuGradeID = stugra.id WHERE stu.buxiStateID!=1 AND stu.buxiStateID!=7\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" + "</script>")
    Page<HashMap<String, Object>> GetAllStuInfoAndMubanImages(Page page, @Param("ew") QueryWrapper queryWrapper);
}