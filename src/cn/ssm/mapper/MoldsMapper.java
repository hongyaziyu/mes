package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.Molds;

public interface MoldsMapper {
    int deleteByPrimaryKey(Integer moldId);

    int insert(Molds record);

    int insertSelective(Molds record);

    Molds selectByPrimaryKey(Integer moldId);

    int updateByPrimaryKeySelective(Molds record);

    int updateByPrimaryKey(Molds record);
    
    //磨具名称查询
   	List<String> selectMoldNames();
   	
   	//模具查询分页返回行数totalCount
   	int selectMoldsByParamtotalCount(@Param(value="mold_name")String mold_name,@Param(value="mold_no")String mold_no);
   	
   	//模具查询
   	//模具查询分页
   	 List<Molds> selectMoldsByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param(value="mold_name")String mold_name,@Param(value="mold_no")String mold_no);

   	 
   	//模具查询
   	 List<Molds> selectMoldsByParam1(String mold_name,String mold_no);
}