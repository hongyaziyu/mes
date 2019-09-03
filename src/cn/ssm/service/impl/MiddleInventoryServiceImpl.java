package cn.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.MiddleProductMapper;
import cn.ssm.po.MiddleProduct;
import cn.ssm.service.MiddleInventoryService;
@Service
public class MiddleInventoryServiceImpl implements MiddleInventoryService {

	@Autowired
	private MiddleProductMapper middleProductMapper;
	
	//1、半成品库存查询
	@Override
	public List<MiddleProduct> selectByMiddleProductint(int startPos,
			int pageSize, String materialNo, String productName) {
		
		return middleProductMapper.selectByMiddleProduct(startPos, pageSize, materialNo, productName);
	}

	//2、添加半成品库存
	@Override
	public int insertSelective(MiddleProduct middleProduct) {
	
		return middleProductMapper.insertSelective(middleProduct);
	}

	//3、删除半成品库存
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return middleProductMapper.deleteByPrimaryKey(id);
	}
	
	//4、1更新-先回显半成品库存
	@Override
	public MiddleProduct selectByPrimaryMiddleProduct(Integer id) {
		
		return middleProductMapper.selectByPrimaryKey(id);
	}
		
	//4、2更新-再更新半成品库存
	@Override
	public int updateByPrimaryKeySelective(MiddleProduct middleProduct) {
		
		return middleProductMapper.updateByPrimaryKeySelective(middleProduct);
	}

	//5、半成品的excel导入查詢是否重複
	@Override
	public List<MiddleProduct> selectByPrimaryMiddleProduct(String materialNo){
		
		return middleProductMapper.selectByPrimaryMiddleProduct(materialNo);
	}
	
	

}
