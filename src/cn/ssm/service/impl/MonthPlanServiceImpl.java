package cn.ssm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ssm.mapper.MonthPlanMapper;
import cn.ssm.po.MonthPlan;
import cn.ssm.service.MonthPlanService;
@Service
public class MonthPlanServiceImpl implements MonthPlanService {
	
	@Autowired
	private MonthPlanMapper monthPlanMapper;
	
	//1.月计划分页查询
	@Override
	public List<MonthPlan> selectMonthPlan(MonthPlan monthPlan) {
		
		return monthPlanMapper.selectMonthPlan(monthPlan);
	}
	
	//2.月计划excel导入数据库
	@Override
	public int insertSelective(MonthPlan monthPlan) {
		
		return monthPlanMapper.insertSelective(monthPlan);
	}
	//3.月计划删除
	@Override
	public int deleteByPrimaryKey(Integer planId) {
		
		return monthPlanMapper.deleteByPrimaryKey(planId);
	}
	
	//4.月计划excel查询是否重复
	@Override
	public List<MonthPlan> selectMonthPlanByParam(String month, String company,
			String clientMaterialNo) {
		
		return monthPlanMapper.selectMonthPlanByParam(month, company, clientMaterialNo);
	}
	
	//5.更新excel月计划
	@Override
	public int updateByPrimaryKeySelective(MonthPlan record) {
		
		return monthPlanMapper.updateByPrimaryKeySelective(record);
	}

}
