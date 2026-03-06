package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.*;
import com.xwcloud.cloud.model.entity.Pxqiandaoqiantuitable;
import org.apache.ibatis.annotations.Param;


import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-04
 */
public interface IPxqiandaoqiantuitableService extends IService<Pxqiandaoqiantuitable> {
    List<Pxqiandaoqiantuitable> selectqiaodao(QueryWrapper queryWrapper);

    List<ExportQianCountVo> ExportQianCount(Long qiyeID);

    List<ExportQianInfoVo> ExportQianInfo(Long qiyeID);

    Page<ziyouqiandaoVo> getziyouqiandaoPage(Page page, QueryWrapper queryWrapper);

    Page<ziyouqiandaostuNumVo> getziyouqiandaostuNumPage(Page page, Long classID, Long qiyeID);

    Page<ziyouqiandaostuNumVo> getziyouqiantuistuNumPage(Page page, Long classID, Long qiyeID);

    Page<stuqiaoDaoNumVo> getstuqiaoDaoNumPage(Page page, Long qiyeID, Long stuID);

    Page<stuqiaoDaoNumVo> getstuqiaoTuiNumPage(Page page, Long qiyeID, Long stuID);

    Page<qiandaoLiushuiVo> getqiandaoliushuiPage(Page page, QueryWrapper queryWrapper);

    Page<shuakaVo> getshuakaPage(Page page, QueryWrapper queryWrapper);

    List<shuakaVo> ExportshuakaPage(QueryWrapper queryWrapper);

    Page<shuakaxiaokeVo> getshuakaxiaokePage(Page page, QueryWrapper queryWrapper);

    List<shuakaxiaokeVo> Exportshuakaxiaoke(QueryWrapper queryWrapper);

    Page<HashMap<String, Object>> GetshualianxiaokeInfoPages(Page page,QueryWrapper queryWrapper);
}
