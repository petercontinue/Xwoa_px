package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxmenutable;
import com.xwcloud.cloud.model.entity.Pxpowerbuttontable;


import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yinqi
 * @since 2020-10-20
 */

public interface IPxmenutableService extends IService<Pxmenutable> {
    public List<Pxmenutable> getErjimenu(long id);

    public List<Pxmenutable> querymenuList(long staffpostID,long qiyeID);

    public  List<Pxpowerbuttontable> getPowerbuttonList(Long menuID, Long staffPostID, long qiyeID);

}
