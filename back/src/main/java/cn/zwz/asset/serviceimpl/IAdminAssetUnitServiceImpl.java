package cn.zwz.asset.serviceimpl;

import cn.zwz.asset.mapper.AdminAssetUnitMapper;
import cn.zwz.asset.entity.AdminAssetUnit;
import cn.zwz.asset.service.IAdminAssetUnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资产计量单位接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAdminAssetUnitServiceImpl extends ServiceImpl<AdminAssetUnitMapper, AdminAssetUnit> implements IAdminAssetUnitService {

}