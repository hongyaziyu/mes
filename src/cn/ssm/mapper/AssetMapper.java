package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.Asset;

public interface AssetMapper {
    int deleteByPrimaryKey(Integer assetId);

    int insert(Asset record);

    int insertSelective(Asset record);

    Asset selectByPrimaryKey(Integer assetId);

    int updateByPrimaryKeySelective(Asset record);

    int updateByPrimaryKey(Asset record);
    
    
    List<Asset> selectAssetNames();
    
  //设备查询分页返回行数totalCount
	int selectAssetByParamtotalCount(@Param(value="asset_name")String asset_name,@Param(value="asset_no")String asset_no);
    
   
  //设备查询分页
 	List<Asset> selectAssetByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param(value="asset_name")String asset_name,@Param(value="asset_no")String asset_no);

 	//excel导入设备查询
 	 List<Asset> selectAssetByParam1(String asset_name,String asset_no);

}
