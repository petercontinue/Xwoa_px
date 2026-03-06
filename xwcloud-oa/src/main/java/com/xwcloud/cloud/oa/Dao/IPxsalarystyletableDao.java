package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxsalarystyletable;
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
public interface IPxsalarystyletableDao extends BaseMapper<Pxsalarystyletable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "salaryStyle", property = "salaryStyle"),
                @Result(column = "isJiaOrJianOrQiuhe", property = "isJiaOrJianOrQiuhe"),
                @Result(column = "staffID", property = "staffID"),
                @Result(column = "lurudate", property = "lurudate"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxsalarystyletable"
            + "</script>")
    List<Pxsalarystyletable> getAllList();
}