package cn.ssm.po;

public class Spc {
    private Integer spcId;

    private String clientMaterialNo;

    private String materialNo;

    private String batchNo;

    private String productName;

    private String processName;

    private String characterVal;

    private String makeStartDate;

    private String makeEndDate;

    private Float mid;

    private Float usl;

    private Float lsl;

    public Integer getSpcId() {
        return spcId;
    }

    public void setSpcId(Integer spcId) {
        this.spcId = spcId;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    public String getCharacterVal() {
        return characterVal;
    }

    public void setCharacterVal(String characterVal) {
        this.characterVal = characterVal == null ? null : characterVal.trim();
    }

    public String getMakeStartDate() {
        return makeStartDate;
    }

    public void setMakeStartDate(String makeStartDate) {
        this.makeStartDate = makeStartDate == null ? null : makeStartDate.trim();
    }

    public String getMakeEndDate() {
        return makeEndDate;
    }

    public void setMakeEndDate(String makeEndDate) {
        this.makeEndDate = makeEndDate == null ? null : makeEndDate.trim();
    }

    public Float getMid() {
        return mid;
    }

    public void setMid(Float mid) {
        this.mid = mid;
    }

    public Float getUsl() {
        return usl;
    }

    public void setUsl(Float usl) {
        this.usl = usl;
    }

    public Float getLsl() {
        return lsl;
    }

    public void setLsl(Float lsl) {
        this.lsl = lsl;
    }
}