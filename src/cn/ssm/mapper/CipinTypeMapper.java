package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.CipinType;

public interface CipinTypeMapper {
	int deleteByPrimaryKey(Integer typeId);

	int insert(CipinType record);

	int insertSelective(CipinType record);

	CipinType selectByPrimaryKey(Integer typeId);

	int updateByPrimaryKeySelective(CipinType record);

	int updateByPrimaryKey(CipinType record);

	//1.1 查询分页返回行数totalCount
	int selectCipinByParamtotalCount(
			@Param(value = "shopName") String shopName,
			@Param(value = "cipinType") String cipinType);

	// 添加到的部分：根据车间名称和次品类型查询
	//1.2 不良品类型查询分页
	List<CipinType> selectCipinByParam(@Param("startPos") int startPos,
			@Param("pageSize") int pageSize,
			@Param(value = "shopName") String shopName,
			@Param(value = "cipinType") String cipinType);
	
	//2.删除
	int deleteByType(String shopName,String cipinType);
	
	
	//3.1修改的查询
	List<CipinType> selectCipinByType(
			@Param(value = "shopName") String shopName,
			@Param(value = "cipinType") String cipinType);
	
	
}