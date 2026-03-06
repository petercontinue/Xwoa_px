package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.chongzhiPayListVo;
import com.xwcloud.cloud.model.Vo.chongzhixiangqingVO;
import com.xwcloud.cloud.model.entity.Pxchongzhipaytable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-12
 */
public interface IPxchongzhipaytableService extends IService<Pxchongzhipaytable> {
    public  Page<chongzhiPayListVo> GetUserChongzhiPayListPages(Page page, QueryWrapper wrapper);

    List<chongzhiPayListVo> GetUserChongzhiPayListList(QueryWrapper wrapper);

    Page<chongzhixiangqingVO> GetUserChongzhixiangqingPages(Page page, Long qiyeID, long stuID);
}
