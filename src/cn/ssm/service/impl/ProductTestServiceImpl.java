package cn.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ssm.mapper.ProductTestMapper;
import cn.ssm.mapper.ProductionPlanMapper;
import cn.ssm.mapper.ProductsBomMapper;
import cn.ssm.mapper.TestProcessMapper;
import cn.ssm.mapper.TrackCardMapper;
import cn.ssm.po.ProductTest;
import cn.ssm.po.ProductsBom;
import cn.ssm.util.DataSource;
import cn.ssm.vo.ProductionRecordInquiry;
import cn.ssm.po.TestProcess;
import cn.ssm.service.ProductTestService;


//service
@Service
@DataSource("dataSource1")
public class ProductTestServiceImpl implements ProductTestService{

	//Mapper注入
	@Autowired 
    private ProductTestMapper productTestMapper;
	@Autowired 
    private TestProcessMapper testProcessMapper;
	@Autowired 
    private TrackCardMapper trackCardMapper;
	@Autowired
	private ProductionPlanMapper productionPlanMapper;
	@Autowired
	private ProductsBomMapper productsBomMapper;
	//1.吴永-根据客户和产品名称、计划单号、批次号查询ProductTest表的信息
		@Override
		public List<ProductTest> selectProductTestByParam(int startPos, int pageSize,String client,
				String materialNo,String start_date,String end_date) {
	    return productTestMapper.selectProductTestByParam(startPos, pageSize,client,materialNo,
	    		start_date,end_date);
		}
	
		
	//2.吴永-添加ProductTest表信息(利用1个集合)
	//因为之后的类涉及到公共部分和不确定部分，所以需要一个集合来循环遍历接受mapper
		@Override
		public void insertProductTest(List<ProductTest> listProductTest)
	    {
			 String clientMaterialNo=listProductTest.get(0).getClientMaterialNo();
			 String materialNo=listProductTest.get(0).getMaterialNo();
			//ProductTest表的循环获得mapper
			for(int i=0;i<listProductTest.size();i++)
			{
				ProductTest productTest=new ProductTest();
				productTest=listProductTest.get(i);
				
				//从ProductionPlan中的取出计划单号，产品名称，产品规格
				 List<ProductsBom>  listproductsBom= new ArrayList<ProductsBom>();
				 listproductsBom=productsBomMapper.selectJCC(clientMaterialNo, materialNo);
						//再将计划单号set到productTest
				if(clientMaterialNo!=null&&!("".equals(clientMaterialNo))&&materialNo!=null&&!("".equals(materialNo))){
						if(listproductsBom.get(0).getProductName()!=null&&!("".equals(listproductsBom.get(0).getProductName()))){
							productTest.setProductName(listproductsBom.get(0).getProductName());
						}
						
						else{
							productTest.setProductName("");
						}
							
				}else{
					productTest.setProductName("");	
				}	
				productTestMapper.insertSelective(productTest);
			}
		 }
      
	//3.吴永-修改ProductTest表信息
	
	//根据testId查询ProductTest数据用于更新前的单条ProductTest数据的回显
	@Override
	public ProductTest selectBytestId(Integer testId) 
	{
		return productTestMapper.selectByPrimaryKey(testId);
	}
	
	//更新（修改）ProductTest表操作
	@Override
	public void updateProductTestbytestId(ProductTest record){
		productTestMapper.updateByPrimaryKeySelective(record);
	}
  
	 
	//4.吴永-根据testId删除ProductTest表数
		@Override
		public void deleteProductTest(Integer testId) {
			 productTestMapper.deleteByPrimaryKey(testId);
		}
		
	//5.吴永-根据客户物料号和物料号从TestProcess表查检测工序
		@Override
		public List<ProductTest> selectProductTestByParam(String materialNo,String clientMaterialNo) {
			List<ProductTest> listProductTest= new ArrayList<ProductTest>();
			if(materialNo!=null&&materialNo!=""&&clientMaterialNo!=null&&clientMaterialNo!=""){
				List<TestProcess> listprocess= testProcessMapper.selectProcess(materialNo,clientMaterialNo);
				if(listprocess!=null){
					
					for(int i=0;i<listprocess.size();i++){
						ProductTest productTest=new ProductTest();
						productTest.setProcessId(String.valueOf(listprocess.get(i).getProcessId()));
						productTest.setProcessName(listprocess.get(i).getProcess());
						productTest.setStandardVal(listprocess.get(i).getStandardVal());
						listProductTest.add(productTest);
					}
				}else{
					ProductTest productTest=new ProductTest();
					productTest.setProcessName(" ");
					listProductTest.add(productTest);
					
				}	
			}else{
				ProductTest productTest=new ProductTest();
				productTest.setProcessName(" ");
				listProductTest.add(productTest);
				
			}
			return listProductTest;
		}
		
        //6.吴永-查询ProductTest表的标准值在TestProcess表中是否存在
		@Override
		public List<TestProcess> selectTestProcessByParam(String materialNo,String clientMaterialNo,
	    		String process,String standardVal){
			return testProcessMapper.selectTestProcessByParam(materialNo,clientMaterialNo,
		    		process,standardVal);
		};
		
		//7.吴永-添加TestProcess表的检测工序
		public  void insertTestProcess(TestProcess testProcess){
			testProcessMapper.insertSelective(testProcess);
	    };
	    
	   ////8.吴永-根据车间和操作工、图号、物料号进行生产记录查询，  
	    @Override
		public List<ProductionRecordInquiry> selectProductionRecordInquiryParam(int startPos,int pageSize,String shop_name,String operator,
				String client_material_no,String material_no) {
	    return trackCardMapper.selectProductionRecordInquiryParam(startPos, pageSize,shop_name, operator,
	    		client_material_no,material_no);
		}
	    @Override
	    public void updateByPrimaryAllSelective(String materialNo,String clientMaterialNo,
	    		String process,String standardVal) {
	     testProcessMapper.updateByPrimaryAllSelective(materialNo,clientMaterialNo,
	    		process,standardVal);
		}
}
				
				 
			  

	




