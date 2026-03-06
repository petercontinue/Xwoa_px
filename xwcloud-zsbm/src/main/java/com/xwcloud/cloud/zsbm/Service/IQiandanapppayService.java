package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Qiandanapppay;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-28
 */
public interface IQiandanapppayService extends IService<Qiandanapppay> {
    Page<HashMap<String, Object>> GetAllQiandanAppPayPages(Page page, QueryWrapper wrapper);
}
