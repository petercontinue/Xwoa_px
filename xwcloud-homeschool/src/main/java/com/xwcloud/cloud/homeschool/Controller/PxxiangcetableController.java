package com.xwcloud.cloud.homeschool.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xwcloud.cloud.common.AjaxJson;
import com.xwcloud.cloud.homeschool.Service.IPxxiangceimagetableService;
import com.xwcloud.cloud.homeschool.Service.IPxxiangcetableService;
import com.xwcloud.cloud.model.Sso.LoginUser;

import com.xwcloud.cloud.model.Vo.PxxiangcetableVo;
import com.xwcloud.cloud.model.entity.Pxxiangceimagetable;
import com.xwcloud.cloud.model.entity.Pxxiangcetable;
import com.xwcloud.cloud.overall.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/homeschool/pxxiangcetable")
@Api(tags = "电子相册")
public class PxxiangcetableController {

    @Autowired
    IPxxiangcetableService iPxxiangcetableService;
    @Autowired
    IPxxiangceimagetableService iPxxiangceimagetableService;

    @Autowired
    LogUtils logUtils;

    //region 电子相册主页

    @RequestMapping(value = "/getXiangcePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取电子相册分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "分页大小", required = true),
            @ApiImplicitParam(name = "current", value = "页码", required = true),
            @ApiImplicitParam(name = "type", value = "null,1.学员相册   2.校区相册，3班级相册，....", required = false),
            @ApiImplicitParam(name = "title", value = "相册标题", required = false)
    })
    public AjaxJson getXiangcePage(
            HttpServletRequest request,
            @RequestParam(required = false, defaultValue = "10") long size,
            @RequestParam(required = false, defaultValue = "1") long current,
            @RequestParam(required = false, defaultValue = "0") int type,
            @RequestParam(required = false) String title
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Page<PxxiangcetableVo> page = new Page<>(current, size);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("xiangce.qiyeID", loginUser.getQiyeID());
        if (type > 0) {
            queryWrapper.eq("xiangce.type", type);
        }
        if (StringUtils.isNotBlank(title)) {
            queryWrapper.like("xiangce.title", title);
        }
        page = iPxxiangcetableService.getPage(page, queryWrapper);
        ajaxJson.setObj(page);
        return ajaxJson;
    }

    @RequestMapping(value = "/getXiangce", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取电子相册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "相册ID", required = true)
    })
    public AjaxJson getXiangce(HttpServletRequest request, String ID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        queryWrapper.eq("id", ID);
        Pxxiangcetable pxxiangcetable = iPxxiangcetableService.getById(queryWrapper);
        ajaxJson.setObj(pxxiangcetable);
        return ajaxJson;
    }

    @RequestMapping(value = "/addXiangce", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "新建电子相册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = false),
            @ApiImplicitParam(name = "title", value = "相册标题", required = true),
            @ApiImplicitParam(name = "miaoshu", value = "相册描述", required = false),
            @ApiImplicitParam(name = "typeparmID", value = "type=2时，这里存的校区ID，type=3时，这里存班级ID", required = false),
            @ApiImplicitParam(name = "type", value = "null,1.学员相册   2.校区相册，3班级相册，....", required = false)
    })
    public AjaxJson addXiangce(HttpServletRequest request,
                               Pxxiangcetable pxxiangcetable
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        pxxiangcetable.setAddStaffID(loginUser.getStaffID());
        pxxiangcetable.setAddTime(new Date());
        pxxiangcetable.setQiyeID(loginUser.getQiyeID());
        ajaxJson.setSuccess(iPxxiangcetableService.save(pxxiangcetable));
        return ajaxJson;
    }

    @RequestMapping(value = "/editXiangce", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改电子相册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "相册ID", required = true),
            @ApiImplicitParam(name = "stuID", value = "学员ID", required = true),
            @ApiImplicitParam(name = "title", value = "相册标题", required = true),
            @ApiImplicitParam(name = "miaoshu", value = "相册描述", required = false),
            @ApiImplicitParam(name = "addStaffID", value = "添加人", required = false),
            @ApiImplicitParam(name = "addTime", value = "添加时间", required = false),
            @ApiImplicitParam(name = "typeparmID", value = "type=2时，这里存的校区ID，type=3时，这里存班级ID", required = false),
            @ApiImplicitParam(name = "type", value = "null,1.学员相册   2.校区相册，3班级相册，....", required = false),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = false),
    })
    public AjaxJson editXiangce(HttpServletRequest request,
                                long id,long stuID,String title,String miaoshu,long typeparmID,int type
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxxiangcetable xiangce=iPxxiangcetableService.getById(id);
        xiangce.setMiaoshu(miaoshu);
        xiangce.setTitle(title);
        xiangce.setType(type);
        xiangce.setTypeparmID(typeparmID);
        xiangce.setStuID(stuID);
        ajaxJson.setSuccess(iPxxiangcetableService.updateById(xiangce));

        return ajaxJson;
    }

    @RequestMapping(value = "/delXiangce", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除电子相册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "IDs", value = "电子相册ID", required = true),
    })
    public AjaxJson delXiangce(HttpServletRequest request,
                               long ID
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("xiangceid", ID);
        iPxxiangceimagetableService.remove(queryWrapper);
        ajaxJson.setSuccess(iPxxiangcetableService.removeById(ID));
        return ajaxJson;
    }
    //endregion

    //region 电子相册详细
    @RequestMapping(value = "/GetXiangceInfo", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据ID查询相册信息")
    public AjaxJson GetXiangceInfo(long xiangceid) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setObj(iPxxiangcetableService.getById(xiangceid));
        return ajaxJson;
    }

    @RequestMapping(value = "/getXiangceImagePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取电子相册分页信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "xiangceid", value = "相册ID", required = true)
    })
    public AjaxJson getXiangceImagePage(HttpServletRequest request, String xiangceid
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("xiangceid", xiangceid);
        queryWrapper.eq("qiyeID", loginUser.getQiyeID());
        ajaxJson.setObj(iPxxiangceimagetableService.list(queryWrapper));
        return ajaxJson;
    }

    @RequestMapping(value = "/addXiangceImage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加相册相片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "xiangceid", value = "相册ID", required = true),
            @ApiImplicitParam(name = "image", value = "图片路径", required = true),
            @ApiImplicitParam(name = "miaoshu", value = "图片描述", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson addXiangceImage(HttpServletRequest request,long xiangceid,String image,String miaoshu) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        Pxxiangceimagetable pxxiangceimagetable=new Pxxiangceimagetable();
        pxxiangceimagetable.setXiangceid(xiangceid);
        pxxiangceimagetable.setAddTime(new Date());
        pxxiangceimagetable.setAddStaffID(loginUser.getStaffID());
        pxxiangceimagetable.setMiaoshu(miaoshu);
        pxxiangceimagetable.setImage(image);
        pxxiangceimagetable.setQiyeID(loginUser.getQiyeID());
        ajaxJson.setSuccess(iPxxiangceimagetableService.save(pxxiangceimagetable));

        return ajaxJson;
    }

    @RequestMapping(value = "/editXiangceImage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改相册相片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "相册图片ID", required = true),
            @ApiImplicitParam(name = "xiangceid", value = "相册ID", required = true),
            @ApiImplicitParam(name = "image", value = "图片路径", required = true),
            @ApiImplicitParam(name = "miaoshu", value = "图片描述", required = true),
            @ApiImplicitParam(name = "addTime", value = "上传时间", required = true),
            @ApiImplicitParam(name = "addStaffID", value = "上传人", required = true),
            @ApiImplicitParam(name = "qiyeID", value = "企业ID", required = true),
    })
    public AjaxJson editXiangceImage(HttpServletRequest request,
                                     Pxxiangceimagetable pxxiangceimagetable
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setSuccess(iPxxiangceimagetableService.updateById(pxxiangceimagetable));

        return ajaxJson;
    }

    @RequestMapping(value = "/delXiangceImage", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "删除相册相片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "相册相片ID", required = true),
    })
    public AjaxJson delXiangceImage(HttpServletRequest request,
                                    String id
    ) {
        AjaxJson ajaxJson = new AjaxJson();
        LoginUser loginUser = (LoginUser) request.getAttribute("loginUser");
        ajaxJson.setSuccess(iPxxiangceimagetableService.removeById(id));
        return ajaxJson;
    }
    //endregion
}
