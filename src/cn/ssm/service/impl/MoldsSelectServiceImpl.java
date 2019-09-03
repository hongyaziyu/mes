package cn.ssm.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ssm.mapper.MoldsMapper;
import cn.ssm.po.Molds;
import cn.ssm.service.MoldsSelectService;
import cn.ssm.util.DataSource;

//注入service---@Service
@Service
@DataSource("dataSource1")
public class MoldsSelectServiceImpl implements MoldsSelectService {

	//调用Mapper层
	@Autowired
	private MoldsMapper moldsMapper;
	
	
	
	
	//添加的部分:多条件查询Param参数（例如：通过客户，产品，车间查询）
	//模具查询分页
	@Override
	public List<Molds> selectMoldsByParam(int startPos,int pageSize,String mold_name,String mold_no) {
		
		return moldsMapper.selectMoldsByParam(startPos, pageSize,mold_name, mold_no);
		
	}

	
	//模具查询
	@Override
	public List<Molds> selectMoldsByParam1(String mold_name, String mold_no) {
		
		return moldsMapper.selectMoldsByParam1(mold_name, mold_no);
	}
	
    //添加的部分:增加数据信息
	@Override
	public int insert(Molds record){
		int a=0;
		try {
			a=moldsMapper.insert(record);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}

	
	//添加的部分:删除数据信息
	@Override
	public int deleteByPrimaryKey(Integer moldId) {
		
		return moldsMapper.deleteByPrimaryKey(moldId);
	}

	//添加的部分:修改数据信息
	@Override
	public int updateByPrimaryKeySelective(Molds record) {
		int a=0;
		try {
			a=moldsMapper.updateByPrimaryKeySelective(record) ;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}



	@Override
	public Molds selectByPrimaryKey(Integer moldId) {
	
		return moldsMapper.selectByPrimaryKey(moldId);
	}



	



	
}
