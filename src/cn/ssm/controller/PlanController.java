package cn.ssm.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ssm.mapper.ShopMapper;
import cn.ssm.mapper.TestProcessMapper;
import cn.ssm.po.DepOpinion;
import cn.ssm.po.GetDetail;
import cn.ssm.po.GetMaterial;
import cn.ssm.po.Person;
import cn.ssm.po.ProductAbnormal;
import cn.ssm.po.ProductTest;
import cn.ssm.po.ProductionPlan;
import cn.ssm.po.ProductsBom;
import cn.ssm.po.Shop;
import cn.ssm.po.ShopDelivery;
import cn.ssm.po.ShopPlan;
import cn.ssm.po.Spc;
import cn.ssm.po.Task;
import cn.ssm.po.TestProcess;
import cn.ssm.po.TrackCard;
import cn.ssm.service.GetMaterialService;
import cn.ssm.service.ProductAbnormalService;
import cn.ssm.service.ProductTestService;
import cn.ssm.service.ProductionPlanService;
import cn.ssm.service.ShopPlanService;
import cn.ssm.service.SpcService;
import cn.ssm.service.TJdbcService;
import cn.ssm.service.TrackCardSelectService;
import cn.ssm.vo.ProductInventory;
import cn.ssm.vo.Zijian;

@Controller
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	private ProductionPlanService productionPlanService;
	@Autowired
	private ShopPlanService shopPlanService;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private ProductTestService productTestService;
	@Autowired
	private GetMaterialService getMaterialService;
	@Autowired
	private TrackCardSelectService trackCardSelectService;
	@Autowired
	private ProductAbnormalService productAbnormalService;
	@Autowired
	private TestProcessMapper testProcessMapper;
	@Autowired
	private SpcService spcService;
	@Autowired
	private TJdbcService tJdbcService;
	@RequestMapping("/toSelectSpc")
	public String toSelectSpc() {
		return "Spc";
	}

	// 计划单号/批次号是否存在查询记录跳转
	@RequestMapping("/tojhorpcorQR")
	public String tojhorpcorQR() throws Exception {

		return "jhorpcorQR";
	}

	// 1.二维码：检查该计划号是否已经用过
	@RequestMapping("/JHDHUpBatchNoAjax")
	public String JHDHUpBatchNoAjax(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String planNo = request.getParameter("planNo");
		List<String> list= shopPlanService.selectPlanNoUpBatchNo(planNo);
		model.addAttribute("list", list);
		return "planNoUpbatchNo";
		

	}

	// 1.Spc页面查询+(从product_test抽取数据显示在Spc和Spc_test表中)
	@RequestMapping("/SelectSpc")
	public String SelectSpc(String clientMaterialNo, String materialNo,
			String process, String batchNo, String characterVal, Model model) {
		Spc spc1 = new Spc();
		spc1 = spcService.selectSpc(clientMaterialNo, materialNo, batchNo,
				process, characterVal);
		// 未满125组信息测量值的表格无信息
		model.addAttribute("spc1", spc1);
		return "Spc";
	}

	// 2.Spc表格数据显示
	@RequestMapping("/SpcTableShowAjax")
	public String SpcTableShowAjax(String clientMaterialNo, String materialNo,
			String batchNo, String process, String characterVal, Model model) {
		List<String> listValue = spcService.SpcTableShow(clientMaterialNo,
				materialNo, batchNo, process, characterVal);
		model.addAttribute("listValue", listValue);
		// 返回一个html页面
		return "Spc_table";

	}

	// 3.Spc曲线显示
	@RequestMapping("/SpcBightShowAjax")
	// 除了返回视图（也就是页面）必须要有@ResponseBody
	@ResponseBody
	public JSONObject SpcBightShowAjax(String clientMaterialNo,
			String materialNo, String batchNo, String process,
			String characterVal) {
		List<String> listValue = new ArrayList<String>();
		listValue = spcService.SpcBightShow(clientMaterialNo, materialNo,
				batchNo, process, characterVal);

		// 构建json向前端传数据（obj.put("key",value)方式传）
		JSONObject obj = new JSONObject();
		obj.put("Mid_x", listValue.get(0));
		obj.put("Ave_R", listValue.get(1));
		obj.put("UCLx", listValue.get(2));
		obj.put("LCLx", listValue.get(3));
		obj.put("UCLr", listValue.get(4));

		// 将集合中均值X 25个转换成json格式
		for (int i = 0; i < 25; i++) {
			obj.put("Ave_" + i, listValue.get(i + 5));
		}

		// 将集合中极差R 25个转换成json格式
		for (int i = 0; i < 25; i++) {
			obj.put("R_" + i, listValue.get(i + 30));
		}

		// 将X均值控制图的最小值转换成json格式
		obj.put("min", listValue.get(55));

		return obj;

	}

	// 4.Spc过程能力评估
	@RequestMapping("/SpcPCIAjax")
	// 除了返回视图（也就是页面）必须要有@ResponseBody
	@ResponseBody
	public JSONObject SpcPCIAjax(String clientMaterialNo, String materialNo,
			String batchNo, String process, String characterVal) {

		List<String> listCP = new ArrayList<String>();
		listCP = spcService.SpcPCI(clientMaterialNo, materialNo, batchNo,
				process, characterVal);

		// 构建json向前端传数据（obj.put("key",value)方式传）
		JSONObject obj = new JSONObject();
		obj.put("Cpk", listCP.get(0));
		obj.put("Ppk", listCP.get(1));

		return obj;

	}

	// Spc工序：根据物料号得到对应的工序，并下拉框显示
	@RequestMapping("/SpcgxAjax")
	public void SpcgxAjax(String materialNo, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String html = spcService.selectProcess(materialNo);
		// PrintWriter out=response.getWriter()获取了一个输出流
		// out.print(html)以输出流形式返回（out.print是先将内容放到带有缓存功能的printWriter再输出到页面）
		// 或者用response.getWriter().write(html);（直接输出到页面 ）
		PrintWriter out = response.getWriter();
		out.print(html);

	}

	// 产品异常单的跳转
	@RequestMapping("/toProductabnormal")
	public String toProductabnormal() throws Exception {
		return "addproductabnormal";
	}

	// 产品异常单的制定
	@RequestMapping("/insertProductAbnormal")
	public String InsertProductAbnormal(ProductAbnormal productAbnormal,
			HttpServletRequest request, Integer num1, Model model)
			throws Exception {
		List<DepOpinion> listDepOpinion = new ArrayList<DepOpinion>();
		for (int i = 1; i <= num1; i++) {
			String department = request.getParameter("department" + i);
			String opinion = request.getParameter("opinion" + i);

			DepOpinion depOpinion = new DepOpinion();

			depOpinion.setDepartment(department);
			depOpinion.setOpinion(opinion);

			listDepOpinion.add(depOpinion);

		}
		productAbnormalService.insertProductAbnormal(productAbnormal);
		Integer abnormalId = productAbnormal.getAbnormalId();
		productAbnormalService.insertAbnormalId(listDepOpinion, abnormalId);
		return "redirect:/record/selectProductAbnormal.action";

	}

	// 跟踪单制定
	@RequestMapping("/insertTrackCard")
	public String insertTrackCard(TrackCard trackCard, HttpSession session,
			HttpServletRequest request, Model model) throws Exception {
		
	    //查询批次号是否存在
		Boolean batchNo=trackCardSelectService.selectBatchNo(trackCard.getBatchNo());
		if(batchNo==false){
			
		// 插入登录界面的人为people	
			return "redirect:/plan/toinsertTrackCard.action";
		}
		Person person = (Person) session.getAttribute("user");
		if (person != null) {
			// 将登录人信息用set方法插入到getMaterial中
			trackCard.setPeople(person.getPersonName());
		}
		// 获取当天日期插入到make_date
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		trackCard.setMakeDate(df.format(day));

		// 获取原材料批次号,并将多选获取的值拼接到后台。插入材料批次号字段中。
		String materialBatchNos[] = request
				.getParameterValues("materialBatchNo");
		String materialBatchNo = "";
		if (materialBatchNos != null) {
			if (materialBatchNos != null) {
				for (int j = 0; j < materialBatchNos.length; j++) {
					materialBatchNo += materialBatchNos[j] + ",";
				}
			}
			// 去除结尾最后一个,

			materialBatchNo = materialBatchNo.substring(0,
					materialBatchNo.length() - 1);
		}
		trackCard.setMaterialBatchNo(materialBatchNo);

		trackCardSelectService.insertTrackCard(trackCard);
		return "redirect:/record/getTrackCard.action";
	}

	// 跟踪单制定的跳转
	@RequestMapping("/toinsertTrackCard")
	public String toinsertTrackCard() throws Exception {
		return "addgenzongdan";
	}

	// 跟踪单计划-根据批次号显示生产指令
	@RequestMapping("/NumberAjax")
	@ResponseBody
	public JSONObject NumberAjax(String batchNo) throws Exception {
		List<ShopPlan> shopPlan = new ArrayList<ShopPlan>();
		shopPlan = trackCardSelectService.selectNumber(batchNo);
		JSONObject obj = new JSONObject();
		obj.put("number", shopPlan.get(0).getPlanNum());

		return obj;
	}

	// 2、申请功能

	// 2.1申请领料功能的跳转
	@RequestMapping("/toApplyMaterial")
	public String toApplyMaterial(Integer getMaterialId, Model model)
			throws Exception {
		// 让申请领料开始显示一行空的数据
		List<Zijian> listZijian = new ArrayList<Zijian>();
		if (getMaterialId == null) {

			Zijian zijian = new Zijian();
			zijian.setCailiaoMc("");
			zijian.setMaterialNum("");
			zijian.setUnit("");
			zijian.setRemark("");
			zijian.setCailiaoBh("");
			zijian.setReferNum("");
			listZijian.add(zijian);
		}
		// 让申请领料开始显示一行空的数据(库存数量)
		 List<ProductInventory> listProductInventory=new ArrayList<ProductInventory>();
		 if (getMaterialId == null) {
			 ProductInventory productInventory=new ProductInventory();
			 productInventory.setProductName("");
			 //productInventory.setProductNum(0);
			 listProductInventory.add(productInventory);
			 
		 }
		model.addAttribute("listProductInventory", listProductInventory);
		model.addAttribute("listZijian", listZijian);
		return "applyMaterials";
	}

	// 2.2申请领料功能
	@RequestMapping("/ApplyMaterial")
	public String ApplyMaterial(GetMaterial getMaterial,
			HttpServletRequest request, HttpSession session, Integer num,
			Model model) throws Exception {
		List<GetDetail> listGetDetail = new ArrayList<GetDetail>();
		// 2.1添加第二张表GetDetail
		// 不确定的部分（用循环语句）
		for (int i = 1; i <= num; i++) {
			String cailiaoMc = request.getParameter("cailiaoMc" + i);
			String cailiaoBh = request.getParameter("cailiaoBh" + i);
			String materialNum = request.getParameter("materialNum" + i);
			String referNum = request.getParameter("referNum" + i);
			String unit = request.getParameter("unit" + i);
			//String remark = request.getParameter("remark" + i);

			// 创建类接受内容
			GetDetail getDetail = new GetDetail();
			getDetail.setCailiaoMc(cailiaoMc);
			getDetail.setCailiaoBh(cailiaoBh);
			getDetail.setMaterialNum(materialNum);
			getDetail.setReferNum(referNum);
			getDetail.setUnit(unit);
			//getDetail.setRemark(remark);

			// 将类getDetail对象加到集合listGetDetail中
			listGetDetail.add(getDetail);
		}
		// 插入登录界面的人为apply_people
		Person person = (Person) session.getAttribute("user");
		if (person != null) {
			// 将登录人信息用set方法插入到getMaterial中
			getMaterial.setApplyPeople(person.getPersonName());
		}
		
		
		//判断该批次号是否存在，不存在则插入新的信息，存在则更新该批次号信息
		String batchNo=getMaterial.getBatchNo();
		Boolean flag=getMaterialService.selectBatchNoIsNull(batchNo);
		
		
		if(flag==false){
			// 2.2添加第一张表GetMaterial
			getMaterialService.insertGetMaterial(getMaterial);
			// 获得第一张表的主键
			Integer getMaterialId = getMaterial.getGetMaterialId();
			// 添加第一张表的主键和第二张表插入数据
			getMaterialService.insertGetDetail(listGetDetail, getMaterialId);

			// 重定向到领料查询界面（显示全部的查询结果）
			return "redirect:/record/SelectMaterial.action";
		}else{
			//根据这个批次号去查主键getMaterialId
			Integer getMaterialId = getMaterialService.selectBatchNotogetMaterialId(batchNo);
			// 添加第一张表的主键和第二张表插入数据
			getMaterialService.updateByGetDetail(listGetDetail, getMaterialId);
			
			// 重定向到领料查询界面（显示全部的查询结果）
			return "redirect:/record/InputMaterialsRecord.action";
		}
		
		

	}

	// 根据图号、物料号两个信息自动显示出子件名称，子件编号的信息
	@RequestMapping("/shopCjAjax1")
	public String shopCjAjax1(HttpServletRequest request, Model model)
			throws Exception {

		String clientMaterialNo = request.getParameter("clientMaterialNo");
		String materialNo = request.getParameter("materialNo");
		String batchNo = request.getParameter("batchNo");
		String shopName = request.getParameter("shopName");
		// 返回到前端jsp的listZijian(包含了材料名称，材料编码，单位，以及理论值参考)
		List<Zijian> listZijian1 = new ArrayList<Zijian>();
		List<Zijian> listZijian2 = new ArrayList<Zijian>();
		// 正常流程车间排产下过批次号情况下,根据客户物料号，物料号,批次号查询出子件名称（材料名称），子件编号（材料编号），单位，基础用量，基本用量与计划数量构成理论值参考
		// 原材料外协流程下新加批次号（暂时无批号）情况下根据客户物料号，物料号查询出子件名称（材料名称），子件编号（材料编号），单位

		listZijian1 = getMaterialService.selectProductsBom(clientMaterialNo,
				materialNo, batchNo, shopName);
		String ReferNum = listZijian1.get(0).getReferNum();
		
		//库存数量查询(将库存信息存入子件的库存信息)
		 List<ProductInventory> listProductInventory=new ArrayList<ProductInventory>();
		 listProductInventory=tJdbcService.selectInventoryNum(materialNo);
		 //model.addAttribute("listProductInventory", listProductInventory);
		
		 //新加！-2018-11-13-----------------------------------------------------------------------------------------------
		 //把库存量的信息存入子件的集合中去
		 for(int i=0;i<listZijian1.size();i++){
			 listZijian1.get(i).setRemark(listProductInventory.get(i).getProductNum());
		 }
		 
		 //到此结束！-------------------------------------------------------------------------------------------------------------
		if (ReferNum.equals(" ")) {
			listZijian2 = getMaterialService.selectProductsBom1(
					clientMaterialNo, materialNo);
			 //把库存量的信息存入子件的集合中去
			 for(int i=0;i<listZijian2.size();i++){
				 listZijian2.get(i).setRemark(listProductInventory.get(i).getProductNum());
			 }
			 
			model.addAttribute("listZijian", listZijian2);
		} else {
			model.addAttribute("listZijian", listZijian1);
		}
         
		
		return "applyMatelDiv";

	}

	// 物料号：根据图号得到对应的物料号，并下拉框显示
	@RequestMapping("/shopMjwlAjax1")
	public void shopMjwlAjax1(String clientMaterialNo,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String html = getMaterialService.selectMaterialNos(clientMaterialNo);
		// PrintWriter out=response.getWriter()获取了一个输出流
		// out.print(html)以输出流形式返回（out.print是先将内容放到带有缓存功能的printWriter再输出到页面）
		// 或者用response.getWriter().write(html);（直接输出到页面 ）
		PrintWriter out = response.getWriter();
		out.print(html);

	}

	// 批次号显示判断：检查该批次号是否已经用过
	@RequestMapping("/shopPCHAjax")
	public void shopPCHAjax(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String batchNo = request.getParameter("batchNo");
		Boolean flag = getMaterialService.selectBatchNoIsNull(batchNo);
		// PrintWriter out=response.getWriter()获取了一个输出流
		// 通过当前HttpServletResponse以流的方式响应数据到请求html或者jsp页面，可以在客户端输出。
		PrintWriter out = response.getWriter();
		if (!flag) {
			// 客户端向前端输出的信息
			out.print(" 该批次号已领过料，请重新填写新的批次号！");
		}
		// 关闭输出流
		out.close();

	}

	// 图号显示：检查有无这条信息图号
	@RequestMapping("/shopWlAjax1")
	public void shopWlAjax1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String clientMaterialNo = request.getParameter("clientMaterialNo");
		Boolean flag = getMaterialService
				.selectMaterialNoIsNull(clientMaterialNo);
		// PrintWriter out=response.getWriter()获取了一个输出流
		// 通过当前HttpServletResponse以流的方式响应数据到请求html或者jsp页面，可以在客户端输出。
		PrintWriter out = response.getWriter();
		if (!flag) {
			// 客户端向前端输出的信息
			out.print("该图号不存在，请检查！");
		}
		// 关闭输出流
		out.close();
	}
	
	// 根据物料号显示产品名称或者产品规格(MySQL数据库)、订单数量(sql_server数据库)
	@RequestMapping("/shopMCorGGAjax")
	@ResponseBody
	public JSONObject shopMCorGGAjax(String materialNo) throws Exception {
		ProductsBom productsBom = new ProductsBom();
		productsBom = getMaterialService.selectCpmcandCpgg(materialNo);
		
		JSONObject obj = new JSONObject();
		obj.put("Cpmc", productsBom.getProductName());
		obj.put("Cpgg", productsBom.getProductSpec());
		
		return obj;
	}

	// 2.吴永-添加ProductTest表信息和更新ProductTest表信息
	@RequestMapping("/addProductTest")
	public String addProductTest(HttpServletRequest request, Integer num,
			Model model, Integer testId) throws Exception {
		if (num != null && num > 0) {
			if (testId == null) {
				// 为ProductTest表设置集合对象接受类
				List<ProductTest> listProductTest = new ArrayList<ProductTest>();

				// 2.1添加到表ProductTest
				// 公共的部分
				// request.getParameter()括号内接受前端jsp页面的输入值
				String client = request.getParameter("client");
				String clientMaterialNo = request
						.getParameter("clientMaterialNo");
				String materialNo = request.getParameter("materialNo");
				String batchNo = request.getParameter("batchNo");
				String planNo = request.getParameter("planNo");

				// 不确定的部分循环输出
				for (int i = 1; i <= num; i++) {
					// String
					// produceDate=request.getParameter("produce_date"+i);
					// 获取当前时间（精确到分钟）
					Date date = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm");
					String checkDate = dateFormat.format(date);

					String processName0 = request.getParameter("gongxu" + i);
					String characterVal = request.getParameter("tezheng" + i);
					String processName = processName0 + "/" + characterVal;
					String standardVal0 = request.getParameter("biaozhunzhi"
							+ i);
					String standardValPlus = request
							.getParameter("biaozhunzhiPlus" + i);
					String standardValMinus = request
							.getParameter("biaozhunzhiMinus" + i);
					// 用于判断当standardValMinus和standardValPlus为空或者不输入时自动赋于0
					if (standardValMinus == null
							|| ("".equals(standardValMinus))) {
						standardValMinus = 0 + " ";
					}
					if (standardValPlus == null || ("".equals(standardValPlus))) {
						standardValPlus = 0 + " ";
					}
					String standardVal = standardVal0 + "+" + standardValPlus
							+ "/-" + standardValMinus;
					String testVal_1 = request.getParameter("clz1" + i);
					String testVal_2 = request.getParameter("clz2" + i);
					String testVal_3 = request.getParameter("clz3" + i);
					String testVal_4 = request.getParameter("clz4" + i);
					String testVal_5 = request.getParameter("clz5" + i);
					String operator = request.getParameter("caozuogong" + i);
					String batchNumber = request.getParameter("pihao" + i);
					String isOk = request.getParameter("panding" + i);
					String processId = request.getParameter("processId" + i);
					// 创建类接受内容
					ProductTest productTest = new ProductTest();
					productTest.setClient(client);
					productTest.setBatchNo(batchNo);
					productTest.setPlanNo(planNo);
					productTest.setClientMaterialNo(clientMaterialNo);
					productTest.setMaterialNo(materialNo);
					productTest.setCheckDate(checkDate);
					productTest.setProcessName(processName);
					productTest.setCharacterVal(characterVal);
					productTest.setStandardVal(standardVal);
					productTest.setTestVal1(testVal_1);
					productTest.setTestVal2(testVal_2);
					productTest.setTestVal3(testVal_3);
					productTest.setTestVal4(testVal_4);
					productTest.setTestVal5(testVal_5);
					productTest.setOperator(operator);
					productTest.setBatchNumber(batchNumber);
					productTest.setIsOk(isOk);

					// 将类ProductTest对象加到集合listProductTest中
					listProductTest.add(productTest);

					// List<TestProcess> listtestProcess = new
					// ArrayList<TestProcess>();
					/*
					 * listtestProcess=productTestService.selectTestProcessByParam
					 * (materialNo,clientMaterialNo, processName,standardVal);
					 */
					if (processId == null || processId == "") {
						TestProcess testProcess = new TestProcess();
						testProcess.setClientMaterialNo(clientMaterialNo);
						testProcess.setMaterialNo(materialNo);
						testProcess.setProcess(processName);
						testProcess.setStandardVal(standardVal);
						productTestService.insertTestProcess(testProcess);

					} else {
						TestProcess testProcess = new TestProcess();
						testProcess.setProcessId(Integer.valueOf(processId));
						testProcess.setClientMaterialNo(clientMaterialNo);
						testProcess.setMaterialNo(materialNo);
						testProcess.setProcess(processName);
						testProcess.setStandardVal(standardVal);
						testProcessMapper.updateByPrimaryKey(testProcess);
					}
				}

				productTestService.insertProductTest(listProductTest);

			}

			else {
				// 2.2更新ProductTest表信息
				String client = request.getParameter("client");
				String clientMaterialNo = request
						.getParameter("clientMaterialNo");
				String materialNo = request.getParameter("materialNo");
				String productName = request.getParameter("productName");
				String planNo = request.getParameter("planNo");
				String batchNo = request.getParameter("batchNo");
				String checkDate = request.getParameter("produce_date1");

				String processName0 = request.getParameter("gongxu1");
				String characterVal = request.getParameter("tezheng1");
				String processName = processName0 + "/" + characterVal;

				String standardVal0 = request.getParameter("biaozhunzhi1");
				String standardValPlus = request
						.getParameter("biaozhunzhiPlus1");
				String standardValMinus = request
						.getParameter("biaozhunzhiMinus1");
				String standardVal = standardVal0 + "+" + standardValPlus
						+ "/-" + standardValMinus;

				String testVal_1 = request.getParameter("clz11");
				String testVal_2 = request.getParameter("clz21");
				String testVal_3 = request.getParameter("clz31");
				String testVal_4 = request.getParameter("clz41");
				String testVal_5 = request.getParameter("clz51");
				String operator = request.getParameter("caozuogong1");
				String batchNumber = request.getParameter("pihao1");
				String isOk = request.getParameter("panding1");
				ProductTest productTest = new ProductTest();
				productTest.setTestId(testId);
				productTest.setClient(client);
				productTest.setPlanNo(planNo);
				productTest.setBatchNo(batchNo);
				productTest.setClientMaterialNo(clientMaterialNo);
				productTest.setMaterialNo(materialNo);
				productTest.setProductName(productName);
				productTest.setCheckDate(checkDate);
				productTest.setProcessName(processName);
				productTest.setCharacterVal(characterVal);
				productTest.setStandardVal(standardVal);
				productTest.setTestVal1(testVal_1);
				productTest.setTestVal2(testVal_2);
				productTest.setTestVal3(testVal_3);
				productTest.setTestVal4(testVal_4);
				productTest.setTestVal5(testVal_5);
				productTest.setOperator(operator);
				productTest.setBatchNumber(batchNumber);
				productTest.setIsOk(isOk);
				productTestService.updateProductTestbytestId(productTest);
			}
		}
		return "redirect:/record/SelectProductTest.action";

	}

	// 3.吴永-根据testId查询ProductTest数据用于更新前的单条ProductTest数据的回显
	@RequestMapping("/HuixianProductTest")
	public String HuixianProductTest(Integer testId, Model model,
			HttpSession session) throws Exception {
		List<ProductTest> listProductTest = new ArrayList<ProductTest>();
		// ProductTest表的查询
		ProductTest productTest = new ProductTest();
		productTest = productTestService.selectBytestId(testId);
		listProductTest.add(productTest);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listProductTest", listProductTest);

		// 获取当前登陆人person
		Person person = (Person) session.getAttribute("user");

		// 如果position为品保部部长的话，返回的页面可以修改检测事件的值
		if (person.getPosition().equals("部长") == true&&person.getDepartment().equals("品保部")==true) {
			return "product_test1";
		} else {
			return "product_test";
		}

	}

	// 4.吴永-根据testId删除ProductTest表数据
	@RequestMapping("/DeleteProductTest")
	public String DeleteProductTest(Integer testId) {
		productTestService.deleteProductTest(testId);
		return "redirect:/record/SelectProductTest.action";
	}

	// 吴永-
	@RequestMapping("/productTestAjax")
	public String productTestAjax(HttpServletRequest request, Model model)
			throws Exception {
		List<ProductTest> listProductTest = new ArrayList<ProductTest>();
		String clientMaterialNo = request.getParameter("clientMaterialNo");
		String materialNo = request.getParameter("materialNo");

		listProductTest = productTestService.selectProductTestByParam(
				materialNo, clientMaterialNo);
		model.addAttribute("listProductTest", listProductTest);
		return "productTestDiv";

	}

	@RequestMapping("/productTestAjax2")
	public void productTestAjax2(HttpServletRequest request) throws Exception {
		String processId = request.getParameter("processId");

		if (processId != null && processId != "") {
			testProcessMapper.deleteByPrimaryKey(Integer.valueOf(processId));
		}
		// 吴永-删除多余的检测工序1是删除原始检测工序，2是删除原始检测工序+特征值
		/*
		 * testProcessMapper.deleteTestProcessduoyu1(materialNo,clientMaterialNo,
		 * process0);
		 * testProcessMapper.deleteTestProcessduoyu2(materialNo,clientMaterialNo
		 * ,process);
		 */

		// model.addAttribute("listProductTest", listProductTest);
		// return "productTestDiv";
	}

	// 吴永-
	@RequestMapping("/toProductTest")
	public String toProductTest(Model model) throws Exception {

		List<ProductTest> listProductTest = new ArrayList<ProductTest>();
		// ProductTest表的查询
		ProductTest productTest = new ProductTest();

		listProductTest.add(productTest);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listProductTest", listProductTest);
		return "product_test";
	}

	// 车间排产计划（将添加页面和修改页面用一个页面通过主键id判断显示空或者回显）
	@RequestMapping("/toShopPlan")
	public String toShopPlan(Integer planId, Model model) throws Exception {

		ShopPlan shopPlan = shopPlanService.selectByPrimaryKey(planId);
		model.addAttribute("shopPlan", shopPlan);

		List<Task> listTask = new ArrayList<Task>();
		listTask = shopPlanService.selectTaskByKey(planId);
		model.addAttribute("listTask", listTask);

		List<String> personNames = shopPlanService.selectPersonNames();
		List<String> listAssetandGauge = shopPlanService.selectAssetNames();
		List<String> moldNames = shopPlanService.selectMoldNames();
		
		model.addAttribute("personNames", personNames);
		model.addAttribute("assetNames", listAssetandGauge);
		model.addAttribute("moldNames", moldNames);
		model.addAttribute("planId", planId);
		return "shopplan";
	}

	//车间排产计划添加
	@RequestMapping("/addShopPlan")
	public String addShopPlan(ShopPlan shopPlan, Integer num,
			HttpServletRequest request, HttpSession session) throws Exception {
		/*List<Task> listTask = new ArrayList<Task>();
		String processSort = "";
		if (num != null&&num !=0) {
			for (int i = 1; i <= num; i++) {
				String caozuogong = "";
				String gongxu = request.getParameter("gongxu" + i);
				String muju = request.getParameter("muju" + i);
				String values[] = request.getParameterValues("caozuogong" + i);
				String shebei = request.getParameter("shebei" + i);

				if (values != null) {
					for (int j = 0; j < values.length; j++) {
						caozuogong += values[j] + ",";
					}
					caozuogong = caozuogong.substring(0,
							caozuogong.length() - 1);
				}

				Task task = new Task();
				task.setProcessName(gongxu);
				task.setMold(muju);
				task.setOperator(caozuogong);
				task.setAsset(shebei);
				listTask.add(task);
				processSort += gongxu + ",";
			}
			Person person = (Person) session.getAttribute("user");
			if (person != null) {
				shopPlan.setPlanPeople(person.getPersonName());
			}
			processSort = processSort.substring(0, processSort.length() - 1);
			// 主键返回
			// 插入shopPlan
			shopPlanService.insertShopPlan(shopPlan, processSort);
			Integer planId = shopPlan.getPlanId();
			shopPlanService.insertTask(listTask, planId);
			return "redirect:/record/toShopPlanList.action";
			//未安排车间的车间排产，记录提交后直接返回记录页面
		}else if(num==0){*/
			return "redirect:/record/toShopPlanList.action";
		/*}
		else {
			return "redirect:toShopPlan";
		}*/
	}

	//周计划添加
	@RequestMapping("/addProductionPlan")
	public String addProductionPlan(ProductionPlan productionPlan, Integer num,
			HttpServletRequest request, HttpSession session) throws Exception {
		if (num != null) {
			productionPlanService.addProductionPlan(productionPlan, num,
					request, session);
			return "redirect:/record/toProductionPlanList";
		} else {
			return "redirect:toSpareProductionPlan";
		}

	}
	

	//月计划添加
	@RequestMapping("/addTotalPlan")
	public String addTotalPlan(ProductionPlan productionPlan,
			HttpSession session) throws Exception {

		productionPlanService.addTotalPlan(productionPlan, session);
		return "redirect:/record/toTotalPlanList";
	}

	//月计划记录显示
	@RequestMapping("/toTotalPlan")
	public String toTotalPlan(Integer planId, Model model) throws Exception {
		ProductionPlan productionPlan = productionPlanService
				.selectProductionPlanByPrimaryKey(planId);
		model.addAttribute("productionPlan", productionPlan);
		return "totalPlan";
	}

	
	//用友读取-周计划显示
	@RequestMapping("/toProductionPlan")
	public String toProductionPlan(String client, String orderNo,
	//-----------------------新加内容------------------------------------------------
			String productName, String productSpec, 
			String orderDate, String planNum, 
	//----------------------------------------------------------------------------
			Integer planId, Model model) throws Exception {
		ProductionPlan productionPlan = new ProductionPlan();
		productionPlan = productionPlanService
				.selectProductionPlanByPrimaryKey(planId);
		if (productionPlan == null) {
			productionPlan = new ProductionPlan();
			if (client != null) {
				productionPlan.setClient(client);
			}
			if (orderNo != null) {
				productionPlan.setOrderNo(orderNo);
			}
	//-------------------添加功能--------------------------------------
		
			if (productName != null) {
				productionPlan.setProductName(productName);
			}if (productSpec != null) {
				productionPlan.setProductSpec(productSpec);
			}if (orderDate != null) {
				productionPlan.setOrderDate(orderDate);
			}if (planNum != null) {
				productionPlan.setPlanNum(planNum);
			}
	//--------------------------------------------------------------------
		}
		model.addAttribute("productionPlan", productionPlan);
		List<ShopDelivery> listShopDelivery = new ArrayList<ShopDelivery>();
		listShopDelivery = productionPlanService
				.selectShopDeliveryByKey(planId);
		model.addAttribute("listShopDelivery", listShopDelivery);
		List<Shop> listShop = shopMapper.selectAll();
		model.addAttribute("listShop", listShop);
		
		//初始时成品库存为空显示
		List<ProductInventory> listFullProductInventory=new ArrayList<ProductInventory>();
		ProductInventory productInventory1=new ProductInventory();
		productInventory1.setProductName("");
		productInventory1.setProductSpec("");
		productInventory1.setUnit("");
		listFullProductInventory.add(productInventory1);
		model.addAttribute("listFullProductInventory", listFullProductInventory);
		
		//初始时半成品库存为空显示
		List<ProductInventory> listMiddleProductInventory=new ArrayList<ProductInventory>();
		ProductInventory productInventory2=new ProductInventory();
		productInventory2.setProductName("");
		productInventory2.setProductSpec("");
		productInventory2.setUnit("");
		listMiddleProductInventory.add(productInventory2);
		model.addAttribute("listMiddleProductInventory", listMiddleProductInventory);
		
		//初始时原材料库存为空显示
		List<ProductInventory> listMaterialInventory=new ArrayList<ProductInventory>();
		ProductInventory productInventory3=new ProductInventory();
		productInventory3.setProductName("");
		productInventory3.setProductSpec("");
		productInventory3.setUnit("");
		listMaterialInventory.add(productInventory3);
		model.addAttribute("listMaterialInventory", listMaterialInventory);
		return "productionPlan";
	}

	//手动输入-备用：周计划及记录显示
	@RequestMapping("/toSpareProductionPlan")
	public String toSpareProductionPlan(String client, String orderNo,
			Integer planId, Model model) throws Exception {
		ProductionPlan productionPlan = new ProductionPlan();
		productionPlan = productionPlanService
				.selectProductionPlanByPrimaryKey(planId);
		if (productionPlan == null) {
			productionPlan = new ProductionPlan();
			if (client != null) {
				productionPlan.setClient(client);
			}
			if (orderNo != null) {
				productionPlan.setOrderNo(orderNo);
			}
		}
		model.addAttribute("productionPlan", productionPlan);
		List<ShopDelivery> listShopDelivery = new ArrayList<ShopDelivery>();
		listShopDelivery = productionPlanService
				.selectShopDeliveryByKey(planId);
		model.addAttribute("listShopDelivery", listShopDelivery);
		List<Shop> listShop = shopMapper.selectAll();
		model.addAttribute("listShop", listShop);
		return "spare_productionPlan";
	}
	
	
	
	// 根据图号、物料号、车间名称三个信息自动显示出操作工的信息

	@RequestMapping("/shopCjAjax")
	public String shopCjAjax(HttpServletRequest request, Model model)
			throws Exception {

		List<Task> listTask = new ArrayList<Task>();
		String clientMaterialNo = request.getParameter("clientMaterialNo");
		String materialNo = request.getParameter("materialNo");
		String shopName = request.getParameter("shopName");
		listTask = shopPlanService.selectTaskByParam(materialNo, shopName,
				clientMaterialNo);
		model.addAttribute("listTask", listTask);
		List<String> personNames = shopPlanService.selectPersonNames();
		List<String> assetNames = shopPlanService.selectAssetNames();
		List<String> moldNames = shopPlanService.selectMoldNames();
		model.addAttribute("personNames", personNames);
		model.addAttribute("assetNames", assetNames);
		model.addAttribute("moldNames", moldNames);
		return "shopdiv";

	}

	@RequestMapping("/shopAjax")
	public String shopAjax(HttpServletRequest request, Model model)
			throws Exception {
		List<ShopDelivery> listShopDelivery = new ArrayList<ShopDelivery>();
		String clientMaterialNo = request.getParameter("clientMaterialNo");
		String materialNo = request.getParameter("materialNo");
		listShopDelivery = productionPlanService.selectShopDeliveryByParam(
				materialNo, clientMaterialNo);
		model.addAttribute("listShopDelivery", listShopDelivery);
		List<Shop> listShop = shopMapper.selectAll();
		model.addAttribute("listShop", listShop);
		return "productionPlandiv";

	}

	
	// 未完成批次号查询
		@RequestMapping("/picihaoAjax")
		public void picihaoAjax(String materialNo,
				HttpServletRequest request, HttpServletResponse response,
				Model model) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			String html = shopPlanService.selectPicihao(materialNo);
			PrintWriter out = response.getWriter();
			out.print(html);

		}


	// 6、周计划-Ajax-计划单号随机生成
			@RequestMapping("/JhdhAjax")
			@ResponseBody
			public JSONObject JhdhAjax() throws Exception {
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
				obj.put("Jhdh",planNo);
				return obj;
			}
			
				//7、二维码分几批生成，excel导出
				@RequestMapping("/excelAjax")
				public void excelAjax(HttpServletResponse response,
						Integer num,String wxorzc,String planNo) throws Exception {
					// 文件名
					String fileName = "三码合一二维码制定.xlsx";
					//获取当前日期
					SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
					String data=sf.format(new Date());
						response.setHeader("Content-disposition",
								"attachment;filename="+ data+planNo+new String(fileName.getBytes("gb2312"),
												"ISO8859-1")); // 设置文件头编码格式
						response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
						response.setHeader("Cache-Control", "no-cache");// 设置头
						response.setDateHeader("Expires", 0);// 设置日期头
					
					// 文件标题栏
					//String[] cellTitle = {"产品名称","图号","三码合一(物料号/计划单号/批次号)"};
					try {

						// 声明一个工作薄
						XSSFWorkbook workBook = null;
						workBook = new XSSFWorkbook();

						// 生成一个表格
						XSSFSheet sheet = workBook.createSheet();
						workBook.setSheetName(0, "三码合一页");

						// 创建行
						XSSFRow row = sheet.createRow((short) 0);
						// 创建单元格
						XSSFCell cell = null;

						// 第一行标题栏
						row = sheet.createRow(0);
						
						cell = row.createCell(0);
						cell.setCellValue("产品名称");
						cell = row.createCell(1);
						cell.setCellValue("图号");
						cell = row.createCell(2);
						cell.setCellValue("批次号");
						cell = row.createCell(3);
						cell.setCellValue("三码合一二维码制定");
						
						// 设置居中
						XSSFCellStyle cellStyle = workBook.createCellStyle();
						cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

						// 设置列宽
						sheet.setColumnWidth(0, 20 * 256);
						sheet.setColumnWidth(1, 20 * 256);
						sheet.setColumnWidth(2, 20 * 256);
						sheet.setColumnWidth(3, 45 * 256);

						// 4.历史数据，业务数据，不用关注
						ProductionPlan productionPlan = new ProductionPlan();
						productionPlan=shopPlanService.selectexcelQR(planNo);

						if (productionPlan != null && !("".equals(productionPlan))) {

							// 5.将历史数据添加到单元格中 (先列后行)
							for (int j = 0; j <num; j++) {
								row = sheet.createRow(j + 1);
								cell = row.createCell(0);
								cell.setCellValue(productionPlan.getProductName());
								
								cell = row.createCell(1);
								cell.setCellValue(productionPlan.getClientMaterialNo());
								
								//插入新编批次号
								cell = row.createCell(2);
								Integer Maxnumber=0;
								String Maxnumber1=shopPlanService.selectQRMaxNum(planNo);
								
								if(Maxnumber1!=null&&!("".equals(Maxnumber1))){
									Maxnumber=Integer.valueOf(Maxnumber1.replaceAll("[[\\s-:punct:]]", ""));//.replaceAll("[[\\s-:punct:]]", "")-正则去除字符串前面的-
								}else{
									Maxnumber=0;
								}
								if(wxorzc.equals("正常")){
										cell.setCellValue("PC-"+planNo.substring(planNo.length()-11)+"-" +(Maxnumber+j+1));
								}else{
										cell.setCellValue("WX-"+planNo.substring(planNo.length()-11)+"-" +(Maxnumber+j+1));
									
								}
								
								//插入三码合一
								cell = row.createCell(3);
								
								if(Maxnumber1!=null&&!("".equals(Maxnumber1))){
									Maxnumber=Integer.valueOf(Maxnumber1.replaceAll("[[\\s-:punct:]]", ""));//.replaceAll("[[\\s-:punct:]]", "")-正则去除字符串前面的-
								}else{
									Maxnumber=0;
								}
								if(wxorzc.equals("正常")){
									cell.setCellValue(productionPlan.getMaterialNo()+"/"+planNo+"/PC-"+planNo.substring(planNo.length()-11)+"-" +(Maxnumber+j+1));
								}else{
									cell.setCellValue(productionPlan.getMaterialNo()+"/"+planNo+"/WX-"+planNo.substring(planNo.length()-11)+"-" +(Maxnumber+j+1));
								}
								
								
								
						
							}
						}

						// 将文件写进输出流
						workBook.write(response.getOutputStream());
						// .flush()写出缓冲区的内容
						response.getOutputStream().flush();
						// 关闭输出流
						response.getOutputStream().close();
					} catch (Exception e) {
						e.printStackTrace();
					
					}
		

	}
				
				
				
				
			
}