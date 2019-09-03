package cn.ssm.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ssm.mapper.AaBomMapper;
import cn.ssm.mapper.AaInventoryMapper;
import cn.ssm.mapper.AaPartnerMapper;
import cn.ssm.mapper.AaUnitMapper;
import cn.ssm.mapper.MiddleProductMapper;
import cn.ssm.mapper.SaSaleorderMapper;
import cn.ssm.mapper.StCurrentstockMapper;
import cn.ssm.mapper.StRdrecordMapper;
import cn.ssm.po.AaInventory;
import cn.ssm.po.MiddleProduct;
import cn.ssm.po.SaSaleorder;
import cn.ssm.service.TJdbcService;
import cn.ssm.util.DataSource;
import cn.ssm.vo.ProductInventory;
import cn.ssm.vo.SaleOrder;
import cn.ssm.vo.SaleOrderDetail;

@Service
@DataSource("dataSource2")
public class TJdbcServiceImpl implements TJdbcService {

	@Autowired
	private SaSaleorderMapper saSaleorderMapper;
	@Autowired
	private AaPartnerMapper aaPartnerMapper;
	@Autowired
	private AaInventoryMapper aaInventoryMapper;
	@Autowired
	private AaUnitMapper aaUnitMapper;
	@Autowired
	private AaBomMapper aaBomMapper;
	@Autowired
	private MiddleProductMapper middleProductMapper;
	//StCurrentstock视图表（可以逆向工程但是表实际不存在）
	@Autowired
	private StCurrentstockMapper stCurrentstockMapper;
	@Autowired
	private StRdrecordMapper stRdrecordMapper;
	//1、订单记录查询
	@Override
	public List<SaleOrder> selectSaleOrder(int startPos, int pageSize,
			String code, String start_date, String end_date) {
		
		List<SaleOrder> listSaleOrder=new ArrayList<SaleOrder>();
		listSaleOrder=saSaleorderMapper.selectSaleOrder(startPos, pageSize, code, start_date, end_date);
		//根据idcustomer查询AA_parnter表中的客户名称
		for(int i=0;i<listSaleOrder.size();i++){
			Integer idcustomer=listSaleOrder.get(i).getIdcustomer();
			String client=aaPartnerMapper.selectCustomer(idcustomer);
			listSaleOrder.get(i).setClient(client);
		}
		
		//将格林威治时间转化为正常标准时间
		for(int i=0;i<listSaleOrder.size();i++){
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 String madedate = sdf.format(listSaleOrder.get(i).getMadedate());
			 String auditeddate = sdf.format(listSaleOrder.get(i).getAuditeddate());
			 listSaleOrder.get(i).setMadedate1(madedate);
			 listSaleOrder.get(i).setAuditeddate1(auditeddate);
			 
		}
		return listSaleOrder;
	}

	
	//2、1 查看详细信息-基础信息
	@Override
	public SaleOrder selectOrderetail(Integer id) {
		SaSaleorder saSaleorder=new SaSaleorder();
		saSaleorder=saSaleorderMapper.selectOrderdetail(id);
		//1)获得制单日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String madedate = sdf.format(saSaleorder.getMadedate());
		//2)获得客户名称
		Integer idcustomer=saSaleorder.getIdcustomer();
		String client=aaPartnerMapper.selectCustomer(idcustomer);
		//3)放到SaleOrder类中
		SaleOrder saleOrder=new SaleOrder();
		saleOrder.setMadedate1(madedate);
		saleOrder.setClient(client);
		saleOrder.setCode(saSaleorder.getCode());
		saleOrder.setMaker(saSaleorder.getMaker());
		return saleOrder;
	}

	
	//2、2、查看详细信息-具体信息
	@Override
	public List<SaleOrderDetail> selectSaleOrderdetail(Integer id) {
		List<SaSaleorder> listsaSaleorder=saSaleorderMapper.selectSaleOrderdetail(id);
		List<SaleOrderDetail> listsaleOrderDetail=new ArrayList<SaleOrderDetail>();
		
		for(int i=0;i<listsaSaleorder.size();i++){
			Integer idinventory=listsaSaleorder.get(i).getIdinventory();
			AaInventory aaInventory=aaInventoryMapper.selectSaleOrder(idinventory);
			String clientMaterialNo=aaInventory.getName();
			String materialNo=aaInventory.getCode();
			String productSpec=aaInventory.getSpecification();
			String productName=aaInventory.getInventorydescript();		
			BigDecimal ordernum=listsaSaleorder.get(i).getQuantity();
			Integer idbaseunit=listsaSaleorder.get(i).getIdbaseunit();
			String unit=aaUnitMapper.selectunitname(idbaseunit);
			//新建一个类去接收数据
			SaleOrderDetail saleOrderDetail=new SaleOrderDetail();
			saleOrderDetail.setClientMaterialNo(clientMaterialNo);
			saleOrderDetail.setMaterialNo(materialNo);
			saleOrderDetail.setProductName(productName);
			saleOrderDetail.setProductSpec(productSpec);
			saleOrderDetail.setOrdernum(ordernum);
			saleOrderDetail.setUnit(unit);
			//将类添加到集合中
			listsaleOrderDetail.add(saleOrderDetail);
		} 
		
		return listsaleOrderDetail;
	}
	
	//3、Ajax-周计划-检查是否有该订单号
	@Override
	//Boolean数值型，类型为True或者False
	public Boolean selectOrderNoIsNull(String orderNo) {
		List<SaSaleorder> list=new ArrayList<SaSaleorder>();
		list=saSaleorderMapper.selectOrderNoIsNull(orderNo);
		if(list.size()<1){
			return false;
		}else{
			return true;
		}
		
	}

	//4、Ajax-周计划-根据订单号查询客户信息
	@Override
	public SaleOrder selectClient(String orderNo) {
		SaSaleorder saSaleorder=new SaSaleorder();
		saSaleorder=saSaleorderMapper.selectClient(orderNo);
		//获得客户名称
		Integer idcustomer=saSaleorder.getIdcustomer();
		String client=aaPartnerMapper.selectCustomer(idcustomer);
		//放到SaleOrder类中
		SaleOrder saleOrder=new SaleOrder();
		saleOrder.setClient(client);
		return saleOrder;
	}

    //5、Ajax-周计划-根据订单号得到图号下拉显示
	@Override
	public String selectClientMaterialNo(String orderNo) {
		List<String> list=saSaleorderMapper.selectClientMaterialNo(orderNo);
		List<String> list1=new ArrayList<String>();
		List<String> list2=new ArrayList<String>();
		for(int j=0;j<list.size();j++){
			Integer idinventory=Integer.valueOf(list.get(j));
			AaInventory aaInventory=aaInventoryMapper.selectSaleOrder(idinventory);	
			list1.add(aaInventory.getName());
			if(aaInventory.getInventorydescript()==null){
				list2.add("未录入信息");	
			}else{
				list2.add(aaInventory.getInventorydescript());
			}
			
		}
				
		String html="<option disabled selected style='display: none;'>--请选择--</option>";
		//循环遍历获得物料号，并将value值返回option前端下拉框
				for(int i=0;i<list1.size();i++){
					//回显value到jsp页面上
			        html+="<option value="+list1.get(i)+">"+list1.get(i)+"("+list2.get(i)+")"+"</option>";
					
		              }
		return html;
	}


	//6、Ajax-周计划-根据订单号、物料号得到具体的产品订单数量
	@Override
	public String selectOrdernum(String orderNo, String materialNo) {
		AaInventory aaInventory=aaInventoryMapper.selectInventoryid(materialNo);
		Integer inventoryid=aaInventory.getId();
		SaSaleorder saSaleorder=saSaleorderMapper.selectOrdernum(orderNo, inventoryid);
		String ordernum=null;
		if(saSaleorder!=null&&!("".equals(saSaleorder))){
		 ordernum=saSaleorder.getCompositionquantity();
		}else{
		 ordernum="/";
		}
		
		return ordernum;
	}


	//7、根据物料号显示原材料库存信息
	@Override
	public List<ProductInventory> selectInventoryNum(String materialNo) {
		//用于接收库存数量的类
		List<ProductInventory> listProductInventory=new ArrayList<ProductInventory>();
		
		List<Integer> list=new ArrayList<Integer>();
		//根据物料号查询子件存货id
		list=aaBomMapper.selectZiIdInventory(materialNo);
	
		for(int j=0;j<list.size();j++){
			ProductInventory productInventory=new ProductInventory();
			//原材料库存数量(条件：ST_CurrentStock.idwarehouse= N'1') --仓库)
			Integer num=stCurrentstockMapper.selectMaterialInventoryNum(list.get(j));
			if(num!=null&&!("".equals(num))){
				productInventory.setProductNum(String.valueOf(num));
			}else{
				productInventory.setProductNum(0+"");
			}
			
			//类加入集合中
			listProductInventory.add(productInventory);
		}
		return listProductInventory;
	}
	
	
	/*//根据订单号,图号显示库存信息(详细思路-保存！！！)
	@Override
	public List<ProductInventory> selectInventoryMessage(String orderNo,String clientMaterialNo) {
		
		//订单号得到所有成品的存货id
		List<Integer> listFuLLALLidinventory=saSaleorderMapper.selectOrderNoToIdInventory(orderNo);

		//根据图号得到所有的物料号
		List<String> listMaterialNo=aaBomMapper.selectAaBom(clientMaterialNo);
		
		//1.根据物料号得到所有母件bom的存货id
		List<Integer> listMuBomidinventory=new ArrayList<Integer>();
		for(int i=0;i<listMaterialNo.size();i++){
			Integer id=aaBomMapper.selectMuIdInventory(listMaterialNo.get(i));
			listMuBomidinventory.add(id);
		}
		
		//2.根据物料号得到所有子件bom的存货id,一个物料号下的子件为多个要用list接收
		List<Integer> listZiBomidinventory=new ArrayList<Integer>();
		for(int i=0;i<listMaterialNo.size();i++){
			List<Integer> list=new ArrayList<Integer>();
			list=aaBomMapper.selectZiIdInventory(listMaterialNo.get(i));
			listZiBomidinventory.addAll(list);
		}
			
		//1)该图号下的成品的存货id集合
			Set<Integer> setFuLLidinventory=new HashSet<Integer>();
		//2)该图号下的半成品的存货id集合
			Set<Integer> setMiddleidinventory=new HashSet<Integer>();
		//3)该图号下的原材料的存货id集合
			Set<Integer> setMaterialidinventory=new HashSet<Integer>();
			
		//I.遍历所有母件的存货id，将其分开为成品的存货id和半成品的存货id
		for(int i=0;i<listMuBomidinventory.size();i++){
			//母件中的存货id如果包含在该订单号下的所有成品的存货id中，则该母件存货id为成品存货id，否则为半成品存货id
			if(listFuLLALLidinventory.contains(listMuBomidinventory.get(i))){
				setFuLLidinventory.add(listMuBomidinventory.get(i));
			}else{
				setMiddleidinventory.add(listMuBomidinventory.get(i));
			}
			
		}
		
		
		//II.遍历所有子件的存货id，将其分开为半成品的存货id和原材料的存货id
		 for(int i=0;i<listZiBomidinventory.size();i++){
			//子件中的存货id如果包含在半成品的存货id中，则该子件存货id为半成品存货id，否则为原材料存货id
			 if(setMiddleidinventory.contains(listZiBomidinventory.get(i))==false){
				 setMaterialidinventory.add(listZiBomidinventory.get(i));
			 }
		 }
		
		 */
		
		
		/*List<ProductInventory> listProductInventory=new ArrayList<ProductInventory>();
		
		//增强for循环遍历Set集合
		for(Integer idinventory:setIdinventory){
			ProductInventory productInventory=new ProductInventory();
			AaInventory aaInventory=aaInventoryMapper.selectSaleOrder(idinventory);
			String productSpec=aaInventory.getSpecification();
			String productName=aaInventory.getInventorydescript();
			//原材料的名称在name字段，若Inventorydescript为空就是原材料，使用name代替
			String productName1=null;
			if(productName==null||productName.equals("")){
				productName1=aaInventory.getName();
			}else{
				productName1=productName;
			}
			Integer idunit=aaInventory.getIdunit();
			String unit=aaUnitMapper.selectunitname(idunit);
			//库存数量(暂时查询没有清楚那张表，暂时做到idinventory查询StCurrentOld表的basequanity字段)
			Integer num=stCurrentstockMapper.selectInventoryNum(idinventory);
				if(num!=null&&!("".equals(num))){
					productInventory.setProductNum(num);
				}else{
					productInventory.setProductNum(0);
				}
			productInventory.setProductName(productName1);
			productInventory.setProductSpec(productSpec);
			productInventory.setUnit(unit);
			listProductInventory.add(productInventory);
		  }
		//return listProductInventory;
			return null;
	}*/

	


		//8.1根据订单号,图号显示成品库存信息
		@Override
		public List<ProductInventory> selectFullInventoryMessage(String orderNo,
				String clientMaterialNo) {
			
			//订单号得到所有成品的存货id
			List<Integer> listFuLLALLidinventory=saSaleorderMapper.selectOrderNoToIdInventory(orderNo);
			
			//根据图号得到所有的物料号
			List<String> listMaterialNo=aaBomMapper.selectAaBom(clientMaterialNo);
			
			//1.根据物料号得到所有母件bom的存货id
			List<Integer> listMuBomidinventory=new ArrayList<Integer>();
			for(int i=0;i<listMaterialNo.size();i++){
				Integer id=aaBomMapper.selectMuIdInventory(listMaterialNo.get(i));
				listMuBomidinventory.add(id);
			}
			
			//1)该图号下的成品的存货id集合
			Set<Integer> setFuLLidinventory=new HashSet<Integer>();
			
			//I.遍历所有母件的存货id，将其分开为成品的存货id和半成品的存货id
			for(int i=0;i<listMuBomidinventory.size();i++){
				//母件中的存货id如果包含在该订单号下的所有成品的存货id中，则该母件存货id为成品存货id，否则为半成品存货id
				if(listFuLLALLidinventory.contains(listMuBomidinventory.get(i))){
					setFuLLidinventory.add(listMuBomidinventory.get(i));
				}
	         }
			
			List<ProductInventory> listFullProductInventory=new ArrayList<ProductInventory>();
			
			//增强for循环遍历Set集合
			for(Integer idinventory:setFuLLidinventory){
				ProductInventory productInventory=new ProductInventory();
				AaInventory aaInventory=aaInventoryMapper.selectSaleOrder(idinventory);
				String productSpec=aaInventory.getSpecification();
				String productName=aaInventory.getInventorydescript();
				Integer idunit=aaInventory.getIdunit();
				String unit=aaUnitMapper.selectunitname(idunit);
				//成品库存数量(条件：ST_CurrentStock.idwarehouse= N'2') --仓库)
				Integer num=stCurrentstockMapper.selectFULLInventoryNum(idinventory);
					if(num!=null&&!("".equals(num))){
						productInventory.setProductNum(String.valueOf(num));
					}else{
						productInventory.setProductNum(0+"");
					}
				productInventory.setProductName(productName);
				productInventory.setProductSpec(productSpec);
				productInventory.setUnit(unit);
				listFullProductInventory.add(productInventory);
			  }
			return listFullProductInventory;
		}



	//8.2根据订单号,图号显示原材料库存信息
	@Override
	public List<ProductInventory> selectMaterialInventoryMessage(
			String orderNo, String clientMaterialNo) {
		//订单号得到所有成品的存货id
		List<Integer> listFuLLALLidinventory=saSaleorderMapper.selectOrderNoToIdInventory(orderNo);
		
		//根据图号得到所有的物料号
		List<String> listMaterialNo=aaBomMapper.selectAaBom(clientMaterialNo);
		
		
		//1.根据物料号得到所有母件bom的存货id
		List<Integer> listMuBomidinventory=new ArrayList<Integer>();
		for(int i=0;i<listMaterialNo.size();i++){
			Integer id=aaBomMapper.selectMuIdInventory(listMaterialNo.get(i));
			listMuBomidinventory.add(id);
		}
		
		//2.根据物料号得到所有子件bom的存货id,一个物料号下的子件为多个要用list接收
		List<Integer> listZiBomidinventory=new ArrayList<Integer>();
		for(int i=0;i<listMaterialNo.size();i++){
			List<Integer> list=new ArrayList<Integer>();
			list=aaBomMapper.selectZiIdInventory(listMaterialNo.get(i));
			listZiBomidinventory.addAll(list);
		}
			
		//1)该图号下的半成品的存货id集合
			Set<Integer> setMiddleidinventory=new HashSet<Integer>();
		//2)该图号下的原材料的存货id集合
			Set<Integer> setMaterialidinventory=new HashSet<Integer>();
		
		
		//I.遍历所有母件的存货id，将其分开为成品的存货id和半成品的存货id
		for(int i=0;i<listMuBomidinventory.size();i++){
			//母件中的存货id如果包含在该订单号下的所有成品的存货id中，则该母件存货id为成品存货id，否则为半成品存货id
			if(listFuLLALLidinventory.contains(listMuBomidinventory.get(i))==false){
				setMiddleidinventory.add(listMuBomidinventory.get(i));
			}
         }
	
		//II.遍历所有子件的存货id，将其分开为半成品的存货id和原材料的存货id
		 for(int i=0;i<listZiBomidinventory.size();i++){
			//子件中的存货id如果包含在半成品的存货id中，则该子件存货id为半成品存货id，否则为原材料存货id
			 if(setMiddleidinventory.contains(listZiBomidinventory.get(i))==false){
				 setMaterialidinventory.add(listZiBomidinventory.get(i));
			 }
		 }
		 
		 
		List<ProductInventory> listMaterialInventory=new ArrayList<ProductInventory>();
		
		//增强for循环遍历Set集合
		for(Integer idinventory:setMaterialidinventory){
			ProductInventory productInventory=new ProductInventory();
			AaInventory aaInventory=aaInventoryMapper.selectSaleOrder(idinventory);
			String productSpec=aaInventory.getSpecification();
			String productName=aaInventory.getName();
			Integer idunit=aaInventory.getIdunit();
			String unit=aaUnitMapper.selectunitname(idunit);
			//原材料库存数量(条件：ST_CurrentStock.idwarehouse= N'1') --仓库)
			Integer num=stCurrentstockMapper.selectMaterialInventoryNum(idinventory);
				if(num!=null&&!("".equals(num))){
					productInventory.setProductNum(String.valueOf(num));
				}else{
					productInventory.setProductNum(0+"");
				}
			productInventory.setProductName(productName);
			productInventory.setProductSpec(productSpec);
			productInventory.setUnit(unit);
			
			//新加！！！-----------------------------------------------------------------------------
			//子件也可能为半成品（即：半成品由半成品和原材料构成），将原材料中的半成品剔除掉！
			if(productName.contains("半成品")==false){
				listMaterialInventory.add(productInventory);
			}
			//新加-到此结束---------------------------------------------------------------------------
		  }
		return listMaterialInventory;
	}
	
	
	//8.3.1根据订单号,图号显示半成品物料号
	@Override
	public List<String> selectMiddleMaterialNoMessage(String orderNo,
			String clientMaterialNo) {
		//订单号得到所有成品的存货id
				List<Integer> listFuLLALLidinventory=saSaleorderMapper.selectOrderNoToIdInventory(orderNo);

				//根据图号得到所有的物料号
				List<String> listMaterialNo=aaBomMapper.selectAaBom(clientMaterialNo);
				
				//1.根据物料号得到所有母件bom的存货id
				List<Integer> listMuBomidinventory=new ArrayList<Integer>();
				for(int i=0;i<listMaterialNo.size();i++){
					Integer id=aaBomMapper.selectMuIdInventory(listMaterialNo.get(i));
					listMuBomidinventory.add(id);
				}
				
				
				//2.根据物料号得到所有子件bom的存货id,一个物料号下的子件为多个要用list接收
				List<Integer> listZiBomidinventory=new ArrayList<Integer>();
				for(int i=0;i<listMaterialNo.size();i++){
					List<Integer> list=new ArrayList<Integer>();
					list=aaBomMapper.selectZiIdInventory(listMaterialNo.get(i));
					listZiBomidinventory.addAll(list);
				}
					
				//2)该图号下的半成品的存货id集合
					List<Integer> listMiddleidinventory=new ArrayList<Integer>();
					
				//I.遍历所有母件的存货id，将其分开为成品的存货id和半成品的存货id
				for(int i=0;i<listMuBomidinventory.size();i++){
					//母件中的存货id如果包含在该订单号下的所有成品的存货id中，则该母件存货id为成品存货id，否则为半成品存货id
					if(listFuLLALLidinventory.contains(listMuBomidinventory.get(i))==false){
						listMiddleidinventory.add(listMuBomidinventory.get(i));
					}
				}
				
				
				//新加！！！----------------------------------------------------------------------
				//（子件也可能为半成品（即：半成品由半成品和原材料构成），将原材料中的半成品加进半成品中！）
				//该图号下的原材料的存货id集合
				Set<Integer> setMaterialidinventory=new HashSet<Integer>();
				//II.遍历所有子件的存货id，将其分开为半成品的存货id和原材料的存货id
				 for(int i=0;i<listZiBomidinventory.size();i++){
					//子件中的存货id如果包含在半成品的存货id中，则该子件存货id为半成品存货id，否则为原材料存货id
					 if(listMiddleidinventory.contains(listZiBomidinventory.get(i))==false){
						 setMaterialidinventory.add(listZiBomidinventory.get(i));
					 }
				 }
				
				//增强for循环遍历Set集合
					for(Integer idinventory:setMaterialidinventory){
						AaInventory aaInventory=aaInventoryMapper.selectSaleOrder(idinventory);
						String productName=aaInventory.getName();
						if(productName.contains("半成品")==true){
							listMiddleidinventory.add(idinventory);
						}
					}
				//新加-到此结束--------------------------------------------------------------------
				 
				 
				//listMiddeleProductMaterialNo获取所有的半成品物料号
				List<String> listMiddeleProductMaterialNo=new ArrayList<String>();
				//3.遍历通过所有的半成品的存货id在inventory中获取所有的半成品物料号
				for(int i=0;i<listMiddleidinventory.size();i++){
				  String materialNo=aaInventoryMapper.selectInventoryidToMaterialNo(listMiddleidinventory.get(i));
				  listMiddeleProductMaterialNo.add(materialNo);
				}
				
		       return listMiddeleProductMaterialNo;
	}

	
		//8.3.2根据物料号显示半成品的库存信息(mysql数据库)
		@Override
		@DataSource("dataSource1")
		public MiddleProduct selectMiddleInventoryMessage(String materialNo) {
			
			return middleProductMapper.selectMiddleInventory(materialNo);
		}

		//9.原材料T+系统的选择模板并选择模板excel导出-查询数据库中该月中原材料的单据数量为单据日期做拼接！
		@Override
		public String selectRowMaterialNum() {
			
			return stRdrecordMapper.selectRowMaterialNum();
		}

		//10.产成品T+系统的选择模板并选择模板excel导出-查询数据库中该月中原材料的单据数量为单据日期做拼接！
		@Override
		public String selectFullProductNum() {
			
			return stRdrecordMapper.selectFullProductNum();
		}
	
	
}
