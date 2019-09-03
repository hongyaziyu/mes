package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;





import cn.ssm.po.CheckContent;

public interface CheckContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckContent record);

    int insertSelective(CheckContent record);

    CheckContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckContent record);

    int updateByPrimaryKey(CheckContent record);
    
    //1.1设备点检内容查询分页返回行数totalCount
  	int selectAssetCheckRecordtotalCount(@Param(value="assetName")String assetName);
     
    //1.2设备点检内容查询分页
   	List<CheckContent> selectAssetCheckRecord(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param(value="assetName")String assetName);

   	//2.更新-先查询设备点检内容
    List<CheckContent> selectAssetCheckRecordByParm(String assetName);
    
    //3.删除设备点检内容
    int deleteAssetCheckRecord(String assetName);
    
    //4.excel导入-查询是否存在该条信息
    List<CheckContent> selectAssetCheckRecordExcel(String assetName,String result);
}