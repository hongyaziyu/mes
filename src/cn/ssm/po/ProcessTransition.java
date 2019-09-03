package cn.ssm.po;

public class ProcessTransition {
    private Integer transitionId;

    private String planNo;

    private String clientMaterialNo;

    private String materialNo;

    private String batchNo;

    private String shopName;

    private String provider;

    private String process1;

    private String acceptor;

    private String process2;

    private String tranDate;

    private String qualifiedNum;

    private String unqualifiedNum;

    private String shopName1;

    public Integer getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(Integer transitionId) {
        this.transitionId = transitionId;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getProcess1() {
        return process1;
    }

    public void setProcess1(String process1) {
        this.process1 = process1 == null ? null : process1.trim();
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor == null ? null : acceptor.trim();
    }

    public String getProcess2() {
        return process2;
    }

    public void setProcess2(String process2) {
        this.process2 = process2 == null ? null : process2.trim();
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate == null ? null : tranDate.trim();
    }

    public String getQualifiedNum() {
        return qualifiedNum;
    }

    public void setQualifiedNum(String qualifiedNum) {
        this.qualifiedNum = qualifiedNum == null ? null : qualifiedNum.trim();
    }

    public String getUnqualifiedNum() {
        return unqualifiedNum;
    }

    public void setUnqualifiedNum(String unqualifiedNum) {
        this.unqualifiedNum = unqualifiedNum == null ? null : unqualifiedNum.trim();
    }

    public String getShopName1() {
        return shopName1;
    }

    public void setShopName1(String shopName1) {
        this.shopName1 = shopName1 == null ? null : shopName1.trim();
    }
}