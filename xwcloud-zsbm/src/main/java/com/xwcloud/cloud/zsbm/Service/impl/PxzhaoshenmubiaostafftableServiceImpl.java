package com.xwcloud.cloud.zsbm.Service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.entity.Pxzhaoshenmubiaostafftable;
import com.xwcloud.cloud.zsbm.Dao.IPxzhaoshenmubiaostafftableDao;
import com.xwcloud.cloud.zsbm.Service.IPxzhaoshenmubiaostafftableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-26
 */
@Service
public class PxzhaoshenmubiaostafftableServiceImpl extends ServiceImpl<IPxzhaoshenmubiaostafftableDao, Pxzhaoshenmubiaostafftable> implements IPxzhaoshenmubiaostafftableService {
	@Autowired
    IPxzhaoshenmubiaostafftableDao iPxzhaoshenmubiaostafftableDao;
}
