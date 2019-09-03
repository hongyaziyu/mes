package cn.ssm.po;

import java.util.List;

public class WorkCard {
    private Integer trackId;

    private Integer cardId;

    private String shopName;

    private String processName;

    private String operator;

    private String asset;

    private String assetState;

    private String mold;

    private String moldState;

    private String totalNum;

    private String hegeNum;

    private String totalCipinNum;

    private String produceDate;

    private String remark;

    private String price;

    private String temPrice;

    private Integer isApproval;
    
  //添加的第三张表的pojo实体映射，以及他的set，get方法(1对多)
    private List<Cipin> cipin;

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset == null ? null : asset.trim();
    }

    public String getAssetState() {
        return assetState;
    }

    public void setAssetState(String assetState) {
        this.assetState = assetState == null ? null : assetState.trim();
    }

    public String getMold() {
        return mold;
    }

    public void setMold(String mold) {
        this.mold = mold == null ? null : mold.trim();
    }

    public String getMoldState() {
        return moldState;
    }

    public void setMoldState(String moldState) {
        this.moldState = moldState == null ? null : moldState.trim();
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum == null ? null : totalNum.trim();
    }

    public String getHegeNum() {
        return hegeNum;
    }

    public void setHegeNum(String hegeNum) {
        this.hegeNum = hegeNum == null ? null : hegeNum.trim();
    }

    public String getTotalCipinNum() {
        return totalCipinNum;
    }

    public void setTotalCipinNum(String totalCipinNum) {
        this.totalCipinNum = totalCipinNum == null ? null : totalCipinNum.trim();
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate == null ? null : produceDate.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getTemPrice() {
        return temPrice;
    }

    public void setTemPrice(String temPrice) {
        this.temPrice = temPrice == null ? null : temPrice.trim();
    }

    public Integer getIsApproval() {
        return isApproval;
    }

    public void setIsApproval(Integer isApproval) {
        this.isApproval = isApproval;
    }
    
    public List<Cipin> getCipin() {
		return cipin;
	}

	public void setCipin(List<Cipin> cipin) {
		this.cipin = cipin;
	}
}