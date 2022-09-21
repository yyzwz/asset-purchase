package cn.zwz.asset.controller;

import cn.hutool.core.date.DateUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.log.LogType;
import cn.zwz.basics.log.SystemLog;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.service.IDepartmentService;
import cn.zwz.data.service.IUserService;
import cn.zwz.asset.entity.AdminAssets;
import cn.zwz.asset.service.IAdminAssetsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 郑为中
 */
@RestController
@Api(tags = "资产库存")
@RequestMapping("/zwz/adminAssets")
@Transactional
public class AdminAssetsController {

    @Autowired
    private IAdminAssetsService iAdminAssetsService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private IUserService iUserService;

    @SystemLog(about = "查询资产库存", type = LogType.MORE_MOUDLE,doType = "ASSET-STOCK-01")
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询资产库存")
    public Result<IPage<AdminAssets>> getByPage(PageVo page){
        IPage<AdminAssets> data = iAdminAssetsService.page(PageUtil.initMpPage(page));
        return new ResultUtil<IPage<AdminAssets>>().setData(data);
    }

    @SystemLog(about = "新增资产库存", type = LogType.MORE_MOUDLE,doType = "ASSET-STOCK-02")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增资产库存")
    public Result<AdminAssets> insert(AdminAssets adminAssets){
        if(iAdminAssetsService.saveOrUpdate(adminAssets)){
            return new ResultUtil<AdminAssets>().setData(adminAssets);
        }
        return ResultUtil.error();
    }

    @SystemLog(about = "编辑资产库存", type = LogType.MORE_MOUDLE,doType = "ASSET-STOCK-03")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑资产库存")
    public Result<AdminAssets> update(AdminAssets adminAssets){
        if(iAdminAssetsService.saveOrUpdate(adminAssets)){
            return new ResultUtil<AdminAssets>().setData(adminAssets);
        }
        return ResultUtil.error();
    }

    @SystemLog(about = "销毁资产库存", type = LogType.MORE_MOUDLE,doType = "ASSET-STOCK-04")
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "销毁资产库存")
    public Result<Object> delByIds(@RequestParam String[] ids){
        User user = securityUtil.getCurrUser();
        for(String id : ids){
            AdminAssets assets = iAdminAssetsService.getById(id);
            if(assets != null) {
                assets.setStatus(1);
                assets.setDestroyName(user.getNickname());
                assets.setDestroyTime(DateUtil.now());
                iAdminAssetsService.saveOrUpdate(assets);
            }
        }
        return ResultUtil.success();
    }

    @SystemLog(about = "资产出库", type = LogType.MORE_MOUDLE,doType = "ASSET-STOCK-05")
    @RequestMapping(value = "/outWage", method = RequestMethod.POST)
    @ApiOperation(value = "资产出库")
    public Result<Object> outWage(@RequestParam String[] ids,@RequestParam String type,@RequestParam String userId){
        User user = securityUtil.getCurrUser();
        for(String id : ids){
            AdminAssets assets = iAdminAssetsService.getById(id);
            if(assets != null) {
                if(type.equals("按人出库")) {
                    assets.setGiveType("按人出库");
                    assets.setGiveId(userId);
                    assets.setGiveName(iUserService.getById(userId).getNickname());
                } else if(type.equals("按部门出库")) {
                    assets.setGiveType("按部门出库");
                    assets.setGiveId(userId);
                    assets.setGiveName(iUserService.getById(userId).getDepartmentTitle());
                } else {
                    assets.setGiveType("销毁出库");
                    assets.setGiveId("");
                    assets.setGiveName("");
                    assets.setStatus(1);
                }
                assets.setOutFlag(1);
                assets.setOutWork(user.getUsername());
                assets.setOutTime(DateUtil.now());
                iAdminAssetsService.saveOrUpdate(assets);
            }
        }
        return ResultUtil.success();
    }
}
