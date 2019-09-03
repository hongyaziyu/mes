package cn.ssm.mapper;

import java.util.List;

import cn.ssm.po.Url;

public interface UrlMapper {
    int deleteByPrimaryKey(Integer urlId);

    int insert(Url record);

    int insertSelective(Url record);

    Url selectByPrimaryKey(Integer urlId);

    int updateByPrimaryKeySelective(Url record);

    int updateByPrimaryKey(Url record);
    
    List<String> selectUrlByParam(String param);
    
    //4.查询url表对应中的全部type
    List<String> selectType();
    
    //根据type查询url
    List<Url> selectUrl(String type);
}