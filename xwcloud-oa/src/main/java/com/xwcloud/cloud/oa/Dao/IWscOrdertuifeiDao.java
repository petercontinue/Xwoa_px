package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscOrdertuifei;
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
public interface IWscOrdertuifeiDao extends BaseMapper<WscOrdertuifei> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "orderNumber", property = "orderNumber"),
                @Result(column = "tuikuanTime", property = "tuikuanTime"),
                @Result(column = "tuikuanShuoming", property = "tuikuanShuoming"),
                @Result(column = "tuikuanRen", property = "tuikuanRen"),
                @Result(column = "tuikuanMsg", property = "tuikuanMsg"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "chuliTuihuoTime", property = "chuliTuihuoTime"),
                @Result(column = "chuliTuihuoShuoming", property = "chuliTuihuoShuoming"),
                @Result(column = "chuliTuihuoRen", property = "chuliTuihuoRen"),
                @Result(column = "chuliTuihuoMsg", property = "chuliTuihuoMsg"),
                @Result(column = "tuihuoState", property = "tuihuoState"),
                @Result(column = "tuikuanState", property = "tuikuanState"),
                @Result(column = "chuliTuikuanTime", property = "chuliTuikuanTime"),
                @Result(column = "chuliTuikuanShuoming", property = "chuliTuikuanShuoming"),
                @Result(column = "chuliTuikuanRen", property = "chuliTuikuanRen"),
                @Result(column = "chuliTuikuanMsg", property = "chuliTuikuanMsg"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_ordertuifei"
            + "</script>")
    List<WscOrdertuifei> getAllList();
}