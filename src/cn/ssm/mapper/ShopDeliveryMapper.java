package cn.ssm.mapper;

import java.util.List;

import cn.ssm.po.ShopDelivery;

public interface ShopDeliveryMapper {
    int deleteByPrimaryKey(Integer shopDeliveryId);

    int insert(ShopDelivery record);

    int insertSelective(ShopDelivery record);

    ShopDelivery selectByPrimaryKey(Integer shopDeliveryId);

    int updateByPrimaryKeySelective(ShopDelivery record);

    int updateByPrimaryKey(ShopDelivery record);
    
	List<ShopDelivery> selectByKey(Integer orderId);
	
	int deleteByKey(Integer planId);
	
	  //计划进度查询
	 Integer selectShopDeliveryByParam(Integer planId);
	//查询生产车间
	 String selectShopNameByPlanId(Integer planId);
}