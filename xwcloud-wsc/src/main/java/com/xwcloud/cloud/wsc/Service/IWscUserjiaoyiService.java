package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.WscUserjiaoyi;


import java.util.HashMap;
import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-02-24
 */
public interface IWscUserjiaoyiService extends IService<WscUserjiaoyi> {
    Page<WscUserjiaoyi> GetwechatChongziPages(Page page, QueryWrapper wrapper);
    List<HashMap<String, Object>> Getchongzhiliushui(QueryWrapper wrapper);
}
