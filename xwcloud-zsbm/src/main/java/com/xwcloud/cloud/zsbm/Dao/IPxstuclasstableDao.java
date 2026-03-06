package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;
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
 * @since 2020-11-14
 */
@Repository
public interface IPxstuclasstableDao extends BaseMapper<Pxstuclasstable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "buxiID", property = "buxiID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxstuclasstable"
            + "</script>")
    List<Pxstuclasstable> getAllList();

    @Select("<script>" + "SELECT * FROM pxstuclasstable WHERE classID=#{classID} AND buxiID=#{buxiID} ORDER BY id LIMIT 0,1" + "</script>")
    Pxstuclasstable GetStuClassByBxIDAndClassID(Long classID, Long buxiID);

    @Select("<script>" + "SELECT * FROM pxstuclasstable WHERE buxiID=#{buxiID}" + "</script>")
    Pxstuclasstable GetStuclassBybuxiID(Long buxiID);

    @Select("<script>" + "SELECT * FROM pxstuclasstable WHERE buxiID=#{buxiID}" + "</script>")
    List<Pxstuclasstable> GetStuclassBybuxiIDlist(Long buxiID);

    @Select("<script>"+"DELETE FROM pxstuclasstable WHERE buxiID=#{buxiID}"+"</script>")
    int DeleteStuclassbybuxiID(Long buxiID);


    //----------------------------------意向学员-----------------------------------

    /**
     * 通过补习课程ID查询学生班级
     * @param buxiID
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxstuclasstable WHERE buxiID = #{buxiID}"+"</script>")
    List<Pxstuclasstable> GetAllStuClassList(Long buxiID);

    /**
     * 通过补习课程ID删除学生班级
     * @param buxiID
     * @return
     */
    @Select("<script>"+"DELETE FROM pxstuclasstable WHERE buxiID=#{buxiID}"+"</script>")
    int DeleteAllStuClassByBuxiID(Long buxiID);

    /**
     * 通过班级ID和补习课程ID查询学生班级
     * @param classID
     * @param buxiID
     * @return
     */
    @Select("<script>"+"SELECT * FROM pxstuclasstable WHERE classID=#{classID} AND buxiID=#{buxiID}"+"</script>")
    Pxstuclasstable GetPxstuclassBybxIDAndclassID(Long classID,Long buxiID);

    /**
     * 通过班级ID删除学生班级表信息
     * @param classID
     * @return
     */
    @Select("<script>"+ "DELETE FROM pxstuclasstable WHERE classID = #{classID}"+"</script>")
    int DeletestuclassByClassID(Long classID);
}