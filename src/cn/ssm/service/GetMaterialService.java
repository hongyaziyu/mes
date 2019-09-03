package cn.ssm.service;

import java.util.List;

import cn.ssm.po.GetDetail;
import cn.ssm.po.GetMaterial;
import cn.ssm.po.ProductsBom;
import cn.ssm.vo.Zijian;


public interface GetMaterialService {

	//根据物料号、起始日期、截止日期查询
	//领料审批记录分页查询
    List<GetMaterial> selectGetMaterialByParam(int startPos,int pageSize,String material_no,String start_date,String end_date);
	
   
	//2、两个表分别添加功能
	//2.1GetMaterial表
    void insertGetMaterial(GetMaterial getMaterial);
    //2.2GetDetai表
    void insertGetDetail(List<GetDetail> listGetDetail,Integer getMaterialId);
    
    
    
	//查看功能（两个表通过内键getMaterialId查询）
	GetMaterial selectByPrimaryKey(Integer getMaterialId);
	
	List<GetDetail> selectByKey(Integer getMaterialId);
	
	//添加功能：更新操作（审批）
    int updateByKey(Integer getMaterialId,String approver);
    
    //添加功能：更新操作（审批不通过）
    int updatenotByKey(Integer getMaterialId,String approver);
    
    //Ajax检查是否有该客户物料号
    Boolean selectMaterialNoIsNull(String clientMaterialNo);
    
    //Ajax根据客户物料号得到物料号，并下拉显示
    String selectMaterialNos(String clientMaterialNo);
    
    
	//Ajax(正常流程车间排产下过批次号情况下)根据客户物料号，物料号，批次号查询出子件名称（材料名称），子件编号（材料编号），单位，基础用量，基本用量与计划数量构成理论值参考
    List<Zijian>selectProductsBom(String clientMaterialNo,String materialNo,String batchNo,String shopName);
    
    //Ajax(外协流程下新加批次号（暂时无批号）情况下)根据客户物料号，物料号查询出子件名称（材料名称），子件编号（材料编号）
    List<Zijian>selectProductsBom1(String clientMaterialNo,String materialNo);
    
    
    //Ajax根据物料号显示产品名称和产品规格
    ProductsBom selectCpmcandCpgg(String materialNo);
    
    //Ajax(申请领料表)检查该批次号是否已经用过
    Boolean selectBatchNoIsNull(String batchNo);
    
    //更新该批次号下的申请领料表(副表-getdetail)
    void updateByGetDetail(List<GetDetail> listGetDetail,Integer getMaterialId);
    
    
    //更新领料表的信息-根据批次号查询主键getMaterialId
    Integer selectBatchNotogetMaterialId(String batchNo);
    
   
    
    
}


