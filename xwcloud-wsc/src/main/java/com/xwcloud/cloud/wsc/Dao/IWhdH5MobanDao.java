package com.xwcloud.cloud.wsc.Dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.WhdH5Moban;
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
 * @since 2021-03-20
 */
@Repository
public interface IWhdH5MobanDao extends BaseMapper<WhdH5Moban> {

@Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "mbName", property = "mbName"),
            @Result(column = "mbImgUrl", property = "mbImgUrl"),
            @Result(column = "ewmUrl", property = "ewmUrl"),
            @Result(column = "mbTypeID", property = "mbTypeID"),
            @Result(column = "headTxtColor", property = "headTxtColor"),
            @Result(column = "headTxtBgcolor", property = "headTxtBgcolor"),
            @Result(column = "contentBgcolor", property = "contentBgcolor"),
            @Result(column = "xheadTxtColor", property = "xheadTxtColor"),
            @Result(column = "xheadImgUrl", property = "xheadImgUrl"),
            @Result(column = "isOpen", property = "isOpen"),
            @Result(column = "paixu", property = "paixu"),
})
@Select("<script>" +
        "SELECT * from  whd_h5_moban"
        + "</script>")
List<WhdH5Moban> getAllList();
}