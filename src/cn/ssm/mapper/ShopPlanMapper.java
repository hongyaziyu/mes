package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.ShopPlan;

public interface ShopPlanMapper {
    int deleteByPrimaryKey(Integer planId);

    int insert(ShopPlan record);

    int insertSelective(ShopPlan record);

    ShopPlan selectByPrimaryKey(Integer planId);

    int updateByPrimaryKeySelective(ShopPlan record);

    int updateByPrimaryKey(ShopPlan record);
    
    List<ShopPlan> selectAllShopPlan();

	// 车间排产记录未完成分页查询查询返回行数totalCount:

	int selectShopPlanByParamtotalCount(@Param("planNo") String planNo,
			@Param("shopName") String shopName, @Param("batchNo") String batchNo);

	
	// 车间排产记录已完成分页查询查询返回行数totalCount:

		int selectFinishShopPlanByParamtotalCount(@Param("planNo") String planNo,
				@Param("shopName") String shopName, @Param("batchNo") String batchNo);
		
	// 车间排产未完成记录分页查询 ：
	List<ShopPlan> selectShopPlanByParam(@Param("startPos") int startPos,
			@Param("pageSize") int pageSize, @Param("planNo") String planNo,
			@Param("shopName") String shopName, @Param("batchNo") String batchNo);

	
	// 车间排产已完成记录分页查询 ：
	List<ShopPlan> selectFinishShopPlanByParam(@Param("startPos") int startPos,
				@Param("pageSize") int pageSize, @Param("planNo") String planNo,
				@Param("shopName") String shopName, @Param("batchNo") String batchNo);
		
    //车间排产计划审批（0-1）
	int updateByKey(Integer planId);
		
	// 根据批次号查询计划数量
	ShopPlan selectShopPlan(String clientMaterialNo, String materialNo,
			String batchNo, String shopName);

	// Ajax(跟踪单界面)-根据批次号查询车间排产表中显示计划数量
	List<ShopPlan> selectNumber(String batchNo);

	// Ajax(二维码制定表)检查该计划单号下(车间排产表中)所有的批次号
    List<String> selectPlanNoUpBatchNo(String planNo);
    
    
    //Ajax-自动弹出查询未完成批次号
 	List<ShopPlan> selectPicihao(String materialNo);
 	
 	//查询批次号
	String selectBatchNoByPlanNo(String plan_no);
	
	//二维码excel导出-该计划单号下的最大二维码数字
	String selectQRMaxNum(String planNo);
}