package cn.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.CheckContentMapper;
import cn.ssm.mapper.CheckRecordMapper;
import cn.ssm.mapper.DailyCheckMapper;
import cn.ssm.po.CheckContent;
import cn.ssm.po.CheckRecord;
import cn.ssm.po.DailyCheck;
import cn.ssm.service.DailyCheckService;
import cn.ssm.util.DataSource;

@Service
@DataSource("dataSource1")
public class DailyCheckServiceImpl implements DailyCheckService {

	@Autowired
	private DailyCheckMapper dailycheckMapper;
	@Autowired
	private CheckRecordMapper checkRecordMapper;
	@Autowired
	private CheckContentMapper checkContentMapper;

	@Override
	public List<DailyCheck> selectByPrimary(int startPos, int pageSize,
			String batchNo, String processName, String assetNo) {

		return dailycheckMapper.selectDailyCheckByParam(startPos, pageSize,
				batchNo, processName, assetNo);

	}

	@Override
	public List<CheckRecord> selectCheckRecordByKey(Integer checkId) {
		return checkRecordMapper.selectCheckRecordByKey(checkId);
	}

	@Override
	public DailyCheck selectByPrimaryKey(Integer checkId) {
		return dailycheckMapper.selectByPrimaryKey(checkId);
	}

	//1.故障查询
	@Override
	public List<DailyCheck> selectByPrimary1(int startPos, int pageSize,
			String batchNo, String processName, String assetNo) {

		return dailycheckMapper.selectBreakdownByParam(startPos, pageSize,
				batchNo, processName, assetNo);

	}
	//2.设备点检内容
	@Override
	public List<CheckContent> selectAssetCheckRecord(int startPos, int pageSize,
			String assetName) {
	
		return checkContentMapper.selectAssetCheckRecord(startPos, pageSize, assetName);
		
		
	}
	//3.添加设备点检内容
	@Override
	public int insertSelective(CheckContent checkContent) {
		
		return checkContentMapper.insertSelective(checkContent);
	}
	
	 //4.更新-先查询出设备点检内容
	@Override
	public List<CheckContent> selectAssetCheckRecordByParm(String assetName) {
		
		return checkContentMapper.selectAssetCheckRecordByParm(assetName);
	}

	 //5.删除设备点检内容
	@Override
	public int deleteByPrimaryKey(String assetName) {
		
		return checkContentMapper.deleteAssetCheckRecord(assetName);
	}

	//4.2更新-先删除后添加
	@Override
	public void insertAssetCheckRecord(List<CheckContent> listAssetCheckContent) {

		for(int i=0;i<listAssetCheckContent.size();i++){
			CheckContent checkContent=new CheckContent();
			checkContent=listAssetCheckContent.get(i);
			checkContentMapper.insertSelective(checkContent);
		}
	}

	 //6.1 excel导入-更新设备点检内容
	@Override
	public int updateByPrimaryKeySelective(CheckContent checkContent) {
		
		return checkContentMapper.updateByPrimaryKeySelective(checkContent);
	}

	//6.2 excel导入-查询是否存在该条信息
	@Override
	public List<CheckContent> selectAssetCheckRecordExcel(String assetName,String result){
		
		return checkContentMapper.selectAssetCheckRecordExcel(assetName, result);
	}
	
}
