package cn.ssm.service;

import java.util.List;

import cn.ssm.po.MiddleProductDetail;

public interface MiddleProductDetailService {
	
	//修改半成品库存详细记录

	List<MiddleProductDetail> selectDetailById(Integer id);

}
