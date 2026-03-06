package com.xwcloud.cloud.pkxk.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.pxkechengcontentVO;
import com.xwcloud.cloud.model.entity.Pxkechengcontenttable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-26
 */
public interface IPxkechengcontenttableService extends IService<Pxkechengcontenttable> {
    List<pxkechengcontentVO> GetKechengContentList(long kechengID);
}
