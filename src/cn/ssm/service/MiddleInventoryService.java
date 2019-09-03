package cn.ssm.service;

import java.util.List;

import cn.ssm.po.MiddleProduct;

public interface MiddleInventoryService {
 
	//1、半成品库存查询
	List<MiddleProduct> selectByMiddleProductint (int startPos,int pageSize,String materialNo,String productName);
	
	//2、添加半成品库存
	int insertSelective(MiddleProduct record);
	
	//3、删除半成品库存
	int deleteByPrimaryKey(Integer id);
	
	//4、1更新-先回显半成品库存
	MiddleProduct selectByPrimaryMiddleProduct(Integer id);
	
	//4、2更新-再更新半成品库存
	int updateByPrimaryKeySelective(MiddleProduct middleProduct);
	
	//5、半成品的excel导入查詢是否重複
	List<MiddleProduct> selectByPrimaryMiddleProduct(String materialNo);
}
