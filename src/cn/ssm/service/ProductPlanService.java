package cn.ssm.service;

import java.util.LinkedHashSet;
import java.util.List;

import cn.ssm.po.ProductsBom;
import cn.ssm.po.ShopProcess;
import cn.ssm.po.ShopSort;
import cn.ssm.po.TestProcess;

public interface ProductPlanService {
	
	//1、根据客户和物料号查询信息
	//产品表查询分页
	 List<ProductsBom> selectProductsBomByParam(int startPos,int pageSize,String client_material_no,String material_no);

	 
	//2、根据客户物料号删除
	void deleteProductsBom(String client_material_no,String material_no);
	   
	void deleteShopProcess(String client_material_no,String material_no);
	   
	void deleteShopSort(String client_material_no,String material_no);
	   
	void deleteTestProcess(String client_material_no,String material_no);
	  
	
	//3、添加总表信息(利用四个集合)
	void insertFour(List<ProductsBom> listProductsBom,List<ShopProcess> listShopProcess,List<ShopSort> listShopSort,List<TestProcess> listTestProcess);
	
	
	//4、修改总表信息
	//先查询
	List<ProductsBom> selectByParam1(String client_material_no,String material_no);
	
	List<ShopProcess> selectByParam2(String client_material_no,String material_no);
	
	List<ShopSort> selectByParam3(String client_material_no,String material_no);
	
	LinkedHashSet<String> selectByParam4(String client_material_no,String material_no);
	
	//再更新
	void updateFour(List<ProductsBom> listProductsBom,List<ShopProcess> listShopProcess,List<ShopSort> listShopSort,List<TestProcess> listTestProcess);
	
	
	
	
	//以下：excel判断数据库中有数据则更新，没有则插入
	//ProductsBom，ShopProcess表的查询
	List<ProductsBom> selectexcelByParam(String client_material_no,String material_no,String zijian_no);
	
	//5\4张表分别插入
	void insertProductsBom(ProductsBom record);
	void insertTestProcess(List<TestProcess> listTestProcess);
	void insertShopProcess(List<ShopProcess> listShopProcess);
	void insertShopSort(List<ShopSort> listShopSort);
	
	//6\4张表分别更新
	void updateProductsBom(ProductsBom record);
	
	//7、由于修改不能修改testprocess值添加总表信息(利用3个集合)
	void insertThree(List<ProductsBom> listProductsBom,List<ShopProcess> listShopProcess,List<ShopSort> listShopSort);
	
	//8、周计划库存量半成品库存未录入时根据物料号显示名称规格。
	ProductsBom selectCpmcandCpgg(String materialNo);
	
	
	
}



