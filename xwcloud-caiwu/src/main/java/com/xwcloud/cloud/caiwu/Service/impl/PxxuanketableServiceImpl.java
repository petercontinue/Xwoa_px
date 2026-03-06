package com.xwcloud.cloud.caiwu.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.caiwu.Dao.IPxxuanketableDao;
import com.xwcloud.cloud.caiwu.Service.IPxxuanketableService;
import com.xwcloud.cloud.model.entity.Pxxuanketable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-15
 */
@Service
public class PxxuanketableServiceImpl extends ServiceImpl<IPxxuanketableDao, Pxxuanketable> implements IPxxuanketableService {
	@Autowired
    IPxxuanketableDao iPxxuanketableDao;
}
