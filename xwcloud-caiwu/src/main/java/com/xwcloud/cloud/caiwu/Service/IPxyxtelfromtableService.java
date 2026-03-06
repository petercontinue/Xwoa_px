package com.xwcloud.cloud.caiwu.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxyxtelfromtable;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-12-01
 */

public interface IPxyxtelfromtableService extends IService<Pxyxtelfromtable> {
    Page<HashMap<String,String>> getZhaoshengList(Page<HashMap<String,String>> page, Wrapper wrapper);
    List<HashMap<String,String>> getZhaoshengBili(Wrapper wrapper);
    List<HashMap<String,String>>getLaiyuantujingList(Wrapper wrapper);
}
