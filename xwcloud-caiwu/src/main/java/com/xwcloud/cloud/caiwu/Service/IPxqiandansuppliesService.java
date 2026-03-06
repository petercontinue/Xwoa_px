package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxqiandansupplies;


import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxqiandansuppliesService extends IService<Pxqiandansupplies> {
    List<Pxqiandansupplies> tuisplist(Wrapper wrapper);

    String getBuysupplies(Wrapper wrapper);
}
