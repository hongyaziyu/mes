package cn.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.GetDetailMapper;
import cn.ssm.mapper.GetMaterialMapper;
import cn.ssm.mapper.ProductRecordMapper;
import cn.ssm.mapper.ProductsBomMapper;
import cn.ssm.po.ProductRecord;
import cn.ssm.po.ProductsBom;
import cn.ssm.util.DataSource;
import cn.ssm.vo.Input;
import cn.ssm.vo.InputMaterialAssociation;
import cn.ssm.vo.Output;
import cn.ssm.service.IntputOrOutputRecordService;

@Service
@DataSource("dataSource1")
public class InputOrOutputRecordServiceImpl implements IntputOrOutputRecordService{

	//Mapper注入
		@Autowired
		private GetMaterialMapper getMaterialMapper;
		@Autowired
		private GetDetailMapper getDetailMapper;
		@Autowired
		private ProductRecordMapper productRecordMapper;
		@Autowired
		private ProductsBomMapper productsBomMapper;
	//1、领料记录
	@Override
	public List<Input> selectinputGetMaterial(int startPos, int pageSize,String material_no,
			String start_date, String end_date) {
		
		return getMaterialMapper.selectinputGetMaterial(startPos, pageSize,material_no, start_date, end_date);
	}

	
	//2、退料记录
	//退原材料记录查询分页
	@Override
	public List<Output> selectoutputGetMaterial(int startPos, int pageSize,String material_no,
			String start_date, String end_date) {
		
		return getMaterialMapper.selectoutputGetMaterial(startPos, pageSize,material_no, start_date, end_date);
	}

	
	
	//3.领退料“查看”详情
	//3.1领料详情查看
	@Override
	public List<Input> selectId(Integer getMaterialId,Integer detailId) {
		
		return getMaterialMapper.selectId(getMaterialId,detailId);
	}


	//3.2退料详情查看
	@Override
	public List<Output> selectById(Integer getMaterialId, Integer detailId) {
		
		return getMaterialMapper.selectById(getMaterialId, detailId);
	}

	 //4.领半成品记录分页
	//添加内容：根据物料号、起始日期、截止日期、（隐藏条件是否领料？is_return=0）查询
	@Override
	public List<ProductRecord> selectInputMiddleRecord(int startPos,
			int pageSize, String material_no, String start_date, String end_date) {
		
		return productRecordMapper.selectInputMiddleRecord(startPos, pageSize, material_no, start_date, end_date);
	}

	//5.半成品记录分页
    //添加内容：根据物料号、起始日期、截止日期、（隐藏条件是否领料？is_return=1）查询
	@Override
	public List<ProductRecord> selectOutputMiddleRecord(int startPos,
			int pageSize, String material_no, String start_date, String end_date) {
		
		List<ProductRecord> ListProductRecord =	productRecordMapper.selectOutputMiddleRecord(startPos, pageSize, material_no, start_date, end_date);
		return ListProductRecord;
	
	}

	//6领料查看/退料查看（根据recordId）
	@Override
	public ProductRecord selectMiddleId(Integer recordId) {
		
		return productRecordMapper.selectByPrimaryKey(recordId);
	}

	//7、成品记录============
	@Override
	public List<ProductRecord> selectOutputFullRecord(int startPos,
			int pageSize, String material_no, String start_date, String end_date) {
		return productRecordMapper.selectOutputFullRecord(startPos, pageSize, material_no, start_date, end_date);
	}

	// 8、是否正常
	@Override
	public int updateIsProblem(Integer recordId) {
		
		return productRecordMapper.updateIsProblem(recordId);
	}

	// 9、原材料外协出库单据记录
	@Override
	public List<Input> selectMaterialAssociation(int startPos,
			int pageSize, String material_no, String start_date,
			String end_date) {
		
		return getMaterialMapper.selectMaterialAssociation(startPos, pageSize, material_no, start_date, end_date);
	}

	// 10、原材料外协入库单据记录
	@Override
	public List<InputMaterialAssociation> selectIntputMaterialAssociation(int startPos,
			int pageSize, String material_no, String start_date, String end_date) {
		List<ProductRecord> listProductRecord= productRecordMapper.selectIntputMaterialAssociation(startPos, pageSize, material_no, start_date, end_date);
		List<InputMaterialAssociation> listInputMaterialAssociation=new ArrayList<InputMaterialAssociation>();
		
		for(int i=0;i<listProductRecord.size();i++){
			InputMaterialAssociation inputMaterialAssociation=new InputMaterialAssociation();
			inputMaterialAssociation.setClientMaterialNo(listProductRecord.get(i).getClientMaterialNo());
			inputMaterialAssociation.setMaterialNo(listProductRecord.get(i).getMaterialNo());
			inputMaterialAssociation.setPlanNo(listProductRecord.get(i).getPlanNo());
			inputMaterialAssociation.setAssociationer(listProductRecord.get(i).getProvider());
			//if条件满足则为外协成品入库，不满足则为外协半成品入库
			if("".equals(listProductRecord.get(i).getBatchNo2())||listProductRecord.get(i).getBatchNo2()==null){
				inputMaterialAssociation.setBatchNo(listProductRecord.get(i).getBatchNo1());
			}else{
				inputMaterialAssociation.setBatchNo(listProductRecord.get(i).getBatchNo2());
			}
			
			//产品规格
			ProductsBom productsBom=productsBomMapper.selectCpmcandCpgg(listProductRecord.get(i).getMaterialNo());
			inputMaterialAssociation.setProductSpec(productsBom.getProductSpec());
			
			inputMaterialAssociation.setProductMc(listProductRecord.get(i).getProductMc());
			inputMaterialAssociation.setProductNum(listProductRecord.get(i).getProductNum());
			inputMaterialAssociation.setUnit(listProductRecord.get(i).getUnit());
			inputMaterialAssociation.setTransDate(listProductRecord.get(i).getTransDate());
			inputMaterialAssociation.setWeight(listProductRecord.get(i).getWeight());
			inputMaterialAssociation.setRecordId(listProductRecord.get(i).getRecordId());
			//类添加到集合中
			listInputMaterialAssociation.add(inputMaterialAssociation);
		}
		
		return listInputMaterialAssociation;
	}

	
	

	//11.1原材料T+系统的模板excel导出（除了外协的原材料出库）
	@Override
	public List<Input> selectRowinMaterial(String material_no, String start_date, String end_date){
		List<Input> listrowinMaterial=new ArrayList<Input>();
		listrowinMaterial=getMaterialMapper.selectRowinMaterial(material_no, start_date, end_date);
		return listrowinMaterial;
	}

	//11.2退原材料T+系统模板excel导出（除了外协的原材料出库）
	@Override
	public List<Output> selectRowoutMaterial(String material_no, String start_date, String end_date) {
		List<Output> listrowoutMaterial=new ArrayList<Output>();
		listrowoutMaterial=getMaterialMapper.selectRowoutMaterial(material_no, start_date, end_date);
		return listrowoutMaterial;
	}

	// 12.成品记录T+模板excel导出
	@Override
	public List<ProductRecord> selectexcelOutputFullRecord(String material_no, String start_date, String end_date) {
	
		return productRecordMapper.selectexcelOutputFullRecord(material_no, start_date, end_date);
	}

	//13.删除领料主表
	@Override
	public int deleteByGematerialPrimaryKey(Integer getMaterialId) {
		getMaterialMapper.deleteByPrimaryKey(getMaterialId);
		return 0;
	}

	//14.删除领料副表
	@Override
	public int deleteByGetdetailPrimaryKey(Integer detailId) {
		getDetailMapper.deleteByPrimaryKey(detailId);
		return 0;
	}

	//15.半成品出库excel导出
	@Override
	public List<ProductRecord> selectexcelinputMiddleRecord(String material_no,
			String start_date, String end_date) {
		
		return productRecordMapper.selectexcelinputMiddleRecord(material_no, start_date, end_date);
	}

	//16.半成品入库excel导出
	@Override
	public List<ProductRecord> selectexceloutputMiddleRecord(
			String material_no, String start_date, String end_date) {
		
		return productRecordMapper.selectexceloutputMiddleRecord(material_no, start_date, end_date);
	}

	//17.原材料外协出库excel查询
	@Override
	public List<Input> selectexcelinputGetMaterial(String material_no,
			String start_date, String end_date) {
		
		return getMaterialMapper.selectexcelinputGetMaterial(material_no, start_date, end_date);
	}

	
	//18.原材料外协入库excel查询
		@Override
		public List<InputMaterialAssociation> selectexcelIntputMaterialAssociation(String material_no, String start_date, String end_date) {
			List<ProductRecord> listProductRecord= productRecordMapper.selectexcelIntputMaterialAssociation(material_no, start_date, end_date);
			List<InputMaterialAssociation> listInputMaterialAssociation=new ArrayList<InputMaterialAssociation>();
			
			for(int i=0;i<listProductRecord.size();i++){
				InputMaterialAssociation inputMaterialAssociation=new InputMaterialAssociation();
				inputMaterialAssociation.setClientMaterialNo(listProductRecord.get(i).getClientMaterialNo());
				inputMaterialAssociation.setMaterialNo(listProductRecord.get(i).getMaterialNo());
				inputMaterialAssociation.setPlanNo(listProductRecord.get(i).getPlanNo());
				inputMaterialAssociation.setAssociationer(listProductRecord.get(i).getProvider());
				//if条件满足则为外协成品入库，不满足则为外协半成品入库
				if("".equals(listProductRecord.get(i).getBatchNo2())||listProductRecord.get(i).getBatchNo2()==null){
					inputMaterialAssociation.setBatchNo(listProductRecord.get(i).getBatchNo1());
				}else{
					inputMaterialAssociation.setBatchNo(listProductRecord.get(i).getBatchNo2());
				}
				
				//产品规格
				ProductsBom productsBom=productsBomMapper.selectCpmcandCpgg(listProductRecord.get(i).getMaterialNo());
				inputMaterialAssociation.setProductSpec(productsBom.getProductSpec());
				
				inputMaterialAssociation.setProductMc(listProductRecord.get(i).getProductMc());
				inputMaterialAssociation.setProductNum(listProductRecord.get(i).getProductNum());
				inputMaterialAssociation.setUnit(listProductRecord.get(i).getUnit());
				inputMaterialAssociation.setTransDate(listProductRecord.get(i).getTransDate());
				inputMaterialAssociation.setWeight(listProductRecord.get(i).getWeight());
				inputMaterialAssociation.setRecordId(listProductRecord.get(i).getRecordId());
				//类添加到集合中
				listInputMaterialAssociation.add(inputMaterialAssociation);
			}
			
			return listInputMaterialAssociation;
		}
	
}
