package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.fensiguanzhuVO;
import com.xwcloud.cloud.model.Vo.fenxiaoinfoVO;
import com.xwcloud.cloud.model.Vo.yongjinVO;
import com.xwcloud.cloud.model.entity.WscUser;
import com.xwcloud.cloud.wsc.Dao.IWscUserDao;
import com.xwcloud.cloud.wsc.Service.IWscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
@Service
public class WscUserServiceImpl extends ServiceImpl<IWscUserDao, WscUser> implements IWscUserService {

    @Autowired
    IWscUserDao iWscUserDao;

    @Override
    public Page<HashMap<String, Object>> GetWscUserPages(Page page, QueryWrapper wrapper) {
        return iWscUserDao.GetWscUserPages(page, wrapper);
    }
    @Override
    public fenxiaoinfoVO getloginuserFenxiaoInfo(long userID) {
        return iWscUserDao.getloginuserFenxiaoInfo(userID);
    }

    @Override
    public Page<fensiguanzhuVO> GetzhishuUserPages(Page page, long wscuserID) {
        return iWscUserDao.GetzhishuUserPages(page, wscuserID);
    }

    @Override
    public Page<fensiguanzhuVO> GetjianjietuijianPages(Page page, long wscuserID) {
        return iWscUserDao.GetjianjietuijianPages(page, wscuserID);
    }

    @Override
    public Page<yongjinVO> GetyiwanchengYongjinInfo(Page page, long wscuserID) {
        return iWscUserDao.GetyiwanchengYongjinInfo(page, wscuserID);
    }

    @Override
    public Page<yongjinVO> GetWeiwanchengYongjinInfoPages(Page page, long wscuserID) {
        return iWscUserDao.GetWeiwanchengYongjinInfoPages(page, wscuserID);
    }
}
