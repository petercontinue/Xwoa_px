package com.xwcloud.cloud.oa.Dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.OA.OaHehuoren;
import com.xwcloud.cloud.model.OA.Vo.HehuorenVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-02
 */
@Repository
public interface IOaHehuorenDao extends BaseMapper<OaHehuoren> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "realName", property = "realName"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "prinvinceID", property = "prinvinceID"),
            @Result(column = "cityID", property = "cityID"),
            @Result(column = "hehuoLevel", property = "hehuoLevel"),
            @Result(column = "levelStartTime", property = "levelStartTime"),
            @Result(column = "levelEndTime", property = "levelEndTime"),
            @Result(column = "fanyongbiFloat", property = "fanyongbiFloat"),
            @Result(column = "shuoming", property = "shuoming"),
            @Result(column = "firstQiandanTime", property = "firstQiandanTime"),
            @Result(column = "hehuoType", property = "hehuoType"),
            @Result(column = "hhFrom", property = "hhFrom"),
            @Result(column = "hhrRole", property = "hhrRole"),
            @Result(column = "addUser", property = "addUser"),
            @Result(column = "addTime", property = "addTime")
    })
    @Select("<script>" +
            "SELECT * from  oa_hehuoren"
            + "</script>")
    List<OaHehuoren> getAllList();


    @Results(id = "HehuorenInfo", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "realName", column = "realName"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "fanyongbiFloat", column = "fanyongbiFloat"),
            @Result(property = "shuoming", column = "shuoming"),
            @Result(property = "levelStartTime", column = "levelStartTime"),
            @Result(property = "levelEndTime", column = "levelEndTime"),
            @Result(property = "firstQiandanTime", column = "firstQiandanTime"),
            @Result(property = "hehuoType", column = "hehuoType"),
            @Result(property = "hhFrom", column = "hhFrom"),
            @Result(property = "hhrRole", column = "hhrRole"),
            @Result(property = "kehucompanyname", column = "kehucompanyname"),
            @Result(property = "hehuoLevel", column = "hehuoLevel", jdbcType = JdbcType.VARCHAR),
            @Result(property = "staffName", column = "staffName"),
            @Result(property = "fangyongbili", column = "fangyongbili"),
            @Result(property = "prinvince", column = "prinvince"),
            @Result(property = "zongfyb", column = "zongfyb"),
            @Result(property = "city", column = "city"),
            @Result(property = "prinvinceID", column = "prinvinceID"),
            @Result(property = "cityID", column = "cityID"),
            @Result(property = "hehuoLevelID", column = "id", jdbcType = JdbcType.BIGINT),
            @Result(property = "addTime", column = "addTime")
    })
    @Select("<script>" +
            "SELECT hehuoren.id,\n" +
            "hehuoren.realName,\n" +
            "hehuoren.sex,\n" +
            "hehuoren.phone,\n" +
            "hehuoren.fanyongbiFloat,\n" +
            "hehuoren.shuoming,\n" +
            "hehuoren.levelStartTime,\n" +
            "hehuoren.levelEndTime,\n" +
            "hehuoren.firstQiandanTime,\n" +
            "hehuoren.hehuoType,\n" +
            "hehuoren.hhFrom,\n" +
            "hehuoren.hhrRole,\n" +
            "hehuoren.prinvinceID,\n" +
            "hehuoren.cityID,\n" +
            "kehu.kehucompanyname,\n" +
            "hehuorenlevel.id as hehuoLevelID,\n" +
            "hehuorenlevel.hehuoLevel,\n" +
            "staff.staffName,\n" +
            "hehuorenlevel.fangyongbili,\n" +
            "hehuoren.addTime,\n" +
            "areas1.areaname as prinvince,\n" +
            "areas2.areaname as city,\n" +
            "fangyongbili+fanyongbiFloat as zongfyb\n" +
            "FROM oa_hehuoren as hehuoren\n" +
            "LEFT JOIN oa_kehu as kehu ON hehuoren.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff as staff ON hehuoren.addUser=staff.id\n" +
            "LEFT JOIN oa_hehuoren_level as hehuorenlevel on hehuoren.hehuoLevel= hehuorenlevel.id\n" +
            "LEFT JOIN areas as areas1 on hehuoren.prinvinceID=areas1.id\n" +
            "LEFT JOIN areas as areas2 on hehuoren.cityID=areas2.id " + " where 1=1 " +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    IPage<HehuorenVo> getAllHehuorenInfo(Page<HehuorenVo> page, @Param("ew") Wrapper wrapper);


    @ResultMap("HehuorenInfo")
    @Select("SELECT hehuoren.id,\n" +
            "hehuoren.realName,\n" +
            "hehuoren.sex,\n" +
            "hehuoren.phone,\n" +
            "hehuoren.fanyongbiFloat,\n" +
            "hehuoren.shuoming,\n" +
            "hehuoren.levelStartTime,\n" +
            "hehuoren.levelEndTime,\n" +
            "hehuoren.firstQiandanTime,\n" +
            "hehuoren.hehuoType,\n" +
            "hehuoren.hhFrom,\n" +
            "hehuoren.hhrRole,\n" +
            "hehuoren.hehuoLevel,\n" +
            "hehuoren.prinvinceID,\n" +
            "hehuoren.cityID,\n" +
            "kehu.kehucompanyname,\n" +
            "hehuorenlevel.hehuoLevel,\n" +
            "staff.staffName,\n" +
            "hehuorenlevel.fangyongbili,\n" +
            "hehuoren.addTime\n" +
            "FROM oa_hehuoren as hehuoren\n" +
            "LEFT JOIN oa_kehu as kehu ON hehuoren.qiyeID=kehu.id\n" +
            "LEFT JOIN oa_staff as staff ON hehuoren.addUser=staff.id\n" +
            "LEFT JOIN oa_hehuoren_level as hehuorenlevel on hehuoren.hehuoLevel= hehuorenlevel.id\n" +
            "where hehuoren.id=#{id}")
        //获取一个合伙人信息
    HehuorenVo getOneHehuorenById(Long id);

}