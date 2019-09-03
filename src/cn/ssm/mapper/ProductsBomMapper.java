package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.ProductsBom;

public interface ProductsBomMapper {
    int deleteByPrimaryKey(Integer productsId);

    int insert(ProductsBom record);

    int insertSelective(ProductsBom record);

    ProductsBom selectByPrimaryKey(Integer productsId);

    int updateByPrimaryKeySelective(ProductsBom record);

    int updateByPrimaryKey(ProductsBom record);
    
    
      //添加部分（通过客户和物料号查询）
  	  //产品表查询分页
      List<ProductsBom> selectProductsBomByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param(value="client_material_no")String client_material_no,@Param(value="material_no")String material_no);
      
      //产品表查询分页返回行数totalCount
      int selectProductsBomByParamtotalCount(@Param(value="client_material_no")String client_material_no,@Param(value="material_no")String material_no);
      
      
      //（根据客户物料号和物料号删除）删除没有返回值所以为void
      void deleteProductsBom(String client_material_no,String material_no);
      
      
      //（通过物料号和客户物料号查询）
      List<ProductsBom> selectByParam(String client_material_no,String material_no);
      
      
      //Ajax检查是否有该客户物料号
      List<ProductsBom> selectMaterialNoIsNull(String clientMaterialNo);
      
      //Ajax根据图号查询物料号并下拉显示
      List<String> selectMaterialNos(@Param("clientMaterialNo")String clientMaterialNo);
      
      //Ajax根据图号、物料号查询得到子件名称（材料名称）
      List<ProductsBom> selectProductsBom(@Param("clientMaterialNo")String clientMaterialNo,@Param("materialNo")String materialNo);
      
      //excel判断插入还是删除-添加部分（通过物料号和客户物料号和子件编码查询）
      List<ProductsBom> selectexcelByParam(String client_material_no,String material_no,String zijian_no);
   
      
      //Ajax-根据物料号得到相应的产品名称
     String selectProductName(String materialNo);
     
     //Ajax-根据物料号得到相应的产品名称和产品规格
     ProductsBom selectCpmcandCpgg(String materialNo);
     
     //根据批次号,图号，物料号查询周计划的计划单号，产品名称，产品规格所有信息
     List<ProductsBom> selectJCC(@Param("clientMaterialNo")String clientMaterialNo,@Param("materialNo")String materialNo);
     
     
}