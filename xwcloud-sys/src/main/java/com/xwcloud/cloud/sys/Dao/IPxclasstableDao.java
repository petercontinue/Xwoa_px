package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxclasstable;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-29
 */
@Repository
public interface IPxclasstableDao extends BaseMapper<Pxclasstable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "zidingyiClassID", property = "zidingyiClassID"),
                @Result(column = "className", property = "className"),
                @Result(column = "campusID", property = "campusID"),
                @Result(column = "maxStuNum", property = "maxStuNum"),
                @Result(column = "is1v1Class", property = "is1v1Class"),
                @Result(column = "isdelete", property = "isdelete"),
                @Result(column = "isShow", property = "isShow"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "classState", property = "classState"),
    })
    @Select("<script>" +
            "SELECT * from  pxclasstable"
            + "</script>")
    List<Pxclasstable> getAllList();


    @Select("<script>" +
            "SELECT * from pxclasstable a LEFT JOIN pxpaiketable b on a.id=b.classID where  b.id is null and a.qiyeID=#{qiyeID}  GROUP BY a.id "
            + "</script>")
    List<Pxclasstable> getNopkClass(Long qiyeID);

    @Select("<script>" +
            "SELECT tm.id,tm.longtimeclass from (\n" +
            "SELECT a.id,(SELECT Count(b.id) from pxkeshistutable b where a.id=b.classID and b.haveclassDate &gt; (SELECT DATE_ADD(NOW(), INTERVAL - 1 MONTH))) longtimeclass from pxclasstable a " +
            "where a.qiyeID=#{qiyeID} " +
            " GROUP BY a.id " +
            ") tm\n" +
            "  where tm.longtimeclass &lt;=0"
            + "</script>")
    List<HashMap<String,String>> getOnemonthnokeshiClass(Long qiyeID);




}