package com.xwcloud.cloud.stu.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.stuIntegralVo;
import com.xwcloud.cloud.model.entity.Pxjifentable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-19
 */
public interface IPxjifentableService extends IService<Pxjifentable> {
	List<stuIntegralVo> ExportIntegral(QueryWrapper queryWrapper);
	List<Pxjifentable> getJF(Long stuID,Long qiyeID);
}
