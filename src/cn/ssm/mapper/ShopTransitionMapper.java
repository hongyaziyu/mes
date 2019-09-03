package cn.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.ssm.po.ShopTransition;

public interface ShopTransitionMapper {
    int deleteByPrimaryKey(Integer transitionId);

    int insert(ShopTransition record);

    int insertSelective(ShopTransition record);

    ShopTransition selectByPrimaryKey(Integer transitionId);

    int updateByPrimaryKeySelective(ShopTransition record);

    int updateByPrimaryKey(ShopTransition record);
    
    //1、计划进度查询
  	List<ShopTransition> selectShopTransitionByParam(@Param(value="planNo")String plan_no,@Param(value="clientMaterialNo")String client_material_no,@Param(value="materialNo")String material_no);

  	//2、计划进度弹窗
  	List<ShopTransition> selectShopTransitionByParam1(@Param(value="planNo")String plan_no,@Param(value="clientMaterialNo")String client_material_no,@Param(value="materialNo")String material_no);


    //3、部分外协单据分页查询
  	List<ShopTransition> selectExterAssociation(@Param("startPos")int startPos, @Param("pageSize")int pageSize,@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);

  	//4、部分外协分页行数totalCount
  	int selectExterAssociationtotalCount(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);
  	
  	//5、部分外协单据excel查询
  	List<ShopTransition> selectexcelExterAssociation(@Param("material_no")String material_no,@Param("start_date")String start_date,@Param("end_date")String end_date);

}