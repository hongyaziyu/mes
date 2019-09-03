package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.Spc;

public interface SpcMapper {
    int deleteByPrimaryKey(Integer spcId);

    int insert(Spc record);

    int insertSelective(Spc record);

    Spc selectByPrimaryKey(Integer spcId);

    int updateByPrimaryKeySelective(Spc record);

    int updateByPrimaryKey(Spc record);
    
    //如果spc中存在这条信息则不插入到spc表中
    List<String> selectSpc(String clientMaterialNo,String materialNo,String batchNo,String process,String characterVal);
    
    //更新采集时间的截止日期变化
   int updateSpc(String clientMaterialNo,String materialNo,String batchNo,String process,String characterVal,String CheckEndDate);
  
    //Spc过程能力分析
    Spc selectPCI(String clientMaterialNo,String materialNo,String batchNo,String process,String characterVal);
    
    //Spc记录分页（返回行数）
    int selectSpcBytotalCount(@Param(value="materialNo")String materialNo,@Param(value="batchNo")String batchNo,@Param(value="process")String process,@Param(value="characterVal")String characterVal);

    //Spc记录查询
    List<Spc> selectSpcrecord(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param(value="materialNo")String materialNo,@Param(value="batchNo")String batchNo,@Param(value="process")String process,@Param(value="characterVal")String characterVal);

    //7.Spc修改记录查询（分页）
    Spc selectUpdateSpcrecord(String batchNo,String process,String characterVal);
}