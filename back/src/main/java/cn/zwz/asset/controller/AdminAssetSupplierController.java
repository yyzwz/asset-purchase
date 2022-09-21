package cn.zwz.asset.controller;

import cn.hutool.core.date.DateUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.log.LogType;
import cn.zwz.basics.log.SystemLog;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.asset.entity.AdminAssetSupplier;
import cn.zwz.asset.service.IAdminAssetSupplierService;
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
@Api(tags = "资产供应商")
@RequestMapping("/zwz/adminAssetSupplier")
@Transactional
public class AdminAssetSupplierController {

    @Autowired
    private IAdminAssetSupplierService iAdminAssetSupplierService;

    @SystemLog(about = "查询全部资产供应商", type = LogType.MORE_MOUDLE,doType = "ASSET-SUPPLIER-01")
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部资产供应商")
    public Result<List<AdminAssetSupplier>> getAll(){
        return new ResultUtil<List<AdminAssetSupplier>>().setData(iAdminAssetSupplierService.list());
    }

    @SystemLog(about = "查询资产供应商", type = LogType.MORE_MOUDLE,doType = "ASSET-SUPPLIER-02")
    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询资产供应商")
    public Result<IPage<AdminAssetSupplier>> getByPage(@ModelAttribute AdminAssetSupplier supplier,@ModelAttribute PageVo page){
        QueryWrapper<AdminAssetSupplier> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(supplier.getName())) {
            qw.like("name",supplier.getName());
        }
        return new ResultUtil<IPage<AdminAssetSupplier>>().setData(iAdminAssetSupplierService.page(PageUtil.initMpPage(page),qw));
    }

    @SystemLog(about = "新增资产供应商", type = LogType.MORE_MOUDLE,doType = "ASSET-SUPPLIER-03")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增资产供应商")
    public Result<AdminAssetSupplier> insert(AdminAssetSupplier adminAssetSupplier){
        adminAssetSupplier.setBm("SU-" + (iAdminAssetSupplierService.count() + 1));
        if(iAdminAssetSupplierService.saveOrUpdate(adminAssetSupplier)){
            return new ResultUtil<AdminAssetSupplier>().setData(adminAssetSupplier);
        }
        return ResultUtil.error();
    }

    @SystemLog(about = "编辑资产供应商", type = LogType.MORE_MOUDLE,doType = "ASSET-SUPPLIER-04")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑资产供应商")
    public Result<AdminAssetSupplier> update(AdminAssetSupplier adminAssetSupplier){
        if(iAdminAssetSupplierService.saveOrUpdate(adminAssetSupplier)){
            return new ResultUtil<AdminAssetSupplier>().setData(adminAssetSupplier);
        }
        return ResultUtil.error();
    }

    @SystemLog(about = "删除资产供应商", type = LogType.MORE_MOUDLE,doType = "ASSET-SUPPLIER-05")
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除资产供应商")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iAdminAssetSupplierService.removeById(id);
        }
        return ResultUtil.success();
    }
}
