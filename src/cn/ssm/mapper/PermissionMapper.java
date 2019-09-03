package cn.ssm.mapper;

import java.util.List;

import cn.ssm.po.Permission;
import cn.ssm.po.Person;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<String> selectUrlByPerson(Person person);
    
    //1.查询Permission表personId对应中的type并用字符串拼接传回，防止前端多线程
    List<Permission> selectPermission(int personId);
    
    
    //2.删除person_id对应的permisssion表
    int deleteBypersonId(Integer personId);
    
}


