package cn.ssm.vo;

import java.math.BigDecimal;

public class SaleOrderDetail {
	  //图号
	  private String clientMaterialNo;
	  //物料号
	  private String materialNo;
	  //订购数量
	  private BigDecimal ordernum;
	  //单位
	  private String  unit;
	  //产品名称
	  private String productName;
	  //产品规格
      private String productSpec;	
	 
	
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
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public BigDecimal getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(BigDecimal ordernum) {
		this.ordernum = ordernum;
	}
	
	
}
