package cn.ssm.mapper;

import java.util.List;

import cn.ssm.po.SpcTest;

public interface SpcTestMapper {
    int deleteByPrimaryKey(Integer testId);

    int insert(SpcTest record);

    int insertSelective(SpcTest record);

    SpcTest selectByPrimaryKey(Integer testId);

    int updateByPrimaryKeySelective(SpcTest record);

    int updateByPrimaryKey(SpcTest record);
    
    //查询是否存在该工序和特征值下的25组测量值
    List<SpcTest> selectSpcTest(String clientMaterialNo,String materialNo,String batchNo,String process,String characterVal);

    //Spc修改记录查询（分页）
    List<SpcTest> selectEditSpcrecord(String batchNo,String process,String characterVal);

}