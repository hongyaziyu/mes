package cn.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.TrackCardMapper;
import cn.ssm.mapper.WorkCardMapper;
import cn.ssm.po.WorkCard;
import cn.ssm.service.WorkCardService;
import cn.ssm.util.DataSource;
import cn.ssm.vo.TemPrice;

@Service
@DataSource("dataSource1")
public class WorkCardServiceImpl implements WorkCardService {
	@Autowired
	private WorkCardMapper workCardMapper;
	@Autowired
	private TrackCardMapper trackCardMapper;
	@Override
	public List<WorkCard> selectWorkCardByParam(int startPos,int pageSize, String produceDate,String produceDate1){
		return workCardMapper.selectWorkCardByParam(startPos, pageSize, produceDate,produceDate1);
	}

	 //4.1临时工价审批分页
	@Override
	public List<TemPrice> SelectByTemprice(int startPos, int pageSize,String shopName,String processName,String start_date,String end_date) {
		List<TemPrice> listtemprice = new ArrayList<TemPrice>();
		listtemprice=trackCardMapper.SelectByTemprice(startPos, pageSize,shopName,processName,start_date,end_date);
		return listtemprice;
	}
	//4.2临时工价审批（0-1）
	@Override
	public int updateapproveTemprice(String batchNo,String temPrice) {
		
		return trackCardMapper.updateapproveTemprice(batchNo,temPrice);
	}
	//4.3临时工价审批不通过（0-2）
	@Override
	public int updatenotapproveTemprice(String batchNo) {
		
		return trackCardMapper.updatenotapproveTemprice(batchNo);
	}

}
