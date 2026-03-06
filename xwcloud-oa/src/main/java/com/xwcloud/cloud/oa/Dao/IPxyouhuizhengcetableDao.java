package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxyouhuizhengcetable;
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
public interface IPxyouhuizhengcetableDao extends BaseMapper<Pxyouhuizhengcetable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "youhuiType", property = "youhuiType"),
                @Result(column = "xianzhijine", property = "xianzhijine"),
                @Result(column = "youhui", property = "youhui"),
                @Result(column = "startDateTime", property = "startDateTime"),
                @Result(column = "endDatetime", property = "endDatetime"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "stuGradeIDs", property = "stuGradeIDs"),
                @Result(column = "useTimes", property = "useTimes"),
                @Result(column = "pxEnable", property = "pxEnable"),
                @Result(column = "wscEnable", property = "wscEnable"),
                @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  pxyouhuizhengcetable"
            + "</script>")
    List<Pxyouhuizhengcetable> getAllList();
}