package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.pramaTypeVo;
import com.xwcloud.cloud.model.Vo.stuparamtypeVO;
import com.xwcloud.cloud.model.entity.Pxstuparamtypetable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-01-14
 */
public interface IPxstuparamtypetableService extends IService<Pxstuparamtypetable> {
    List<stuparamtypeVO> GetStuparamtypeList(long qiyeID);

    List<pramaTypeVo> getOne(Long qiyeID);

    List<Pxstuparamtypetable> selectstuparamtype(@Param("ew") QueryWrapper queryWrapper);
}
