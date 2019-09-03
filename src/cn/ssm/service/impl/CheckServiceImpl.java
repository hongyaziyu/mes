package cn.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ssm.mapper.HeightGaugeMapper;
import cn.ssm.mapper.SpecialGaugeMapper;
import cn.ssm.po.HeightGauge;
import cn.ssm.po.SpecialGauge;
import cn.ssm.service.CheckSelectService;
import cn.ssm.util.DataSource;

@Service
@DataSource("dataSource1")
public class CheckServiceImpl implements CheckSelectService {

	
	@Autowired
	private HeightGaugeMapper heightGaugeMapper;
	@Autowired
	private SpecialGaugeMapper specialGaugeMapper;
	
	
	
	//1.1.检具-高度规查询分页
	@Override
	public List<HeightGauge> selectHeightGaugeByParam(int startPos,
			int pageSize, String gaugeNo, String productSpecification) {
		
		return heightGaugeMapper.selectHeightGaugeByParam(startPos, pageSize, gaugeNo, productSpecification);
	}
	
	
	//1.5.excel检具-高度规查询
	@Override
	public List<HeightGauge> selectHeightGaugeByParam1(String gaugeNo,
			String productSpecification) {
		
		return heightGaugeMapper.selectHeightGaugeByParam1(gaugeNo, productSpecification);
	}
	
	
	//1.2.更新检具-高度规信息的servicece接口
	@Override
	public int insert(HeightGauge record) {
		
		int a=0;
		try {
			a=heightGaugeMapper.insertSelective(record);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}
	
	
	//1.3.删除检具-高度规信息的servicece接口
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return heightGaugeMapper.deleteByPrimaryKey(id);
	}
	
	
	//1.4.更新检具-高度规信息的servicece接口
	@Override
	public int updateByPrimaryKeySelective(HeightGauge record) {
		int a=0;
		try {
		a= heightGaugeMapper.updateByPrimaryKeySelective(record);
              } catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}
	
	
	@Override
	public HeightGauge selectByPrimaryKey(Integer id) {
		
		return heightGaugeMapper.selectByPrimaryKey(id);
	}


	
	//2.1检具-特殊规查询分页
	@Override
	public List<SpecialGauge> selectSpecialGaugeByParam(int startPos,
			int pageSize, String gaugeName, String gaugeNo) {
		
		return specialGaugeMapper.selectSpecialGaugeByParam(startPos, pageSize, gaugeName, gaugeNo);
	}

	//2.5检具-特殊规查询
	@Override
	public List<SpecialGauge> selectSpecialGaugeByParam1(String gaugeName,
			String gaugeNo) {
		
		return specialGaugeMapper.selectSpecialGaugeByParam1(gaugeName, gaugeNo);
	}

	//2.2更新检具-特殊规信息的servicece接口
	@Override
	public int insertSelect(SpecialGauge record) {
		
		return specialGaugeMapper.insertSelective(record);
	}

	//2.3删除检具-特殊规信息的servicece接口	
	@Override
	public int deletePrimaryKey(Integer id) {
		
		return specialGaugeMapper.deleteByPrimaryKey(id);
	}

	//2.4更新检具-特殊规信息的servicece接口
	@Override
	public int updatePrimaryKeySelective(SpecialGauge record) {
		
		return specialGaugeMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public SpecialGauge selectPrimaryKey(Integer id) {
		
		return specialGaugeMapper.selectByPrimaryKey(id);
	}
	
	
	
	
	
	
	
	
	
}
