package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.huodongdetailVO;
import com.xwcloud.cloud.model.Vo.mymobanVO;
import com.xwcloud.cloud.model.entity.WhdH5Huodongfabu;
import com.xwcloud.cloud.wsc.Dao.IWhdH5HuodongfabuDao;
import com.xwcloud.cloud.wsc.Service.IWhdH5HuodongfabuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-19
 */
@Service
public class WhdH5HuodongfabuServiceImpl extends ServiceImpl<IWhdH5HuodongfabuDao, WhdH5Huodongfabu> implements IWhdH5HuodongfabuService {

    @Autowired
    IWhdH5HuodongfabuDao whdH5HuodongfabuDao;

    @Override
    public Page<mymobanVO> GetMyzhaoxuexiuMobanPages(Page page, long qiyeID) {
        return whdH5HuodongfabuDao.GetMyzhaoxuexiuMobanPages(page, qiyeID);
    }

    @Override
    public huodongdetailVO getHuodongDetailInfo(long qiyeID, long huodongID) {
        return whdH5HuodongfabuDao.getHuodongDetailInfo(qiyeID, huodongID);
    }

    @Override
    public List<HashMap<String, Object>> GetMubanHuodongDetail(QueryWrapper wrapper) {
        return whdH5HuodongfabuDao.GetMubanHuodongDetail(wrapper);
    }
}
