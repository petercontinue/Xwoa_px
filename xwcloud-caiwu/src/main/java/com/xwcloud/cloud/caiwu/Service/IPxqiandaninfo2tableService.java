package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxqiandaninfo2table;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-24
 */
public interface IPxqiandaninfo2tableService extends IService<Pxqiandaninfo2table> {

    List<Pxqiandaninfo2table> tuiallzf(Wrapper wrapper);
}
