package cn.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.CipinTypeMapper;
import cn.ssm.po.CipinType;
import cn.ssm.service.CipinTypeService;
import cn.ssm.util.DataSource;
@Service
@DataSource("dataSource1")
public class CipinTypeServiceImpl implements CipinTypeService {

	
	@Autowired
	private CipinTypeMapper cipinTypeMapper;
	
	// 1.(查询)不良品类型查询分页
	@Override
	public List<CipinType> selectCipinByParam(int startPos, int pageSize,
			String shopName, String cipinType) {
		
		return cipinTypeMapper.selectCipinByParam(startPos, pageSize, shopName, cipinType);
	}

	//2.(插入)
	@Override
	public int insertSelective(CipinType record) {
		
		return cipinTypeMapper.insertSelective(record);
	}

	//3.(删除)
	@Override
	public int deleteByPrimaryKey(String shopName,String cipinType) {
	
		return cipinTypeMapper.deleteByType(shopName,cipinType);
	}

	//4.(更新-先删除后插入)
	@Override
	public void insertByType(List<CipinType> listCipinType) {
		
		for(int i=0;i<listCipinType.size();i++){
			CipinType cipinType=new CipinType();
			cipinType=listCipinType.get(i);
			cipinTypeMapper.insertSelective(cipinType);
		}
		
	}
	//5.(更新的查询)
	@Override
	public List<CipinType> selectCipinByType(String shopName, String cipinType) {
		
		return cipinTypeMapper.selectCipinByType(shopName, cipinType);
	}
	

}
