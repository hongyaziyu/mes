package cn.ssm.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.ssm.mapper.DailyCheckMapper;
import cn.ssm.mapper.GetMaterialMapper;
import cn.ssm.mapper.GetSecDetailMapper;
import cn.ssm.mapper.GetSecMaterialsMapper;
import cn.ssm.mapper.MoldRecordMapper;
import cn.ssm.mapper.MonthPlanMapper;
import cn.ssm.mapper.ProcessTransitionMapper;
import cn.ssm.mapper.ProductAbnormalMapper;
import cn.ssm.mapper.ProductRecordMapper;
import cn.ssm.mapper.ProductTestMapper;
import cn.ssm.mapper.ProductionPlanMapper;
import cn.ssm.mapper.ShopDeliveryMapper;
import cn.ssm.mapper.ShopPlanMapper;
import cn.ssm.mapper.ShopTransitionMapper;
import cn.ssm.mapper.SpcMapper;
import cn.ssm.mapper.TrackCardMapper;
import cn.ssm.mapper.WorkCardMapper;
import cn.ssm.po.CheckRecord;
import cn.ssm.po.Cipin;
import cn.ssm.po.DailyCheck;
import cn.ssm.po.DepOpinion;
import cn.ssm.po.GetDetail;
import cn.ssm.po.GetMaterial;
import cn.ssm.po.GetSecDetail;
import cn.ssm.po.GetSecMaterials;
import cn.ssm.po.MoldRecord;
import cn.ssm.po.MonthPlan;
import cn.ssm.po.Person;
import cn.ssm.po.ProcessTransition;
import cn.ssm.po.ProductAbnormal;
import cn.ssm.po.ProductRecord;
import cn.ssm.po.ProductTest;
import cn.ssm.po.ProductionPlan;
import cn.ssm.po.ShopPlan;
import cn.ssm.po.ShopTransition;
import cn.ssm.po.Spc;
import cn.ssm.po.SpcTest;
import cn.ssm.po.TrackCard;
import cn.ssm.po.TransitionCipin;
import cn.ssm.po.WorkCard;
import cn.ssm.service.DailyCheckService;
import cn.ssm.service.GetDetailService;
import cn.ssm.service.GetMaterialService;
import cn.ssm.service.IntputOrOutputRecordService;
import cn.ssm.service.MoldRecordService;
import cn.ssm.service.MonthPlanService;
import cn.ssm.service.ProductAbnormalService;
import cn.ssm.service.ProductTestService;
import cn.ssm.service.ProductionPlanService;
import cn.ssm.service.SalaryService;
import cn.ssm.service.SecMaterialService;
import cn.ssm.service.ShopPlanService;
import cn.ssm.service.SpcService;
import cn.ssm.service.TJdbcService;
import cn.ssm.service.TrackCardSelectService;
import cn.ssm.service.WorkCardService;
import cn.ssm.util.Page;
import cn.ssm.vo.ExterAssociation;
import cn.ssm.vo.Input;
import cn.ssm.vo.InputMaterialAssociation;
import cn.ssm.vo.InputSec;
import cn.ssm.vo.Output;
import cn.ssm.vo.ProductionRecordInquiry;
import cn.ssm.vo.Salary;
import cn.ssm.vo.TemPrice;
import cn.ssm.vo.WeekPlan;

@Controller
@RequestMapping("/record")
public class RecordController {
	@Autowired
	private ProductionPlanService productionPlanService;	
	@Autowired
	private ShopPlanService shopPlanService;	
	@Autowired
	private WorkCardService workCardService;
	@Autowired
	private IntputOrOutputRecordService inputOrOutputRecordService;
	@Autowired
	private SalaryService salaryService;
	@Autowired
	private GetMaterialService getMaterialService;
	@Autowired
	private MoldRecordService moldRecordService;
	@Autowired
	private ProductTestService productTestService;
	@Autowired
	private TrackCardSelectService trackCardSelectService;
	@Autowired
	private ProductAbnormalService productAbnormalService;
	@Autowired
	private DailyCheckService dailyCheckService;
	@Autowired
	private GetDetailService getDetailService;
	@Autowired
	private SpcService spcService;
	@Autowired
	private SecMaterialService secMaterialService;
	@Autowired
	private TJdbcService tJdbcService;
	@Autowired
	private MonthPlanService monthPlanService;
	// 分页所需的
	@Autowired
	private ProductionPlanMapper productionPlanMapper;
	@Autowired
	private GetMaterialMapper getMaterialMapper;
	@Autowired
	private ShopPlanMapper shopPlanMapper;
	@Autowired
	private ProductAbnormalMapper productAbnormalMapper;
	@Autowired
	private TrackCardMapper trackCardMapper;
	@Autowired
	private WorkCardMapper workCardMapper;
	@Autowired
	private MoldRecordMapper moldRecordMapper;
	@Autowired
	private ProductTestMapper productTestMapper;
	@Autowired
	private DailyCheckMapper dailycheckMapper;
	@Autowired
	private ProductRecordMapper productRecordMapper;
	@Autowired
	private SpcMapper spcMapper;
	@Autowired
	private GetSecMaterialsMapper getSecMaterialsMapper;
	@Autowired
	private GetSecDetailMapper getSecDetailMapper;
	@Autowired
	private ShopTransitionMapper shopTransitionMapper;
	@Autowired
	private ShopDeliveryMapper shopDeliveryMapper ;
	@Autowired
	private ProcessTransitionMapper processTransitionMapper;
	@Autowired
	private MonthPlanMapper monthPlanMapper;
	// 首页跳转
	@RequestMapping("/toHomePage")
	public String toHomePage() throws Exception {

		return "homePage";
	}

	// 计划进度查询跳转页面
	@RequestMapping("/toselectProductionPlan")
	public String toselectProductionPlan() throws Exception {
		return "productionproess";
	}

	// 半成品入库记录问题是否解决（将已解决由0到1）
	@RequestMapping("/approveMiddleProblem")
	public String approveProblem(ProductRecord productRecord, Integer recordId,
			String temPrice, HttpSession session, Model model) throws Exception {

		inputOrOutputRecordService.updateIsProblem(recordId);
		return "redirect:OutputMiddleMaterialsRecord.action";

	}

	// 成品入库记录问题是否解决（将已解决由0到1）
	@RequestMapping("/approveFullProblem")
	public String approveFullProblem(ProductRecord productRecord,
			Integer recordId, String temPrice, HttpSession session, Model model)
			throws Exception {

		inputOrOutputRecordService.updateIsProblem(recordId);
		return "redirect:toOutputFullMaterialsRecord.action";

	}

	// 成品入库===========================================
	@RequestMapping("/toOutputFullMaterialsRecord")
	public String toOutputFullMaterialsRecord(Integer pageNow,
			String material_no, String start_date, String end_date, Model model) {
		// 第一、二张表的查询
		List<ProductRecord> listoutputmaterial = new ArrayList<ProductRecord>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date.equals(""))&&((end_date != null || !("".equals(end_date))))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || !("".equals(start_date)))
				&& ((end_date == null ||end_date.equals("")))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null ||start_date.equals(""))
				&& (end_date == null ||end_date.equals(""))) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = productRecordMapper.selectOutputFullRecordtotalCount(
				material_no, start_date, end_date);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listoutputmaterial = inputOrOutputRecordService.selectOutputFullRecord(
				page.getStartPos(), page.getPageSize(), material_no,
				start_date, end_date);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listoutputmaterial", listoutputmaterial);
		model.addAttribute("page", page);
		model.addAttribute("material_no", material_no);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		
		//用于excel的回显查询条件
		Input HX= new Input();
		if (material_no != null && !("".equals(material_no))) {
			HX.setMaterialNo(material_no);
		}
		if (start_date != null &&!("1000-1-1".equals(start_date))) {
			HX.setShopName(start_date);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
			HX.setMaterialName(end_date);
		}
		model.addAttribute("HX", HX);
		return "outputfull";

	}

	// 成品入库查看详情===========================================
	@RequestMapping("toOutputFullRecord")
	public String toOutputFullRecord(Integer recordId, Model model)
			throws Exception {
		// 第一、二张表GetMaterial的查询
		ProductRecord outputmiddlerecord = new ProductRecord();
		outputmiddlerecord = inputOrOutputRecordService
				.selectMiddleId(recordId);
		model.addAttribute("outputmiddlerecord", outputmiddlerecord);
		return "lookoutputfull";
	}

	
	//成品入库删除记录
	@RequestMapping("DeleteOutputFullRecord")
	public String DeleteOutputFullRecord(Integer recordId)
			throws Exception {
		productRecordMapper.deleteByPrimaryKey(recordId);
		return "redirect:toOutputFullMaterialsRecord.action";
	}
	
	
	// 1、临时工价审批查询功能：分页查询
	// 临时工价审批记录分页查询
	@RequestMapping("/toTemprice")
	public String toTemprice(HttpSession session, String shopName,
			String processName, String start_date, String end_date,
			Integer pageNow, Model model) throws Exception {
		List<TemPrice> listtemprice = new ArrayList<TemPrice>();
		int totalCount = 0;

		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}
		// 领料审批记录分页查询返回行数totalCount
		totalCount = trackCardMapper.SelectByTempricetotalCount(shopName,
				processName, start_date, end_date);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listtemprice = workCardService
				.SelectByTemprice(page.getStartPos(), page.getPageSize(),
						shopName, processName, start_date, end_date);

		// controller层中使model能返回一个jsp页面
		model.addAttribute("listtemprice", listtemprice);
		model.addAttribute("shopName", shopName);
		model.addAttribute("processName", processName);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		model.addAttribute("page", page);

		// (更新查询数量的变化)车间主任需求：添加对审批数量的查询
		Integer num3 = trackCardMapper.selectTempriceCount();
		session.setAttribute("num3", num3);

		return "approve_temprice";
	}

	// 2审批or不审批功能

	// 2.1审批功能（将已审批由0到1）

	@RequestMapping("/approveTemprice")
	public String approveTemprice(WorkCard workCard, String batchNo,
			String temPrice, HttpSession session, Model model) throws Exception {
		workCardService.updateapproveTemprice(batchNo, temPrice);
		return "redirect:toTemprice.action";
	}

	// 2.2审批功能（将审批不通过由0到2）

	@RequestMapping("/notapproveTemprice")
	public String notapproveTemprice(WorkCard workCard, String batchNo,
			HttpSession session, Model model) throws Exception {

		workCardService.updatenotapproveTemprice(batchNo);
		return "redirect:toTemprice.action";

	}

	// Spc记录(查询)
	@RequestMapping("/toSpcrecord")
	public String toSpcrecord(Integer pageNow, String materialNo,
			String batchNo, String process, String characterVal, Model model) {
		List<Spc> listSpcrecord = new ArrayList<Spc>();
		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = spcMapper.selectSpcBytotalCount(materialNo, batchNo,
				process, characterVal);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listSpcrecord = spcService.selectSpcrecord(page.getStartPos(),
				page.getPageSize(), materialNo, batchNo, process, characterVal);
		model.addAttribute("listSpcrecord", listSpcrecord);
		model.addAttribute("page", page);
		model.addAttribute("materialNo", materialNo);
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("process", process);
		model.addAttribute("characterVal", characterVal);
		return "Spc_record";
	}

	// Spc记录的修改操作
	// 先查询出来，用于数据的回显
	@RequestMapping("/toupdateSpcrecord")
	public String toupdateSpcrecord(HttpServletRequest request, String batchNo,
			String process, String characterVal, Model model) throws Exception {
		// 查询Spc表的主要信息并回显
		Spc spc = spcService.selectUpdateSpcrecord(batchNo, process,
				characterVal);
		model.addAttribute("spc", spc);
		// 查询Spc_test的主要信息并回显
		List<SpcTest> listSpcTest = spcService.selectEditSpcrecord(batchNo,
				process, characterVal);
		model.addAttribute("listSpcTest", listSpcTest);
		return "editSpc_record";
	}

	@RequestMapping("/saveOrupdateSpc")
	public String saveOrupdateSpc(HttpServletRequest request, Spc spcId,
			Model model) throws Exception {
		List<SpcTest> listSpcTest = new ArrayList<SpcTest>();
		for (int i = 1; i <= 25; i++) {
			// 公共部分
			String clientMaterialNo = request.getParameter("clientMaterialNo");
			String materialNo = request.getParameter("materialNo");
			String batchNo = request.getParameter("batchNo");
			String processName = request.getParameter("processName");
			String characterVal = request.getParameter("characterVal");

			// 循环的部分
			String testId = request.getParameter("testId" + i);
			String testVal1 = request.getParameter("testVal1" + i);
			String testVal2 = request.getParameter("testVal2" + i);
			String testVal3 = request.getParameter("testVal3" + i);
			String testVal4 = request.getParameter("testVal4" + i);
			String testVal5 = request.getParameter("testVal5" + i);
			SpcTest s = new SpcTest();

			// 公共部分插入
			s.setMaterialNo(materialNo);
			s.setClientMaterialNo(clientMaterialNo);
			s.setBatchNo(batchNo);
			s.setProcessName(processName);
			s.setCharacterVal(characterVal);

			// 循环的部分插入
			s.setTestId(Integer.valueOf(testId));
			s.setTestVal1(Float.valueOf(testVal1));
			s.setTestVal2(Float.valueOf(testVal2));
			s.setTestVal3(Float.valueOf(testVal3));
			s.setTestVal4(Float.valueOf(testVal4));
			s.setTestVal5(Float.valueOf(testVal5));

			Float TestVal1 = Float.valueOf(testVal1);
			Float TestVal2 = Float.valueOf(testVal2);
			Float TestVal3 = Float.valueOf(testVal3);
			Float TestVal4 = Float.valueOf(testVal4);
			Float TestVal5 = Float.valueOf(testVal5);
			double sumX = (double) Math.round((TestVal1 + TestVal2 + TestVal3
					+ TestVal4 + TestVal5) * 100) / 100;
			s.setSumX(sumX);
			// 插入平均值
			s.setAveX((double) Math.round((sumX / 5) * 100) / 100);
			// 插入方差(取得这5个测量值每组的最大值和最小值)
			float max = 0;
			float min = 0;

			// 找出最大值赋给max
			if (TestVal1 >= TestVal2 && TestVal1 >= TestVal3
					&& TestVal1 >= TestVal4 && TestVal1 >= TestVal5) {
				max = TestVal1;
			} else if (TestVal2 >= TestVal1 && TestVal2 >= TestVal3
					&& TestVal2 >= TestVal4 && TestVal2 >= TestVal5) {
				max = TestVal2;
			} else if (TestVal3 >= TestVal1 && TestVal3 >= TestVal2
					&& TestVal3 >= TestVal4 && TestVal3 >= TestVal5) {
				max = TestVal3;
			} else if (TestVal4 >= TestVal1 && TestVal4 >= TestVal2
					&& TestVal4 >= TestVal3 && TestVal4 >= TestVal5) {
				max = TestVal4;
			} else if (TestVal5 >= TestVal1 && TestVal5 >= TestVal2
					&& TestVal5 >= TestVal3 && TestVal5 >= TestVal4) {
				max = TestVal5;
			} else {
				max = TestVal5;
			}
			// 找出最小值赋给min
			if (TestVal1 <= TestVal2 && TestVal1 <= TestVal3
					&& TestVal1 <= TestVal4 && TestVal1 <= TestVal5) {
				min = TestVal1;
			} else if (TestVal2 <= TestVal1 && TestVal2 <= TestVal3
					&& TestVal2 <= TestVal4 && TestVal2 <= TestVal5) {
				min = TestVal2;
			} else if (TestVal3 <= TestVal1 && TestVal3 <= TestVal2
					&& TestVal3 <= TestVal4 && TestVal3 <= TestVal5) {
				min = TestVal3;
			} else if (TestVal4 <= TestVal1 && TestVal4 <= TestVal2
					&& TestVal4 <= TestVal3 && TestVal4 <= TestVal5) {
				min = TestVal4;
			} else if (TestVal5 <= TestVal1 && TestVal5 <= TestVal2
					&& TestVal5 <= TestVal3 && TestVal5 <= TestVal4) {
				min = TestVal5;
			} else {
				min = TestVal5;
			}
			s.setR(Double.parseDouble(String.valueOf(max))
					- Double.parseDouble(String.valueOf(min)));
			listSpcTest.add(s);
		}
		// 先更新spc表
		spcService.updateByPrimaryKeySelective(spcId);
		// 再更新spcTest
		spcService.updateSpcTest(listSpcTest);
		return "redirect:toSpcrecord.action";
	}

	// 材料批次号显示
	@RequestMapping("/cailiaopicihaoAjax")
	public void cailiaopicihaoAjax(String clientMaterialNo, String materialNo,
			String batchNo, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String html = getDetailService.selectCailiaopicihao(clientMaterialNo,
				materialNo, batchNo);
		PrintWriter out = response.getWriter();
		out.print(html);

	}
	
	// 客户查询显示
		@RequestMapping("/clientAjax")
		public void clientAjax(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			String html = productionPlanService.selectClient();
			PrintWriter out = response.getWriter();
			out.print(html);

		}


	// 未完成计划单号查询
	@RequestMapping("/jihuadanhaoAjax")
	public void jihuadanhaoAjax(String clientMaterialNo, String materialNo,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String html = productionPlanService.selectJihuadanhao(clientMaterialNo,
				materialNo);
		PrintWriter out = response.getWriter();
		out.print(html);

	}

	// 故障查询
	@RequestMapping("/tobreakdown")
	public String tobreakdown(Integer pageNow, String batchNo,
			String processName, String assetNo, Model model) throws Exception {
		List<DailyCheck> listDailyCheck = new ArrayList<DailyCheck>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = dailycheckMapper.selectByPrimarytotalCount1(batchNo,
				processName, assetNo);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listDailyCheck = dailyCheckService.selectByPrimary1(page.getStartPos(),
				page.getPageSize(), batchNo, processName, assetNo);
		model.addAttribute("listDailyCheck", listDailyCheck);
		model.addAttribute("page", page);
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("processName", processName);
		model.addAttribute("assetNo", assetNo);
		return "breakdown";
	}

	// 次品记录记录单
	@RequestMapping("/selectWorkCard")
	public String SelectWorkCard(Integer pageNow, String produceDate,
			String produceDate1, Model model) throws Exception {
		List<WorkCard> listWorkCard = new ArrayList<WorkCard>();
// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((produceDate == null || produceDate == "")
				&& ((produceDate1 != null ||produceDate1 != ""))) {
			produceDate = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((produceDate != null || produceDate != "")
				&& ((produceDate1 == null || produceDate1 == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			produceDate1 = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((produceDate == null || produceDate == "")
				&& (produceDate1 == null || produceDate1 == "")) {
			produceDate = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			produceDate1 = sf.format(new Date());
		}

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = workCardMapper.selectWorkCardByParamtotalCount(
				produceDate, produceDate1);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listWorkCard = workCardService.selectWorkCardByParam(
				page.getStartPos(), page.getPageSize(), produceDate,
				produceDate1);
		model.addAttribute("listWorkCard", listWorkCard);
		model.addAttribute("page", page);
		model.addAttribute("produceDate", produceDate);
		model.addAttribute("produceDate1", produceDate1);

		return "workCard";

	}

	// 计划进度查询
	@RequestMapping("/selectProductionPlan")
	public String SelectProductionPlan(String plan_no,
			String client_material_no, String material_no, Model model)
			throws Exception {
		List<ShopTransition> listShopTransition1 = new ArrayList<ShopTransition>();
		listShopTransition1 = productionPlanService
				.selectShopTransitionByParam1(plan_no, client_material_no,
						material_no);
		//System.out.println(listShopTransition1.size());
		

		List<WeekPlan> listWeekPlan = new ArrayList<WeekPlan>();
		listWeekPlan = productionPlanService.selectProductionPlanByParam1(
				plan_no, client_material_no, material_no);
		if(listShopTransition1.size()==0){
			
		String	batchNo=shopPlanService.selectBatchNoByPlanNo(plan_no);
		
		listWeekPlan.get(0).setBatchNo(batchNo);
	
			Integer planId1 = listWeekPlan.get(0).getPlanId();
			
		 String shopName=shopDeliveryMapper.selectShopNameByPlanId(planId1);
		 listWeekPlan.get(0).setShopName(shopName);
				   
			model.addAttribute("listWeekPlan", listWeekPlan);
			
			return "productionproess1";
			
		}else {
			if (listWeekPlan.size() > 0) {
				// 集合中获得固定元素用 .get(0).getX
				Integer planId = listWeekPlan.get(0).getPlanId();
				Integer sendNum = null;
				if (planId != null)
					// 通过PlanId查询sendNum实交数量
					sendNum = productionPlanService
					.selectShopDeliveryByParam(planId);
				String sendNum2 = "";
				if (sendNum != null)
					sendNum2 = sendNum.toString();
				
				List<ShopTransition> listShopTransition = new ArrayList<ShopTransition>();
				listShopTransition = productionPlanService
						.selectShopTransitionByParam(plan_no, client_material_no,
								material_no);
				List<WeekPlan> listWeekPlan2 = new ArrayList<WeekPlan>();
				for (int i = 0; i < listShopTransition.size(); i++) {
					WeekPlan weekPlan = new WeekPlan();
					weekPlan.setShop2(listShopTransition.get(i).getShop2());
					weekPlan.setClient(listWeekPlan.get(0).getClient());
					weekPlan.setClientMaterialNo(listWeekPlan.get(0)
							.getClientMaterialNo());
					weekPlan.setMaterialNo(listWeekPlan.get(0).getMaterialNo());
					weekPlan.setOrderDate(listWeekPlan.get(0).getOrderDate());
					weekPlan.setPlanId(listWeekPlan.get(0).getPlanId());
					weekPlan.setPlanNo(listWeekPlan.get(0).getPlanNo());
					weekPlan.setPlanNum(listWeekPlan.get(0).getPlanNum());
					weekPlan.setQualifiedNum(listShopTransition.get(i)
							.getQualifiedNum());
					weekPlan.setSendNum(sendNum2);
					weekPlan.setBatchNo(listShopTransition.get(i).getBatchNo());
					weekPlan.setAcceptor(listShopTransition.get(i).getAcceptor());
					weekPlan.setShop1(listShopTransition.get(i).getShop1());
					weekPlan.setProvider(listShopTransition.get(i).getProvider());
					weekPlan.setSort(listWeekPlan.get(0).getSort());
					listWeekPlan2.add(weekPlan);
				}
				
				model.addAttribute("listWeekPlan", listWeekPlan2);
				
			}
			model.addAttribute("listShopTransition1", listShopTransition1);
		
			return "productionproess";
		}
		
		
		
	}

	// 产品异常单的查询
	// 产品异常单记录分页
	@RequestMapping("/selectProductAbnormal")
	public String selectProductAbnormal(Integer pageNow,String material_no,
			String client,String start_date, String end_date, Model model) throws Exception {

		List<ProductAbnormal> listProductAbnormal = new ArrayList<ProductAbnormal>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）

		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}
		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = productAbnormalMapper
				.selectProductAbnormalByParamtotalCount(client,material_no,start_date,end_date);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listProductAbnormal = productAbnormalService
				.selectProductAbnormalByParam(page.getStartPos(),page.getPageSize(),client,material_no,start_date,end_date);

		model.addAttribute("listProductAbnormal", listProductAbnormal);
		model.addAttribute("page", page);
		model.addAttribute("material_no", material_no);
		model.addAttribute("client", client);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);

		return "ProductAbnormal";
	}

	// 产品异常单的修改

	@RequestMapping("/getProductAbnormal")
	public String getProductAbnormal(Integer abnormalId, Model model)
			throws Exception {
		ProductAbnormal productAbnormal = productAbnormalService
				.selectByPrimaryKey(abnormalId);
		model.addAttribute("productAbnormal", productAbnormal);
		List<DepOpinion> listDepOpinion = new ArrayList<DepOpinion>();
		listDepOpinion = productAbnormalService.selectByAbnormalId(abnormalId);
		model.addAttribute("listDepOpinion", listDepOpinion);
		return "editproductabnormal";
	}

	@RequestMapping("/updateProductAbnormal")
	public String updateProductAbnormal(HttpServletRequest request,
			ProductAbnormal productAbnormal, Integer num1, Model model)
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

		productAbnormalService.updateByPrimaryKeySelective(productAbnormal);
		Integer abnormalId = productAbnormal.getAbnormalId();
		productAbnormalService.deleteByAbnormalId(abnormalId);
		productAbnormalService.updateAbnormalId(listDepOpinion, abnormalId);
		return "redirect:selectProductAbnormal";

	}

	// 跟踪单四个条件查询
	// 跟踪单记录分页
	@RequestMapping("/getTrackCard")
	public String getTrackCard(Integer pageNow,HttpSession session, String client, String plan_no,
			String client_material_no, String material_no, Model model)
			throws Exception {
		// 定义一个参数listTrackCard作为调用service层方法 的返回值
		List<TrackCard> listTrackCard = new ArrayList<TrackCard>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = trackCardMapper.selectTrackCardByParamtotalCount(client,
				plan_no, client_material_no, material_no);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		// 调用service的层中实现类的方法
		listTrackCard = trackCardSelectService.selectTrackCardByParam(
				page.getStartPos(), page.getPageSize(), client, plan_no,
				client_material_no, material_no);
		// controller层中使model能返回一个jsp页面，在jsp页面便于用EL表达式${}获取值，使model.addAttribute返回jsp页面输出
		model.addAttribute("listTrackCard", listTrackCard);
		model.addAttribute("page", page);
		model.addAttribute("client", client);
		model.addAttribute("plan_no", plan_no);
		model.addAttribute("client_material_no", client_material_no);
		model.addAttribute("material_no", material_no);
		return "genzongdanlist";
	}

	// 跟踪单的修改操作
	// 1.先查询三表进行回显
	@RequestMapping("/UpdateTrackCard")
	public String UpdateTrackCard(Integer cardId, Model model) throws Exception {
		// TrackCard表的查询（1表）
		TrackCard trackCard = trackCardSelectService.selectByPrimaryKey(cardId);
		model.addAttribute("trackCard", trackCard);
		// WorkCard表的查询（2表）
		List<WorkCard> listWorkCard = new ArrayList<WorkCard>();
		listWorkCard = trackCardSelectService.SelectBycardId(cardId);
		model.addAttribute("listWorkCard", listWorkCard);
		return "editgenzongdan";
	}

	// 2.再进行修改（更新）操作
	@RequestMapping("/editTrackCard")
	public String editTrackCard(HttpServletRequest request,HttpServletResponse response,
			TrackCard trackCard, Integer num1, Model model) {
		// 循环获得二表的信息
		List<WorkCard> listWorkCard = new ArrayList<WorkCard>();
		if (num1 != null) {
			for (int i = 1; i <= num1; i++) {
				// 获得前端的信息
				String trackId = request.getParameter("trackId" + i);
				String cardId3 = request.getParameter("cardId" + i);
				String shopName = request.getParameter("shopName" + i);
				String processName = request.getParameter("processName" + i);
				String operator = request.getParameter("operator" + i);
				String asset = request.getParameter("asset" + i);
				String assetState = request.getParameter("assetState" + i);
				String mold = request.getParameter("mold" + i);
				String moldState = request.getParameter("moldState" + i);
				String totalNum = request.getParameter("totalNum" + i);
				String hegeNum = request.getParameter("hegeNum" + i);
				String produceDate = request.getParameter("produceDate" + i);
				String price = request.getParameter("price" + i);
				// 新建类接受前端的信息，把信息放到类中
				WorkCard workCard = new WorkCard();

				// trackId加上强制转换（从String到Int型）
				Integer trackId1 = Integer.parseInt(trackId);
				workCard.setTrackId(trackId1);
				Integer cardId1 = Integer.parseInt(cardId3);
				workCard.setCardId(cardId1);
				workCard.setShopName(shopName);
				workCard.setProcessName(processName);
				workCard.setOperator(operator);
				workCard.setAsset(asset);
				workCard.setAssetState(assetState);
				workCard.setMold(mold);
				workCard.setMoldState(moldState);
				workCard.setTotalNum(totalNum);
				workCard.setHegeNum(hegeNum);
				workCard.setProduceDate(produceDate);
				workCard.setPrice(price);
				
				
				// 把类放到集合中去
				listWorkCard.add(workCard);
				String temp2 = "num" + (i + 1);
				// 获得前端num2,3,4....的值
				String temp3 = request.getParameter(temp2);
				// 判断num是否为空，防止为空时为空字符串，无次品时不执行下列循环
				if (temp3 != null && temp3 != "") {
					// String强制转换成int
					Integer num2 = Integer.parseInt(temp3);
					List<Cipin> listCipin = new ArrayList<Cipin>();
					for (int j = 1; j <= num2; j++) {
						// i和j已确定3表的name为唯一标示
						String cipinId = request
								.getParameter(i + "cipinId" + j);
						String cipinType = request.getParameter(i + "cipinType"
								+ j);
						String cipinSpecies = request.getParameter(i
								+ "cipinSpecies" + j);
						String cipinNum = request.getParameter(i + "cipinNum"
								+ j);
						Cipin cipin = new Cipin();

						Integer cipinId1 = Integer.parseInt(cipinId);
						cipin.setCipinId(cipinId1);

						// 将2表的主键值给3表外键（要不直接给3表加外键，会导致，没有外键的三表会有空的信息）
						cipin.setTrackId(workCard.getTrackId());
						cipin.setCipinType(cipinType);
						cipin.setCipinSpecies(cipinSpecies);
						cipin.setCipinNum(cipinNum);

						listCipin.add(cipin);
						trackCardSelectService.updateByCipinId(listCipin);
					}
				}
				trackCardSelectService.updateByTrackId(listWorkCard);
			}
			// 更新trackCard表的信息
			trackCardSelectService.updateByPrimaryKey(trackCard);
		//	return "redirect:getTrackCard";
				
			return "genzongdanlist";
		} else {
			// 更新trackCard表的信息
			trackCardSelectService.updateByPrimaryKey(trackCard);
			// 跟踪单的后两个表的信息android录入，所以为空时提交会报错，防止未录入时报错
			return "genzongdanlist";
		}
	}

	
	   //删除跟踪单
		@RequestMapping("/DeleteTrackCard")
		public String DeleteTrackCard(Integer cardId, Model model) throws Exception {
			// 更新trackCard表的信息
			trackCardSelectService.deleteByPrimaryKey(cardId);
			return "genzongdanlist";
		}
		
		
		
	// 1.吴永-根据客户和产品名称、计划单号、批次号查询ProductTest表的信息
	@RequestMapping("/SelectProductTest")
	public String SelectProductTest(Integer pageNow, String client,
			String materialNo, String start_date, String end_date, Model model)
			throws Exception {
		List<ProductTest> listproductTest = new ArrayList<ProductTest>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
		
			Date date=new Date();//取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
			date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			end_date = formatter.format(date);
		}
		
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
		
			Date date=new Date();//取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动
			date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			end_date = formatter.format(date);
		}
		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = productTestMapper.selectProductTestByParamtotalCount(
				client, materialNo, start_date, end_date);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listproductTest = productTestService.selectProductTestByParam(
				page.getStartPos(), page.getPageSize(), client, materialNo,
				start_date, end_date);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listproductTest", listproductTest);
		model.addAttribute("page", page);
		model.addAttribute("client", client);
		model.addAttribute("materialNo", materialNo);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		// 返回到指定的jsp页面
		return "productTestList";

	}

	// 8.吴永-根据车间和操作工、图号、物料号进行生产记录查询，分页
	// 存到生产记录查询的新建pojo类ProductionRecordInquiry。
	@RequestMapping("/SelectProductionRecordInquiry")
	public String SelectProductionRecordInquiry(Integer pageNow,
			String shop_name, String operator, String client_material_no,
			String material_no, Model model) throws Exception {
		List<ProductionRecordInquiry> listproductionRecordInquiry = new ArrayList<ProductionRecordInquiry>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = trackCardMapper
				.selectProductionRecordInquiryParamtotalCount(shop_name,
						operator, client_material_no, material_no);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listproductionRecordInquiry = productTestService
				.selectProductionRecordInquiryParam(page.getStartPos(),
						page.getPageSize(), shop_name, operator,
						client_material_no, material_no);

		// controller层中使model能返回一个jsp页面
		model.addAttribute("listproductionRecordInquiry",
				listproductionRecordInquiry);
		model.addAttribute("page", page);
		model.addAttribute("shop_name", shop_name);
		model.addAttribute("operator", operator);
		model.addAttribute("client_material_no", client_material_no);
		model.addAttribute("material_no", material_no);
		// 返回到指定的jsp页面
		return "productRecord";

	}

	// 查询（根据图号，物料号，模具名称）
	// 模具出入库记录分页查询
	@RequestMapping("/moldRecord")
	public String moldRecord(Integer pageNow, String materialNo,
			String batchNo,String moldNo, Model model) {
		List<MoldRecord> listmoldrecord = new ArrayList<MoldRecord>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = moldRecordMapper.selectByPrimarytotalCount(materialNo, batchNo, moldNo);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listmoldrecord = moldRecordService.selectByPrimary(page.getStartPos(),
				page.getPageSize(), materialNo,batchNo,moldNo);
		model.addAttribute("listmoldrecord", listmoldrecord);
		model.addAttribute("page", page);
		model.addAttribute("materialNo", materialNo);
		model.addAttribute("batchNo", batchNo);		
		model.addAttribute("moldNo", moldNo);
		return "moldrecord";

	}

	// 1、领辅料审批查询功能：条件（根据物料号、起始日期、截止日期查询）分页查询
	// 领辅料审批记录分页查询
	@RequestMapping("/toApproveSecMaterial")
	public String toApproveSecMaterial(HttpSession session, Integer pageNow,
			String shopName, String start_date, String end_date, Model model)
			throws Exception {
		List<GetSecMaterials> listmaterial = new ArrayList<GetSecMaterials>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）

		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}

		int totalCount = 0;
		// 领料审批记录分页查询返回行数totalCount
		totalCount = getSecMaterialsMapper
				.selectGetSecMaterialsByParamtotalCount(shopName, start_date,
						end_date);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listmaterial = secMaterialService.selectGetSecMaterialsByParam(
				page.getStartPos(), page.getPageSize(), shopName, start_date,
				end_date);

		// controller层中使model能返回一个jsp页面
		model.addAttribute("listmaterial", listmaterial);
		model.addAttribute("page", page);
		model.addAttribute("shopName", shopName);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);

		// (更新查询数量的变化)车间主任需求：添加对审批数量的查询
		Integer num2 = getSecMaterialsMapper.selectSecGetMaterialCount();
		session.setAttribute("num2", num2);

		return "approve_sec_material";
	}

	// 2查看功能

	// 2.1查看功能（两个表通过内键getMaterialId查询）
	@RequestMapping("/lookSecMaterial")
	public String lookSecMaterial(Integer getMaterialsId, Model model)
			throws Exception {

		// 第一张表GetMaterial的查询
		GetSecMaterials getSecMaterials = secMaterialService
				.selectGetSecMaterialsKey(getMaterialsId);
		model.addAttribute("getSecMaterials", getSecMaterials);

		// 第二张表GetDetail的查询
		List<GetSecDetail> listGetSecDetail = new ArrayList<GetSecDetail>();
		listGetSecDetail = secMaterialService.selectByKey(getMaterialsId);
		model.addAttribute("listGetSecDetail", listGetSecDetail);

		return "lookSecmaterial";
	}

	// 3审批or不审批功能

	// 3.1审批功能（将已审批由0到1）

	@RequestMapping("/approveSecMaterial")
	public String approveSecMaterial(GetMaterial getMaterial,
			Integer getMaterialsId, HttpSession session, Model model)
			throws Exception {

		// 得到登录的人赋于审批的人
		Person person = (Person) session.getAttribute("user");
		String approver = person.getPersonName();

		secMaterialService.updateByKey(getMaterialsId, approver);
		return "redirect:toApproveSecMaterial.action";

	}

	// 3.2审批功能（将审批不通过由0到2）

	@RequestMapping("/notapproveSecMaterial")
	public String notapproveSecMaterial(GetMaterial getMaterial,
			Integer getMaterialsId, HttpSession session, Model model)
			throws Exception {

		// 得到登录的人赋于审批的人
		Person person = (Person) session.getAttribute("user");
		String approver = person.getPersonName();

		secMaterialService.updatenotByKey(getMaterialsId, approver);
		return "redirect:toApproveSecMaterial.action";

	}

	// 1、领料审批查询功能：条件（根据物料号、起始日期、截止日期查询）分页查询
	// 领料审批记录分页查询
	@RequestMapping("/SelectMaterial")
	public String SelectMaterial(HttpSession session, Integer pageNow,
			String material_no, String start_date, String end_date, Model model)
			throws Exception {
		List<GetMaterial> listmaterial = new ArrayList<GetMaterial>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）

		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}

		int totalCount = 0;
		// 领料审批记录分页查询返回行数totalCount
		totalCount = getMaterialMapper.selectGetMaterialByParamtotalCount(
				material_no, start_date, end_date);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listmaterial = getMaterialService.selectGetMaterialByParam(
				page.getStartPos(), page.getPageSize(), material_no,
				start_date, end_date);

		// controller层中使model能返回一个jsp页面
		model.addAttribute("listmaterial", listmaterial);
		model.addAttribute("page", page);
		model.addAttribute("material_no", material_no);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);

		// (更新查询数量的变化)车间主任需求：添加对审批数量的查询
		Integer num1 = getMaterialMapper.selectGetMaterialCount();
		session.setAttribute("num1", num1);

		return "selectMaterials";
	}

	// 2查看功能

	// 2.1查看功能（两个表通过内键getMaterialId查询）
	@RequestMapping("/lookMaterial")
	public String lookMaterial(Integer getMaterialId, Model model)
			throws Exception {

		// 第一张表GetMaterial的查询
		GetMaterial getMaterial = getMaterialService
				.selectByPrimaryKey(getMaterialId);
		model.addAttribute("getMaterial", getMaterial);

		// 第二张表GetDetail的查询
		List<GetDetail> listGetDetail = new ArrayList<GetDetail>();
		listGetDetail = getMaterialService.selectByKey(getMaterialId);
		model.addAttribute("listGetDetail", listGetDetail);

		return "lookMaterials";
	}

	// 3审批or不审批功能

	// 3.1审批不通过功能
	@RequestMapping("/notapproveMaterial")
	public String notapproveMaterial(GetMaterial getMaterial,
			Integer getMaterialId, HttpSession session, Model model)
			throws Exception {
		// 得到登录的人赋于审批的人
		Person person = (Person) session.getAttribute("user");
		String approver = person.getPersonName();

		getMaterialService.updatenotByKey(getMaterialId, approver);
		return "redirect:SelectMaterial.action";
	}

	// 3.2审批功能（将是否审批由0到1）

	@RequestMapping("/approveMaterial")
	public String approve(GetMaterial getMaterial, Integer getMaterialId,
			HttpSession session, Model model) throws Exception {

		// 得到登录的人赋于审批的人
		Person person = (Person) session.getAttribute("user");
		String approver = person.getPersonName();

		getMaterialService.updateByKey(getMaterialId, approver);
		return "redirect:SelectMaterial.action";

	}

	// 1.工资查询
	// 工资单记录查询分页
	@RequestMapping("/SalarySelect")
	public String SalarySelect(Integer pageNow, String operator,
			String shop_name, String date, Model model) {
		List<WorkCard> listsalary = new ArrayList<WorkCard>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = workCardMapper.SelectByPrimarytotalCount(operator,
				shop_name, date);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listsalary = salaryService.SelectByPrimary(page.getStartPos(),
				page.getPageSize(), operator, shop_name, date);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listsalary", listsalary);
		model.addAttribute("page", page);
		model.addAttribute("operator", operator);
		model.addAttribute("shop_name", shop_name);
		model.addAttribute("date", date);

		// 用于回显得查询条件供excel下载用
		WorkCard HX = new WorkCard();
		if (operator != null && !("".equals(operator))) {
			HX.setOperator(operator);
		}
		if (shop_name != null && !("".equals(shop_name))) {
			HX.setShopName(shop_name);
		}
		if (date != null && !("".equals(date))) {
			HX.setProduceDate(date);
		}
		model.addAttribute("HX", HX);
		return "salary";
	}

	// 2.工资详细信息查询
	// 工资单详细记录分页
	@RequestMapping("/SalaryDetailSelect")
	public String SalaryDetailSelect(Integer pageNow, String shop_name,
			String operator, String date, Model model) {

		// 2.第一、二张表的查询
		List<Salary> listdetailsalary = new ArrayList<Salary>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = trackCardMapper.SelectByPrimaryDatetotalCount(shop_name,
				operator, date);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listdetailsalary = salaryService.SelectByPrimaryDate(
				page.getStartPos(), page.getPageSize(), shop_name, operator,
				date);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listdetailsalary", listdetailsalary);
		model.addAttribute("page", page);
		model.addAttribute("operator", operator);
		model.addAttribute("shop_name", shop_name);
		model.addAttribute("date", date);

		// 用于回显得查询条件供excel下载用
		Salary HX = new Salary();
		if (operator != null && !("".equals(operator))) {
			HX.setOperator(operator);
		}
		if (shop_name != null && !("".equals(shop_name))) {
			HX.setShopName(shop_name);
		}
		if (date != null && !("".equals(date))) {
			HX.setProduceDate(date);
		}
		model.addAttribute("HX", HX);

		return "salary_detail";
	}

	// 3.工资单信息的excel导出
	@RequestMapping("/Salaryexport")
	public void Salaryexport(HttpServletResponse response,
			String operator, String shop_name, String date) throws Exception {

		// 文件名
		String fileName = "工资单信息.xlsx";
		if (date == null && shop_name == null && operator == null) {

			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
		} else {
			response.setHeader("Content-disposition",
					"attachment;filename="+ date+ shop_name+ operator+ new String(fileName.getBytes("gb2312"),
									"ISO8859-1")); // 设置文件头编码格式
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
		}

		// 文件标题栏
		String[] cellTitle = { "操作工", "工资" };
		try {

			// 声明一个工作薄
			XSSFWorkbook workBook = null;
			workBook = new XSSFWorkbook();

			// 生成一个表格
			XSSFSheet sheet = workBook.createSheet();
			workBook.setSheetName(0, "工资单");

			// 创建表格标题行 第2行(循环将标题值赋于第1行)
			XSSFRow titleRow = sheet.createRow(1);
			for (int i = 0; i < cellTitle.length; i++) {
				titleRow.createCell(i).setCellValue(cellTitle[i]);
			}

			// 创建行
			XSSFRow row = sheet.createRow((short) 0);
			// 创建单元格
			XSSFCell cell = null;

			// 第一行标题栏
			row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue(date);
			cell = row.createCell(1);
			cell.setCellValue(shop_name);
			cell = row.createCell(2);
			cell.setCellValue(operator);
			cell = row.createCell(3);
			cell.setCellValue("工资单信息");

			// 设置居中
			XSSFCellStyle cellStyle = workBook.createCellStyle();
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

			// 设置列宽
			sheet.setColumnWidth(0, 25 * 256);
			sheet.setColumnWidth(1, 30 * 256);
			sheet.setColumnWidth(3, 10 * 256);

			// 4.历史数据，业务数据，不用关注
			List<WorkCard> listsalary = new ArrayList<WorkCard>();
			listsalary = salaryService.SelectexcelByPrimary(operator, shop_name, date);

			if (listsalary != null && listsalary.size() > 0) {

				// 5.将历史数据添加到单元格中 (先列后行)
				for (int j = 0; j < listsalary.size(); j++) {
					row = sheet.createRow(j + 2);
					cell = row.createCell(0);
					cell.setCellValue(listsalary.get(j).getOperator());
					double gzd = 0.0;

					if (listsalary.get(j).getShopName().equals("仪表工段")) {

						if (listsalary.get(j).getPrice() != null
								&& !("".equals(listsalary.get(j).getPrice()))) {
							gzd = (Double.valueOf(listsalary.get(j)
									.getHegeNum()) + Double.valueOf(listsalary
									.get(j).getTotalCipinNum()))
									* Double.valueOf(listsalary.get(j)
											.getPrice());

							cell = row.createCell(1);
							cell.setCellValue(String.valueOf(gzd));
						} else {
							cell = row.createCell(1);
							cell.setCellValue("临时工价未审批!/bom无工价");
						}

					} else {

						if (listsalary.get(j).getPrice() != null
								&& !("".equals(listsalary.get(j).getPrice()))) {
							gzd = Double
									.valueOf(listsalary.get(j).getHegeNum())
									* Double.valueOf(listsalary.get(j)
											.getPrice());

							cell = row.createCell(1);
							cell.setCellValue(String.valueOf(gzd));
						} else {

							cell = row.createCell(1);
							cell.setCellValue("临时工价未审批!/bom无工价");
						}

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

	// 3.工资单详细信息的excel导出
	@RequestMapping("/Salarydetailexport")
	public void Salarydetailexport(Integer pageNow,
			HttpServletResponse response, String operator, String shop_name,
			String date) throws Exception {
		
		// 文件名
		String fileName = "工资单详细信息.xlsx";
		if (date == null && shop_name == null && operator == null) {
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
		} else {
			response.setHeader("Content-disposition",
					"attachment;filename="+ date+ shop_name+ operator
							+ new String(fileName.getBytes("gb2312"),"ISO8859-1")); // 设置文件头编码格式
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
		}

		// 文件标题栏
		String[] cellTitle = { "生产日期", "批次号", "图号", "物料号", "车间名称", "产品规格", "工序","操作工", "合格数量", "工价", "料废工资", "工资"
				,"工废种类1","工废种类2", "工废种类3", "工废种类4", "工废种类5", "工废种类6", "工废种类7", "工废种类8", "工废种类9", "工废种类10", "工废种类11", "工废种类12"};
		try {
			// 声明一个工作薄
			XSSFWorkbook workBook = null;
			workBook = new XSSFWorkbook();

			// 生成一个表格
			XSSFSheet sheet = workBook.createSheet();
			workBook.setSheetName(0, "工资单详细信息");

			// 创建表格标题行 第一行(循环将标题值赋于第一行)
			XSSFRow titleRow = sheet.createRow(1);
			for (int i = 0; i < cellTitle.length; i++) {
				titleRow.createCell(i).setCellValue(cellTitle[i]);
			}

			// 设置居中
			XSSFCellStyle cellStyle = workBook.createCellStyle();
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

			// 设置列宽
			sheet.setColumnWidth(0, 20 * 256);
			sheet.setColumnWidth(1, 20 * 256);
			sheet.setColumnWidth(2, 20 * 256);
			sheet.setColumnWidth(3, 20 * 256);
			sheet.setColumnWidth(5, 25 * 256);
			sheet.setColumnWidth(6, 20 * 256);
			sheet.setColumnWidth(7, 25 * 256);
			sheet.setColumnWidth(10, 30 * 256);
			sheet.setColumnWidth(11, 30 * 256);
			
			sheet.setColumnWidth(12, 15 * 256);
			sheet.setColumnWidth(13, 15 * 256);
			sheet.setColumnWidth(14, 15 * 256);
			sheet.setColumnWidth(15, 15 * 256);
			sheet.setColumnWidth(16, 15 * 256);
			sheet.setColumnWidth(17, 15 * 256);
			sheet.setColumnWidth(18, 15 * 256);
			sheet.setColumnWidth(19, 15 * 256);
			sheet.setColumnWidth(20, 15 * 256);
			sheet.setColumnWidth(21, 15 * 256);
			sheet.setColumnWidth(22, 15 * 256);
			sheet.setColumnWidth(23, 15 * 256);
			// 创建行
			XSSFRow row = sheet.createRow((short) 0);
			// 创建单元格
			XSSFCell cell = null;

			// 第一行标题栏
			row = sheet.createRow(0);
			cell = row.createCell(0);
			cell.setCellValue(date);
			cell = row.createCell(1);
			cell.setCellValue(shop_name);
			cell = row.createCell(2);
			cell.setCellValue(operator);
			cell = row.createCell(3);
			cell.setCellValue("工资单详细信息");

			// 4.历史数据，业务数据，不用关注
			List<Salary> listdetailsalary = new ArrayList<Salary>();
			listdetailsalary = salaryService.SelectexcelByPrimaryDate(shop_name,operator, date);
			double gzd = 0.0;
			double lfgz = 0.0;

			if (listdetailsalary != null && listdetailsalary.size() > 0) {

				// 5.将历史数据添加到单元格中 (先列后行)
				for (int j = 0; j < listdetailsalary.size(); j++) {
					row = sheet.createRow(j + 2);
					cell = row.createCell(0);
					cell.setCellValue(listdetailsalary.get(j).getProduceDate());
					cell = row.createCell(1);
					cell.setCellValue(listdetailsalary.get(j)
							.getBatchNo());
					cell = row.createCell(2);
					cell.setCellValue(listdetailsalary.get(j)
							.getClientMaterialNo());
					cell = row.createCell(3);
					cell.setCellValue(listdetailsalary.get(j).getMaterialNo());
					cell = row.createCell(4);
					cell.setCellValue(listdetailsalary.get(j).getShopName());
					cell = row.createCell(5);
					cell.setCellValue(listdetailsalary.get(j).getProductSpec());
					cell = row.createCell(6);
					cell.setCellValue(listdetailsalary.get(j).getProcessName());
					cell = row.createCell(7);
					cell.setCellValue(listdetailsalary.get(j).getOperator());
					
					//合格品数
					cell = row.createCell(8);
					cell.setCellValue(listdetailsalary.get(j).getHegeNum());
					
					//工价显示
					if (listdetailsalary.get(j).getPrice() != null&& (!("".equals(listdetailsalary.get(j).getPrice())))){
					cell = row.createCell(9);
					cell.setCellValue(listdetailsalary.get(j).getPrice());
					}else{
					cell = row.createCell(9);
					cell.setCellValue("无");
					}
					// 料废工资
					if (listdetailsalary.get(j).getShopName().equals("仪表工段")) {
						if (listdetailsalary.get(j).getPrice() != null
								&& (!("".equals(listdetailsalary.get(j)
										.getPrice())))) {
							double lfgz1 = Double.valueOf(listdetailsalary.get(
									j).getTotalCipinNum())
									* Double.valueOf(listdetailsalary.get(j)
											.getPrice());
							lfgz = (double) Math.round((lfgz1) * 1000) / 1000;
							cell = row.createCell(10);
							cell.setCellValue(String.valueOf(lfgz) + "");

						} else {
							cell = row.createCell(10);
							cell.setCellValue("临时工价未审批！/bom无工价");
						}

					} else {
						lfgz = 0.0;
						cell = row.createCell(10);
						cell.setCellValue(String.valueOf(lfgz) + "");
					}
					

					// 总工资（要考虑料废工资）
					if (listdetailsalary.get(j).getShopName().equals("仪表工段")) {
						if (listdetailsalary.get(j).getPrice() != null
								&& !("".equals(listdetailsalary.get(j)
										.getPrice()))) {
							gzd = (Double.valueOf(listdetailsalary.get(j)
									.getHegeNum()) + Double
									.valueOf(listdetailsalary.get(j)
											.getTotalCipinNum()))
									* Double.valueOf(listdetailsalary.get(j)
											.getPrice());
							cell = row.createCell(11);
							cell.setCellValue(String.valueOf(gzd) + "");
						} else {
							cell = row.createCell(11);
							cell.setCellValue("临时工价未审批!/bom无工价");
						}

						
					} else {
						if (listdetailsalary.get(j).getPrice() != null
								&& !("".equals(listdetailsalary.get(j)
										.getPrice()))) {
							gzd = Double.valueOf(listdetailsalary.get(j)
									.getHegeNum())
									* Double.valueOf(listdetailsalary.get(j)
											.getPrice());
							cell = row.createCell(11);
							cell.setCellValue(String.valueOf(gzd) + "");
						} else {
							cell = row.createCell(11);
							cell.setCellValue("临时工价未审批!/bom无工价");
						}
					}

					 //工废种类和数目
					for(int i=0;i<listdetailsalary.get(j).getCipin().size();i++){
						cell = row.createCell(12+i);
						cell.setCellValue(listdetailsalary.get(j).getCipin().get(i).getCipinSpecies()+"("
						+listdetailsalary.get(j).getCipin().get(i).getCipinNum()+")");
						
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

	// 1.领辅料单查询//领辅料记录分页
	// 领辅料记录分页
	@RequestMapping("/toInputSecMaterialsRecord")
	public String toInputSecMaterialsRecord(Integer pageNow, String shopName,
			String start_date, String end_date, Model model) {
		// 根据物料号查询是否二表的半成品批次号存在

		// 1.1第一、二张表的查询
		List<InputSec> listinputmaterial = new ArrayList<InputSec>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date.equals(""))&&((end_date != null || !("".equals(end_date))))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || !("".equals(start_date)))
				&& ((end_date == null ||end_date.equals("")))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null ||start_date.equals(""))
				&& (end_date == null ||end_date.equals(""))) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = getSecMaterialsMapper.selectinputSecGetMaterialtotalCount(
				shopName, start_date, end_date);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listinputmaterial = secMaterialService.selectinputSecGetMaterial(
				page.getStartPos(), page.getPageSize(), shopName, start_date,
				end_date);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listinputmaterial", listinputmaterial);

		model.addAttribute("page", page);
		model.addAttribute("shopName", shopName);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		//用于excel的回显查询条件
		Input HX= new Input();
		if (shopName != null && !("".equals(shopName))) {
			HX.setShopName(shopName);
		}
		if (start_date != null &&!("1000-1-1".equals(start_date))) {
			HX.setCailiaoMc(start_date);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
			HX.setMaterialName(end_date);
		}
		model.addAttribute("HX", HX);
		return "input_secrecord";

	}

	//1.1删除领辅料记录
	@RequestMapping("DeleteInputSecRecord")
	public String DeleteInputSecRecord(Integer getMaterialsId, Integer detailId) throws Exception {
		// 删除第二张表
		secMaterialService.deleteInputSecGetdetailRecord(detailId);
		// 删除第一张表
		secMaterialService.deleteInputSecGetMaterialRecord(getMaterialsId);
		return "redirect:toInputSecMaterialsRecord.action";
		
	}
	
	
	// 2.退辅料单查询//退辅料记录分页
	// 退辅料记录分页
	@RequestMapping("/toOutputSecMaterialsRecord")
	public String toOutputSecMaterialsRecord(Integer pageNow,
			String reshopName, String start_date, String end_date, Model model) {
		// 根据物料号查询是否二表的半成品批次号存在

		// 1.1第一、二张表的查询
		List<GetSecDetail> listoutputsecmaterial = new ArrayList<GetSecDetail>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date.equals(""))&&((end_date != null || !("".equals(end_date))))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || !("".equals(start_date)))
				&& ((end_date == null ||end_date.equals("")))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null ||start_date.equals(""))
				&& (end_date == null ||end_date.equals(""))) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = getSecDetailMapper.selectoutputSecGetMaterialtotalCount(
				reshopName, start_date, end_date);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listoutputsecmaterial = secMaterialService.selectoutputSecGetMaterial(
				page.getStartPos(), page.getPageSize(), reshopName, start_date,
				end_date);

		// controller层中使model能返回一个jsp页面
		model.addAttribute("listoutputsecmaterial", listoutputsecmaterial);

		model.addAttribute("page", page);
		model.addAttribute("reshopName", reshopName);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		//用于excel的回显查询条件
		Input HX= new Input();
		if (reshopName != null && !("".equals(reshopName))) {
			HX.setShopName(reshopName);
		}
		if (start_date != null &&!("1000-1-1".equals(start_date))) {
			HX.setCailiaoMc(start_date);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
			HX.setMaterialName(end_date);
		}
		model.addAttribute("HX", HX);
		return "output_secrecord";

	}

	//2.1删除领辅料记录
		@RequestMapping("DeleteOutputSecRecord")
		public String DeleteOutputSecRecord(Integer getMaterialsId, Integer detailId) throws Exception {
			// 删除第二张表
			secMaterialService.deleteInputSecGetdetailRecord(detailId);
			// 删除第一张表
			secMaterialService.deleteInputSecGetMaterialRecord(getMaterialsId);
			return "redirect:toOutputSecMaterialsRecord.action";
			
		}
		
	// 1.领料单查询//领原材料记录分页
	// 领原材料记录分页
	@RequestMapping("/InputMaterialsRecord")
	public String InputMaterialsRecord(Integer pageNow, String material_no,
			String start_date, String end_date, Model model) {
		// 根据物料号查询是否二表的半成品批次号存在

		// 1.1第一、二张表的查询
		List<Input> listinputmaterial = new ArrayList<Input>();
		// 用于查询条件未输时的判断查询

		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date.equals(""))&&((end_date != null || !("".equals(end_date))))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || !("".equals(start_date)))
				&& ((end_date == null ||end_date.equals("")))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null ||start_date.equals(""))
				&& (end_date == null ||end_date.equals(""))) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = getMaterialMapper.selectinputGetMaterialtotalCount(
				material_no, start_date, end_date);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listinputmaterial = inputOrOutputRecordService.selectinputGetMaterial(
				page.getStartPos(), page.getPageSize(), material_no,
				start_date, end_date);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listinputmaterial", listinputmaterial);

		model.addAttribute("page", page);
		model.addAttribute("material_no", material_no);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		//用于excel的回显查询条件
		Input HX= new Input();
		if (material_no != null && !("".equals(material_no))) {
			HX.setMaterialNo(material_no);
		}
		if (start_date != null &&!("1000-1-1".equals(start_date))) {
			HX.setShopName(start_date);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
			HX.setMaterialName(end_date);
		}
		model.addAttribute("HX", HX);
		
		return "inputrecord";

	}

	// 退原材料记录查询分页
	// 2.退料单查询
	@RequestMapping("/OutputMaterialsRecord")
	public String OutputMaterialsRecord(Integer pageNow, String material_no,
			String start_date, String end_date, Model model) {
		// 第一、二张表的查询
		List<Output> listoutputmaterial = new ArrayList<Output>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date.equals(""))&&((end_date != null || !("".equals(end_date))))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || !("".equals(start_date)))
				&& ((end_date == null ||end_date.equals("")))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null ||start_date.equals(""))
				&& (end_date == null ||end_date.equals(""))) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = getMaterialMapper.selectoutputGetMaterialtotalCount(
				material_no, start_date, end_date);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listoutputmaterial = inputOrOutputRecordService
				.selectoutputGetMaterial(page.getStartPos(),
						page.getPageSize(), material_no, start_date, end_date);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listoutputmaterial", listoutputmaterial);
		model.addAttribute("page", page);
		model.addAttribute("material_no", material_no);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		//用于excel的回显查询条件
		Output HX= new Output();
			if (material_no != null && !("".equals(material_no))) {
				HX.setMaterialNo(material_no);
			}
			if (start_date != null &&!("1000-1-1".equals(start_date))) {
				HX.setShopName(start_date);
			}
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
				HX.setMaterialName(end_date);
			}
			model.addAttribute("HX", HX);
		return "outputrecord";

	}

	// 3.领退料查看详情
	// 3.1.1领料详情页面
	@RequestMapping("toInputRecord")
	public String toInputRecord(Integer getMaterialId, Integer detailId,
			Model model) throws Exception {
		// 第一、二张表GetMaterial的查询
		List<Input> inputrecord = new ArrayList<Input>();
		inputrecord = inputOrOutputRecordService.selectId(getMaterialId,
				detailId);
		model.addAttribute("inputrecord", inputrecord);
		return "lookinputrecord";
	}

	
	// 3.1.2删除领料记录
		@RequestMapping("DeleteInputRecord")
		public String DeleteInputRecord(Integer getMaterialId, Integer detailId) throws Exception {
			// 删除第二张表
			inputOrOutputRecordService.deleteByGetdetailPrimaryKey(detailId);
			// 删除第一张表
			inputOrOutputRecordService.deleteByGematerialPrimaryKey(getMaterialId);
			return "redirect:InputMaterialsRecord.action";
			
		}
		
		
	// 3.2.1退料详情页面
	@RequestMapping("toOutputRecord")
	public String toOutputRecord(Integer getMaterialId, Integer detailId,
			Model model) throws Exception {
		// 第一、二张表GetMaterial的查询
		List<Output> outputrecord = new ArrayList<Output>();
		outputrecord = inputOrOutputRecordService.selectById(getMaterialId,
				detailId);
		model.addAttribute("outputrecord", outputrecord);
		return "lookoutputrecord";
	}

	
	// 3.2.2删除退料记录
		@RequestMapping("DeleteOutputRecord")
		public String DeleteOutputRecord(Integer getMaterialId, Integer detailId) throws Exception {
			// 删除第二张表
			inputOrOutputRecordService.deleteByGetdetailPrimaryKey(detailId);
			// 删除第一张表
			inputOrOutputRecordService.deleteByGematerialPrimaryKey(getMaterialId);
			return "redirect:OutputMaterialsRecord.action";
		}
			
			
	// 4.原材料外协出库的excel导出
		@RequestMapping("/toMaterialexcel")
		public void toMaterialexcel(HttpServletResponse response,
				String material_no, String start_date, String end_date) throws Exception {


		    Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			// 文件名
			String fileName = "-原材料外协单据.xlsx";
			if (material_no == null && start_date == null && end_date == null) {

				response.setHeader("Content-disposition", "attachment;filename="
						+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			} else {
				response.setHeader("Content-disposition",
						"attachment;filename="+df.format(day)+ material_no+ new String(fileName.getBytes("gb2312"),
										"ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			}

			// 文件标题栏
			String[] cellTitle = { "图号", "物料号","批次号","产品名称","产品规格","原材料批次号","材料名称","材料编号","数量","领料日期"};
			try {

				// 声明一个工作薄
				XSSFWorkbook workBook = null;
				workBook = new XSSFWorkbook();

				// 生成一个表格
				XSSFSheet sheet = workBook.createSheet();
				workBook.setSheetName(0, "原材料外协单据");

				// 创建表格标题行 第2行(循环将标题值赋于第1行)
				XSSFRow titleRow = sheet.createRow(1);
				for (int i = 0; i < cellTitle.length; i++) {
					titleRow.createCell(i).setCellValue(cellTitle[i]);
				}

				// 创建行
				XSSFRow row = sheet.createRow((short) 0);
				// 创建单元格
				XSSFCell cell = null;

				// 第一行标题栏
				row = sheet.createRow(0);
				cell = row.createCell(2);
				cell.setCellValue(material_no);
				cell = row.createCell(3);
				cell.setCellValue(df.format(day));
				cell = row.createCell(4);
				cell.setCellValue("原材料外协单据");

				// 设置居中
				XSSFCellStyle cellStyle = workBook.createCellStyle();
				cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

				// 设置列宽
				sheet.setColumnWidth(0, 20 * 256);
				sheet.setColumnWidth(1, 20 * 256);
				sheet.setColumnWidth(2, 20 * 256);
				sheet.setColumnWidth(3, 20 * 256);
				sheet.setColumnWidth(4, 20 * 256);
				sheet.setColumnWidth(5, 25 * 256);
				sheet.setColumnWidth(6, 20 * 256);
				sheet.setColumnWidth(7, 20 * 256);
				sheet.setColumnWidth(9, 20 * 256);

				// 4.历史数据，业务数据，不用关注
				List<Input> listinputmaterial = new ArrayList<Input>();
				// 用于查询条件未输时的判断查询
				// 1、起始为空赋给一个特别小的值（1000-1-1）
				if ((start_date == null || start_date == "")
						&& ((end_date != null || end_date != ""))) {
					start_date = "1000-1-1";
				}
				// 2、截止为空赋给一个特别大的值（今天）
				if ((start_date != null || start_date != "")
						&& ((end_date == null || end_date == ""))) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());

				}
				// 3、起始截止都为空两个都赋值
				if ((start_date == null || start_date == "")
						&& (end_date == null || end_date == "")) {
					start_date = "1000-1-1";
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());
				}
				listinputmaterial = inputOrOutputRecordService.selectexcelinputGetMaterial( material_no,
						start_date, end_date);
				
				if (listinputmaterial != null && listinputmaterial.size() > 0) {

					// 5.将历史数据添加到单元格中 (先列后行)
					for (int j = 0; j < listinputmaterial.size(); j++) {
						row = sheet.createRow(j + 2);
						cell = row.createCell(0);
						cell.setCellValue(listinputmaterial.get(j).getClientMaterialNo());
						cell = row.createCell(1);
						cell.setCellValue(listinputmaterial.get(j).getMaterialNo());
						cell = row.createCell(2);
						cell.setCellValue(listinputmaterial.get(j).getBatchNo());
						cell = row.createCell(3);
						cell.setCellValue(listinputmaterial.get(j).getMaterialName());
						cell = row.createCell(4);
						cell.setCellValue(listinputmaterial.get(j).getProductSpec());
						cell = row.createCell(5);
						cell.setCellValue(listinputmaterial.get(j).getMaterialBatchNo());
						cell = row.createCell(6);
						cell.setCellValue(listinputmaterial.get(j).getCailiaoMc());
						cell = row.createCell(7);
						cell.setCellValue(listinputmaterial.get(j).getCailiaoBh());
						cell = row.createCell(8);
						cell.setCellValue(listinputmaterial.get(j).getMaterialNum());
						cell = row.createCell(9);
						cell.setCellValue(listinputmaterial.get(j).getGetDate());
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
			
			
	// 1.半成品出库查询
	// 半成品出库记录分页
	@RequestMapping("/InputMiddleMaterialsRecord")
	public String InputMiddleMaterialsRecord(Integer pageNow,
			String material_no, String start_date, String end_date, Model model) {

		// 1.1第一、二张表的查询
		List<ProductRecord> listinputmaterial = new ArrayList<ProductRecord>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = productRecordMapper.selectInputMiddleRecordtotalCount(
				material_no, start_date, end_date);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listinputmaterial = inputOrOutputRecordService.selectInputMiddleRecord(
				page.getStartPos(), page.getPageSize(), material_no,
				start_date, end_date);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listinputmaterial", listinputmaterial);
		model.addAttribute("page", page);
		model.addAttribute("material_no", material_no);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		//用于excel的回显查询条件
		Input HX= new Input();
		if (material_no != null && !("".equals(material_no))) {
			HX.setMaterialNo(material_no);
		}
		if (start_date != null &&!("1000-1-1".equals(start_date))) {
			HX.setShopName(start_date);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
			HX.setMaterialName(end_date);
		}
		model.addAttribute("HX", HX);
		return "inputmiddle";

	}

	// 2.半成品入库查询
	// 半成品入库记录分页
	@RequestMapping("/OutputMiddleMaterialsRecord")
	public String OutputMiddleMaterialsRecord(Integer pageNow,
			String material_no, String start_date, String end_date, Model model) {
		// 第一、二张表的查询
		List<ProductRecord> listoutputmaterial = new ArrayList<ProductRecord>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = productRecordMapper.selectOutputMiddleRecordtotalCount(
				material_no, start_date, end_date);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listoutputmaterial = inputOrOutputRecordService
				.selectOutputMiddleRecord(page.getStartPos(),
						page.getPageSize(), material_no, start_date, end_date);
		// controller层中使model能返回一个jsp页面
		//String kg="kg";
		model.addAttribute("listoutputmaterial", listoutputmaterial);
		model.addAttribute("page", page);
		model.addAttribute("material_no", material_no);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		//用于excel的回显查询条件
		Input HX= new Input();
		if (material_no != null && !("".equals(material_no))) {
			HX.setMaterialNo(material_no);
		}
		if (start_date != null &&!("1000-1-1".equals(start_date))) {
			HX.setShopName(start_date);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
			HX.setMaterialName(end_date);
		}
		model.addAttribute("HX", HX);
		return "outputmiddle";

	}

	
	//删除半成品入库记录
	@RequestMapping("DeleteOutputMiddleRecord")
	public String DeleteInputMiddleRecord(Integer recordId)
			throws Exception {
		productRecordMapper.deleteByPrimaryKey(recordId);
		return "redirect:OutputMiddleMaterialsRecord.action";
		
	}
	
	
	//删除半成品出库记录
		@RequestMapping("DeleteInputMiddleRecord")
		public String DeleteOutputMiddleRecord(Integer recordId)
				throws Exception {
			productRecordMapper.deleteByPrimaryKey(recordId);
			return "redirect:InputMiddleMaterialsRecord.action";
			
		}
	// 3.领半成品查看详情
	// 3.1领半成品详情页面
	@RequestMapping("toInputMiddleRecord")
	public String toInputMiddleRecord(Integer recordId, Model model)
			throws Exception {
		// 第一、二张表GetMaterial的查询
		ProductRecord inputmiddlerecord = new ProductRecord();
		inputmiddlerecord = inputOrOutputRecordService.selectMiddleId(recordId);
		model.addAttribute("inputmiddlerecord", inputmiddlerecord);
		return "lookinputmiddle";
	}

		// 3.2退半成品详情页面
		@RequestMapping("toOutputMiddleRecord")
		public String toOutputMiddleRecord(Integer recordId, Model model)
				throws Exception {
			// 第一、二张表GetMaterial的查询
			ProductRecord outputmiddlerecord = new ProductRecord();
			outputmiddlerecord = inputOrOutputRecordService
					.selectMiddleId(recordId);
			model.addAttribute("outputmiddlerecord", outputmiddlerecord);
			return "lookoutputmiddle";
		}

	
	    
			
	// 月计划-未完成记录
	@RequestMapping("/toTotalPlanList")
	public String toTotalPlanList(Integer pageNow, String client,
			String clientMaterialNo, String start_date, String end_date,
			Model model) throws Exception {
		List<ProductionPlan> listProductionPlan = new ArrayList<ProductionPlan>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}
		int totalCount = 0;
		// 返回月计划的行数totalCount
		totalCount = productionPlanMapper.selectTotalPlanCount(client,
				clientMaterialNo, start_date, end_date);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}
		listProductionPlan = productionPlanService.selectTotalPlanByParam(
				page.getStartPos(), page.getPageSize(), client,
				clientMaterialNo, start_date, end_date);
		model.addAttribute("listProductionPlan", listProductionPlan);
		model.addAttribute("page", page);
		model.addAttribute("client", client);
		model.addAttribute("clientMaterialNo", clientMaterialNo);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		return "totalPlanList";
	}

	// 月计划-已完成记录
	@RequestMapping("/toFinishTotalPlanList")
	public String toFinishTotalPlanList(Integer pageNow, String client,
			String clientMaterialNo, String start_date, String end_date,
			Model model) throws Exception {
		List<ProductionPlan> listProductionPlan = new ArrayList<ProductionPlan>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}
		int totalCount = 0;
		// 返回月计划的行数totalCount
		totalCount = productionPlanMapper.selectFinishTotalPlanCount(client,
				clientMaterialNo, start_date, end_date);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}
		listProductionPlan = productionPlanService
				.selectFinishTotalPlanByParam(page.getStartPos(),
						page.getPageSize(), client, clientMaterialNo,
						start_date, end_date);
		model.addAttribute("listProductionPlan", listProductionPlan);
		model.addAttribute("page", page);
		model.addAttribute("client", client);
		model.addAttribute("clientMaterialNo", clientMaterialNo);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		return "totalPlanList";
	}

	// 月计划审批功能（将是否审批由0到1）

	@RequestMapping("/approveTotalPlanList")
	public String approveTotalPlanList(Integer planId, Model model)
			throws Exception {

		productionPlanService.updateByKey(planId);
		return "redirect:toTotalPlanList.action";

	}

	// 周计划记录-未完成记录
	@RequestMapping("/toProductionPlanList")
	public String toProductionPlanList(Integer pageNow, String client,
			String clientMaterialNo, String start_date, String end_date,
			Model model) throws Exception {
		List<ProductionPlan> listProductionPlan = new ArrayList<ProductionPlan>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}
		int totalCount = 0;
		// 返回周计划的行数
		totalCount = productionPlanMapper.selectProductionPlanCount(client,
				clientMaterialNo, start_date, end_date);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}
		listProductionPlan = productionPlanService.selectProductionPlanByParam(
				page.getStartPos(), page.getPageSize(), client,
				clientMaterialNo, start_date, end_date);
		model.addAttribute("listProductionPlan", listProductionPlan);
		model.addAttribute("page", page);
		model.addAttribute("client", client);
		model.addAttribute("clientMaterialNo", clientMaterialNo);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		// 判断是否没有未完成记录
		if (listProductionPlan.isEmpty()) {
			model.addAttribute("is_product", 0);
		} else {
			model.addAttribute("is_product", listProductionPlan.get(0)
					.getIsProduct());
		}
		return "ProductionPlanList";
	}

	// 周计划记录-已经完成记录
	@RequestMapping("/toFinishProductionPlanList")
	public String toFinishProductionPlanList(Integer pageNow, String client,
			String clientMaterialNo, String start_date, String end_date,
			Model model) throws Exception {
		List<ProductionPlan> listProductionPlan = new ArrayList<ProductionPlan>();
		// 用于查询条件未输时的判断查询
		// 1、起始为空赋给一个特别小的值（1000-1-1）
		if ((start_date == null || start_date == "")
				&& ((end_date != null || end_date != ""))) {
			start_date = "1000-1-1";
		}
		// 2、截止为空赋给一个特别大的值（今天）
		if ((start_date != null || start_date != "")
				&& ((end_date == null || end_date == ""))) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());

		}
		// 3、起始截止都为空两个都赋值
		if ((start_date == null || start_date == "")
				&& (end_date == null || end_date == "")) {
			start_date = "1000-1-1";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			end_date = sf.format(new Date());
		}
		int totalCount = 0;
		// 返回周计划的行数
		totalCount = productionPlanMapper.selectFinishProductionPlanCount(
				client, clientMaterialNo, start_date, end_date);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}
		listProductionPlan = productionPlanService
				.selectFinishProductionPlanByParam(page.getStartPos(),
						page.getPageSize(), client, clientMaterialNo,
						start_date, end_date);
		model.addAttribute("listProductionPlan", listProductionPlan);
		model.addAttribute("page", page);
		model.addAttribute("client", client);
		model.addAttribute("clientMaterialNo", clientMaterialNo);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		// 判断是否没有已完成记录
		if (listProductionPlan.isEmpty()) {
			model.addAttribute("is_product", 1);
		} else {
			model.addAttribute("is_product", listProductionPlan.get(0)
					.getIsProduct());
		}
		return "ProductionPlanList";
	}

	
	// 周计划删除功能
		@RequestMapping("/DeleteProductionPlanList")
		public String DeleteProductionPlanList(Integer planId)throws Exception {
			//删除二表信息
			productionPlanService.deleteScheduleProductionPlanList(planId);
			//删除一表信息
			productionPlanService.deleteProductionPlanList(planId);
			return "redirect:toProductionPlanList.action";

		}
	// 周计划审批功能（将是否审批由0到1）

	@RequestMapping("/approveProductionPlan")
	public String approveProductionPlan(Integer planId, Model model)
			throws Exception {

		productionPlanService.updateByKey(planId);
		return "redirect:toProductionPlanList.action";

	}

	@RequestMapping("/findProductionPlan")
	public String findProductionPlan(String orderNo, Model model)
			throws Exception {
		List<ProductionPlan> listProductionPlan = new ArrayList<ProductionPlan>();
		listProductionPlan = productionPlanMapper.selectZhouPlanByKey(orderNo);
		model.addAttribute("listProductionPlan", listProductionPlan);
		return "ProductionPlanList";
	}

	// 车间排产未完成记录分页查询 ：
	@RequestMapping("/toShopPlanList")
	public String toShopPlanList(Integer pageNow, String planNo,
			String shopName, String batchNo, Model model) throws Exception {
		List<ShopPlan> listShopPlan = new ArrayList<ShopPlan>();

		int totalCount = 0;
		// 返回月计划的行数totalCount
		totalCount = shopPlanMapper.selectShopPlanByParamtotalCount(planNo,
				shopName, batchNo);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listShopPlan = shopPlanService.selectShopPlanByParam(
				page.getStartPos(), page.getPageSize(), planNo, shopName,
				batchNo);
		model.addAttribute("listShopPlan", listShopPlan);
		model.addAttribute("page", page);
		model.addAttribute("planNo", planNo);
		model.addAttribute("shopName", shopName);
		model.addAttribute("batchNo", batchNo);
		// 判断是否没有未完成车间排产记录
		if (listShopPlan.isEmpty()) {
			model.addAttribute("is_product", 0);
		} else {
			model.addAttribute("is_product", listShopPlan.get(0).getIsProduct());
		}
		return "shopplanlist";
	}

	// 车间排产已完成记录分页查询 ：
	@RequestMapping("/toFinishShopPlanList")
	public String toFinishShopPlanList(Integer pageNow, String planNo,
			String shopName, String batchNo, Model model) throws Exception {
		List<ShopPlan> listShopPlan = new ArrayList<ShopPlan>();

		int totalCount = 0;
		// 返回月计划的行数totalCount
		totalCount = shopPlanMapper.selectFinishShopPlanByParamtotalCount(
				planNo, shopName, batchNo);
		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listShopPlan = shopPlanService.selectFinishShopPlanByParam(
				page.getStartPos(), page.getPageSize(), planNo, shopName,
				batchNo);
		model.addAttribute("listShopPlan", listShopPlan);
		model.addAttribute("page", page);
		model.addAttribute("planNo", planNo);
		model.addAttribute("shopName", shopName);
		model.addAttribute("batchNo", batchNo);
		// 判断是否没有已完成车间排产记录
		if (listShopPlan.isEmpty()) {
			model.addAttribute("is_product", 1);
		} else {
			model.addAttribute("is_product", listShopPlan.get(0).getIsProduct());
		}
		return "shopplanlist";
	}

	// 车间排产计划审批功能（将是否审批由0到1）

	@RequestMapping("/approveShopPlanList")
	public String approveShopPlanList(Integer planId, Model model)
			throws Exception {

		shopPlanService.updateByKey(planId);
		return "redirect:toShopPlanList.action";

	}

	// 日检记录查询
	@RequestMapping("/DailyCheck")
	public String DailyCheck(Integer pageNow, String batchNo,
			String processName, String assetNo, Model model) throws Exception {
		List<DailyCheck> listDailyCheck = new ArrayList<DailyCheck>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = dailycheckMapper.selectByPrimarytotalCount(batchNo,
				processName, assetNo);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listDailyCheck = dailyCheckService.selectByPrimary(page.getStartPos(),
				page.getPageSize(), batchNo, processName, assetNo);
		model.addAttribute("listDailyCheck", listDailyCheck);
		model.addAttribute("page", page);
		model.addAttribute("batchNo", batchNo);
		model.addAttribute("processName", processName);
		model.addAttribute("assetNo", assetNo);
		return "dailycheck";
	}

	// 日检记录查询
	@RequestMapping("/lookCheck")
	public String lookCheck(Integer checkId, Model model) throws Exception {

		// 第一张表DailyCheck的查询
		DailyCheck dailyCheck = dailyCheckService.selectByPrimaryKey(checkId);
		model.addAttribute("dailyCheck", dailyCheck);

		// 第二张表CheckRecord的查询
		List<CheckRecord> listCheckRecord = new ArrayList<CheckRecord>();
		listCheckRecord = dailyCheckService.selectCheckRecordByKey(checkId);
		model.addAttribute("listCheckRecord", listCheckRecord);

		return "lookCheck";
	}

	
		// 1.部分外协记录
		@RequestMapping("/toExterAssociation")
		public String toExterAssociation(Integer pageNow, HttpServletResponse response,
				String material_no, String start_date, String end_date,Model model) throws Exception {
			List<ExterAssociation> listExterAssociation=new ArrayList<ExterAssociation>();
			// 用于查询条件未输时的判断查询
				// 1、起始为空赋给一个特别小的值（1000-1-1）
				if ((start_date == null || start_date.equals(""))&&((end_date != null || !("".equals(end_date))))) {
					start_date = "1000-1-1";
				}
				// 2、截止为空赋给一个特别大的值（今天）
				if ((start_date != null || !("".equals(start_date)))
						&& ((end_date == null ||end_date.equals("")))) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());

				}
				// 3、起始截止都为空两个都赋值
				if ((start_date == null ||start_date.equals(""))
						&& (end_date == null ||end_date.equals(""))) {
					start_date = "1000-1-1";
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());
				}

			int totalCount = 0;
			// 返回月计划的行数totalCount
			totalCount = shopTransitionMapper.selectExterAssociationtotalCount(material_no, start_date, end_date);
			Page page = null;
			if (pageNow != null) {
				page = new Page(totalCount, pageNow);
			} else {
				page = new Page(totalCount, 1);
			}

			listExterAssociation=shopPlanService.selectExterAssociation(page.getStartPos(), page.getPageSize(), material_no, start_date, end_date);
			
			model.addAttribute("listExterAssociation", listExterAssociation);
			model.addAttribute("page", page);
			model.addAttribute("material_no", material_no);
			model.addAttribute("start_date", start_date);
			model.addAttribute("end_date", end_date);
			//用于excel的回显查询条件
				Input HX= new Input();
				if (material_no != null && !("".equals(material_no))) {
					HX.setMaterialNo(material_no);
				}
				if (start_date != null &&!("1000-1-1".equals(start_date))) {
					HX.setShopName(start_date);
				}
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
					HX.setMaterialName(end_date);
				}
				model.addAttribute("HX", HX);
			return "exter_association";
		}

		
		// 1.1删除部分外协记录
				@RequestMapping("DeleteExterAssociationexcel")
				public String DeleteExterAssociationexcel(Integer transitionId) throws Exception {
					// 删除交接表中的部分外协交接
					shopPlanService.deleteExterAssociationexcel(transitionId);
					return "redirect:toExterAssociation.action";
					
				}
		
		// 2.部分外协的excel导出
		@RequestMapping("/toExterAssociationexcel")
		public void toExterAssociationexcel(HttpServletResponse response,
				String material_no, String start_date, String end_date) throws Exception {


		    Date day = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			// 文件名
			String fileName = "-部分外协单据.xlsx";
			if (material_no == null && start_date == null && end_date == null) {

				response.setHeader("Content-disposition", "attachment;filename="
						+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			} else {
				response.setHeader("Content-disposition",
						"attachment;filename="+df.format(day)+ "-"+material_no+ new String(fileName.getBytes("gb2312"),
										"ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			}

			// 文件标题栏
			String[] cellTitle = {"外协供应商","计划单号","图号","物料号","批次号","产品名称","提供工段","接收工段","外协日期","外协数量"};
			try {

				// 声明一个工作薄
				XSSFWorkbook workBook = null;
				workBook = new XSSFWorkbook();

				// 生成一个表格
				XSSFSheet sheet = workBook.createSheet();
				workBook.setSheetName(0, "部分外协清单");

				// 创建表格标题行 第2行(循环将标题值赋于第1行)
				XSSFRow titleRow = sheet.createRow(1);
				for (int i = 0; i < cellTitle.length; i++) {
					titleRow.createCell(i).setCellValue(cellTitle[i]);
				}

				// 创建行
				XSSFRow row = sheet.createRow((short) 0);
				// 创建单元格
				XSSFCell cell = null;

				// 第一行标题栏
				row = sheet.createRow(0);
				cell = row.createCell(2);
				cell.setCellValue(material_no);
				cell = row.createCell(3);
				cell.setCellValue(df.format(day));
				cell = row.createCell(4);
				cell.setCellValue("部分外协单据");

				// 设置居中
				XSSFCellStyle cellStyle = workBook.createCellStyle();
				cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

				// 设置列宽
				sheet.setColumnWidth(0, 20 * 256);
				sheet.setColumnWidth(1, 20 * 256);
				sheet.setColumnWidth(2, 20 * 256);
				sheet.setColumnWidth(3, 20 * 256);
				sheet.setColumnWidth(4, 20 * 256);
				sheet.setColumnWidth(5, 20 * 256);
				sheet.setColumnWidth(8, 20 * 256);
				
				// 4.历史数据，业务数据，不用关注
			List<ExterAssociation> listExterAssociation=new ArrayList<ExterAssociation>();
			listExterAssociation=shopPlanService.selectexcelExterAssociation(material_no, start_date, end_date);
				
			if (listExterAssociation != null && listExterAssociation.size() > 0) {

					// 5.将历史数据添加到单元格中 (先列后行)
					for (int j = 0; j < listExterAssociation.size(); j++) {
						row = sheet.createRow(j + 2);
						cell = row.createCell(0);
						if(!(listExterAssociation.get(j).getShop2()).equals("外协中")){
							cell.setCellValue(listExterAssociation.get(j).getProvider());
						}else{
							cell.setCellValue(listExterAssociation.get(j).getAcceptor());
						}
						
						cell = row.createCell(1);
						cell.setCellValue(listExterAssociation.get(j).getPlanNo());
						cell = row.createCell(2);
						cell.setCellValue(listExterAssociation.get(j).getClientMaterialNo());
						cell = row.createCell(3);
						cell.setCellValue(listExterAssociation.get(j).getMaterialNo());
						cell = row.createCell(4);
						cell.setCellValue(listExterAssociation.get(j).getBatchNo());
						cell = row.createCell(5);
						cell.setCellValue(listExterAssociation.get(j).getProductName());
						cell = row.createCell(6);
						cell.setCellValue(listExterAssociation.get(j).getShop1());
						cell = row.createCell(7);
						cell.setCellValue(listExterAssociation.get(j).getShop2());
						cell = row.createCell(8);
						cell.setCellValue(listExterAssociation.get(j).getTranDate());
						cell = row.createCell(9);
						cell.setCellValue(listExterAssociation.get(j).getQualifiedNum());
						
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
		
		
	
		// 原材料外协出库记录分页
		@RequestMapping("/toMaterialsAssociation")
		public String toMaterialsAssociation(Integer pageNow, String material_no,
				String start_date, String end_date, Model model) {
			// 根据物料号查询是否二表的半成品批次号存在

			// 1.1第一、二张表的查询
			List<Input> listmaterialassociation = new ArrayList<Input>();
			// 用于查询条件未输时的判断查询
			// 1、起始为空赋给一个特别小的值（1000-1-1）
			if ((start_date == null || start_date.equals(""))&&((end_date != null || !("".equals(end_date))))) {
				start_date = "1000-1-1";
			}
			// 2、截止为空赋给一个特别大的值（今天）
			if ((start_date != null || !("".equals(start_date)))
					&& ((end_date == null ||end_date.equals("")))) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				end_date = sf.format(new Date());

			}
			// 3、起始截止都为空两个都赋值
			if ((start_date == null ||start_date.equals(""))
					&& (end_date == null ||end_date.equals(""))) {
				start_date = "1000-1-1";
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				end_date = sf.format(new Date());
			}


			int totalCount = 0;
			// 返回查询的行数totalCount

			totalCount = getMaterialMapper.selectMaterialAssociationtotalCount(material_no, start_date, end_date);

			Page page = null;
			if (pageNow != null) {
				page = new Page(totalCount, pageNow);
			} else {
				page = new Page(totalCount, 1);
			}

			listmaterialassociation = inputOrOutputRecordService.selectMaterialAssociation(page.getStartPos(), page.getPageSize(), material_no, start_date, end_date);
			// controller层中使model能返回一个jsp页面
			model.addAttribute("listmaterialassociation", listmaterialassociation);

			model.addAttribute("page", page);
			model.addAttribute("material_no", material_no);
			model.addAttribute("start_date", start_date);
			model.addAttribute("end_date", end_date);
			//用于excel的回显查询条件
			Input HX= new Input();
					if (material_no != null && !("".equals(material_no))) {
						HX.setMaterialNo(material_no);
					}
					if (start_date != null &&!("1000-1-1".equals(start_date))) {
						HX.setShopName(start_date);
					}
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
						HX.setMaterialName(end_date);
					}
					model.addAttribute("HX", HX);
			return "output_material_association";

		}
				
		 // 2.原材料外协出库excel导出
			@RequestMapping("/toMaterialsAssociationexcel")
			public void toMaterialsAssociationexcel(Integer pageNow, HttpServletResponse response,
					String material_no, String start_date, String end_date) throws Exception {

				// 分页使用
				int totalCount = 0;
				Page page = null;
				if (pageNow != null) {
					page = new Page(totalCount, pageNow);
				} else {
					page = new Page(totalCount, 1);
				}
				

			    Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				// 文件名
				String fileName = "-原材料外协出库单据.xlsx";
				if (material_no == null && start_date == null && end_date == null) {

					response.setHeader("Content-disposition", "attachment;filename="
							+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
					response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
					response.setHeader("Cache-Control", "no-cache");// 设置头
					response.setDateHeader("Expires", 0);// 设置日期头
				} else {
					response.setHeader("Content-disposition",
							"attachment;filename="+df.format(day)+ "-"+material_no+ new String(fileName.getBytes("gb2312"),
											"ISO8859-1")); // 设置文件头编码格式
					response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
					response.setHeader("Cache-Control", "no-cache");// 设置头
					response.setDateHeader("Expires", 0);// 设置日期头
				}

				// 文件标题栏
				String[] cellTitle = {"图号","物料号","计划单号","批次号","产品名称","产品规格","原材料批次号","材料名称","材料编号","外协出库数量","单位","外协出库日期"};
				try {

					// 声明一个工作薄
					XSSFWorkbook workBook = null;
					workBook = new XSSFWorkbook();

					// 生成一个表格
					XSSFSheet sheet = workBook.createSheet();
					workBook.setSheetName(0, "原材料外协出库清单");

					// 创建表格标题行 第2行(循环将标题值赋于第1行)
					XSSFRow titleRow = sheet.createRow(1);
					for (int i = 0; i < cellTitle.length; i++) {
						titleRow.createCell(i).setCellValue(cellTitle[i]);
					}

					// 创建行
					XSSFRow row = sheet.createRow((short) 0);
					// 创建单元格
					XSSFCell cell = null;

					// 第一行标题栏
					row = sheet.createRow(0);
					cell = row.createCell(2);
					cell.setCellValue(material_no);
					cell = row.createCell(3);
					cell.setCellValue(df.format(day));
					cell = row.createCell(4);
					cell.setCellValue("原材料外协出库单据");

					// 设置居中
					XSSFCellStyle cellStyle = workBook.createCellStyle();
					cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

					// 设置列宽
					sheet.setColumnWidth(0, 20 * 256);
					sheet.setColumnWidth(1, 20 * 256);
					sheet.setColumnWidth(2, 20 * 256);
					sheet.setColumnWidth(3, 20 * 256);
					sheet.setColumnWidth(4, 20 * 256);
					sheet.setColumnWidth(5, 20 * 256);
					sheet.setColumnWidth(6, 20 * 256);
					sheet.setColumnWidth(7, 20 * 256);
					sheet.setColumnWidth(8, 20 * 256);
					sheet.setColumnWidth(9, 15 * 256);
					sheet.setColumnWidth(11, 15 * 256);
					
					// 4.历史数据，业务数据，不用关注
				List<Input> listmaterialassociation=new ArrayList<Input>();
				listmaterialassociation = inputOrOutputRecordService.selectMaterialAssociation(page.getStartPos(), page.getPageSize(), material_no, start_date, end_date);
					
				if (listmaterialassociation != null && listmaterialassociation.size() > 0) {

						// 5.将历史数据添加到单元格中 (先列后行)
						for (int j = 0; j < listmaterialassociation.size(); j++) {
							row = sheet.createRow(j + 2);
							cell = row.createCell(0);
							cell.setCellValue(listmaterialassociation.get(j).getClientMaterialNo());
							cell = row.createCell(1);
							cell.setCellValue(listmaterialassociation.get(j).getMaterialNo());
							cell = row.createCell(2);
							cell.setCellValue(listmaterialassociation.get(j).getPlanNo());
							cell = row.createCell(3);
							cell.setCellValue(listmaterialassociation.get(j).getBatchNo());
							//产品名称用unqualified不合格数前端充当
							cell = row.createCell(4);
							cell.setCellValue(listmaterialassociation.get(j).getMaterialName());
							cell = row.createCell(5);
							cell.setCellValue(listmaterialassociation.get(j).getProductSpec());
							cell = row.createCell(6);
							cell.setCellValue(listmaterialassociation.get(j).getMaterialBatchNo());
							cell = row.createCell(7);
							cell.setCellValue(listmaterialassociation.get(j).getCailiaoMc());
							cell = row.createCell(8);
							cell.setCellValue(listmaterialassociation.get(j).getCailiaoBh());
							cell = row.createCell(9);
							cell.setCellValue(listmaterialassociation.get(j).getMaterialNum());
							cell = row.createCell(10);
							cell.setCellValue(listmaterialassociation.get(j).getUnit());
							cell = row.createCell(11);
							cell.setCellValue(listmaterialassociation.get(j).getGetDate());
							
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
			
			
			// 原材料外协入库记录分页
			@RequestMapping("/toIntputMaterialsAssociation")
			public String toIntputMaterialsAssociation(Integer pageNow, String material_no,
					String start_date, String end_date, Model model) {
				// 第一、二张表的查询
				List<InputMaterialAssociation> listintputmaterialassociation = new ArrayList<InputMaterialAssociation>();
				// 用于查询条件未输时的判断查询
				// 1、起始为空赋给一个特别小的值（1000-1-1）
				if ((start_date == null || start_date.equals(""))&&((end_date != null || !("".equals(end_date))))) {
					start_date = "1000-1-1";
				}
				// 2、截止为空赋给一个特别大的值（今天）
				if ((start_date != null || !("".equals(start_date)))
						&& ((end_date == null ||end_date.equals("")))) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());

				}
				// 3、起始截止都为空两个都赋值
				if ((start_date == null ||start_date.equals(""))
						&& (end_date == null ||end_date.equals(""))) {
					start_date = "1000-1-1";
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());
				}


				int totalCount = 0;
				// 返回查询的行数totalCount

				totalCount = productRecordMapper.selectIntputMaterialAssociationtotalCount(material_no, start_date, end_date);

				Page page = null;
				if (pageNow != null) {
					page = new Page(totalCount, pageNow);
				} else {
					page = new Page(totalCount, 1);
				}

				listintputmaterialassociation = inputOrOutputRecordService.selectIntputMaterialAssociation(page.getStartPos(),page.getPageSize(), material_no, start_date, end_date);
				// controller层中使model能返回一个jsp页面
				model.addAttribute("listintputmaterialassociation", listintputmaterialassociation);
				model.addAttribute("page", page);
				model.addAttribute("material_no", material_no);
				model.addAttribute("start_date", start_date);
				model.addAttribute("end_date", end_date);
				//用于excel的回显查询条件
				Input HX= new Input();
						if (material_no != null && !("".equals(material_no))) {
							HX.setMaterialNo(material_no);
						}
						if (start_date != null &&!("1000-1-1".equals(start_date))) {
							HX.setShopName(start_date);
						}
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
							HX.setMaterialName(end_date);
						}
						model.addAttribute("HX", HX);
				return "input_material_association";

			}
			
			
			 //删除原材料入库信息
			@RequestMapping("/deleteInputMaterialsAssociation")
			public String deleteInputMaterialsAssociation(Integer recordId) throws Exception {
				productRecordMapper.deleteByPrimaryKey(recordId);
				return "redirect:toIntputMaterialsAssociation.action";
			}

			 // 2.原材料外协入库excel导出
			@RequestMapping("/toInputMaterialsAssociationexcel")
			public void toInputMaterialsAssociationexcel(HttpServletResponse response,
					String material_no, String start_date, String end_date) throws Exception {
				
			    Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				// 文件名
				String fileName = "-原材料外协入库单据.xlsx";
				if (material_no == null && start_date == null && end_date == null) {

					response.setHeader("Content-disposition", "attachment;filename="
							+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
					response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
					response.setHeader("Cache-Control", "no-cache");// 设置头
					response.setDateHeader("Expires", 0);// 设置日期头
				} else {
					response.setHeader("Content-disposition",
							"attachment;filename="+df.format(day)+ "-"+material_no+ new String(fileName.getBytes("gb2312"),
											"ISO8859-1")); // 设置文件头编码格式
					response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
					response.setHeader("Cache-Control", "no-cache");// 设置头
					response.setDateHeader("Expires", 0);// 设置日期头
				}

				// 文件标题栏
				String[] cellTitle = {"图号","物料号","计划单号","批次号","产品名称","产品规格","外协入库重量","外协入库数量","单位","外协入库日期"};
				try {

					// 声明一个工作薄
					XSSFWorkbook workBook = null;
					workBook = new XSSFWorkbook();

					// 生成一个表格
					XSSFSheet sheet = workBook.createSheet();
					workBook.setSheetName(0, "原材料外协入库清单");

					// 创建表格标题行 第2行(循环将标题值赋于第1行)
					XSSFRow titleRow = sheet.createRow(1);
					for (int i = 0; i < cellTitle.length; i++) {
						titleRow.createCell(i).setCellValue(cellTitle[i]);
					}

					// 创建行
					XSSFRow row = sheet.createRow((short) 0);
					// 创建单元格
					XSSFCell cell = null;

					// 第一行标题栏
					row = sheet.createRow(0);
					cell = row.createCell(2);
					cell.setCellValue(material_no);
					cell = row.createCell(3);
					cell.setCellValue(df.format(day));
					cell = row.createCell(4);
					cell.setCellValue("原材料外协入库单据");

					// 设置居中
					XSSFCellStyle cellStyle = workBook.createCellStyle();
					cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

					// 设置列宽
					sheet.setColumnWidth(0, 20 * 256);
					sheet.setColumnWidth(1, 20 * 256);
					sheet.setColumnWidth(2, 20 * 256);
					sheet.setColumnWidth(3, 20 * 256);
					sheet.setColumnWidth(4, 20 * 256);
					sheet.setColumnWidth(5, 20 * 256);
					sheet.setColumnWidth(6, 15 * 256);
					sheet.setColumnWidth(7, 15 * 256);
					sheet.setColumnWidth(9, 15 * 256);
					
					
					// 4.历史数据，业务数据，不用关注
				List<InputMaterialAssociation> listintputmaterialassociation=new ArrayList<InputMaterialAssociation>();
				listintputmaterialassociation = inputOrOutputRecordService.selectexcelIntputMaterialAssociation(material_no, start_date, end_date);
				if (listintputmaterialassociation != null && listintputmaterialassociation.size() > 0) {

						// 5.将历史数据添加到单元格中 (先列后行)
						for (int j = 0; j < listintputmaterialassociation.size(); j++) {
							row = sheet.createRow(j + 2);
							cell = row.createCell(0);
							cell.setCellValue(listintputmaterialassociation.get(j).getClientMaterialNo());
							cell = row.createCell(1);
							cell.setCellValue(listintputmaterialassociation.get(j).getMaterialNo());
							cell = row.createCell(2);
							cell.setCellValue(listintputmaterialassociation.get(j).getPlanNo());
							cell = row.createCell(3);
							cell.setCellValue(listintputmaterialassociation.get(j).getBatchNo());
							cell = row.createCell(4);
							cell.setCellValue(listintputmaterialassociation.get(j).getProductMc());
							cell = row.createCell(5);
							cell.setCellValue(listintputmaterialassociation.get(j).getProductSpec());
							cell = row.createCell(6);
							cell.setCellValue(listintputmaterialassociation.get(j).getWeight()+"kg");
							cell = row.createCell(7);
							cell.setCellValue(listintputmaterialassociation.get(j).getProductNum());
							cell = row.createCell(8);
							cell.setCellValue(listintputmaterialassociation.get(j).getUnit());
							cell = row.createCell(9);
							cell.setCellValue(listintputmaterialassociation.get(j).getTransDate());
							
							
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
			
		

			 // 领原材料T+excel导出
			@RequestMapping("/importRawMaterialExcel")
			public void importRawMaterialExcel(Integer pageNow, HttpServletResponse response,
					String material_no, String start_date, String end_date) throws Exception {

				// 获取当天日期
				Date day = new Date();
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");

				// 文件名
				String fileName = "-原材料T+模板出库单据.xlsx";
				if (material_no == null && start_date == null && end_date == null) {

					response.setHeader("Content-disposition", "attachment;filename="
							+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
					response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
					response.setHeader("Cache-Control", "no-cache");// 设置头
					response.setDateHeader("Expires", 0);// 设置日期头
				} else {
					response.setHeader("Content-disposition",
							"attachment;filename="+df1.format(day)+ "-"+material_no+ new String(fileName.getBytes("gb2312"),
											"ISO8859-1")); // 设置文件头编码格式
					response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
					response.setHeader("Cache-Control", "no-cache");// 设置头
					response.setDateHeader("Expires", 0);// 设置日期头
				}

				// 文件标题栏
				String[] cellTitle = {"单据日期","单据编号","业务类型编码","业务类型","出库类别编码","出库类别","领用部门编码","领用部门","领用人编码","领用人","备注","仓库编码","仓库","项目编码","项目","材料编码"
						,"材料名称","手工规格","计量单位","数量","批号","金额","手工确定成本","领用人","备注","长","宽","高","系数","π值"};
				try {

					// 声明一个工作薄
					XSSFWorkbook workBook = null;
					workBook = new XSSFWorkbook();

					// 生成一个表格
					XSSFSheet sheet = workBook.createSheet();
					workBook.setSheetName(0, "mes原材料出库清单");

					// 创建表格标题行 第2行(循环将标题值赋于第1行)
					XSSFRow titleRow = sheet.createRow(1);
					for (int i = 0; i < cellTitle.length; i++) {
						titleRow.createCell(i).setCellValue(cellTitle[i]);
					}

					// 创建行
					XSSFRow row = sheet.createRow((short) 0);
					// 创建单元格
					XSSFCell cell = null;
					// 第一行标题栏
					row = sheet.createRow(0);
					cell = row.createCell(2);
					cell.setCellValue(material_no);
					cell = row.createCell(3);
					cell.setCellValue(df1.format(day));
					cell = row.createCell(4);
					cell.setCellValue("mes原材料出库单据");

					// 设置居中
					XSSFCellStyle cellStyle = workBook.createCellStyle();
					cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

					// 设置列宽
					sheet.setColumnWidth(0, 20 * 256);
					sheet.setColumnWidth(1, 20 * 256);
					sheet.setColumnWidth(2, 10 * 256);
					sheet.setColumnWidth(11, 10 * 256);
					sheet.setColumnWidth(18, 10 * 256);
					sheet.setColumnWidth(20, 30 * 256);
					
					// 4.历史数据，业务数据，不用关注
					//原材料的记录选择（除了外协的原材料出库）
					List<Input> listrowinMaterial=new ArrayList<Input>();
					// 用于查询条件未输时的判断查询
					// 1、起始为空赋给一个特别小的值（1000-1-1）
					if ((start_date == null || start_date.equals(""))&&((end_date != null || !("".equals(end_date))))) {
						start_date = "1000-1-1";
					}
					// 2、截止为空赋给一个特别大的值（今天）
					if ((start_date != null || !("".equals(start_date)))
							&& ((end_date == null ||end_date.equals("")))) {
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						end_date = sf.format(new Date());

					}
					// 3、起始截止都为空两个都赋值
					if ((start_date == null ||start_date.equals(""))
							&& (end_date == null ||end_date.equals(""))) {
						start_date = "1000-1-1";
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						end_date = sf.format(new Date());
					}

					listrowinMaterial = inputOrOutputRecordService.selectRowinMaterial(material_no, start_date, end_date);
				if (listrowinMaterial != null && listrowinMaterial.size() > 0) {

						// 5.将历史数据添加到单元格中 (先列后行)
						for (int j = 0; j < listrowinMaterial.size(); j++) {
							row = sheet.createRow(j + 2);
							//1.单据日期
							cell = row.createCell(0);
							cell.setCellValue(df1.format(day));
							//2.单据编号
							//（需要先查询数据库中该月中原材料的单据日期数量）
							cell = row.createCell(1);
							Integer num=0;
							String num1=tJdbcService.selectRowMaterialNum();
							if(num1!=null&&!("".equals(num1))){
							    num=Integer.valueOf(num1.replaceAll("^(0+)", ""));//.replaceAll("^(0+)", "")-正则去除字符串前面的0
							}else{
								num=0;
							}
							
							//判断如果是1位数拼接为：当前日期到月份"yyyy-MM-+000+num"
							if(num>=0&&num<9){
								num=num+1+j;
								cell.setCellValue("MD-"+df2.format(day)+"-000"+num);
							}
							//判断如果2位数拼接为：当前日期到月份"yyyy-MM-+00+num"
							if(num>=9&&num<99){
								num=num+1+j;
								cell.setCellValue("MD-"+df2.format(day)+"-00"+num);
							}
							//判断如果3位数拼接为：当前日期到月份"yyyy-MM-+0+num"
							if(num>=99){
								num=num+1+j;
								
								cell.setCellValue("MD-"+df2.format(day)+"-0"+num);
							}
							
							cell = row.createCell(2);
							cell.setCellValue("41");
							cell = row.createCell(7);
							cell.setCellValue(listrowinMaterial.get(j).getShopName());
							cell = row.createCell(11);
							cell.setCellValue("01");
							cell = row.createCell(15);
							cell.setCellValue(listrowinMaterial.get(j).getCailiaoBh());
							cell = row.createCell(18);
							cell.setCellValue(listrowinMaterial.get(j).getUnit());
							cell = row.createCell(19);
							cell.setCellValue(listrowinMaterial.get(j).getMaterialNum());
							cell = row.createCell(20);
							cell.setCellValue(listrowinMaterial.get(j).getMaterialBatchNo());
							cell = row.createCell(23);
							cell.setCellValue(listrowinMaterial.get(j).getAcceptor());
							
							
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
			

			// 退原材料T+模板excel导出
			@RequestMapping("/outportRawMaterialExcel")
			public void outportRawMaterialExcel(Integer pageNow, HttpServletResponse response,
				String material_no, String start_date, String end_date) throws Exception {
			
			// 获取当天日期
			Date day = new Date();
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");

			// 文件名
			String fileName = "-原材料T+模板入库单据.xlsx";
			if (material_no == null && start_date == null && end_date == null) {

				response.setHeader("Content-disposition", "attachment;filename="
						+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			} else {
				response.setHeader("Content-disposition",
						"attachment;filename="+df1.format(day)+ "-"+material_no+ new String(fileName.getBytes("gb2312"),
										"ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			}

			// 文件标题栏
			String[] cellTitle = {"单据日期","单据编号","业务类型编码","业务类型","出库类别编码","出库类别","领用部门编码","领用部门","领用人编码","领用人","备注","仓库编码","仓库","项目编码","项目","材料编码"
					,"材料名称","手工规格","计量单位","数量","批号","金额","手工确定成本","领用人","备注","长","宽","高","系数","π值"};
			try {

				// 声明一个工作薄
				XSSFWorkbook workBook = null;
				workBook = new XSSFWorkbook();

				// 生成一个表格
				XSSFSheet sheet = workBook.createSheet();
				workBook.setSheetName(0, "mes原材料入库清单");

				// 创建表格标题行 第2行(循环将标题值赋于第1行)
				XSSFRow titleRow = sheet.createRow(1);
				for (int i = 0; i < cellTitle.length; i++) {
					titleRow.createCell(i).setCellValue(cellTitle[i]);
				}

				// 创建行
				XSSFRow row = sheet.createRow((short) 0);
				// 创建单元格
				XSSFCell cell = null;
				// 第一行标题栏
				row = sheet.createRow(0);
				cell = row.createCell(2);
				cell.setCellValue(material_no);
				cell = row.createCell(3);
				cell.setCellValue(df1.format(day));
				cell = row.createCell(4);
				cell.setCellValue("mes原材料入库单据");
				
				// 设置居中
				XSSFCellStyle cellStyle = workBook.createCellStyle();
				cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

				// 设置列宽
				sheet.setColumnWidth(0, 20 * 256);
				sheet.setColumnWidth(1, 20 * 256);
				sheet.setColumnWidth(2, 10 * 256);
				sheet.setColumnWidth(11, 10 * 256);
				sheet.setColumnWidth(18, 10 * 256);
				sheet.setColumnWidth(20, 30 * 256);
				
				// 4.历史数据，业务数据，不用关注
				//原材料的记录选择（除了外协的原材料出库）
				List<Output> listrowoutMaterial=new ArrayList<Output>();
				// 用于查询条件未输时的判断查询
				// 1、起始为空赋给一个特别小的值（1000-1-1）
				if ((start_date == null || start_date.equals(""))&&((end_date != null 
						|| !("".equals(end_date))))) {
					start_date = "1000-1-1";
				}
				// 2、截止为空赋给一个特别大的值（今天）
				if ((start_date != null || !("".equals(start_date)))
						&& ((end_date == null ||end_date.equals("")))) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());

				}
				// 3、起始截止都为空两个都赋值
				if ((start_date == null ||start_date.equals(""))
						&& (end_date == null ||end_date.equals(""))) {
					start_date = "1000-1-1";
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());
				}

				listrowoutMaterial = inputOrOutputRecordService.selectRowoutMaterial(material_no, start_date, end_date);
			if (listrowoutMaterial != null && listrowoutMaterial.size() > 0) {

					// 5.将历史数据添加到单元格中 (先列后行)
					for (int j = 0; j < listrowoutMaterial.size(); j++) {
						row = sheet.createRow(j + 2);
						//1.单据日期
						cell = row.createCell(0);
						cell.setCellValue(df1.format(day));
						//2.单据编号
						//（需要先查询数据库中该月中原材料的单据日期数量）
						cell = row.createCell(1);
						Integer num=0;
						String num1=tJdbcService.selectRowMaterialNum();
						if(num1!=null&&!("".equals(num1))){
						    num=Integer.valueOf(num1.replaceAll("^(0+)", ""));//.replaceAll("^(0+)", "")-正则去除字符串前面的0
						}else{
							num=0;
						}
						
						//判断如果是1位数拼接为：当前日期到月份"yyyy-MM-+000+num"
						if(num>=0&&num<9){
							num=num+1+j;
							cell.setCellValue("MD-"+df2.format(day)+"-000"+num);
						}
						//判断如果2位数拼接为：当前日期到月份"yyyy-MM-+00+num"
						if(num>=9&&num<99){
							num=num+1+j;
							cell.setCellValue("MD-"+df2.format(day)+"-00"+num);
						}
						//判断如果3位数拼接为：当前日期到月份"yyyy-MM-+0+num"
						if(num>=99){
							num=num+1+j;
							
							cell.setCellValue("MD-"+df2.format(day)+"-0"+num);
						}
						
						cell = row.createCell(2);
						cell.setCellValue("42");
						cell = row.createCell(7);
						cell.setCellValue(listrowoutMaterial.get(j).getShopName());
						cell = row.createCell(11);
						cell.setCellValue("01");
						cell = row.createCell(15);
						cell.setCellValue(listrowoutMaterial.get(j).getCailiaoBh());
						cell = row.createCell(18);
						cell.setCellValue(listrowoutMaterial.get(j).getUnit());
						cell = row.createCell(19);
						cell.setCellValue(listrowoutMaterial.get(j).getMaterialNum());
						cell = row.createCell(20);
						cell.setCellValue(listrowoutMaterial.get(j).getMaterialBatchNo());
						cell = row.createCell(23);
						cell.setCellValue(listrowoutMaterial.get(j).getAcceptor());
						
						
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
			
			
		
		// 领辅料T+模板excel导出
		@RequestMapping("/importSecMaterialExcel")
		public void importSecMaterialExcel(Integer pageNow, HttpServletResponse response,
				String shopName, String start_date, String end_date) throws Exception {
			// 获取当天日期
			Date day = new Date();
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");

			// 文件名
			String fileName = "-辅料T+模板出库单据.xlsx";
			if (shopName == null && start_date == null && end_date == null) {

				response.setHeader("Content-disposition", "attachment;filename="
						+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			} else {
				response.setHeader("Content-disposition",
						"attachment;filename="+df1.format(day)+ "-"+shopName+ new String(fileName.getBytes("gb2312"),
										"ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			}

			// 文件标题栏
			String[] cellTitle = {"单据日期","单据编号","业务类型编码","业务类型","出库类别编码","出库类别","领用部门编码","领用部门","领用人编码","领用人","备注","仓库编码","仓库","项目编码","项目","材料编码"
					,"材料名称","手工规格","计量单位","数量","批号","金额","手工确定成本","领用人","备注","长","宽","高","系数","π值"};
			try {

				// 声明一个工作薄
				XSSFWorkbook workBook = null;
				workBook = new XSSFWorkbook();

				// 生成一个表格
				XSSFSheet sheet = workBook.createSheet();
				workBook.setSheetName(0, "mes辅料出库清单");

				// 创建表格标题行 第2行(循环将标题值赋于第1行)
				XSSFRow titleRow = sheet.createRow(1);
				for (int i = 0; i < cellTitle.length; i++) {
					titleRow.createCell(i).setCellValue(cellTitle[i]);
				}

				// 创建行
				XSSFRow row = sheet.createRow((short) 0);
				// 创建单元格
				XSSFCell cell = null;
				// 第一行标题栏
				row = sheet.createRow(0);
				cell = row.createCell(2);
				cell.setCellValue(shopName);
				cell = row.createCell(3);
				cell.setCellValue(df1.format(day));
				cell = row.createCell(4);
				cell.setCellValue("mes辅料出库单据");
				
				// 设置居中
				XSSFCellStyle cellStyle = workBook.createCellStyle();
				cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

				// 设置列宽
				sheet.setColumnWidth(0, 20 * 256);
				sheet.setColumnWidth(1, 20 * 256);
				sheet.setColumnWidth(2, 10 * 256);
				sheet.setColumnWidth(11, 10 * 256);
				sheet.setColumnWidth(18, 10 * 256);
				sheet.setColumnWidth(20, 30 * 256);
				
				// 4.历史数据，业务数据，不用关注
				List<InputSec> listsecinMaterial=new ArrayList<InputSec>();
				
				// 用于查询条件未输时的判断查询
				// 1、起始为空赋给一个特别小的值（1000-1-1）
				if ((start_date == null || start_date.equals(""))&&((end_date != null 
						|| !("".equals(end_date))))) {
					start_date = "1000-1-1";
				}
				// 2、截止为空赋给一个特别大的值（今天）
				if ((start_date != null || !("".equals(start_date)))
						&& ((end_date == null ||end_date.equals("")))) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());

				}
				// 3、起始截止都为空两个都赋值
				if ((start_date == null ||start_date.equals(""))
						&& (end_date == null ||end_date.equals(""))) {
					start_date = "1000-1-1";
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());
				}

				listsecinMaterial = secMaterialService.selectexcelinputSecGetMaterial(shopName, start_date, end_date);
			if (listsecinMaterial != null && listsecinMaterial.size() > 0) {

					// 5.将历史数据添加到单元格中 (先列后行)
					for (int j = 0; j < listsecinMaterial.size(); j++) {
						row = sheet.createRow(j + 2);
						//1.单据日期
						cell = row.createCell(0);
						cell.setCellValue(df1.format(day));
						//2.单据编号
						//（需要先查询数据库中该月中原材料的单据日期数量）
						Integer num=0;
						String num1=tJdbcService.selectRowMaterialNum();
						if(num1!=null&&"".equals(num1)){
							 num=Integer.valueOf(num1.replaceAll("^(0+)", ""));//.replaceAll("^(0+)", "")-正则去除字符串前面的0
						}else{
							num=0;
						}
						//判断如果是1位数拼接为：当前日期到月份"yyyy-MM-+000+num"
						if(num>=0&&num<9){
							num=num+1+j;
							cell.setCellValue("MD-"+df2.format(day)+"-000"+num);
						}
						//判断如果2位数拼接为：当前日期到月份"yyyy-MM-+00+num"
						if(num>=9&&num<99){
							num=num+1+j;
							cell.setCellValue("MD-"+df2.format(day)+"-00"+num);
						}
						//判断如果3位数拼接为：当前日期到月份"yyyy-MM-+0+num"
						if(num>=99){
							num=num+1+j;
							
							cell.setCellValue("MD-"+df2.format(day)+"-0"+num);
						}
						
						cell = row.createCell(2);
						cell.setCellValue("41");
						cell = row.createCell(7);
						cell.setCellValue(listsecinMaterial.get(j).getShopName());
						cell = row.createCell(11);
						cell.setCellValue("03");
						cell = row.createCell(15);
						cell.setCellValue(listsecinMaterial.get(j).getSecMaterialNo());
						cell = row.createCell(18);
						cell.setCellValue(listsecinMaterial.get(j).getUnit());
						cell = row.createCell(19);
						cell.setCellValue(listsecinMaterial.get(j).getNum());
						cell = row.createCell(23);
						cell.setCellValue(listsecinMaterial.get(j).getAcceptor());
						
						
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
			
		
		// 退辅料T+模板excel导出
		@RequestMapping("/outportSecMaterialExcel")
		public void outportSecMaterialExcel(Integer pageNow, HttpServletResponse response,
				String reshopName, String start_date, String end_date) throws Exception {
			// 获取当天日期
			Date day = new Date();
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");

			// 文件名
			String fileName = "-辅料T+模板入库单据.xlsx";
			if (reshopName == null && start_date == null && end_date == null) {

				response.setHeader("Content-disposition", "attachment;filename="
						+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			} else {
				response.setHeader("Content-disposition",
						"attachment;filename="+df1.format(day)+ "-"+reshopName+ new String(fileName.getBytes("gb2312"),
										"ISO8859-1")); // 设置文件头编码格式
				response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
				response.setHeader("Cache-Control", "no-cache");// 设置头
				response.setDateHeader("Expires", 0);// 设置日期头
			}

			// 文件标题栏
			String[] cellTitle = {"单据日期","单据编号","业务类型编码","业务类型","出库类别编码","出库类别","领用部门编码","领用部门","领用人编码","领用人","备注","仓库编码","仓库","项目编码","项目","材料编码"
					,"材料名称","手工规格","计量单位","数量","批号","金额","手工确定成本","领用人","备注","长","宽","高","系数","π值"};
			try {

				// 声明一个工作薄
				XSSFWorkbook workBook = null;
				workBook = new XSSFWorkbook();

				// 生成一个表格
				XSSFSheet sheet = workBook.createSheet();
				workBook.setSheetName(0, "mes辅料入库清单");

				// 创建表格标题行 第2行(循环将标题值赋于第1行)
				XSSFRow titleRow = sheet.createRow(1);
				for (int i = 0; i < cellTitle.length; i++) {
					titleRow.createCell(i).setCellValue(cellTitle[i]);
				}

				// 创建行
				XSSFRow row = sheet.createRow((short) 0);
				// 创建单元格
				XSSFCell cell = null;
				// 第一行标题栏
				row = sheet.createRow(0);
				cell = row.createCell(2);
				cell.setCellValue(reshopName);
				cell = row.createCell(3);
				cell.setCellValue(df1.format(day));
				cell = row.createCell(4);
				cell.setCellValue("mes辅料入库单据");
				
				// 设置居中
				XSSFCellStyle cellStyle = workBook.createCellStyle();
				cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

				// 设置列宽
				sheet.setColumnWidth(0, 20 * 256);
				sheet.setColumnWidth(1, 20 * 256);
				sheet.setColumnWidth(2, 10 * 256);
				sheet.setColumnWidth(11, 10 * 256);
				sheet.setColumnWidth(18, 10 * 256);
				sheet.setColumnWidth(20, 30 * 256);
				
				// 4.历史数据，业务数据，不用关注
				List<GetSecDetail> listsecoutMaterial=new ArrayList<GetSecDetail>();
				
				// 用于查询条件未输时的判断查询
				// 1、起始为空赋给一个特别小的值（1000-1-1）
				if ((start_date == null || start_date.equals(""))&&((end_date != null 
						|| !("".equals(end_date))))) {
					start_date = "1000-1-1";
				}
				// 2、截止为空赋给一个特别大的值（今天）
				if ((start_date != null || !("".equals(start_date)))
						&& ((end_date == null ||end_date.equals("")))) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());

				}
				// 3、起始截止都为空两个都赋值
				if ((start_date == null ||start_date.equals(""))
						&& (end_date == null ||end_date.equals(""))) {
					start_date = "1000-1-1";
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					end_date = sf.format(new Date());
				}

				listsecoutMaterial = secMaterialService.selectexceloutputSecGetMaterial(reshopName, start_date, end_date);
			if (listsecoutMaterial != null && listsecoutMaterial.size() > 0) {

					// 5.将历史数据添加到单元格中 (先列后行)
					for (int j = 0; j < listsecoutMaterial.size(); j++) {
						row = sheet.createRow(j + 2);
						//1.单据日期
						cell = row.createCell(0);
						cell.setCellValue(df1.format(day));
						//2.单据编号
						//（需要先查询数据库中该月中原材料的单据日期数量）
						Integer num=0;
						String num1=tJdbcService.selectRowMaterialNum();
						if(num1!=null&&"".equals(num1)){
							 num=Integer.valueOf(num1.replaceAll("^(0+)", ""));//.replaceAll("^(0+)", "")-正则去除字符串前面的0
						}else{
							num=0;
						}
						//判断如果是1位数拼接为：当前日期到月份"yyyy-MM-+000+num"
						if(num>=0&&num<9){
							num=num+1+j;
							cell.setCellValue("MD-"+df2.format(day)+"-000"+num);
						}
						//判断如果2位数拼接为：当前日期到月份"yyyy-MM-+00+num"
						if(num>=9&&num<99){
							num=num+1+j;
							cell.setCellValue("MD-"+df2.format(day)+"-00"+num);
						}
						//判断如果3位数拼接为：当前日期到月份"yyyy-MM-+0+num"
						if(num>=99){
							num=num+1+j;
							
							cell.setCellValue("MD-"+df2.format(day)+"-0"+num);
						}
						
						cell = row.createCell(2);
						cell.setCellValue("42");
						cell = row.createCell(7);
						cell.setCellValue(listsecoutMaterial.get(j).getReshopName());
						cell = row.createCell(11);
						cell.setCellValue("03");
						cell = row.createCell(15);
						cell.setCellValue(listsecoutMaterial.get(j).getSecMaterialNo());
						cell = row.createCell(18);
						cell.setCellValue(listsecoutMaterial.get(j).getUnit());
						cell = row.createCell(19);
						cell.setCellValue(listsecoutMaterial.get(j).getNum());
						cell = row.createCell(23);
						cell.setCellValue(listsecoutMaterial.get(j).getReceiver());
						
						
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
		
				
				//产成品入库单T+模板excel导出
				@RequestMapping("/outportFullProductExcel")
				public void outportFullProductExcel(Integer pageNow, HttpServletResponse response,
						String material_no, String start_date, String end_date) throws Exception {
					// 获取当天日期
					Date day = new Date();
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");

					// 文件名
					String fileName = "-成品T+模板入库单据.xlsx";
					if (material_no == null && start_date == null && end_date == null) {

						response.setHeader("Content-disposition", "attachment;filename="
								+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
						response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
						response.setHeader("Cache-Control", "no-cache");// 设置头
						response.setDateHeader("Expires", 0);// 设置日期头
					} else {
						response.setHeader("Content-disposition",
								"attachment;filename="+df1.format(day)+ "-"+material_no+ new String(fileName.getBytes("gb2312"),
												"ISO8859-1")); // 设置文件头编码格式
						response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
						response.setHeader("Cache-Control", "no-cache");// 设置头
						response.setDateHeader("Expires", 0);// 设置日期头
					}

					// 文件标题栏
					String[] cellTitle = {"单据日期","单据编号","业务类型编码","业务类型","入库类别编码","入库类别","生产车间编码","生产车间","经手人编码","经手人","备注","仓库编码","仓库","项目编码","项目","产品编码"
							,"产品名称","规格型号","手工规格","计量单位","实收数量","批号","工时","金额"};
					try {

						// 声明一个工作薄
						XSSFWorkbook workBook = null;
						workBook = new XSSFWorkbook();

						// 生成一个表格
						XSSFSheet sheet = workBook.createSheet();
						workBook.setSheetName(0, "mes成品入库清单");

						// 创建表格标题行 第2行(循环将标题值赋于第1行)
						XSSFRow titleRow = sheet.createRow(1);
						for (int i = 0; i < cellTitle.length; i++) {
							titleRow.createCell(i).setCellValue(cellTitle[i]);
						}

						// 创建行
						XSSFRow row = sheet.createRow((short) 0);
						// 创建单元格
						XSSFCell cell = null;
						// 第一行标题栏
						row = sheet.createRow(0);
						cell = row.createCell(2);
						cell.setCellValue(material_no);
						cell = row.createCell(3);
						cell.setCellValue(df1.format(day));
						cell = row.createCell(4);
						cell.setCellValue("mes成品入库单据");
						
						// 设置居中
						XSSFCellStyle cellStyle = workBook.createCellStyle();
						cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

						// 设置列宽
						sheet.setColumnWidth(0, 20 * 256);
						sheet.setColumnWidth(1, 20 * 256);
						sheet.setColumnWidth(2, 10 * 256);
						sheet.setColumnWidth(11, 10 * 256);
						sheet.setColumnWidth(18, 10 * 256);
						
						
						// 4.历史数据，业务数据，不用关注
						List<ProductRecord> listoutputmaterial = new ArrayList<ProductRecord>();
						
						
						// 用于查询条件未输时的判断查询
						// 1、起始为空赋给一个特别小的值（1000-1-1）
						if ((start_date == null || start_date.equals(""))&&((end_date != null 
								|| !("".equals(end_date))))) {
							start_date = "1000-1-1";
						}
						// 2、截止为空赋给一个特别大的值（今天）
						if ((start_date != null || !("".equals(start_date)))
								&& ((end_date == null ||end_date.equals("")))) {
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
							end_date = sf.format(new Date());

						}
						// 3、起始截止都为空两个都赋值
						if ((start_date == null ||start_date.equals(""))
								&& (end_date == null ||end_date.equals(""))) {
							start_date = "1000-1-1";
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
							end_date = sf.format(new Date());
						}

						listoutputmaterial = inputOrOutputRecordService.selectexcelOutputFullRecord(material_no, start_date, end_date);
					if (listoutputmaterial != null && listoutputmaterial.size() > 0) {

							// 5.将历史数据添加到单元格中 (先列后行)
							for (int j = 0; j < listoutputmaterial.size(); j++) {
								row = sheet.createRow(j + 2);
								//1.单据日期
								cell = row.createCell(0);
								cell.setCellValue(df1.format(day));
								//2.单据编号
								//（需要先查询数据库中该月中原材料的单据日期数量）
								Integer num=0;
								String num1=tJdbcService.selectRowMaterialNum();
								if(num1!=null&&"".equals(num1)){
									 num=Integer.valueOf(num1.replaceAll("^(0+)", ""));//.replaceAll("^(0+)", "")-正则去除字符串前面的0
								}else{
									num=0;
								}
								//判断如果是1位数拼接为：当前日期到月份"yyyy-MM-+000+num"
								if(num>=0&&num<9){
									num=num+1+j;
									cell.setCellValue("MC-"+df2.format(day)+"-000"+num);
								}
								//判断如果2位数拼接为：当前日期到月份"yyyy-MM-+00+num"
								if(num>=9&&num<99){
									num=num+1+j;
									cell.setCellValue("MC"+df2.format(day)+"-00"+num);
								}
								//判断如果3位数拼接为：当前日期到月份"yyyy-MM-+0+num"
								if(num>=99){
									num=num+1+j;
									
									cell.setCellValue("MC-"+df2.format(day)+"-0"+num);
								}
								
								cell = row.createCell(2);
								cell.setCellValue("03");
								cell = row.createCell(7);
								cell.setCellValue(listoutputmaterial.get(j).getShop1());
								cell = row.createCell(9);
								cell.setCellValue(listoutputmaterial.get(j).getProvider());
								cell = row.createCell(11);
								cell.setCellValue("02");
								cell = row.createCell(15);
								cell.setCellValue(listoutputmaterial.get(j).getMaterialNo());
								cell = row.createCell(19);
								cell.setCellValue(listoutputmaterial.get(j).getUnit());
								cell = row.createCell(20);
								cell.setCellValue(listoutputmaterial.get(j).getProductNum());
								
								
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
				
				
				// 工序交接记录的查询
				//1.1工序交接记录的分页
				@RequestMapping("/toselectProcessTransition")
				public String toselectProcessTransition(Integer pageNow,String provider,String batchNo,
						String shopName,String start_date, String end_date, Model model) throws Exception {

					List<ProcessTransition> listProcessTransition = new ArrayList<ProcessTransition>();
					// 用于查询条件未输时的判断查询
					// 1、起始为空赋给一个特别小的值（1000-1-1）

					if ((start_date == null || start_date == "")
							&& ((end_date != null || end_date != ""))) {
						start_date = "1000-1-1";
					}
					// 2、截止为空赋给一个特别大的值（今天）
					if ((start_date != null || start_date != "")
							&& ((end_date == null || end_date == ""))) {
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						end_date = sf.format(new Date());

					}
					// 3、起始截止都为空两个都赋值
					if ((start_date == null || start_date == "")
							&& (end_date == null || end_date == "")) {
						start_date = "1000-1-1";
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						end_date = sf.format(new Date());
					}
					int totalCount = 0;
					// 返回查询的行数totalCount

					totalCount = processTransitionMapper.selectProcessTransitionByParamtotalCount(provider,batchNo, shopName, start_date, end_date);
							

					Page page = null;
					if (pageNow != null) {
						page = new Page(totalCount, pageNow);
					} else {
						page = new Page(totalCount, 1);
					}

					listProcessTransition = productionPlanService.selectProcessTransitionByParam(page.getStartPos(), page.getPageSize(),provider, batchNo, shopName, start_date, end_date);

					model.addAttribute("listProcessTransition", listProcessTransition);
					model.addAttribute("page", page);
					model.addAttribute("provider", provider);
					model.addAttribute("batchNo", batchNo);
					model.addAttribute("shopName", shopName);
					model.addAttribute("start_date", start_date);
					model.addAttribute("end_date", end_date);
					
					//用于excel的回显查询条件
					ShopTransition HX= new ShopTransition();
					if (provider != null && !("".equals(provider))) {
						HX.setProvider(provider);
					}
					if (batchNo != null && !("".equals(batchNo))) {
						HX.setBatchNo(batchNo);
					}
					if (shopName != null && !("".equals(shopName))) {
						HX.setShop1(shopName);
					}
					if (start_date != null &&!("1000-1-1".equals(start_date))) {
						HX.setShop2(start_date);
					}
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					if (end_date != null && !(end_date.equals(sf.format(new Date())))) {
						HX.setTranDate(end_date);
					}
					model.addAttribute("HX", HX);
					return "process_transition";
				}
				
				//1.2 工序交接记录删除功能
				@RequestMapping("/DeleteProcessTransition")
				public String DeleteProcessTransition(Integer transitionId)throws Exception {
					//删除工序交接表记录副表信息
					productionPlanService.deleteTransitionCipin(transitionId);
					//删除工序交接表记录主表信息
					productionPlanService.deleteProcessTransition(transitionId);
					return "redirect:toselectProcessTransition.action";

				}
				
				
				//1.3工序交接查看详情
				@RequestMapping("toProcessTransitionDetail")
				public String toProcessTransitionDetail(Integer transitionId, Model model)
						throws Exception {
					List<TransitionCipin> listTransitionCipin=new ArrayList<TransitionCipin>();
					listTransitionCipin=productionPlanService.selectProcessTransitionDetail(transitionId);
					model.addAttribute("listTransitionCipin",listTransitionCipin);
					return "lookprocess_transition";
				}
				
				
				//1.4工序交接记录excel导出
				@RequestMapping("/toprocessTransitionExcel")
				public void toprocessTransitionExcel(Integer pageNow, HttpServletResponse response,
						String provider,String batchNo,String shopName, String start_date, String end_date) throws Exception {
					// 获取当天日期
					Date day = new Date();
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			
					// 文件名
					String fileName = "-工序交接记录单据.xlsx";
					if (start_date == null && end_date == null) {

						response.setHeader("Content-disposition", "attachment;filename="
								+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
						response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
						response.setHeader("Cache-Control", "no-cache");// 设置头
						response.setDateHeader("Expires", 0);// 设置日期头
					} else {
						response.setHeader("Content-disposition",
								"attachment;filename="+df1.format(day)+ "-"+batchNo+ new String(fileName.getBytes("gb2312"),
												"ISO8859-1")); // 设置文件头编码格式
						response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
						response.setHeader("Cache-Control", "no-cache");// 设置头
						response.setDateHeader("Expires", 0);// 设置日期头
					}

					// 文件标题栏
					String[] cellTitle ={"计划单号","批次号","图号","物料号","提供车间","提供工序","提供人","接收车间","接收工序","接收人","接受日期","合格品数","次品数","次品类别1","次品种类1","次品类别2","次品种类2"
							,"次品类别3","次品种类3","次品类别4","次品种类4","次品类别5","次品种类5","次品类别6","次品种类6","次品类别7","次品种类7","次品类别8","次品种类8","次品类别9","次品种类9","次品类别10","次品种类10"
							,"次品类别11","次品种类11","次品类别12","次品种类12","次品类别13","次品种类13","次品类别14","次品种类14","次品类别15","次品种类15","次品类别16","次品种类16"};
					try {

						// 声明一个工作薄
						XSSFWorkbook workBook = null;
						workBook = new XSSFWorkbook();

						// 生成一个表格
						XSSFSheet sheet = workBook.createSheet();
						workBook.setSheetName(0, "工序交接记录清单");

						// 创建表格标题行 第2行(循环将标题值赋于第1行)
						XSSFRow titleRow = sheet.createRow(1);
						for (int i = 0; i < cellTitle.length; i++) {
							titleRow.createCell(i).setCellValue(cellTitle[i]);
						}

						// 创建行
						XSSFRow row = sheet.createRow((short) 0);
						// 创建单元格
						XSSFCell cell = null;
						// 第一行标题栏
						row = sheet.createRow(0);
						cell = row.createCell(2);
						cell.setCellValue(batchNo);
						cell = row.createCell(3);
						cell.setCellValue(df1.format(day));
						cell = row.createCell(4);
						cell.setCellValue("工序交接记录单据");
						
						// 设置居中
						XSSFCellStyle cellStyle = workBook.createCellStyle();
						cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

						// 设置列宽
						sheet.setColumnWidth(0, 20 * 256);
						sheet.setColumnWidth(1, 20 * 256);
						sheet.setColumnWidth(2, 15 * 256);
						sheet.setColumnWidth(3, 15 * 256);
						sheet.setColumnWidth(4, 15 * 256);
						sheet.setColumnWidth(5, 15 * 256);
						sheet.setColumnWidth(6, 20 * 256);
						sheet.setColumnWidth(7, 15 * 256);
						sheet.setColumnWidth(8, 15 * 256);
						sheet.setColumnWidth(9, 20 * 256);
						sheet.setColumnWidth(10, 20 * 256);
						//设置次品的excel列宽
						for(int k=0;k<=15;k++){
							sheet.setColumnWidth(2*k+1+13, 20 * 256);
						}
						
						
						// 4.历史数据，业务数据，不用关注
						List<ProcessTransition> listProcessTransition = new ArrayList<ProcessTransition>();
						
						// 用于查询条件未输时的判断查询
						// 1、起始为空赋给一个特别小的值（1000-1-1）
						if ((start_date == null || start_date.equals(""))&&((end_date != null 
								|| !("".equals(end_date))))) {
							start_date = "1000-1-1";
						}
						// 2、截止为空赋给一个特别大的值（今天）
						if ((start_date != null || !("".equals(start_date)))
								&& ((end_date == null ||end_date.equals("")))) {
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
							end_date = sf.format(new Date());

						}
						// 3、起始截止都为空两个都赋值
						if ((start_date == null ||start_date.equals(""))
								&& (end_date == null ||end_date.equals(""))) {
							start_date = "1000-1-1";
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
							end_date = sf.format(new Date());
						}
					
						
						listProcessTransition = productionPlanService.selectExcelProcessTransitionByParam(provider, batchNo, shopName, start_date, end_date);
					if (listProcessTransition != null && listProcessTransition.size() > 0) {

							// 5.将历史数据添加到单元格中 (先列后行)
							for (int j = 0; j < listProcessTransition.size(); j++) {
								row = sheet.createRow(j + 2);
								
								cell = row.createCell(0);
								cell.setCellValue(listProcessTransition.get(j).getPlanNo());
								cell = row.createCell(1);
								cell.setCellValue(listProcessTransition.get(j).getBatchNo());
								cell = row.createCell(2);
								cell.setCellValue(listProcessTransition.get(j).getClientMaterialNo());
								cell = row.createCell(3);
								cell.setCellValue(listProcessTransition.get(j).getMaterialNo());
								cell = row.createCell(4);
								cell.setCellValue(listProcessTransition.get(j).getShopName());
								cell = row.createCell(5);
								cell.setCellValue(listProcessTransition.get(j).getProcess1());
								cell = row.createCell(6);
								cell.setCellValue(listProcessTransition.get(j).getProvider());
								cell = row.createCell(7);
								cell.setCellValue(listProcessTransition.get(j).getShopName1());
								cell = row.createCell(8);
								cell.setCellValue(listProcessTransition.get(j).getProcess2());
								cell = row.createCell(9);
								cell.setCellValue(listProcessTransition.get(j).getAcceptor());
								cell = row.createCell(10);
								cell.setCellValue(listProcessTransition.get(j).getTranDate());
								cell = row.createCell(11);
								cell.setCellValue(listProcessTransition.get(j).getQualifiedNum());
								cell = row.createCell(12);
								cell.setCellValue(listProcessTransition.get(j).getUnqualifiedNum());
								//获取外键transitionId查询交接记录次品详细信息
								Integer transitionId=listProcessTransition.get(j).getTransitionId();
								List<TransitionCipin> listTransitionCipin=productionPlanService.selectProcessTransitionDetail(transitionId);
									//循环插入交接记录次品详细信息
									for(int m=0;m<listTransitionCipin.size();m++){
										cell = row.createCell(2*m+13);
										cell.setCellValue(listTransitionCipin.get(m).getCipinType());
										cell = row.createCell(2*m+1+13);
										cell.setCellValue(listTransitionCipin.get(m).getCipinSpecies()+"("+listTransitionCipin.get(m).getCipinNum()+")");
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
				
				
				
				
				//半成品出库单excel导出
				@RequestMapping("/importMiddleRecordExcel")
				public void importMiddleRecordExcel(Integer pageNow, HttpServletResponse response,
						String material_no, String start_date, String end_date) throws Exception {
					// 获取当天日期
					Date day = new Date();
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			
					// 文件名
					String fileName = "-半成品出库单据.xlsx";
					if (material_no == null && start_date == null && end_date == null) {

						response.setHeader("Content-disposition", "attachment;filename="
								+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
						response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
						response.setHeader("Cache-Control", "no-cache");// 设置头
						response.setDateHeader("Expires", 0);// 设置日期头
					} else {
						response.setHeader("Content-disposition",
								"attachment;filename="+df1.format(day)+ "-"+material_no+ new String(fileName.getBytes("gb2312"),
												"ISO8859-1")); // 设置文件头编码格式
						response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
						response.setHeader("Cache-Control", "no-cache");// 设置头
						response.setDateHeader("Expires", 0);// 设置日期头
					}

					// 文件标题栏
					String[] cellTitle ={"出库日期","计划单号","图号","物料号","半成品批次号","半成品名称","出库数量","提供班组","提供人","接受班组","接受人","备注"};
					try {

						// 声明一个工作薄
						XSSFWorkbook workBook = null;
						workBook = new XSSFWorkbook();

						// 生成一个表格
						XSSFSheet sheet = workBook.createSheet();
						workBook.setSheetName(0, "mes半成品出库清单");

						// 创建表格标题行 第2行(循环将标题值赋于第1行)
						XSSFRow titleRow = sheet.createRow(1);
						for (int i = 0; i < cellTitle.length; i++) {
							titleRow.createCell(i).setCellValue(cellTitle[i]);
						}

						// 创建行
						XSSFRow row = sheet.createRow((short) 0);
						// 创建单元格
						XSSFCell cell = null;
						// 第一行标题栏
						row = sheet.createRow(0);
						cell = row.createCell(2);
						cell.setCellValue(material_no);
						cell = row.createCell(3);
						cell.setCellValue(df1.format(day));
						cell = row.createCell(4);
						cell.setCellValue("mes半成品出库单据");
						
						// 设置居中
						XSSFCellStyle cellStyle = workBook.createCellStyle();
						cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

						// 设置列宽
						sheet.setColumnWidth(0, 15 * 256);
						sheet.setColumnWidth(1, 20 * 256);
						sheet.setColumnWidth(2, 15 * 256);
						sheet.setColumnWidth(3, 15 * 256);
						sheet.setColumnWidth(4, 20 * 256);
						sheet.setColumnWidth(5, 20 * 256);
						
						// 4.历史数据，业务数据，不用关注
						List<ProductRecord> listinputmiddlexcel = new ArrayList<ProductRecord>();
						
						// 用于查询条件未输时的判断查询
						// 1、起始为空赋给一个特别小的值（1000-1-1）
						if ((start_date == null || start_date.equals(""))&&((end_date != null 
								|| !("".equals(end_date))))) {
							start_date = "1000-1-1";
						}
						// 2、截止为空赋给一个特别大的值（今天）
						if ((start_date != null || !("".equals(start_date)))
								&& ((end_date == null ||end_date.equals("")))) {
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
							end_date = sf.format(new Date());

						}
						// 3、起始截止都为空两个都赋值
						if ((start_date == null ||start_date.equals(""))
								&& (end_date == null ||end_date.equals(""))) {
							start_date = "1000-1-1";
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
							end_date = sf.format(new Date());
						}

						listinputmiddlexcel = inputOrOutputRecordService.selectexcelinputMiddleRecord(material_no, start_date, end_date);
					if (listinputmiddlexcel != null && listinputmiddlexcel.size() > 0) {

							// 5.将历史数据添加到单元格中 (先列后行)
							for (int j = 0; j < listinputmiddlexcel.size(); j++) {
								row = sheet.createRow(j + 2);
								
								cell = row.createCell(0);
								cell.setCellValue(listinputmiddlexcel.get(j).getTransDate());
								cell = row.createCell(1);
								cell.setCellValue(listinputmiddlexcel.get(j).getPlanNo());
								cell = row.createCell(2);
								cell.setCellValue(listinputmiddlexcel.get(j).getClientMaterialNo());
								cell = row.createCell(3);
								cell.setCellValue(listinputmiddlexcel.get(j).getMaterialNo());
								cell = row.createCell(4);
								cell.setCellValue(listinputmiddlexcel.get(j).getBatchNo2());
								cell = row.createCell(5);
								cell.setCellValue(listinputmiddlexcel.get(j).getProductMc());
								cell = row.createCell(6);
								cell.setCellValue(listinputmiddlexcel.get(j).getProductNum());
								cell = row.createCell(7);
								cell.setCellValue(listinputmiddlexcel.get(j).getShop1());
								cell = row.createCell(8);
								cell.setCellValue(listinputmiddlexcel.get(j).getProvider());
								cell = row.createCell(9);
								cell.setCellValue(listinputmiddlexcel.get(j).getShop2());
								cell = row.createCell(10);
								cell.setCellValue(listinputmiddlexcel.get(j).getAcceptor());
								cell = row.createCell(11);
								cell.setCellValue(listinputmiddlexcel.get(j).getRemark());
								
								
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
				
				//半成品入库单excel导出
				@RequestMapping("/outportMiddleRecordExcel")
				public void outportMiddleRecordExcel(Integer pageNow, HttpServletResponse response,
						String material_no, String start_date, String end_date) throws Exception {
					// 获取当天日期
					Date day = new Date();
					SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			
					// 文件名
					String fileName = "-半成品入库单据.xlsx";
					if (material_no == null && start_date == null && end_date == null) {

						response.setHeader("Content-disposition", "attachment;filename="
								+ new String(fileName.getBytes("gb2312"), "ISO8859-1")); // 设置文件头编码格式
						response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
						response.setHeader("Cache-Control", "no-cache");// 设置头
						response.setDateHeader("Expires", 0);// 设置日期头
					} else {
						response.setHeader("Content-disposition",
								"attachment;filename="+df1.format(day)+ "-"+material_no+ new String(fileName.getBytes("gb2312"),
												"ISO8859-1")); // 设置文件头编码格式
						response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");// 设置类型
						response.setHeader("Cache-Control", "no-cache");// 设置头
						response.setDateHeader("Expires", 0);// 设置日期头
					}

					// 文件标题栏
					String[] cellTitle ={"入库日期","计划单号","图号","物料号","半成品批次号","半成品名称","入库重量(kg)","入库数量","提供班组","提供人","接受班组","接受人","备注"};
					try {

						// 声明一个工作薄
						XSSFWorkbook workBook = null;
						workBook = new XSSFWorkbook();

						// 生成一个表格
						XSSFSheet sheet = workBook.createSheet();
						workBook.setSheetName(0, "mes半成品入库清单");

						// 创建表格标题行 第2行(循环将标题值赋于第1行)
						XSSFRow titleRow = sheet.createRow(1);
						for (int i = 0; i < cellTitle.length; i++) {
							titleRow.createCell(i).setCellValue(cellTitle[i]);
						}

						// 创建行
						XSSFRow row = sheet.createRow((short) 0);
						// 创建单元格
						XSSFCell cell = null;
						// 第一行标题栏
						row = sheet.createRow(0);
						cell = row.createCell(2);
						cell.setCellValue(material_no);
						cell = row.createCell(3);
						cell.setCellValue(df1.format(day));
						cell = row.createCell(4);
						cell.setCellValue("mes半成品入库单据");
						
						// 设置居中
						XSSFCellStyle cellStyle = workBook.createCellStyle();
						cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

						// 设置列宽
						sheet.setColumnWidth(0, 15 * 256);
						sheet.setColumnWidth(1, 20 * 256);
						sheet.setColumnWidth(2, 15 * 256);
						sheet.setColumnWidth(3, 15 * 256);
						sheet.setColumnWidth(4, 20 * 256);
						sheet.setColumnWidth(5, 20 * 256);
						sheet.setColumnWidth(6, 20 * 256);
						sheet.setColumnWidth(12, 20 * 256);
						
						// 4.历史数据，业务数据，不用关注
						List<ProductRecord> listoutputmiddlexcel = new ArrayList<ProductRecord>();
						
						// 用于查询条件未输时的判断查询
						// 1、起始为空赋给一个特别小的值（1000-1-1）
						if ((start_date == null || start_date.equals(""))&&((end_date != null 
								|| !("".equals(end_date))))) {
							start_date = "1000-1-1";
						}
						// 2、截止为空赋给一个特别大的值（今天）
						if ((start_date != null || !("".equals(start_date)))
								&& ((end_date == null ||end_date.equals("")))) {
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
							end_date = sf.format(new Date());

						}
						// 3、起始截止都为空两个都赋值
						if ((start_date == null ||start_date.equals(""))
								&& (end_date == null ||end_date.equals(""))) {
							start_date = "1000-1-1";
							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
							end_date = sf.format(new Date());
						}

						listoutputmiddlexcel = inputOrOutputRecordService.selectexceloutputMiddleRecord(material_no, start_date, end_date);
					if (listoutputmiddlexcel != null && listoutputmiddlexcel.size() > 0) {

							// 5.将历史数据添加到单元格中 (先列后行)
							for (int j = 0; j < listoutputmiddlexcel.size(); j++) {
								row = sheet.createRow(j + 2);
								
								cell = row.createCell(0);
								cell.setCellValue(listoutputmiddlexcel.get(j).getTransDate());
								cell = row.createCell(1);
								cell.setCellValue(listoutputmiddlexcel.get(j).getPlanNo());
								cell = row.createCell(2);
								cell.setCellValue(listoutputmiddlexcel.get(j).getClientMaterialNo());
								cell = row.createCell(3);
								cell.setCellValue(listoutputmiddlexcel.get(j).getMaterialNo());
								cell = row.createCell(4);
								cell.setCellValue(listoutputmiddlexcel.get(j).getBatchNo2());
								cell = row.createCell(5);
								cell.setCellValue(listoutputmiddlexcel.get(j).getProductMc());
								cell = row.createCell(6);
								cell.setCellValue(listoutputmiddlexcel.get(j).getWeight());
								cell = row.createCell(7);
								cell.setCellValue(listoutputmiddlexcel.get(j).getProductNum());
								cell = row.createCell(8);
								cell.setCellValue(listoutputmiddlexcel.get(j).getShop1());
								cell = row.createCell(9);
								cell.setCellValue(listoutputmiddlexcel.get(j).getProvider());
								cell = row.createCell(10);
								cell.setCellValue(listoutputmiddlexcel.get(j).getShop2());
								cell = row.createCell(11);
								cell.setCellValue(listoutputmiddlexcel.get(j).getAcceptor());
								cell = row.createCell(12);
								cell.setCellValue(listoutputmiddlexcel.get(j).getRemark());
								
								
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
				
				
				
			
				
				// 1.1月计划记录
				@RequestMapping("/toMonthPlan")
				public String toMonthPlan(MonthPlan monthPlan, Integer pageNow,Model model) throws Exception {
					
				
					int totalCount = 0;
					// 返回周计划的行数
					totalCount = monthPlanMapper.selectMonthPlanCount(monthPlan);
					Page page = null;
					if (pageNow != null) {
						page = new Page(totalCount, pageNow);
					} else {
						page = new Page(totalCount, 1);
					}  
					monthPlan.setPageSize(page.getPageSize());
					monthPlan.setStartPos(page.getStartPos());
					
					List<MonthPlan>	listMonthPlan = monthPlanService.selectMonthPlan(monthPlan);
					model.addAttribute("listMonthPlan", listMonthPlan);
					model.addAttribute("page", page);
					model.addAttribute("company", monthPlan.getCompany());
					model.addAttribute("clientMaterialNo", monthPlan.getClientMaterialNo());
					model.addAttribute("month", monthPlan.getMonth());
					return "monthPlan";

				}
				
				
				// 1.2生成月计划导入excel模板
				@RequestMapping("/toMonthSample")
				public void toMonthSample(HttpServletResponse response) {
					// 文件名
					String fileName = " ";
					// 获取当天日期
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					fileName = df.format(day) + "-月计划模板" + ".xls";

					response.setContentType("application/x-excel");
					response.setCharacterEncoding("UTF-8");

					// 为了标题中文拼接
					try {
						response.addHeader("Content-Disposition", new String(
								("attachment; filename=" + fileName).getBytes("GBK"),
								"ISO-8859-1"));
					} catch (UnsupportedEncodingException e1) {

						e1.printStackTrace();
					}

					try {
						// 1.创建excel文件
						WritableWorkbook book = Workbook.createWorkbook(response
								.getOutputStream());
						// 居中
						WritableCellFormat wf = new WritableCellFormat();
						wf.setAlignment(Alignment.CENTRE);

						WritableSheet sheet = null;
						SheetSettings settings = null;

						// 2.创建sheet并设置冻结前1行
						sheet = book.createSheet("月计划清单", 0);
						settings = sheet.getSettings();
						settings.setVerticalFreeze(1);
						// 3.添加第一行及第二行标题数据 (先列后行)

						sheet.addCell(new Label(0, 0, "月份", wf));
						sheet.addCell(new Label(1, 0, "物料名称", wf));
						sheet.setColumnView(1, 20);
						sheet.addCell(new Label(2, 0, "客户", wf));
						sheet.setColumnView(2, 30);
						sheet.addCell(new Label(3, 0, "图号", wf));
						sheet.setColumnView(3, 15);
						sheet.addCell(new Label(4, 0, "产品规格", wf));
						sheet.setColumnView(4, 25);
						sheet.addCell(new Label(5, 0, "计划数量", wf));
						sheet.addCell(new Label(6, 0, "备注", wf));
						sheet.setColumnView(6, 30);

						// 6.写入excel并关闭
						book.write();
						book.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				
				
				 //1.3月计划excel导入
				@RequestMapping("/importMonthExcel")
				@ResponseBody
				public String importMonthExcel(HttpServletRequest request,
						@RequestParam(value = "excelPath", required = false) MultipartFile excelPath)throws Exception {
					// 需要添加数据判断： 数据库有待插入表中主键相同的数据，换成update,数据库中没有，使用insert
					// 需要判断excel表是否为空，为空，return 0 ，
					try {
						Workbook book = Workbook.getWorkbook(excelPath.getInputStream());// 获取流(会自动识别流中文件的实际地址，取得excel文件中的内容)

						Sheet sheet = book.getSheet(0);
						for (int i = 1; i < sheet.getRows(); i++) {
							// 有效数据的行数
							Cell[] cell = sheet.getRow(i);
							// 判断有效数据的行中是否存在空
							if (cell.length == 0)
								continue;

							// 判断sheet的名称是不是设备表，以确定导入的是不是设备表
							if (sheet.getName().equals("月计划清单") == false) {
								return "3";
							}
							MonthPlan m=new MonthPlan();
							// 判断excel表格中内容不为空，为空跳出循环，excel表格内容为空！
							if (sheet.getCell(0, i).getContents() != null&& !("".equals(sheet.getCell(0, i).getContents()))
							&&sheet.getCell(3, i).getContents() != null&& !("".equals(sheet.getCell(3, i).getContents()))
							&&sheet.getCell(5, i).getContents() != null&& !("".equals(sheet.getCell(5, i).getContents()))) {
								//插入数据库内容
								m.setMonth(sheet.getCell(0, i).getContents());
								m.setProductName(sheet.getCell(1, i).getContents());
								m.setCompany(sheet.getCell(2, i).getContents());
								m.setClientMaterialNo(sheet.getCell(3, i).getContents());
								m.setSpec(sheet.getCell(4, i).getContents());
								m.setMonthNum(sheet.getCell(5, i).getContents());
								m.setRemark(sheet.getCell(6, i).getContents());

								} else {
									return "2";
								}
								// 检查数据库中是否存在该条记录，有则只更新，不插入。
								String month = m.getMonth();
								String company = m.getCompany();
								String clientMaterialNo=m.getClientMaterialNo();
								List<MonthPlan> YesorNo = monthPlanService.selectMonthPlanByParam(month, company, clientMaterialNo);
								if (YesorNo.size() == 0) {
									// 循环将数据插入数据库
									monthPlanService.insertSelective(m);
								} else {
									// 更新是根据主键更新，获得主键
									m.setPlanId(YesorNo.get(0).getPlanId());
									monthPlanService.updateByPrimaryKeySelective(m);
								}
							
						}
						book.close();
						return "1";
					} catch (Exception e) {
						return "0";

					}

				}
				
				
				//月计划删除记录
				@RequestMapping("DeleteMonthRecord")
				public String DeleteMonthRecord(Integer planId)
						throws Exception {
					monthPlanService.deleteByPrimaryKey(planId);
					return "redirect:toMonthPlan.action";
				}
					
}