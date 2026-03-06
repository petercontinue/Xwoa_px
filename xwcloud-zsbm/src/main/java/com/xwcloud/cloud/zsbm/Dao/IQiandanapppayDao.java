package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.entity.Qiandanapppay;
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
 * @since 2021-05-28
 */
@Repository
public interface IQiandanapppayDao extends BaseMapper<Qiandanapppay> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandanDate", property = "qiandanDate"),
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
            @Result(column = "youhuishuoming", property = "youhuishuoming"),
            @Result(column = "daijinquanmoney", property = "daijinquanmoney"),
            @Result(column = "addstaffID", property = "addstaffID"),
            @Result(column = "isdingjing", property = "isdingjing"),
            @Result(column = "qdtype", property = "qdtype"),
            @Result(column = "buxiStateID", property = "buxiStateID"),
            @Result(column = "payState", property = "payState"),
            @Result(column = "payDate", property = "payDate"),
            @Result(column = "payUser", property = "payUser"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  qiandanapppay"
            + "</script>")
    List<Qiandanapppay> getAllList();

    /**
     * 分页查询小程序支付待支付信息
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>"+"SELECT\n" +
            "a.id,b.stuName,c.campusName,b.campusID,d.stuGradeName,b.zidingyiStuID,a.HetongMoney,a.shishouTotalMoney,a.kechengmoney,\n" +
            "a.wupinmoney,a.zafeimoney,a.dingjing,a.youhuijine,a.daijinquanMoney,a.payState,a.qiandanDate,a.moneyStyle,staff.staffName,\t(\n" +
            "\tSELECT\n" +
            "\t\tGROUP_CONCAT( staff.staffName ) \n" +
            "\tFROM\n" +
            "\t\tqiandanapppayyejiren AS qds\n" +
            "\t\tLEFT JOIN pxstafftable AS staff ON qds.qiandanstaffID = staff.id \n" +
            "\tWHERE\n" +
            "\t\tqiandanAppayID = a.ID \n" +
            "\t) AS yejistaffName\n" +
            "FROM\n" +
            "\tqiandanapppay AS a\n" +
            "\tJOIN pxstutable AS b ON a.stuID = b.id\n" +
            "\tJOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "\tJOIN pxstugradetable AS d ON b.stuGradeID = d.id\n" +
            "\tLEFT JOIN pxstafftable AS staff ON a.addstaffID = staff.id"+
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>"
            +"</script>")
    Page<HashMap<String, Object>> GetAllQiandanAppPayPages(Page page, @Param("ew") Wrapper wrapper);
}