package cn.ssm.vo;

import java.util.Date;

public class SaleOrder {
      //订单号
	  private String code;
	  //客户名称
	  private String client;
	  //客户id
	  private Integer idcustomer;
	  //制单人
	  private String maker;
	  //制单日期
	  private Date madedate;
	  //审批人
	  private String auditor;
	  //审核日期
	  private Date auditeddate;
	  
	  //标准制单时间
	  private String madedate1;
	  //标准审核日期
	  private String auditeddate1;
	  //关闭人
	  private String closer;
	  
	  private Integer id;
	  
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Integer getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(Integer idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public Date getMadedate() {
		return madedate;
	}
	public void setMadedate(Date madedate) {
		this.madedate = madedate;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getAuditeddate() {
		return auditeddate;
	}
	public void setAuditeddate(Date auditeddate) {
		this.auditeddate = auditeddate;
	}
	public String getMadedate1() {
		return madedate1;
	}
	public void setMadedate1(String madedate1) {
		this.madedate1 = madedate1;
	}
	public String getAuditeddate1() {
		return auditeddate1;
	}
	public void setAuditeddate1(String auditeddate1) {
		this.auditeddate1 = auditeddate1;
	}
	public String getCloser() {
		return closer;
	}
	public void setCloser(String closer) {
		this.closer = closer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	 
	
	  
}
