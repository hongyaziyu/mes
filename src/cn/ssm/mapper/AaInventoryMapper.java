package cn.ssm.mapper;

import cn.ssm.po.AaInventory;

public interface AaInventoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AaInventory record);

    int insertSelective(AaInventory record);

    AaInventory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AaInventory record);

    int updateByPrimaryKeyWithBLOBs(AaInventory record);

    int updateByPrimaryKey(AaInventory record);
    
    //1、根据存货id查询图号、物料号、产品名称、产品规格
    AaInventory selectSaleOrder(Integer id);
    
    //2、根据物料号查询存货id
    AaInventory selectInventoryid(String materialNo);
    
 	//3、根据存货id查询物料号
    String selectInventoryidToMaterialNo(Integer id);
}