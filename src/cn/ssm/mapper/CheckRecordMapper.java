package cn.ssm.mapper;

import java.util.List;

import cn.ssm.po.CheckRecord;

public interface CheckRecordMapper {
    int deleteByPrimaryKey(Integer recordId);

    int insert(CheckRecord record);

    int insertSelective(CheckRecord record);

    CheckRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(CheckRecord record);

    int updateByPrimaryKey(CheckRecord record);
    
    List<CheckRecord> selectCheckRecordByKey(Integer checkId);
}