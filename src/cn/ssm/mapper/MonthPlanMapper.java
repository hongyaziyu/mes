package cn.ssm.mapper;

import java.util.List;
import cn.ssm.po.MonthPlan;

public interface MonthPlanMapper {
    int deleteByPrimaryKey(Integer planId);

    int insert(MonthPlan record);

    int insertSelective(MonthPlan record);

    MonthPlan selectByPrimaryKey(Integer planId);

    int updateByPrimaryKeySelective(MonthPlan record);

    int updateByPrimaryKey(MonthPlan record);
    //1.查询月计划
	List<MonthPlan> selectMonthPlan(MonthPlan monthPlan);
	
	//2.查询月计划分页数量
	int selectMonthPlanCount(MonthPlan monthPlan);
	
	//3.月计划excel查询是否重复
	List<MonthPlan> selectMonthPlanByParam(String month,String company,String clientMaterialNo);
}