package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesouttable;
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
public interface IPxteachingsuppliesouttableDao extends BaseMapper<Pxteachingsuppliesouttable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "suppliesId", property = "suppliesId"),
                @Result(column = "outStaffId", property = "outStaffId"),
                @Result(column = "outReason", property = "outReason"),
                @Result(column = "luruStaffId", property = "luruStaffId"),
                @Result(column = "outDate", property = "outDate"),
                @Result(column = "outNum", property = "outNum"),
                @Result(column = "outNum_before", property = "outnumBefore"),
                @Result(column = "type", property = "type"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliesouttable"
            + "</script>")
    List<Pxteachingsuppliesouttable> getAllList();
}