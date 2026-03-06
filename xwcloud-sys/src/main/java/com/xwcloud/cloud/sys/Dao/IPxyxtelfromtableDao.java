package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-24
 */
@Repository
public interface IPxyxtelfromtableDao extends BaseMapper<Pxyxtelfromtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "telFromName", property = "telFromName"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxtelfromtable"
            + "</script>")
    List<Pxyxtelfromtable> getAllList();

    @Select("<script>"+"SELECT * FROM pxstutable WHERE yxFromID = #{yxFromID}"+"</script>")
    List<Pxstutable> GetstuBytelFromID(String yxFromID);
}