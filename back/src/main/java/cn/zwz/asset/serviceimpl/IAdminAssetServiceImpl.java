package cn.zwz.asset.serviceimpl;

import cn.zwz.asset.mapper.AdminAssetMapper;
import cn.zwz.asset.entity.AdminAsset;
import cn.zwz.asset.service.IAdminAssetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资产类型接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAdminAssetServiceImpl extends ServiceImpl<AdminAssetMapper, AdminAsset> implements IAdminAssetService {

}