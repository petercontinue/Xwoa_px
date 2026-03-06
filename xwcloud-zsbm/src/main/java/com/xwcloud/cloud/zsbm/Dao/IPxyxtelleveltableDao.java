package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxyxtelleveltable;
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
 * @since 2021-01-25
 */
@Repository
public interface IPxyxtelleveltableDao extends BaseMapper<Pxyxtelleveltable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "telLevelName", property = "telLevelName"),
                @Result(column = "beizhu", property = "beizhu"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxtelleveltable "
            + "</script>")
    List<Pxyxtelleveltable> getAllList();

    @Select("<script>" +
            "SELECT id, telLevelName name FROM pxyxtelleveltable where qiyeid=#{qiyeID}" +
            "</script>")
    List<searchVO> getYxSearchTelLevelList(Long qiyeID);

}