package cn.ssm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.CipinType;

public interface CipinTypeService {
		// 添加到的部分：根据车间名称和次品类型查询
		// 1.(查询)不良品类型查询分页
		List<CipinType> selectCipinByParam(@Param("startPos") int startPos,
				@Param("pageSize") int pageSize,
				@Param(value = "shopName") String shopName,
				@Param(value = "cipinType") String cipinType);
		
		//2.(插入)
		int insertSelective(CipinType record);
		
		//3.(删除)
		int deleteByPrimaryKey(String shopName,String cipinType);
		
		//4.(更新-先删除后插入)
		void insertByType(List<CipinType> listCipinType);
		
		//5.(更新的查询)
		List<CipinType> selectCipinByType(
				@Param(value = "shopName") String shopName,
				@Param(value = "cipinType") String cipinType);
		
}
