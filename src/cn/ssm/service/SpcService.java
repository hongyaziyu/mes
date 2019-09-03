package cn.ssm.service;

import java.util.List;

import cn.ssm.po.Spc;
import cn.ssm.po.SpcTest;


public interface SpcService {
  
    //1.Spc查询
    Spc selectSpc(String clientMaterialNo,String materialNo,String batchNo,String process,String characterVal);
    
    
    //2.Spc表格
    List<String> SpcTableShow(String clientMaterialNo,String materialNo,String batchNo,String process,String characterVal);
    
    
    //3.Spc曲线
    List<String> SpcBightShow(String clientMaterialNo,String materialNo,String batchNo,String process,String characterVal);
    
    
    //4.Spc过程能力评估
    List<String> SpcPCI(String clientMaterialNo,String materialNo,String batchNo,String process,String characterVal);
    
    
    //5.Ajax根据物料号得到工序，并下拉显示
    String selectProcess(String materialNo);
    
    //6.Spc记录查询（分页）
    List<Spc> selectSpcrecord(int startPos,int pageSize,String materialNo,String batchNo,String process,String characterVal);
  
    //7.Spc修改记录查询（分页）
    Spc selectUpdateSpcrecord(String batchNo,String process,String characterVal);
    
    
    //8.SpcTest修改记录查询（分页）
    List<SpcTest> selectEditSpcrecord(String batchNo,String process,String characterVal);
    
    //9.Spc修改
    int updateByPrimaryKeySelective(Spc spcId);
    
    //10.SpcTest修改
    void updateSpcTest(List<SpcTest> listSpcTest);
}
