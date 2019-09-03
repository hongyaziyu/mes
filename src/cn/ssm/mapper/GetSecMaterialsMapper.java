package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.GetSecMaterials;
import cn.ssm.vo.InputSec;

public interface GetSecMaterialsMapper {
    int deleteByPrimaryKey(Integer getMaterialsId);

    int insert(GetSecMaterials record);

    int insertSelective(GetSecMaterials record);

    GetSecMaterials selectByPrimaryKey(Integer getMaterialsId);

    int updateByPrimaryKeySelective(GetSecMaterials record);

    int updateByPrimaryKey(GetSecMaterials record);
    
    //1.领辅料审批记录分页查询
    List<GetSecMaterials> selectGetSecMaterialsByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("shopName")String shopName,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
    //2.领辅料审批记录分页查询返回行数totalCount
  	int selectGetSecMaterialsByParamtotalCount(@Param("shopName")String shopName,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
    //3.1添加功能：更新操作（审批）
    int updateByKey(Integer getMaterialsId,String approver);
    
    //3.2添加功能：更新操作（审批不通过）
    int updatenotByKey(Integer getMaterialsId,String approver);
    
    //4.领辅料记录分页
    List<InputSec> selectinputSecGetMaterial(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("shopName")String shopName,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
    //5.领辅料记录分页返回行数totalCount
  	int selectinputSecGetMaterialtotalCount(@Param("shopName")String shopName,@Param("start_date")String start_date,@Param("end_date")String end_date);
    
    //6.查询领辅料未审批数量
    int selectSecGetMaterialCount();
    
    //7.领取辅料（excel导出当日的信息为T+模板）
    List<InputSec> selectexcelinputSecGetMaterial(@Param("shopName")String shopName,@Param("start_date")String start_date,@Param("end_date")String end_date);



}