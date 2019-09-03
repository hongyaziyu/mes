package cn.ssm.service;

import java.util.List;

import cn.ssm.po.ProductionPlan;
import cn.ssm.po.ShopPlan;
import cn.ssm.po.Task;
import cn.ssm.vo.ExterAssociation;

public interface ShopPlanService {
	void insertShopPlan(ShopPlan shopPlan, String processSort);
	
	ShopPlan selectByPrimaryKey(Integer planId);
	
	void insertTask(List<Task> listTask, Integer planId);
	
	List<Task> selectTaskByKey(Integer planId);
	
	List<ShopPlan> selectAllShopPlan( );
	
	//1.车间排产未完成记录分页查询	：
	List<ShopPlan> selectShopPlanByParam(int startPos,int pageSize,String planNo, String shopName, String batchNo);
	
	
	//2.车间排产已完成记录分页查询	：
	List<ShopPlan> selectFinishShopPlanByParam(int startPos,int pageSize,String planNo, String shopName, String batchNo);
	
	
	//3.车间排产计划审批操作
	 int updateByKey(Integer planId);
	 
	 
	//4.操作工查询
	List<String> selectPersonNames();
	
	//5.设备名称查询
	List<String> selectAssetNames();
	
	//6.模具名称查询
	List<String> selectMoldNames();
	
	
	
	//7.Ajax
	List<Task> selectTaskByParam(String materialNo, String shopName, String clientMaterialNo);

	Boolean selectMaterialNoIsNull(String clientMaterialNo);

	String selectMaterialNos(String clientMaterialNo);
	
	//8.Ajax-批次号显示
	String selectPicihao(String materialNo);
	
	//9.Ajax(二维码制定表)检查该计划单号（车间排产表）下的所有批次号
    List<String> selectPlanNoUpBatchNo(String planNo);
	
    //10.部分外协单据分页查询
    List<ExterAssociation> selectExterAssociation(int startPos,int pageSize,String material_no,String start_date,String end_date);
   
    //11.查询批次号
	String selectBatchNoByPlanNo(String plan_no);
	
	//12.二维码的excel导出（根据计划单号查询图号，产品名称）
	ProductionPlan selectexcelQR(String planNo);
	
	//13.二维码excel导出-该计划单号下的最大二维码数字
	String selectQRMaxNum(String planNo);
	
	//14.删除部分外协记录
	int deleteExterAssociationexcel(Integer transitionId);
	
	//10.部分外协单据excel查询
    List<ExterAssociation> selectexcelExterAssociation(String material_no,String start_date,String end_date);
}


