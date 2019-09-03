package cn.ssm.service;

import java.util.List;

import cn.ssm.po.MiddleProduct;
import cn.ssm.vo.ProductInventory;
import cn.ssm.vo.SaleOrder;
import cn.ssm.vo.SaleOrderDetail;

public interface TJdbcService {

	//1、订单记录查询
	List<SaleOrder> selectSaleOrder(int startPos,int pageSize,String code,String start_date,String end_date);
	
	//2、1  查看详细信息
	SaleOrder selectOrderetail(Integer id);
	
	//2、2、查看详细信息-具体信息
    List<SaleOrderDetail> selectSaleOrderdetail(Integer id);
    
    //3、Ajax-周计划-检查是否有该订单号
    Boolean selectOrderNoIsNull(String orderNo);
    
    //4、Ajax-周计划-根据订单号查询客户信息
    SaleOrder selectClient(String orderNo);
    
    //5、Ajax-周计划-根据订单号得到图号下拉显示
    String selectClientMaterialNo(String orderNo);
    
    //6、Ajax-周计划-根据订单号、物料号得到具体的产品订单数量
    String selectOrdernum(String orderNo,String materialNo);
    
    //7、根据物料号显示原材料库存信息
    List<ProductInventory> selectInventoryNum(String materialNo);
    
    
    //8.1根据订单号,图号显示成品库存信息
    List<ProductInventory> selectFullInventoryMessage(String orderNo,String clientMaterialNo);
      
    //8.2根据订单号,图号显示原材料库存信息
    List<ProductInventory> selectMaterialInventoryMessage(String orderNo,String clientMaterialNo);
    
    //8.3.1根据订单号,图号显示半成品的所有物料号
    List<String> selectMiddleMaterialNoMessage(String orderNo,String clientMaterialNo);
    
    //8.3.2根据订单号,图号显示半成品的库存信息
    MiddleProduct selectMiddleInventoryMessage(String materialNo);
    
    //9.原材料T+系统的选择模板并选择模板excel导出-查询数据库中该月中原材料的单据数量为单据日期做拼接！
  	String selectRowMaterialNum();
  	
	//10.产成品T+系统的选择模板并选择模板excel导出-查询数据库中该月中原材料的单据数量为单据日期做拼接！
  	String selectFullProductNum();
}
