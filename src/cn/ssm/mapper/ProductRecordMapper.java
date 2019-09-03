package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.ProductRecord;

public interface ProductRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(ProductRecord record);

    int insertSelective(ProductRecord record);

    ProductRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(ProductRecord record);

    int updateByPrimaryKey(ProductRecord record);
    
  //领半成品\退半成品记录
    //1.1领半成品记录/领半成品记录分页
    List<ProductRecord> selectInputMiddleRecord(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);

    //1.2领半成品记录分页返回行数totalCount
    int selectInputMiddleRecordtotalCount(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
     

    //2.1退半成品记录/退半成品记录分页
    List<ProductRecord> selectOutputMiddleRecord(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);

    //2.2退半成品记录分页返回行数totalCount
    int selectOutputMiddleRecordtotalCount(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
    
    //3.1成品记录==============
  	int selectOutputFullRecordtotalCount(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
  	//3.2成品记录==============
  	List<ProductRecord> selectOutputFullRecord(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);

  	
    //4、是否正常（问题是否解决）
 	int updateIsProblem(Integer recordId);
 	
 	 //5.1原材料外协入库单据记录
    List<ProductRecord> selectIntputMaterialAssociation(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);

    //5.2原材料外协入库单据记录返回行数totalCount
    int selectIntputMaterialAssociationtotalCount(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);


    //6.成品记录T+模板excel导出
 	List<ProductRecord> selectexcelOutputFullRecord(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);

 	//7.半成品出库excel导出
 	List<ProductRecord> selectexcelinputMiddleRecord(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
 	
 	//8.半成品入库excel导出
 	List<ProductRecord> selectexceloutputMiddleRecord(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
 	
 	//9.原材料外协入库excel
    List<ProductRecord> selectexcelIntputMaterialAssociation(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
}


