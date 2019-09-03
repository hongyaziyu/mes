package cn.ssm.service;

import java.util.List;

import cn.ssm.po.ProductTest;
import cn.ssm.vo.ProductionRecordInquiry;
import cn.ssm.po.TestProcess;


public interface ProductTestService {
	
	//1.吴永-根据客户和产品名称、计划单号、批次号查询ProductTest表的信息
	 List<ProductTest> selectProductTestByParam( int startPos,int pageSize,String client,String materialNo,
			 String start_date,String end_date);
 
    //2.吴永-添加ProductTest表信息(利用1个集合)
		void insertProductTest(List<ProductTest> listProductTest);
	
	//3.吴永-修改ProductTest表信息
	//根据testId查询ProductTest数据用于更新前的单条ProductTest数据的回显
		ProductTest selectBytestId(Integer testId);
	
	//吴永-再更新ProductTest表信息
		void updateProductTestbytestId(ProductTest record);
		
	//4.吴永-根据testId删除ProductTest表数据
	    void deleteProductTest(Integer testId);
	    
	//5.吴永-根据客户物料号和物料号从TestProcess表查检测工序
	    List<ProductTest> selectProductTestByParam(String materialNo,String clientMaterialNo);
	
	//6.吴永-查询ProductTest表的物料号、客户物料号、检测工序和标准值在TestProcess表中是否存在
	    List<TestProcess> selectTestProcessByParam(String materialNo,String clientMaterialNo,
	    		String process,String standardVal);
	    
	//7.吴永-添加TestProcess表的检测工序
	    void insertTestProcess(TestProcess testProcess);
	    
	//8.吴永-根据车间和操作工、图号、物料号进行生产记录查询，
	    List<ProductionRecordInquiry> selectProductionRecordInquiryParam(int startPos,int pageSize,String shop_name,String operator,
				String client_material_no,String material_no); 
	    
	    
	//
	    void  updateByPrimaryAllSelective(String materialNo,String clientMaterialNo,
	    		String process,String standardVal);
}
	
	   

	
	



