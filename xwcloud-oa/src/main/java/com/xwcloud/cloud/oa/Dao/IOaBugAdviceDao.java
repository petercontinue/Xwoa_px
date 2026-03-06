package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.OA.OaBugAdvice;
import com.xwcloud.cloud.model.OA.Vo.BugOrAdviceInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-12
 */
@Repository
public interface IOaBugAdviceDao extends BaseMapper<OaBugAdvice> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "isBugOrAdvice", property = "isBugOrAdvice"),
            @Result(column = "content", property = "content"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "kehufanduiDateTime", property = "kehufanduiDateTime"),
            @Result(column = "addUser", property = "addUser"),
            @Result(column = "addTime", property = "addTime"),
    })
    @Select("<script>" +
            "SELECT * from  oa_bug_advice"
            + "</script>")
    List<OaBugAdvice> getAllList();


    @Results(id = "BugOrAdviceInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "isBugOrAdvice", column = "isBugOrAdvice"),
            @Result(property = "content", column = "content"),
            @Result(property = "qiyeID", column = "qiyeID"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "kehufankuiDateTime", column = "kehufankuiDateTime"),
            @Result(property = "addUser", column = "addUser"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "addTime", column = "addTime")
    })
    @Select("<script>" +
            "SELECT bug_advice.id,\n" +
            "bug_advice.isBugOrAdvice,\n" +
            "bug_advice.content,\n" +
            "bug_advice.qiyeID,\n" +
            "bug_advice.addUser,\n" +
            "bug_advice.kehufankuiDateTime,\n" +
            "bug_advice.addTime,\n" +
            "staff.staffName,\n" +
            "kehu.kehucompanyname\n" +
            "from oa_bug_advice bug_advice\n" +
            "LEFT JOIN oa_kehu kehu on bug_advice.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on bug_advice.addUser=staff.id\n" + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<BugOrAdviceInfo> getAllBugOrAdviceInfo(Page<BugOrAdviceInfo> page, @Param("ew") Wrapper wrapper);

    @ResultMap(value = "BugOrAdviceInfo")
    @Select("SELECT bug_advice.id,\n" +
            "bug_advice.isBugOrAdvice,\n" +
            "bug_advice.content,\n" +
            "bug_advice.qiyeID,\n" +
            "bug_advice.addUser,\n" +
            "bug_advice.kehufankuiDateTime,\n" +
            "bug_advice.addTime,\n" +
            "staff.staffName,\n" +
            "kehu.kehucompanyname\n" +
            "from oa_bug_advice bug_advice\n" +
            "LEFT JOIN oa_kehu kehu on bug_advice.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff staff on bug_advice.addUser=staff.id\n" +
            "where bug_advice.id=#{id}")
        //根据id查询一个
    BugOrAdviceInfo getOneBugOrAdviceInfo(Long id);

}