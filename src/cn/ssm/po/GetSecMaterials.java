package cn.ssm.po;

public class GetSecMaterials {
    private Integer getMaterialsId;

    private String shopName;

    private String acceptor;

    private String provider;

    private String applyer;

    private String applyData;

    private String approver;

    private String approveData;

    private Integer isApprove;

    private Integer isApply;

    private Integer isPushed;

    private Integer isRecorded;

    private Integer isReturn;

    public Integer getGetMaterialsId() {
        return getMaterialsId;
    }

    public void setGetMaterialsId(Integer getMaterialsId) {
        this.getMaterialsId = getMaterialsId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor == null ? null : acceptor.trim();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer == null ? null : applyer.trim();
    }

    public String getApplyData() {
        return applyData;
    }

    public void setApplyData(String applyData) {
        this.applyData = applyData == null ? null : applyData.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public String getApproveData() {
        return approveData;
    }

    public void setApproveData(String approveData) {
        this.approveData = approveData == null ? null : approveData.trim();
    }

    public Integer getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Integer isApprove) {
        this.isApprove = isApprove;
    }

    public Integer getIsApply() {
        return isApply;
    }

    public void setIsApply(Integer isApply) {
        this.isApply = isApply;
    }

    public Integer getIsPushed() {
        return isPushed;
    }

    public void setIsPushed(Integer isPushed) {
        this.isPushed = isPushed;
    }

    public Integer getIsRecorded() {
        return isRecorded;
    }

    public void setIsRecorded(Integer isRecorded) {
        this.isRecorded = isRecorded;
    }

    public Integer getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }
}