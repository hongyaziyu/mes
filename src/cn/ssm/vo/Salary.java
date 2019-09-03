package cn.ssm.vo;

import java.util.List;

import cn.ssm.po.Cipin;

public class Salary {
   
	private String produceDate;
	
	private String clientMaterialNo;
	
	private String materialNo;
	
	private String batchNo;
	
	private String shopName;
	
	private String productSpec;
	 
	private String processName;
	
	private String operator;
	 
	private String hegeNum;
	
    private String price;
    
  //料费计算新增加 ================================
  	private Integer trackId;
  	
  	private String totalCipinNum;
  	
  	//料废和工废数量查询增加 ================================修改 
  	
  	private List<Cipin> cipin;
  	
	public List<Cipin> getCipin() {
		return cipin;
	}

	public void setCipin(List<Cipin> cipin) {
		this.cipin = cipin;
	}

	public Integer getTrackId() {
		return trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}

	public String getTotalCipinNum() {
		return totalCipinNum;
	}

	public void setTotalCipinNum(String totalCipinNum) {
		this.totalCipinNum = totalCipinNum;
	}

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	public String getClientMaterialNo() {
		return clientMaterialNo;
	}

	public void setClientMaterialNo(String clientMaterialNo) {
		this.clientMaterialNo = clientMaterialNo;
	}

	public String getMaterialNo() {
		return materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getHegeNum() {
		return hegeNum;
	}

	public void setHegeNum(String hegeNum) {
		this.hegeNum = hegeNum;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	 
}
