package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Dao.IWscUserguanzhuDao;
import com.xwcloud.cloud.wsc.Service.IWscUserguanzhuService;

import com.xwcloud.cloud.model.Vo.fensiguanzhuVO;
import com.xwcloud.cloud.model.entity.WscUserguanzhu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-19
 */
@Service
public class WscUserguanzhuServiceImpl extends ServiceImpl<IWscUserguanzhuDao, WscUserguanzhu> implements IWscUserguanzhuService {

    @Autowired
    IWscUserguanzhuDao iWscUserguanzhuDao;

    @Override
    public Page<fensiguanzhuVO> GetAllfensiPages(Page page, long wscuserID) {
        return iWscUserguanzhuDao.GetAllfensiPages(page, wscuserID);
    }

    @Override
    public Page<fensiguanzhuVO> GetAllGuanzhuPages(Page page, long wscuserID) {
        return iWscUserguanzhuDao.GetAllGuanzhuPages(page, wscuserID);
    }
}
