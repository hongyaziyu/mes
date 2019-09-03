package cn.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.PermissionMapper;
import cn.ssm.mapper.PersonMapper;
import cn.ssm.mapper.UrlMapper;
import cn.ssm.po.Permission;
import cn.ssm.po.Person;
import cn.ssm.po.Url;
import cn.ssm.service.PersonService;
import cn.ssm.util.DataSource;

@Service
@DataSource("dataSource1")
public class PersonServiceImpl implements PersonService{

	//Mapper注入
    @Autowired
	private PersonMapper personMapper;
    @Autowired
   	private PermissionMapper permissionMapper;
    @Autowired
   	private UrlMapper urlMapper;

    
	//1.人员权限-查询
	@Override
	public List<Person> selectLimitManage(int startPos, int pageSize,
			String number, String person_name, String department) {
		
		return personMapper.selectLimitManage(startPos, pageSize, number, person_name, department);
	}

	
	
	//2.人员权限-修改（回显的查询）
	@Override
	public Person selectPerson(int personId) {
		
		return personMapper.selectByPrimaryKey(personId);
	}
	
	

	//3.查询Permission表personId对应中的type并用字符串拼接传回，防止前端多线程
	@Override
	public String selectPermission(int personId) {
		List<Permission> list=new ArrayList<Permission>();
		list=permissionMapper.selectPermission(personId);
		String str="";
		for(Permission permission:list){
			str+=permission.getType()+",";
		}
		
		return str;
	}

	
	
	//4.查询url表对应中的全部type
	@Override
	public List<String> selectType() {
		
		return urlMapper.selectType();
	}


	//5.更新person表
	@Override
	public int updateByPrimaryKeySelective(Person person) {
		
		return personMapper.updateByPrimaryKeySelective(person);
	}


    //6.根据type查询url
	@Override
	public List<Url> selectUrl(String type) {
		
		return urlMapper.selectUrl(type);
	}


   //7.删除-person_id对应的permisssion表（更新时and删除时使用）
	@Override
	public int deleteBypersonId(Integer personId) {
		
		return permissionMapper.deleteBypersonId(personId);
	}


	//8.插入-根据person_id插入permisssion表
	@Override
	public int insertSelective(Permission permission) {
		
		return permissionMapper.insertSelective(permission);
	}


	 //9.person_id对应的person表（删除时使用）
	@Override
	public int deleteByPrimaryKey(Integer personId) {
		
		return personMapper.deleteByPrimaryKey(personId);
	}


	 //10.excel-根据员工编号查询数据库
	@Override
	public List<Person> selectPersonByNumber(String number) {
		
		return personMapper.selectPersonByNumber(number);
	}


	//11.excel-插入数据库
	@Override
	public int insertByNumber(Person person) {
		int a=0;
		try {
			a=personMapper.insert(person);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}


	//12.excel-更新数据库
	@Override
	public int updateByNumber(Person person) {
		int a=0;
		try {
			a=personMapper.updateByPrimaryKeySelective(person);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}

}
