package com.xwcloud.cloud.zsbm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxyxtelleveltable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-25
 */
public interface IPxyxtelleveltableService extends IService<Pxyxtelleveltable> {
	List<searchVO> getYxSearchTelLevelList(Long qiyeID);
}
