package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.WhdToupiaoHuodongVo;
import com.xwcloud.cloud.model.entity.WhdToupiaoHuodong;
import com.xwcloud.cloud.wsc.Dao.IWhdToupiaoHuodongDao;
import com.xwcloud.cloud.wsc.Service.IWhdToupiaoHuodongService;
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
 * @since 2021-01-17
 */
@Service
public class WhdToupiaoHuodongServiceImpl extends ServiceImpl<IWhdToupiaoHuodongDao, WhdToupiaoHuodong> implements IWhdToupiaoHuodongService {

    @Autowired
    private IWhdToupiaoHuodongDao whdToupiaoHuodongDao;

    @Override
    public Page<WhdToupiaoHuodongVo> getWhdToupiaoHuodongPage(Page page, QueryWrapper wrapper) {
        return whdToupiaoHuodongDao.getWhdToupiaoHuodongPage(page, wrapper);
    }

    @Override
    public String gettoupaioNum(QueryWrapper wrapper) {
        return whdToupiaoHuodongDao.gettoupaioNum(wrapper);
    }

    @Override
    public List<HashMap<String, Object>> GetToupiaoHuodongInfoByID(long toupiaohdID) {
        return whdToupiaoHuodongDao.GetToupiaoHuodongInfoByID(toupiaohdID);
    }
}
