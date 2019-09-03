package cn.ssm.mapper;

import java.util.List;

import cn.ssm.po.TransitionCipin;

public interface TransitionCipinMapper {
    int deleteByPrimaryKey(Integer cipinId);

    int insert(TransitionCipin record);

    int insertSelective(TransitionCipin record);

    TransitionCipin selectByPrimaryKey(Integer cipinId);

    int updateByPrimaryKeySelective(TransitionCipin record);

    int updateByPrimaryKey(TransitionCipin record);
    
    //工序交接查看详细记录
  	List<TransitionCipin> selectProcessTransitionDetail(Integer transitionId);
  	
  	//根据外键transitionId删除交接记录副表信息
  	int deleteTransitionCipin(Integer transitionId);
  	
}