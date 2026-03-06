package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WscYongjinbili;
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
 * @since 2021-01-17
 */
@Repository
public interface IWscYongjinbiliDao extends BaseMapper<WscYongjinbili> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "jibei", property = "jibei"),
            @Result(column = "bili", property = "bili"),
            @Result(column = "qiyeID", property = "qiyeid"),
})
@Select("<script>" +
        "SELECT * from  wsc_yongjinbili"
        + "</script>")
List<WscYongjinbili> getAllList();
}