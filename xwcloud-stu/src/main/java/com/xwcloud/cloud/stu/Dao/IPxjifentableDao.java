package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.stuIntegralVo;
import com.xwcloud.cloud.model.entity.Pxjifentable;
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
 * @since 2020-11-19
 */
@Repository
public interface IPxjifentableDao extends BaseMapper<Pxjifentable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "type", property = "type"),
            @Result(column = "oldIntegral", property = "oldIntegral"),
            @Result(column = "integral", property = "integral"),
            @Result(column = "staffID", property = "staffID"),
            @Result(column = "createTime", property = "createTime"),
            @Result(column = "remark", property = "remark"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxjifentable"
            + "</script>")
    List<Pxjifentable> getAllList();

    //导出学员积分
    @Select("<script>" +
            "select evalua.id as id,pxstutable.id as stuID,pxstutable.stuName as stuName,pxcampustable.campusName as campusName,pxstugradetable.stuGradeName as stuGradeName," +
            "pxstafftable.staffName as jingbanStaff,evalua.oldIntegral as oldIntegral,(select integral-oldIntegral from pxjifentable where id=evalua.id) as integral," +
            "evalua.createTime as createTime,evalua.remark as remark,(case WHEN evalua.type=1 THEN '增加' ELSE '扣减' END) as type " +
            "from pxjifentable as evalua " +
            "left join pxstutable on evalua.stuID=pxstutable.id " +
            "left join pxcampustable on pxstutable.campusID = pxcampustable.id  " +
            "left join pxstugradetable on pxstutable.stuGradeID=pxstugradetable.id " +
            "left join pxstafftable on evalua.staffID=pxstafftable.id " +
            " WHERE 1=1 and pxcampustable.isOpen=1 and (pxstutable.buxiStateID =2 or pxstutable.buxiStateID =3 or pxstutable.buxiStateID =6) " +
            "<if test='ew!=null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<stuIntegralVo> ExportIntegral(@Param("ew") Wrapper wrapper);

    //获取学员积分
    @Select("<script>" + "select * from pxjifentable where stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxjifentable> getJF(Long stuID, Long qiyeID);
}