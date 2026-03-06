package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstuclasstable;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-14
 */
public interface IPxstuclasstableService extends IService<Pxstuclasstable> {

    Pxstuclasstable GetStuClassByBxIDAndClassID(Long classID, Long buxiID);

    Pxstuclasstable GetStuclassBybuxiID(Long buxiID);

    List<Pxstuclasstable> GetStuclassBybuxiIDlist(Long buxiID);

    int DeleteStuclassbybuxiID(Long buxiID);

    List<Pxstuclasstable> GetAllStuClassList(Long buxiID);

    int DeleteAllStuClassByBuxiID(Long buxiID);

    Pxstuclasstable GetPxstuclassBybxIDAndclassID(Long classID, Long buxiID);

    int DeletestuclassByClassID(Long classID);
}
