package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.ITuichongzhiyuespDao;
import com.xwcloud.cloud.caiwu.Service.ITuichongzhiyuespService;
import com.xwcloud.cloud.model.entity.Tuichongzhiyuesp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-07-16
 */
@Service
public class TuichongzhiyuespServiceImpl extends ServiceImpl<ITuichongzhiyuespDao, Tuichongzhiyuesp> implements ITuichongzhiyuespService {
	@Autowired
    ITuichongzhiyuespDao iTuichongzhiyuespDao;
}
