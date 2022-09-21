package cn.zwz.asset.serviceimpl;

import cn.zwz.asset.mapper.AdminAssetSupplierMapper;
import cn.zwz.asset.entity.AdminAssetSupplier;
import cn.zwz.asset.service.IAdminAssetSupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资产供应商接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAdminAssetSupplierServiceImpl extends ServiceImpl<AdminAssetSupplierMapper, AdminAssetSupplier> implements IAdminAssetSupplierService {

}