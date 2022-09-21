package cn.zwz.asset.serviceimpl;

import cn.zwz.asset.mapper.AdminAssetWareMapper;
import cn.zwz.asset.entity.AdminAssetWare;
import cn.zwz.asset.service.IAdminAssetWareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资产仓库档案接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAdminAssetWareServiceImpl extends ServiceImpl<AdminAssetWareMapper, AdminAssetWare> implements IAdminAssetWareService {

}