package cn.ssm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.Person;

public interface PersonMapper {
    int deleteByPrimaryKey(Integer personId);

    int insert(Person record);

    void insertSelective(Person record);

    Person selectByPrimaryKey(Integer personId);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
    
    //操作工查询
    List<String> selectPersonNames();
    
    //登录根据工号和密码查询条件
    List<Person> selectByLoginidAndPassword(Map<String,String> map);
    
    List<Person> selectByLoginId(String loginid);
    
    List<Person> selectByEmail(String email);
    
    //查询权限人员信息
    List<Person> selectLimitManage(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param(value="number")String number,@Param(value="person_name")String person_name,@Param(value="department")String department);
    
    //权限人员分页返回行数totalCount
  	int selectLimitManagetotalCount(@Param(value="number")String number,@Param(value="person_name")String person_name,@Param(value="department")String department);

    //excel-根据员工编号查询数据库
  	List<Person> selectPersonByNumber(String number);

}


