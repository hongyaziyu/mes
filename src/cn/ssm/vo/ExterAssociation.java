package cn.ssm.vo;
//部分外协出入\库单据
public class ExterAssociation {
	private Integer transitionId;
	
	private String planNo;

    private String clientMaterialNo;

    private String materialNo;

    private String batchNo;

    private String shop1;

    private String acceptor;
    
    private String provider;

    private String shop2;

    private String tranDate;

    private String qualifiedNum;
    
    private String productName;
    
    private String productSpec;

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
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

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getShop1() {
		return shop1;
	}

	public void setShop1(String shop1) {
		this.shop1 = shop1;
	}

	public String getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}

	public String getShop2() {
		return shop2;
	}

	public void setShop2(String shop2) {
		this.shop2 = shop2;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getQualifiedNum() {
		return qualifiedNum;
	}

	public void setQualifiedNum(String qualifiedNum) {
		this.qualifiedNum = qualifiedNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getTransitionId() {
		return transitionId;
	}

	public void setTransitionId(Integer transitionId) {
		this.transitionId = transitionId;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

    
    
}
