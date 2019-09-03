package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.HeightGauge;

public interface HeightGaugeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeightGauge record);

    int insertSelective(HeightGauge record);

    HeightGauge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeightGauge record);

    int updateByPrimaryKey(HeightGauge record);
    
    
    //1.检具-高度规查询分页返回行数totalCount
  	int selectHeightGaugeByParamtotalCount(@Param(value="gaugeNo")String gaugeNo,@Param(value="productSpecification")String productSpecification);
        
    //2.检具-高度规查询分页
   	List<HeightGauge> selectHeightGaugeByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param(value="gaugeNo")String gaugeNo,@Param(value="productSpecification")String productSpecification);

   	//3.excel导入检具-高度规查询
    List<HeightGauge> selectHeightGaugeByParam1(String gaugeNo,String productSpecification);
    
    //4.车间排产显示检具-高度规编号
    List<String> selectHeightGaugeNo();
}