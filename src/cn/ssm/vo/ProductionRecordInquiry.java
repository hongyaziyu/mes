package cn.ssm.vo;


//多表查询（生产记录查询的新建pojo类）
public class ProductionRecordInquiry {
   
	private String produceDate;
	
	private String planNo;

	private String batchNo;
	
	private String materialName;
	
	private String shopName;
	
	private String processName;
	
	private String operator;
	
	private String hegeNum;
	
	private String totalCipinNum;
	
	private String asset;

    private String assetState;
    
    private String mold;

    private String moldState;

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	public String getCipinNum() {
		return totalCipinNum;
	}

	public void setCipinNum(String cipinNum) {
		this.totalCipinNum = cipinNum;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public String getAssetState() {
		return assetState;
	}

	public void setAssetState(String assetState) {
		this.assetState = assetState;
	}

	public String getMold() {
		return mold;
	}

	public void setMold(String mold) {
		this.mold = mold;
	}

	public String getMoldState() {
		return moldState;
	}

	public void setMoldState(String moldState) {
		this.moldState = moldState;
	}

	 
}
