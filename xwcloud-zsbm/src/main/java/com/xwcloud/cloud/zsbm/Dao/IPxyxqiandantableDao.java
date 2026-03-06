package com.xwcloud.cloud.zsbm.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.yxqiandanVo;
import com.xwcloud.cloud.model.entity.Pxyxqiandantable;
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
 * @since 2021-04-15
 */
@Repository
public interface IPxyxqiandantableDao extends BaseMapper<Pxyxqiandantable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "stuID", property = "stuID"),
            @Result(column = "qianDanMoney", property = "qianDanMoney"),
            @Result(column = "isBaomingOrChongzhi", property = "isBaomingOrChongzhi"),
            @Result(column = "yxShichangRenID", property = "yxShichangRenID"),
            @Result(column = "yxDengjiRenID", property = "yxDengjiRenID"),
            @Result(column = "yxFuzheRenID", property = "yxFuzheRenID"),
            @Result(column = "yxQiandanRenID", property = "yxQiandanRenID"),
            @Result(column = "yxDengjiDateTime", property = "yxDengjiDateTime"),
            @Result(column = "yxFenpeiDateTime", property = "yxFenpeiDateTime"),
            @Result(column = "yxQiandanDateTime", property = "yxQiandanDateTime"),
            @Result(column = "qiyeID", property = "qiyeID"),
    })
    @Select("<script>" +
            "SELECT * from  pxyxqiandantable"
            + "</script>")
    List<Pxyxqiandantable> getAllList();


    //--------------------------------------意向学员----------------------------------
    //分页查询意向签单信息
    @Select("<script>" +
            "SELECT c.campusName,b.stuName,a.qianDanMoney,a.isBaomingOrChongzhi,a.yxQiandanDateTime,a.yxDengjiDateTime,\n" +
            "d.staffName fzStaffName,e.staffName scStaffName,f.staffName qdStaffName \n" +
            "FROM pxyxqiandantable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "LEFT JOIN pxstafftable d ON d.id=a.yxFuzheRenID\n" +
            "LEFT JOIN pxstafftable e ON e.id=a.yxShichangRenID\n" +
            "LEFT JOIN pxstafftable f ON f.id=a.yxQiandanRenID\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<yxqiandanVo> GetAllLiuyanyixiangqiandanPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" +
            "SELECT c.campusName,b.stuName,a.qianDanMoney,if(a.isBaomingOrChongzhi=1,'报名','充值')isBaomingOrChongzhi," +
            "a.yxQiandanDateTime,a.yxDengjiDateTime,\n" +
            "ifnull(d.staffName,'') fzStaffName,ifnull(e.staffName,'') scStaffName,f.staffName qdStaffName \n" +
            "FROM pxyxqiandantable AS a\n" +
            "LEFT JOIN pxstutable AS b ON a.stuID = b.id\n" +
            "LEFT JOIN pxcampustable AS c ON b.campusID = c.id\n" +
            "LEFT JOIN pxstafftable d ON d.id=a.yxFuzheRenID\n" +
            "LEFT JOIN pxstafftable e ON e.id=a.yxShichangRenID\n" +
            "LEFT JOIN pxstafftable f ON f.id=a.yxQiandanRenID\n" +
            "<where>" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<yxqiandanVo> GetyixiangqiandanList(@Param("ew") Wrapper wrapper);
}