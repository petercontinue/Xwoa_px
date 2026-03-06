package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxkeshistuteachertable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-23
 */
public interface IPxkeshistuteachertableService extends IService<Pxkeshistuteachertable> {
    List<Pxkeshistuteachertable> selectstuTeakehao(QueryWrapper queryWrapper);
}
