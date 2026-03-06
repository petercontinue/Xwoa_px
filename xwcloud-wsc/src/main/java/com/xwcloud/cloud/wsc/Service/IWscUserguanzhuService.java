package com.xwcloud.cloud.wsc.Service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.xwcloud.cloud.model.Vo.fensiguanzhuVO;
import com.xwcloud.cloud.model.entity.WscUserguanzhu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaowei
 * @since 2021-05-19
 */
public interface IWscUserguanzhuService extends IService<WscUserguanzhu> {
    Page<fensiguanzhuVO> GetAllfensiPages(Page page, long wscuserID);
    Page<fensiguanzhuVO> GetAllGuanzhuPages(Page page,long wscuserID);
}
