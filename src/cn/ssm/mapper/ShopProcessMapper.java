package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.ShopProcess;

public interface ShopProcessMapper {
    int deleteByPrimaryKey(Integer processId);

    int insert(ShopProcess record);

    int insertSelective(ShopProcess record);

    ShopProcess selectByPrimaryKey(Integer processId);

    int updateByPrimaryKeySelective(ShopProcess record);

    int updateByPrimaryKey(ShopProcess record);
    
    
    List<String> selectProcess(@Param("materialNo")String materialNo, @Param("shopName")String shopName, @Param("clientMaterialNo")String clientMaterialNo);

    //添加的部分（根据客户物料号和物料号删除）删除没有返回值所以为void
    void deleteShopProcess(@Param("clientMaterialNo")String clientMaterialNo,@Param("material_no")String material_no);
    
    //添加部分（通过图号和物料号查询）
    List<ShopProcess> selectByParam(@Param("clientMaterialNo")String clientMaterialNo,@Param("material_no")String material_no);
}