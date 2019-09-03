package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.MiddleProduct;

public interface MiddleProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MiddleProduct record);

    int insertSelective(MiddleProduct record);

    MiddleProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MiddleProduct record);

    int updateByPrimaryKey(MiddleProduct record);
    
    //1、半成品库存分页查询数量
    int selectByMiddletotalCount(@Param(value="materialNo")String materialNo,@Param(value="productName")String productName);

    //2、半成品库存查询
    List<MiddleProduct> selectByMiddleProduct(@Param("startPos") int startPos,
			@Param("pageSize") int pageSize,
			@Param(value = "materialNo") String materialNo,
			@Param(value = "productName") String productName);
    
    //3、半成品的excel导入查詢是否重複
    List<MiddleProduct> selectByPrimaryMiddleProduct(String materialNo);
    
    //4、周计划——半成品库存显示(根据物料号得到对应的库存信息)
    MiddleProduct selectMiddleInventory(String materialNo);
}