package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.vo.ProductionRecordInquiry;
import cn.ssm.vo.Salary;
import cn.ssm.vo.TemPrice;
import cn.ssm.po.TrackCard;

public interface TrackCardMapper {
    int deleteByPrimaryKey(Integer cardId);

    int insert(TrackCard record);

    int insertSelective(TrackCard record);

    TrackCard selectByPrimaryKey(Integer cardId);

    int updateByPrimaryKeySelective(TrackCard record);

    int updateByPrimaryKey(TrackCard record);
    
    //添加内容；根据车间，操作工，年份，月份工资详细信息的查询（用到pojo的映射，在pojo中加入）
	//工资单详细记录分页
    List<Salary> SelectByPrimaryDate(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("shop_name")String shop_name, @Param("operator")String operator,@Param("date")String date);
    
	//工资单详细记录分页查询返回行数totalCount
	int SelectByPrimaryDatetotalCount(@Param("shop_name")String shop_name, @Param("operator")String operator,@Param("date")String date);
    
 
	//工资单详细记录分页
    List<Salary> SelectexcelByPrimaryDate(@Param("shop_name")String shop_name, @Param("operator")String operator,@Param("date")String date);
	
	//吴永-添加部分（根据车间和操作工、图号、物料号进行生产记录查询）分页
    List<ProductionRecordInquiry> selectProductionRecordInquiryParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("shop_name")String shop_name,
         @Param("operator")String operator,@Param("client_material_no")String client_material_no,
         @Param("material_no") String material_no);
    
  //吴永-添加部分（根据车间和操作工、图号、物料号进行生产记录查询）分页返回行数totalCount
  	int selectProductionRecordInquiryParamtotalCount(@Param("shop_name")String shop_name,
  	         @Param("operator")String operator,@Param("client_material_no")String client_material_no,
  	         @Param("material_no") String material_no);

    
    
 
    //跟踪单记录分页返回行数totalCount
  	int selectTrackCardByParamtotalCount(@Param("client")String client,@Param("plan_no")String plan_no,@Param("client_material_no")String client_material_no,@Param("material_no")String material_no);

    
   //添加的部分：根据客户、产品、车间查询跟踪单信息
   //跟踪单记录分页
    List<TrackCard> selectTrackCardByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("client")String client,@Param("plan_no")String plan_no,@Param("client_material_no")String client_material_no,@Param("material_no")String material_no);

    
    //4.1临时工价审批记录查询分页
    List<TemPrice> SelectByTemprice(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("shopName")String shopName,@Param("processName")String processName,@Param("start_date")String start_date,@Param("end_date")String end_date);

  	//4.2临时工价审批（0-1）
    int updateapproveTemprice(String batchNo,String temPrice);
    
    //4.3临时工价审批不通过（0-2）
    int updatenotapproveTemprice(String batchNo);
    
    
    //4.4临时工价审批记录查询分页返回行数totalCount
 	int SelectByTempricetotalCount(@Param("shopName")String shopName,@Param("processName")String processName,@Param("start_date")String start_date,@Param("end_date")String end_date);
 	
 	//4.5登录界面-用查询临时工价未审批数量
    int selectTempriceCount();
    
    //模具出入库-查询计划单号和图号
    TrackCard selectByBatchNo(String batchNo);
    
    //查询批次号是否存在
   	int selectBatchNo(String batchNo);
}
