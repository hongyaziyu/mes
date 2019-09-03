package cn.ssm.mapper;

import java.util.List;

import cn.ssm.po.MiddleProductDetail;

public interface MiddleProductDetailMapper {
    int deleteByPrimaryKey(Integer detailId);

    int insert(MiddleProductDetail record);

    int insertSelective(MiddleProductDetail record);

    MiddleProductDetail selectByPrimaryKey(Integer detailId);

    int updateByPrimaryKeySelective(MiddleProductDetail record);

    int updateByPrimaryKey(MiddleProductDetail record);
  //修改半成品库存详细记录
	List<MiddleProductDetail> selectDetailById(Integer id);
	//修改半成品库存详细记录
	int deleteDetailById(Integer id);
}