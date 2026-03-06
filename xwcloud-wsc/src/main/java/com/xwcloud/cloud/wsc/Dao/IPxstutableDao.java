package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
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

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-07
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
            @Result(column = "dengjiTeacherID", property = "dengjiTeacherID"),
            @Result(column = "dengjiTime", property = "dengjiTime"),
            @Result(column = "nextGenjinTime", property = "nextGenjinTime"),
            @Result(column = "tingkeTime", property = "tingkeTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxstutable"
            + "</script>")
    List<Pxstutable> getAllList();
    /**
     * 查询报名记录
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT qiandan.qiandandate,qdsubject.buykeshiNum,qdsubject.kechengStyle,kecheng.kechengName,stu.stuName" +
            " FROM pxqiandansubjecttable AS qdsubject \n" +
            "LEFT JOIN pxqiandaninfotable AS qiandan ON qdsubject.qianDanInfoID = qiandan.id\n" +
            "LEFT JOIN pxkechengtable AS kecheng ON qdsubject.kechengID = kecheng.id\n" +
            "LEFT JOIN pxstutable AS stu ON qiandan.stuID =stu.id\n" +
            "WHERE (qiandan.moneyStyle=1 OR qiandan.moneyStyle = 2) " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<baomingrecordsVO> GetAllbaomingRecords(@Param("ew") Wrapper wrapper);

    /**
     * 查询剩余课时信息
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT buxi.*,kecheng.*,stu.*," +
            "(SELECT GROUP_CONCAT(className) FROM pxclasstable WHERE id IN (SELECT classID FROM pxstuclasstable  WHERE buxiID = buxi.id )) AS stuclass  FROM pxbuxikechengtable AS buxi \n" +
            "LEFT JOIN pxkechengtable AS kecheng ON buxi.kechengID = kecheng.id\n" +
            "LEFT JOIN pxstutable AS stu ON buxi.stuID = stu.id WHERE 1 = 1 " + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<remainkeshiWscVO> GetStuAllRemainkeshi(@Param("ew") Wrapper wrapper);

    /**
     * 查询充值信息
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT chongzhi.id,stu.id AS stuID,stu.stuName,chongzhi.shideTotalMoney AS allmoney,chongzhi.shijiChongzhiMoney AS shimoney,chongzhi.songMoney AS zengmoney,chongzhi.chongzhiDatetime AS adddate,\n" +
            "staff.staffName,chongzhi.shuoming AS beizhu,stu.remainChongzhi AS yue FROM pxchongzhitable AS chongzhi \n" +
            "LEFT JOIN pxstutable AS stu ON chongzhi.stuID = stu.id \n" +
            "LEFT JOIN pxstafftable AS staff ON chongzhi.addStaffID = staff.id WHERE 1 = 1 " + "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<chongzhirecordsVO> GetStuChongzhjiList(@Param("ew") Wrapper wrapper);

    /**
     * 查询当前登录的账号（手机号码）绑定的所有学生信息
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT\tb.*  from wsc_user_bind a\n" +
            "LEFT JOIN pxstutable b on a.stuId=b.id " +
            " where 1 = 1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxstutable> GetAllStuListLoginPhone(@Param("ew") Wrapper wrapper);

    //学员端获取学员积分信息
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

    //查询所有教师和老师生日信息
    @Select("<script>" + "select stu.id AS dataID, stu.stuName AS name,stu.stuPhoto AS touxiangImg,'学生' AS roleName,stu.stubirth \n" +
            "            AS birthday,(SELECT COUNT(*)FROM birthdaydianzang WHERE beidianzanUserID = stu.id ) AS dianzangCount,\n" +
            "\t\t\t\t\t\t(SELECT COUNT(*) FROM birthdayzhufu WHERE zhufuUserID = stu.id) AS pinglunCount\n" +
            "\t\t\t\t\t\tfrom pxstutable AS stu where MONTH(stu.stubirth) = MONTH(NOW()) and DAY(stu.stubirth) = DAY(NOW()) AND qiyeID = #{qiyeID}\n" +
            "union all \n" +
            " SELECT staff.id AS dataID,staff.staffName AS name,staff.photo AS touxiangImg,'教师' AS roleName, staff.staffBirthday \n" +
            "            AS birthday,(SELECT COUNT(*)FROM birthdaydianzang WHERE beidianzanUserID = staff.id ) AS dianzangCount ,\n" +
            "\t\t\t\t\t\t(SELECT COUNT(*) FROM birthdayzhufu WHERE zhufuUserID = id) AS pinglunCount\n" +
            "\t\t\t\t\t\tFROM pxstafftable AS staff WHERE MONTH(staff.staffBirthday) = MONTH(now()) AND DAY(staff.staffBirthday) = DAY(NOW())  AND qiyeID = #{qiyeID}" + "</script>")
    List<shengrizhushouVO> GetAllteacherAndStuBirthday(long qiyeID);
}