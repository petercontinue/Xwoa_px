package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxpowermenubuttontable;
import com.xwcloud.cloud.model.entity.Pxpowertable;

import com.xwcloud.cloud.model.Vo.quanxianfanhui;
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
public interface IPxpowermenubuttontableDao extends BaseMapper<Pxpowermenubuttontable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "menuID", property = "menuID"),
            @Result(column = "buttonID", property = "buttonID"),
            @Result(column = "isShow", property = "isShow"),
    })
    @Select("<script>" +
            "SELECT * from  pxpowermenubuttontable"
            + "</script>")
    List<Pxpowermenubuttontable> getAllList();

    @Select("<script>"+"SELECT * FROM pxpowermenubuttontable WHERE menuID = #{menuID}"+"</script>")
    List<Pxpowermenubuttontable> GetpowermenuButtonListBymenuID(Long menuID);

    @Select("<script>"+"SELECT * FROM pxpowermenubuttontable WHERE menuID = #{menuID} AND buttonID = #{buttonID}"+"</script>")
    List<Pxpowermenubuttontable> GetpowermenuButtonListBymenuIDAndbuttonID(Long menuID,Long buttonID);

    @Select("<script>"+"SELECT a.menuID,a.buttonID,b.buttonName FROM pxpowermenubuttontable AS a LEFT JOIN pxpowerbuttontable AS b ON a.buttonID = b.ID WHERE a.menuID = #{menuID}"+"</script>")
    List<quanxianfanhui> getmenusbuttonList(long menuID);

    @Select("<script>"+"select * from pxpowertable AS a where a.staffpostID = #{staffpostID} and a.menuID = #{menuID}  AND a.qiyeID = #{qiyeID} LIMIT 1"+"</script>")
    Pxpowertable GetStaffpostmenuPower(long staffpostID, long menuID, long qiyeID);
}