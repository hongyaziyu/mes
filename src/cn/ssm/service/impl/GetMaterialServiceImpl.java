package cn.ssm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.GetDetailMapper;
import cn.ssm.mapper.GetMaterialMapper;
import cn.ssm.mapper.ProductionPlanMapper;
import cn.ssm.mapper.ProductsBomMapper;
import cn.ssm.mapper.ShopPlanMapper;
import cn.ssm.po.GetDetail;
import cn.ssm.po.GetMaterial;
import cn.ssm.po.ProductsBom;
import cn.ssm.po.ShopPlan;
import cn.ssm.service.GetMaterialService;
import cn.ssm.util.DataSource;
import cn.ssm.vo.Zijian;

@Service
@DataSource("dataSource1")
public class GetMaterialServiceImpl implements GetMaterialService {

	//Mapper注入
	@Autowired
	private GetMaterialMapper getMaterialMapper;
	@Autowired
	private GetDetailMapper getDetailMapper;
	@Autowired
	private ProductsBomMapper productsBomMapper;
	@Autowired
	private ShopPlanMapper shopPlanMapper;
	@Autowired
	private ProductionPlanMapper productionPlanMapper;
	
	//1、查询
	//根据物料号、起始日期、截止日期查询
		//领料审批记录分页查询
	@Override
	public List<GetMaterial> selectGetMaterialByParam(int startPos, int pageSize,String material_no,String start_date,String end_date) {
		
		return getMaterialMapper.selectGetMaterialByParam(startPos, pageSize,material_no, start_date, end_date);
	}

	
	
	//两个表分别添加
	//2.1、GetMaterial表
	@Override
	public void insertGetMaterial(GetMaterial getMaterial){
		 String clientMaterialNo=getMaterial.getClientMaterialNo();
		 String materialNo=getMaterial.getMaterialNo();
		//从ProductionPlan中的取出计划单号，产品名称，产品规格
		 List<ProductsBom>  listproductsBom= new ArrayList<ProductsBom>();
		 listproductsBom=productsBomMapper.selectJCC(clientMaterialNo, materialNo);
			//再将计划单号set到GetMaterial
	if(clientMaterialNo!=null&&!("".equals(clientMaterialNo))&&materialNo!=null&&!("".equals(materialNo))){
					  if(listproductsBom.get(0).getProductName()!=null&&!("".equals(listproductsBom.get(0).getProductName()))){
							 getMaterial.setMaterialName(listproductsBom.get(0).getProductName());
						  }else{
							 getMaterial.setMaterialName("");
						  }
					  if(listproductsBom.get(0).getProductSpec()!=null&&!("".equals(listproductsBom.get(0).getProductSpec()))){
							 getMaterial.setProductSpec(listproductsBom.get(0).getProductSpec());
						  }else{
							 getMaterial.setProductSpec("");
						  }
		
	}else{
		getMaterial.setMaterialName("");
		getMaterial.setProductSpec("");
	}
		//将今天的日期插入到apply_date中
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String applyDate = sf.format(new Date());
				getMaterial.setApplyDate(applyDate);
				getMaterial.setIsApproval(0);
		getMaterialMapper.insertSelective(getMaterial);
	}
	
	@Override
	public void insertGetDetail(List<GetDetail> listGetDetail,Integer getMaterialId) {
	//2.2GetDetail表
		//第二张表GetDetail的循环获得mapper
		   for(int i=0;i<listGetDetail.size();i++){
			   GetDetail getDetail=new GetDetail();
			   getDetail=listGetDetail.get(i);
			   getDetail.setGetMaterialId(getMaterialId);
			   getDetail.setIsReturn(0);
			   getDetailMapper.insertSelective(getDetail);
		   }

	}
	//3、查看
	@Override
	public GetMaterial selectByPrimaryKey(Integer getMaterialId) {
		
		return  getMaterialMapper.selectByPrimaryKey(getMaterialId);
	}

	@Override
	public List<GetDetail> selectByKey(Integer getMaterialId) {
		
		return  getDetailMapper.selectByKey(getMaterialId);
	}


    //4.更新操作(审批)
	@Override
	public int updateByKey(Integer getMaterialId,String approver) {
		
		return getMaterialMapper.updateByKey(getMaterialId,approver);
		
	}

	 //4.更新操作(审批不通过)
	@Override
	public int updatenotByKey(Integer getMaterialId,String approver) {
		
		return getMaterialMapper.updatenotByKey(getMaterialId,approver);
		
	}
		
		
	//5.Ajax检查是否有该客户物料号
	@Override
	//Boolean数值型，类型为True或者False
	public Boolean selectMaterialNoIsNull(String clientMaterialNo) {
		List<ProductsBom> list=new ArrayList<ProductsBom>();
		list=productsBomMapper.selectMaterialNoIsNull(clientMaterialNo);
		if(list.size()<1){
			return false;
		}else{
			return true;
		}
		
	}
	
	//6.Ajax物料号：根据图号得到对应的物料号，并下拉框显示
	@Override
	public String selectMaterialNos(String clientMaterialNo) {
		List<String> list=productsBomMapper.selectMaterialNos(clientMaterialNo);
		String html="<option disabled selected style='display: none;'>--请选择--</option>";
		//循环遍历获得物料号，并将value值返回option前端下拉框
				for(int i=0;i<list.size();i++){
					String materialNo=list.get(i);
					String str=productsBomMapper.selectProductName(materialNo);
					if(str==null){
						 html+="<option value="+list.get(i)+">"+list.get(i)+"("+"未录入信息"+")"+"</option>";
					}else{
						//回显value到jsp页面上
				        html+="<option value="+list.get(i)+">"+list.get(i)+"("+str+")"+"</option>";
					}
					
					
		              }
		return html;
	}


	//7.Ajax(正常流程车间排产下过批次号情况下)根据客户物料号，物料号,批次号查询出子件名称（材料名称），子件编号（材料编号），单位，基础用量，基本用量与计划数量构成理论值参考
	@Override
	public List<Zijian> selectProductsBom(String clientMaterialNo,String materialNo, String batchNo ,String shopName) {
		List<Zijian> listZijian= new ArrayList<Zijian>();
		List<ProductsBom> listProductsBom = new ArrayList<ProductsBom>();
		//从shopplan中的取出计划数量
		ShopPlan shopPlan= new ShopPlan();
		shopPlan=shopPlanMapper.selectShopPlan(clientMaterialNo,materialNo,batchNo,shopName);
			
		if(materialNo!=null&&materialNo!=""&&clientMaterialNo!=null&&clientMaterialNo!=""&&batchNo!=null&&batchNo!=""&&shopName!=null&&shopName!=""&&shopPlan!=null){ 
			listProductsBom = productsBomMapper.selectProductsBom(clientMaterialNo,materialNo);
			if(listProductsBom.size()>0){
				for(int i=0;i<listProductsBom.size();i++){
							Zijian zijian=new Zijian();
							//循环获取listProductsBom插入set到zijian类中
							zijian.setCailiaoMc(listProductsBom.get(i).getZijianName());
							zijian.setCailiaoBh(listProductsBom.get(i).getZijianNo());
							zijian.setUnit(listProductsBom.get(i).getUnit());
			if(listProductsBom.get(i).getRatio1()!=0){
				//基础用量，基本用量与计划数量构成理论值参考(计划数量*基本用量)对计划数量为字符串型，对其强制转换为整型Integer.parseInt，
				//之后结果再向上取整，结果为整型再变为字符串型（方法：整型+空字符串b=字符串型）
							String b=" ";
							String a=(int) Math.ceil(Integer.parseInt(shopPlan.getPlanNum())*listProductsBom.get(i).getRatio1())+b;
							zijian.setReferNum(a);
							//把getDetail类添加到listGetDetail集合中
							listZijian.add(zijian);
						}
			
			
              }
				//没有对应的子件，设置材料名称cailiaoMc为空插进去
			                   }else{
			                	   Zijian zijian=new Zijian();
			                	   zijian.setCailiaoMc(" ");
			                	   zijian.setCailiaoBh(" ");
			                	   zijian.setUnit(" ");
			                	   zijian.setReferNum(" ");
								listZijian.add(zijian);

			                        }	
			
			//物料号为空，设置材料名称cailiaoMc为空插进去
		}else{
		  Zijian zijian=new Zijian();
      	   zijian.setCailiaoMc(" ");
      	   zijian.setCailiaoBh(" ");
      	   zijian.setUnit(" ");
      	   zijian.setReferNum(" ");
		  listZijian.add(zijian);

		}	
		return listZijian;
		
	}
	
	//7.Ajax(原材料外协流程下新加批次号（暂时无批号）情况下)根据客户物料号，物料号查询出子件名称（材料名称），子件编号（材料编号），单位，基础用量，基本用量与计划数量构成理论值参考
		@Override
		public List<Zijian> selectProductsBom1(String clientMaterialNo,String materialNo) {
			List<Zijian> listZijian= new ArrayList<Zijian>();
			List<ProductsBom> listProductsBom = new ArrayList<ProductsBom>();
			if(materialNo!=null&&materialNo!=""&&clientMaterialNo!=null&&clientMaterialNo!=""){ 
				listProductsBom = productsBomMapper.selectProductsBom(clientMaterialNo,materialNo);
				if(listProductsBom.size()>0){
					for(int i=0;i<listProductsBom.size();i++){
								Zijian zijian=new Zijian();
								//循环获取listProductsBom插入set到zijian类中
								zijian.setCailiaoMc(listProductsBom.get(i).getZijianName());
								zijian.setCailiaoBh(listProductsBom.get(i).getZijianNo());
								zijian.setUnit(listProductsBom.get(i).getUnit());
				
								//把getDetail类添加到listGetDetail集合中
								listZijian.add(zijian);
							}
	               }
					//没有对应的子件，设置材料名称cailiaoMc为空插进去
				//物料号为空，设置材料名称cailiaoMc为空插进去
			}else{
				 Zijian zijian=new Zijian();
	      	   zijian.setCailiaoMc(" ");
	      	   zijian.setCailiaoBh(" ");
	      	   zijian.setUnit(" ");
			  listZijian.add(zijian);

			}	
			return listZijian;
			
		}
		
		
		
	 //8.Ajax根据物料号显示产品名称和产品规格
	@Override
	public ProductsBom selectCpmcandCpgg(String materialNo) {
		ProductsBom productsBom=new ProductsBom();
		productsBom=productsBomMapper.selectCpmcandCpgg(materialNo);
		return productsBom;
	}


 //9.Ajax检查该批次号是否已经用过
	//Boolean数值型，类型为True或者False
	@Override
	public Boolean selectBatchNoIsNull(String batchNo) {
		List<GetMaterial> list=new ArrayList<GetMaterial>();
		list=getMaterialMapper.selectBatchNoIsNull(batchNo);
		if(list.size()<1){
			return false;
		}else{
			return true;
		}
	}


	//更新该批次号下的申请领料表(副表-getdetail)
	@Override
	public void updateByGetDetail(List<GetDetail> listGetDetail,Integer getMaterialId) {
		//第二张表GetDetail的循环获得mapper
		   for(int i=0;i<listGetDetail.size();i++){
			   String material_num=listGetDetail.get(i).getMaterialNum();
			   getDetailMapper.updateByGetDetail(material_num, getMaterialId);
		   }
		
	}


	//更新领料表的信息-根据批次号查询主键getMaterialId
	@Override
	public Integer selectBatchNotogetMaterialId(String batchNo) {
		
		return getMaterialMapper.selectBatchNotogetMaterialId(batchNo);
	}


	 
		

}
