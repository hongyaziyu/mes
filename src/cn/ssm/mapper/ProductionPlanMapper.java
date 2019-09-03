package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.ProductionPlan;
import cn.ssm.vo.WeekPlan;

public interface ProductionPlanMapper {
	int deleteByPrimaryKey(Integer planId);

	int insert(ProductionPlan record);

	int insertSelective(ProductionPlan record);

	ProductionPlan selectByPrimaryKey(Integer planId);

	int updateByPrimaryKeySelective(ProductionPlan record);

	int updateByPrimaryKey(ProductionPlan record);

	// 周计划查询--未完成
	List<ProductionPlan> selectProductionPlanByParam(
			@Param("startPos") int startPos, @Param("pageSize") int pageSize,
			@Param("client") String client,
			@Param("clientMaterialNo") String clientMaterialNo,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date);

	// 周计划查询--已经完成
	List<ProductionPlan> selectFinishProductionPlanByParam(
			@Param("startPos") int startPos, @Param("pageSize") int pageSize,
			@Param("client") String client,
			@Param("clientMaterialNo") String clientMaterialNo,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date);

	// 周计划查询返回行数totalCount--未完成
	int selectProductionPlanCount(@Param("client") String client,
			@Param("clientMaterialNo") String clientMaterialNo,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date);

	// 周计划查询返回行数totalCount--已经完成
	int selectFinishProductionPlanCount(@Param("client") String client,
			@Param("clientMaterialNo") String clientMaterialNo,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date);

	// 月计划查询-未完成月计划查询
	List<ProductionPlan> selectTotalPlanByParam(
			@Param("startPos") int startPos, @Param("pageSize") int pageSize,
			@Param("client") String client,
			@Param("clientMaterialNo") String clientMaterialNo,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date);

	// 月计划查询-已完成月计划查询
	List<ProductionPlan> selectFinishTotalPlanByParam(
			@Param("startPos") int startPos, @Param("pageSize") int pageSize,
			@Param("client") String client,
			@Param("clientMaterialNo") String clientMaterialNo,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date);

	// 月计划查询返回行数totalCount-未完成月计划查询
	int selectTotalPlanCount(@Param("client") String client,
			@Param("clientMaterialNo") String clientMaterialNo,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date);

	// 月计划查询返回行数totalCount-已完成月计划查询
	int selectFinishTotalPlanCount(@Param("client") String client,
			@Param("clientMaterialNo") String clientMaterialNo,
			@Param("start_date") String start_date,
			@Param("end_date") String end_date);

	// 月计划or周计划审批
	int updateByKey(Integer planId);

	List<ProductionPlan> selectZhouPlanByKey(String orderNo);

	// 计划进度弹窗查询
	List<WeekPlan> selectProductionPlanByParam1(
			@Param(value = "planNo") String plan_no,
			@Param(value = "clientMaterialNo") String client_material_no,
			@Param(value = "materialNo") String material_no);

	// 根据批次号,图号，物料号查询周计划的计划单号，产品名称，产品规格所有信息
	ProductionPlan selectJCC(String clientMaterialNo, String materialNo);

	 //Ajax-自动弹出查询未完成计划单号
	List<ProductionPlan> selectJihuadanhao(String clientMaterialNo,
			String materialNo);
	

	// 客户查询显示
	List<ProductionPlan> selectClient();
	
	//二维码的excel导出（根据计划单号查询图号，产品名称）
	ProductionPlan selectexcelQR(String planNo);
	
	//周计划-计划单号编码
	String selectPlanNoCode();
}