package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxteachingsuppliesbuytable;
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
public interface IPxteachingsuppliesbuytableDao extends BaseMapper<Pxteachingsuppliesbuytable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "shangpingName", property = "shangpingName"),
                @Result(column = "shangpingTypeID", property = "shangpingTypeID"),
                @Result(column = "guigeID", property = "guigeID"),
                @Result(column = "buyNum", property = "buyNum"),
                @Result(column = "danwei", property = "danwei"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "addDate", property = "addDate"),
                @Result(column = "isShenhe", property = "isShenhe"),
                @Result(column = "shenheNopassReason", property = "shenheNopassReason"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxteachingsuppliesbuytable"
            + "</script>")
    List<Pxteachingsuppliesbuytable> getAllList();
}