package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxpowertable;
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
 * @since 2020-12-07
 */
@Repository
public interface IPxpowertableDao extends BaseMapper<Pxpowertable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffpostID", property = "staffpostID"),
            @Result(column = "menuID", property = "menuID"),
            @Result(column = "dataFanwei", property = "dataFanwei"),
            @Result(column = "insertbtn", property = "insertbtn"),
            @Result(column = "updatebtn", property = "updatebtn"),
            @Result(column = "deletebtn", property = "deletebtn"),
            @Result(column = "pringbtn", property = "pringbtn"),
            @Result(column = "daorubtn", property = "daorubtn"),
            @Result(column = "daochubtn", property = "daochubtn"),
            @Result(column = "qitabtn", property = "qitabtn"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxpowertable"
            + "</script>")
    List<Pxpowertable> getAllList();

    /**
     * 通过菜单ID跟岗位ID查询对应的权限
     * @param menuID
     * @param staffPostID
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxpowertable WHERE menuID = #{menuID} AND staffPostID = #{staffPostID} AND qiyeID=#{qiyeID}"+"</script>")
    List<Pxpowertable>GetPowersBystaffpostIDandmenuID(Long menuID,Long staffPostID,long qiyeID);
}