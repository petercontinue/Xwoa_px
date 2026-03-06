package com.xwcloud.cloud.sys.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxstugradetable;
import com.xwcloud.cloud.model.entity.Pxstutable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-10-21
 */
public interface IPxstugradetableService extends IService<Pxstugradetable> {
    List<Pxstugradetable> GetStuGradeListByqiyeIDAndGrade(Long qiyeID, String stuGradeName);

    List<Pxstutable> GetstuByqiyeIDAndgradeID(Long qiyeID, String stuGradeID);
}
