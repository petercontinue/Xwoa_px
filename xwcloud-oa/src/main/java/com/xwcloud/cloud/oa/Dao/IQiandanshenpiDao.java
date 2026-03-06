package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Qiandanshenpi;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-08-25
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
                @Result(column = "youhuishuoming", property = "youhuishuoming"),
                @Result(column = "daijinquanmoney", property = "daijinquanmoney"),
                @Result(column = "addstaffID", property = "addstaffID"),
                @Result(column = "isdingjing", property = "isdingjing"),
                @Result(column = "qiadnatype", property = "qiadnatype"),
                @Result(column = "buxiStateID", property = "buxiStateID"),
                @Result(column = "hetongUrl", property = "hetongUrl"),
                @Result(column = "shenpistaffID", property = "shenpistaffID"),
                @Result(column = "shenpiState", property = "shenpiState"),
                @Result(column = "shenpiDateTime", property = "shenpiDateTime"),
                @Result(column = "shenpishuoming", property = "shenpishuoming"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  qiandanshenpi"
            + "</script>")
    List<Qiandanshenpi> getAllList();
}