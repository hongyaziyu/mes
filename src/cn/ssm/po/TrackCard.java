package cn.ssm.po;

public class TrackCard {
    private Integer cardId;

    private String client;

    private String planNo;

    private String clientMaterialNo;

    private String materialNo;

    private String batchNo;

    private String materialName;

    private String productSpec;

    private String lupihao;

    private String productNo;

    private String productDes;

    private String productFig;

    private String materialSpec;

    private String materialBatchNo;

    private String productionOrder;

    private String people;

    private String makeDate;

    private Integer isNew;
    
    //添加的第二张表的pojo实体映射，以及他的set，get方法(1对多)
    //private List<WorkCard> workCard;
    
    
    

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client == null ? null : client.trim();
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo == null ? null : planNo.trim();
    }

    public String getClientMaterialNo() {
        return clientMaterialNo;
    }

    public void setClientMaterialNo(String clientMaterialNo) {
        this.clientMaterialNo = clientMaterialNo == null ? null : clientMaterialNo.trim();
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo == null ? null : materialNo.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec == null ? null : productSpec.trim();
    }

    public String getLupihao() {
        return lupihao;
    }

    public void setLupihao(String lupihao) {
        this.lupihao = lupihao == null ? null : lupihao.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes == null ? null : productDes.trim();
    }

    public String getProductFig() {
        return productFig;
    }

    public void setProductFig(String productFig) {
        this.productFig = productFig == null ? null : productFig.trim();
    }

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec == null ? null : materialSpec.trim();
    }

    public String getMaterialBatchNo() {
        return materialBatchNo;
    }

    public void setMaterialBatchNo(String materialBatchNo) {
        this.materialBatchNo = materialBatchNo == null ? null : materialBatchNo.trim();
    }

    public String getProductionOrder() {
        return productionOrder;
    }

    public void setProductionOrder(String productionOrder) {
        this.productionOrder = productionOrder == null ? null : productionOrder.trim();
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate == null ? null : makeDate.trim();
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

	/*public List<WorkCard> getWorkCard() {
		return workCard;
	}

	public void setWorkCard(List<WorkCard> workCard) {
		this.workCard = workCard;
	}*/

	

}