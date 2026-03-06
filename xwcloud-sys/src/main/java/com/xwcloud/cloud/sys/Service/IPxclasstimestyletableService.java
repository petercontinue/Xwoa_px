package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxclasstimestyletable;
import com.xwcloud.cloud.model.entity.Pxkechengtable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-21
 */
public interface IPxclasstimestyletableService extends IService<Pxclasstimestyletable> {
    List<Pxkechengtable> GetkechengByKechengshichang(String classTimeStyleID);
}
