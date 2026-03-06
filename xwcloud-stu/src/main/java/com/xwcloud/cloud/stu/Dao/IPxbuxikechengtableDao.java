package com.xwcloud.cloud.stu.Dao;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxbuxikechengtable;
import com.xwcloud.cloud.model.entity.Pxkechengtable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-06
 */
@Repository
public interface IPxbuxikechengtableDao extends BaseMapper<Pxbuxikechengtable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "kechengID", property = "kechengID"),
            @Result(column = "originalprice", property = "originalprice"),
            @Result(column = "kechengprice", property = "kechengprice"),
            @Result(column = "remainkeshi", property = "remainkeshi"),
            @Result(column = "keshiNum", property = "keshiNum"),
            @Result(column = "zongjia", property = "zongjia"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "buykeshiDateTime", property = "buykeshiDateTime"),
            @Result(column = "isShow", property = "isShow"),
            @Result(column = "jifeiStyleID", property = "jifeiStyleID"),
            @Result(column = "type", property = "type"),
            @Result(column = "qianDanSubjectID", property = "qianDanSubjectID"),
            @Result(column = "qianDanInfoID", property = "qianDanInfoID"),
            @Result(column = "shareBuxiID", property = "shareBuxiID"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxbuxikechengtable"
            + "</script>")
    List<Pxbuxikechengtable> getAllList();


    @Select("<script>" +
            "SELECT * from  pxbuxikechengtable " +
            " WHERE 1=1  " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<Pxbuxikechengtable> selectbxkc(@Param("ew") QueryWrapper queryWrapper);

    /**
     * 获取并入到的课程
     */

    @Select("<script>" +
            "SELECT a.id id,b.kechengName name,a.kechengprice kechengprice,a.remainkeshi remainkeshi from pxbuxikechengtable a \n" +
            "LEFT JOIN pxkechengtable b on a.kechengID = b.id \n" +
            "WHERE a.id!=#{buxiID} and a.stuID=#{stuID} and a.isShow=1 and (a.jifeiStyleID=1 or a.jifeiStyleID=2) and a.qiyeID=#{qiyeID}" +
            "</script>")
    List<NewhebingKcVo> getHebingTokecheng(Long buxiID, Long stuID, Long qiyeID);

    //获取学员补习课程
    @Select("<script>" +
            "SELECT\n" +
            "\tkc.id id,\n" +
            "pxstutable.parentTel," +
            "\tpxcampustable.campusName,\n" +
            "\tkc.isShow,kc.stuID AS stuID,\n" +
            "pxstutable.zidingyiStuID zidingyiStuID," +
            "\t\tpxstutable.stuName AS stuName,\n" +
            "\t\tkc.keshiNum AS Courses,\n" +
            "\t\tkc.remainkeshi AS remainkeshi,\n" +
            "\t\tpxsubjecttable.subjectName AS bxsubject,\n" +
            "\t\tpxstutable.activity AS stuStatus,\n" +
            "pxstutable.buxiStateID buxiStateID," +
            "\t\tpxbuxistyletable.buxiStyleName AS buxiStatus,\n" +
            "\t\tkc.kechengID kechengID,\n" +
            "\t\tpxkechengtable.kechengName AS buxiCourse,\n" +
            "\t\tpxclasstimestyletable.classTimeStyleName AS CourseTime, pxkechengtable.classTimeStyleID CourseTimeID,\n" +
            "\t\tkc.jifeiStyleID AS buxiPayStyle,\n" +
            "\t\tkc.originalprice AS OldCoursePrice,\n" +
            "\t\tkc.kechengprice AS CoursePrice,\n" +
            "\t\tkc.startDate AS startTime,\n" +
            "\t\tkc.endDate AS endTime,\n" +
            "\t\t( SELECT COUNT( id ) FROM pxstukxqtable a WHERE a.bxkcID = kc.id ) crossCampus,\n" +
            "\t\tkc.type type,kc.shareBuxiID shareBuxiID \n" +
            "\tFROM\n" +
            "\t\tpxbuxikechengtable AS kc\n" +
            "\t\tJOIN pxstutable ON kc.stuID = pxstutable.id\n" +
            "\t\tJOIN pxkechengtable ON kc.kechengID = pxkechengtable.id\n" +
            "\t\tJOIN pxsubjecttable ON pxkechengtable.subjectID = pxsubjecttable.id\n" +
            "\t\tJOIN pxbuxistyletable ON pxkechengtable.buxiStyleID = pxbuxistyletable.id\n" +
            "\t\tJOIN pxcampustable ON pxstutable.campusID = pxcampustable.id\n" +
            "\t\tJOIN pxclasstimestyletable ON pxkechengtable.classTimeStyleID = pxclasstimestyletable.id \n" +
            "\tWHERE\n" +
            "\t\t pxcampustable.isOpen != 2  " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "ORDER BY kc.stuID DESC" +
            "</script>")
    Page<buxiKeChengVo> getbuxiCourse(Page page, @Param("ew") QueryWrapper queryWrapper);


    @Select("<script>" +
            "SELECT a.*,b.stuName stuName,c.kechengName kechengName from pxbuxikechengtable a \n" +
            "join pxstutable b on a.stuID =b.id\n" +
            "join pxkechengtable c on a.kechengID =c.id " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<HashMap<String, Object>> getshareInfo(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 获取校区校的所有课程
     *
     * @param campusID
     * @param jifeiStyleID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,a.kechengName name ,a.buyZonjia buyZonjia,a.kechengOriginalPrice kechengOriginalPrice," +
            "a.kechengprice kechengprice,a.keshiNum keshiNum " +
            " from pxkechengtable a \n" +
            "LEFT JOIN pxsubjecttable b on a.subjectID=b.id\n" +
            "LEFT JOIN pxbuxistyletable c on a.buxiStyleID=c.id\n" +
            "WHERE b.campusID=#{campusID} and a.isShow=1 and jifeiStyleID=#{jifeiStyleID} and a.qiyeID=#{qiyeID}\n"
            + "</script>")
    List<addKSBkcVo> Getnewkc(Long campusID, int jifeiStyleID, Long qiyeID);

    /**
     * 换课获取科目课程
     *
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id ,  concat(concat(concat('(','', d.campusName),'_',b.subjectName),') ', a.kechengName) name ,a.jifeiStyleID jifeiStyleID \n" +
            "from pxkechengtable a \n" +
            "LEFT JOIN pxsubjecttable b on a.subjectID=b.id\n" +
            "LEFT JOIN pxbuxistyletable c on a.buxiStyleID=c.id\n" +
            "LEFT JOIN pxcampustable d on a.campusID=d.id\n" +
            "where d.isOpen!=2 and a.isShow=1 and a.qiyeID=#{qiyeID}\n"
            + "</script>")
    List<ChangekechengVo> getnewkcInfo(Long qiyeID);


    /***
     * 扩科转送获取课程
     * @param campusID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id , concat(concat('(',b.subjectName, ')'),' ',a.kechengName) name\n" +
            "from pxkechengtable a \n" +
            "LEFT JOIN pxsubjecttable b on a.subjectID =b.id\n" +
            "LEFT JOIN pxcampustable c on a.campusID=c.id\n" +
            "where c.isOpen!=2 and  a.campusID=#{campusID} and a.isShow=1 and a.qiyeID=#{qiyeID}\n"
            + "</script>")
    List<listVo> getcamkecheng(Long campusID, Long qiyeID);

    /**
     * 跨校区设置详情
     *
     * @param buxiID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id, a.stuID stuID,c.zidingyiStuID zidingyiStuID,c.stuName stuName,a.bxkcID bxkcID,(SELECT campusName from pxcampustable where id=c" +
            ".campusID and d.isOpen !=2) stucampusName,\n" +
            "b.kechengName kechengName,d.campusName kxqCampusName,e.staffName addstaff,a.addTime addTime\n" +
            "from pxstukxqtable a\n" +
            "LEFT JOIN pxkechengtable b on a.kcID=b.id\n" +
            "LEFT JOIN pxstutable c on a.stuID=c.id\n" +
            "LEFT JOIN pxcampustable d on a.kxqCampusID=d.id\n" +
            "LEFT JOIN pxstafftable e on a.addStaffID=e.id\n" +
            "where d.isOpen !=2 and a.bxkcID=#{buxiID} and a.qiyeID=#{qiyeID}" +
            "</script>")
    Page<kxqKcInfoVo> getkxqinfo(Page page, Long buxiID, Long qiyeID);

    //导出学员补习课程
    @Select("<script>" +
            "SELECT pxcampustable.campusName,kc.isShow,pxstutable.id as stuID,pxstutable.parentTel parentTel,pxstutable.stuName as stuName,kc.keshiNum as " +
            "Courses,kc.remainkeshi as remainkeshi,pxsubjecttable.subjectName as bxsubject," +
            "pxstutable.activity as stuStatus,pxbuxistyletable.buxiStyleName as buxiStatus,pxkechengtable.kechengName as buxiCourse,pxclasstimestyletable" +
            ".classTimeStyleName as CourseTime," +
            "kc.jifeiStyleID as buxiPayStyle,kc.originalprice as OldCoursePrice,kc.kechengprice as CoursePrice,kc.startDate as startTime,kc.endDate as " +
            "endTime,pxstugradetable.stuGradeName as stuGradeName " +
            "from pxbuxikechengtable as kc " +
            "left join pxstutable on kc.stuID=pxstutable.id " +
            "left join pxkechengtable on kc.kechengID= pxkechengtable.id " +
            "left join pxsubjecttable on pxkechengtable.subjectID=pxsubjecttable.id " +
            "left join pxbuxistyletable on pxkechengtable.buxiStyleID=pxbuxistyletable.id " +
            "left join pxcampustable on pxstutable.campusID=pxcampustable.id " +
            "left join pxclasstimestyletable on pxkechengtable.classTimeStyleID =pxclasstimestyletable.id " +
            "left join pxstugradetable on pxstutable.stuGradeID=pxstugradetable.id" +
            " WHERE 1=1 and pxcampustable.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<buxiKeChengVo> ExportbuxiCourse(@Param("ew") QueryWrapper queryWrapper);

    //添加课时计费课程时
    @Select("<script>" +
            "select a.id as id,a.buxiStyleID as buxiStyleID,b.buxiStyleName as buxiStyleName,b.is1v1 as is1v1 " +
            "from pxkechengtable as a " +
            "LEFT JOIN pxbuxistyletable as b on a.buxiStyleID=b.id " +
            " WHERE buxiStyleName='一对一' and is1v1=1 and a.id=#{kcID} and a.qiyeID=#{qiyeID}" +
            "</script>")
    List<stubxStyleVo> getstubxStyle(Long kcID, Long qiyeID);

    //获取课程
    @Select("<script>" +
            "SELECT a.id id ,b.kechengName name,b.kechengprice kechengprice from pxbuxikechengtable a\n" +
            "LEFT JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "where a.stuID=#{stuID}  and a.isShow=1 and a.type!=2 and a.qiyeID=#{qiyeID}" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<HashMap<String, Object>> getZSkechengs(String stuID, Long qiyeID, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 扩科获取其他课程
     *
     * @param stuID
     * @param qiyeID
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT id ,kechengName name,kechengprice kechengprice from pxkechengtable where id not in " +
            "(SELECT b.id  from pxbuxikechengtable a LEFT JOIN pxkechengtable b on a.kechengID=b.id where a.stuID=#{stuID} and a.qiyeID=#{qiyeID} )" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<HashMap<String, Object>> getOtherKechengs(String stuID, Long qiyeID, @Param("ew") QueryWrapper queryWrapper);


    @Select("<script>" +
            "SELECT a.id id,b.kechengName name,a.type type,a.remainkeshi remainkeshi,a.kechengprice kechengprice " +
            "from pxbuxikechengtable a\n" +
            "LEFT JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "where (b.jifeiStyleID=1 or b.jifeiStyleID=2) and a.isShow=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<kechengVos> getzhuangsongkecheng(@Param("ew") QueryWrapper queryWrapper);

    @Select("<script>" +
            "SELECT a.id id,b.kechengName name,a.type type,a.remainkeshi remainkeshi,a.kechengprice kechengprice " +
            "from pxbuxikechengtable a\n" +
            "LEFT JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "where a.stuID=#{stuID} and b.jifeiStyleID=3 and a.isShow=1  and a.qiyeID=#{qiyeID} and a.type !=2 " +
            "</script>")
    List<kechengVos> getzhuangsongdaykecheng(Long stuID, Long qiyeID);

    //region 课时转送
    @Select("<script>" +
            "select a.id,a.zhuansongDate,pxstafftable.staffName as jingbanren,cama.campusName as songcampus,stua.id as songstuID,\n" +
            "stua.zidingyiStuID zidingyiStuIDa,stua.stuName as songstu,kca.kechengName as songkc,a.songKeshiNum,a.songkechengPrice * a.songKeshiNum as " +
            "songkechengPrice,\n" +
            "(SELECT sum(remainkeshi) from pxbuxikechengtable where kechengID=kca.id and stuID=stua.id ) as songyukeshi,camb.campusName as shoucampus,stub.id" +
            " as shoustuID,\n" +
            "stub.zidingyiStuID zidingyiStuIDb,stub.stuName as shoustu,kcb.kechengName as shoukc,a.shouKeshi as shouKeshi,\n" +
            "(SELECT sum(remainkeshi) from pxbuxikechengtable where kechengID=kcb.id and stuID=stub.id ) as shouyukeshi,\n" +
            "a.shoukechengPrice * a.shouKeshi as shoukechengPrice,a.shuoMing,kca.jifeiStyleID songjifeiStyleID,kcb.jifeiStyleID shoujifeiStyleID ,\n" +
            "((SELECT (case WHEN COUNT(q.id) &gt; 0 THEN sum(q.remainkeshi) ELSE 0 END)  from pxbuxikechengtable q where  q.kechengID=a.songkechengID and q" +
            ".stuID =a.songstuID)+\n" +
            "(SELECT (case WHEN COUNT(id) &gt; 0 THEN SUM(songKeshiNum) ELSE 0 END) from pxkeshizhuansongtable where id!= a.id and songstuID=a.songstuID and " +
            "songkechengID=a.songkechengID and a.zhuansongDate &lt; zhuansongDate )) allshengyukeshi\n" +
            "from pxkeshizhuansongtable as a \n" +
            "left join pxstutable as stua on a.songstuID=stua.id\n" +
            "LEFT JOIN pxstutable as stub on a.shoustuID=stub.id\n" +
            "LEFT JOIN pxcampustable as cama on stua.campusID=cama.id \n" +
            "LEFT JOIN pxcampustable as camb on stub.campusID=camb.id \n" +
            "LEFT JOIN pxkechengtable as kca on a.songkechengID=kca.id \n" +
            "LEFT JOIN pxkechengtable as kcb on a.shoukechengID=kcb.id \n" +
            "LEFT JOIN pxstafftable on a.adduser=pxstafftable.id " +
            " WHERE 1=1 and cama.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<transferVo> getTransfer(Page page, @Param("ew") QueryWrapper queryWrapper);

    //导出课时转送
    @Select("<script>" +
            "select a.id,a.zhuansongDate as zhuansongDate,pxstafftable.staffName as jingbanren," +
            "cama.campusName as songcampus,stua.id as songstuID,stua.stuName as songstu,kca.kechengName as songkc,a.songKeshiNum as songKeshiNum,a" +
            ".songkechengPrice * a.songKeshiNum as songkechengPrice," +
            "(SELECT sum(remainkeshi) from pxbuxikechengtable where kechengID=kca.id and stuID=stua.id ) as songyukeshi," +
            "camb.campusName as shoucampus,stub.id as shoustuID,stub.stuName as shoustu,kcb.kechengName as shoukc,a.shouKeshi as shouKeshi," +
            "(SELECT sum(remainkeshi) from pxbuxikechengtable where kechengID=kcb.id and stuID=stub.id ) as shouyukeshi," +
            "a.shoukechengPrice * a.shouKeshi as shoukechengPrice,a.shuoMing as shuoMing " +
            "from pxkeshizhuansongtable as a " +
            "left join pxstutable as stua on a.songstuID=stua.id " +
            "LEFT JOIN pxstutable as stub on a.shoustuID=stub.id " +
            "LEFT JOIN pxcampustable as cama on stua.campusID=cama.id " +
            "LEFT JOIN pxcampustable as camb on stub.campusID=camb.id " +
            "LEFT JOIN pxkechengtable as kca on a.songkechengID=kca.id " +
            "LEFT JOIN pxkechengtable as kcb on a.shoukechengID=kcb.id " +
            "LEFT JOIN pxstafftable on a.adduser=pxstafftable.id" +
            " WHERE 1=1 and cama.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<transferVo> ExportTransfer(@Param("ew") QueryWrapper queryWrapper);

    //按照学员ID，课程ID获取启用的补习课程
    @Select("<script>" + "select * from pxbuxikechengtable where stuID=#{stuID} and kechengID=#{kechengID} and isShow=1 and qiyeID=#{qiyeID}" + "</script>")
    List<Pxbuxikechengtable> getStubxkc(Long stuID, Long kechengID, Long qiyeID);

    //按照学员ID，课程ID获取未启用的补习课程
    @Select("<script>" + "select * from pxbuxikechengtable where stuID=#{stuID} and kechengID=#{kechengID} and isShow=0 and qiyeID=#{qiyeID}" + "</script>")
    List<Pxbuxikechengtable> getStubxkcNoShow(Long stuID, Long kechengID, Long qiyeID);

    //按照ID和课单价获取去补习课程
    @Select("<script>" + "select * from pxbuxikechengtable where id=#{ID} and kechengprice=#{kechengprice} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxbuxikechengtable> getShoubxkc(Long ID, BigDecimal kechengprice, Long qiyeID);
    //endregion

    //region 课时赠送
    @Select("<script>" +
            "select a.id,stu.id as stuID,stu.zidingyiStuID zidingyiStuID,stu.stuName as stuName,pxcampustable.campusName as campusName,pxstugradetable" +
            ".stuGradeName as stuGradeName,(select staffName from pxstafftable where id=stu.banzhurenTeacherID) as banzhuren," +
            "pxkechengtable.kechengName as kechengName,a.keshiShu as keshiShu,a.jifeiStyle as jifeiStyle,a.songYangyin as songYangyin,a.addDate as addDate," +
            "pxstafftable.staffName as staffName " +
            "from pxkeshizengsongtable as a " +
            "LEFT JOIN pxstutable as stu on a.stuID = stu.id " +
            "LEFT JOIN pxcampustable on stu.campusID=pxcampustable.id " +
            "LEFT JOIN pxstugradetable on stu.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxstafftable on a.caozuoStaffId=pxstafftable.id " +
            "LEFT JOIN pxkechengtable on a.kechengID=pxkechengtable.id" +
            " WHERE 1=1 and pxcampustable.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<zengsongVo> getZengSong(Page page, @Param("ew") QueryWrapper queryWrapper);

    // 导出课时赠送
    @Select("<script>" +
            "select a.id,stu.id as stuID,stu.stuName as stuName,pxcampustable.campusName as campusName,pxstugradetable.stuGradeName as stuGradeName,(select " +
            "staffName from pxstafftable where id=stu.banzhurenTeacherID) as banzhuren," +
            "pxkechengtable.kechengName as kechengName,a.keshiShu as keshiShu,a.jifeiStyle as jifeiStyle,a.songYangyin as songYangyin,a.addDate as addDate," +
            "pxstafftable.staffName as staffName " +
            "from pxkeshizengsongtable as a " +
            "LEFT JOIN pxstutable as stu on a.stuID = stu.id " +
            "LEFT JOIN pxcampustable on stu.campusID=pxcampustable.id " +
            "LEFT JOIN pxstugradetable on stu.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxstafftable on a.caozuoStaffId=pxstafftable.id " +
            "LEFT JOIN pxkechengtable on a.kechengID=pxkechengtable.id" +
            " WHERE 1=1 and pxcampustable.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<zengsongVo> ExportZengSong(@Param("ew") QueryWrapper queryWrapper);

    //赠送课时
    @Select("<script>" + "select * from pxbuxikechengtable where id=#{id} and stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxbuxikechengtable> getByIDAStuID(Long id, Long stuID, Long qiyeID);

    //按照课程ID获取
    @Select("<script>" + "select * from pxbuxikechengtable where kechengID=#{kcID} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxbuxikechengtable> getByKechengID(Long kcID, Long qiyeID);

    //按照ID、学员ID获取
    @Select("<script>" + "select * from pxbuxikechengtable where id=#{ID} and stuID=#{stuID} and jifeiStyleID=3 and qiyeID=#{qiyeID}" + "</script>")
    List<Pxbuxikechengtable> getJF3(Long ID, Long stuID, Long qiyeID);

    //按照学员ID获取
    @Select("<script>" + "select * from pxbuxikechengtable where stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxbuxikechengtable> getByStuID(Long stuID, Long qiyeID);

    //按照学员ID、课程Id获取buxiID
    @Select("<script>" + "select * from pxbuxikechengtable where stuID=#{stuID} and kechengID=#{kcID} and qiyeID=#{qiyeID} " + "</script>")
    List<Pxbuxikechengtable> getBxToStuAKc(Long stuID, Long kcID, Long qiyeID);

    //按照学员ID、课程ID、单价等获取buxiID
    @Select("<script>" + "select * from pxbuxikechengtable where stuID=#{stuID} and kechengID=#{kcID} and kechengprice=#{kechengprice} and type=2 and " +
            "qiyeID=#{qiyeID}" + "</script>")
    List<Pxbuxikechengtable> getBxToType(Long stuID, Long kcID, BigDecimal kechengprice, Long qiyeID);
    //endregion

    //region 课程变动流水
    @Select("<script>" +
            "select kccg.id,kccg.type as type,pxcampustable.campusName as campusName," +
            "stu.stuName as oldStu,stu.id as oldStuID,kc.kechengName as oldkechengName,kccg.oldprice as oldprice,kccg.oldrkeshi as oldrkeshi,kccg.oldzongjia " +
            "as oldzongjia,kccg.oldstartDate as oldstartDate," +
            "kccg.oldendDate as oldendDate,kccg.newStuID as newStuID,(select stuName from pxstutable where id=kccg.newStuID) as newStu,(select kechengName " +
            "from pxkechengtable where id=kccg.newkcID) as newkechengName," +
            "kccg.newprice as newprice,kccg.newrkeshi as newrkeshi,kccg.newzongjia as newzongjia,kccg.newstartDate as newstartDate,kccg.newendDate as " +
            "newendDate,pxstafftable.staffName as jingbanren,kccg.addDate as addDate " +
            "from pxbxkcchangetable as kccg " +
            "LEFT JOIN pxstutable as stu on kccg.oldStuID=stu.id " +
            "LEFT JOIN pxkechengtable as kc on kccg.oldkcID=kc.id " +
            "LEFT JOIN pxstafftable on kccg.addStaffID=pxstafftable.id " +
            "LEFT JOIN pxcampustable on stu.campusID=pxcampustable.id " +
            " WHERE 1=1 and pxcampustable.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            " ORDER BY  kccg.addDate DESC " +
            "</script>")
    Page<bxChangeVo> getkcLiuShui(Page page, @Param("ew") QueryWrapper queryWrapper);

    //导出
    @Select("<script>" +
            "select kccg.id,kccg.type as type,pxcampustable.campusName as campusName," +
            "stu.stuName as oldStu,stu.id as oldStuID,kc.kechengName as oldkechengName,kccg.oldprice as oldprice,kccg.oldrkeshi as oldrkeshi,kccg.oldzongjia " +
            "as oldzongjia,kccg.oldstartDate as oldstartDate," +
            "kccg.oldendDate as oldendDate,kccg.newStuID as newStuID,(select stuName from pxstutable where id=kccg.newStuID) as newStu,(select kechengName " +
            "from pxkechengtable where id=kccg.newkcID) as newkechengName," +
            "kccg.newprice as newprice,kccg.newrkeshi as newrkeshi,kccg.newzongjia as newzongjia,kccg.newstartDate as newstartDate,kccg.newendDate as " +
            "newendDate,pxstafftable.staffName as jingbanren,kccg.addDate as addDate " +
            "from pxbxkcchangetable as kccg " +
            "LEFT JOIN pxstutable as stu on kccg.oldStuID=stu.id " +
            "LEFT JOIN pxkechengtable as kc on kccg.oldkcID=kc.id " +
            "LEFT JOIN pxstafftable on kccg.addStaffID=pxstafftable.id " +
            "LEFT JOIN pxcampustable on stu.campusID=pxcampustable.id " +
            " WHERE pxcampustable.isOpen !=2 and kccg.qiyeID=#{qiyeID}" +
            "</script>")
    List<bxChangeVo> ExportkcLiuShui(Long qiyeID);
    //endregion

    //region 学员成绩
    @Select("<script>" +
            "select sc.id as id,pxcampustable.campusName as campusName," +
            "pxstugradetable.stuGradeName as stuGradeName,stu.stuName as stuName,(select staffName from pxstafftable where id=stu.banzhurenTeacherID) as " +
            "banzhuren,k.id kechengID,k.kechengName kechengName ,sc.testTypeID testTypeID," +
            "pxsubjecttable.subjectName as subjectName,sc.testTitle as testTitle,sc.score as score,sc.dankepaiming as dankepaiming,sc.zongfenpaiming as " +
            "zongfenpaiming,pxtesttypetable.testType as testType," +
            "sc.scoreType as scoreType,sc.scoreDate as scoreDate,sc.addDateTime as addDateTime,pxstafftable.staffName as staffName,sc.beiZhu as beiZhu " +
            "from pxscoretable as sc " +
            "LEFT JOIN pxstutable as stu on sc.stuID=stu.id " +
            "LEFT JOIN pxstafftable on sc.addStaffID=pxstafftable.id " +
            "LEFT JOIN pxstugradetable on stu.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxcampustable on stu.campusID=pxcampustable.id " +
            "LEFT JOIN pxsubjecttable on sc.subjectID=pxsubjecttable.id " +
            "LEFT JOIN pxtesttypetable on sc.testTypeID=pxtesttypetable.id " +
            "LEFT JOIN pxkechengtable k on sc.kechengID=k.id " +
            " WHERE 1=1 and pxcampustable.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<scoreVo> getScore(Page page, @Param("ew") QueryWrapper queryWrapper);

    //导出
    @Select("<script>" +
            "select sc.id as id,pxcampustable.campusName as campusName," +
            "pxstugradetable.stuGradeName as stuGradeName,stu.stuName as stuName,(select staffName from pxstafftable where id=stu.banzhurenTeacherID) as " +
            "banzhuren," +
            "pxsubjecttable.subjectName as subjectName,sc.testTitle as testTitle,sc.score as score,sc.dankepaiming as dankepaiming,sc.zongfenpaiming as " +
            "zongfenpaiming,pxtesttypetable.testType as testType," +
            "sc.scoreType as scoreType,sc.scoreDate as scoreDate,sc.addDateTime as addDateTime,pxstafftable.staffName as staffName,sc.beiZhu as beiZhu " +
            "from pxscoretable as sc " +
            "LEFT JOIN pxstutable as stu on sc.stuID=stu.id " +
            "LEFT JOIN pxstafftable on sc.addStaffID=pxstafftable.id " +
            "LEFT JOIN pxstugradetable on stu.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxcampustable on stu.campusID=pxcampustable.id " +
            "LEFT JOIN pxsubjecttable on sc.subjectID=pxsubjecttable.id " +
            "LEFT JOIN pxtesttypetable on sc.testTypeID=pxtesttypetable.id " +
            " WHERE 1=1 and pxcampustable.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<scoreVo> ExportScore(@Param("ew") QueryWrapper queryWrapper);

    //endregion

    //region 学员考级
    @Select("<script>" +
            "select kj.id as id,pxcampustable.campusName as campusName,pxstugradetable.stuGradeName as stuGradeName,stu.stuName as stuName,pxsubjecttable" +
            ".subjectName as subjectName,stu.id stuID,pxsubjecttable.id subjectID," +
            "kj.jibie as jibie,(select COUNT(id) FROM pxkaojisqtable where stuid=kj.stuid) as kaojisq,pxstafftable.staffName as staffName,kj.time as " +
            "addDateTime " +
            "from pxkaojitable as kj " +
            "LEFT JOIN pxstutable as stu on kj.stuID=stu.id " +
            "LEFT JOIN pxstafftable on kj.addsatff=pxstafftable.id " +
            "LEFT JOIN pxstugradetable on stu.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxcampustable on stu.campusID=pxcampustable.id " +
            "LEFT JOIN pxsubjecttable on kj.kemuid=pxsubjecttable.id " +
            " WHERE 1=1 and pxcampustable.isOpen !=2 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<kaojiVo> getKaoJi(Page page, @Param("ew") QueryWrapper queryWrapper);

    //导出考级
    @Select("<script>" +
            "select kj.id as id,pxcampustable.campusName as campusName,pxstugradetable.stuGradeName as stuGradeName,stu.stuName as stuName,pxsubjecttable" +
            ".subjectName as subjectName," +
            "kj.jibie as jibie,(select COUNT(id) FROM pxkaojisqtable where stuid=kj.stuid) as kaojisq,pxstafftable.staffName as staffName,kj.time as " +
            "addDateTime " +
            "from pxkaojitable as kj " +
            "LEFT JOIN pxstutable as stu on kj.stuID=stu.id " +
            "LEFT JOIN pxstafftable on kj.addsatff=pxstafftable.id " +
            "LEFT JOIN pxstugradetable on stu.stuGradeID=pxstugradetable.id " +
            "LEFT JOIN pxcampustable on stu.campusID=pxcampustable.id " +
            "LEFT JOIN pxsubjecttable on kj.kemuid=pxsubjecttable.id " +
            " WHERE 1=1 and pxcampustable.isOpen !=2 and kj.qiyeID=#{qiyeID}" +
            "</script>")
    List<kaojiVo> ExportKaoJi(Long qiyeID);
    //endregion

    //region 发证
    @Select("<script>" +
            "SELECT stu.id id ,stu.stuName,pxkechengtable.kechengName as kechengName,pxcertificatetable.zsName as zsName,stu.id stuID,\n" +
            "(SELECT COUNT(id) from pxfazhengtable where Stuid=stu.id AND ZSid=pxkechengtable.ZSid) as FZstate,\n" +
            "(SELECT pxstafftable.staffName from pxfazhengtable LEFT JOIN pxstafftable on pxfazhengtable.FZstaff=pxstafftable.id where Stuid=stu.id and " +
            "ZSid=pxkechengtable.ZSid ) as FZstaff,pxkechengtable.ZSid ZSid,\n" +
            "(SELECT FZdate from pxfazhengtable where Stuid=stu.id AND ZSid=pxkechengtable.ZSid) asFZdate ,\n" +
            "(SELECT FZImage from pxfazhengtable where stuID=stu.id and ZSid=pxkechengtable.ZSid ) FZImage\n" +
            "from pxstutable as stu \n" +
            "LEFT JOIN pxbuxikechengtable on stu.id=pxbuxikechengtable.stuID \n" +
            "LEFT JOIN pxcampustable on stu.campusID = pxcampustable.id \n" +
            "LEFT JOIN pxkechengtable on pxbuxikechengtable.kechengID=pxkechengtable.id \n" +
            "LEFT JOIN pxstuclasstable on pxbuxikechengtable.id = pxstuclasstable.buxiID\n" +
            "LEFT JOIN pxclasstable on pxstuclasstable.classID=pxclasstable.id \n" +
            "LEFT JOIN pxcertificatetable on pxkechengtable.ZSid=pxcertificatetable.id  " +
            " WHERE 1=1 and pxcampustable.isOpen !=2 " +
            "and (stu.buxiStateID =2 or stu.buxiStateID =3 or stu.buxiStateID =6) " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<fazhengVo> getfazhengPage(Page page, @Param("ew") QueryWrapper queryWrapper);
    //endregion

    //按照学员ID获取
    @Select("<script>" + "select * from pxbuxikechengtable where stuID=#{stuID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxbuxikechengtable> getbuxikc(Long stuID, Long qiyeID);

    //按照课程ID获取
    @Select("<script>" + "select * from pxkechengtable where id=#{kcID} and qiyeID=#{qiyeID}" + "</script>")
    List<Pxkechengtable> getKc(Long kcID, Long qiyeID);


    /**
     * 班课获取学员名单
     */
    @Select("<script>" + "SELECT a.id id,c.id campusID,c.campusName campusName,a.stuID stuID,b.zidingyiStuID zidingyiStuID,\n" +
            "b.stuName stuName,d.id stuGradeID,d.stuGradeName stuGradeName,f.kechengName kechengName,a.kechengID kechengID,a.isShow isShow \n" +
            "from pxbuxikechengtable a \n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxcampustable c on b.campusID=c.id\n" +
            "LEFT JOIN pxstugradetable d on b.stuGradeID=d.id\n" +
            "LEFT JOIN pxstuclasstable e on a.id=e.buxiID\n" +
            "LEFT JOIN pxkechengtable f on a.kechengID=f.id\n" +
            "WHERE c.isOpen !=2 and (b.buxiStateID=1 or b.buxiStateID=2 or b.buxiStateID=3 or b.buxiStateID=6) " +
            " and e.classID=#{classID} and a.qiyeID=#{qiyeID}" + "</script>")
    Page<classgetstuVo> getstumingdan(Page page, Long classID, Long qiyeID);

    /**
     * 插班/转班/退班
     */
    @Select("<script>" +
            "SELECT a.id id, b.campusID campusID,d.campusName campusName,b.id stuID,b.zidingyiStuID zidingyiStuID,b.stuName stuName,c.stuGradeName " +
            "stuGradeName,b.stuGradeID stuGradeID,a.kechengID KechengID,\n" +
            "(case WHEN a.type !=2 THEN (case WHEN a.jifeiStyleID=3 THEN e.kechengName ELSE concat(concat('(',a.kechengprice, ')'),' ', e.kechengName) END) " +
            "ELSE concat('(赠送)',' ', e.kechengName) END) kechengName,a.isShow isShow,\n" +
            "(SELECT (case WHEN COUNT(id)>0 THEN COUNT(id) ELSE 0 END) from pxstuclasstable where buxiID=a.id) classcount,\n" +
            "(SELECT GROUP_CONCAT(DISTINCT className) FROM pxstuclasstable i LEFT JOIN pxclasstable j on i.classID=j.id where buxiID=a.id) className, \n" +
            "(SELECT GROUP_CONCAT(DISTINCT classID) FROM pxstuclasstable i LEFT JOIN pxclasstable j on i.classID=j.id where buxiID=a.id) classID \n" +
            "from pxbuxikechengtable a \n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxstugradetable c on b.stuGradeID=c.id\n" +
            "LEFT JOIN pxcampustable d on b.campusID=d.id\n" +
            "LEFT JOIN pxkechengtable e ON a.kechengID=e.id\n" +
            "LEFT JOIN pxsubjecttable g on e.subjectID=g.id\n" +
            "LEFT JOIN pxbuxistyletable h on e.buxiStyleID=h.id\n" +
            "WHERE b.buxiStateID=2 and h.buxiStyleName != '一对一' \n " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<ZbCbVo> getzhuangbanchaban(Page page, @Param("ew") QueryWrapper queryWrapper);

    /**
     * 转班时的原班级
     *
     * @param buxiID
     * @param qiyeID
     * @return
     */
    @Select("<script>" +
            "SELECT b.classID id ,c.className name \n" +
            "from pxbuxikechengtable a  \n" +
            "LEFT JOIN pxstuclasstable b on a.id=b.buxiID\n" +
            "LEFT JOIN pxclasstable c on b.classID=c.id\n" +
            "where a.id=#{buxiID} and a.qiyeID=#{qiyeID} " +
            "</script>")
    List<listVo> getoldclass(Long buxiID, Long qiyeID);


    @Select("<script>" +
            "SELECT * from pxbuxikechengtable a where a.qiyeID=#{qiyeID} and a.id =#{id} " +
            "</script>")
    List<Pxbuxikechengtable> getAny(Long qiyeID, Long id);


    @Select("<script>" +
            "SELECT a.id id ,a.stuID stuID,d.stuName stuName,c.className className,e.kechengName \n" +
            "from pxbuxikechengtable a \n" +
            "LEFT JOIN pxstuclasstable b on a.id=b.buxiID\n" +
            "LEFT JOIN pxclasstable c on b.classID=c.id\n" +
            "LEFT JOIN pxstutable d on a.stuID=d.id\n" +
            "LEFT JOIN pxkechengtable e on a.kechengID=e.id\n" +
            "WHERE a.id=#{id} and a.qiyeID=#{qiyeID}" +
            "</script>")
    Page<havaclassVo> getHaveClass(Page page, Long id, Long qiyeID);


    @Select("<script>" +
            "select sum(remainkeshi) as allrkeshi from pxbuxikechengtable where kechengID=#{kechengID} and stuID=#{stuID} and qiyeID=#{qiyeID}"
            + "</script>")
    List<allrkeshiVo> getallrkeshi(Long kechengID, Long stuID, Long qiyeID);


    @Select("<script>" +
            "SELECT a.id id ,d.campusName campusName,(case WHEN b.zidingyiStuID is null THEN a.stuID ELSE b.zidingyiStuID END) stuID,b.stuName stuName,c" +
            ".stuGradeName stuGradeName,e.kechengName kechengName,\n" +
            "(case WHEN e.isShow =1 THEN '启用' ELSE '未启用' END) isShow,\n" +
            "(select (case WHEN COUNT(n.id)>0 THEN GROUP_CONCAT(DISTINCT m.className) ELSE '' END) from pxstuclasstable n LEFT JOIN pxclasstable m on n" +
            ".classID =m.id where n.buxiID =a.id) allClassName \n" +
            "from pxbuxikechengtable a \n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxstugradetable c on b.stuGradeID=c.id\n" +
            "LEFT JOIN pxcampustable d on b.campusID=d.id\n" +
            "LEFT JOIN pxkechengtable e on a.kechengID =e.id\n" +
            "LEFT JOIN pxsubjecttable f on e.subjectID=f.id\n" +
            "LEFT JOIN pxbuxistyletable g on e.buxiStyleID=g.id\n" +
            "where b.buxiStateID=2 and a.qiyeID=#{qiyeID}\n" +
            "</script>")
    List<DeriveChabanXinxiVo> DeriveChabanXinxi(Long qiyeID);

    @Select("<script>" +
            "select * from pxbuxikechengtable where  stuID=#{stuID} and id!=#{buxiID} and qiyeID=#{qiyeID}"
            + "</script>")
    List<Pxbuxikechengtable> getstuALLbuxiKC(Long stuID, Long buxiID, Long qiyeID);


    @Select("<script>" +
            "SELECT a.*,b.kechengName kechengName,c.id classtimeID,c.classTimeStyleName classTimeStyleName from pxbuxikechengtable a \n" +
            "JOIN pxkechengtable b on a.kechengID=b.id\n" +
            "join pxclasstimestyletable c on b.classTimeStyleID=c.id " +
            "join pxcampustable d on b.campusID=d.id \n" +
            "where d.isOpen !=2" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    List<HashMap<String, Object>> getstuOtherbuxi(@Param("ew") QueryWrapper queryWrapper);
}