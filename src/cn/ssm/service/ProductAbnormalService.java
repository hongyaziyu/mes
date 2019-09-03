package cn.ssm.service;

import java.util.List;
import cn.ssm.po.DepOpinion;
import cn.ssm.po.ProductAbnormal;

public interface ProductAbnormalService {
	
	
	//	查询
	//产品异常单记录分页
	List<ProductAbnormal> selectProductAbnormalByParam(int startPos,int pageSize, String client,String material_no,String start_date, String end_date);

	
	 
	 
	//添加

	void insertProductAbnormal(ProductAbnormal productAbnormal);
	void insertAbnormalId(List<DepOpinion> listDepOpinion, Integer abnormalId);

	 
	//修改	 
	 ProductAbnormal selectByPrimaryKey(Integer abnormalId);
	 List<DepOpinion> selectByAbnormalId(Integer abnormalId);
	 
	
	 int updateByPrimaryKeySelective(ProductAbnormal productAbnormal);
	 void deleteByAbnormalId(Integer abnormalId);
	 void updateAbnormalId(List<DepOpinion> listDepOpinion, Integer abnormalId);




	



	





	





	




	

 

	
	
	
	 
}
