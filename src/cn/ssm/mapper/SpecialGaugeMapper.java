package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.SpecialGauge;

public interface SpecialGaugeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecialGauge record);

    int insertSelective(SpecialGauge record);

    SpecialGauge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecialGauge record);

    int updateByPrimaryKey(SpecialGauge record);
    
    //1.检具-特殊规查询分页返回行数totalCount
  	int selectSpecialGaugeByParamtotalCount(@Param(value="gaugeName")String gaugeName,@Param(value="gaugeNo")String gaugeNo);
        
    //2.检具-特殊规查询分页
   	List<SpecialGauge> selectSpecialGaugeByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param(value="gaugeName")String gaugeName,@Param(value="gaugeNo")String gaugeNo);

   	//3.excel导入检具-特殊规查询
    List<SpecialGauge> selectSpecialGaugeByParam1(String gaugeName,String gaugeNo);
    
    //4.车间排产显示检具-特殊规编号
    List<String> selectSpecialGaugeNo();
}