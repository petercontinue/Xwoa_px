package com.xwcloud.cloud.wsc.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxxuanketable;
import com.xwcloud.cloud.wsc.Dao.IPxxuanketableDao;
import com.xwcloud.cloud.wsc.Service.IPxxuanketableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-31
 */
@Service
public class PxxuanketableServiceImpl extends ServiceImpl<IPxxuanketableDao, Pxxuanketable> implements IPxxuanketableService {
	@Autowired
    IPxxuanketableDao iPxxuanketableDao;
}
