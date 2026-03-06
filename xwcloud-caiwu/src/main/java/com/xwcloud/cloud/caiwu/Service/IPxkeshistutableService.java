package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.LiushilvStuVo;
import com.xwcloud.cloud.model.entity.Pxkeshistutable;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
public interface IPxkeshistutableService extends IService<Pxkeshistutable> {
    Page<HashMap<String, Object>> getNianjiPage(Page page, Wrapper wrapper);

    Page<HashMap<String, Object>> getBanjiPage(Page page, Wrapper wrapper);

    Page<HashMap<String, Object>> getBuxiStylePage(Page page, Wrapper wrapper);

    Page<HashMap<String, Object>> getKemuPage(Page page, Wrapper wrapper);

    Page<HashMap<String, String>> getBanzhurenPage(Page page, Wrapper wrapper);

    Page<HashMap<String, String>> getYuejunkehaoPage(Page page, Wrapper wrapper);

    Page<HashMap<String, Object>> getJiaoshiyuejunkehaoPage(Page page, Wrapper wrapper);

    List<HashMap<String, String>> getKehaotongbihuanbi(long campusID, Integer startDate, Integer endDate, long qiyeID);

    Page<HashMap<String, Object>> getXuesherenshu(Page page, QueryWrapper queryWrapper, QueryWrapper queryWrapper2);

    List<HashMap<String, Object>> exportxueyuanrenshu(@Param("ew") QueryWrapper queryWrapper,QueryWrapper queryWrapper2);

    Page<HashMap<String, String>> getTeacherStu(Page page, long campusID, String teacherName, long qiyeID);

    Page<HashMap<String, String>> getTeacherStuDetaile(Page page, long campusID, long teacherID, long qiyeID);

    Page<HashMap<String, String>> getBanzhurenStu(Page page, long campusID, String teacherName, long qiyeID);

    Page<HashMap<String, String>> getBanzhurenStuDetaile(Page page, long campusID, long teacherID, long qiyeID);

    List<HashMap<String, Object>> getBanzhurenList(Wrapper wrapper);

    Page<LiushilvStuVo> getTeaStuLiushiPage(Page page, Wrapper wrapper, QueryWrapper queryWrapper);

    Page<LiushilvStuVo> getBanzhurenStuLiushiPage(Page page, Wrapper wrapper);
//    Page<LiushilvStuVo> getCampusStuLiushiPage(Page page, Wrapper wrapper);

    Page<LiushilvStuVo> getCampusStuLiushiPage(Page page, Wrapper wrapper, int yue, QueryWrapper queryWrapper);

    Page<HashMap<String, Object>> getClassProfit(Page page, QueryWrapper wrapper);

}
