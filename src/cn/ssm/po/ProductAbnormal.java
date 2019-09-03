package cn.ssm.po;

public class ProductAbnormal {
    private Integer abnormalId;

    private String abnormalNo;

    private String client;

    private String planNo;

    private String batchNo;

    private String clientMaterialNo;

    private String productName;

    private String materialNo;

    private String site;

    private String halfProductName;

    private String productSpecfic;

    private String produceDate;

    private String abnormalNum;

    private String sendDate;

    private String sendPerson;

    private String abnormalDesc;

    private String trackVerification;

    private String verificDate;

    private String verifier;

    private String remark;

    private Integer isRepair;

    public Integer getAbnormalId() {
        return abnormalId;
    }

    public void setAbnormalId(Integer abnormalId) {
        this.abnormalId = abnormalId;
    }

    public String getAbnormalNo() {
        return abnormalNo;
    }

    public void setAbnormalNo(String abnormalNo) {
        this.abnormalNo = abnormalNo == null ? null : abnormalNo.trim();
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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getClientMaterialNo() {
        return clientMaterialNo;
    }

    public void setClientMaterialNo(String clientMaterialNo) {
        this.clientMaterialNo = clientMaterialNo == null ? null : clientMaterialNo.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo == null ? null : materialNo.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public String getHalfProductName() {
        return halfProductName;
    }

    public void setHalfProductName(String halfProductName) {
        this.halfProductName = halfProductName == null ? null : halfProductName.trim();
    }

    public String getProductSpecfic() {
        return productSpecfic;
    }

    public void setProductSpecfic(String productSpecfic) {
        this.productSpecfic = productSpecfic == null ? null : productSpecfic.trim();
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate == null ? null : produceDate.trim();
    }

    public String getAbnormalNum() {
        return abnormalNum;
    }

    public void setAbnormalNum(String abnormalNum) {
        this.abnormalNum = abnormalNum == null ? null : abnormalNum.trim();
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate == null ? null : sendDate.trim();
    }

    public String getSendPerson() {
        return sendPerson;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson == null ? null : sendPerson.trim();
    }

    public String getAbnormalDesc() {
        return abnormalDesc;
    }

    public void setAbnormalDesc(String abnormalDesc) {
        this.abnormalDesc = abnormalDesc == null ? null : abnormalDesc.trim();
    }

    public String getTrackVerification() {
        return trackVerification;
    }

    public void setTrackVerification(String trackVerification) {
        this.trackVerification = trackVerification == null ? null : trackVerification.trim();
    }

    public String getVerificDate() {
        return verificDate;
    }

    public void setVerificDate(String verificDate) {
        this.verificDate = verificDate == null ? null : verificDate.trim();
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier == null ? null : verifier.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsRepair() {
        return isRepair;
    }

    public void setIsRepair(Integer isRepair) {
        this.isRepair = isRepair;
    }
}