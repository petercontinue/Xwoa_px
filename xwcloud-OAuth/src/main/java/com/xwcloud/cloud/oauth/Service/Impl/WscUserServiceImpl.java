package com.xwcloud.cloud.oauth.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.WscUser;
import com.xwcloud.cloud.oauth.Dao.IWscUserDao;
import com.xwcloud.cloud.oauth.Service.IWscUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-19
 */
@Service
public class WscUserServiceImpl extends ServiceImpl<IWscUserDao, WscUser> implements IWscUserService {
	
}
