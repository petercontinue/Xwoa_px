package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.yuekeTeacherVO;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabutable;
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
 * @since 2021-05-15
 */
@Repository
public interface IPxyueketeacherfabutableDao extends BaseMapper<Pxyueketeacherfabutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "haveLessonDate", property = "haveLessonDate"),
            @Result(column = "startLessonTime", property = "startLessonTime"),
            @Result(column = "endLessonTime", property = "endLessonTime"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "KechengID", property = "kechengID"),
            @Result(column = "classroomID", property = "classroomID"),
            @Result(column = "minSuccessYuekeStuNum", property = "minSuccessYuekeStuNum"),
            @Result(column = "maxStuNum", property = "maxStuNum"),
            @Result(column = "paikeID", property = "paikeID"),
            @Result(column = "yuekeState", property = "yuekeState"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "addTime", property = "addTime"),
            @Result(column = "addStaffID", property = "addStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "yuekeTitle", property = "yuekeTitle"),
            @Result(column = "yuekeshuoming", property = "yuekeshuoming"),
            @Result(column = "yuekeImg", property = "yuekeImg"),
            @Result(column = "liulanTimes", property = "liulanTimes"),
            @Result(column = "fenxiangTimes", property = "fenxiangTimes"),
    })
    @Select("<script>" +
            "SELECT * from  pxyueketeacherfabutable"
            + "</script>")
    List<Pxyueketeacherfabutable> getAllList();

    /**
     * 分页查询教师发布约课信息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT teacherfabu.*,kecheng.kechengName,class.className,campus.campusName,buxistyle.buxiStyleName,(SELECT  COUNT(*) FROM pxyueketeacherfabustutable WHERE yuekeTeachFabuID = teacherfabu.id) as current  FROM pxyueketeacherfabutable as teacherfabu \n" +
            "         LEFT JOIN pxclasstable as class ON teacherfabu.classID = class.id \n" +
            "         LEFT JOIN pxkechengtable as kecheng on teacherfabu.KechengID = kecheng.id \n" +
            "         LEFT JOIN pxcampustable AS campus ON teacherfabu.campusID = campus.id \n" +
            "         LEFT JOIN pxbuxistyletable as buxistyle ON kecheng.buxiStyleID = buxistyle.id" + "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    Page<yuekeTeacherVO> GetYuekTeacherFabuPages(Page page, @Param("ew") Wrapper wrapper);


    @Select("<script>"+"SELECT teacherfabu.*,kecheng.*,kecheng.kechengName,class.className,campus.campusName,buxistyle.buxiStyleName,(SELECT  COUNT(*) FROM pxyueketeacherfabustutable WHERE yuekeTeachFabuID = teacherfabu.id) as current  FROM pxyueketeacherfabutable as teacherfabu \n" +
            "         LEFT JOIN pxclasstable as class ON teacherfabu.classID = class.id \n" +
            "         LEFT JOIN pxkechengtable as kecheng on teacherfabu.KechengID = kecheng.id \n" +
            "         LEFT JOIN pxcampustable AS campus ON teacherfabu.campusID = campus.id \n" +
            "         LEFT JOIN pxbuxistyletable as buxistyle ON kecheng.buxiStyleID = buxistyle.id" + "<if test='ew!=null'>" +
            "${ew.customSqlSegment}" +
            "</if>"
            + "</script>")
    List<yuekeTeacherVO> GetYuekDetailInfo(@Param("ew")Wrapper wrapper);
}