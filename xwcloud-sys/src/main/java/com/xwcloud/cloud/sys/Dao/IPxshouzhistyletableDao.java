package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;

import com.xwcloud.cloud.model.entity.Pxshouzhistyletable;
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
public interface IPxshouzhistyletableDao extends BaseMapper<Pxshouzhistyletable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "shouzhiStyle", property = "shouzhiStyle"),
            @Result(column = "isshouOrzhichu", property = "isshouOrzhichu"),
            @Result(column = "beizhu", property = "beizhu"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "lurudate", property = "lurudate"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxshouzhistyletable"
            + "</script>")
    List<Pxshouzhistyletable> getAllList();

    /**
     * 查询收支方式
     * @return
     */
    @Select("<script>"+"SELECT a.*,b.shouzhiStyle,c.moneystyleName,d.staffName as addStaffName,e.staffName as jinbanrenName from pxliushuizhangtable a LEFT JOIN pxshouzhistyletable b  on  a.shouzhiStyleID = b.id " +
            "LEFT JOIN pxpaymoneystyletable c on a.payMoneyStyle = c.id"
            +" LEFT JOIN pxstafftable d on a.addStaffID = d.id"
            +" LEFT JOIN pxstafftable e on a.jinbanRen =  e.id"
            +"</script>")
    List<Pxliushuizhangtable> getallLiushuizhang();
}