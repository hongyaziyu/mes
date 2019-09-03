package cn.ssm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ssm.mapper.AssetMapper;
import cn.ssm.po.Asset;
import cn.ssm.service.AssetSelectService;
import cn.ssm.util.DataSource;




//service---@Service
@Service
@DataSource("dataSource1")
public class AssetSelectServiceImpl implements AssetSelectService {

	//Mapper注入
	@Autowired
	private AssetMapper assetMapper;
	
	
	
	
	//添加的部分:多条件查询Param参数（例如：通过客户，产品，车间查询）
	//设备查询分页
	@Override
	public List<Asset> selectAssetByParam(int startPos,int pageSize,String asset_name,String asset_no) {
		
		return assetMapper.selectAssetByParam(startPos, pageSize,asset_name,asset_no);
		
	}
	//excel导入设备查询
	@Override
	public List<Asset> selectAssetByParam1(String asset_name, String asset_no) {
		
		return assetMapper.selectAssetByParam1(asset_name, asset_no);
	}
	
	
    //添加的部分:增加数据信息
	@Override
	public int insert(Asset record){
		int a=0;
		try {
			a=assetMapper.insert(record);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}

	
	//添加的部分:删除数据信息
	@Override
	public int deleteByPrimaryKey(Integer assetId) {
		
		return assetMapper.deleteByPrimaryKey(assetId);
	}

	//添加的部分:修改数据信息
	@Override
	public int updateByPrimaryKeySelective(Asset record) {
		int a=0;
		try {
			a=assetMapper.updateByPrimaryKeySelective(record) ;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}



	@Override
	public Asset selectByPrimaryKey(Integer assetId) {
	
		return assetMapper.selectByPrimaryKey(assetId);
	}







	
}
