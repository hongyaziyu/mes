package cn.ssm.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import jxl.Cell;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.ssm.mapper.AssetMapper;
import cn.ssm.mapper.CheckContentMapper;
import cn.ssm.mapper.CipinTypeMapper;
import cn.ssm.mapper.HeightGaugeMapper;
import cn.ssm.mapper.MiddleProductDetailMapper;
import cn.ssm.mapper.MiddleProductMapper;
import cn.ssm.mapper.PersonMapper;
import cn.ssm.mapper.ProductsBomMapper;
import cn.ssm.mapper.SecondaryMaterialsMapper;
import cn.ssm.mapper.SpecialGaugeMapper;
import cn.ssm.po.Asset;
import cn.ssm.po.CheckContent;
import cn.ssm.po.CipinType;
import cn.ssm.po.HeightGauge;
import cn.ssm.po.MiddleProduct;
import cn.ssm.po.MiddleProductDetail;
import cn.ssm.po.Molds;
import cn.ssm.po.Permission;
import cn.ssm.po.Person;
import cn.ssm.po.ProductsBom;
import cn.ssm.po.SecondaryMaterials;
import cn.ssm.po.ShopProcess;
import cn.ssm.po.ShopSort;
import cn.ssm.po.SpecialGauge;
import cn.ssm.po.TestProcess;
import cn.ssm.po.Url;
import cn.ssm.service.AssetSelectService;
import cn.ssm.service.CheckSelectService;
import cn.ssm.service.CipinTypeService;
import cn.ssm.service.DailyCheckService;
import cn.ssm.service.MiddleInventoryService;
import cn.ssm.service.MiddleProductDetailService;
import cn.ssm.service.PersonService;
import cn.ssm.service.ProductPlanService;
import cn.ssm.service.SecMaterialService;
import cn.ssm.util.MD5;
import cn.ssm.util.Page;

@Controller
@RequestMapping("/manage")
public class ManageController {
	@Autowired
	private AssetSelectService assetSelectService;
	@Autowired
	private ProductPlanService productPlanService;
	@Autowired
	private PersonService personService;
	@Autowired
	private CipinTypeService cipinTypeService;
	@Autowired
	private SecMaterialService secMaterialService;
	@Autowired
	private CheckSelectService checkSelectService;
	@Autowired
	private MiddleInventoryService middleInventoryService;
	@Autowired
	private DailyCheckService dailyCheckService;
	@Autowired
	private MiddleProductDetailService middleProductDetailService;
	// 分页所需
	@Autowired
	private AssetMapper assetMapper;
	@Autowired
	private ProductsBomMapper productsBomMapper;
	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private CipinTypeMapper cipinTypeMapper;
	@Autowired
	private SecondaryMaterialsMapper secondaryMaterialsMapper;
	@Autowired
	private HeightGaugeMapper heightGaugeMapper;
	@Autowired
	private SpecialGaugeMapper specialGaugeMapper;
	@Autowired
	private MiddleProductMapper middleProductMapper;
	@Autowired
	private CheckContentMapper checkContentMapper;
	@Autowired
	private MiddleProductDetailMapper middleProductDetailMapper;
	
   // 实现添加网页的跳转(检具-高度规表)
	@RequestMapping("/toinsertHeightGauge")
	public String toinsertHeightGauge() throws Exception {
		return "addheight_gauge";
	}
	
	// 实现添加网页的跳转(检具-特殊规表)
	@RequestMapping("/toinsertSpecialGauge")
	public String toinsertSpecialGauge() throws Exception {
		return "addspecial_gauge";
	}
	
	// 实现添加网页的跳转(辅料类型表)
	@RequestMapping("/toinsertSec")
	public String toinsertSec() throws Exception {
		return "addsec_material";
	}
	
	// 实现添加网页的跳转(添加不良品类型表)
	@RequestMapping("/toinsertCipin")
	public String toinsertCipin() throws Exception {
		return "addcipin_type";
	}
	
	// 实现添加网页的跳转(添加设备表)
	@RequestMapping("/toinsertAsset")
	public String toinsertAsset() throws Exception {
		return "insertasset";
	}

	// 实现添加网页的跳转(添加模具表)
	@RequestMapping("/toinsertMolds")
	public String toinsertAsset(Molds record, Model model) throws Exception {
		return "insertmolds";

	}

	// 实现添加网页的跳转(添加产品表)
	@RequestMapping("/toaddProduct")
	public String toaddProduct() throws Exception {
		return "addproduct";

	}
	
	// 实现添加网页的跳转(添加半成品库存表)
	@RequestMapping("/toaddMiddleInventory")
	public String toaddMiddleInventory() throws Exception {
		return "addmiddle_inventory";

	}
	
	// 实现添加网页的跳转(添加设备点检内容表)
	@RequestMapping("/toaddAssetCheckRecord")
	public String toaddAssetCheckRecord() throws Exception {
		return "addasset_check_record";

	}
	
	           // 1、查询操作
				// 检具-高度规查询分页
				@RequestMapping("/togetHeightGauge")
				// 返回String类型
				public String togetHeightGauge(Integer pageNow, String gaugeNo,
						String productSpecification, Model model) throws Exception {
					// 定义一个参数listAsset作为调用service层方法 的返回值
					List<HeightGauge> listHeight = new ArrayList<HeightGauge>();

					int totalCount = 0;
					// 返回查询的行数totalCount

					totalCount = heightGaugeMapper.selectHeightGaugeByParamtotalCount(gaugeNo, productSpecification);
					Page page = null;
					if (pageNow != null) {
						page = new Page(totalCount, pageNow);
					} else {
						page = new Page(totalCount, 1);
					}

					// 调用service的层中实现类的方法
					listHeight = checkSelectService.selectHeightGaugeByParam(page.getStartPos(),page.getPageSize(), gaugeNo, productSpecification);
					// controller层中使model能返回一个jsp页面，在jsp页面便于用EL表达式${}获取值，使model.addAttribute返回jsp页面输出
					model.addAttribute("listHeight", listHeight);
					// 返回page类（里面有总页数，当前页等信息）
					model.addAttribute("page", page);
					model.addAttribute("gaugeNo", gaugeNo);
					model.addAttribute("productSpecification", productSpecification);
					return "height_gauge";
				}
		
				// 2、添加检具-高度规信息操作
				@RequestMapping("/insertHeightGauge")
				// 为方法设置访问路径
				public String insertHeightGauge(HeightGauge record, Model model) throws Exception {
					checkSelectService.insert(record);
					return "redirect:togetHeightGauge.action";

				}

				// 3、删除操作
				@RequestMapping("/deleteHeightGauge")
				public String deleteHeightGauge(Integer id, Model model) {
					checkSelectService.deleteByPrimaryKey(id);
					return "redirect:togetHeightGauge.action";
				}

				// 4、修改操作
				// 先查询出来，用于数据的回显
				@RequestMapping("/updateHeightGauge")
				public String updateHeightGauge(Integer id, Model model) throws Exception {
					HeightGauge height = checkSelectService.selectByPrimaryKey(id);
					model.addAttribute("height", height);
					return "editheight_gauge";
				}

				@RequestMapping("/saveOrupdateHeightGauge")
				public String saveOrupdateHeightGauge(HeightGauge record, Model model) throws Exception {
					checkSelectService.updateByPrimaryKeySelective(record);
					return "redirect:togetHeightGauge.action";

				}
			
				// 5.生成excel模板
				@RequestMapping("/SpecialGaugeSample")
				public void SpecialGaugeSample(HttpServletResponse response) {
					// 文件名
					String fileName = " ";
					// 获取当天日期
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					fileName = df.format(day) + "检具-特殊规模板" + ".xls";

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
						sheet = book.createSheet("特殊规", 0);
						settings = sheet.getSettings();
						settings.setVerticalFreeze(1);
						// 3.添加第一行及第二行标题数据 (先列后行)

						sheet.addCell(new Label(0, 0, "所检产品", wf));
						sheet.setColumnView(0, 40);
						sheet.addCell(new Label(1, 0, "适用类型", wf));
						sheet.addCell(new Label(2, 0, "检具名称", wf));
						sheet.setColumnView(2, 40);
						sheet.addCell(new Label(3, 0, "检具编号", wf));
						sheet.setColumnView(3, 30);
						// 6.写入excel并关闭
						book.write();
						book.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				
				// 6.excel导入
				@Transactional
				@RequestMapping("/importSpecialGaugeExcel")
				@ResponseBody
				public String importSpecialGaugeExcel(HttpServletRequest request,@RequestParam(value = "excelPath", required = false) MultipartFile excelPath)
						throws Exception {
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

							// 判断sheet的名称是不是高度表，以确定导入的是不是高度表
							if (sheet.getName().equals("特殊规") == false) {
								return "3";
							}
							SpecialGauge s = new SpecialGauge();
							// 判断excel表格中内容不为空，为空跳出循环，excel表格内容为空！
							if (sheet.getCell(2, i).getContents() != null
								&& !("".equals(sheet.getCell(2, i).getContents()))
								&&sheet.getCell(3, i).getContents() != null
								&& !("".equals(sheet.getCell(3, i).getContents()))) {
								s.setInspectionProduction(sheet.getCell(0, i).getContents());
								s.setType(sheet.getCell(1, i).getContents());
								s.setGaugeName(sheet.getCell(2, i).getContents());
								s.setGaugeNo(sheet.getCell(3, i).getContents());
								
							} else {
								return "2";
							}
							// 检查数据库中是否存在该条记录，有则只更新，不插入。
							String gaugeName = s.getGaugeName();
							String gaugeNo = s.getGaugeNo();
							List<SpecialGauge> YesorNo = checkSelectService.selectSpecialGaugeByParam1(gaugeName, gaugeNo);

							if (YesorNo.size() == 0) {
								// 循环将数据插入数据库
								checkSelectService.insertSelect(s);
							} else {
								// 更新是根据主键更新，获得主键
								s.setId(YesorNo.get(0).getId());
								checkSelectService.updatePrimaryKeySelective(s);
							}

						}
						book.close();
						return "1";
					} catch (Exception e) {
						return "0";

					}

				}
				
				
				
				
				
				
				// 1、查询操作
				// 检具-特殊规查询分页
				@RequestMapping("/togetSpecialGauge")
				// 返回String类型
				public String togetSpecialGauge(Integer pageNow, String gaugeName,
						String gaugeNo, Model model) throws Exception {
					// 定义一个参数listAsset作为调用service层方法 的返回值
					List<SpecialGauge> listSpecial = new ArrayList<SpecialGauge>();

					int totalCount = 0;
					// 返回查询的行数totalCount

					totalCount = specialGaugeMapper.selectSpecialGaugeByParamtotalCount(gaugeName, gaugeNo);
					Page page = null;
					if (pageNow != null) {
						page = new Page(totalCount, pageNow);
					} else {
						page = new Page(totalCount, 1);
					}

					// 调用service的层中实现类的方法
					listSpecial = checkSelectService.selectSpecialGaugeByParam(page.getStartPos(),page.getPageSize(), gaugeName, gaugeNo);
					// controller层中使model能返回一个jsp页面，在jsp页面便于用EL表达式${}获取值，使model.addAttribute返回jsp页面输出
					model.addAttribute("listSpecial", listSpecial);
					// 返回page类（里面有总页数，当前页等信息）
					model.addAttribute("page", page);
					model.addAttribute("gaugeName", gaugeName);
					model.addAttribute("gaugeNo", gaugeNo);
					return "special_gauge";
				}
		
				// 2、添加检具-特殊规信息操作
				@RequestMapping("/insertSpecialGauge")
				// 为方法设置访问路径
				public String insertSpecialGauge(SpecialGauge record, Model model) throws Exception {
					checkSelectService.insertSelect(record);
					return "redirect:togetSpecialGauge.action";

				}

				// 3、删除检具-特殊规操作
				@RequestMapping("/deleteSpecialGauge")
				public String deleteSpecialGauge(Integer id, Model model) {
					checkSelectService.deletePrimaryKey(id);
					return "redirect:togetSpecialGauge.action";
				}

				// 4、修改检具-特殊规操作
				// 先查询出来，用于数据的回显
				@RequestMapping("/updateSpecialGauge")
				public String updateSpecialGauge(Integer id, Model model) throws Exception {
					SpecialGauge special = checkSelectService.selectPrimaryKey(id);
					model.addAttribute("special", special);
					return "editspecial_gauge";
				}

				@RequestMapping("/saveOrupdateSpecialGauge")
				public String saveOrupdateSpecialGauge(SpecialGauge record, Model model) throws Exception {
					checkSelectService.updatePrimaryKeySelective(record);
					return "redirect:togetSpecialGauge.action";

				}
				
				// 5.生成excel模板
				@RequestMapping("/HeightGaugeSample")
				public void HeightGaugeSample(HttpServletResponse response) {
					// 文件名
					String fileName = " ";
					// 获取当天日期
					Date day = new Date();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					fileName = df.format(day) + "检具-高度规模板" + ".xls";

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
						sheet = book.createSheet("高度规", 0);
						settings = sheet.getSettings();
						settings.setVerticalFreeze(1);
						// 3.添加第一行及第二行标题数据 (先列后行)

						sheet.addCell(new Label(0, 0, "所检物料号", wf));
						sheet.setColumnView(0, 40);
						sheet.addCell(new Label(1, 0, "产品规格", wf));
						sheet.setColumnView(1, 40);
						sheet.addCell(new Label(2, 0, "检具公差范围", wf));
						sheet.setColumnView(2, 20);
						sheet.addCell(new Label(3, 0, "数量", wf));
						sheet.addCell(new Label(4, 0, "申报日期", wf));
						sheet.setColumnView(4, 20);
						sheet.addCell(new Label(5, 0, "用于何处", wf));
						sheet.setColumnView(5, 20);
						sheet.addCell(new Label(6, 0, "完成日期人", wf));
						sheet.setColumnView(6, 20);
						sheet.addCell(new Label(7, 0, "领用人员", wf));
						sheet.setColumnView(7, 30);
						// 6.写入excel并关闭
						book.write();
						book.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				
				// 6.excel导入
				@RequestMapping("/importHeightGaugeExcel")
				@ResponseBody
				public String importHeightGaugeExcel(HttpServletRequest request,@RequestParam(value = "excelPath", required = false) MultipartFile excelPath)
						throws Exception {
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

							// 判断sheet的名称是不是高度表，以确定导入的是不是高度表
							if (sheet.getName().equals("高度规") == false) {
								return "3";
							}
							HeightGauge h = new HeightGauge();
							// 判断excel表格中内容不为空，为空跳出循环，excel表格内容为空！
							if (sheet.getCell(0, i).getContents() != null
								&& !("".equals(sheet.getCell(0, i).getContents()))
								&&sheet.getCell(1, i).getContents() != null
								&& !("".equals(sheet.getCell(1, i).getContents()))) {
								h.setGaugeNo(sheet.getCell(0, i).getContents());
								h.setProductSpecification(sheet.getCell(1, i).getContents());
								h.setToleranceRange(sheet.getCell(2, i).getContents());
								h.setNum(sheet.getCell(3, i).getContents());
								h.setDeclarationDate(sheet.getCell(4, i).getContents());
								h.setPurpose(sheet.getCell(5, i).getContents());
								h.setCompletionDate(sheet.getCell(6, i).getContents());
								h.setCollarWorkers(sheet.getCell(7, i).getContents());
							} else {
								return "2";
							}
							// 检查数据库中是否存在该条记录，有则只更新，不插入。
							String gaugeNo = h.getGaugeNo();
							String productSpecification = h.getProductSpecification();
							List<HeightGauge> YesorNo = checkSelectService.selectHeightGaugeByParam1(gaugeNo, productSpecification);

							if (YesorNo.size() == 0) {
								// 循环将数据插入数据库
								checkSelectService.insert(h);
							} else {
								// 更新是根据主键更新，获得主键
								h.setId(YesorNo.get(0).getId());
								checkSelectService.updateByPrimaryKeySelective(h);
							}

						}
						book.close();
						return "1";
					} catch (Exception e) {
						return "0";

					}

				}
				
				
				
				
	        // 1、查询操作
			// 辅料查询分页
			@RequestMapping("/toSecMaterial")
			// 返回String类型
			public String toSecMaterial(Integer pageNow, String secMaterialsName,
					String type, Model model) throws Exception {
				// 定义一个参数listAsset作为调用service层方法 的返回值
				List<SecondaryMaterials> listSec = new ArrayList<SecondaryMaterials>();

				int totalCount = 0;
				// 返回查询的行数totalCount

				totalCount = secondaryMaterialsMapper.selectSecByParamtotalCount(secMaterialsName, type);
				Page page = null;
				if (pageNow != null) {
					page = new Page(totalCount, pageNow);
				} else {
					page = new Page(totalCount, 1);
				}

				// 调用service的层中实现类的方法
				listSec = secMaterialService.selectSecByParam(page.getStartPos(),page.getPageSize(), secMaterialsName, type);
				// controller层中使model能返回一个jsp页面，在jsp页面便于用EL表达式${}获取值，使model.addAttribute返回jsp页面输出
				model.addAttribute("listSec", listSec);
				// 返回page类（里面有总页数，当前页等信息）
				model.addAttribute("page", page);
				model.addAttribute("secMaterialsName", secMaterialsName);
				model.addAttribute("type", type);
				return "sec_material";
			}
	
			// 2、添加辅料信息操作
			@RequestMapping("/insertSec")
			// 为方法设置访问路径
			public String insertSec(SecondaryMaterials record, Model model) throws Exception {
				secMaterialService.insert(record);
				return "redirect:toSecMaterial.action";

			}

			// 3、删除操作
			@RequestMapping("/deleteSec")
			public String deleteSec(Integer materialsId, Model model) {
				secMaterialService.deleteByPrimaryKey(materialsId);
				return "redirect:toSecMaterial.action";
			}

			// 4、修改操作
			// 先查询出来，用于数据的回显
			@RequestMapping("/updateSec")
			public String updateSec(Integer materialsId, Model model) throws Exception {
				SecondaryMaterials sec = secMaterialService.selectByPrimaryKey(materialsId);
				model.addAttribute("sec", sec);
				return "editsec_material";
			}

			@RequestMapping("/saveOrupdateSec")
			public String saveOrupdateSec(SecondaryMaterials record, Model model) throws Exception {
				secMaterialService.updateByPrimaryKeySelective(record);
				return "redirect:toSecMaterial.action";

			}	
			
			// 5.生成excel模板
			@RequestMapping("/SecSample")
			public void SecSample(HttpServletResponse response) {
				// 文件名
				String fileName = " ";
				// 获取当天日期
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				fileName = df.format(day) + "-辅料表模板" + ".xls";

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
					sheet = book.createSheet("辅料清单", 0);
					settings = sheet.getSettings();
					settings.setVerticalFreeze(1);
					// 3.添加第一行及第二行标题数据 (先列后行)

					sheet.addCell(new Label(0, 0, "存货名称", wf));
					sheet.setColumnView(0, 40);
					sheet.addCell(new Label(1, 0, "规格型号", wf));
					sheet.setColumnView(1, 30);
					sheet.addCell(new Label(2, 0, "计价方式", wf));
					sheet.addCell(new Label(3, 0, "所属类别编号", wf));
					sheet.setColumnView(3, 20);
					sheet.addCell(new Label(4, 0, "主计量", wf));
					sheet.addCell(new Label(5, 0, "存货描述", wf));
					sheet.setColumnView(5, 50);
					sheet.addCell(new Label(6, 0, "产品图号", wf));
					sheet.setColumnView(6, 20);

					// 6.写入excel并关闭
					book.write();
					book.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			// 6.excel导入
			@RequestMapping("/importSecExcel")
			@ResponseBody
			public String importSecExcel(
					HttpServletRequest request,
					@RequestParam(value = "excelPath", required = false) MultipartFile excelPath)
					throws Exception {
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
						if (sheet.getName().equals("辅料清单") == false) {
							return "3";
						}
						SecondaryMaterials s = new SecondaryMaterials();
						// 判断excel表格中内容不为空，为空跳出循环，excel表格内容为空！
						if (sheet.getCell(0, i).getContents() != null
							&& !("".equals(sheet.getCell(0, i).getContents()))) {
							s.setSecMaterialsName(sheet.getCell(0, i).getContents());
							s.setType(sheet.getCell(1, i).getContents());
							s.setMoney(sheet.getCell(2, i).getContents());
							s.setNumber(sheet.getCell(3, i).getContents());
							s.setUnit(sheet.getCell(4, i).getContents());
							s.setProductDescribe(sheet.getCell(5, i).getContents());
							s.setProductNo(sheet.getCell(6, i).getContents());
						} else {
							return "2";
						}
						// 检查数据库中是否存在该条记录，有则只更新，不插入。
						String secMaterialsName = s.getSecMaterialsName();
						String type = s.getType();
						List<SecondaryMaterials> YesorNo = secMaterialService.selectSecByParamAjax(secMaterialsName, type);

						if (YesorNo.size() == 0) {
							// 循环将数据插入数据库
							secMaterialService.insert(s);
						} else {
							// 更新是根据主键更新，获得主键
							s.setMaterialsId(YesorNo.get(0).getMaterialsId());
							secMaterialService.updateByPrimaryKeySelective(s);
						}

					}
					book.close();
					return "1";
				} catch (Exception e) {
					return "0";

				}

			}
	// 1、查询操作通过Id和name
		// 不良品查询分页
		@RequestMapping("/toCipin")
		// 返回String类型
		public String toCipin(Integer pageNow, String shopName,
				String cipinType, Model model) throws Exception {
			// 定义一个参数listAsset作为调用service层方法 的返回值
			List<CipinType> listCipin = new ArrayList<CipinType>();

			int totalCount = 0;
			// 返回查询的行数totalCount

			totalCount = cipinTypeMapper.selectCipinByParamtotalCount(shopName,
					cipinType);

			Page page = null;
			if (pageNow != null) {
				page = new Page(totalCount, pageNow);
			} else {
				page = new Page(totalCount, 1);
			}

			// 调用service的层中实现类的方法
			listCipin = cipinTypeService.selectCipinByParam(page.getStartPos(),page.getPageSize(), shopName, cipinType);
			// controller层中使model能返回一个jsp页面，在jsp页面便于用EL表达式${}获取值，使model.addAttribute返回jsp页面输出
			model.addAttribute("listCipin", listCipin);
			// 返回page类（里面有总页数，当前页等信息）
			model.addAttribute("page", page);
			model.addAttribute("shopName", shopName);
			model.addAttribute("cipinType", cipinType);
			return "cipin_type";
		}
		
		
			
		// 2、添加不良品表信息操作
		@RequestMapping("/insertCipin")
		// 为方法设置访问路径
		public String insertCipin(CipinType record,HttpServletRequest request,Integer num, Model model) throws Exception {
			
			for (int i = 1; i <= num; i++) {
			// request.getParameter()括号内接受前端jsp页面的输入值
			String shopName = request.getParameter("shopName" + i);
			String cipinType = request.getParameter("cipinType" + i);
			String cipinDetail = request.getParameter("cipinDetail" + i);
			// 创建类接受内容
			CipinType cipinType1 = new CipinType();
			cipinType1.setShopName(shopName);
			cipinType1.setCipinType(cipinType);
			cipinType1.setCipinDetail(cipinDetail);
			
			cipinTypeService.insertSelective(cipinType1);
		}
			return "redirect:toCipin.action";

		}
		
		// 3、删除操作
		@RequestMapping("/deleteCipin")
		public String deleteCipin(String shopName,String cipinType, Model model) {
			cipinTypeService.deleteByPrimaryKey(shopName,cipinType);
			return "redirect:toCipin.action";
		}

		
		
		// 4、修改操作
		// 先查询出来，用于数据的回显
		@RequestMapping("/updateCipin")
		public String updateCipin(String shopName,String cipinType, Model model) throws Exception {
			List<CipinType> listcipinType = cipinTypeService.selectCipinByType(shopName, cipinType);
			model.addAttribute("listcipinType", listcipinType);
			return "editcipin_type";
		}

		@RequestMapping("/saveOrupdate2")
		public String saveOrupdate2(HttpServletRequest request,Integer num, Model model) throws Exception {
			
			List<CipinType> listCipinType=new ArrayList<CipinType>();
			for (int i = 1; i <= num; i++) {
				// request.getParameter()括号内接受前端jsp页面的输入值
				String shopName = request.getParameter("shopName" + i);
				String cipinType = request.getParameter("cipinType" + i);
				String cipinDetail = request.getParameter("cipinDetail" + i);
				// 创建类接受内容
				CipinType cipinType1 = new CipinType();
				cipinType1.setShopName(shopName);
				cipinType1.setCipinType(cipinType);
				cipinType1.setCipinDetail(cipinDetail);
				listCipinType.add(cipinType1);
			}	
			String shopName = request.getParameter("shopName1");
			String cipinType = request.getParameter("cipinType1");
			//先删除再插入
			cipinTypeService.deleteByPrimaryKey(shopName, cipinType);
			cipinTypeService.insertByType(listCipinType);
			return "redirect:toCipin.action";
		}
		
		
		
	// 1、查询操作通过Id和name
	// 设备查询分页
	// 方法路径-@RequestMapping("/getAsset")
	@RequestMapping("/togetAsset")
	// 返回String类型
	public String togetAsset(Integer pageNow, String asset_name,
			String asset_no, Model model) throws Exception {
		// 定义一个参数listAsset作为调用service层方法 的返回值
		List<Asset> listAsset = new ArrayList<Asset>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = assetMapper.selectAssetByParamtotalCount(asset_name,
				asset_no);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		// 调用service的层中实现类的方法
		listAsset = assetSelectService.selectAssetByParam(page.getStartPos(),
				page.getPageSize(), asset_name, asset_no);
		// controller层中使model能返回一个jsp页面，在jsp页面便于用EL表达式${}获取值，使model.addAttribute返回jsp页面输出
		model.addAttribute("listAsset", listAsset);
		// 返回page类（里面有总页数，当前页等信息）
		model.addAttribute("page", page);
		model.addAttribute("asset_name", asset_name);
		model.addAttribute("asset_no", asset_no);
		return "asset";
	}

	// 2、添加设备信息操作
	@RequestMapping("/insertAsset")
	// 为方法设置访问路径
	public String insertAsset(Asset record, Model model) throws Exception {
		assetSelectService.insert(record);
		return "redirect:togetAsset.action";

	}

	// 3、删除操作
	@RequestMapping("/deleteAsset")
	public String deleteAsset(Integer assetId, Model model) {
		assetSelectService.deleteByPrimaryKey(assetId);
		return "redirect:togetAsset.action";
	}

	// 4、修改操作
	// 先查询出来，用于数据的回显
	@RequestMapping("/updateAsset")
	public String updateAsset(Integer assetId, Model model) throws Exception {
		Asset asset = assetSelectService.selectByPrimaryKey(assetId);
		model.addAttribute("asset", asset);
		return "editasset";
	}

	@RequestMapping("/saveOrupdate")
	public String saveOrupdate(Asset record, Model model) throws Exception {
		assetSelectService.updateByPrimaryKeySelective(record);
		return "redirect:togetAsset.action";
	}

	// 6.生成excel模板
	@RequestMapping("/AssetSample")
	public void AssetSample(HttpServletResponse response) {
		// 文件名
		String fileName = " ";
		// 获取当天日期
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		fileName = df.format(day) + "-设备表模板" + ".xls";

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
			sheet = book.createSheet("设备表", 0);
			settings = sheet.getSettings();
			settings.setVerticalFreeze(1);
			// 3.添加第一行及第二行标题数据 (先列后行)

			sheet.addCell(new Label(0, 0, "设备名称", wf));
			sheet.setColumnView(0, 20);
			sheet.addCell(new Label(1, 0, "设备编号", wf));
			sheet.setColumnView(1, 20);
			sheet.addCell(new Label(2, 0, "型号", wf));
			sheet.setColumnView(2, 20);
			/*sheet.addCell(new Label(3, 0, "设备分级", wf));
			sheet.setColumnView(3, 15);*/
			sheet.addCell(new Label(3, 0, "位置", wf));
			sheet.setColumnView(3, 25);
			/*sheet.addCell(new Label(5, 0, "生产厂家", wf));
			sheet.setColumnView(5, 20);
			sheet.addCell(new Label(6, 0, "出厂日期", wf));
			sheet.setColumnView(6, 20);
			sheet.addCell(new Label(7, 0, "购置时间", wf));
			sheet.setColumnView(7, 20);*/
			sheet.addCell(new Label(4, 0, "启用时间", wf));
			sheet.setColumnView(4, 20);
			/*sheet.addCell(new Label(9, 0, "出厂编号", wf));
			sheet.setColumnView(9, 25);
			sheet.addCell(new Label(10, 0, "负责人", wf));
			sheet.setColumnView(10, 10);*/
			sheet.addCell(new Label(5, 0, "是否通用", wf));
			sheet.addCell(new Label(6, 0, "备注", wf));
			sheet.setColumnView(6, 15);

			// 6.写入excel并关闭
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 7.excel导入
	@RequestMapping("/importAssetExcel")
	@ResponseBody
	public String importAssetExcel(
			HttpServletRequest request,
			@RequestParam(value = "excelPath", required = false) MultipartFile excelPath)
			throws Exception {
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
				if (sheet.getName().equals("设备表") == false) {
					return "3";
				}
				Asset a = new Asset();
				// 判断excel表格中内容不为空，为空跳出循环，excel表格内容为空！
				if (sheet.getCell(0, i).getContents() != null
						&& !("".equals(sheet.getCell(0, i).getContents()))
						&& sheet.getCell(1, i).getContents() != null
						&& !("".equals(sheet.getCell(1, i).getContents()))
						) {
					a.setAssetName(sheet.getCell(0, i).getContents());
					a.setAssetNo(sheet.getCell(1, i).getContents());
					a.setAssetType(sheet.getCell(2, i).getContents());
					a.setPosition(sheet.getCell(3, i).getContents());
					a.setActiveTime(sheet.getCell(4, i).getContents());
					if("否".equals(sheet.getCell(5, i).getContents())){
						a.setIsCommon(0);
					}else{
						a.setIsCommon(1);
					}
				
					a.setRemark(sheet.getCell(6, i).getContents());
				
				} else {
					return "2";
				}
				// 检查数据库中是否存在该条记录，有则只更新，不插入。
				String asset_name = a.getAssetName();
				String asset_no = a.getAssetNo();
				List<Asset> YesorNo = assetSelectService.selectAssetByParam1(
						asset_name, asset_no);

				if (YesorNo.size() == 0) {
					// 循环将数据插入数据库
					assetSelectService.insert(a);
				} else {
					// 更新是根据主键更新，获得主键
					a.setAssetId(YesorNo.get(0).getAssetId());
					assetSelectService.updateByPrimaryKeySelective(a);
				}

			}
			book.close();
			return "1";
		} catch (Exception e) {
			return "0";

		}

	}

	/*// 1、查询操作通过Id和name
	// 模具查询分页
	// 方法路径-@RequestMapping("/togetMolds")
	@RequestMapping("/togetMolds")
	// 返回String类型
	public String togetMolds(Integer pageNow, String mold_name, String mold_no,
			Model model) throws Exception {
		// 定义一个参数listMolds作为调用service层方法 的返回值
		List<Molds> listMolds = new ArrayList<Molds>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = moldsMapper.selectMoldsByParamtotalCount(mold_name,
				mold_no);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		// 调用service的层中实现类的方法
		listMolds = moldsSelectService.selectMoldsByParam(page.getStartPos(),
				page.getPageSize(), mold_name, mold_no);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listMolds", listMolds);
		model.addAttribute("page", page);
		model.addAttribute("mold_name", mold_name);
		model.addAttribute("mold_no", mold_no);
		return "molds";
	}

	// 2、添加模具信息操作
	@RequestMapping("/insertMolds")
	// 为方法设置访问路径
	public String insertMolds(HttpServletRequest request, Molds record)
			throws Exception {
		int insert = moldsSelectService.insert(record);
		// 传递数据到前端
		request.setAttribute("insert", insert);
		return "redirect:togetMolds.action";

	}

	// 3、删除操作
	@RequestMapping("/deleteMolds")
	public String deleteMolds(Integer moldId, Model model) {
		moldsSelectService.deleteByPrimaryKey(moldId);
		return "redirect:togetMolds.action";
	}

	// 4、修改操作
	@RequestMapping("/updateMolds")
	public String updateMolds(Integer moldId, Model model) throws Exception {
		Molds molds = moldsSelectService.selectByPrimaryKey(moldId);
		model.addAttribute("molds", molds);
		return "editmolds";
	}

	@RequestMapping("/saveOrupdate1")
	public String saveOrupdate(Molds record, Model model) throws Exception {
		moldsSelectService.updateByPrimaryKeySelective(record);
		return "redirect:togetMolds.action";
	}

	// 5.生成excel模板
	@RequestMapping("/MoldsSample")
	public void MoldsSample(HttpServletResponse response) {
		// 文件名
		String fileName = " ";
		// 获取当天日期
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		fileName = df.format(day) + "-模具表模板" + ".xls";

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
			sheet = book.createSheet("模具表", 0);
			settings = sheet.getSettings();
			settings.setVerticalFreeze(1);
			// 3.添加第一行及第二行标题数据 (先列后行)

			sheet.addCell(new Label(0, 0, "客户", wf));
			sheet.setColumnView(0, 15);
			sheet.addCell(new Label(1, 0, "产品图号", wf));
			sheet.setColumnView(1, 25);
			sheet.addCell(new Label(2, 0, "模具名称", wf));
			sheet.setColumnView(2, 15);
			sheet.addCell(new Label(3, 0, "模具编号", wf));
			sheet.setColumnView(3, 30);
			sheet.addCell(new Label(4, 0, "库位号", wf));
			sheet.setColumnView(4, 20);
			sheet.addCell(new Label(5, 0, "副数", wf));
			sheet.addCell(new Label(4, 0, "备注", wf));
			sheet.setColumnView(4, 50);

			// 6.写入excel并关闭
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 7.excel导入
	@RequestMapping("/importMoldExcel")
	@ResponseBody
	public String importMoldExcel(
			HttpServletRequest request,
			@RequestParam(value = "excelPath", required = false) MultipartFile excelPath)
			throws Exception {
		// 需要添加数据判断： 数据库有待插入表中主键相同的数据，换成update,数据库中没有，使用insert
		// 需要判断excel表是否为空，为空，return 0 ，
		try {

			Workbook book = Workbook.getWorkbook(excelPath.getInputStream());// 获取流(会自动识别流中文件的实际地址，取得excel文件中的内容)
			// 获得第一个工作表对象

			Sheet sheet = book.getSheet(0);
			for (int i = 1; i < sheet.getRows(); i++) {
				Cell[] cell = sheet.getRow(i);
				if (cell.length == 0)
					continue;

				Molds m = new Molds();
				// 判断sheet的名称是不是模具表，以确定导入的是不是设模具表
				if (sheet.getName().equals("模具表") == false) {
					return "3";
				}
				// 判断excel表格中内容不为空，为空跳出循环，excel表格内容为空！！
				if (
					sheet.getCell(2, i).getContents() != null
					&& !("".equals(sheet.getCell(2, i).getContents()))
					&& sheet.getCell(3, i).getContents() != null
					&& !("".equals(sheet.getCell(3, i).getContents()))) {
					
					m.setClient(sheet.getCell(0, i).getContents());
					m.setFigureNo(sheet.getCell(1, i).getContents());
					m.setMoldName(sheet.getCell(2, i).getContents());
					m.setMoldNo(sheet.getCell(3, i).getContents());
					m.setLibraryNo(sheet.getCell(4, i).getContents());
					m.setSideNo(sheet.getCell(5, i).getContents());
					m.setRemark(sheet.getCell(4, i).getContents());
				} else {
					return "2";
				}
				// 检查数据库中是否存在该条记录，有则只更新，不插入。
				String mold_name = m.getMoldName();
				String mold_no = m.getMoldNo();
				List<Molds> YesorNo = new ArrayList<Molds>();

				YesorNo = moldsSelectService.selectMoldsByParam1(mold_name,
						mold_no);
				if (YesorNo.size() == 0) {
					moldsSelectService.insert(m);
				} else {
					// 更新是根据主键更新，获得主键
					m.setMoldId(YesorNo.get(0).getMoldId());
					moldsSelectService.updateByPrimaryKeySelective(m);
				}

			}
			// 循环将数据插入数据库

			book.close();
			return "1";
		} catch (Exception e) {
			return "0";
		}

	}*/

	// 1、实现总表的查询
	// 产品表查询分页
	@RequestMapping("/toSelectProduct")
	public String toSelectProduct(Integer pageNow, String client_material_no,
			String material_no, Model model) throws Exception {
		List<ProductsBom> listproduct = new ArrayList<ProductsBom>();

		int totalCount = 0;
		// 返回查询的行数totalCount

		totalCount = productsBomMapper.selectProductsBomByParamtotalCount(
				client_material_no, material_no);

		Page page = null;
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		listproduct = productPlanService.selectProductsBomByParam(
				page.getStartPos(), page.getPageSize(), client_material_no,
				material_no);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listproduct", listproduct);
		model.addAttribute("page", page);
		model.addAttribute("client_material_no", client_material_no);
		model.addAttribute("material_no", material_no);
		// 返回到指定的jsp页面
		return "product";

	}

	// 2、多表的添加数据，在一张总表里填写，同时添加到四张数据库表中
	@RequestMapping("/addProduct")
	public String addProduct(HttpServletRequest request, Integer num1,
			Integer num2, Integer num3, Integer num4, Model model)
			throws Exception {
		// 分别为四张表设置集合对象接受类
		List<ProductsBom> listProductsBom = new ArrayList<ProductsBom>();
		List<ShopProcess> listShopProcess = new ArrayList<ShopProcess>();
		List<ShopSort> listShopSort = new ArrayList<ShopSort>();
		List<TestProcess> listTestProcess = new ArrayList<TestProcess>();

		// 2.1添加第一张表products_Bom
		// 公共的部分
		// request.getParameter()括号内接受前端jsp页面的输入值
		String client = request.getParameter("client");
		String clientMaterialNo = request.getParameter("clientMaterialNo");
		String materialNo = request.getParameter("materialNo");
		String productName = request.getParameter("productName");
		String productSpec = request.getParameter("productSpec");
		// 不确定的部分循环输出
		for (int i = 1; i <= num1; i++) {
			String zijianNo = request.getParameter("zijianNo" + i);
			String zijianName = request.getParameter("zijianName" + i);
			String ratio1 = request.getParameter("ratio1" + i);
			float a = 0;
			float b = 0;
			float c = 0;
			if (ratio1 != "" && ratio1 != null) {
				a = Float.parseFloat(ratio1);
			}
			String ratio2 = request.getParameter("ratio2" + i);

			if (ratio2 != "" && ratio2 != null) {
				b = Float.parseFloat(ratio2);
			}
			String weight = request.getParameter("weight" + i);

			if (weight != "" && weight != null) {
				c = Float.parseFloat(weight);
			}
			String unit = request.getParameter("unit" + i);

			// 创建类接受每个内容
			ProductsBom productsBom = new ProductsBom();
			productsBom.setClient(client);
			productsBom.setClientMaterialNo(clientMaterialNo);
			productsBom.setMaterialNo(materialNo);
			productsBom.setProductName(productName);
			productsBom.setProductSpec(productSpec);
			productsBom.setZijianNo(zijianNo);
			productsBom.setZijianName(zijianName);
			productsBom.setRatio1(a);
			productsBom.setRatio2(b);
			productsBom.setWeight(c);
			productsBom.setUnit(unit);
			// 将类productsBom对象加到集合listProductsBom中
			listProductsBom.add(productsBom);
		}

		// 2.2添加第二张表shop_process

		// 不确定的部分循环输出
		for (int i = 1; i <= num2; i++) {
			// request.getParameter()括号内接受前端jsp页面的输入值
			String shop = request.getParameter("shop2" + i);
			String process = request.getParameter("process2" + i);
			String moldNo = request.getParameter("moldNo" + i);
			String price = request.getParameter("price" + i);
			String remark = request.getParameter("remark" + i);
			// 创建类接受内容
			ShopProcess shopProcess = new ShopProcess();
			shopProcess.setClientMaterialNo(clientMaterialNo);
			shopProcess.setMaterialNo(materialNo);
			shopProcess.setShop(shop);
			shopProcess.setProcess(process);
			shopProcess.setMoldNo(moldNo);
			shopProcess.setPrice(price);
			shopProcess.setRemark(remark);

			// 将类shopProcess加到集合listShopProcess中
			listShopProcess.add(shopProcess);
		}

		// 2.3添加第三张表shop_sort

		// 不确定的部分循环输出
		for (int i = 1; i <= num3; i++) {
			String shop = request.getParameter("shop" + i);

			// 创建类接受内容
			ShopSort shopSort = new ShopSort();
			shopSort.setClientMaterialNo(clientMaterialNo);
			shopSort.setMaterialNo(materialNo);
			shopSort.setShop(shop);

			// 将类shopSort加到集合listShopSort中
			listShopSort.add(shopSort);
		}

		// 2.4添加第四张表test_process

		// 不确定的部分循环输出
		for (int i = 1; i <= num4; i++) {
			String process = request.getParameter("process" + i);

			// 创建类接受内容
			TestProcess testProcess = new TestProcess();
			testProcess.setClientMaterialNo(clientMaterialNo);
			testProcess.setMaterialNo(materialNo);
			testProcess.setProcess(process);

			// 将类shopProcess加到集合listShopProcess中
			listTestProcess.add(testProcess);
		}

		productPlanService.insertFour(listProductsBom, listShopProcess,
				listShopSort, listTestProcess);

		return "redirect:toSelectProduct.action";

	}

	// 3、多表的修改数据，在一张总表里修改，同时修改四张对应数据库表

	// 用于数据的回显
	@RequestMapping("/UpdateProduct")
	public String UpdateProduct(String client_material_no, String material_no,
			Model model) throws Exception {

		// 第一张表的查询
		List<ProductsBom> listproduct = new ArrayList<ProductsBom>();
		listproduct = productPlanService.selectByParam1(client_material_no,
				material_no);
		// controller层中使model能返回一个jsp页面
		model.addAttribute("listproduct", listproduct);

		// 第二张表的查询
		List<ShopProcess> listshopprocess = new ArrayList<ShopProcess>();
		listshopprocess = productPlanService.selectByParam2(client_material_no,
				material_no);

		// 4没有的时候ShopProcess表显示为空
		ShopProcess shopProcess = new ShopProcess();
		if (listshopprocess.size() == 0) {

			shopProcess.setProcess("");
			shopProcess.setClientMaterialNo("");
			shopProcess.setMaterialNo("");
			shopProcess.setShop("");
			shopProcess.setMoldNo("");
			shopProcess.setPrice("");
			shopProcess.setRemark("");
			listshopprocess.add(shopProcess);
		}

		// controller层中使model能返回一个jsp页面
		model.addAttribute("listshopprocess", listshopprocess);

		// 第三张表的查询
		List<ShopSort> listshopsort = new ArrayList<ShopSort>();
		listshopsort = productPlanService.selectByParam3(client_material_no,
				material_no);
		// controller层中使model能返回一个jsp页面

		// 3没有的时候ShopSort表显示为空
		ShopSort shopSort = new ShopSort();
		if (listshopsort.size() == 0) {
			shopSort.setClientMaterialNo("");
			shopSort.setMaterialNo("");
			shopSort.setShop("");
			listshopsort.add(shopSort);
		}

		model.addAttribute("listshopsort", listshopsort);

		// 第四表的查询
		LinkedHashSet<String> hs = new LinkedHashSet<String>();
		hs = productPlanService.selectByParam4(client_material_no, material_no);

		// 2没有的时候Testprocess表显示为空
		if (hs.size() == 0) {
			hs.add("");
		}

		// controller层中使model能返回一个jsp页面
		model.addAttribute("hs", hs);

		return "editproduct";
	}

	@RequestMapping("/SaveOrUpdate")
	public String SaveOrUpdate(HttpServletRequest request, Integer num1,
			Integer num2, Integer num3, Integer num4, Model model)
			throws Exception {
		// 分别为四张表设置集合对象接受类
		List<ProductsBom> listProductsBom = new ArrayList<ProductsBom>();
		List<ShopProcess> listShopProcess = new ArrayList<ShopProcess>();
		List<ShopSort> listShopSort = new ArrayList<ShopSort>();
		// 3.1修改第一张表products_Bom
		// 公共的部分
		String client = request.getParameter("client");
		String clientMaterialNo = request.getParameter("clientMaterialNo");
		String materialNo = request.getParameter("materialNo");
		String productName = request.getParameter("productName");
		String productSpec = request.getParameter("productSpec");
		// 不确定的部分循环输出
		for (int i = 1; i <= num1; i++) {
			String zijianNo = request.getParameter("zijianNo" + i);
			String zijianName = request.getParameter("zijianName" + i);
			String ratio1 = request.getParameter("ratio1" + i);
			float a = 0;
			float b = 0;
			float c = 0;
			if (ratio1 != "" && ratio1 != null) {
				a = Float.parseFloat(ratio1);
			}
			String ratio2 = request.getParameter("ratio2" + i);

			if (ratio2 != "" && ratio2 != null) {
				b = Float.parseFloat(ratio2);
			}
			String weight = request.getParameter("weight" + i);

			if (weight != "" && weight != null) {
				c = Float.parseFloat(weight);
			}
			String unit = request.getParameter("unit" + i);

			// 创建类接受内容
			ProductsBom productsBom = new ProductsBom();
			productsBom.setClient(client);
			productsBom.setClientMaterialNo(clientMaterialNo);
			productsBom.setMaterialNo(materialNo);
			productsBom.setProductName(productName);
			productsBom.setProductSpec(productSpec);
			productsBom.setZijianNo(zijianNo);
			productsBom.setZijianName(zijianName);
			productsBom.setRatio1(a);
			productsBom.setRatio2(b);
			productsBom.setWeight(c);
			productsBom.setUnit(unit);
			// 将类productsBom加到集合listProductsBom中
			listProductsBom.add(productsBom);
		}

		// 3.2修改第二张表shop_product

		// 不确定的部分循环输出
		for (int i = 1; i <= num2; i++) {
			// request.getParameter()括号内接受前端jsp页面的输入值
			String shop = request.getParameter("shop2" + i);
			String process = request.getParameter("process2" + i);
			String moldNo = request.getParameter("moldNo" + i);
			String price = request.getParameter("price" + i);
			String remark = request.getParameter("remark" + i);
			// 创建类接受内容
			ShopProcess shopProcess = new ShopProcess();
			shopProcess.setClientMaterialNo(clientMaterialNo);
			shopProcess.setMaterialNo(materialNo);
			shopProcess.setShop(shop);
			shopProcess.setProcess(process);
			shopProcess.setMoldNo(moldNo);
			shopProcess.setPrice(price);
			shopProcess.setRemark(remark);
			// 将类shopProcess加到集合listShopProcess中
			listShopProcess.add(shopProcess);
		}

		// 3.3修改第三张表shop_sort

		// 不确定的部分循环输出
		for (int i = 1; i <= num3; i++) {
			String shop = request.getParameter("shop" + i);

			// 创建类接受内容
			ShopSort shopSort = new ShopSort();
			shopSort.setClientMaterialNo(clientMaterialNo);
			shopSort.setMaterialNo(materialNo);
			shopSort.setShop(shop);

			// 将类shopSort加到集合listShopSort中
			listShopSort.add(shopSort);
		}

		// 先删除3张表
		productPlanService.deleteProductsBom(clientMaterialNo, materialNo);
		productPlanService.deleteShopProcess(clientMaterialNo, materialNo);
		productPlanService.deleteShopSort(clientMaterialNo, materialNo);

		// 再添加3张表的信息
		productPlanService.insertThree(listProductsBom, listShopProcess,
				listShopSort);
		return "redirect:toSelectProduct.action";
	}

	// 4、四张表根据客户物料号和物料号删除数据
	@RequestMapping("/DeleteFour")
	public String DeleteFour(String client_material_no, String material_no,
			Model model) throws Exception {
		//productPlanService.deleteProductsBom(client_material_no, material_no);
		productPlanService.deleteShopProcess(client_material_no, material_no);
		productPlanService.deleteShopSort(client_material_no, material_no);
		productPlanService.deleteTestProcess(client_material_no, material_no);
		return "redirect:toSelectProduct.action";

	}

	// 6.生成excel模板
	@RequestMapping("/BomSample")
	public void BomSample(HttpServletResponse response) {
		// 文件名
		String fileName = " ";

		// 获取当天日期
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		fileName = df.format(day) + "-BOM清单模板" + ".xls";

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

			/*// (1)BOM清单
			WritableSheet sheet1 = null;
			SheetSettings settings1 = null;

			// 2.创建sheet并设置冻结前1行
			sheet1 = book.createSheet("BOM清单", 0);
			settings1 = sheet1.getSettings();
			settings1.setVerticalFreeze(1);
			// 3.添加第一行及第二行标题数据 (先列后行)

			sheet1.addCell(new Label(0, 0, "BOMID", wf));
			// 设置列宽
			sheet1.setColumnView(0, 10);
			sheet1.addCell(new Label(1, 0, "版本说明", wf));
			sheet1.addCell(new Label(2, 0, "母件编码", wf));
			sheet1.setColumnView(2, 20);
			sheet1.addCell(new Label(3, 0, "母件物料号", wf));
			sheet1.setColumnView(3, 20);
			sheet1.addCell(new Label(4, 0, "产品名称", wf));
			sheet1.setColumnView(4, 25);
			sheet1.addCell(new Label(5, 0, "产品规格", wf));
			sheet1.setColumnView(5, 25);
			sheet1.addCell(new Label(6, 0, "母件工程图号", wf));
			sheet1.setColumnView(6, 25);
			sheet1.addCell(new Label(7, 0, "子件编码", wf));
			sheet1.setColumnView(7, 15);
			sheet1.addCell(new Label(8, 0, "子件名称", wf));
			sheet1.setColumnView(8, 25);
			sheet1.addCell(new Label(9, 0, "基本用量", wf));
			sheet1.addCell(new Label(10, 0, "净重", wf));
			sheet1.addCell(new Label(11, 0, "单位", wf));*/

			//(2)检测工序BOM
			WritableSheet sheet2 = null;
			SheetSettings settings2 = null;

			// 2.创建sheet并设置冻结前1行
			sheet2 = book.createSheet("检测工序BOM", 0);
			settings2 = sheet2.getSettings();
			settings2.setVerticalFreeze(1);
			// 3.添加第一行及第二行标题数据 (先列后行)

			sheet2.addCell(new Label(0, 0, "客户名称", wf));
			// 设置列宽
			sheet2.setColumnView(0, 15);
			sheet2.addCell(new Label(1, 0, "母件物料号", wf));
			sheet2.setColumnView(1, 25);
			sheet2.addCell(new Label(2, 0, "母件编码", wf));
			sheet2.setColumnView(2, 25);
			sheet2.addCell(new Label(3, 0, "产品检测工序", wf));
			sheet2.setColumnView(3, 30);
			sheet2.addCell(new Label(4, 0, "产品名称", wf));
			sheet2.setColumnView(4, 20);

			// (3)车间顺序BOM
			WritableSheet sheet3 = null;
			SheetSettings settings3 = null;

			// 2.创建sheet并设置冻结前1行
			sheet3 = book.createSheet("车间顺序BOM", 1);
			settings3 = sheet3.getSettings();
			settings3.setVerticalFreeze(1);
			// 3.添加第一行及第二行标题数据 (先列后行)

			sheet3.addCell(new Label(0, 0, "母件物料号", wf));
			// 设置列宽
			sheet3.setColumnView(0, 25);
			sheet3.addCell(new Label(1, 0, "母件编码", wf));
			sheet3.setColumnView(1, 25);
			sheet3.addCell(new Label(2, 0, "工段名称", wf));
			sheet3.setColumnView(2, 20);
			sheet3.addCell(new Label(3, 0, "产品名称", wf));
			sheet3.setColumnView(3, 25);

			// (4)车间顺序BOM
			WritableSheet sheet4 = null;
			SheetSettings settings4 = null;

			// 2.创建sheet并设置冻结前1行
			sheet4 = book.createSheet("车间生成工序BOM", 2);
			settings4 = sheet4.getSettings();
			settings4.setVerticalFreeze(1);
			// 3.添加第一行及第二行标题数据 (先列后行)

			sheet4.addCell(new Label(0, 0, "母件物料号", wf));
			// 设置列宽
			sheet4.setColumnView(0, 25);
			sheet4.addCell(new Label(1, 0, "母件编码", wf));
			sheet4.setColumnView(1, 25);
			sheet4.addCell(new Label(2, 0, "生产车间", wf));
			sheet4.setColumnView(2, 20);
			sheet4.addCell(new Label(3, 0, "车间生产工序顺序", wf));
			sheet4.setColumnView(3, 25);
			sheet4.addCell(new Label(4, 0, "模具编码", wf));
			sheet4.setColumnView(4, 25);
			sheet4.addCell(new Label(5, 0, "备注", wf));
			sheet4.setColumnView(5, 25);
			sheet4.addCell(new Label(6, 0, "工价", wf));
			sheet4.setColumnView(6, 15);
			sheet4.addCell(new Label(7, 0, "产品名称", wf));
			sheet4.setColumnView(7, 25);

			// 6.写入excel并关闭
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 7.excel导入
	@RequestMapping("/importProductExcel")
	@ResponseBody
	public String importProductExcel(
			HttpServletRequest request,
			@RequestParam(value = "excelPath", required = false) MultipartFile excelPath)
			throws Exception {
		// 需要添加数据判断： 数据库有待插入表中主键相同的数据，换成update,数据库中没有，使用insert
		// 需要判断excel表是否为空，为空，return 0 ，
		try {
			Workbook book = Workbook.getWorkbook(excelPath.getInputStream());// 获取流(会自动识别流中文件的实际地址，取得excel文件中的内容)

			/*// 1、循环获得ProductsBom(Bom清单表)表中的excel值
			Sheet sheet1 = book.getSheet(0);
			for (int i = 1; i < sheet1.getRows(); i++) {
				Cell[] cell = sheet1.getRow(i);
				if (cell.length == 0)
					continue;
				// 判断sheet1的名称是不是BOM清单，以确定导入的是不是BOM清单表
				if (sheet1.getName().equals("BOM清单") == false) {
					return "3";
				}

				ProductsBom p = new ProductsBom();
				// 判断excel表格sheet1中内容不为空，为空跳出循环，excel表格内容为空！！
				if (sheet1.getCell(2, i).getContents() != null
						&& !("".equals(sheet1.getCell(2, i).getContents()))
						&& sheet1.getCell(3, i).getContents() != null
						&& !("".equals(sheet1.getCell(3, i).getContents()))
						&& sheet1.getCell(4, i).getContents() != null
						&& !("".equals(sheet1.getCell(4, i).getContents()))
						&& sheet1.getCell(5, i).getContents() != null
						&& !("".equals(sheet1.getCell(5, i).getContents()))
						&& sheet1.getCell(6, i).getContents() != null
						&& !("".equals(sheet1.getCell(6, i).getContents()))
						&& sheet1.getCell(7, i).getContents() != null
						&& !("".equals(sheet1.getCell(7, i).getContents()))
						&& sheet1.getCell(8, i).getContents() != null
						&& !("".equals(sheet1.getCell(8, i).getContents()))
						&& sheet1.getCell(9, i).getContents() != null
						&& !("".equals(sheet1.getCell(9, i).getContents()))
						&& sheet1.getCell(10, i).getContents() != null
						&& !("".equals(sheet1.getCell(10, i).getContents()))
						&& sheet1.getCell(11, i).getContents() != null
						&& !("".equals(sheet1.getCell(11, i).getContents()))) {

					p.setMaterialNo(sheet1.getCell(2, i).getContents());
					p.setClientMaterialNo(sheet1.getCell(3, i).getContents());
					p.setProductName(sheet1.getCell(4, i).getContents());
					p.setProductSpec(sheet1.getCell(5, i).getContents());
					p.setZijianNo(sheet1.getCell(7, i).getContents());
					p.setZijianName(sheet1.getCell(8, i).getContents());
					p.setRatio1(Float.parseFloat(sheet1.getCell(9, i)
							.getContents()));
					p.setRatio2(Float.parseFloat(sheet1.getCell(10, i)
							.getContents()));
					p.setWeight(Float.parseFloat(sheet1.getCell(10, i)
							.getContents()));
					p.setUnit(sheet1.getCell(11, i).getContents());
				} else {
					return "2";
				}
			
				// 检查数据库中是否存在该条记录，有则只更新，不插入。
				String client_material_no = p.getClientMaterialNo();
				String material_no = p.getMaterialNo();
				String zijian_no = p.getZijianNo();
				List<ProductsBom> YesorNo = new ArrayList<ProductsBom>();

				// 检查是否该ProductsBom表的子件存在
				YesorNo = productPlanService.selectexcelByParam(
						client_material_no, material_no, zijian_no);
				if (YesorNo.size() == 0) {
					productPlanService.insertProductsBom(p);
				} else {
					// 更新是根据主键更新，获得主键
					p.setProductsId(YesorNo.get(0).getProductsId());
					productPlanService.updateProductsBom(p);
				}
			}
*/
			
			// 2、循环获得TestProcess表(检测工序表)中的excel值
			List<TestProcess> listTestProcess = new ArrayList<TestProcess>();

			Sheet sheet2 = book.getSheet(0);
			for (int i = 1; i < sheet2.getRows(); i++) {
				Cell[] cell = sheet2.getRow(i);
				if (cell.length == 0)
					continue;
				// 判断sheet2的名称是不是检测工序BOM，以确定导入的是不是BOM清单表
				if (sheet2.getName().equals("检测工序BOM") == false) {
					return "3";
				}

				TestProcess t = new TestProcess();

				// 判断excel表格sheet2中内容不为空，为空跳出循环，excel表格内容为空！！
				if (sheet2.getCell(1, i).getContents() != null
						&& !("".equals(sheet2.getCell(1, i).getContents()))
						&& sheet2.getCell(2, i).getContents() != null
						&& !("".equals(sheet2.getCell(2, i).getContents()))
						&& sheet2.getCell(3, i).getContents() != null
						&& !("".equals(sheet2.getCell(3, i).getContents()))) {
					t.setClientMaterialNo(sheet2.getCell(1, i).getContents());
					t.setMaterialNo(sheet2.getCell(2, i).getContents());
					t.setProcess(sheet2.getCell(3, i).getContents());
				} else {
					return "2";
				}

				listTestProcess.add(t);
			}
			// 先删除该图号，物料号下的所有TestProcess表中内容再一起插入。
			for (int i = 0; i < listTestProcess.size(); i++) {
				String client_material_no = listTestProcess.get(i)
						.getClientMaterialNo();
				String material_no = listTestProcess.get(i).getMaterialNo();

				productPlanService.deleteTestProcess(client_material_no,
						material_no);
			}
			productPlanService.insertTestProcess(listTestProcess);

			// 3、循环获得 ShopSort表(车间顺序表)中的excel值
			List<ShopSort> listShopSort = new ArrayList<ShopSort>();

			Sheet sheet3 = book.getSheet(1);
			for (int i = 1; i < sheet3.getRows(); i++) {
				Cell[] cell = sheet3.getRow(i);
				if (cell.length == 0)
					continue;
				// 判断sheet3的名称是不是车间顺序BOM，以确定导入的是不是BOM清单表
				if (sheet3.getName().equals("车间顺序BOM") == false) {
					return "3";
				}

				ShopSort s = new ShopSort();

				// 判断excel表格sheet3中内容不为空，为空跳出循环，excel表格内容为空！！
				if (sheet3.getCell(0, i).getContents() != null
						&& !("".equals(sheet3.getCell(0, i).getContents()))
						&& sheet3.getCell(1, i).getContents() != null
						&& !("".equals(sheet3.getCell(1, i).getContents()))
						&& sheet3.getCell(2, i).getContents() != null
						&& !("".equals(sheet3.getCell(2, i).getContents()))) {
					s.setClientMaterialNo(sheet3.getCell(0, i).getContents());
					s.setMaterialNo(sheet3.getCell(1, i).getContents());
					s.setShop(sheet3.getCell(2, i).getContents());
				} else {
					return "2";
				}
				listShopSort.add(s);
			}

			// 先删除该图号，物料号下的所有shopsort表中内容再一起插入。
			for (int i = 0; i < listShopSort.size(); i++) {
				String client_material_no = listShopSort.get(i)
						.getClientMaterialNo();
				String material_no = listShopSort.get(i).getMaterialNo();

				productPlanService.deleteShopSort(client_material_no,
						material_no);
			}
			productPlanService.insertShopSort(listShopSort);

			// 4、循环获得ShopProcess表(车间生产工序BOM表)中的excel值
			List<ShopProcess> listShopProcess = new ArrayList<ShopProcess>();

			Sheet sheet4 = book.getSheet(2);
			for (int i = 1; i < sheet4.getRows(); i++) {
				Cell[] cell = sheet4.getRow(i);
				if (cell.length == 0)
					continue;
				// 判断sheet4的名称是不是车间生成工序BOM，以确定导入的是不是BOM清单表
				if (sheet4.getName().equals("车间生成工序BOM") == false) {
					return "3";
				}

				ShopProcess ss = new ShopProcess();
				// 判断excel表格sheet4中内容不为空，为空跳出循环，excel表格内容为空！！
				if (sheet4.getCell(0, i).getContents() != null
						&& !("".equals(sheet4.getCell(0, i).getContents()))
						&& sheet4.getCell(1, i).getContents() != null
						&& !("".equals(sheet4.getCell(1, i).getContents()))
						&& sheet4.getCell(2, i).getContents() != null
						&& !("".equals(sheet4.getCell(2, i).getContents()))
						&& sheet4.getCell(3, i).getContents() != null
						&& !("".equals(sheet4.getCell(3, i).getContents()))
						/*&& sheet4.getCell(6, i).getContents() != null
						&& !("".equals(sheet4.getCell(6, i).getContents()))*/) {
					ss.setClientMaterialNo(sheet4.getCell(0, i).getContents());
					ss.setMaterialNo(sheet4.getCell(1, i).getContents());
					ss.setShop(sheet4.getCell(2, i).getContents());
					ss.setProcess(sheet4.getCell(3, i).getContents());
					ss.setMoldNo(sheet4.getCell(4, i).getContents());
					ss.setRemark(sheet4.getCell(5, i).getContents());
					ss.setPrice(sheet4.getCell(6, i).getContents());
				} else {
					return "2";
				}
				listShopProcess.add(ss);
			}
			// 先删除该图号，物料号下的所有ShopProcess表中内容再一起插入。
			for (int i = 0; i < listShopProcess.size(); i++) {
				String client_material_no = listShopProcess.get(i)
						.getClientMaterialNo();
				String material_no = listShopProcess.get(i).getMaterialNo();

				productPlanService.deleteShopProcess(client_material_no,
						material_no);
			}
			productPlanService.insertShopProcess(listShopProcess);

			book.close();
			return "1";
		} catch (Exception e) {
			return "0";
		}

	}

	// 人员权限管理页面
	@RequestMapping("/LimitManage")
	public String LimitManage(Integer pageNow, String number,
			String person_name, String department, Model model)
			throws Exception {
		List<Person> listPerson = new ArrayList<Person>();
		int totalCount = 0;
		// 返回查询的行数totalCount
		Page page = null;
		totalCount = personMapper.selectLimitManagetotalCount(number,
				person_name, department);
		if (pageNow != null) {
			page = new Page(totalCount, pageNow);
		} else {
			page = new Page(totalCount, 1);
		}

		// 调用service的层中实现类的方法
		listPerson = personService.selectLimitManage(page.getStartPos(),
				page.getPageSize(), number, person_name, department);
		// controller层中使model能返回一个jsp页面，在jsp页面便于用EL表达式${}获取值，使model.addAttribute返回jsp页面输出
		model.addAttribute("listPerson", listPerson);
		model.addAttribute("page", page);
		model.addAttribute("number", number);
		model.addAttribute("person_name", person_name);
		model.addAttribute("department", department);

		return "person";

	}

	// 修改权限管理页面
	// 1)数据回显
	@RequestMapping("/editLimitManage")
	public String editLimitManage(Integer personId, Model model)
			throws Exception {
		// 回显第一张表的内容
		Person person = personService.selectPerson(personId);
		model.addAttribute("person", person);

		// 查询url表对应中的全部type
		List<String> listType = personService.selectType();
		model.addAttribute("listType", listType);

		// 查询Permission表personId对应中的type并用字符串拼接传回，防止前端多线程
		String permission = personService.selectPermission(personId);
		model.addAttribute("permission", permission);
		return "editperson";

	}

	// 2)更新人员权限操作
	@RequestMapping("/UpdateLimitManage")
	public String UpdateLimitManage(Person person, HttpServletRequest request,
			Integer personId) throws Exception {

		// 1.更新person表
		personService.updateByPrimaryKeySelective(person);

		// 2.删除person_id对应的permisssion表
		personService.deleteBypersonId(personId);

		// 3.再插入person_id对应的permisssion表（将type和url插入）
		// 获得前端的type放到数组中getParameterValues获取多值（一般用于checkbox，复选框）
		String types[] = request.getParameterValues("type");

		if (types != null) {
			for (int j = 0; j < types.length; j++) {

				Permission permission = new Permission();
				// 根据type查询url表中的url
				List<Url> listUrl = new ArrayList<Url>();
				listUrl = personService.selectUrl(types[j]);
				for (int i = 0; i < listUrl.size(); i++) {
					permission.setPersonId(personId);
					permission.setType(listUrl.get(i).getType());
					permission.setUrl(listUrl.get(i).getUrl());
					personService.insertSelective(permission);
				}

			}
		}
		return "redirect:LimitManage.action";
	}

	// 3)删除人员权限操作
	@RequestMapping("/DeleteLimitManage")
	public String DeleteLimitManage(Integer personId) {
		// 注意必须先删除子表再删除父表
		// 删除-person_id对应的permisssion表
		personService.deleteBypersonId(personId);
		// 删除-person_id对应的person表（删除时使用）
		personService.deleteByPrimaryKey(personId);

		return "redirect:LimitManage.action";
	}

	// 4)人员权限生成excel模板
	@RequestMapping("/PersonSample")
	public void PersonSample(HttpServletResponse response) {
		// 文件名
		String fileName = " ";
		// 获取当天日期
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		fileName = df.format(day) + "-人员清单模板" + ".xls";

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
			sheet = book.createSheet("人员信息", 0);
			settings = sheet.getSettings();
			settings.setVerticalFreeze(1);
			// 3.添加第一行及第二行标题数据 (先列后行)

			sheet.addCell(new Label(0, 0, "员工编号", wf));
			sheet.setColumnView(0, 15);
			sheet.addCell(new Label(1, 0, "员工姓名", wf));
			sheet.setColumnView(1, 15);
			sheet.addCell(new Label(2, 0, "性别", wf));
			sheet.addCell(new Label(3, 0, "职位", wf));
			sheet.setColumnView(3, 15);
			sheet.addCell(new Label(4, 0, "员工所在部门", wf));
			sheet.setColumnView(4, 15);
			sheet.addCell(new Label(5, 0, "密码", wf));
			sheet.setColumnView(5, 15);
			sheet.addCell(new Label(6, 0, "email", wf));
			sheet.setColumnView(6, 20);

			// 6.写入excel并关闭
			book.write();
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 5)人员权限的excel导入
	@RequestMapping("/importPersonExcel")
	@ResponseBody
	public String importPersonExcel(
			HttpServletRequest request,
			@RequestParam(value = "excelPath", required = false) MultipartFile excelPath)
			throws Exception {
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
				if (sheet.getName().equals("人员信息") == false) {
					return "3";
				}
				Person person = new Person();
				// 判断excel表格中内容不为空，为空跳出循环，excel表格内容为空！
				if (sheet.getCell(0, i).getContents() != null
						&& !("".equals(sheet.getCell(0, i).getContents()))
						&& sheet.getCell(1, i).getContents() != null
						&& !("".equals(sheet.getCell(1, i).getContents()))
						&& sheet.getCell(2, i).getContents() != null
						&& !("".equals(sheet.getCell(2, i).getContents()))
						&& sheet.getCell(3, i).getContents() != null
						&& !("".equals(sheet.getCell(3, i).getContents()))
						&& sheet.getCell(4, i).getContents() != null
						&& !("".equals(sheet.getCell(4, i).getContents()))) {

					person.setNumber(sheet.getCell(0, i).getContents());
					person.setPersonName(sheet.getCell(1, i).getContents());
					person.setSex(sheet.getCell(2, i).getContents());
					person.setPosition(sheet.getCell(3, i).getContents());
					person.setDepartment(sheet.getCell(4, i).getContents());
					// 密码和email不为空情况下，直接读取excel中表格内容
					if (sheet.getCell(5, i).getContents() != null
							&& !("".equals(sheet.getCell(5, i).getContents()))) {
						// 对密码进行MD5加密！！
						String password = new MD5().getPassword(sheet.getCell(
								5, i).getContents());
						person.setPassword(password);

					} else {
						// 对密码进行MD5加密！--未填时默认密码为：123456
						String password = new MD5().getPassword("123456");
						person.setPassword(password);
					}
					if (sheet.getCell(6, i).getContents() != null
							&& !("".equals(sheet.getCell(6, i).getContents()))) {
						person.setEmail(sheet.getCell(6, i).getContents());
					} else {
						// 未填时默认邮箱为：1234@qq.com
						person.setEmail("1234@qq.com");
					}

				} else {
					return "2";
				}
				// 检查数据库中是否存在该条记录，有则只更新，不插入。
				String number = person.getNumber();
				List<Person> YesorNo = personService
						.selectPersonByNumber(number);

				if (YesorNo.size() == 0) {
					// 循环将数据插入数据库
					personService.insertByNumber(person);
				} else {
					// 更新是根据主键更新，获得主键
					person.setPersonId(YesorNo.get(0).getPersonId());
					personService.updateByNumber(person);
				}

			}
			book.close();
			return "1";
		} catch (Exception e) {
			return "0";

		}

	}
	
	
	
	
	
			
			// 1、半成品库存
			//1)半成品库存查询分页
			@RequestMapping("/toMiddleInventory")
			// 返回String类型
			public String toMiddleInventory(Integer pageNow, String materialNo,
					String productName, Model model) throws Exception {
				// 定义一个参数listAsset作为调用service层方法 的返回值
				List<MiddleProduct> listMiddleProduct = new ArrayList<MiddleProduct>();

				int totalCount = 0;
				// 返回查询的行数totalCount

				totalCount = middleProductMapper.selectByMiddletotalCount(materialNo, productName);

				Page page = null;
				if (pageNow != null) {
					page = new Page(totalCount, pageNow);
				} else {
					page = new Page(totalCount, 1);
				}

				// 调用service的层中实现类的方法
				listMiddleProduct =middleInventoryService.selectByMiddleProductint(page.getStartPos(),page.getPageSize(), materialNo, productName);
				// controller层中使model能返回一个jsp页面，在jsp页面便于用EL表达式${}获取值，使model.addAttribute返回jsp页面输出
				model.addAttribute("listMiddleProduct", listMiddleProduct);
				// 返回page类（里面有总页数，当前页等信息）
				model.addAttribute("page", page);
				model.addAttribute("materialNo", materialNo);
				model.addAttribute("productName", productName);
				return "middle_product_inventory";
			}
	
			
			
			// 2)添加半成品库存信息
			@RequestMapping("/insertMiddleInventory")
			// 为方法设置访问路径
			public String insertMiddleInventory(MiddleProduct record,HttpServletRequest request,Integer quantiy, Model model) throws Exception {
				
				for (int i = 1; i <= quantiy; i++) {
				// request.getParameter()括号内接受前端jsp页面的输入值
				String materialNo = request.getParameter("materialNo" + i);
				String productName = request.getParameter("productName" + i);
				String type = request.getParameter("type" + i);
				String num = request.getParameter("num" + i);
				String unit = request.getParameter("unit" + i);
				// 创建类接受内容
				MiddleProduct middleProduct = new MiddleProduct();
				middleProduct.setMaterialNo(materialNo);
				middleProduct.setProductName(productName);
				middleProduct.setType(type);
				middleProduct.setNum(Integer.valueOf(num));
				middleProduct.setUnit(unit);
				
				middleInventoryService.insertSelective(middleProduct);
			}
				return "redirect:toMiddleInventory.action";

			}
			
			
			// 3)、删除操作
						@RequestMapping("/deleteMiddleInventory")
						public String deleteMiddleInventory(Integer id, Model model) {
							
							//修改半成品库存详细记录
						    middleProductDetailMapper.deleteDetailById(id);
							
							middleInventoryService.deleteByPrimaryKey(id);
							
							return "redirect:toMiddleInventory.action";
						}


			
						// 4)、修改操作
						// 先查询出来，用于数据的回显
						@RequestMapping("/updateMiddleInventory")
						public String updateMiddleInventory(Integer id, Model model) throws Exception {
							MiddleProduct middleProduct=middleInventoryService.selectByPrimaryMiddleProduct(id);
							model.addAttribute("middleProduct", middleProduct);
						
							//修改半成品库存详细记录
							List<MiddleProductDetail> middleProductDetail = middleProductDetailService.selectDetailById(id);
							model.addAttribute("middleProductDetail", middleProductDetail);
							model.addAttribute("size", middleProductDetail.size());				
							return "editmiddle_inventory";
						}
						
						// 再更新半成品库存信息
						@SuppressWarnings("deprecation")
						@RequestMapping("/saveOrupdateMiddle")
						public String saveOrupdateMiddle(HttpServletRequest request,MiddleProduct middleProduct, Model model) throws Exception {
							
							//修改半成品库存详细记录
							MiddleProductDetail middleProductDetail=new MiddleProductDetail();
							
							MiddleProduct middleProduct1=middleInventoryService.selectByPrimaryMiddleProduct(middleProduct.getId());
							middleProductDetail.setId(middleProduct.getId());
							middleProductDetail.setOldNum(middleProduct1.getNum());
							middleProductDetail.setNewNum(middleProduct.getNum());
							middleProductDetail.setEditTime(new Date().toLocaleString());	
							Person person = (Person) request.getSession().getAttribute("user");
							middleProductDetail.setEditPerson(person.getPersonName());
							middleInventoryService.updateByPrimaryKeySelective(middleProduct);
						if(middleProduct.getNum().equals(middleProduct1.getNum())){
							
							middleProductDetailMapper.updateByPrimaryKeySelective(middleProductDetail);
						}else {
							
							middleProductDetailMapper.insertSelective(middleProductDetail);
						}
							return "redirect:toMiddleInventory.action";
						}

			
		    // 5)半成品库存表生成excel模板
			@RequestMapping("/MiddleroductionSample")
			public void MiddleroductionSample(HttpServletResponse response) {
				// 文件名
				String fileName = " ";
				// 获取当天日期
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				fileName = df.format(day) + "-半成品库存表模板" + ".xls";
	
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
					sheet = book.createSheet("半成品库存信息", 0);
					settings = sheet.getSettings();
					settings.setVerticalFreeze(1);
					// 3.添加第一行及第二行标题数据 (先列后行)
	
					sheet.addCell(new Label(0, 0, "物料号", wf));
					sheet.setColumnView(0, 25);
					sheet.addCell(new Label(1, 0, "产品名称", wf));
					sheet.setColumnView(1, 25);
					sheet.addCell(new Label(2, 0, "规格型号", wf));
					sheet.setColumnView(2, 25);
					sheet.addCell(new Label(3, 0, "库存数量", wf));
					sheet.setColumnView(3, 15);
					sheet.addCell(new Label(4, 0, "单位", wf));
					
					// 6.写入excel并关闭
					book.write();
					book.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	
			}

			
			 // 6)導入半成品库存表excel
			@RequestMapping("/importMiddleInventoryExcel")
			@ResponseBody
			public String importMiddleInventoryExcel(
					HttpServletRequest request,
					@RequestParam(value = "excelPath", required = false) MultipartFile excelPath)
					throws Exception {
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
						if (sheet.getName().equals("半成品库存信息") == false) {
							return "3";
						}
						MiddleProduct m=new MiddleProduct();
						// 判断excel表格中内容不为空，为空跳出循环，excel表格内容为空！
						if (sheet.getCell(0, i).getContents() != null
								&& !("".equals(sheet.getCell(0, i).getContents()))
								&& sheet.getCell(1, i).getContents() != null
								&& !("".equals(sheet.getCell(1, i).getContents()))
								) {
							m.setMaterialNo(sheet.getCell(0, i).getContents());
							m.setProductName(sheet.getCell(1, i).getContents());
							m.setType(sheet.getCell(2, i).getContents());
							if("".equals(sheet.getCell(3, i).getContents())||sheet.getCell(3, i).getContents()==null){
								m.setNum(0);
							}else{
								m.setNum(Integer.valueOf(sheet.getCell(3, i).getContents()));
							}
						
							m.setUnit(sheet.getCell(4, i).getContents());
						
						} else {
							return "2";
						}
						// 检查数据库中是否存在该条记录，有则只更新，不插入。
						String materialNo = m.getMaterialNo();
						List<MiddleProduct> YesorNo = middleInventoryService.selectByPrimaryMiddleProduct(materialNo);

						if (YesorNo.size() == 0) {
							// 循环将数据插入数据库
							middleInventoryService.insertSelective(m);
						} else {
							// 更新是根据主键更新，获得主键
							m.setId(YesorNo.get(0).getId());
							middleInventoryService.updateByPrimaryKeySelective(m);
						}

					}
					book.close();
					return "1";
				} catch (Exception e) {
					return "0";

				}

			}
			
			
			//1).设备点检内容管理
			@RequestMapping("/toAssetCheckRecord")
			// 返回String类型
			public String toAssetCheckRecord(Integer pageNow, String assetName, Model model) throws Exception {
				// 定义一个参数listAsset作为调用service层方法 的返回值
				List<CheckContent> listCheckRecord = new ArrayList<CheckContent>();

				int totalCount = 0;
				// 返回查询的行数totalCount

				totalCount = checkContentMapper.selectAssetCheckRecordtotalCount(assetName);
				Page page = null;
				if (pageNow != null) {
					page = new Page(totalCount, pageNow);
				} else {
					page = new Page(totalCount, 1);
				}

				// 调用service的层中实现类的方法
				listCheckRecord = dailyCheckService.selectAssetCheckRecord(page.getStartPos(), page.getPageSize(), assetName);
				// controller层中使model能返回一个jsp页面，在jsp页面便于用EL表达式${}获取值，使model.addAttribute返回jsp页面输出
				model.addAttribute("listCheckRecord", listCheckRecord);
				// 返回page类（里面有总页数，当前页等信息）
				model.addAttribute("page", page);
				model.addAttribute("assetName", assetName);
				
				return "asset_check_record";
			}
			
			
			// 2、添加设备点检内容表信息操作
			@RequestMapping("/insertAssetCheckRecord")
			// 为方法设置访问路径
			public String insertAssetCheckRecord(CheckContent record,HttpServletRequest request,Integer num, Model model) throws Exception {
				
				for (int i = 1; i <= num; i++) {
				// request.getParameter()括号内接受前端jsp页面的输入值
				String assetName = request.getParameter("assetName" + i);
				String result = request.getParameter("result" + i);
				String assetNo=null;
				// 创建类接受内容
				CheckContent checkContent = new CheckContent();
				checkContent.setAssetName(assetName);
				checkContent.setResult(result);
				checkContent.setAssetNo(assetNo);
				
				dailyCheckService.insertSelective(checkContent);
			}
				return "redirect:toAssetCheckRecord.action";

			}
			
		
			
			// 3、修改设备点检内容操作
			// 先查询出来，用于数据的回显
			@RequestMapping("/updateAssetCheckRecord")
			public String updateAssetCheckRecord(String assetName, Model model) throws Exception {
				List<CheckContent> listAssetCheckContent = dailyCheckService.selectAssetCheckRecordByParm(assetName);
				model.addAttribute("listAssetCheckContent", listAssetCheckContent);
				return "editasset_check_record";
			}

			@RequestMapping("/saveOrupdateAssetCheckRecord")
			public String saveOrupdateAssetCheckRecord(HttpServletRequest request,Integer num, Model model) throws Exception {
				
				List<CheckContent> listAssetCheckContent=new ArrayList<CheckContent>();
				for (int i = 1; i <= num; i++) {
					// request.getParameter()括号内接受前端jsp页面的输入值
					String assetName = request.getParameter("assetName" + i);
					String result = request.getParameter("result" + i);
					String assetNo=null;
					// 创建类接受内容
					CheckContent checkContent = new CheckContent();
					checkContent.setAssetName(assetName);
					checkContent.setResult(result);
					checkContent.setAssetNo(assetNo);
					listAssetCheckContent.add(checkContent);
				}	
				String assetName = request.getParameter("assetName1");
				//先删除再插入
				dailyCheckService.deleteByPrimaryKey(assetName);
				dailyCheckService.insertAssetCheckRecord(listAssetCheckContent);
				return "redirect:toAssetCheckRecord.action";
			}
			
			
			 // 4)设备点检内容表生成excel模板
			@RequestMapping("/AssetCheckRecordSample")
			public void AssetCheckRecordSample(HttpServletResponse response) {
				// 文件名
				String fileName = " ";
				// 获取当天日期
				Date day = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				fileName = df.format(day) + "-设备点检内容表模板" + ".xls";
	
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
					sheet = book.createSheet("设备点检内容信息", 0);
					settings = sheet.getSettings();
					settings.setVerticalFreeze(1);
					// 3.添加第一行及第二行标题数据 (先列后行)
					sheet.addCell(new Label(0, 0, "序号", wf));
					sheet.addCell(new Label(1, 0, "设备名称", wf));
					sheet.setColumnView(1, 20);
					sheet.addCell(new Label(2, 0, "设备点检内容", wf));
					sheet.setColumnView(2, 80);
					
					// 6.写入excel并关闭
					book.write();
					book.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	
			}
			
			 // 6)设备点检内容表excel
			@RequestMapping("/importAssetCheckRecordExcel")
			@ResponseBody
			public String importAssetCheckRecordExcel(HttpServletRequest request,
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
						if (sheet.getName().equals("设备点检内容信息") == false) {
							return "3";
						}
						CheckContent c=new CheckContent();
						// 判断excel表格中内容不为空，为空跳出循环，excel表格内容为空！
						if (sheet.getCell(1, i).getContents() != null
								&& !("".equals(sheet.getCell(1, i).getContents()))) {
							c.setAssetName(sheet.getCell(1, i).getContents());
							if("".equals(sheet.getCell(2, i).getContents())||sheet.getCell(2, i).getContents()==null){
								c.setResult("该设备未填写点检内容！");
							}else{
								c.setResult(sheet.getCell(2, i).getContents());
							}
						
						} else {
							return "2";
						}
						// 检查数据库中是否存在该条记录，有则只更新，不插入。
						String assetName = c.getAssetName();
						String result = c.getResult();
						List<CheckContent> YesorNo = dailyCheckService.selectAssetCheckRecordExcel(assetName, result);
						if (YesorNo.size() == 0) {
							// 循环将数据插入数据库
							dailyCheckService.insertSelective(c);
						} else {
							// 更新是根据主键更新，获得主键
							c.setId(YesorNo.get(0).getId());
							dailyCheckService.updateByPrimaryKeySelective(c);
						}

					}
					book.close();
					return "1";
				} catch (Exception e) {
					return "0";

				}

			}
			
			
			
			
}