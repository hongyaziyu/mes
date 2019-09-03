package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.AaBom;

public interface AaBomMapper {
    int insert(AaBom record);

    int insertSelective(AaBom record);
    
    //1、查询所有的图号对应的物料号
    List<String> selectAaBom(@Param(value="clientMaterialNo")String clientMaterialNo);
    
    //2、根据物料号得到所有的子件bom的存货id
    List<Integer> selectZiIdInventory(String materialNo);
    
    //3、根据物料号得到所有母件bom的存货id
    Integer selectMuIdInventory(String materialNo);
}