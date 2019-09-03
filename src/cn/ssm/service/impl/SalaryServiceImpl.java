package cn.ssm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.CipinMapper;
import cn.ssm.mapper.TrackCardMapper;
import cn.ssm.mapper.WorkCardMapper;
import cn.ssm.po.Cipin;
import cn.ssm.po.WorkCard;
import cn.ssm.service.SalaryService;
import cn.ssm.util.DataSource;
import cn.ssm.vo.Salary;

@Service
@DataSource("dataSource1")
public class SalaryServiceImpl implements SalaryService{

	
	//mapper层的自动注入
	@Autowired
	private WorkCardMapper workCardMapper;
	@Autowired
	private TrackCardMapper trackCardMapper;
	@Autowired
	private CipinMapper cipinMapper;
	  //1.工资信息查询
		 //工资单记录查询分页
		@Override
		public List<WorkCard> SelectByPrimary(int startPos, int pageSize,String operator,String shop_name, String date) {
			
			//料废数量查询==============================修改
			List<WorkCard> listWorkCard = new ArrayList<WorkCard>() ;
			
			listWorkCard=workCardMapper.SelectByPrimary(startPos, pageSize,operator,shop_name, date);
			
					for(int i = 0; i < listWorkCard.size(); i++){
						Cipin cipin = cipinMapper.selectByTrackId(listWorkCard.get(i).getTrackId());
					    String totalCipinNum =cipin.getCipinNum();
						listWorkCard.get(i).setTotalCipinNum(totalCipinNum);
						
					}
					
			return listWorkCard;
		}

		//3.2工资单记录excel查询分页

		@Override
		public List<WorkCard> SelectexcelByPrimary(String operator, String shop_name, String date) {

			//料废数量查询==============================修改
			List<WorkCard> listWorkCard = new ArrayList<WorkCard>() ;
			
			listWorkCard=workCardMapper.SelectexcelByPrimary(operator,shop_name, date);
			
					for(int i = 0; i < listWorkCard.size(); i++){
						Cipin cipin = cipinMapper.selectByTrackId(listWorkCard.get(i).getTrackId());
					    String totalCipinNum =cipin.getCipinNum();
						listWorkCard.get(i).setTotalCipinNum(totalCipinNum);
						
					}
					
			return listWorkCard;
		}
		
		
		
		
		
	   //2.工资详细信息查询
		//工资单详细记录分页
		@Override
		public List<Salary> SelectByPrimaryDate(int startPos, int pageSize,String shop_name, String operator,
				String date) {
			//料废数量查询==============================修改
			List<Salary> listSalary= new ArrayList<Salary>();
			
			listSalary= trackCardMapper.SelectByPrimaryDate(startPos, pageSize,shop_name, operator, date);
			
			for(int i = 0; i < listSalary.size(); i++){
			  Integer totalCipinNum=0;
			  List<Cipin> listcipin = cipinMapper.selectWasteByTrackId(listSalary.get(i).getTrackId());
			  List<Cipin> listCipin1 =new ArrayList<Cipin>();
			  for(int j = 0; j < listcipin.size(); j++){
				
				  
				 
				  String cipinType = listcipin.get(j).getCipinType();
				  if("料废".equals(cipinType)){
					  
					  totalCipinNum+=Integer.parseInt(listcipin.get(j).getCipinNum());
					  
				  }else{
					  
					  Cipin cipin=new Cipin();
					 
					  String cipinSpecies = listcipin.get(j).getCipinSpecies(); 
					  cipin.setCipinSpecies(cipinSpecies);
					  String cipinNum = listcipin.get(j).getCipinNum();
					  cipin.setCipinNum(cipinNum);
					  listCipin1.add(cipin);
				  }
				  
				  
			  }
			  
			  listSalary.get(i).setCipin(listCipin1);
			  listSalary.get(i).setTotalCipinNum(totalCipinNum.toString()); 
				
			}
			 
			 return listSalary;
		}

		//3.工资单详细记录excel数据查询
		@Override
		public List<Salary> SelectexcelByPrimaryDate(String shop_name,
				String operator, String date) {
			//料废数量查询==============================修改
			List<Salary> listSalary= new ArrayList<Salary>();
			listSalary= trackCardMapper.SelectexcelByPrimaryDate(shop_name, operator, date);
			
			for(int i = 0; i < listSalary.size(); i++){
			  Integer totalCipinNum=0;
			  List<Cipin> listcipin = cipinMapper.selectWasteByTrackId(listSalary.get(i).getTrackId());
			  List<Cipin> listCipin1 =new ArrayList<Cipin>();
			  for(int j = 0; j < listcipin.size(); j++){
				
				  
				 
				  String cipinType = listcipin.get(j).getCipinType();
				  if("料废".equals(cipinType)){
					  
					  totalCipinNum+=Integer.parseInt(listcipin.get(j).getCipinNum());
					  
				  }else{
					  
					  Cipin cipin=new Cipin();
					 
					  String cipinSpecies = listcipin.get(j).getCipinSpecies(); 
					  cipin.setCipinSpecies(cipinSpecies);
					  String cipinNum = listcipin.get(j).getCipinNum();
					  cipin.setCipinNum(cipinNum);
					  listCipin1.add(cipin);
				  }
				  
				  
			  }
			  
			  listSalary.get(i).setCipin(listCipin1);
			  listSalary.get(i).setTotalCipinNum(totalCipinNum.toString()); 
				
			}
			 
			 return listSalary;
		}

}
