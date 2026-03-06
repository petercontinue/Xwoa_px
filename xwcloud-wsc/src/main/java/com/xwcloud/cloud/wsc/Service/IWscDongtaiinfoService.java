package com.xwcloud.cloud.wsc.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.dongtaiVO;
import com.xwcloud.cloud.model.Vo.dongtaiinfoVO;
import com.xwcloud.cloud.model.entity.WscDongtaiinfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-18
 */
public interface IWscDongtaiinfoService extends IService<WscDongtaiinfo> {
    List<dongtaiVO> GetIndexDongtaifenxiang(long wscuserID);

    List<dongtaiinfoVO> GetDongtaiInfo(long dongtaiID, long wscuserID);

    Page<dongtaiVO> GetMyDongtaiInfo(Page page, long wscuserID, QueryWrapper queryWrapper);
}
