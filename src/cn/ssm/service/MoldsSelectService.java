package cn.ssm.service;

import java.util.List;
import cn.ssm.po.Molds;




public interface MoldsSelectService {

	
	
//添加的部分：根据设备的名字和编号进行查询的servicece接口
	//模具查询分页
	List<Molds> selectMoldsByParam(int startPos,int pageSize,String mold_name,String mold_no);

   //模具查询
		 List<Molds> selectMoldsByParam1(String mold_name,String mold_no);

//添加的部分：更新设备信息的servicece接口
	int insert(Molds record);
	
//添加的部分：删除设备信息的servicece接口	
	int deleteByPrimaryKey(Integer moldId);
	
//添加的部分：更新设备信息的servicece接口		
	int updateByPrimaryKeySelective(Molds record);
	
	 Molds selectByPrimaryKey(Integer moldId);

	 
	 
}
