package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.ProductTest;

public interface ProductTestMapper {
    int deleteByPrimaryKey(Integer testId);

    int insert(ProductTest record);

    int insertSelective(ProductTest record);

    ProductTest selectByPrimaryKey(Integer testId);

    int updateByPrimaryKeySelective(ProductTest record);

    int updateByPrimaryKey(ProductTest record);
    
    //吴永-添加部分（根据客户和产品名称、计划单号、批次号查询ProductTest表的信息）
    List<ProductTest> selectProductTestByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("client")String client,
     @Param("materialNo")String materialNo,@Param("start_date")String start_date,
     @Param("end_date")String end_date);
   
    
  //查询ProductTest表的信息返回行数totalCount
  	int selectProductTestByParamtotalCount(@Param("client")String client,
  		     @Param("materialNo")String materialNo,@Param("start_date")String start_date,
  		     @Param("end_date")String end_date);
    
    
  	 //1.1Spc曲线的图号，物料号，批次号，产品名称，工序
    ProductTest selectSpc(String clientMaterialNo,String materialNo,String batchNo,String processName);
    
    //1.2Spc的标准值中的字段（18.0+0.5/-0.2：18.0中值，0.5,0.2）
    String selectStandard(String clientMaterialNo,String materialNo,String batchNo,String processName);
    
    //1.3Spc曲线查询检测日期最大的字段
    String selectEndCheckDate(String clientMaterialNo,String materialNo,String batchNo,String processName);
    
    //1.4Spc曲线查询检测日期最小的字段
    String selectStartCheckDate(String clientMaterialNo,String materialNo,String batchNo,String processName);
    
    
    //2.Spc抽取测量值
    List<ProductTest> selectTestVal(String clientMaterialNo,String materialNo,String batchNo,String processName);
    
}