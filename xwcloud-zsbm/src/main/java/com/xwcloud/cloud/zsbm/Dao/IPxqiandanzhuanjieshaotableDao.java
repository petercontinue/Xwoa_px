package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.xwcloud.cloud.model.entity.Pxqiandanzhuanjieshaotable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxqiandanzhuanjieshaotableDao extends BaseMapper<Pxqiandanzhuanjieshaotable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "qiandanID", property = "qiandanID"),
            @Result(column = "zhuanjieshaoFromStuID", property = "zhuanjieshaoFromStuID"),
            @Result(column = "zhuanjieshaoFromStaffID", property = "zhuanjieshaoFromStaffID"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxqiandanzhuanjieshaotable"
            + "</script>")
    List<Pxqiandanzhuanjieshaotable> getAllList();
}