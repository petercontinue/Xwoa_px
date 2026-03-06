package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Qiandanshenpi;
import com.xwcloud.cloud.model.Vo.qiandanshenpiVO;
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
 * @since 2021-04-08
 */
@Repository
public interface IQiandanshenpiDao extends BaseMapper<Qiandanshenpi> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandandate", property = "qiandandate"),
            @Result(column = "zafeimoney", property = "zafeimoney"),
            @Result(column = "wupinmoney", property = "wupinmoney"),
            @Result(column = "kechengmoney", property = "kechengmoney"),
            @Result(column = "shishouTotalMoney", property = "shishouTotalMoney"),
            @Result(column = "HetongMoney", property = "hetongMoney"),
            @Result(column = "dingjing", property = "dingjing"),
            @Result(column = "jiazhangDemand", property = "jiazhangDemand"),
            @Result(column = "zhuanjieshaoID", property = "zhuanjieshaoID"),
            @Result(column = "moneyStyle", property = "moneyStyle"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "fromType", property = "fromType"),
            @Result(column = "qiandanType", property = "qiandanType"),
            @Result(column = "youhuiID", property = "youhuiID"),
            @Result(column = "youhuijine", property = "youhuijine"),
            @Result(column = "daijinquanmoney", property = "daijinquanmoney"),
            @Result(column = "youhuishuoming", property = "youhuishuoming"),
            @Result(column = "addstaffID", property = "addstaffID"),
            @Result(column = "isdingjing", property = "isdingjing"),
            @Result(column = "buxiStateID", property = "buxiStateID"),
            @Result(column = "shenpistaffID", property = "shenpistaffID"),
            @Result(column = "shenpiState", property = "shenpiState"),
            @Result(column = "shenpiDateTime", property = "shenpiDateTime"),
            @Result(column = "shenpishuoming", property = "shenpishuoming"),
            @Result(column = "qiadnatype", property = "qiadnatype"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  qiandanshenpi"
            + "</script>")
    List<Qiandanshenpi> getAllList();

    @Select("<script>" + "SELECT\n" +
            "            a.*,\n" +
            "            b.stuName,\n" +
            "            c.campusName,\n" +
            "            (SELECT GROUP_CONCAT(staff.staffName) FROM qiandanshenpiyejiren AS yjr LEFT JOIN pxstafftable AS staff ON yjr.qiandanstaffID=staff.id WHERE yjr.qiandanshenpiID = a.id) AS yejistaffName,\n" +
            "            (SELECT  CASE pst.paymoneystyleID WHEN -1 THEN'小程序支付' WHEN -2 THEN '充值余额支付'\n" +
            "        ELSE GROUP_CONCAT(paystyle.moneystyleName) END\n" +
            "FROM qiandanshenpipaymoney AS pst LEFT JOIN pxpaymoneystyletable AS paystyle ON pst.paymoneystyleID = paystyle.id WHERE pst.qiandanshenpiID = a.id) AS paystyle,\n" +
            "\t\t\t\t\t\td.staffName AS jinbanren,e.staffName AS shenpistaffname\n" +
            "            FROM\n" +
            "            qiandanshenpi AS a\n" +
            "            LEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "            LEFT JOIN pxcampustable AS c ON a.campusID = c.id\n" +
            "\t\t\t\t\t\tLEFT JOIN pxstafftable AS d ON a.addStaffID = d.id\n" +
            " \tLEFT JOIN pxstafftable AS e ON a.shenpistaffID = e.id" +
            "            WHERE\n" +
            "            c.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<qiandanshenpiVO> GetAllQiandanshenpiPages(Page page, @Param("ew") Wrapper wrapper);
}