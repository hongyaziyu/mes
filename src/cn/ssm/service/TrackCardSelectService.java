package cn.ssm.service;

import java.util.List;

import cn.ssm.po.Cipin;
import cn.ssm.po.ShopPlan;
import cn.ssm.po.TrackCard;
import cn.ssm.po.WorkCard;

public interface TrackCardSelectService {

	//添加的部分：根据四个条件进行查询的servicece接口
	//跟踪单记录分页
	 List<TrackCard> selectTrackCardByParam( int startPos,int pageSize,String client,String plan_no,String client_material_no,String material_no);
	
	//增加功能
	 void insertTrackCard(TrackCard trackCard);
	
	 

	 //（修改操作）根据cardId查询TrackCard表的内容
	 TrackCard selectByPrimaryKey(Integer cardId);
	 
	 //（修改操作）：根据cardId查询WorkCard
	  List<WorkCard> SelectBycardId(Integer cardId);
	  
	 //更新trackCard表的信息
	 int updateByPrimaryKey(TrackCard trackCard);
	 
	 //删除trackCard表的信息（删除跟踪单）
	 int deleteByPrimaryKey(Integer cardId);
	 
	 //更新WorkCard表的信息
	void updateByTrackId(List<WorkCard> listWorkCard);
	 
	 
	 //更新Cipin表的信息
	void updateByCipinId(List<Cipin> listCipin);
	
	

    //Ajax跟踪单界面-根据批次号查询车间排产表中显示计划数量
    List<ShopPlan> selectNumber(String batchNo);
    
    //查询批次号是否存在
    Boolean selectBatchNo(String batchNo);
}
