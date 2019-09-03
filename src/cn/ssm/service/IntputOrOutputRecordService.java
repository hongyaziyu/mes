package cn.ssm.service;

import java.util.List;

import cn.ssm.po.ProductRecord;
import cn.ssm.vo.Input;
import cn.ssm.vo.InputMaterialAssociation;
import cn.ssm.vo.Output;

public interface IntputOrOutputRecordService {

	// 1.领料查询
	// 添加内容：根据物料号、起始日期、截止日期、（隐藏条件是否领料？is_return=0）查询
	List<Input> selectinputGetMaterial(int startPos, int pageSize,
			String material_no, String start_date, String end_date);

	// 2.退料查询
	// 退原材料记录查询分页
	// 添加内容：根据物料号、起始日期、截止日期、（隐藏条件是否领料？is_return=1）查询
	List<Output> selectoutputGetMaterial(int startPos, int pageSize,
			String material_no, String start_date, String end_date);

	// 3、查看（领退料详情）功能（两个表通过内键getMaterialId,detailId查询）
	// 3.1领料查看
	List<Input> selectId(Integer getMaterialId, Integer detailId);

	// 3.2退料查看
	List<Output> selectById(Integer getMaterialId, Integer detailId);

	// 4.领半成品查询
	// 领半成品记录分页
	// 添加内容：根据物料号、起始日期、截止日期、（隐藏条件是否领料？is_return=0）查询
	List<ProductRecord> selectInputMiddleRecord(int startPos, int pageSize,
			String material_no, String start_date, String end_date);

	// 5.退半成品查询
	// 退半成品记录分页
	// 添加内容：根据物料号、起始日期、截止日期、（隐藏条件是否领料？is_return=1）查询
	List<ProductRecord> selectOutputMiddleRecord(int startPos, int pageSize,
			String material_no, String start_date, String end_date);

	// 6、查看（领退料详情）功能（两个表通过内键getMaterialId,detailId查询）
	// 领料查看/退料查看
	ProductRecord selectMiddleId(Integer recordId);

	// 7.成品记录
	List<ProductRecord> selectOutputFullRecord(int startPos, int pageSize,
			String material_no, String start_date, String end_date);
	
	// 8、是否正常（问题是否解决）
	int updateIsProblem(Integer recordId);
	

	// 9、原材料外协出库单据记录
	List<Input> selectMaterialAssociation(int startPos, int pageSize,
			String material_no, String start_date, String end_date);
	
	// 10、原材料外协入库单据记录
	List<InputMaterialAssociation> selectIntputMaterialAssociation(int startPos, int pageSize,
			String material_no, String start_date, String end_date);
	
	
	//11.1领原材料T+系统模板excel导出（除了外协的原材料出库）
	List<Input>selectRowinMaterial(String material_no, String start_date, String end_date);
	
	
	//11.2退原材料T+系统模板excel导出（除了外协的原材料出库）
	List<Output>selectRowoutMaterial(String material_no, String start_date, String end_date);
	
	// 12.成品记录T+模板excel导出
	List<ProductRecord> selectexcelOutputFullRecord(String material_no, String start_date, String end_date);
	
	//13.删除领料主表
	 int deleteByGematerialPrimaryKey(Integer getMaterialId);
	 
	//14.删除领料副表
	 int deleteByGetdetailPrimaryKey(Integer detailId);
	
	//15.半成品出库excel导出
	List<ProductRecord> selectexcelinputMiddleRecord(String material_no, String start_date, String end_date);
	
	//16.半成品入库excel导出
	List<ProductRecord> selectexceloutputMiddleRecord(String material_no, String start_date, String end_date);
	
	//17.原材料外协出库excel查询
	List<Input> selectexcelinputGetMaterial(String material_no, String start_date, String end_date);
	
	//18.原材料外协入库excel查询
	List<InputMaterialAssociation> selectexcelIntputMaterialAssociation(String material_no, String start_date, String end_date);
}
