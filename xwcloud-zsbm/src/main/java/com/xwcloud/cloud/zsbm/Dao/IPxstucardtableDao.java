package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstucardtable;
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
 * @since 2020-11-18
 */
@Repository
public interface IPxstucardtableDao extends BaseMapper<Pxstucardtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "cardNumber", property = "cardNumber"),
            @Result(column = "addDate", property = "addDate"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstucardtable"
            + "</script>")
    List<Pxstucardtable> getAllList();

    @Select("<script>"+"DELETE FROM pxstucardtable WHERE stuID=#{stuID} AND qiyeID=#{qiyeID}"+"</script>")
    Integer deleteStuCardRecordsByStuID(Long stuID,long qiyeID);
}