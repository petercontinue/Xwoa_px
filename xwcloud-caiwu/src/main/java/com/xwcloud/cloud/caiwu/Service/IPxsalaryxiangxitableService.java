package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.PxsalaryxiangxitableVo;
import com.xwcloud.cloud.model.entity.Pxsalaryxiangxitable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-25
 */
public interface IPxsalaryxiangxitableService extends IService<Pxsalaryxiangxitable> {
	List<PxsalaryxiangxitableVo> getxiangxiList(Wrapper wrapper);
}
