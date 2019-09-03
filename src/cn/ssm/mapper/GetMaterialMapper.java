package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.GetMaterial;
import cn.ssm.vo.Input;
import cn.ssm.vo.Output;


public interface GetMaterialMapper {
    int deleteByPrimaryKey(Integer getMaterialId);

    int insert(GetMaterial record);

    int insertSelective(GetMaterial record);

    GetMaterial selectByPrimaryKey(Integer getMaterialId);

    int updateByPrimaryKeySelective(GetMaterial record);

    int updateByPrimaryKey(GetMaterial record);
    
  //1、领料表
    //添加内容：根据物料号、起始日期、截止日期查询
    //根据物料号、起始日期、截止日期查询
  	//领料审批记录分页查询
    List<GetMaterial> selectGetMaterialByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
    
  //领料审批记录分页查询返回行数totalCount
  	int selectGetMaterialByParamtotalCount(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
    
    //添加功能：更新操作（审批）
    int updateByKey(Integer getMaterialId,String approver);
    //添加功能：更新操作（审批不通过）
    int updatenotByKey(Integer getMaterialId,String approver);
    
    
    //2.领原材料\退原材料记录
    //2.1领原材料记录
    //添加内容：根据物料号、起始日期、截止日期、（隐藏条件是否领料is_return=0领料）查询
  //领原材料记录分页
    List<Input> selectinputGetMaterial(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
  ////领原材料记录分页返回行数totalCount
  	int selectinputGetMaterialtotalCount(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
   //2.2退原材料记录
  //退原材料记录查询分页
    //添加内容：根据物料号、起始日期、截止日期、（隐藏条件是否领料？is_return=1退料）查询
    List<Output> selectoutputGetMaterial(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
  ///退原材料记录分页返回行数totalCount
  	int selectoutputGetMaterialtotalCount(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
    //3.领原材料、退原材料查看
    //3.1领原材料查看
    List<Input> selectId(Integer getMaterialId,Integer detailId);
    
    //3.2退原材料查看
    List<Output> selectById(Integer getMaterialId,Integer detailId);
    
    
    //4.Ajax检查该批次号是否已经用过
    List<GetMaterial> selectBatchNoIsNull(String batchNo);
    
    
    //5.查询领原材料未审批数量
    int selectGetMaterialCount();
    
    //6.原材料外协出库记录分页
    List<Input> selectMaterialAssociation(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);

    //7.领原材料记录分页返回行数totalCount
  	int selectMaterialAssociationtotalCount(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);

  	//8.1领原材料T+系统模板excel导出（除了外协的原材料出库）
  	List<Input> selectRowinMaterial(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
  
  	//8.2退原材料T+系统模板excel导出（除了外协的原材料出库）
  	List<Output> selectRowoutMaterial(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
  	
  	//9.更新领料表的信息-根据批次号查询主键getMaterialId
    Integer selectBatchNotogetMaterialId(String batchNo);
    
    //10.原材料外协出库excel导查询
    List<Input> selectexcelinputGetMaterial(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
}