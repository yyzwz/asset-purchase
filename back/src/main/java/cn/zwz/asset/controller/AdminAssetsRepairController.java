package cn.zwz.asset.controller;

import cn.zwz.asset.entity.AdminAssets;
import cn.zwz.asset.service.IAdminAssetService;
import cn.zwz.asset.service.IAdminAssetsService;
import cn.zwz.basics.log.LogType;
import cn.zwz.basics.log.SystemLog;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.asset.entity.AdminAssetsRepair;
import cn.zwz.asset.service.IAdminAssetsRepairService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 郑为中
 */
@Slf4j
@RestController
@Api(tags = "资产报修")
@RequestMapping("/zwz/adminAssetsRepair")
@Transactional
public class AdminAssetsRepairController {

    @Autowired
    private IAdminAssetsRepairService iAdminAssetsRepairService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private IAdminAssetsService iAdminAssetsService;

    @SystemLog(about = "新增资产报修", type = LogType.MORE_MOUDLE,doType = "ASSET-REPAIR-01")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ApiOperation(value = "新增资产报修")
    public Result<Object> add(@RequestParam String assetId){
        AdminAssets aa = iAdminAssetsService.getById(assetId);
        if(aa == null) {
            return ResultUtil.error("资产不存在");
        }
        AdminAssetsRepair aar = new AdminAssetsRepair();
        aar.setAssetId(aa.getId());
        aar.setAssetName(aa.getName());
        aar.setAssetType(aa.getGiveType());
        aar.setAssetWare(aa.getWarehouse());
        User currUser = securityUtil.getCurrUser();
        aar.setRepairId(currUser.getId());
        aar.setRepairName(currUser.getNickname());
        iAdminAssetsRepairService.saveOrUpdate(aar);
        return ResultUtil.success();
    }

    @SystemLog(about = "查询单条资产报修", type = LogType.MORE_MOUDLE,doType = "ASSET-REPAIR-02")
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条资产报修")
    public Result<AdminAssetsRepair> get(@RequestParam String id){
        return new ResultUtil<AdminAssetsRepair>().setData(iAdminAssetsRepairService.getById(id));
    }

    @SystemLog(about = "查询资产报修个数", type = LogType.MORE_MOUDLE,doType = "ASSET-REPAIR-03")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询资产报修个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iAdminAssetsRepairService.count());
    }

    @SystemLog(about = "查询全部资产报修", type = LogType.MORE_MOUDLE,doType = "ASSET-REPAIR-04")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资产报修")
    public Result<List<AdminAssetsRepair>> getAll(){
        return new ResultUtil<List<AdminAssetsRepair>>().setData(iAdminAssetsRepairService.list());
    }

    @SystemLog(about = "查询资产报修", type = LogType.MORE_MOUDLE,doType = "ASSET-REPAIR-05")
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询资产报修")
    public Result<IPage<AdminAssetsRepair>> getByPage(@ModelAttribute AdminAssetsRepair adminAssetsRepair ,@ModelAttribute PageVo page){
        QueryWrapper<AdminAssetsRepair> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(adminAssetsRepair.getAssetName())) {
            qw.like("asset_name",adminAssetsRepair.getAssetName());
        }
        qw.eq("repair_id",securityUtil.getCurrUser().getId());
        IPage<AdminAssetsRepair> data = iAdminAssetsRepairService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<AdminAssetsRepair>>().setData(data);
    }

    @SystemLog(about = "增加资产报修", type = LogType.MORE_MOUDLE,doType = "ASSET-REPAIR-06")
    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增加资产报修")
    public Result<AdminAssetsRepair> saveOrUpdate(AdminAssetsRepair adminAssetsRepair){
        if(iAdminAssetsRepairService.saveOrUpdate(adminAssetsRepair)){
            return new ResultUtil<AdminAssetsRepair>().setData(adminAssetsRepair);
        }
        return ResultUtil.error();
    }

    @SystemLog(about = "新增资产报修", type = LogType.MORE_MOUDLE,doType = "ASSET-REPAIR-07")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增资产报修")
    public Result<AdminAssetsRepair> insert(AdminAssetsRepair adminAssetsRepair){
        iAdminAssetsRepairService.saveOrUpdate(adminAssetsRepair);
        return new ResultUtil<AdminAssetsRepair>().setData(adminAssetsRepair);
    }

    @SystemLog(about = "编辑资产报修", type = LogType.MORE_MOUDLE,doType = "ASSET-REPAIR-08")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑资产报修")
    public Result<AdminAssetsRepair> update(AdminAssetsRepair adminAssetsRepair){
        iAdminAssetsRepairService.saveOrUpdate(adminAssetsRepair);
        return new ResultUtil<AdminAssetsRepair>().setData(adminAssetsRepair);
    }

    @SystemLog(about = "删除资产报修", type = LogType.MORE_MOUDLE,doType = "ASSET-REPAIR-09")
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除资产报修")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iAdminAssetsRepairService.removeById(id);
        }
        return ResultUtil.success();
    }
}
