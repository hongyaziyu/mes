package cn.ssm.mapper;

import java.util.List;

import cn.ssm.po.DepOpinion;

public interface DepOpinionMapper {
    int deleteByPrimaryKey(Integer opinionId);

    int insert(DepOpinion record);

    int insertSelective(DepOpinion record);

    DepOpinion selectByPrimaryKey(Integer opinionId);

    int updateByPrimaryKeySelective(DepOpinion record);

    int updateByPrimaryKey(DepOpinion record);
    
    
	
//  修改
  List<DepOpinion> selectByAbnormalId(Integer abnormalId);
  void deleteByAbnormalId(Integer abnormalId);
  
  
}