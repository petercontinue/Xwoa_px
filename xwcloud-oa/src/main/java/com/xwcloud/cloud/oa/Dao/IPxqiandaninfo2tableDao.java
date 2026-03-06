package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxqiandaninfo2table;
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
public interface IPxqiandaninfo2tableDao extends BaseMapper<Pxqiandaninfo2table> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "qianDanOtherMoneyID", property = "qianDanOtherMoneyID"),
                @Result(column = "jiaoxueYonpingID", property = "jiaoxueYonpingID"),
                @Result(column = "onePrice", property = "onePrice"),
                @Result(column = "nums", property = "nums"),
                @Result(column = "zongMoney", property = "zongMoney"),
                @Result(column = "qianInfoTabID", property = "qianInfoTabID"),
                @Result(column = "type", property = "type"),
                @Result(column = "tuiMoney", property = "tuiMoney"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandaninfo2table"
            + "</script>")
    List<Pxqiandaninfo2table> getAllList();
}