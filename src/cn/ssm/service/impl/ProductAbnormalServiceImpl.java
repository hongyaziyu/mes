package cn.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.DepOpinionMapper;
import cn.ssm.mapper.ProductAbnormalMapper;
import cn.ssm.mapper.ProductionPlanMapper;
import cn.ssm.mapper.ProductsBomMapper;
import cn.ssm.po.DepOpinion;
import cn.ssm.po.ProductAbnormal;
import cn.ssm.po.ProductsBom;
import cn.ssm.service.ProductAbnormalService;
import cn.ssm.util.DataSource;

@Service
@DataSource("dataSource1")
public class ProductAbnormalServiceImpl implements ProductAbnormalService {
	@Autowired
	private ProductAbnormalMapper productAbnormalMapper;
	@Autowired
	private DepOpinionMapper  depOpinionMapper;
	@Autowired
	private ProductionPlanMapper productionPlanMapper;
	@Autowired
	private ProductsBomMapper productsBomMapper;
	
 //	查询
	//产品异常单记录分页
	@Override
	public List<ProductAbnormal> selectProductAbnormalByParam(int startPos,
			int pageSize, String client, String material_no,String start_date, String end_date) {
		return productAbnormalMapper.selectProductAbnormalByParam(startPos, pageSize,client,material_no,start_date,end_date);
	}
	
	
	
	
	
//添加
	@Override
	public void insertProductAbnormal(ProductAbnormal productAbnormal){
		 String clientMaterialNo=productAbnormal.getClientMaterialNo();
		 String materialNo=productAbnormal.getMaterialNo();
		 
		//从ProductionPlan中的取出计划单号，产品名称，产品规格
		 List<ProductsBom>  listproductsBom= new ArrayList<ProductsBom>();
		 listproductsBom=productsBomMapper.selectJCC(clientMaterialNo, materialNo);
			//再将计划单号set到GetMaterial
		if(clientMaterialNo!=null&&!("".equals(clientMaterialNo))&&materialNo!=null&&!("".equals(materialNo))){
				  if(listproductsBom.get(0).getProductName()!=null&&!("".equals(listproductsBom.get(0).getProductName()))){
					  productAbnormal.setProductName(listproductsBom.get(0).getProductName());
					  }else{
						  productAbnormal.setProductName("");
					  }
				  if(listproductsBom.get(0).getProductSpec()!=null&&!("".equals(listproductsBom.get(0).getProductSpec()))){
						productAbnormal.setProductSpecfic(listproductsBom.get(0).getProductSpec());
					  }else{
						  productAbnormal.setProductSpecfic("");
					  }
		}else{
			productAbnormal.setProductName("");
			productAbnormal.setProductSpecfic("");
		}	
		 productAbnormalMapper.insertSelective(productAbnormal);
	}
	@Override
	public void insertAbnormalId(List<DepOpinion> listDepOpinion, Integer abnormalId){
		for(int i=0;i<listDepOpinion.size();i++){
			   DepOpinion depOpinion=new DepOpinion();
			   depOpinion=listDepOpinion.get(i);
			   depOpinion.setAbnormalId(abnormalId);
			   depOpinionMapper.insertSelective(depOpinion);
		   }
	}
	
	
	
	
	
//修改
	@Override
	public ProductAbnormal selectByPrimaryKey(Integer abnormalId){	
		return productAbnormalMapper.selectByPrimaryKey(abnormalId);
	}
	@Override
	public List<DepOpinion> selectByAbnormalId(Integer abnormalId){
		return depOpinionMapper.selectByAbnormalId(abnormalId);
	}
	@Override
	public void deleteByAbnormalId(Integer abnormalId){
		depOpinionMapper.deleteByAbnormalId(abnormalId);
	}
	@Override
	public int updateByPrimaryKeySelective(ProductAbnormal productAbnormal){	 
		return productAbnormalMapper.updateByPrimaryKeySelective(productAbnormal);
	 }
	@Override
	public void updateAbnormalId(List<DepOpinion> listDepOpinion, Integer abnormalId){
		 for(int i=0;i<listDepOpinion.size();i++){
			   DepOpinion depOpinion=new DepOpinion();
			   depOpinion=listDepOpinion.get(i);
			   depOpinion.setAbnormalId(abnormalId);
			   depOpinionMapper.insertSelective(depOpinion);
		   }
		
	}


  
    
}
