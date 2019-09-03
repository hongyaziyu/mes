package cn.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.GetSecDetailMapper;
import cn.ssm.mapper.GetSecMaterialsMapper;
import cn.ssm.mapper.SecondaryMaterialsMapper;
import cn.ssm.po.GetSecDetail;
import cn.ssm.po.GetSecMaterials;
import cn.ssm.po.SecondaryMaterials;
import cn.ssm.service.SecMaterialService;
import cn.ssm.util.DataSource;
import cn.ssm.vo.InputSec;

@Service
@DataSource("dataSource1")
public class SecMaterialServiceImpl implements SecMaterialService {
 
	
	@Autowired
	private SecondaryMaterialsMapper secondaryMaterialsMapper;
	@Autowired
	private GetSecMaterialsMapper getSecMaterialsMapper;
	@Autowired
	private GetSecDetailMapper getSecDetailMapper;
	
	//1.辅料查询分页
	@Override
	public List<SecondaryMaterials> selectSecByParam(int startPos,
			int pageSize, String secMaterialsName, String type) {
		
		return secondaryMaterialsMapper.selectSecByParam(startPos, pageSize, secMaterialsName, type);
	}

	//2.插入辅料信息
	@Override
	public int insert(SecondaryMaterials record) {
		int a=0;
		try {
			a=secondaryMaterialsMapper.insertSelective(record);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}

	//3.删除辅料信息
	@Override
	public int deleteByPrimaryKey(Integer materialsId) {
		
		return secondaryMaterialsMapper.deleteByPrimaryKey(materialsId);
	}

	
	//4.更新辅料信息
	
	//4.1回显操作
		@Override
		public SecondaryMaterials selectByPrimaryKey(Integer materialsId) {
		
			return secondaryMaterialsMapper.selectByPrimaryKey(materialsId);
		}
		
		
	//4.2更新操作
	@Override
	public int updateByPrimaryKeySelective(SecondaryMaterials record) {
		int a=0;
		try {
			a=secondaryMaterialsMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return a;
	}

	
	//5.Ajax-excel导入设备查询
	@Override
	public List<SecondaryMaterials> selectSecByParamAjax(
			String secMaterialsName, String type) {
		
		return secondaryMaterialsMapper.selectSecByParamAjax(secMaterialsName, type);
	}

	//6.领料审批记录分页查询
		@Override
		public List<GetSecMaterials> selectGetSecMaterialsByParam(int startPos, int pageSize,String shopName,String start_date,String end_date) {
			
			return getSecMaterialsMapper.selectGetSecMaterialsByParam(startPos, pageSize, shopName, start_date, end_date);
		}
		
	//7.1更新操作（审批）
		@Override
		public int updateByKey(Integer getMaterialsId, String approver) {
			
			return getSecMaterialsMapper.updateByKey(getMaterialsId, approver);
		}
	 
		
	//7.2更新操作（审批不通过）
		@Override
		public int updatenotByKey(Integer getMaterialsId, String approver) {
			
			return getSecMaterialsMapper.updatenotByKey(getMaterialsId, approver);
		}
		
	//8.查看
		@Override
		public GetSecMaterials selectGetSecMaterialsKey(Integer getMaterialsId) {
			
			return  getSecMaterialsMapper.selectByPrimaryKey(getMaterialsId);
		}

		@Override
		public List<GetSecDetail> selectByKey(Integer getMaterialsId) {
			
			return  getSecDetailMapper.selectByKey(getMaterialsId);
		}
		 
		
	   //9.领辅料查询：根据车间、起始日期、截止日期、（隐藏条件是否领料？is_return=0）查询
		@Override
		public List<InputSec> selectinputSecGetMaterial(int startPos,
				int pageSize, String shopName, String start_date,
				String end_date) {
			
			return getSecMaterialsMapper.selectinputSecGetMaterial(startPos, pageSize, shopName, start_date, end_date);
		}
		
		 //10.退辅料记录分页
		@Override
		public List<GetSecDetail> selectoutputSecGetMaterial(int startPos,
				int pageSize, String reshopName, String start_date,
				String end_date) {
			
			return getSecDetailMapper.selectoutputSecGetMaterial(startPos, pageSize, reshopName, start_date, end_date);
		}
		
		
		//11.领取辅料（excel导出T+模板）
		@Override
		public List<InputSec> selectexcelinputSecGetMaterial(String shopName,String start_date,String end_date) {
			
			return getSecMaterialsMapper.selectexcelinputSecGetMaterial(shopName, start_date, end_date);
		}
		
		//12.退辅料（excel导出T+模板）
		@Override
		public List<GetSecDetail> selectexceloutputSecGetMaterial(String reshopName,String start_date,String end_date) {
				List<GetSecDetail> listsecoutMaterial=new ArrayList<GetSecDetail>();
				listsecoutMaterial=	getSecDetailMapper.selectexceloutputSecGetMaterial(reshopName, start_date, end_date);
		return listsecoutMaterial;
		}

		//13.1删除领辅料信息主表
		@Override
		public int deleteInputSecGetMaterialRecord(Integer getMaterialsId) {
		
			return getSecMaterialsMapper.deleteByPrimaryKey(getMaterialsId);
		}
		//13.2删除领辅料信息副表
		@Override
		public int deleteInputSecGetdetailRecord(Integer detailId) {
			
			return getSecDetailMapper.deleteByPrimaryKey(detailId);
		}

}
