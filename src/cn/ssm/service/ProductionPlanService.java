package cn.ssm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.ssm.po.ProcessTransition;
import cn.ssm.po.ProductionPlan;
import cn.ssm.po.ShopDelivery;
import cn.ssm.po.ShopTransition;
import cn.ssm.po.TransitionCipin;
import cn.ssm.vo.WeekPlan;

public interface ProductionPlanService {

	void addProductionPlan(ProductionPlan productionPlan, Integer num,
			HttpServletRequest request, HttpSession session);

	
	//周计划查询-未完成
	List<ProductionPlan> selectProductionPlanByParam( int startPos,int pageSize,String client,
			String clientMaterialNo,String start_date,String end_date);
	
	//周计划查询-已经完成
		List<ProductionPlan> selectFinishProductionPlanByParam( int startPos,int pageSize,String client,
				String clientMaterialNo,String start_date,String end_date);

		
	ProductionPlan selectProductionPlanByPrimaryKey(Integer planId);

	List<ShopDelivery> selectShopDeliveryByKey(Integer planId);

	void addTotalPlan(ProductionPlan productionPlan, HttpSession session);

	//月计划查询-未完成
	List<ProductionPlan> selectTotalPlanByParam(int startPos, int pageSize,
			String client, String clientMaterialNo,String start_date,String end_date);
	
	//月计划查询-已经完成
	List<ProductionPlan> selectFinishTotalPlanByParam(int startPos, int pageSize,
			String client, String clientMaterialNo,String start_date,String end_date);
	
	
	List<ShopDelivery> selectShopDeliveryByParam(String materialNo,
			String clientMaterialNo);
	
	
	//月计划or周计划审批操作
	 int updateByKey(Integer planId);
	

    //计划进度查询
	List<WeekPlan> selectProductionPlanByParam1(String plan_no , String client_material_no, String material_no);

	Integer selectShopDeliveryByParam(Integer planId);

	List<ShopTransition> selectShopTransitionByParam(String plan_no,String client_material_no, String material_no);

	List<ShopTransition> selectShopTransitionByParam1(String plan_no,String client_material_no, String material_no);

	
	//计划单号显示
	String selectJihuadanhao(String clientMaterialNo, String materialNo);
	

	// 客户查询显示
	String selectClient();
	
	//周计划-计划单号编码(查询该月最大计划单号后3位)
	String selectPlanNoCode();
	
	
	//工序记录交接查询分页
	List<ProcessTransition> selectProcessTransitionByParam(int startPos,int pageSize,String provider, String batchNo,String shopName,String start_date, String end_date);
	
	//周计划删除主表功能
	int deleteProductionPlanList(Integer planId);
	
	//周计划删除副表功能
	int deleteScheduleProductionPlanList(Integer planId);

	//工序交接记录删除process_transition（主表）
	int deleteProcessTransition(Integer transitionId);
	
	//工序交接记录删除Transition_cipin表（副表）
	int deleteTransitionCipin(Integer transitionId);
	 
	//工序交接记录excel查询分页
	List<ProcessTransition> selectExcelProcessTransitionByParam(String provider, String batchNo,String shopName,String start_date, String end_date);
	
	//工序交接查看详细记录
	List<TransitionCipin> selectProcessTransitionDetail(Integer transitionId);
	
	

}





