package cn.ssm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.ssm.po.SecondaryMaterials;

public interface SecondaryMaterialsMapper {
    int deleteByPrimaryKey(Integer materialsId);

    int insert(SecondaryMaterials record);

    int insertSelective(SecondaryMaterials record);

    SecondaryMaterials selectByPrimaryKey(Integer materialsId);

    int updateByPrimaryKeySelective(SecondaryMaterials record);

    int updateByPrimaryKey(SecondaryMaterials record);
    
    
  //辅料查询分页返回行数totalCount
  	int selectSecByParamtotalCount(@Param(value="secMaterialsName")String secMaterialsName,@Param(value="type")String type);

  //辅料查询分页
   	List<SecondaryMaterials> selectSecByParam(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param(value="secMaterialsName")String secMaterialsName,@Param(value="type")String type);

  //Ajax-excel导入设备查询
	 List<SecondaryMaterials> selectSecByParamAjax(String secMaterialsName,String type);

}