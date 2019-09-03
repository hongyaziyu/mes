package cn.ssm.po;

public class GetDetail {
    private Integer detailId;

    private Integer getMaterialId;

    private String cailiaoMc;

    private String batchNo;

    private String materialBatchNo;

    private String cailiaoBh;

    private String unit;

    private String materialNum;

    private String referNum;

    private String provider;

    private String acceptor;

    private String getDate;

    private String remark;

    private Integer isReturn;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getGetMaterialId() {
        return getMaterialId;
    }

    public void setGetMaterialId(Integer getMaterialId) {
        this.getMaterialId = getMaterialId;
    }

    public String getCailiaoMc() {
        return cailiaoMc;
    }

    public void setCailiaoMc(String cailiaoMc) {
        this.cailiaoMc = cailiaoMc == null ? null : cailiaoMc.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getMaterialBatchNo() {
        return materialBatchNo;
    }

    public void setMaterialBatchNo(String materialBatchNo) {
        this.materialBatchNo = materialBatchNo == null ? null : materialBatchNo.trim();
    }

    public String getCailiaoBh() {
        return cailiaoBh;
    }

    public void setCailiaoBh(String cailiaoBh) {
        this.cailiaoBh = cailiaoBh == null ? null : cailiaoBh.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getMaterialNum() {
        return materialNum;
    }

    public void setMaterialNum(String materialNum) {
        this.materialNum = materialNum == null ? null : materialNum.trim();
    }

    public String getReferNum() {
        return referNum;
    }

    public void setReferNum(String referNum) {
        this.referNum = referNum == null ? null : referNum.trim();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor == null ? null : acceptor.trim();
    }

    public String getGetDate() {
        return getDate;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate == null ? null : getDate.trim();
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
}