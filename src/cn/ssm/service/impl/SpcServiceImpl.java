package cn.ssm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.ssm.mapper.ProductTestMapper;
import cn.ssm.mapper.SpcMapper;
import cn.ssm.mapper.SpcTestMapper;
import cn.ssm.mapper.TestProcessMapper;
import cn.ssm.po.ProductTest;
import cn.ssm.po.Spc;
import cn.ssm.po.SpcTest;
import cn.ssm.service.SpcService;
import cn.ssm.util.DataSource;

@Service
@DataSource("dataSource1")
public class SpcServiceImpl implements SpcService {

	@Autowired
	private ProductTestMapper productTestMapper;
	@Autowired
	private TestProcessMapper testProcessMapper;
	@Autowired
	private SpcMapper spcMapper;
	@Autowired
	private SpcTestMapper spcTestMapper;

	// 1.Spc曲线查询页面
	@Override
	public Spc selectSpc(String clientMaterialNo, String materialNo,
			String batchNo, String process, String characterVal) {
		// 1)获取Spc曲线的图号，物料号，批次号，产品名称，工序

		// 自己拼接的特征值和检测工序
		String processName = process + "/" + characterVal;

		ProductTest productTest = new ProductTest();
		productTest = productTestMapper.selectSpc(clientMaterialNo, materialNo,
				batchNo, processName);
		Spc spc = new Spc();
		if (productTest != null) {
			spc.setClientMaterialNo(productTest.getClientMaterialNo());
			spc.setMaterialNo(productTest.getMaterialNo());
			spc.setBatchNo(productTest.getBatchNo());
			spc.setProductName(productTest.getProductName());
			spc.setProcessName(process);
			spc.setCharacterVal(characterVal);
		} else {
			spc.setClientMaterialNo("");
			spc.setMaterialNo("");
			spc.setBatchNo("");
			spc.setProductName("");
			spc.setProcessName("");
			spc.setCharacterVal("");
		}
		// 2)获取采集时间的开始日期与截止日期
		String CheckStartDate = productTestMapper.selectStartCheckDate(
				clientMaterialNo, materialNo, batchNo, processName);
		String CheckEndDate = productTestMapper.selectEndCheckDate(
				clientMaterialNo, materialNo, batchNo, processName);

		// 如果为空设置初始采集时间，结束采集时间为空
		if (CheckStartDate != null && !("".equals(CheckStartDate))
				&& CheckEndDate != null && !("".equals(CheckEndDate))) {
			spc.setMakeStartDate(CheckStartDate);
			spc.setMakeEndDate(CheckEndDate);
		} else {
			spc.setMakeStartDate("");
			spc.setMakeEndDate("");
		}

		// 3)获取Spc的标准值中的中值、上公差限、、下公差限
		String Standard = productTestMapper.selectStandard(clientMaterialNo,
				materialNo, batchNo, processName);
		// Float.parseFloat()将字符串型转换成浮点型
		// 如果为空将中值，上公差限，下公差限都设置为null
		if (Standard != null) {
			float a = 0;
			float b = 0;
			float c = 0;
			if (Standard.substring(0, Standard.indexOf("+")) != ""
					&& Standard.substring(0, Standard.indexOf("+")) != null) {
				a = Float.parseFloat(Standard.substring(0,
						Standard.indexOf("+")));
			}
			if (Standard.substring(0, Standard.indexOf("+")) != ""
					&& Standard.substring(0, Standard.indexOf("+")) != null
					&& Standard.substring(Standard.indexOf("+") + 1,
							Standard.indexOf("/")) != ""
					&& Standard.substring(Standard.indexOf("+") + 1,
							Standard.indexOf("/")) != null) {
				String s1 = Standard.substring(0, Standard.indexOf("+"));
				String s2 = Standard.substring(Standard.indexOf("+") + 1,
						Standard.indexOf("/"));
				b = Float.parseFloat(s1) + Float.parseFloat(s2);
			}
			if (Standard.substring(0, Standard.indexOf("+")) != ""
					&& Standard.substring(0, Standard.indexOf("+")) != null
					&& Standard.substring(Standard.indexOf("-") + 1) != ""
					&& Standard.substring(Standard.indexOf("-") + 1) != null) {
				String s3 = Standard.substring(0, Standard.indexOf("+"));
				String s4 = Standard.substring(Standard.indexOf("-") + 1);
				c = Float.parseFloat(s3) - Float.parseFloat(s4);
			}
			spc.setMid(a);
			spc.setUsl(b);
			spc.setLsl(c);
		} else {
			spc.setMid(null);
			spc.setUsl(null);
			spc.setLsl(null);
		}

		// 将信息插入到spc主表里
		List<String> listspc = spcMapper.selectSpc(clientMaterialNo,
				materialNo, batchNo, process, characterVal);
		// 如果spc中存在这条信息则不插入到spc表中+前面几组有一个为空就是查询不到结果时都不执行插入语句
		if (listspc.size() == 0 && spc.getClientMaterialNo() != ""
				&& spc.getMakeEndDate() != "" && spc.getMid() != null) {
			spcMapper.insertSelective(spc);
		}
		// 后期新的数值更新他的截止日期时间
		else if (listspc.size() >= 0) {
			spcMapper.updateSpc(clientMaterialNo, materialNo, batchNo, process,
					characterVal, CheckEndDate);
		}

		
		
		
		// 二表spc_test插入特征值
		List<ProductTest> TestVal = productTestMapper.selectTestVal(
				clientMaterialNo, materialNo, batchNo, processName);
		// 将TestVal中的测量1-5值取出放到一个新建的listTestVal集合中
		// 如果输入的信息无法查到测量值则不进行二表提取插入操作
		if (TestVal.size() > 0) {
			List<String> listTestVal = new ArrayList<String>();
			for (int i = 0; i < TestVal.size(); i++) {

				if (TestVal.get(i).getTestVal1() != null
						&& !("".equals(TestVal.get(i).getTestVal1()))) {
					listTestVal.add(TestVal.get(i).getTestVal1());
				}
				if (TestVal.get(i).getTestVal2() != null
						&& !("".equals(TestVal.get(i).getTestVal2()))) {
					listTestVal.add(TestVal.get(i).getTestVal2());
				}
				if (TestVal.get(i).getTestVal3() != null
						&& !("".equals(TestVal.get(i).getTestVal3()))) {
					listTestVal.add(TestVal.get(i).getTestVal3());
				}
				if (TestVal.get(i).getTestVal4() != null
						&& !("".equals(TestVal.get(i).getTestVal4()))) {
					listTestVal.add(TestVal.get(i).getTestVal4());
				}
				if (TestVal.get(i).getTestVal5() != null
						&& !("".equals(TestVal.get(i).getTestVal5()))) {
					listTestVal.add(TestVal.get(i).getTestVal5());
				}

			}
			SpcTest spcTest = new SpcTest();
			// 只有存在125条测量值才会插入二表的信息
			if (listTestVal.size() >= 125) {
				// 从listTestVal抽取125条数据插入 spcTest中
				for (int i = 0; i < 125; i++) {
					// 每5个循环插入到spctest中的测量值1-5中

					if (i % 5 == 0) {
						spcTest.setTestVal1(Float.parseFloat(listTestVal.get(i)));
					} else if (i % 5 == 1) {
						spcTest.setTestVal2(Float.parseFloat(listTestVal.get(i)));
					} else if (i % 5 == 2) {

						spcTest.setTestVal3(Float.parseFloat(listTestVal.get(i)));
						System.out.println(spcTest.getTestVal3());
					} else if (i % 5 == 3) {
						spcTest.setTestVal4(Float.parseFloat(listTestVal.get(i)));
					} else if (i % 5 == 4) {
						spcTest.setTestVal5(Float.parseFloat(listTestVal.get(i)));
					}

					// 如果5个测量值中有一个为空则不进行插入数据
					if (spcTest.getTestVal1() != null
							&& spcTest.getTestVal2() != null
							&& spcTest.getTestVal3() != null
							&& spcTest.getTestVal4() != null
							&& spcTest.getTestVal5() != null) {
						// 插入测量值的和
						// Double.parseDouble(String.valueOf())：float型转化为double型
						double s = Double.parseDouble(String.valueOf(spcTest
								.getTestVal1()))
								+ Double.parseDouble(String.valueOf(spcTest
										.getTestVal2()))
								+ Double.parseDouble(String.valueOf(spcTest
										.getTestVal3()))
								+ Double.parseDouble(String.valueOf(spcTest
										.getTestVal4()))
								+ Double.parseDouble(String.valueOf(spcTest
										.getTestVal5()));
						spcTest.setSumX(s);
						// 插入平均值
						float ave = (spcTest.getTestVal1()
								+ spcTest.getTestVal2() + spcTest.getTestVal3()
								+ spcTest.getTestVal4() + spcTest.getTestVal5()) / 5;
						spcTest.setAveX(Double.parseDouble(String.valueOf(ave)));
						// 插入方差(取得这5个测量值每组的最大值和最小值)
						float max = 0;
						float min = 0;
						// 找出最大值赋给max
						if (spcTest.getTestVal1() >= spcTest.getTestVal2()
								&& spcTest.getTestVal1() >= spcTest
										.getTestVal3()
								&& spcTest.getTestVal1() >= spcTest
										.getTestVal4()
								&& spcTest.getTestVal1() >= spcTest
										.getTestVal5()) {
							max = spcTest.getTestVal1();
						} else if (spcTest.getTestVal2() >= spcTest
								.getTestVal1()
								&& spcTest.getTestVal2() >= spcTest
										.getTestVal3()
								&& spcTest.getTestVal2() >= spcTest
										.getTestVal4()
								&& spcTest.getTestVal2() >= spcTest
										.getTestVal5()) {
							max = spcTest.getTestVal2();
						} else if (spcTest.getTestVal3() >= spcTest
								.getTestVal1()
								&& spcTest.getTestVal3() >= spcTest
										.getTestVal2()
								&& spcTest.getTestVal3() >= spcTest
										.getTestVal4()
								&& spcTest.getTestVal3() >= spcTest
										.getTestVal5()) {
							max = spcTest.getTestVal3();
						} else if (spcTest.getTestVal4() >= spcTest
								.getTestVal1()
								&& spcTest.getTestVal4() >= spcTest
										.getTestVal2()
								&& spcTest.getTestVal4() >= spcTest
										.getTestVal3()
								&& spcTest.getTestVal4() >= spcTest
										.getTestVal5()) {
							max = spcTest.getTestVal4();
						} else if (spcTest.getTestVal5() >= spcTest
								.getTestVal1()
								&& spcTest.getTestVal5() >= spcTest
										.getTestVal2()
								&& spcTest.getTestVal5() >= spcTest
										.getTestVal3()
								&& spcTest.getTestVal5() >= spcTest
										.getTestVal4()) {
							max = spcTest.getTestVal5();
						} else {
							max = spcTest.getTestVal5();
						}
						// 找出最小值赋给min
						if (spcTest.getTestVal1() <= spcTest.getTestVal2()
								&& spcTest.getTestVal1() <= spcTest
										.getTestVal3()
								&& spcTest.getTestVal1() <= spcTest
										.getTestVal4()
								&& spcTest.getTestVal1() <= spcTest
										.getTestVal5()) {
							min = spcTest.getTestVal1();
						} else if (spcTest.getTestVal2() <= spcTest
								.getTestVal1()
								&& spcTest.getTestVal2() <= spcTest
										.getTestVal3()
								&& spcTest.getTestVal2() <= spcTest
										.getTestVal4()
								&& spcTest.getTestVal2() <= spcTest
										.getTestVal5()) {
							min = spcTest.getTestVal2();
						} else if (spcTest.getTestVal3() <= spcTest
								.getTestVal1()
								&& spcTest.getTestVal3() <= spcTest
										.getTestVal2()
								&& spcTest.getTestVal3() <= spcTest
										.getTestVal4()
								&& spcTest.getTestVal3() <= spcTest
										.getTestVal5()) {
							min = spcTest.getTestVal3();
						} else if (spcTest.getTestVal4() <= spcTest
								.getTestVal1()
								&& spcTest.getTestVal4() <= spcTest
										.getTestVal2()
								&& spcTest.getTestVal4() <= spcTest
										.getTestVal3()
								&& spcTest.getTestVal4() <= spcTest
										.getTestVal5()) {
							min = spcTest.getTestVal4();
						} else if (spcTest.getTestVal5() <= spcTest
								.getTestVal1()
								&& spcTest.getTestVal5() <= spcTest
										.getTestVal2()
								&& spcTest.getTestVal5() <= spcTest
										.getTestVal3()
								&& spcTest.getTestVal5() <= spcTest
										.getTestVal4()) {
							min = spcTest.getTestVal5();
						} else {
							min = spcTest.getTestVal5();
						}

						spcTest.setR(Double.parseDouble(String.valueOf(max))
								- Double.parseDouble(String.valueOf(min)));
						// 插入图号、物料号、批次号、工序、特征值
						spcTest.setClientMaterialNo(spc.getClientMaterialNo());
						spcTest.setMaterialNo(spc.getMaterialNo());
						spcTest.setBatchNo(spc.getBatchNo());
						spcTest.setProcessName(spc.getProcessName());
						spcTest.setCharacterVal(spc.getCharacterVal());
						// 如果二表该特征值下存在25条数据，则不插入
						List<SpcTest> Test = spcTestMapper.selectSpcTest(
								clientMaterialNo, materialNo, batchNo, process,
								characterVal);
						if (Test.size() < 25) {

							spcTestMapper.insertSelective(spcTest);
							// 插入之后再将test_val_1-5设置为null
							spcTest.setTestVal1(null);
							spcTest.setTestVal2(null);
							spcTest.setTestVal3(null);
							spcTest.setTestVal4(null);
							spcTest.setTestVal5(null);
							// System.out.println(spcTest.getTestVal3());
						}

					}
					// 对应于if(listTestVal.size()>=125){判断125条数据是否获得
				}
				// }对应于 if(TestVal.size()>0){判断测量值是否为空
			}
		}
		
		//重新查询Spc表的信息回显到桌面
		processName=process;
        Spc spc1=spcMapper.selectUpdateSpcrecord(batchNo, processName, characterVal);
		return spc1;
	}

	
	
	// 2.Spc表格显示
	@Override
	public List<String> SpcTableShow(String clientMaterialNo,
			String materialNo, String batchNo, String process,
			String characterVal) {
		// 从二表中抽取Test_Val1-5的25条数据
		List<SpcTest> Value = spcTestMapper.selectSpcTest(clientMaterialNo,
				materialNo, batchNo, process, characterVal);
		// 新建一个集合储存125条数据+25条和+25条均值+25条极差
		List<String> listValue = new ArrayList<String>();
		for (int i = 0; i < 25; i++) {
			listValue.add(String.valueOf(Value.get(i).getTestVal1()));
			listValue.add(String.valueOf(Value.get(i).getTestVal2()));
			listValue.add(String.valueOf(Value.get(i).getTestVal3()));
			listValue.add(String.valueOf(Value.get(i).getTestVal4()));
			listValue.add(String.valueOf(Value.get(i).getTestVal5()));
			listValue.add(String.valueOf(Value.get(i).getSumX()));
			listValue.add(String.valueOf(Value.get(i).getAveX()));
			listValue.add(String.valueOf(Value.get(i).getR()));
		}
		return listValue;
	}

	// 3.Spc曲线显示
	@Override
	public List<String> SpcBightShow(String clientMaterialNo,
			String materialNo, String batchNo, String process,
			String characterVal) {
		List<SpcTest> SpcBight = spcTestMapper.selectSpcTest(clientMaterialNo,
				materialNo, batchNo, process, characterVal);
		// 元素累加集合中的x均值和R极差叠加
		double X = 0.0;
		double R = 0.0;
		for (int i = 0; i < SpcBight.size(); i++) {
			X = X + SpcBight.get(i).getAveX();
			R = R + SpcBight.get(i).getR();
		}
		// 对中值保留2位小数
		double X1 = (double) Math.round((X / 25) * 100) / 100;
		// 对R平均保留2位小数
		double R1 = (double) Math.round((R / 25) * 100) / 100;
		// X中值
		String Mid_x = String.valueOf(X1);
		// R均值
		String Ave_R = String.valueOf(R1);
		// 分别得到Spc曲线需要的数值
		double A2 = 0.577;
		Double D4 = 2.2114;
		// 对X均值上公差限，下公差限，R极差的上公差限保留三位小数
		double UCLx1 = (double) Math.round((X / 25 + A2 * (R / 25)) * 100) / 100;
		double LCLx1 = (double) Math.round((X / 25 - A2 * (R / 25)) * 100) / 100;
		double LCLr1 = (double) Math.round((D4 * (R / 25)) * 100) / 100;
		// x均值控制图的控制上下限
		String UCLx = String.valueOf(UCLx1);
		String LCLx = String.valueOf(LCLx1);
		// r均值控制图的控制上限，下限默认为0(D3在组数为5的情况下为0)
		String UCLr = String.valueOf(LCLr1);

		// 分别向集合中依次传数据
		List<String> listValue = new ArrayList<String>();
		listValue.add(Mid_x);
		listValue.add(Ave_R);
		listValue.add(UCLx);
		listValue.add(LCLx);
		listValue.add(UCLr);

		for (int i = 0; i < SpcBight.size(); i++) {
			listValue.add(String.valueOf(SpcBight.get(i).getAveX()));
		}
		for (int i = 0; i < SpcBight.size(); i++) {
			listValue.add(String.valueOf(SpcBight.get(i).getR()));
		}

		// 设置一个集合接受平均值
		List<String> listmin = new ArrayList<String>();
		// (int)((X/25-A2*(R/25))*10)/10.0f)保留1位置小数但不进行四舍五入
		listmin.add(String
				.valueOf((int) ((X / 25 - A2 * (R / 25)) * 10) / 10.0f));
		for (int i = 0; i < SpcBight.size(); i++) {
			//
			listmin.add(String
					.valueOf((int) (SpcBight.get(i).getAveX() * 10) / 10.0f));
		}
		// 再从平均值的集合中获取最小的值
		listValue.add(Collections.min(listmin));
		return listValue;
	}

	// 4.Spc过程能力评估
	@Override
	public List<String> SpcPCI(String clientMaterialNo, String materialNo,
			String batchNo, String process, String characterVal) {

		Spc SpcPCI = spcMapper.selectPCI(clientMaterialNo, materialNo, batchNo,
				process, characterVal);
		List<SpcTest> SpcTestPCI = spcTestMapper.selectSpcTest(
				clientMaterialNo, materialNo, batchNo, process, characterVal);

		// 1.计算Cpk的值
		// 元素累加集合中的x均值和R极差叠加
		double X = 0.0;
		double R = 0.0;
		for (int i = 0; i < SpcTestPCI.size(); i++) {
			X = X + SpcTestPCI.get(i).getAveX();
			R = R + SpcTestPCI.get(i).getR();
		}
		// 对中值保留2位小数
		double X1 = (double) Math.round((X / 25) * 100) / 100;
		// 对R平均保留3位小数
		double R1 = (double) Math.round((R / 25) * 1000) / 1000;
		// 将USL,LSL将float转换成double类型
		double USL = Double.parseDouble(String.valueOf(SpcPCI.getUsl()));
		double LSL = Double.parseDouble(String.valueOf(SpcPCI.getLsl()));

		// d=R平均/d2
		double D2 = 2.326;
		double d1 = (double) Math.round((R1 / D2) * 1000) / 1000;

		double Cpk1 = (USL - X1) / (3 * d1);
		double Cpk2 = (X1 - LSL) / (3 * d1);
		// 取出最小的得到Cpk的值
		double Cpk = 0.0;
		if (Cpk1 <= Cpk2) {
			Cpk = Cpk1;
		} else {
			Cpk = Cpk2;
		}

		// 2.计算Ppk的值
		double sum = 0.0;
		for (int i = 0; i < SpcTestPCI.size(); i++) {
			// (Xi-X中值)^2
			// double
			// s=(SpcTestPCI.get(i).getAveX()-X1)*(SpcTestPCI.get(i).getAveX()-X1);
			double s = (SpcTestPCI.get(i).getTestVal1() - X1)
					* (SpcTestPCI.get(i).getTestVal1() - X1)
					+ (SpcTestPCI.get(i).getTestVal2() - X1)
					* (SpcTestPCI.get(i).getTestVal2() - X1)
					+ (SpcTestPCI.get(i).getTestVal3() - X1)
					* (SpcTestPCI.get(i).getTestVal3() - X1)
					+ (SpcTestPCI.get(i).getTestVal4() - X1)
					* (SpcTestPCI.get(i).getTestVal4() - X1)
					+ (SpcTestPCI.get(i).getTestVal5() - X1)
					* (SpcTestPCI.get(i).getTestVal5() - X1);
			// ∑(i=1-25)(Xi-X中值)^2
			sum = sum + s;
		}
		double d2 = (double) Math.round((Math.sqrt(sum / 124)) * 1000) / 1000;

		double Ppk1 = (USL - X1) / (3 * d2);
		double Ppk2 = (X1 - LSL) / (3 * d2);
		// 取出最小的得到Cpk的值
		double Ppk = 0.0;
		if (Ppk1 <= Ppk2) {
			Ppk = Ppk1;
		} else {
			Ppk = Ppk2;
		}

		List<String> listCP = new ArrayList<String>();

		listCP.add(String.valueOf((double) Math.round(Cpk * 100) / 100));
		listCP.add(String.valueOf((double) Math.round(Ppk * 100) / 100));

		return listCP;
	}

	// 5.Ajax根据物料号得到工序，并下拉显示
	@Override
	public String selectProcess(String materialNo) {
		List<String> list = testProcessMapper
				.selectMaterialNoToProcess(materialNo);
		String html = "<option disabled selected style='display: none;'>--请选择--</option>";
		// 循环遍历获得物料号，并将value值返回option前端下拉框
		for (int i = 0; i < list.size(); i++) {
			// 回显value到jsp页面上
			html += "<option value=" + list.get(i) + ">" + list.get(i)
					+ "</option>";

		}
		return html;
	}

	// 6.Spc记录查询（分页）
	@Override
	public List<Spc> selectSpcrecord(int startPos, int pageSize,
			String materialNo, String batchNo, String process,
			String characterVal) {

		return spcMapper.selectSpcrecord(startPos, pageSize, materialNo,
				batchNo, process, characterVal);
	}

	// 7.Spc修改记录查询（分页）
	@Override
	public Spc selectUpdateSpcrecord(String batchNo, String process,
			String characterVal) {
		Spc spc=spcMapper.selectUpdateSpcrecord(batchNo, process, characterVal);
		return spc;
	}
	
	
	// 8.SpcTest修改记录查询（分页）
	@Override
	public List<SpcTest> selectEditSpcrecord(String batchNo, String process,
			String characterVal) {
		List<SpcTest> listSpcTest=spcTestMapper.selectEditSpcrecord(batchNo, process, characterVal);
		return listSpcTest;
	}
	
	//9.Spc修改
	@Override
	public int updateByPrimaryKeySelective(Spc spcId) {
		
		return spcMapper.updateByPrimaryKey(spcId);
	}

	
	//10.SpcTest修改
	@Override
	public void updateSpcTest(List<SpcTest> listSpcTest) {
		for(int i=0;i<listSpcTest.size();i++){
			SpcTest spcTest=listSpcTest.get(i);
			spcTestMapper.updateByPrimaryKey(spcTest);
		}
		
	}
}
