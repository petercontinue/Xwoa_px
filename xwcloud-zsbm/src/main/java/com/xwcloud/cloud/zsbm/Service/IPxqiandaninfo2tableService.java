package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.zaffeiListVo;
import com.xwcloud.cloud.model.entity.Pxqiandaninfo2table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxqiandaninfo2tableService extends IService<Pxqiandaninfo2table> {
    Pxqiandaninfo2table GetQiandanInfoByQiandanID(Long qianInfoTabID, Long qianDanOtherMoneyID);

    Integer deleteQiandanInfo2ByQiandanID(Long qianInfoTabID);

    Page<zaffeiListVo> GetQiandanOtherMoneyPages(Page page, QueryWrapper wrapper);

    List<zaffeiListVo> GetQiandanOtherMoneyList(QueryWrapper wrapper);

    String getzf(@Param("ew") Wrapper wrapper);
}
