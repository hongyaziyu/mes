package cn.ssm.service;

import java.util.List;

import cn.ssm.vo.Salary;
import cn.ssm.po.WorkCard;

public interface SalaryService {
	 
    //1.添加内容：根据操作工，车间，月份（工资查询）
	 //工资单记录查询分页
    List<WorkCard> SelectByPrimary(int startPos,int pageSize,String operator,String shop_name,String date);
    
    //2.1添加内容；根据车间，操作工，年份，月份（工资详细信息的查询） 
   //工资单详细记录分页
    List<Salary> SelectByPrimaryDate(int startPos,int pageSize,String shop_name,String operator,String date);
    
    //3.1工资单详细记录excel数据查询
    List<Salary> SelectexcelByPrimaryDate(String shop_name,String operator,String date);
   
    //3.2工资单记录excel查询分页
    List<WorkCard> SelectexcelByPrimary(String operator,String shop_name,String date);
}



