package com.xwcloud.cloud.pkxk.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.SumkehaoVo;
import com.xwcloud.cloud.model.Vo.stukehaoShowVo;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import com.xwcloud.cloud.model.pkxk.Vo.stuKehaoVo;

import com.xwcloud.cloud.model.Vo.xiaokedayingVo;
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
 * @since 2020-11-22
 */
@Repository
public interface IPxkeshistutableDao extends BaseMapper<Pxkeshistutable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "classID", property = "classID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "kechengContent", property = "kechengContent"),
            @Result(column = "StuGradeID", property = "StuGradeID"),
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
            @Result(column = "shareBuxiID", property = "shareBuxiID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxkeshistutable"
            + "</script>")
    List<Pxkeshistutable> getAllList();

    /**
     * 各条件查询数据
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT * from  pxkeshistutable" +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxkeshistutable> selectkehao(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 分页获取学生课耗
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select a.id as id,b.id as stuID,b.zidingyiStuID as zidingyiID,b.stuName as stuName,e.classTimeStyleName  classTimeStyleName," +
            "f.stuGradeName as stuGradeName,d.className as className,h.kechengName as kechengName,i.staffName as banzhuren,c.buxiStyleName as buxiStyleName," +
            "a.teacherNames as teacherNames,(select staffName from pxstafftable where id=a.zhujiao) as zhujiao," +
            "a.haveClassDate as haveClassDate,a.weekN as weekN," +
            "a.startLessonDateTime as startLessonDateTime,a.endLessonDateTime as endLessonDateTime," +
            "a.keshiNum as keshiNum,a.kechengPrice as kechengPrice,g.campusName as campusName,a.stuKaoqingStyle as stukaoqing,a.shuoMing as shuoMing," +
            "addu.staffName AS adduser,a.addtime addtime," +
            "(case WHEN (a.shareBuxiID is NULL or a.shareBuxiID=0) THEN 2 ELSE 1 END) sharexiaoke ," +
            "(case WHEN (a.shareBuxiID is NULL or a.shareBuxiID=0) THEN '' ELSE (SELECT concat(z.stuName,'的:',y.kechengName) from pxbuxikechengtable x \n" +
            "join pxkechengtable y on x.kechengID=y.id\n" +
            "join pxstutable z on x.stuID=z.id\n" +
            "where x.id =1364771634743554049) END) sharexiaokeshuoming "+
            "from pxkeshistutable as a " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxbuxistyletable as c on a.buxiStyleID=c.id " +
            "LEFT JOIN pxclasstable as d on a.classID=d.id " +
            "LEFT JOIN pxclasstimestyletable as e on a.classTimeStyleID=e.id " +
            "LEFT JOIN pxstugradetable as f on b.stuGradeID=f.id " +
            "LEFT JOIN pxcampustable as g on a.campusID=g.id " +
            "LEFT JOIN pxkechengtable as h on a.kechengID=h.id " +
            "LEFT JOIN pxstafftable as i on b.banzhurenTeacherID=i.id\n" +
            "LEFT JOIN pxstafftable as addu on a.adduser=addu.id"+
            " WHERE 1=1 and g.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<stuKehaoVo> getStukehao(Page page, @Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT sum(keshinum * kechengprice) as kehao from  pxkeshistutable" +
            " where buxikechengid=#{buxiID} and qiyeID=#{qiyeID}" +
            "</script>")
    SumkehaoVo getkehao(Long buxiID, Long qiyeID);

    /**
     * 导出学员课耗
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "select a.id as id,b.id as stuID,b.stuName as stuName,\n" +
            "f.stuGradeName as stuGradeName,d.className as className,h.kechengName as kechengName,\n" +
            " (case WHEN i.staffName is null THEN '-' ELSE i.staffName END)  banzhuren,\n" +
            "c.buxiStyleName as buxiStyleName,\n" +
            " (case WHEN a.stuKaoqingStyle=1 THEN '正常' \n" +
            " WHEN a.stuKaoqingStyle=2 THEN '请假' \n" +
            " WHEN a.stuKaoqingStyle=3 THEN '旷课' \n" +
            " WHEN a.stuKaoqingStyle=4 THEN '迟到' \n" +
            " WHEN a.stuKaoqingStyle=5 THEN '早退' ELSE '补课' END) as stukaoqing,\n" +
            "a.teacherNames as teacherNames,a.zhujiao as zhujiao,a.haveClassDate as haveClassDate,a.weekN as weekN,\n" +
            "a.startLessonDateTime as startLessonDateTime,a.endLessonDateTime as endLessonDateTime,\n" +
            "a.keshiNum as keshiNum,a.kechengPrice as kechengPrice,g.campusName as campusName,a.shuoMing as shuoMing \n" +
            "from pxkeshistutable as a \n" +
            "LEFT JOIN pxstutable as b on a.stuID=b.id \n" +
            "LEFT JOIN pxbuxistyletable as c on a.buxiStyleID=c.id \n" +
            "LEFT JOIN pxclasstable as d on a.classID=d.id \n" +
            "LEFT JOIN pxclasstimestyletable as e on a.classTimeStyleID=e.id \n" +
            "LEFT JOIN pxstugradetable as f on b.stuGradeID=f.id \n" +
            "LEFT JOIN pxcampustable as g on a.campusID=g.id \n" +
            "LEFT JOIN pxkechengtable as h on a.kechengID=h.id \n" +
            "LEFT JOIN pxstafftable as i on b.banzhurenTeacherID=i.id" +
            " WHERE 1=1 and g.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<stuKehaoVo> ExportStukehao(@Param("ew") QueryWrapper queryWrapper);


    /**
     * 学员已上课耗查看
     *
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id as id,b.zidingyiStuID as zidingyiStuID,a.stuID as stuID,b.stuName as stuName,d.className as className," +
            "a.haveClassDate as haveClassDate,a.kechengPrice as kechengPrice," +
            "a.keshiNum as keshiNum, a.keshiNum * a.kechengPrice as payMoney " +
            "FROM pxkeshistutable as a " +
            "LEFT JOIN pxstutable as b on a.stuID=b.id " +
            "LEFT JOIN pxbuxistyletable as c on a.buxiStyleID=c.id " +
            "LEFT JOIN pxclasstable as d on a.classID=d.id " +
            "LEFT JOIN pxclasstimestyletable as e on a.classTimeStyleID=e.id " +
            " WHERE 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<stukehaoShowVo> getstukehaoShowPage(Page page, @Param("ew") QueryWrapper queryWrapper);


    /**
     * 消课打印
     *
     * @param page
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,g.campusName campusName,b.id stuID,b.zidingyiStuID zidingyiStuID,b.stuName stuName,b.stuGradeID StuGradeID,f.stuGradeName stuGradeName,\n" +
            "d.className className,a.buxiStyleID buxiStyleID,c.buxiStyleName buxiStyleName,a.teacherNames staffName,a.zhujiao zhujiao,\n" +
            "a.haveClassDate haveClassDate,a.startLessonDateTime startLessonDateTime,a.endLessonDateTime endLessonDateTime,a.buxikechengID buxikechengID,\n" +
            "a.keshiNum keshiNum,\n" +
            "(select  (case WHEN remainkeshi is NULL THEN 0 ELSE remainkeshi END) from pxbuxikechengtable WHERE stuID=a.stuID and id=a.buxikechengID LIMIT 1) remainkeshi,\n" +
            "a.adduser adduser,a.addtime addTime\n" +
            "from pxkeshistutable a \n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxbuxistyletable c on a.buxiStyleID=c.id\n" +
            "LEFT JOIN pxclasstable d on a.classID=d.id\n" +
            "LEFT JOIN pxclasstimestyletable e on a.classTimeStyleID=e.id\n" +
            "LEFT JOIN pxstugradetable f on a.StuGradeID=f.id\n" +
            "LEFT JOIN pxcampustable g on a.campusID=g.id\n" +
            "WHERE g.isOpen !=2 and a.addtime &gt; date_sub(now(), interval 2 hour) " +
            "</script>")
    Page<xiaokedayingVo> getxiaokedayingPage(Page page);

}