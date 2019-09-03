package cn.ssm.mapper;

import cn.ssm.po.StCurrentstock;

public interface StCurrentstockMapper {
    int insert(StCurrentstock record);

    int insertSelective(StCurrentstock record);
    
    //1、成品库存数量(条件：ST_CurrentStock.idwarehouse= N'1')
    Integer selectFULLInventoryNum(Integer idinventory);
    //2.原材料库存数量(条件：ST_CurrentStock.idwarehouse= N'1')
    Integer selectMaterialInventoryNum(Integer idinventory);
   
}