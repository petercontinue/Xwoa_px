package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.wsc.Dao.IPxyueketeacherfabupriceDao;
import com.xwcloud.cloud.wsc.Service.IPxyueketeacherfabupriceService;

import com.xwcloud.cloud.model.entity.Pxyueketeacherfabuprice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-17
 */
@Service
public class PxyueketeacherfabupriceServiceImpl extends ServiceImpl<IPxyueketeacherfabupriceDao, Pxyueketeacherfabuprice> implements IPxyueketeacherfabupriceService {

    @Autowired
    IPxyueketeacherfabupriceDao iPxyueketeacherfabupriceDao;

    @Override
    public List<Pxyueketeacherfabuprice> GetAllYuekepriceByyuekeTeacherId(long yuekeID, long qiyeID) {
        return iPxyueketeacherfabupriceDao.GetAllYuekepriceByyuekeTeacherId(yuekeID, qiyeID);
    }
}
