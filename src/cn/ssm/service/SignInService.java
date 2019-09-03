package cn.ssm.service;

import java.util.List;
import java.util.Map;

import cn.ssm.po.Person;

public interface SignInService {
	
	public List<Person> signIn(Map<String,String> map) throws Exception;
	
	public List<Person> selectByLoginId(String loginid)throws Exception;
    
	public List<Person> selectByEmail(String email)throws Exception;
	
	public void insertPerson(Person person)throws Exception;

	public int updatePassword(Person person)throws Exception;
}
