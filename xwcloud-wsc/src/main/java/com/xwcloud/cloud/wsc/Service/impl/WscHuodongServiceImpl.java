package com.xwcloud.cloud.wsc.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.Vo.wschuodongVO;
import com.xwcloud.cloud.model.entity.WscHuodong;
import com.xwcloud.cloud.wsc.Dao.IWscHuodongDao;
import com.xwcloud.cloud.wsc.Service.IWscHuodongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class WscHuodongServiceImpl extends ServiceImpl<IWscHuodongDao, WscHuodong> implements IWscHuodongService {

    @Autowired
    IWscHuodongDao wscHuodongDao;

    @Override
    public List<WscHuodong> GetAllWscHuodongList() {
        return wscHuodongDao.GetAllWscHuodongList();
    }

    @Override
    public List<wschuodongVO> GetAllhuodongList(long qiyeID) {
        return wscHuodongDao.GetAllhuodongList(qiyeID);
    }
}
