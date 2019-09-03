package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.GetSecDetail;

public interface GetSecDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(GetSecDetail record);

    int insertSelective(GetSecDetail record);

    GetSecDetail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(GetSecDetail record);

    int updateByPrimaryKey(GetSecDetail record);
    
    //1.领辅料审批表查看功能-根据getMaterialId查询功能
    List<GetSecDetail> selectByKey(Integer getMaterialsId);
    
    //2.退辅料记录分页返回行数totalCount
  	int selectoutputSecGetMaterialtotalCount(@Param("reshopName")String reshopName,@Param("start_date")String start_date,@Param("end_date")String end_date);
 
    //3.退辅料记录分页
    List<GetSecDetail> selectoutputSecGetMaterial(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("reshopName")String reshopName,@Param("start_date")String start_date,@Param("end_date")String end_date);

    //4.退辅料（excel导出T+模板）
    List<GetSecDetail> selectexceloutputSecGetMaterial(@Param("reshopName")String reshopName,@Param("start_date")String start_date,@Param("end_date")String end_date);
}