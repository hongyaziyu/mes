package cn.ssm.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.ssm.mapper.SaSaleorderMapper;
import cn.ssm.po.MiddleProduct;
import cn.ssm.po.ProductsBom;
import cn.ssm.service.ProductPlanService;
import cn.ssm.service.ProductionPlanService;
import cn.ssm.service.TJdbcService;
import cn.ssm.util.DynamicDataSourceHolder;
import cn.ssm.util.Page;
import cn.ssm.vo.ProductInventory;
import cn.ssm.vo.SaleOrder;
import cn.ssm.vo.SaleOrderDetail;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/jdbc")
public class TJdbcController {
	@Autowired
	private TJdbcService tJdbcService;
	@Autowired
	private ProductPlanService productPlanService;
	@Autowired
	private ProductionPlanService productionPlanService;
	//分页使用
	@Autowired
	private SaSaleorderMapper saSaleorderMapper;
    
	//1、订单记录
	@RequestMapping("toSaleOrderList")
	
	public String toSaleOrderList(Integer pageNow,String code,String start_date,String end_date,Model model)throws Exception{
		List<SaleOrder> listSaleOrder=new ArrayList<SaleOrder>();
		       // 用于查询条件未输时的判断查询
				// 1、起始为空赋给一个特别小的值（1000-1-1 00:00）
				if ((start_date == null || start_date == "")
						&& ((end_date != null || end_date != ""))) {
					start_date = "1000-01-01";
					
				}
				// 2、截止为空赋给一个特别大的值（今天）
				if ((start_date != null || start_date != "")
						&& ((end_date == null || end_date == ""))) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					 end_date=sf.format(new Date());
					
				}
				// 3、起始截止都为空两个都赋值
				if ((start_date == null || start_date == "")
						&& (end_date == null || end_date == "")) {
					
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				    start_date = "1000-01-01";
				    end_date=sf.format(new Date());
					
				}
                
				int totalCount = 0;
				//转换到dataSource2数据库
				DynamicDataSourceHolder.setDataSource("dataSource2");
				// 返回查询的行数totalCount
				totalCount = saSaleorderMapper.selectSaleOrderCount(code, start_date, end_date);

				Page page = null;
				if (pageNow != null) {
					page = new Page(totalCount, pageNow);
				} else {
					page = new Page(totalCount, 1);
				}
				
				listSaleOrder=tJdbcService.selectSaleOrder(page.getStartPos(),page.getPageSize(), code, start_date, end_date);
				
				// controller层中使model能返回一个jsp页面
				model.addAttribute("listSaleOrder", listSaleOrder);
				model.addAttribute("page", page);
				model.addAttribute("code", code);
				model.addAttribute("start_date", start_date);
				model.addAttribute("end_date", end_date);
		
		
		return "saleorderList";
	}
	
	
	//2、查看订单详情
	  @RequestMapping("toSaleOrderDetail")
	  public String toSaleOrderDetail(Integer id,Model model){
		  //显示基础信息
		  SaleOrder saleOrder=tJdbcService.selectOrderetail(id);
		  model.addAttribute("saleOrder", saleOrder);
			
		  //显示具体信息
		  List<SaleOrderDetail> listsaleOrderDetail=new ArrayList<SaleOrderDetail>();
		  listsaleOrderDetail=tJdbcService.selectSaleOrderdetail(id);
		  model.addAttribute("listsaleOrderDetail", listsaleOrderDetail);
		return "saleorderList_detail";
		  
	  }
	
	
	  //3、Ajax-周计划-查询是存在该订单号
	  @RequestMapping("/OrderNoAjax")
		public void shopWlAjax1(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			String orderNo = request.getParameter("orderNo");
			Boolean flag = tJdbcService.selectOrderNoIsNull(orderNo);
			// PrintWriter out=response.getWriter()获取了一个输出流
			// 通过当前HttpServletResponse以流的方式响应数据到请求html或者jsp页面，可以在客户端输出。
			PrintWriter out = response.getWriter();
			if (!flag) {
				// 客户端向前端输出的信息
				out.print("该订单号不存在，请检查！");
			}
			// 关闭输出流
			out.close();
		}
	
	
	 //4、Ajax-周计划-根据订单号显示客户信息
		@RequestMapping("/ClientAjax")
		@ResponseBody
		public JSONObject ClientAjax(String orderNo) throws Exception {
			 SaleOrder saleOrder = new SaleOrder();
			 saleOrder = tJdbcService.selectClient(orderNo);
			JSONObject obj = new JSONObject();
			obj.put("Client", saleOrder.getClient());
			return obj;
		}
		
	 //5、Ajax-周计划-根据订单号自动下拉显示图号
		@RequestMapping("/ClientMaterialNoAjax")
		public void ClientMaterialNoAjax(String orderNo,
				HttpServletRequest request, HttpServletResponse response,
				Model model) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			String html = tJdbcService.selectClientMaterialNo(orderNo);
			PrintWriter out = response.getWriter();
			out.print(html);

		}
		
		// 6、根据物料号、订单号显示订单数量(sql_server数据库)
		@RequestMapping("/OrderNumAjax")
		@ResponseBody
		public JSONObject OrderNumAjax(String materialNo,String orderNo) throws Exception {
			//订单数量(sql_server数据库)
			String ordernum=tJdbcService.selectOrdernum(orderNo, materialNo);
			//自定义计划单号（月份例如：JH-2018-11）
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
			String data=sdf.format(new Date());
			String planNo=null;
			Integer max=0;
			String max1=productionPlanService.selectPlanNoCode();
			if(max1!=null&&!("".equals(max1))){
				max=Integer.valueOf(max1.replaceAll("^(0+)", ""));//.replaceAll("^(0+)", "")-正则去除字符串前面的0
			}else{
				max=0;
			}
		
		    if(max>=0&&max<9){//判断如果是1位数拼接为：当前日期到月份"yyyy-MM-+00+max"
				max=max+1;
				 planNo="JH-"+data+"-00"+max;
			}else if(max>=9&&max<99){//判断如果2位数拼接为：当前日期到月份"yyyy-MM-+0+max"
				max=max+1;
				 planNo="JH-"+data+"-0"+max;
			}else if(max>=99){//判断如果3位数拼接为：当前日期到月份"yyyy-MM-+max"
				max=max+1;
				planNo="JH-"+data+"-"+max;
			}
			
			JSONObject obj = new JSONObject();
			//订单数量(sql_server数据库)
			obj.put("Ddnum", ordernum);
			obj.put("Jhdh",planNo);
			return obj;
		}
		
		//7、根据订单号、物料号显示库存信息
		@RequestMapping("/InventoryMessageAjax")
		public String InventoryMessageAjax(HttpServletRequest request, Model model)
				throws Exception {
			String orderNo = request.getParameter("orderNo");
			String clientMaterialNo = request.getParameter("clientMaterialNo");
			
			//1.成品库存(sql_server数据库)
			List<ProductInventory> listFullProductInventory=new ArrayList<ProductInventory>();
			listFullProductInventory=tJdbcService.selectFullInventoryMessage(orderNo,clientMaterialNo);
			
			//2.原材料库存(sql_server数据库)
			List<ProductInventory> listMaterialInventory=new ArrayList<ProductInventory>();
			listMaterialInventory=tJdbcService.selectMaterialInventoryMessage(orderNo,clientMaterialNo);
			
			//3.半成品库存(mysql数据库)
			List<ProductInventory> listMiddleProductInventory=new ArrayList<ProductInventory>();
			//3.1listMiddeleProductMaterialNo获取所有的半成品物料号
			List<String> listMiddeleProductMaterialNo=new ArrayList<String>();
			listMiddeleProductMaterialNo=tJdbcService.selectMiddleMaterialNoMessage(orderNo,clientMaterialNo);
			
			
			//3.2遍历3.1listMiddeleProductMaterialNo查询所有的库存信息
			for(int i=0;i<listMiddeleProductMaterialNo.size();i++){
				ProductInventory productInventory=new ProductInventory();
				String materialNo=listMiddeleProductMaterialNo.get(i);
				MiddleProduct middleProduct=tJdbcService.selectMiddleInventoryMessage(materialNo);
				if(middleProduct!=null){
					productInventory.setProductName(middleProduct.getProductName());
					productInventory.setProductSpec(middleProduct.getType());
					productInventory.setProductNum(String.valueOf(middleProduct.getNum()));
					productInventory.setUnit(middleProduct.getUnit());
				}else{
					ProductsBom productsBom=new ProductsBom();
					productsBom=productPlanService.selectCpmcandCpgg(materialNo);
					productInventory.setProductName(productsBom.getProductName());
					productInventory.setProductSpec(productsBom.getProductSpec());
					productInventory.setProductNum("该半成品库存暂未录入");
					productInventory.setUnit("/");
				}
				
				
				//把类加到集合中
				listMiddleProductInventory.add(productInventory);
			}
			
			model.addAttribute("listFullProductInventory", listFullProductInventory);
			model.addAttribute("listMaterialInventory", listMaterialInventory);
			model.addAttribute("listMiddleProductInventory", listMiddleProductInventory);
			return "productInventorydiv";

		}
		
		
}
