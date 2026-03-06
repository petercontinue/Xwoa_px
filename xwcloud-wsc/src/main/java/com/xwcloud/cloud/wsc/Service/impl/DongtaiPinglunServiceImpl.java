package com.xwcloud.cloud.wsc.Service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Service.IDongtaiPinglunService;

import com.xwcloud.cloud.model.entity.DongtaiPinglun;
import com.xwcloud.cloud.wsc.Dao.IDongtaiPinglunDao;
import com.xwcloud.cloud.wsc.Dao.dongtaipinglunVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-19
 */
@Service
public class DongtaiPinglunServiceImpl extends ServiceImpl<IDongtaiPinglunDao, DongtaiPinglun> implements IDongtaiPinglunService {

    @Autowired
    IDongtaiPinglunDao iDongtaiPinglunDao;

    @Override
    public List<dongtaipinglunVO> GetDongtaiPinglunInfos(long dongtaiID) {
        return iDongtaiPinglunDao.GetDongtaiPinglunInfos(dongtaiID);
    }
}
