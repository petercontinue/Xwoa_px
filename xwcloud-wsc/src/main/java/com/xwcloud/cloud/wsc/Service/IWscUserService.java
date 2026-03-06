package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.fensiguanzhuVO;
import com.xwcloud.cloud.model.Vo.fenxiaoinfoVO;
import com.xwcloud.cloud.model.Vo.yongjinVO;
import com.xwcloud.cloud.model.entity.WscUser;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWscUserService extends IService<WscUser> {
    Page<HashMap<String, Object>> GetWscUserPages(Page page, QueryWrapper wrapper);

    fenxiaoinfoVO getloginuserFenxiaoInfo(long userID);
    Page<fensiguanzhuVO> GetzhishuUserPages(Page page, long wscuserID);
    Page<fensiguanzhuVO> GetjianjietuijianPages(Page page,long wscuserID);
    Page<yongjinVO> GetyiwanchengYongjinInfo(Page page, long wscuserID);
    Page<yongjinVO> GetWeiwanchengYongjinInfoPages(Page page,long wscuserID);
}
