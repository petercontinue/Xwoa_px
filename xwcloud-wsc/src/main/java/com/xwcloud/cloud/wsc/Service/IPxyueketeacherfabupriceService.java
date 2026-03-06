package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabuprice;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-17
 */
public interface IPxyueketeacherfabupriceService extends IService<Pxyueketeacherfabuprice> {
    List<Pxyueketeacherfabuprice> GetAllYuekepriceByyuekeTeacherId(long yuekeID, long qiyeID);
}
