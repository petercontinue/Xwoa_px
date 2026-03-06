package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstucardtable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-19
 */
public interface IPxstucardtableService extends IService<Pxstucardtable> {
    List<Pxstucardtable> selectstucard(QueryWrapper queryWrapper);
	
}
