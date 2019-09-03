package cn.ssm.po;

public class ProductRecord {
    private Integer recordId;

    private String planNo;

    private String clientMaterialNo;

    private String materialNo;

    private String productMc;

    private String batchNo1;

    private String batchNo2;

    private String weight;

    private String productNum;

    private String unit;

    private String shop1;

    private String provider;

    private String shop2;

    private String acceptor;

    private String transDate;

    private String remark;

    private Integer isReturn;

    private Integer isNormal;

    private Integer isRecorded;

    private Integer isError;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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

    public String getProductMc() {
        return productMc;
    }

    public void setProductMc(String productMc) {
        this.productMc = productMc == null ? null : productMc.trim();
    }

    public String getBatchNo1() {
        return batchNo1;
    }

    public void setBatchNo1(String batchNo1) {
        this.batchNo1 = batchNo1 == null ? null : batchNo1.trim();
    }

    public String getBatchNo2() {
        return batchNo2;
    }

    public void setBatchNo2(String batchNo2) {
        this.batchNo2 = batchNo2 == null ? null : batchNo2.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum == null ? null : productNum.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getShop1() {
        return shop1;
    }

    public void setShop1(String shop1) {
        this.shop1 = shop1 == null ? null : shop1.trim();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getShop2() {
        return shop2;
    }

    public void setShop2(String shop2) {
        this.shop2 = shop2 == null ? null : shop2.trim();
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor == null ? null : acceptor.trim();
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate == null ? null : transDate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }

    public Integer getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(Integer isNormal) {
        this.isNormal = isNormal;
    }

    public Integer getIsRecorded() {
        return isRecorded;
    }

    public void setIsRecorded(Integer isRecorded) {
        this.isRecorded = isRecorded;
    }

    public Integer getIsError() {
        return isError;
    }

    public void setIsError(Integer isError) {
        this.isError = isError;
    }
}