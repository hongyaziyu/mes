package cn.ssm.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.CipinMapper;
import cn.ssm.mapper.ProductionPlanMapper;
import cn.ssm.mapper.ProductsBomMapper;
import cn.ssm.mapper.ShopPlanMapper;
import cn.ssm.mapper.TrackCardMapper;
import cn.ssm.mapper.WorkCardMapper;
import cn.ssm.po.Cipin;
import cn.ssm.po.ProductsBom;
import cn.ssm.po.ShopPlan;
import cn.ssm.po.TrackCard;
import cn.ssm.po.WorkCard;
import cn.ssm.service.TrackCardSelectService;
import cn.ssm.util.DataSource;


@Service
@DataSource("dataSource1")
public class TrackCardSelectServiceImpl implements TrackCardSelectService {
	@Autowired
	private  TrackCardMapper trackCardMapper;
	@Autowired
	private WorkCardMapper workCardMapper;
	@Autowired
	private CipinMapper cipinMapper;
	@Autowired
	private ProductionPlanMapper productionPlanMapper;
	@Autowired
	private ShopPlanMapper shopPlanMapper;
	@Autowired
	private ProductsBomMapper productsBomMapper;
	
	//跟踪单四个条件查询
	//跟踪单记录分页
	@Override
	public List<TrackCard> selectTrackCardByParam(int startPos, int pageSize,String client,String plan_no,String client_material_no,String material_no) {
		
		return trackCardMapper.selectTrackCardByParam(startPos, pageSize,client,plan_no,client_material_no, material_no);
		
	}
	
	
	
    //添加跟踪单一表的信息
	@Override
	public void insertTrackCard(TrackCard trackCard) {
		 String clientMaterialNo=trackCard.getClientMaterialNo();
		 String materialNo=trackCard.getMaterialNo();
		
		//从ProductionPlan中的取出计划单号，产品名称，产品规格
		 List<ProductsBom>  listproductsBom= new ArrayList<ProductsBom>();
		 listproductsBom=productsBomMapper.selectJCC(clientMaterialNo, materialNo);
			
			//再将计划单号set到GetMaterial
		if(clientMaterialNo!=null&&!("".equals(clientMaterialNo))&&materialNo!=null&&!("".equals(materialNo))){
			  		  if(listproductsBom.get(0).getProductName()!=null&&!("".equals(listproductsBom.get(0).getProductName()))){
						  trackCard.setMaterialName(listproductsBom.get(0).getProductName());
						  }else{
								trackCard.setMaterialName("");
						  }
					  if(listproductsBom.get(0).getProductSpec()!=null&&!("".equals(listproductsBom.get(0).getProductSpec()))){
						  trackCard.setProductSpec(listproductsBom.get(0).getProductSpec());
						  }else{
							  trackCard.setProductSpec("");
						  }
		}else{
			trackCard.setMaterialName("");
			trackCard.setProductSpec("");
		}
	
		trackCardMapper.insertSelective(trackCard);
		
	}
	

	@Override
	public TrackCard selectByPrimaryKey(Integer cardId) {
		
		return trackCardMapper.selectByPrimaryKey(cardId);
	}



	@Override
	public List<WorkCard> SelectBycardId(Integer cardId) {
		
		return workCardMapper.SelectBycardId(cardId);
	}



	@Override
	public int updateByPrimaryKey(TrackCard trackCard) {
		
		return trackCardMapper.updateByPrimaryKeySelective(trackCard);
	}


   //循环更新WorkCard表的信息
	@Override
	public void updateByTrackId(List<WorkCard> listWorkCard) {
		//第一张总表products_bom的循环获得mapper
				for(int i=0;i<listWorkCard.size();i++){
					WorkCard workCard=new WorkCard();
					workCard=listWorkCard.get(i);
					workCardMapper.updateByPrimaryKeySelective(workCard);
				}
	}


   //循环更新Cipin表的信息
	@Override
	public void updateByCipinId(List<Cipin> listCipin) {
		for(int i=0;i<listCipin.size();i++){
			Cipin cipin=new Cipin();
			cipin=listCipin.get(i);
			cipinMapper.updateByPrimaryKeySelective(cipin);
		}
	}


	  //Ajax跟踪单界面-根据批次号查询车间排产表中显示计划数量
	@Override
	public List<ShopPlan> selectNumber(String batchNo) {
		
		return shopPlanMapper.selectNumber(batchNo);
	}


	//删除trackCard表的信息（删除跟踪单）
	@Override
	public int deleteByPrimaryKey(Integer cardId) {
	
		return trackCardMapper.deleteByPrimaryKey(cardId);
	}

	
	//查询批次号是否存在
	@Override
	public Boolean selectBatchNo(String batchNo) {
		
	int	batchNo1=trackCardMapper.selectBatchNo(batchNo);
	if(batchNo1>0){
		return false ;
	}else{
		return true ;
	}	
	
			
	}
	
	
	
	
}
	
	
	

