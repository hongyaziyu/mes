package cn.ssm.mapper;

import cn.ssm.po.AaPartner;

public interface AaPartnerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AaPartner record);

    int insertSelective(AaPartner record);

    AaPartner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AaPartner record);

    int updateByPrimaryKeyWithBLOBs(AaPartner record);

    int updateByPrimaryKey(AaPartner record);
    
    //1、订单信息中根据idcustomer查询客户名称
    String selectCustomer(Integer idcustomer);
}