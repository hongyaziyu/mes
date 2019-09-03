package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.ShopSort;

public interface ShopSortMapper {
    int deleteByPrimaryKey(Integer sortId);
    
    //原来就有的部分（添加信息）
    int insert(ShopSort record);

    int insertSelective(ShopSort record);

    ShopSort selectByPrimaryKey(Integer sortId);
  
    int updateByPrimaryKeySelective(ShopSort record);
    
    //原来就有的部分（修改信息）
    int updateByPrimaryKey(ShopSort record);
    
    
    //添加的部分（根据客户物料号和物料号删除）删除没有返回值所以为void
    void deleteShopSort(@Param("clientMaterialNo")String clientMaterialNo,@Param("material_no")String material_no);
    
    //添加部分（通过客户和物料号查询）
    List<ShopSort> selectByParam(@Param("clientMaterialNo")String clientMaterialNo,@Param("material_no")String material_no);
    
    List<String> selectShop(@Param("materialNo")String materialNo, @Param("clientMaterialNo")String clientMaterialNo);
}