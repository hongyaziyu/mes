package cn.ssm.service;

import java.util.List;

import cn.ssm.po.MoldRecord;

public interface MoldRecordService {

	//查询（根据图号，物料号，模具名称）
		//模具出入库记录分页查询
	    List<MoldRecord> selectByPrimary(int startPos,int pageSize, String materialNo,
				String batchNo,String moldNo);
	}