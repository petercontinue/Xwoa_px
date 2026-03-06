package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;
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
 * @since 2021-05-28
 */
@Repository
public interface IPxqiandaninfotableDao extends BaseMapper<Pxqiandaninfotable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qiandandate", property = "qiandandate"),
            @Result(column = "shishouTotalMoney", property = "shishouTotalMoney"),
            @Result(column = "HetongMoney", property = "hetongMoney"),
            @Result(column = "jiazhangDemand", property = "jiazhangDemand"),
            @Result(column = "zhuanjieshaoID", property = "zhuanjieshaoID"),
            @Result(column = "moneyStyle", property = "moneyStyle"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qianDanStaffID", property = "qianDanStaffID"),
            @Result(column = "recordInStaffID", property = "recordInStaffID"),
            @Result(column = "recordInTime", property = "recordInTime"),
            @Result(column = "PayMoneyStyle", property = "payMoneyStyle"),
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
}