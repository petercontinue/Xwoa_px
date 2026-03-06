package com.xwcloud.cloud.pkxk.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxqingjiatable;
import com.xwcloud.cloud.pkxk.Dao.IPxqingjiatableDao;
import com.xwcloud.cloud.pkxk.Service.IPxqingjiatableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-06
 */
@Service
public class PxqingjiatableServiceImpl extends ServiceImpl<IPxqingjiatableDao, Pxqingjiatable> implements IPxqingjiatableService {
	@Autowired
    IPxqingjiatableDao iPxqingjiatableDao;
}
