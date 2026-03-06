package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxtuifeitable;
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
public interface IPxtuifeitableDao extends BaseMapper<Pxtuifeitable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "qiandanID", property = "qiandanID"),
                @Result(column = "tuifeispID", property = "tuifeispID"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "tuifeiType", property = "tuifeiType"),
                @Result(column = "beforeTuifeiRemainXuefei", property = "beforeTuifeiRemainXuefei"),
                @Result(column = "shoudTuifeiTotalMoney", property = "shoudTuifeiTotalMoney"),
                @Result(column = "shijiTuifeiTotalMoney", property = "shijiTuifeiTotalMoney"),
                @Result(column = "afterTuifeiRemainXuefei", property = "afterTuifeiRemainXuefei"),
                @Result(column = "beforeTuifeiJifen", property = "beforeTuifeiJifen"),
                @Result(column = "tuijifen", property = "tuijifen"),
                @Result(column = "afterTuifeiJifen", property = "afterTuifeiJifen"),
                @Result(column = "beforeTFchongzhiRemainMoney", property = "beforeTFchongzhiRemainMoney"),
                @Result(column = "tuiChongzhiMoney", property = "tuiChongzhiMoney"),
                @Result(column = "afterTFchongzhiRemainMoney", property = "afterTFchongzhiRemainMoney"),
                @Result(column = "tuifeiPayStyleID", property = "tuifeiPayStyleID"),
                @Result(column = "liushuiID", property = "liushuiID"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxtuifeitable"
            + "</script>")
    List<Pxtuifeitable> getAllList();
}