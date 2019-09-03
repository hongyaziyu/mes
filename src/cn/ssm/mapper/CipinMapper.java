package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.Cipin;

public interface CipinMapper {
    int deleteByPrimaryKey(Integer cipinId);

    int insert(Cipin record);

    int insertSelective(Cipin record);

    Cipin selectByPrimaryKey(Integer cipinId);

    int updateByPrimaryKeySelective(Cipin record);

    int updateByPrimaryKey(Cipin record);
    
  //料废数量查询==============================修改
    Cipin selectByTrackId(@Param("trackId")Integer trackId);
    
  //料废和工废数量查询==============================
    List<Cipin> selectWasteByTrackId(@Param("trackId")Integer trackId);
}