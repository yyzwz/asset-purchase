package cn.zwz.asset.controller;

import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.log.LogType;
import cn.zwz.basics.log.SystemLog;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.asset.entity.AdminAssetWare;
import cn.zwz.asset.service.IAdminAssetWareService;
import cn.zwz.data.utils.ZwzNullUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郑为中
 */
@RestController
@Api(tags = "资产仓库档案")
@RequestMapping("/zwz/adminAssetWare")
@Transactional
public class AdminAssetWareController {

    @Autowired
    private IAdminAssetWareService iAdminAssetWareService;

    @SystemLog(about = "查询全部资产计量单位", type = LogType.MORE_MOUDLE,doType = "ASSET-UNIT-01")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有资产仓库档案")
    public Result<List<AdminAssetWare>> getAll(){
        return new ResultUtil<List<AdminAssetWare>>().setData(iAdminAssetWareService.list());
    }

    @SystemLog(about = "查询资产计量单位", type = LogType.MORE_MOUDLE,doType = "ASSET-UNIT-02")
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询资产计量单位")
    public Result<IPage<AdminAssetWare>> getByPage(@ModelAttribute AdminAssetWare ware,@ModelAttribute PageVo page){
        QueryWrapper<AdminAssetWare> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(ware.getName())) {
            qw.like("name",ware.getName());
        }
        if(!ZwzNullUtils.isNull(ware.getAdminName())) {
            qw.like("admin_name",ware.getAdminName());
        }
        return new ResultUtil<IPage<AdminAssetWare>>().setData(iAdminAssetWareService.page(PageUtil.initMpPage(page),qw));
    }

    @SystemLog(about = "新增资产计量单位", type = LogType.MORE_MOUDLE,doType = "ASSET-UNIT-03")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增资产计量单位")
    public Result<AdminAssetWare> insert(AdminAssetWare adminAssetWare){
        if(iAdminAssetWareService.saveOrUpdate(adminAssetWare)){
            return new ResultUtil<AdminAssetWare>().setData(adminAssetWare);
        }
        return ResultUtil.error();
    }

    @SystemLog(about = "编辑资产计量单位", type = LogType.MORE_MOUDLE,doType = "ASSET-UNIT-04")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑资产计量单位")
    public Result<AdminAssetWare> update(AdminAssetWare adminAssetWare){
        if(iAdminAssetWareService.saveOrUpdate(adminAssetWare)){
            return new ResultUtil<AdminAssetWare>().setData(adminAssetWare);
        }
        return ResultUtil.error();
    }

    @SystemLog(about = "删除资产计量单位", type = LogType.MORE_MOUDLE,doType = "ASSET-UNIT-05")
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除资产计量单位")
    public Result<Object> delAllByIds(@RequestParam String[] ids){
        for(String id : ids){
            iAdminAssetWareService.removeById(id);
        }
        return ResultUtil.success();
    }
}
