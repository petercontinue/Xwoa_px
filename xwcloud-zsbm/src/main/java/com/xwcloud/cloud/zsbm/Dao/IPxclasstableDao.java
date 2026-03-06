package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxclasstable;
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
 * @since 2020-11-13
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
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxclasstable"
            + "</script>")
    List<Pxclasstable> getAllList();

    @Select("<script>" + "SELECT * FROM pxclasstable WHERE className=#{className};" + "</script>")
    List<Pxclasstable> GetClassByClassName(String className);

    @Select("<script>" + "SELECT * FROM pxclasstable WHERE className=#{className} ORDER BY id LIMIT 0,1" + "</script>")
    Pxclasstable GetClassByClassNameOne(String className);

    /**
     * 根据校区查询班级信息
     * @param campusID
     * @return
     */
    @Select("<script>" + "SELECT a.id,CONCAT('（',(SELECT COUNT(*) FROM pxbuxikechengtable AS k LEFT JOIN pxstuclasstable AS stuclass ON k.ID = stuclass.buxiID LEFT JOIN pxstutable AS stu ON k.stuID = stu.id\n" +
            "WHERE stuclass.classID = a.id AND (stu.buxiStateID = 1 || stu.buxiStateID = 2 || stu.buxiStateID= 3 || stu.buxiStateID = 6)),'人）_',a.className)  AS name" +
            " FROM pxclasstable AS a WHERE  a.isShow = 1 AND a.isdelete = false AND a.is1v1Class !=1 AND a.campusID = #{campusID} AND a.qiyeID=#{qiyeID}" + "</script>")
    List<searchVO> GetAllClassInfoList(long campusID,long qiyeID);


    //-----------------------------------意向学员-------------------------------------
    /**
     * 通过班级名称查找班级
     * @param className
     * @return
     */
    @Select("<script>" + "SELECT * FROM pxclasstable WHERE className =#{className}" + "</script>")
    List<Pxclasstable> GetClassByClassnameList(String className);

}