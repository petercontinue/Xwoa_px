package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.paiketeacherVo;
import com.xwcloud.cloud.model.entity.Pxpaiketeachertable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-16
 */
public interface IPxpaiketeachertableService extends IService<Pxpaiketeachertable> {
	paiketeacherVo getPtID(Long paikeID, Long qiyeID);
	paiketeacherVo getTkTpaike(Long paikeID, Long qiyeID);
	List<Pxpaiketeachertable> getBypaikeID(Long paikeID,Long qiyeID);
}
