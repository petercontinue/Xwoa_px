package com.xwcloud.cloud.wsc.Dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xwcloud.cloud.model.Vo.fensiguanzhuVO;
import com.xwcloud.cloud.model.entity.WscUserguanzhu;
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
 * @since 2021-05-19
 */
@Repository
public interface IWscUserguanzhuDao extends BaseMapper<WscUserguanzhu> {

    @Results(id = "BaseResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "wscuserID", property = "wscuserID"),
            @Result(column = "beiguanzhuUserID", property = "beiguanzhuUserID"),
            @Result(column = "guanzhuDatetine", property = "guanzhuDatetine"),
    })
    @Select("<script>" +
            "SELECT * from  wsc_userguanzhu"
            + "</script>")
    List<WscUserguanzhu> getAllList();

    /**
     * 分页查询我的粉丝信息
     * @param page
     * @param wscuserID
     * @return
     */
    @Select("<script>"+"SELECT u.nickName,u.headImage,a.guanzhuDatetine,u.sex,(SELECT COUNT(*) FROM wsc_dongtaiinfo WHERE wscuserID=u.id) AS dtcount,u.id as scuserID " +
            "FROM wsc_userguanzhu AS a LEFT JOIN wsc_user AS u ON a.wscuserID = u.id " +
            "WHERE  a.beiguanzhuUserID = #{wscuserID}"+"</script>")
    Page<fensiguanzhuVO> GetAllfensiPages(Page page, long wscuserID);

    /**
     * 分页查询我的关注信息
     * @param page
     * @param wscuserID
     * @return
     */
    @Select("<script>"+"SELECT u.nickName,u.headImage,a.guanzhuDatetine,u.sex,(SELECT COUNT(*) FROM wsc_dongtaiinfo WHERE wscuserID=a.beiguanzhuUserID) AS dtcount,u.id as scuserID" +
            " FROM wsc_userguanzhu AS a LEFT JOIN wsc_user " +
            "AS u ON a.beiguanzhuUserID = u.id WHERE a.wscuserID = #{wscuserID}"+"</script>")
    Page<fensiguanzhuVO> GetAllGuanzhuPages(Page page,long wscuserID);
}