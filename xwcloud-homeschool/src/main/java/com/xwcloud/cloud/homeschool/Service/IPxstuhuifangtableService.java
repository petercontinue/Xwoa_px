package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxstuHuifang;

import com.xwcloud.cloud.model.Vo.oldstuhuifangVO;
import com.xwcloud.cloud.model.entity.Pxstutable;
import com.xwcloud.cloud.model.entity.Pxstuhuifangtable;
import com.xwcloud.cloud.model.Vo.PxstuhuifangVo;
import com.xwcloud.cloud.model.Vo.PxstutablekechengVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-10
 */
public interface IPxstuhuifangtableService extends IService<Pxstuhuifangtable> {
	Page<PxstuhuifangVo> getStuPage(Page page, Wrapper wrapper);
	Page<PxstuhuifangVo> getStuPageByClass(Page page, Wrapper wrapper);
	Page<oldstuhuifangVO> getPage(Page page, Wrapper wrapper);
	List<PxstuhuifangVo> getJoinList(Wrapper wrapper);
	List<PxstuhuifangVo> getStuJoinList(Wrapper wrapper);
	PxstuHuifang getStu(long stuID,long qiyeID);
	List<PxstutablekechengVo> getStuKechengList(long stuID,long qiyeID);
	int editStu(Pxstutable pxstutable);
}
