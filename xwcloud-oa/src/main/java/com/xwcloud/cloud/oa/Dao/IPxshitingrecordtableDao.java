package com.xwcloud.cloud.oa.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xwcloud.cloud.model.entity.Pxshitingrecordtable;
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
public interface IPxshitingrecordtableDao extends BaseMapper<Pxshitingrecordtable> {

    @Results(id = "BaseResultMap", value = {
                @Result(column = "id", property = "id"),
                @Result(column = "daofangID", property = "daofangID"),
                @Result(column = "chabanOr1v1", property = "chabanOr1v1"),
                @Result(column = "subjectID", property = "subjectID"),
                @Result(column = "kechengID", property = "kechengID"),
                @Result(column = "classID", property = "classID"),
                @Result(column = "haveClassDate", property = "haveClassDate"),
                @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
                @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
                @Result(column = "weekN", property = "weekN"),
                @Result(column = "stTeacher", property = "stTeacher"),
                @Result(column = "yxStuID", property = "yxStuID"),
                @Result(column = "isAddStuNumToTeacher", property = "isAddStuNumToTeacher"),
                @Result(column = "shitingPrice", property = "shitingPrice"),
                @Result(column = "classRoomID", property = "classRoomID"),
                @Result(column = "shiTingManyiduID", property = "shiTingManyiduID"),
                @Result(column = "shiTingShuoming", property = "shiTingShuoming"),
                @Result(column = "addStaffID", property = "addStaffID"),
                @Result(column = "addTime", property = "addTime"),
                @Result(column = "qiyeID", property = "qiyeID"),
                @Result(column = "paikeID", property = "paikeID"),
                @Result(column = "liushuiID", property = "liushuiID"),
    })
    @Select("<script>" +
            "SELECT * from  pxshitingrecordtable"
            + "</script>")
    List<Pxshitingrecordtable> getAllList();
}