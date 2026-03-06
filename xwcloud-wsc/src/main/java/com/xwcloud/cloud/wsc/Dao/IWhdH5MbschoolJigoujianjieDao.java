package com.xwcloud.cloud.wsc.Dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.WhdH5MbschoolJigoujianjie;
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
 * @since 2021-03-22
 */
@Repository
public interface IWhdH5MbschoolJigoujianjieDao extends BaseMapper<WhdH5MbschoolJigoujianjie> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "mbSchoolName", property = "mbSchoolName"),
            @Result(column = "mbLianxifangshi", property = "mbLianxifangshi"),
            @Result(column = "mbLianxifangshiUrl", property = "mbLianxifangshiUrl"),
            @Result(column = "mbschoolTel", property = "mbschoolTel"),
            @Result(column = "qiyeID", property = "qiyeID"),
})
@Select("<script>" +
        "SELECT * from  whd_h5_mbschool_jigoujianjie"
        + "</script>")
List<WhdH5MbschoolJigoujianjie> getAllList();
}