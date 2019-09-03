package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.MoldRecord;

public interface MoldRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(MoldRecord record);

    int insertSelective(MoldRecord record);

    MoldRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(MoldRecord record);

    int updateByPrimaryKey(MoldRecord record);
    
  ////模具出入库记录分页查询返回行数totalCount
  	int selectByPrimarytotalCount(@Param("material_no")String materialNo,@Param("batch_no")String batchNo,@Param("mold_no")String moldNo);
    
    
    //查询（根据图号，物料号，模具名称）
  //模具出入库记录分页查询
    List<MoldRecord> selectByPrimary(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String materialNo,@Param("batch_no")String batchNo,@Param("mold_no")String moldNo);
}