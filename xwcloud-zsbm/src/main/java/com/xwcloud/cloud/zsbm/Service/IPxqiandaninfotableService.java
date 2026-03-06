package com.xwcloud.cloud.zsbm.Service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.Vo.StuYueInfoVo;
import com.xwcloud.cloud.model.Vo.exportqdInfoVo;
import com.xwcloud.cloud.model.Vo.qianDanInFoVo;
import com.xwcloud.cloud.model.Vo.updateqiandanVO;
import com.xwcloud.cloud.model.entity.Pxqiandaninfotable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-11
 */
public interface IPxqiandaninfotableService extends IService<Pxqiandaninfotable> {
    public Page<qianDanInFoVo> GetQiandanInfoPages(Page page,QueryWrapper wrapper);

    public Page<StuYueInfoVo> GetAllStuYuePages(Page page, QueryWrapper wrapper);


    List<Pxqiandaninfotable> GetStuQianDanList(Long stuID,Long qiyeID);

    List<Pxqiandaninfotable> GetXqOrXfList(Long stuID,Long qiyeID);

    Integer deleteQiandanByStuID(Long stuID,Long qiyeID);

    Pxqiandaninfotable GetXqOrXfinfo(Long stuID,Long qiyeID);

    List<exportqdInfoVo> GetQiandanInfoList(QueryWrapper wrapper);

    List<updateqiandanVO> GetQiandanInfoLits(long qiandanID);

    qianDanInFoVo GetqiandanInfoByqiandanID(Long qiandanID);
}
