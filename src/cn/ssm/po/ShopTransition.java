package cn.ssm.po;

public class ShopTransition {
    private Integer transitionId;

    private String planNo;

    private String clientMaterialNo;

    private String materialNo;

    private String batchNo;

    private String provider;

    private String shop1;

    private String acceptor;

    private String shop2;

    private String tranDate;

    private String qualifiedNum;

    private String unqualifiedNum;

    private Integer type;

    private Integer priority;

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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getShop1() {
        return shop1;
    }

    public void setShop1(String shop1) {
        this.shop1 = shop1 == null ? null : shop1.trim();
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor == null ? null : acceptor.trim();
    }

    public String getShop2() {
        return shop2;
    }

    public void setShop2(String shop2) {
        this.shop2 = shop2 == null ? null : shop2.trim();
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}