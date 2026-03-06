package com.xwcloud.cloud.oauth.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xwcloud.cloud.model.OA.OaKehu;
import com.xwcloud.cloud.model.OA.OaQiandan;
import com.xwcloud.cloud.model.OA.Vo.KehuSmsVo;
import com.xwcloud.cloud.model.OA.Vo.KehuVo;
import com.xwcloud.cloud.model.OA.Vo.YiqiandanKehuVo;

import com.xwcloud.cloud.oauth.Dao.IOaKehuDao;
import com.xwcloud.cloud.oauth.Service.IOaKehuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiaowei
 * @since 2021-06-29
 */
@Service
public class OaKehuServiceImpl extends ServiceImpl<IOaKehuDao, OaKehu> implements IOaKehuService {


}
