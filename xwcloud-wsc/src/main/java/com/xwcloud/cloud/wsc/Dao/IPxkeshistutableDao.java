package com.xwcloud.cloud.wsc.Dao;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.stuKehaoVo;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
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
 * @since 2021-05-05
 */
@Repository
public interface IPxkeshistutableDao extends BaseMapper<Pxkeshistutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "tongkekechengID", property = "tongkekechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "StuGradeID", property = "stuGradeID"),
            @Result(column = "teacherIDs", property = "teacherIDs"),
            @Result(column = "teacherNames", property = "teacherNames"),
            @Result(column = "haveClassDate", property = "haveClassDate"),
            @Result(column = "weekN", property = "weekN"),
            @Result(column = "startLessonDateTime", property = "startLessonDateTime"),
            @Result(column = "endLessonDateTime", property = "endLessonDateTime"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "buxiStyleID", property = "buxiStyleID"),
            @Result(column = "classTimeStyleID", property = "classTimeStyleID"),
            @Result(column = "kechengPrice", property = "kechengPrice"),
            @Result(column = "stuKaoqingStyle", property = "stuKaoqingStyle"),
            @Result(column = "dakaoqingStyle", property = "dakaoqingStyle"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "adduser", property = "adduser"),
            @Result(column = "addtime", property = "addtime"),
            @Result(column = "zhujiao", property = "zhujiao"),
            @Result(column = "buxikechengID", property = "buxikechengID"),
            @Result(column = "banzhurenStaffID", property = "banzhurenStaffID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshistutable"
            + "</script>")
    List<Pxkeshistutable> getAllList();

    /**
     * 学员上课记录
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select a.id as id,b.id as stuID,b.zidingyiStuID as zidingyiID,b.stuName as stuName," +
            "f.stuGradeName as stuGradeName,d.className as className,h.kechengName as kechengName,i.staffName as banzhuren,c.buxiStyleName as buxiStyleName," +
            "a.teacherNames as teacherNames,a.zhujiao as zhujiao,a.haveClassDate as haveClassDate,a.weekN as weekN," +
            "a.startLessonDateTime as startLessonDateTime,a.endLessonDateTime as endLessonDateTime," +
            "a.keshiNum as keshiNum,a.kechengPrice as kechengPrice,g.campusName as campusName,a.stuKaoqingStyle as stukaoqing,a.shuoMing as shuoMing,(select staffName from pxstafftable where id = a.adduser) adduser,a.addtime addtime " +
            "from pxkeshistutable as a " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxbuxistyletable as c on a.buxiStyleID=c.id " +
            "LEFT JOIN pxclasstable as d on a.classID=d.id " +
            "LEFT JOIN pxclasstimestyletable as e on a.classTimeStyleID=e.id " +
            "LEFT JOIN pxstugradetable as f on b.stuGradeID=f.id " +
            "LEFT JOIN pxcampustable as g on a.campusID=g.id " +
            "LEFT JOIN pxkechengtable as h on a.kechengID=h.id " +
            "LEFT JOIN pxstafftable as i on b.banzhurenTeacherID=i.id" +
            " WHERE 1=1 and g.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<stuKehaoVo> getkeshiStu(Page page, @Param("ew") QueryWrapper queryWrapper);
}