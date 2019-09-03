package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.DailyCheck;

public interface DailyCheckMapper {
	int deleteByPrimaryKey(Integer checkId);

	int insert(DailyCheck record);

	int insertSelective(DailyCheck record);

	DailyCheck selectByPrimaryKey(Integer checkId);

	int updateByPrimaryKeySelective(DailyCheck record);

	int updateByPrimaryKey(DailyCheck record);

	// 查询返回行数totalCount
	int selectByPrimarytotalCount(@Param("batchNo") String batchNo,
			@Param("processName") String processName,
			@Param("assetNo") String assetNo);

	List<DailyCheck> selectDailyCheckByParam(@Param("startPos") int startPos,
			@Param("pageSize") int pageSize, @Param("batchNo") String batchNo,
			@Param("processName") String processName,
			@Param("assetNo") String assetNo);

	// 故障查询
	int selectByPrimarytotalCount1(@Param("batchNo") String batchNo,
			@Param("processName") String processName,
			@Param("assetNo") String assetNo);

	List<DailyCheck> selectBreakdownByParam(@Param("startPos") int startPos,
			@Param("pageSize") int pageSize, @Param("batchNo") String batchNo,
			@Param("processName") String processName,
			@Param("assetNo") String assetNo);

}