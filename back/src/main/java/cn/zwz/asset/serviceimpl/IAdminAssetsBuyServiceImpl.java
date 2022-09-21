package cn.zwz.asset.serviceimpl;

import cn.zwz.asset.mapper.AdminAssetsBuyMapper;
import cn.zwz.asset.entity.AdminAssetsBuy;
import cn.zwz.asset.service.IAdminAssetsBuyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资产采购接口实现
 * @author 郑为中
 */
@Slf4j
@Service
@Transactional
public class IAdminAssetsBuyServiceImpl extends ServiceImpl<AdminAssetsBuyMapper, AdminAssetsBuy> implements IAdminAssetsBuyService {

}