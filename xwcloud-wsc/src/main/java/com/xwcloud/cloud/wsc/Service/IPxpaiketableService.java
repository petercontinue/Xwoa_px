package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.daikebiaoVO;
import com.xwcloud.cloud.model.Vo.stupaikeVO;
import com.xwcloud.cloud.model.Vo.teapaikeVO;
import com.xwcloud.cloud.model.Vo.xiaokeVO;
import com.xwcloud.cloud.model.entity.Pxpaiketable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
public interface IPxpaiketableService extends IService<Pxpaiketable> {
    Page<stupaikeVO> GetStupaikePages(Page page, QueryWrapper wrapper);

    Page<teapaikeVO> GetTeacherPaikePages(Page page, QueryWrapper wrapper);

    Page<daikebiaoVO>GetDayKebiaoPage(Page page, QueryWrapper wrapper);

    Page<xiaokeVO> GetAllxiaokeDataPage(Page page, QueryWrapper wrapper);
}
