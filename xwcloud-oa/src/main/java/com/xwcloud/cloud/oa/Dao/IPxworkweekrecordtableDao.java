package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxworkweekrecordtable;
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
public interface IPxworkweekrecordtableDao extends BaseMapper<Pxworkweekrecordtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "staffID", property = "staffID"),
                @Result(column = "startDate", property = "startDate"),
                @Result(column = "endDate", property = "endDate"),
                @Result(column = "thisWeekRecord", property = "thisWeekRecord"),
                @Result(column = "nextWeekRecord", property = "nextWeekRecord"),
                @Result(column = "luruDate", property = "luruDate"),
                @Result(column = "imgsUrl", property = "imgsUrl"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxworkweekrecordtable"
            + "</script>")
    List<Pxworkweekrecordtable> getAllList();
}