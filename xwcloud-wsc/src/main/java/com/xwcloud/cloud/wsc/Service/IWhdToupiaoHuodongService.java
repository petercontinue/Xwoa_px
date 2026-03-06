package com.xwcloud.cloud.wsc.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.WhdToupiaoHuodongVo;
import com.xwcloud.cloud.model.entity.WhdToupiaoHuodong;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-17
 */
public interface IWhdToupiaoHuodongService extends IService<WhdToupiaoHuodong> {
	Page<WhdToupiaoHuodongVo> getWhdToupiaoHuodongPage(Page page, QueryWrapper wrapper);
	String gettoupaioNum(QueryWrapper wrapper);
	List<HashMap<String, Object>> GetToupiaoHuodongInfoByID(long toupiaohdID);
}
