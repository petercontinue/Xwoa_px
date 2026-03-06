package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.OaStaff;
import com.xwcloud.cloud.model.OA.Vo.StaffVo;
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
 * @since 2021-06-29
 */
@Repository
public interface IOaStaffDao extends BaseMapper<OaStaff> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "addtime", property = "addtime"),
            @Result(column = "offtime", property = "offtime"),
            @Result(column = "passwd", property = "passwd"),
            @Result(column = "stafflogoimg", property = "stafflogoimg"),
            @Result(column = "staffName", property = "staffName"),
            @Result(column = "staffpostID", property = "staffpostID"),
            @Result(column = "staffpostName", property = "staffpostName"),
            @Result(column = "staffBirthday", property = "staffBirthday"),
            @Result(column = "staffstate", property = "staffstate"),
            @Result(column = "stafftel", property = "stafftel"),
            @Result(column = "workName", property = "workName"),
            @Result(column = "worktel", property = "worktel"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "shuoming", property = "shuoming"),
    })
    @Select("<script>" +
            "SELECT * from  oa_staff"
            + "</script>")
    List<OaStaff> getAllList();

    @Results(id = "StaffInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "addtime", column = "addtime"),
            @Result(property = "passwd", column = "passwd"),
            @Result(property = "stafflogoimg", column = "stafflogoimg"),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "staffpostID", column = "staffpostID"),
            @Result(property = "staffpostName", column = "staffpostName"),
            @Result(property = "staffstate", column = "staffstate"),
            @Result(property = "stafftel", column = "stafftel"),
            @Result(property = "workName", column = "workName"),
            @Result(property = "worktel", column = "worktel"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "shuoming", column = "shuoming"),
            @Result(property = "staffBirthday", column = "staffBirthday"),
            @Result(property = "offtime", column = "offtime")
    })
    @Select("<script>" + "SELECT staff.id,\n" +
            "staff.addtime,\n" +
            "staff.offtime,\n" +
            "staff.passwd,\n" +
            "staff.stafflogoimg,\n" +
            "staff.staffName,\n" +
            "staff.staffpostID,\n" +
            "p.staffpostName AS staffpostName,\n" +
            "staff.staffBirthday,\n" +
            "staff.staffstate,\n" +
            "staff.stafftel,\n" +
            "staff.shuoming,\n" +
            "staff.workName,staff.worktel,\n" +
            "kehu.kehucompanyname\n" +
            "from oa_staff staff\n" +
            "LEFT JOIN oa_staffpost AS p ON staff.staffpostID = p.id\n" +
            "LEFT JOIN oa_kehu kehu on staff.qiyeID=kehu.id " + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<StaffVo> getAllStaffInfo(Page<StaffVo> page, @Param("ew") Wrapper wrapper);
}