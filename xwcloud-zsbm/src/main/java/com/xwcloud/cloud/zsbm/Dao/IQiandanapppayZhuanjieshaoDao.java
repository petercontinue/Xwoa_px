package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.QiandanapppayZhuanjieshao;
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
 * @since 2021-07-26
 */
@Repository
public interface IQiandanapppayZhuanjieshaoDao extends BaseMapper<QiandanapppayZhuanjieshao> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "qiandanapppayID", property = "qiandanapppayID"),
                @Result(column = "zhuanjieshaoFromStuID", property = "zhuanjieshaoFromStuID"),
                @Result(column = "zhuanjieshaoFromStaffID", property = "zhuanjieshaoFromStaffID"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  qiandanapppayZhuanjieshao"
            + "</script>")
    List<QiandanapppayZhuanjieshao> getAllList();
}