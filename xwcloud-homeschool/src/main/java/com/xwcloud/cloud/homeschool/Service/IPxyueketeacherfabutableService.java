package com.xwcloud.cloud.homeschool.Service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xwcloud.cloud.model.entity.Pxyueketeacherfabutable;
import com.xwcloud.cloud.model.Vo.PxyueketeacherfabutableVo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2020-11-04
 */
public interface IPxyueketeacherfabutableService extends IService<Pxyueketeacherfabutable> {
	Page<PxyueketeacherfabutableVo> getPage(Page page,Wrapper wrapper);
    List<PxyueketeacherfabutableVo> getJoinList( Wrapper wrapper);
    List<HashMap<String,String>>getKechengList(Wrapper wrapper);
}
