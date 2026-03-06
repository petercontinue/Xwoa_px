package com.xwcloud.cloud.sys.Dao;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.model.Vo.asstestyleVO;
import com.xwcloud.cloud.model.Vo.staffBirthVo;
import com.xwcloud.cloud.model.Vo.staffinfoVo;
import com.xwcloud.cloud.model.Vo.stafftelVo;
import com.xwcloud.cloud.model.entity.Pxstafftable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yinqi
 * @since 2020-10-18
 */
@Repository
public interface IPxstafftableDao extends BaseMapper<Pxstafftable> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "staffName", property = "staffName"),
            @Result(column = "staffTel", property = "staffTel"),
            @Result(column = "password", property = "password"),
            @Result(column = "role", property = "role"),
            @Result(column = "staffSex", property = "staffSex"),
            @Result(column = "staffBirthday", property = "staffBirthday"),
            @Result(column = "campusID", property = "campusID"),
            @Result(column = "campusID", property = "pxcampustable"),//, one = @One(select = "getPxcampustableById", fetchType = FetchType.EAGER)
            @Result(column = "staffPostID", property = "staffPostID"),
            @Result(column = "staffState", property = "staffState"),
            @Result(column = "photo", property = "photo"),
            @Result(column = "QQ", property = "qq"),
            @Result(column = "email", property = "email"),
            @Result(column = "wx", property = "wx"),
            @Result(column = "douyin", property = "douyin"),
            @Result(column = "joinTime", property = "joinTime"),
            @Result(column = "shuoMing", property = "shuoMing"),
            @Result(column = "openid", property = "openid"),
            @Result(column = "unionid", property = "unionid"),
            @Result(column = "phoneMac", property = "phoneMac"),
            @Result(column = "qiyeID", property = "qiyeID"),
            @Result(column = "showInApp", property = "showInApp"),
    })
    @Select("<script>" +
            "SELECT * from  pxstafftable"
            + "</script>")
    List<Pxstafftable> getAllList();

//    @Select("SELECT * FROM pxcampustable WHERE id = #{id}")
//    @Results(id = "BaseResultMap", value = {
//            @Result(column = "id", property = "id"),
//            @Result(column = "campusName", property = "campusName"),
//            @Result(column = "campusAddress", property = "campusAddress"),
//            @Result(column = "campusTel", property = "campusTel"),
//            @Result(column = "QRcodePrint", property = "QRcodePrint"),
//            @Result(column = "QRcodeWx", property = "QRcodeWx"),
//            @Result(column = "isOpen", property = "isOpen"),
//            @Result(column = "buyDateTime", property = "buyDateTime"),
//            @Result(column = "nextPayTime", property = "nextPayTime"),
//            @Result(column = "appID", property = "appID"),
//            @Result(column = "appSecret", property = "appSecret"),
//            @Result(column = "wxShanghuID", property = "wxShanghuID"),
//            @Result(column = "wxShanghuKey", property = "wxShanghuKey"),
//            @Result(column = "wxShanghuZhengshuAddr", property = "wxShanghuZhengshuAddr"),
//            @Result(column = "qiyeID", property = "qiyeID"),
//    })
//    Pxcampustable getPxcampustableById(@Param("id") String id);

    @Update("UPDATE pxstafftable SET staffState=#{staffState} WHERE id=#{id}")
    int updateStaffState(int staffState, Long id);

    /**
     * 分页查询员工通讯录
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id, a.staffName,a.campusID,c.campusName,a.staffTel,b.staffpostName,a.staffTel as UserName from pxstafftable a LEFT JOIN Pxstaffposttable b  on  a.staffPostID = b.id " +
            "LEFT JOIN Pxcampustable c on a.campusID = c.id"
            + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<stafftelVo> getstaffTel(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 修改员工电话
     *
     * @param staffTel
     * @param id
     * @return
     */
    @Update("UPDATE pxstafftable SET staffTel=#{staffTel} WHERE id=#{id}")
    int UpdatetaffTel(String staffTel, Long id);

    /**
     * 分页查询员工生日
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.id,c.campusName,a.staffTel,b.staffpostName,a.staffBirthday,a.staffName from pxstafftable a LEFT JOIN Pxstaffposttable b  on  a.staffPostID = b.id " +
            "LEFT JOIN Pxcampustable c on a.campusID = c.id" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<staffBirthVo> getStaffBirth(Page page, @Param("ew") Wrapper wrapper);

    /**
     * 查询所有员工生日信息（不分页）
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.id,c.campusName,a.staffTel,b.staffpostName,a.staffBirthday,a.staffName from pxstafftable a LEFT JOIN Pxstaffposttable b  on  a.staffPostID = b.id " +
            "LEFT JOIN Pxcampustable c on a.campusID = c.id" + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<staffBirthVo> GetStaffBirthList(@Param("ew") Wrapper wrapper);

    /**
     * 修改员工生日
     *
     * @param staffBirthday
     * @param id
     * @return
     */
    @Update("<script>" + "UPDATE pxstafftable SET staffBirthday=#{staffBirthday} WHERE id=#{id}" + "</script>")
    int UpdateStaffBirthday(String staffBirthday, Long id);

    /**
     * 查询员工信息
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Select("<script>" + "SELECT a.id,a.staffName,a.staffSex,a.staffTel,a.campusID as campusID,a.role role,a.staffPostID," +
            "a.staffState,b.campusName,c.staffpostName,a.joinTime,a.staffBirthday,a.shuoMing,a.jiaoxueJingyan," +
            "(SELECT nickName from wsc_user  where phoneNumber=a.staffTel and qiyeID=a.qiyeID ) nickName," +
            "(select GROUP_CONCAT(y.subjectName) from pxteachsubjecttable x JOIN pxsubjecttable y on x.teachSubjectID=y.id where staffID=a.id )  teaSubject  " +
            " FROM pxstafftable AS a " +
            "LEFT JOIN pxcampustable AS b ON a.campusID = b.id\n" +
            "LEFT JOIN pxstaffposttable AS c ON a.staffPostID = c.id\n" +
//            "LEFT JOIN pxwxusertable AS d ON a.staffTel=d.tel AND a.id = d.staffID" +
            " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    Page<staffinfoVo> GetstaffInfoPages(Page page, @Param("ew") Wrapper wrapper);

    @Select("<script>" + "SELECT * FROM pxstafftable WHERE campusID = #{campusID}" + "</script>")
    List<Pxstafftable> GetStaffListByCampusID(Long campusID);

    /**
     * 查询员工通讯录（不分页）
     *
     * @param wrapper
     * @return
     */
    @Select("<script>" +
            "SELECT a.id, a.staffName,a.campusID,c.campusName,a.staffTel,b.staffpostName,a.staffTel as UserName from pxstafftable a LEFT JOIN Pxstaffposttable b  on  a.staffPostID = b.id " +
            "LEFT JOIN Pxcampustable c on a.campusID = c.id"
            + " WHERE 1=1" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>"
            + "</script>")
    List<stafftelVo> GetstafftelList(@Param("ew") Wrapper wrapper);

    @Select("<script>" + "select * from pxstafftable where campusID = #{campusID} AND staffPostID =#{staffPostID}" + "</script>")
    List<Pxstafftable> GetstaffInfoBycampusidAndstaffpostID(Long campusID, Long staffPostID);

    /**
     * 查询所有员工（校区_姓名）绑定下拉选择
     *
     * @param qiyeID
     * @return
     */
    @Select("<script>" + "SELECT a.id,CONCAT(b.campusName,'_',a.staffName) AS `name` FROM pxstafftable AS a LEFT JOIN pxcampustable AS b ON a.campusID = b.id WHERE a.qiyeID =#{qiyeID}" + "</script>")
    List<asstestyleVO> GetAllStaffList(Long qiyeID);

    @Select("<script>" + "\tSELECT\n" +
            "\tc.text\n" +
            "\tFROM\n" +
            "\t\tpxpowertable AS a\n" +
            "\t\tLEFT JOIN pxstaffposttable AS b ON a.staffPostID = b.id\n" +
            "\t\tLEFT JOIN pxmenutable AS c ON a.menuID = c.id \n" +
            "\tWHERE\n" +
            "\t\ta.qiyeID = #{qiyeID} \n" +
            "\t\tAND a.staffPostID = #{staffPostID} " + "</script>")
    List<String> GetStaffInfoDetail(long qiyeID, long staffPostID);


    @Select("<script>" +
            "SELECT * from pxstafftable a \n" +
            "LEFT JOIN  pxwxusertable b on a.staffTel=b.tel \n" +
            " where 1=1\n" +
            "<if test='ew != null'>" +
            " AND ${ew.SqlSegment}" +
            "</if>" +
            " GROUP BY a.id"
            + "</script>")
    List<HashMap<String, String>> getnowxTeaList(@Param("ew") QueryWrapper queryWrapper);
}