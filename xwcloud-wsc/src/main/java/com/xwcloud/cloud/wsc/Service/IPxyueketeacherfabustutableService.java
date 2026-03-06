package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.joinyuekeInfoVO;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabustutable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-17
 */
public interface IPxyueketeacherfabustutableService extends IService<Pxyueketeacherfabustutable> {
    List<joinyuekeInfoVO> getJoinyuekeStuInfos(long yuekeID, long qiyeID);
}
