package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscKanjiaBangkanrecord;
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
public interface IWscKanjiaBangkanrecordDao extends BaseMapper<WscKanjiaBangkanrecord> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "kanjiaFaqiID", property = "kanjiaFaqiID"),
                @Result(column = "bangkanrenWxUserID", property = "bangkanrenWxUserID"),
                @Result(column = "kanMoney", property = "kanMoney"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_kanjia_bangkanrecord"
            + "</script>")
    List<WscKanjiaBangkanrecord> getAllList();
}