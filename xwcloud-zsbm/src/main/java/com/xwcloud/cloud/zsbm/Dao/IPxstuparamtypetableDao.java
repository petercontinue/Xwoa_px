package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.pramaTypeVo;
import com.xwcloud.cloud.model.Vo.stuparamtypeVO;
import com.xwcloud.cloud.model.entity.Pxstuparamtypetable;
import org.apache.ibatis.annotations.Param;
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
public interface IPxstuparamtypetableDao extends BaseMapper<Pxstuparamtypetable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuParamTypeName", property = "stuparamtypename"),
            @Result(column = "IsBiTian", property = "isbitian"),
            @Result(column = "widthType", property = "widthtype"),
            @Result(column = "qiyeID", property = "qiyeid"),
    })
    @Select("<script>" +
            "SELECT * from  pxstuparamtypetable"
            + "</script>")
    List<Pxstuparamtypetable> getAllList();

    @Select("<script>"+"SELECT * FROM pxstuparamtypetable WHERE qiyeID = #{qiyeID}"+"</script>")
    List<stuparamtypeVO> GetStuparamtypeList(long qiyeID);


    //-----------------意向学员----------------------------
    /**
     * 获取自定义字段的必填项/并给定默认值
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT *,(SELECT paramValue from pxstuparamvaluetable where stuParamTypeID=a.id  limit 1) pvalue\n" +
            "from pxstuparamtypetable a\n" +
            "WHERE a.IsBiTian=TRUE\n "
            + "</script>")
    List<pramaTypeVo> getOne(Long qiyeID);

    @Select("<script>" +
            "SELECT * from  pxstuparamtypetable where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<Pxstuparamtypetable> selectstuparamtype(@Param("ew") QueryWrapper queryWrapper);
}