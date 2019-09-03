package cn.ssm.service;

import java.util.List;

import cn.ssm.po.SpecialGauge;
import cn.ssm.po.HeightGauge;

public interface CheckSelectService {
   
	
	    //1.1检具-高度规查询分页
		List<HeightGauge> selectHeightGaugeByParam(int startPos,int pageSize,String gaugeNo,String productSpecification);
		
	    //1.5.excel检具-高度规查询
		List<HeightGauge> selectHeightGaugeByParam1(String gaugeNo,String productSpecification);

	    //1.2.更新检具-高度规信息的servicece接口
		int insert(HeightGauge record);
		
	    //1.3.删除检具-高度规信息的servicece接口	
		int deleteByPrimaryKey(Integer id);
		
	    //1.4.更新检具-高度规信息的servicece接口		
		int updateByPrimaryKeySelective(HeightGauge record);
		
		HeightGauge selectByPrimaryKey(Integer id);
		
		
	
		//2.1检具-特殊规查询分页
		List<SpecialGauge> selectSpecialGaugeByParam(int startPos,int pageSize,String gaugeName,String gaugeNo);
		
		//2.5检具-特殊规查询
		List<SpecialGauge> selectSpecialGaugeByParam1(String gaugeName,String gaugeNo);
		
		//2.2更新检具-特殊规信息的servicece接口
		int insertSelect(SpecialGauge record);
		
		//2.3删除检具-特殊规信息的servicece接口	
		int deletePrimaryKey(Integer id);
		
		//2.4更新检具-特殊规信息的servicece接口		
		int updatePrimaryKeySelective(SpecialGauge record);
		
		SpecialGauge selectPrimaryKey(Integer id);
}
