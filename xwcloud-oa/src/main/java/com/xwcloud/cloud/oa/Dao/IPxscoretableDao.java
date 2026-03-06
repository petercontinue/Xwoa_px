package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxscoretable;
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
public interface IPxscoretableDao extends BaseMapper<Pxscoretable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "subjectID", property = "subjectID"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "score", property = "score"),
                @Result(column = "dankepaiming", property = "dankepaiming"),
                @Result(column = "zongfenpaiming", property = "zongfenpaiming"),
                @Result(column = "scoreType", property = "scoreType"),
                @Result(column = "testTypeID", property = "testTypeID"),
                @Result(column = "testTitle", property = "testTitle"),
                @Result(column = "addDateTime", property = "addDateTime"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "beiZhu", property = "beiZhu"),
                @Result(column = "scoreDate", property = "scoreDate"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxscoretable"
            + "</script>")
    List<Pxscoretable> getAllList();
}