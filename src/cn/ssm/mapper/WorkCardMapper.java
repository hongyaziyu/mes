package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.WorkCard;

public interface WorkCardMapper {
    int deleteByPrimaryKey(Integer trackId);

    int insert(WorkCard record);

    int insertSelective(WorkCard record);

    WorkCard selectByPrimaryKey(Integer trackId);

    int updateByPrimaryKeySelective(WorkCard record);

    int updateByPrimaryKey(WorkCard record);
    
    //1添加内容：根据操作工，车间，月份（工资查询）
    //1.1工资单记录查询分页
    List<WorkCard> SelectByPrimary(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("operator")String operator,@Param("shop_name")String shop_name,@Param("date")String date);
    
    //1.2工资单记录查询分页返回行数totalCount
  	int SelectByPrimarytotalCount(@Param("operator")String operator,@Param("shop_name")String shop_name,@Param("date")String date);
  	
  	//1.3工资单记录excel查询
    List<WorkCard> SelectexcelByPrimary(@Param("operator")String operator,@Param("shop_name")String shop_name,@Param("date")String date);

    
    //2.1（修改操作）：根据cardId查询
    List<WorkCard> SelectBycardId(Integer cardId); 
    
    //3.1次品率查询返回行数totalCount
  	int selectWorkCardByParamtotalCount(@Param("produceDate") String produceDate,@Param("produceDate1") String produceDate1);
   
    //3.2次品率查询
    List<WorkCard> selectWorkCardByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("produceDate") String produceDate,@Param("produceDate1") String produceDate1);

    //模具出入库-查询模具使用频次
  	String SelectBycardId1(Integer cardId , String  mold);
    
 	
    
}
