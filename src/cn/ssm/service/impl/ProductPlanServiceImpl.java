package cn.ssm.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ssm.mapper.ProductsBomMapper;
import cn.ssm.mapper.ShopProcessMapper;
import cn.ssm.mapper.ShopSortMapper;
import cn.ssm.mapper.TestProcessMapper;
import cn.ssm.po.ProductsBom;
import cn.ssm.po.ShopProcess;
import cn.ssm.po.ShopSort;
import cn.ssm.po.TestProcess;
import cn.ssm.service.ProductPlanService;
import cn.ssm.util.DataSource;

//service
@Service
@DataSource("dataSource1")
public class ProductPlanServiceImpl implements ProductPlanService{

	//Mapper注入
	@Autowired 
    private ProductsBomMapper productsBomMapper;
	
	@Autowired
	private ShopProcessMapper shopProcessMapper;
	
	@Autowired
	private ShopSortMapper shopSortMapper;
	
	@Autowired
	private TestProcessMapper testProcessMapper;
	
	
	
	//1、根据客户，客户物料号查询products的信息
	//产品表查询分页
	@Override
	public List<ProductsBom> selectProductsBomByParam(int startPos,int pageSize,String client_material_no,
			String material_no) {
		
		return productsBomMapper.selectProductsBomByParam(startPos, pageSize,client_material_no,material_no);
	}

	
	
    //2、根据客户物料号和删除四张表的信息
	@Override
	public void deleteProductsBom(String client_material_no,String material_no) {
		 productsBomMapper.deleteProductsBom(client_material_no,material_no);
	}
	
	@Override
	public void deleteShopProcess(String client_material_no,String material_no) {
		 shopProcessMapper.deleteShopProcess(client_material_no,material_no);
	}

	@Override
	public void deleteShopSort(String client_material_no,String material_no) {
		 shopSortMapper.deleteShopSort(client_material_no,material_no);
	}

	@Override
	public void deleteTestProcess(String client_material_no,String material_no) {
		testProcessMapper.deleteTestProcess(client_material_no,material_no);
	}



	
	//3、添加总表信息
	//因为之后的类涉及到公共部分和不确定部分，所以需要一个集合来循环遍历接受mapper
	@Override
	public void insertFour(List<ProductsBom> listProductsBom,
			List<ShopProcess> listShopProcess, List<ShopSort> listShopSort,
			List<TestProcess> listTestProcess) {
		
		//第一张总表products_bom的循环获得mapper
		for(int i=0;i<listProductsBom.size();i++){
			ProductsBom productsBom=new ProductsBom();
			productsBom=listProductsBom.get(i);
			productsBomMapper.insert(productsBom);
		}
		
	   //第二张表shop_process的循环获得mapper
	   for(int i=0;i<listShopProcess.size();i++){
		   ShopProcess shopProcess=new ShopProcess();
		   shopProcess=listShopProcess.get(i);
		   shopProcessMapper.insert(shopProcess);
	   }
		   
	   //第三张表shop_sort的循环获得mapper
	  for(int i=0;i<listShopSort.size();i++){
		  ShopSort shopSort=new ShopSort();
		  shopSort=listShopSort.get(i);
		  shopSortMapper.insert(shopSort);
	  }	 
	  
	    //第四张表test_process的循环获得mapper
	  for(int i=0;i<listTestProcess.size();i++){
		  TestProcess testProcess=new TestProcess();
		  testProcess=listTestProcess.get(i);
		  testProcessMapper.insert(testProcess);
	  }
	}	
	
	
	//7、由于修改不能修改testprocess值添加总表信息(利用3个集合)
	@Override
	public void insertThree(List<ProductsBom> listProductsBom,
			List<ShopProcess> listShopProcess, List<ShopSort> listShopSort) {
		//第一张总表products_bom的循环获得mapper
				for(int i=0;i<listProductsBom.size();i++){
					ProductsBom productsBom=new ProductsBom();
					productsBom=listProductsBom.get(i);
					productsBomMapper.insert(productsBom);
				}
				
			   //第二张表shop_process的循环获得mapper
			   for(int i=0;i<listShopProcess.size();i++){
				   ShopProcess shopProcess=new ShopProcess();
				   shopProcess=listShopProcess.get(i);
				   shopProcessMapper.insert(shopProcess);
			   }
				   
			   //第三张表shop_sort的循环获得mapper
			  for(int i=0;i<listShopSort.size();i++){
				  ShopSort shopSort=new ShopSort();
				  shopSort=listShopSort.get(i);
				  shopSortMapper.insert(shopSort);
			  }	 
	}
	
	  //4、修改信息
	
	//根据客户物料号和物料号查询
	@Override
	public List<ProductsBom> selectByParam1(String client_material_no,
			String material_no) {
		List<ProductsBom> listproduct = new ArrayList<ProductsBom>();
		listproduct= productsBomMapper.selectByParam(client_material_no,material_no);
		return listproduct;
	}
	
	@Override
	public List<ShopProcess> selectByParam2(String client_material_no,
			String material_no) {	
		return shopProcessMapper.selectByParam(client_material_no,material_no);
	}
	
	@Override
	public List<ShopSort> selectByParam3(String client_material_no,
			String material_no) {
		return shopSortMapper.selectByParam(client_material_no,material_no);
	}
	
	@Override
	public LinkedHashSet<String> selectByParam4(String client_material_no,
			String material_no) {
		
		List<TestProcess> listtestprocess = new ArrayList<TestProcess>();
		listtestprocess=testProcessMapper.selectByParam(client_material_no, material_no);
	
		LinkedHashSet<String> hs = new LinkedHashSet<String>();  
		for(int i=0;i<listtestprocess.size();i++){
			if(listtestprocess.get(i).getProcess().contains("/")){
				String process=listtestprocess.get(i).getProcess().substring(0, listtestprocess.get(i).getProcess().indexOf("/"));
				hs.add(process);
			}
			else{
				hs.add(listtestprocess.get(i).getProcess());
			}
		} 
		
		
		return hs;
	}
	
	
	
       //更新（修改）操作
	@Override
	public void updateFour(List<ProductsBom> listProductsBom,
			List<ShopProcess> listShopProcess, List<ShopSort> listShopSort,
			List<TestProcess> listTestProcess) {
		
		//第一张总表products_bom的循环获得mapper
				for(int i=0;i<listProductsBom.size();i++){
					ProductsBom productsBom=new ProductsBom();
					productsBom=listProductsBom.get(i);
					productsBomMapper.updateByPrimaryKey(productsBom);
				}
				
			   //第二张表shop_process的循环获得mapper
			   for(int i=0;i<listShopProcess.size();i++){
				   ShopProcess shopProcess=new ShopProcess();
				   shopProcess=listShopProcess.get(i);
				   shopProcessMapper.updateByPrimaryKey(shopProcess);
			   }
				   
			   //第三张表shop_sort的循环获得mapper
			  for(int i=0;i<listShopSort.size();i++){
				  ShopSort shopSort=new ShopSort();
				  shopSort=listShopSort.get(i);
				  shopSortMapper.updateByPrimaryKey(shopSort);
			  }	 
			  
			    //第四张表test_process的循环获得mapper
			  for(int i=0;i<listTestProcess.size();i++){
				  TestProcess testProcess=new TestProcess();
				  testProcess=listTestProcess.get(i);
				  testProcessMapper.updateByPrimaryKey(testProcess);
			  }
		
	}

//excel插入或者更新判断
	//5\4张表分别插入
	@Override
	public void insertProductsBom(ProductsBom record) {
		
		productsBomMapper.insertSelective(record);
		
	}
	@Override
	public void insertTestProcess(List<TestProcess> listTestProcess) {
		//testProcessMapper.insertSelective(record);
		 for(int i=0;i<listTestProcess.size();i++){
			 TestProcess testProcess=new TestProcess();
			 testProcess=listTestProcess.get(i);
			 testProcessMapper.insertSelective(testProcess);
	     }
		
	}
	@Override
	public void insertShopProcess(List<ShopProcess> listShopProcess) {
		for(int i=0;i<listShopProcess.size();i++){
			ShopProcess shopProcess=new ShopProcess();
			shopProcess=listShopProcess.get(i);
			shopProcessMapper.insertSelective(shopProcess);
		}
		 
		
	}
	@Override
	public void insertShopSort(List<ShopSort> listShopSort) {
	     for(int i=0;i<listShopSort.size();i++){
	    	 ShopSort shopSort=new ShopSort();
	    	 shopSort=listShopSort.get(i);
	    	 shopSortMapper.insertSelective(shopSort);
	     }
	}


	//6\主表更新
	@Override
	public void updateProductsBom(ProductsBom record) {
		productsBomMapper.updateByPrimaryKeySelective(record);
		
	}
	



	//7、excel-ProductsBom表的查询
	@Override
	public List<ProductsBom> selectexcelByParam(String client_material_no,
			String material_no, String zijian_no) {
		
		return productsBomMapper.selectexcelByParam(client_material_no, material_no, zijian_no);
	}


	//8、周计划库存量半成品库存未录入时根据物料号显示名称规格。
	@Override
	public ProductsBom selectCpmcandCpgg(String materialNo) {
		
		return productsBomMapper.selectCpmcandCpgg(materialNo);
	}


	





}


