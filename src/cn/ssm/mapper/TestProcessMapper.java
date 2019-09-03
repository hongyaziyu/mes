package cn.ssm.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.TestProcess;

public interface TestProcessMapper {
    int deleteByPrimaryKey(Integer processId);
    
    //原来就有的部分（添加信息）
    int insert(TestProcess record);

    int insertSelective(TestProcess record);

    TestProcess selectByPrimaryKey(Integer processId);

    int updateByPrimaryKeySelective(TestProcess record);

    //原来就有的部分（修改信息）
    int updateByPrimaryKey(TestProcess record);
    
    //添加的部分（根据客户物料号和物料号删除）删除没有返回值所以为void
   void deleteTestProcess(String client_material_no,String material_no);
   
   //添加部分（通过图号和物料号查询）
   List<TestProcess> selectByParam(String client_material_no,String material_no);
 
   
   //吴永-添加部分（根据客户物料号和物料号从TestProcess表查检测工序）
     List<TestProcess> selectProcess(@Param("materialNo")String materialNo, @Param("clientMaterialNo")String clientMaterialNo);
     
   //吴永-添加部分(查询ProductTest表的标准值在TestProcess表中是否存在)
     List<TestProcess> selectTestProcessByParam(@Param("materialNo")String materialNo, @Param("clientMaterialNo")String clientMaterialNo,
     		@Param("process")String process, @Param("standardVal")String standardVal);
     
     void  updateByPrimaryAllSelective(@Param("materialNo")String materialNo, @Param("clientMaterialNo")String clientMaterialNo,
     		@Param("process")String process,@Param("standardVal")String standardVal);
   //吴永-删除多余的检测工序1是删除原始检测工序，2是删除原始检测工序+特征值
     void  deleteTestProcessduoyu1(@Param("materialNo")String materialNo, @Param("clientMaterialNo")String clientMaterialNo,
      		@Param("process0")String process0);
     void  deleteTestProcessduoyu2(@Param("materialNo")String materialNo, @Param("clientMaterialNo")String clientMaterialNo,
      		@Param("process")String process);
     
     
     //Ajax根据物料号得到工序，并下拉显示
     List<String> selectMaterialNoToProcess(String materialNo);
}