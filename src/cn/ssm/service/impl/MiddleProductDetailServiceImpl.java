package cn.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ssm.mapper.MiddleProductDetailMapper;
import cn.ssm.po.MiddleProductDetail;
import cn.ssm.service.MiddleProductDetailService;
@Service
public class MiddleProductDetailServiceImpl implements
		MiddleProductDetailService {
	@Autowired
	private MiddleProductDetailMapper middleProductDetailMapper;

	@Override
	public List<MiddleProductDetail> selectDetailById(Integer id) {
		//修改半成品库存详细记录
		return middleProductDetailMapper.selectDetailById(id);
		
	}

}
