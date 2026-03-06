package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxliushuizhangtable;
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
public interface IPxliushuizhangtableDao extends BaseMapper<Pxliushuizhangtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "liushuiDateTime", property = "liushuiDateTime"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "liushuiZaiyao", property = "liushuiZaiyao"),
                @Result(column = "payMoneyStyle", property = "payMoneyStyle"),
                @Result(column = "shouruMoney", property = "shouruMoney"),
                @Result(column = "zhichuMoney", property = "zhichuMoney"),
                @Result(column = "shouzhiStyleID", property = "shouzhiStyleID"),
                @Result(column = "jinbanRen", property = "jinbanRen"),
                @Result(column = "stuID", property = "stuID"),
                @Result(column = "qiandanID", property = "qiandanID"),
                @Result(column = "orderNumber", property = "orderNumber"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "luruTime", property = "luruTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxliushuizhangtable"
            + "</script>")
    List<Pxliushuizhangtable> getAllList();
}