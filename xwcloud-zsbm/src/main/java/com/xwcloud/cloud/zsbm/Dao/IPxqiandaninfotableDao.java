package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.StuYueInfoVo;
import com.xwcloud.cloud.model.Vo.exportqdInfoVo;
import com.xwcloud.cloud.model.Vo.qianDanInFoVo;
import com.xwcloud.cloud.model.Vo.updateqiandanVO;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;
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
 * @since 2020-11-11
 */
@Repository
public interface IPxqiandaninfotableDao extends BaseMapper<Pxqiandaninfotable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandandate", property = "qiandandate"),
            @Result(column = "shishouTotalMoney", property = "shishouTotalMoney"),
            @Result(column = "HetongMoney", property = "HetongMoney"),
            @Result(column = "jiazhangDemand", property = "jiazhangDemand"),
            @Result(column = "zhuanjieshaoID", property = "zhuanjieshaoID"),
            @Result(column = "moneyStyle", property = "moneyStyle"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qianDanStaffID", property = "qianDanStaffID"),
            @Result(column = "recordInStaffID", property = "recordInStaffID"),
            @Result(column = "recordInTime", property = "recordInTime"),
            @Result(column = "PayMoneyStyle", property = "PayMoneyStyle"),
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

    //查询签单信息（分页查询）
    @Select("<script>" +
            "SELECT a.id,c.id campusID, c.campusName,b.zidingyiStuID,b.stuName,d.stuGradeName,a.shishouTotalMoney shishouTotalMoney,a.HetongMoney,a.youhuijine," +
            "a.beizhu,a.zhuanjieshaoID,a.recordInTime AS qiandandate,\n" +
            "(SELECT COUNT(*) FROM pxqiandanstafftable WHERE qiandanID =a.id) AS qiandanstaffcount,\n" +
            "(SELECT GROUP_CONCAT(staff.staffName) " +
            "FROM pxqiandanstafftable AS qds " +
            "LEFT JOIN pxstafftable AS staff ON qds.staffID=staff.id WHERE qiandanID = a.ID) AS yejistaffName,\n" +

            "(SELECT GROUP_CONCAT(staff.id) " +
            "FROM pxqiandanstafftable AS qds " +
            "LEFT JOIN pxstafftable AS staff ON qds.staffID=staff.id WHERE qiandanID = a.ID) AS yejistaff,\n" +

            "(SELECT SUM(money) FROM pxdaijinquantable WHERE qiandanID = a.id) AS daijinquanMoney,\n" +
            "(a.HetongMoney - a.shishouTotalMoney) AS weikuan ,(SELECT SUM(zongMoney) FROM pxqiandaninfo2table WHERE qianInfoTabID = a.id) AS SumotherMoney ,\n" +
            "(SELECT SUM(zongjia) FROM pxqiandansubjecttable where qianDanInfoID = a.id) AS kechengMoney,\n" +
            "(SELECT staffName FROM pxstafftable WHERE id = a.recordInStaffID) AS jinbanStaffName,a.moneyStyle,\n" +
            "(SELECT SUM(SumMoney) FROM pxqiandansupplies WHERE qianDanInfoID = a.id) AS qiandansuppliesMoney,\n" +
            "(select staffName from pxstafftable where id = b.yxshichangTeacherID) shichangtea " +
            "FROM pxqiandaninfotable AS a " +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.id " +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id " +
            "LEFT JOIN pxstugradetable AS d ON b.stuGradeID = d.id \n" +
            "WHERE(a.moneyStyle=1||a.moneyStyle=2) " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<qianDanInFoVo> GetQiandanInfoPages(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 合同管理查询签单详细信息
     *
     * @param qiandanID
     * @return
     */
    @Select("<script>" + "SELECT a.id,a.hetong, c.campusName,b.zidingyiStuID,b.stuName,d.stuGradeName,a.HetongMoney,a.youhuijine,a.beizhu,a.zhuanjieshaoID,a.recordInTime AS qiandandate,\n" +
            "            (SELECT COUNT(*) FROM pxqiandanstafftable WHERE qiandanID =a.id) AS qiandanstaffcount,\n" +
            "            (SELECT GROUP_CONCAT(staff.staffName) FROM pxqiandanstafftable AS qds LEFT JOIN pxstafftable AS staff ON qds.staffID=staff.id WHERE qiandanID = a.ID) AS yejistaffName,\n" +
            "            (SELECT SUM(money) FROM pxdaijinquantable WHERE qiandanID = a.id) AS daijinquanMoney,\n" +
            "            (a.HetongMoney - a.shishouTotalMoney) AS weikuan ,(SELECT SUM(zongMoney) FROM pxqiandaninfo2table WHERE qianInfoTabID = a.id) AS SumotherMoney ,\n" +
            "            (SELECT SUM(zongjia) FROM pxqiandansubjecttable where qianDanInfoID = a.id) AS kechengMoney,\n" +
            "            (SELECT staffName FROM pxstafftable WHERE id = a.recordInStaffID) AS jinbanStaffName,a.moneyStyle,\n" +
            "            (SELECT SUM(SumMoney) FROM pxqiandansupplies WHERE qianDanInfoID = a.id) AS qiandansuppliesMoney\n" +
            "            FROM pxqiandaninfotable AS a LEFT JOIN pxstutable AS b ON a.stuID = b.id LEFT JOIN pxcampustable AS c ON b.campusID = c.id LEFT JOIN pxstugradetable AS d ON b.stuGradeID = d.id\n" +
            "\t\t\t\t\t\tWHERE a.id = #{qiandanID}" + "</script>")
    qianDanInFoVo GetqiandanInfoByqiandanID(Long qiandanID);

    //查询签单信息（不分页）
    @Select("<script>" +
            "SELECT a.id,c.id campusID, c.campusName,b.stuName,d.stuGradeName,a.HetongMoney,a.youhuijine,a.beizhu,a.recordInTime AS qiandandate,\n" +
            "( CASE WHEN b.zidingyiStuID IS NOT NULL THEN b.zidingyiStuID ELSE b.id END ) stuID,\n" +
            "(SELECT COUNT(*) FROM pxqiandanstafftable WHERE qiandanID =a.id) AS qiandanstaffcount,b.parentTel,\n" +
            "(SELECT GROUP_CONCAT(DISTINCT staff.staffName) " +
            "FROM pxqiandanstafftable AS qds " +
            "LEFT JOIN pxstafftable AS staff ON qds.staffID=staff.id WHERE qiandanID = a.ID) AS yejistaffName,\n" +
            "(select GROUP_CONCAT(DISTINCT b.moneystyleName) from pxqiandanpaymoney a LEFT JOIN pxpaymoneystyletable b on a.paymoneyStyleID=b.id where qiandanID=a.id ) paystyle," +
            "(SELECT GROUP_CONCAT(staff.id) " +
            "FROM pxqiandanstafftable AS qds " +
            "LEFT JOIN pxstafftable AS staff ON qds.staffID=staff.id WHERE qiandanID = a.ID) AS yejistaff,\n" +
            "(SELECT SUM(money) FROM pxdaijinquantable WHERE qiandanID = a.id) AS daijinquanMoney,\n" +
            "(a.HetongMoney - a.shishouTotalMoney) AS weikuan ," +
            "( CASE WHEN a.zhuanjieshaoID IS NOT NULL THEN '是' ELSE '否' END ) iszhuangjieshao," +
            "( CASE a.moneyStyle WHEN 1 THEN '新签' WHEN 2 THEN '续费' WHEN 3 THEN '退费' WHEN 4 THEN '转送' WHEN 5 THEN '换课换出' ELSE '换课得到' END ) qdmoneyStyle," +
            "(SELECT SUM(zongMoney) FROM pxqiandaninfo2table WHERE qianInfoTabID = a.id) AS SumotherMoney ,\n" +
            "(SELECT SUM(zongjia) FROM pxqiandansubjecttable where qianDanInfoID = a.id) AS kechengMoney,\n" +
            "(SELECT staffName FROM pxstafftable WHERE id = a.recordInStaffID) AS jinbanStaffName,\n" +
            "(SELECT SUM(SumMoney) FROM pxqiandansupplies WHERE qianDanInfoID = a.id) AS qiandansuppliesMoney,\n" +
            "(select staffName from pxstafftable where id = b.yxshichangTeacherID) shichangtea " +
            "FROM pxqiandaninfotable AS a " +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.id " +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id " +
            "LEFT JOIN pxstugradetable AS d ON b.stuGradeID = d.id \n" +
            "WHERE (a.moneyStyle=1||a.moneyStyle=2) and c.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "  ORDER BY a.qiandandate DESC"
            + "</script>")
    List<exportqdInfoVo> GetQiandanInfoList(@Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT * FROM pxqiandaninfotable WHERE id = #{Id} AND qiyeID=#{qiyeID}" + "</script>")
    Pxqiandaninfotable GetQiandanByQiandanID(Long Id, long qiyeID);

    /**
     * 查询学生余额信息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.id,b.campusName,a.stuName,a.parentTel,a.remainChongzhi,c.stuGradeName,a.zidingyiStuID " +
            "FROM pxstutable AS a " +
            "LEFT JOIN pxcampustable as b ON a.campusID = b.id " +
            "LEFT JOIN pxstugradetable AS c ON a.stuGradeID = c.id " +
            "WHERE a.remainChongzhi is not NULL " +
            "and (a.buxiStateID =2 or a.buxiStateID =3 or a.buxiStateID =6) "+
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<StuYueInfoVo> GetAllStuYuePages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT * FROM pxqiandaninfotable WHERE stuID = #{stuID} AND qiyeID=#{qiyeID}" + "</script>")
    List<Pxqiandaninfotable> GetStuQianDanList(Long stuID, long qiyeID);

    @Select("<script>" + "SELECT * FROM pxqiandaninfotable WHERE stuID = #{stuID} AND qiyeID=#{qiyeID} AND (moneyStyle=1 OR moneyStyle = 2)" + "</script>")
    List<Pxqiandaninfotable> GetXqOrXfList(Long stuID, long qiyeID);

    @Select("<script>" + "DELETE FROM pxqiandaninfotable WHERE stuID=#{stuID} AND qiyeID=#{qiyeID}" + "</script>")
    Integer deleteQiandanByStuID(Long stuID, long qiyeID);

    @Select("<script>" + "SELECT * FROM pxqiandaninfotable WHERE stuID = #{stuID} AND qiyeID=#{qiyeID} AND (moneyStyle=1 OR moneyStyle = 2) ORDER BY id LIMIT 0,1" + "</script>")
    Pxqiandaninfotable GetXqOrXfinfo(Long stuID, long qiyeID);

    /**
     * 查询签单详情信息
     *
     * @param qiandanID
     * @return
     */
    @Select("<script>" + "SELECT a.id,b.id AS stuID,b.stuName,b.stuSex,c.id AS campusID,b.stuGradeID,b.buxiStateID,b.parentTel AS parentsTel,a.qianDanStaffID,a.PayMoneyStyle,a.qiandandate,a.fromType AS telFromID,b.oldSchool,b.oldSchoolTeacher,b.stubirth AS StuBrithday,b.IDnumber,b.IDImage,b.ruxuechengji,b.laoshiyaoqiu,b.jijixing,b.xingge,a.HetongMoney,a.shishouTotalMoney,a.beizhu,a.youhuiID,a.youhuijine,a.youhuishuoming,d.money AS daijinquanMoney,(SELECT SUM(zongjia) FROM pxqiandansubjecttable where qianDanInfoID = a.id) AS kechengMoney,(SELECT SUM(SumMoney) FROM pxqiandansupplies WHERE qianDanInfoID = a.id) AS wupingMoney,(SELECT SUM(zongMoney) FROM pxqiandaninfo2table WHERE qianInfoTabID = a.id) AS zafeiMoney," +
            "b.zidingyiStuID  AS zidingyiStuNo,b.parentTelRelation,(SELECT oldSchoolName FROM pxoldschooltable WHERE oldSchoolID = b.oldSchool) AS " +
            "oldSchoolID, (SELECT " +
            "oldSchoolTeacherName FROM pxoldschoolteachertable WHERE oldSchoolTeacherID = b.oldSchoolTeacher) AS oldSchoolTeacherID  FROM pxqiandaninfotable AS a \n" +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.ID \n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id \n" +
            "LEFT JOIN pxdaijinquantable AS d ON a.id = d.qiandanID \n" +
            "WHERE c.isOpen !=2 AND a.id = #{qiandanID}" + "</script>")
    List<updateqiandanVO> GetQiandanInfoLits(long qiandanID);

}