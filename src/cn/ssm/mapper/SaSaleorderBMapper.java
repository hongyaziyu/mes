package cn.ssm.mapper;

import cn.ssm.po.SaSaleorderB;

public interface SaSaleorderBMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaSaleorderB record);

    int insertSelective(SaSaleorderB record);

    SaSaleorderB selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaSaleorderB record);

    int updateByPrimaryKeyWithBLOBs(SaSaleorderB record);

    int updateByPrimaryKey(SaSaleorderB record);
}