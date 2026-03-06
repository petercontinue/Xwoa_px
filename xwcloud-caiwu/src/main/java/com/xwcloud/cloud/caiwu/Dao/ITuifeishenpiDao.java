package com.xwcloud.cloud.caiwu.Dao;





import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.shenpiVo;
import com.xwcloud.cloud.model.entity.Tuifeishenpi;
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
 * @since 2021-04-08
 */
@Repository
public interface ITuifeishenpiDao extends BaseMapper<Tuifeishenpi> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "tuiqiandanID", property = "tuiqiandanID"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "spqiandanID", property = "spqiandanID"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "type", property = "type"),
            @Result(column = "yingtuiMoney", property = "yingtuiMoney"),
            @Result(column = "sqtuiMoney", property = "sqtuiMoney"),
            @Result(column = "youhuiMoney", property = "youhuiMoney"),
            @Result(column = "daijinquanMoney", property = "daijinquanMoney"),
            @Result(column = "tuifeibanlidate", property = "tuifeibanlidate"),
            @Result(column = "tuifeishuoming", property = "tuifeishuoming"),
            @Result(column = "sppayMoneystyle", property = "sppayMoneystyle"),
            @Result(column = "chuliTime", property = "chuliTime"),
            @Result(column = "yejiren", property = "yejiren"),
            @Result(column = "spUser", property = "spUser"),
            @Result(column = "spfinish", property = "spfinish"),
            @Result(column = "spshuoming", property = "spshuoming"),
            @Result(column = "adduser", property = "adduser"),
            @Result(column = "addTiem", property = "addTiem"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  tuifeishenpi"
            + "</script>")
    List<Tuifeishenpi> getAllList();

    /**
     * （分页）退费审批
     * @param page
     * @param queryWrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id id,b.id stuID, b.zidingyiStuID zidingyiStuID,b.stuName stuName,a.type type,a.sqtuiMoney sqtuiMoney,c.moneystyleName moneystyleName,\n" +
            "a.tuifeibanlidate tuifeibanlidate,a.chuliTime chuliTime,(SELECT staffName from pxstafftable where id=a.yejiren) yejistaff,\n" +
            "a.tuifeishuoming tuifeishuoming,(SELECT staffName from pxstafftable where id=a.adduser) shenqinren ," +
            "(SELECT staffName from pxstafftable where id=a.spUser) shenpiren,a.spfinish spfinish,a.spshuoming spshuoming \n" +
            "from tuifeishenpi a \n" +
            "LEFT JOIN pxstutable b on a.stuID=b.id\n" +
            "LEFT JOIN pxpaymoneystyletable c on a.sppayMoneystyle=c.id " +
            "where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</script>")
    Page<shenpiVo> getshenpiPage(Page page, @Param("ew") QueryWrapper queryWrapper);


}