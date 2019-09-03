package cn.ssm.service;

import java.util.List;

import cn.ssm.po.WorkCard;
import cn.ssm.vo.TemPrice;

public interface WorkCardService {

	 List<WorkCard> selectWorkCardByParam(int startPos,int pageSize,String produceDate,String produceDate1);
	 
    //4.1临时工价审批分页
	 List<TemPrice> SelectByTemprice(int startPos,int pageSize,String shopName,String processName,String start_date,String end_date);
	 
	//4.2临时工价审批（0-1）
    int updateapproveTemprice(String batchNo,String temPrice);
    
    //4.3临时工价审批不通过（0-2）
    int updatenotapproveTemprice(String batchNo);
	
}
