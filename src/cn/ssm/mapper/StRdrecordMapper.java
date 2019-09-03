package cn.ssm.mapper;

import cn.ssm.po.StRdrecord;

public interface StRdrecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StRdrecord record);

    int insertSelective(StRdrecord record);

    StRdrecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StRdrecord record);

    int updateByPrimaryKeyWithBLOBs(StRdrecord record);

    int updateByPrimaryKey(StRdrecord record);
    
    //1.原材料T+系统的选择模板并选择模板excel导出-查询数据库中该月中原材料的单据数量为单据日期做拼接！
  	String selectRowMaterialNum();
  	//2.产成品T+系统的选择模板并选择模板excel导出-查询数据库中该月中原材料的单据数量为单据日期做拼接！
  	String selectFullProductNum();
  
}