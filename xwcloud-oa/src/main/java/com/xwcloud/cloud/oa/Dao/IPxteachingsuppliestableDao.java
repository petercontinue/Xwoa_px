package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliestable;
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
public interface IPxteachingsuppliestableDao extends BaseMapper<Pxteachingsuppliestable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "name", property = "name"),
                @Result(column = "typeId", property = "typeId"),
                @Result(column = "specs", property = "specs"),
                @Result(column = "StockNum", property = "stockNum"),
                @Result(column = "StockUnit", property = "stockUnit"),
                @Result(column = "addDate", property = "addDate"),
                @Result(column = "yanshouStaffId", property = "yanshouStaffId"),
                @Result(column = "rukuShuoming", property = "rukuShuoming"),
                @Result(column = "buyPrice", property = "buyPrice"),
                @Result(column = "salePrice", property = "salePrice"),
                @Result(column = "kucunyujing", property = "kucunyujing"),
                @Result(column = "IsQiYong", property = "isQiYong"),
                @Result(column = "changpinTiaoma", property = "changpinTiaoma"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliestable"
            + "</script>")
    List<Pxteachingsuppliestable> getAllList();
}