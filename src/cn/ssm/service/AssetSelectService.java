package cn.ssm.service;

import java.util.List;
import cn.ssm.po.Asset;

public interface AssetSelectService {

	//添加的部分：根据设备的名字和编号进行查询的servicece接口
	//设备查询分页
	List<Asset> selectAssetByParam(int startPos,int pageSize,String asset_name,String asset_no);
	
	//设备查询
	List<Asset> selectAssetByParam1(String asset_name,String asset_no);

	//添加的部分：更新设备信息的servicece接口
	int insert(Asset record);
	
	//添加的部分：删除设备信息的servicece接口	
	int deleteByPrimaryKey(Integer assetId);
	
	//添加的部分：更新设备信息的servicece接口		
	int updateByPrimaryKeySelective(Asset record);
	
	 Asset selectByPrimaryKey(Integer assetId);

}
