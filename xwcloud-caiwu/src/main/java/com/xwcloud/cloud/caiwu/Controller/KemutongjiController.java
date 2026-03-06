package com.xwcloud.cloud.caiwu.Controller;

import com.xwcloud.cloud.caiwu.IService.ICaiwuService;
import com.xwcloud.cloud.common.AjaxJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shujutongji/kemu")
@Api(tags = "排课统计")
public class KemutongjiController {

    @Autowired
    ICaiwuService iCaiwuService;

    @RequestMapping(value = "/getKemushoufeiPage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目收费统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name="size",value="分页大小",required=false),
            @ApiImplicitParam(name="current",value="页码",required=false),
            @ApiImplicitParam(name="campusID",value="校区ID",required=false),
            @ApiImplicitParam(name="kemuName",value="科目名称",required=false),
            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getKemushoufeiPage(@RequestParam("size") long size,
                                       @RequestParam("current") long current,
                                       @RequestParam("campusID") long campusID,
                                       @RequestParam("kemuName") String kemuName,
                                       @RequestParam("qiyeID") long qiyeID
    ) {
        return iCaiwuService.getKemushoufeiPage(size, current, campusID, kemuName, qiyeID);
    }

    @RequestMapping(value = "/getKemuyufeePage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "科目余额统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name="size",value="分页大小",required=false),
            @ApiImplicitParam(name="current",value="页码",required=false),
            @ApiImplicitParam(name="campusID",value="校区ID",required=false),
            @ApiImplicitParam(name="kemuName",value="科目名称",required=false),
            @ApiImplicitParam(name="qiyeID",value="企业ID",required=true),
    })
    public AjaxJson getKemuyufeePage(@RequestParam("size") long size,
                                       @RequestParam("current") long current,
                                       @RequestParam("campusID") long campusID,
                                       @RequestParam("kemuName") String kemuName,
                                       @RequestParam("qiyeID") long qiyeID
    ) {
        return iCaiwuService.getKemuyufeePage(size, current, campusID, kemuName, qiyeID);
    }
}
