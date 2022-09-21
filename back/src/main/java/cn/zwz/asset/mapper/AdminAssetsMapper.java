package cn.zwz.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.zwz.asset.entity.AdminAssets;

/**
 * 资产库存数据处理层
 * @author 郑为中
 */
public interface AdminAssetsMapper extends BaseMapper<AdminAssets> {
    int getNumber();
}