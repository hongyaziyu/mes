package cn.ssm.service;

import java.util.List;

import cn.ssm.po.Permission;
import cn.ssm.po.Person;
import cn.ssm.po.Url;

public interface PersonService {

	//1.人员权限-查询
	List<Person> selectLimitManage(int startPos,int pageSize,String number,String person_name,String department);
	
	//2.人员权限-修改（回显的查询）
	Person selectPerson(int personId);
	
	//3.查询Permission表personId对应中的type并用字符串拼接传回，防止前端多线程
	String selectPermission(int personId);
	
	//4.查询url表对应中的全部type
	List<String> selectType();
	
	//5.更新person表
	 int updateByPrimaryKeySelective(Person person);
	 
    //6.根据type查询url
     List<Url> selectUrl(String type);
     
    //7.删除-person_id对应的permisssion表（更新时and删除时使用）
     int deleteBypersonId(Integer personId);
     
    //8.插入-根据person_id插入permisssion表
     int insertSelective(Permission permission);
     
    //9.person_id对应的person表（删除时使用）
     int deleteByPrimaryKey(Integer personId);
     
    //10.excel-根据员工编号查询数据库
     List<Person> selectPersonByNumber(String number);
     
    //11.excel-插入数据库
     int insertByNumber(Person person);
     
    //12.excel-更新数据库		
 	 int updateByNumber(Person person);
}
