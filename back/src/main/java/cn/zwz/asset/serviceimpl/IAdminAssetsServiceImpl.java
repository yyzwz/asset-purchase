package cn.zwz.asset.serviceimpl;

import cn.zwz.asset.mapper.AdminAssetsMapper;
import cn.zwz.asset.entity.AdminAssets;
import cn.zwz.asset.service.IAdminAssetsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资产库存接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAdminAssetsServiceImpl extends ServiceImpl<AdminAssetsMapper, AdminAssets> implements IAdminAssetsService {

}