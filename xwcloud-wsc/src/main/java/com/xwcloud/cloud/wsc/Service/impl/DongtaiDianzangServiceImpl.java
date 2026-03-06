package com.xwcloud.cloud.wsc.Service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Service.IDongtaiDianzangService;

import com.xwcloud.cloud.model.entity.DongtaiDianzang;
import com.xwcloud.cloud.wsc.Dao.IDongtaiDianzangDao;
import com.xwcloud.cloud.wsc.Dao.dongtaidianzanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-19
 */
@Service
public class DongtaiDianzangServiceImpl extends ServiceImpl<IDongtaiDianzangDao, DongtaiDianzang> implements IDongtaiDianzangService {

    @Autowired
    IDongtaiDianzangDao iDongtaiDianzangDao;

    @Override
    public List<dongtaidianzanVO> GetAllDongtaiDianzangInfo(long dongtaiID) {
        return iDongtaiDianzangDao.GetAllDongtaiDianzangInfo(dongtaiID);
    }
}
