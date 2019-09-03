package cn.ssm.service;
import java.util.List;

import cn.ssm.po.CheckContent;
import cn.ssm.po.CheckRecord;
import cn.ssm.po.DailyCheck;

public interface DailyCheckService {
	 List<DailyCheck> selectByPrimary(int startPos,int pageSize,String batchNo,String processName,String assetNo);
	 
	 List<CheckRecord> selectCheckRecordByKey(Integer checkId);
	 DailyCheck selectByPrimaryKey(Integer checkId);
	 
	 //1.故障查询
	 List<DailyCheck> selectByPrimary1(int startPos,int pageSize,String batchNo,String processName,String assetNo);
	 
	 //2.设备点检内容
	 List<CheckContent> selectAssetCheckRecord(int startPos,int pageSize,String assetName);
	 
	 //3.添加设备点检内容
	 int insertSelective(CheckContent checkContent);
	 
	 //4.1更新-先查询出设备点检内容
	 List<CheckContent> selectAssetCheckRecordByParm(String assetName);
	 
	 //5.删除设备点检内容
	 int deleteByPrimaryKey(String assetName);
	 
	 //4.2更新-先删除后添加
	 void insertAssetCheckRecord(List<CheckContent> listAssetCheckContent);
	 
	 //6.1 excel导入-更新设备点检内容
	 int updateByPrimaryKeySelective(CheckContent checkContent);
	 
	 //6.2 excel导入-查询是否存在该条信息
	 List<CheckContent> selectAssetCheckRecordExcel(String assetName,String result);
}
