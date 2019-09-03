package cn.ssm.service;

import java.util.List;
import cn.ssm.po.MonthPlan;

public interface MonthPlanService {
	//1.月计划分页查询
	List<MonthPlan> selectMonthPlan(MonthPlan monthPlan);
	//2.月计划excel导入数据库
	int insertSelective(MonthPlan monthPlan);
	//3.月计划删除
	int deleteByPrimaryKey(Integer planId);
	//4.月计划excel查询是否重复
	List<MonthPlan> selectMonthPlanByParam(String month,String company,String clientMaterialNo);
	//5.更新excel月计划
	int updateByPrimaryKeySelective(MonthPlan record);
}
