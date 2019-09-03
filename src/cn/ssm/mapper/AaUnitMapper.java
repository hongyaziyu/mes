package cn.ssm.mapper;

import cn.ssm.po.AaUnit;

public interface AaUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AaUnit record);

    int insertSelective(AaUnit record);

    AaUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AaUnit record);

    int updateByPrimaryKeyWithBLOBs(AaUnit record);

    int updateByPrimaryKey(AaUnit record);
    
    //1、根据id去查计量单位表中单位名称
    String selectunitname(Integer id);
}