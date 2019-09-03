package cn.ssm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.AssetMapper;
import cn.ssm.mapper.HeightGaugeMapper;
import cn.ssm.mapper.MoldsMapper;
import cn.ssm.mapper.PersonMapper;
import cn.ssm.mapper.ProductionPlanMapper;
import cn.ssm.mapper.ProductsBomMapper;
import cn.ssm.mapper.ShopPlanMapper;
import cn.ssm.mapper.ShopProcessMapper;
import cn.ssm.mapper.ShopTransitionMapper;
import cn.ssm.mapper.SpecialGaugeMapper;
import cn.ssm.mapper.TaskMapper;
import cn.ssm.po.Asset;
import cn.ssm.po.ProductionPlan;
import cn.ssm.po.ProductsBom;
import cn.ssm.po.ShopPlan;
import cn.ssm.po.ShopTransition;
import cn.ssm.po.Task;
import cn.ssm.service.ShopPlanService;
import cn.ssm.util.DataSource;
import cn.ssm.vo.ExterAssociation;
@Service
@DataSource("dataSource1")
public class ShopPlanServiceImpl implements ShopPlanService {
	@Autowired
	private ShopPlanMapper shopPlanMapper;
	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private AssetMapper assetMapper;
	@Autowired
	private MoldsMapper moldsMapper;
	@Autowired
	private ShopProcessMapper shopProcessMapper;
	@Autowired
	private ProductsBomMapper productsBomMapper;
	@Autowired
	private ProductionPlanMapper productionPlanMapper;
	@Autowired
	private HeightGaugeMapper heightGaugeMapper;
	@Autowired 
	private SpecialGaugeMapper specialGaugeMapper;
	@Autowired 
	private ShopTransitionMapper shopTransitionMapper;
	
    //1. Ajax-未完成批次号查询
	@Override
	public String selectPicihao(String materialNo) {
		List<ShopPlan> list = shopPlanMapper.selectPicihao(materialNo);
		String html = "";
		for (int i = 0; i < list.size(); i++) {
			html += "<option value=" + list.get(i).getBatchNo() + ">"
					+ list.get(i).getBatchNo() + "</option>";
		}
		return html;

	}
	@Override
	public void insertShopPlan(ShopPlan shopPlan,String processSort) {
		 String clientMaterialNo=shopPlan.getClientMaterialNo();
		 String materialNo=shopPlan.getMaterialNo();
			//从ProductionPlan中的取出计划单号，产品名称，产品规格
		 List<ProductsBom>  listproductsBom= new ArrayList<ProductsBom>();
		 listproductsBom=productsBomMapper.selectJCC(clientMaterialNo, materialNo);
		if(shopPlan.getPlanId()==null){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String planDate = sf.format(new Date());
			shopPlan.setPlanDate(planDate);	
			shopPlan.setProcessSort(processSort);
			shopPlan.setIsLatest(1);
			shopPlan.setIsNew(1);
			//添加时默认设置为未生产即is_product=0
			shopPlan.setIsProduct(0);
						//再将计划单号set到shopPlan
					if(clientMaterialNo!=null&&!("".equals(clientMaterialNo))&&materialNo!=null&&!("".equals(materialNo))){
						shopPlan.setMaterialName(listproductsBom.get(0).getProductName());
						shopPlan.setProductSpec(listproductsBom.get(0).getProductSpec());
					}else{
						shopPlan.setMaterialName("");
						shopPlan.setProductSpec("");
					}	
			shopPlanMapper.insertSelective(shopPlan);
		}else{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String planDate = sf.format(new Date());
			shopPlan.setPlanDate(planDate);	
			shopPlan.setProcessSort(processSort);
			shopPlan.setIsNew(0);
			shopPlan.setIsLatest(1);
			shopPlan.setIsProduct(0);
			shopPlanMapper.updateByPrimaryKey(shopPlan);
			shopPlan.setIsNew(1);
			shopPlan.setIsProduct(0);
			shopPlan.setPlanId(null);
			//再将计划单号set到shopPlan
			if(clientMaterialNo!=null&&!("".equals(clientMaterialNo))&&materialNo!=null&&!("".equals(materialNo))){
								 if(listproductsBom.get(0).getProductName()!=null&&!("".equals(listproductsBom.get(0).getProductName()))){
									 shopPlan.setMaterialName(listproductsBom.get(0).getProductName());
								  }else{
									  shopPlan.setMaterialName("");
								  }
								  if(listproductsBom.get(0).getProductSpec()!=null&&!("".equals(listproductsBom.get(0).getProductSpec()))){
									  shopPlan.setProductSpec(listproductsBom.get(0).getProductSpec());
									  }else{
										  shopPlan.setProductSpec("");
									  }
				
					}else{
						shopPlan.setMaterialName("");
						shopPlan.setProductSpec("");
					}	
			shopPlanMapper.insertSelective(shopPlan);
			
		}
		
	}

	
	
	//把shopPlan中的主键planId插入到task表中副键planId中
	@Override
	public void insertTask(List<Task> listTask, Integer planId) {
		for(int i=0;i<listTask.size();i++){
			Task task=new Task();
			task=listTask.get(i);
			task.setPlanId(planId);
			taskMapper.insertSelective(task);
		}
	}

	@Override
	public List<ShopPlan> selectAllShopPlan() {
		List<ShopPlan> listShopPlan=shopPlanMapper.selectAllShopPlan();
		return listShopPlan;
	}
	
	//2.车间排产未完成记录分页查询	：
	@Override
	public List<ShopPlan> selectShopPlanByParam(int startPos, int pageSize,String planNo, String shopName, String batchNo) {
		List<ShopPlan> listShopPlan=shopPlanMapper.selectShopPlanByParam(startPos, pageSize, planNo, shopName, batchNo);
		return listShopPlan;
	}
	
	
	//3.车间排产已完成记录分页查询	：
		@Override
		public List<ShopPlan> selectFinishShopPlanByParam(int startPos, int pageSize,String planNo, String shopName, String batchNo) {
			List<ShopPlan> listShopPlan=shopPlanMapper.selectFinishShopPlanByParam(startPos, pageSize, planNo, shopName, batchNo);
			return listShopPlan;
		}
		
		
	//4.车间排产计划最开始显示一行空的
	@Override
	public ShopPlan selectByPrimaryKey(Integer planId) {
		if(planId!=null){
			ShopPlan shopPlan=shopPlanMapper.selectByPrimaryKey(planId);
			return shopPlan;
		}else{
			return null;
		}		
	}

	
	//5.车间排产计划审批
	@Override
	public int updateByKey(Integer planId) {
		
		return shopPlanMapper.updateByKey(planId);
	}

	
	
	@Override
	public List<Task> selectTaskByKey(Integer planId) {
		List<Task> listTask= new ArrayList<Task>();
		if(planId!=null){
			listTask=taskMapper.selectTaskByKey(planId);
			
		}else {
			Task task=new Task();
			task.setProcessName(" ");
			listTask.add(task);
		}
		return listTask;
	}

	
	//6.操作工、设备名称、磨具名称、检具编号查询
	@Override
	public List<String> selectPersonNames() {
		
		return personMapper.selectPersonNames();
	}

	@Override
	public List<String> selectAssetNames() {
	//检具编号也添加到里面
		List<String> listAssetandGauge=new ArrayList<String>();
		List<Asset> listasset=assetMapper.selectAssetNames();
		
		for(int i=0;i<listasset.size();i++){
			String assetName=null;
			if(listasset.get(i).getAssetType()!=null){
				 assetName=listasset.get(i).getAssetName()+listasset.get(i).getAssetType()+"(编号:"+listasset.get(i).getAssetNo()+")";
			}else{
				 assetName=listasset.get(i).getAssetName()+"(编号:"+listasset.get(i).getAssetNo()+")";
			}
			
			listAssetandGauge.add(assetName);
		}
		
		listAssetandGauge.addAll(heightGaugeMapper.selectHeightGaugeNo());
		listAssetandGauge.addAll(specialGaugeMapper.selectSpecialGaugeNo());
		return listAssetandGauge;
	
	}
	@Override
	public List<String> selectMoldNames() {
		
		return moldsMapper.selectMoldNames();
	}
	
	
	
	
	@Override
	public List<Task> selectTaskByParam(String materialNo, String shopName, String clientMaterialNo) {
		List<Task> listTask= new ArrayList<Task>();
		if(materialNo!=null&&materialNo!=""&&shopName!=null&&shopName!=""){
			List<String> process= shopProcessMapper.selectProcess(materialNo, shopName,clientMaterialNo);
			if(process.size()>0){
				for(int i=0;i<process.size();i++){
					Task task=new Task();
					task.setProcessName(process.get(i));
					listTask.add(task);
				}
			}else{
				Task task=new Task();
				task.setProcessName(" ");
				listTask.add(task);
				
			}	
		}else{
			Task task=new Task();
			task.setProcessName(" ");
			listTask.add(task);
			
		}
		return listTask;
	}

	@Override
	//7.Boolean数值型，类型为True或者False
	public Boolean selectMaterialNoIsNull(String clientMaterialNo) {
		List<ProductsBom> list=new ArrayList<ProductsBom>();
		list=productsBomMapper.selectMaterialNoIsNull(clientMaterialNo);
		//有图号则显示返回true，无返回false
		if(list.size()<1){
			return false;
		}else{
			return true;
		}
		
	}

	@Override
	public String selectMaterialNos(String clientMaterialNo) {
		List<String> list=productsBomMapper.selectMaterialNos(clientMaterialNo);
		String html="<option disabled selected style='display: none;'>--请选择--</option>";
		//循环遍历获得物料号，并将value值和name值返回option前端下拉框
				for(int i=0;i<list.size();i++){
					String materialNo=list.get(i);
					String str=productsBomMapper.selectProductName(materialNo);
					
			        html+="<option value="+list.get(i)+">"+list.get(i)+"("+str+")"+"</option>";
		              }
		return html;
	}



	//9.Ajax(二维码制定表)检查该计划单号（车间排产表）下的所有批次号
		//Boolean数值型，类型为True或者False
		@Override
		public List<String> selectPlanNoUpBatchNo(String planNo) {
			List<String> list=new ArrayList<String>();
			 list=shopPlanMapper.selectPlanNoUpBatchNo(planNo);
			 return list;
		}
			
			
			
	//10.部分外协单据分页查询
		@Override
		public List<ExterAssociation> selectExterAssociation(int startPos,
				int pageSize, String material_no, String start_date,
				String end_date) {
			List<ShopTransition> listShopTransition=shopTransitionMapper.selectExterAssociation(startPos, pageSize, material_no, start_date, end_date);
			List<ExterAssociation> listExterAssociation=new ArrayList<ExterAssociation>();
			
			for(int i=0;i<listShopTransition.size();i++){
				ExterAssociation exterAssociation=new ExterAssociation();
				exterAssociation.setTransitionId(listShopTransition.get(i).getTransitionId());
				exterAssociation.setPlanNo(listShopTransition.get(i).getPlanNo());
				exterAssociation.setClientMaterialNo(listShopTransition.get(i).getClientMaterialNo());
				exterAssociation.setMaterialNo(listShopTransition.get(i).getMaterialNo());
				exterAssociation.setBatchNo(listShopTransition.get(i).getBatchNo());
				//产品名称,产品规格（根据物料号查询得到）
				ProductsBom productsBom=productsBomMapper.selectCpmcandCpgg(listShopTransition.get(i).getMaterialNo());
				exterAssociation.setProductName(productsBom.getProductName());
				exterAssociation.setProductSpec(productsBom.getProductSpec());
			
				exterAssociation.setProvider(listShopTransition.get(i).getProvider());
				exterAssociation.setAcceptor(listShopTransition.get(i).getAcceptor());
				exterAssociation.setShop1(listShopTransition.get(i).getShop1());
				exterAssociation.setShop2(listShopTransition.get(i).getShop2());
				exterAssociation.setTranDate(listShopTransition.get(i).getTranDate());
				exterAssociation.setQualifiedNum(listShopTransition.get(i).getQualifiedNum());
				//把类添加入到集合中
				listExterAssociation.add(exterAssociation);
			}
			
			return listExterAssociation;
		}
		
		//11.查询批次号
		@Override
		public String selectBatchNoByPlanNo(String plan_no) {
			
			return	shopPlanMapper.selectBatchNoByPlanNo( plan_no);
			
		}
		
		//12.二维码的excel导出（根据计划单号查询图号，产品名称）
		@Override
		public ProductionPlan selectexcelQR(String planNo) {
			
			return productionPlanMapper.selectexcelQR(planNo);
		}
		
		//13.二维码excel导出-该计划单号下的最大二维码数字
		@Override
		public String selectQRMaxNum(String planNo) {
			
			return shopPlanMapper.selectQRMaxNum(planNo);
		}
		
		//14.删除部分外协记录
		@Override
		public int deleteExterAssociationexcel(Integer transitionId) {
			
			return shopTransitionMapper.deleteByPrimaryKey(transitionId);
		}

	
		//10.部分外协单据分页查询
				@Override
				public List<ExterAssociation> selectexcelExterAssociation(String material_no, String start_date,
						String end_date) {
					List<ShopTransition> listShopTransition=shopTransitionMapper.selectexcelExterAssociation(material_no, start_date, end_date);
					List<ExterAssociation> listExterAssociation=new ArrayList<ExterAssociation>();
					
					for(int i=0;i<listShopTransition.size();i++){
						ExterAssociation exterAssociation=new ExterAssociation();
						exterAssociation.setTransitionId(listShopTransition.get(i).getTransitionId());
						exterAssociation.setPlanNo(listShopTransition.get(i).getPlanNo());
						exterAssociation.setClientMaterialNo(listShopTransition.get(i).getClientMaterialNo());
						exterAssociation.setMaterialNo(listShopTransition.get(i).getMaterialNo());
						exterAssociation.setBatchNo(listShopTransition.get(i).getBatchNo());
						//产品名称,产品规格（根据物料号查询得到）
						ProductsBom productsBom=productsBomMapper.selectCpmcandCpgg(listShopTransition.get(i).getMaterialNo());
						exterAssociation.setProductName(productsBom.getProductName());
						exterAssociation.setProductSpec(productsBom.getProductSpec());
					
						exterAssociation.setProvider(listShopTransition.get(i).getProvider());
						exterAssociation.setAcceptor(listShopTransition.get(i).getAcceptor());
						exterAssociation.setShop1(listShopTransition.get(i).getShop1());
						exterAssociation.setShop2(listShopTransition.get(i).getShop2());
						exterAssociation.setTranDate(listShopTransition.get(i).getTranDate());
						exterAssociation.setQualifiedNum(listShopTransition.get(i).getQualifiedNum());
						//把类添加入到集合中
						listExterAssociation.add(exterAssociation);
					}
					
					return listExterAssociation;
				}
}
