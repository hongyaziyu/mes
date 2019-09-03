package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.ProcessTransition;

public interface ProcessTransitionMapper {
    int deleteByPrimaryKey(Integer transitionId);

    int insert(ProcessTransition record);

    int insertSelective(ProcessTransition record);

    ProcessTransition selectByPrimaryKey(Integer transitionId);

    int updateByPrimaryKeySelective(ProcessTransition record);

    int updateByPrimaryKey(ProcessTransition record);
    
    //查询  
    //工序交接单记录分页
    List<ProcessTransition> selectProcessTransitionByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("provider")String provider,@Param("batchNo")String batchNo,@Param("shopName")String shopName,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
    //工序交接记录分页查询返回行数totalCount
  	int selectProcessTransitionByParamtotalCount(@Param("provider")String provider,@Param("batchNo")String batchNo,@Param("shopName")String shopName,@Param("start_date")String start_date,@Param("end_date")String end_date);
  	
    //工序交接单excel记录分页
    List<ProcessTransition> selectExcelProcessTransitionByParam(@Param("provider")String provider,@Param("batchNo")String batchNo,@Param("shopName")String shopName,@Param("start_date")String start_date,@Param("end_date")String end_date);

}