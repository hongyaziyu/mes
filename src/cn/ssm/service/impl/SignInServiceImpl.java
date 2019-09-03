package cn.ssm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.PersonMapper;
import cn.ssm.po.Person;
import cn.ssm.service.SignInService;
import cn.ssm.util.DataSource;
@Service
@DataSource("dataSource1")
public class SignInServiceImpl implements SignInService{
	
	@Autowired 
	private PersonMapper personMapper;
	@Override
	public List<Person> signIn(Map<String,String> map) throws Exception {
		
		return personMapper.selectByLoginidAndPassword(map);
	}
	@Override
	public List<Person> selectByLoginId(String loginid) {
		
		return personMapper.selectByLoginId(loginid);
	}
	@Override
	public List<Person> selectByEmail(String email) {
		
		return personMapper.selectByEmail(email);
	}
	
	@Override
	public void insertPerson(Person person) throws Exception {
		personMapper.insertSelective(person);
	
		
		
	}
	@Override
	public int updatePassword(Person person) throws Exception {
		
		return personMapper.updateByPrimaryKeySelective(person);
	}
	
}
