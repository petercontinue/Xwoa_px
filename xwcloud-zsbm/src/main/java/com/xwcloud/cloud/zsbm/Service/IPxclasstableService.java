package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.searchVO;
import com.xwcloud.cloud.model.entity.Pxclasstable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-13
 */
public interface IPxclasstableService extends IService<Pxclasstable> {

    List<Pxclasstable> GetClassByClassName(String className);

    Pxclasstable GetClassByClassNameOne(String className);

    List<searchVO> GetAllClassInfoList(long campusID, long qiyeID);

    List<Pxclasstable> GetClassByClassnameList(String className);

}
