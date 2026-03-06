package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.searchVO;
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
 * @since 2021-01-14
 */
@Repository
public interface IPxyxtelfromtableDao extends BaseMapper<Pxyxtelfromtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "telFromName", property = "telfromname"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "addStaffID", property = "addstaffid"),
            @Result(column = "addTime", property = "addtime"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxtelfromtable"
            + "</script>")
    List<Pxyxtelfromtable> getAllList();


    @Select("<script>" + "SELECT * FROM pxyxtelfromtable WHERE qiyeID=#{qiyeID}" + "</script>")
    List<Pxyxtelfromtable> GetAllTelFromList(long qiyeID);

    @Select("<script>" +
            "SELECT id, telFromName name FROM pxyxtelfromtable where qiyeid=#{qiyeID}" +
            "</script>")
    List<searchVO> getYxSearchtelFrom(Long qiyeID);
}