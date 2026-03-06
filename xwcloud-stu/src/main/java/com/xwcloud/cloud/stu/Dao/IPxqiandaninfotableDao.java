package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
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

    @Select("<script>" + "select * from pxqiandaninfotable where stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxqiandaninfotable> getQD(Long stuID,Long qiyeID);

    //删除转送课时时获取签单
    @Select("<script>" + "select * from pxqiandaninfotable where moneyStyle=4 and stuID=#{stuID} and shishouTotalMoney=#{sMoney} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxqiandaninfotable> getdelzsQD(Long stuID, BigDecimal sMoney,Long qiyeID);

}