package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Qiandanshenpi;
import com.xwcloud.cloud.model.Vo.qiandanshenpiVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-04-08
 */
public interface IQiandanshenpiService extends IService<Qiandanshenpi> {
    Page<qiandanshenpiVO> GetAllQiandanshenpiPages(Page page, QueryWrapper wrapper);
}
