package cn.ssm.po;

public class CipinType {
    private Integer typeId;

    private String shopName;

    private String cipinType;

    private String cipinDetail;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getCipinType() {
        return cipinType;
    }

    public void setCipinType(String cipinType) {
        this.cipinType = cipinType == null ? null : cipinType.trim();
    }

    public String getCipinDetail() {
        return cipinDetail;
    }

    public void setCipinDetail(String cipinDetail) {
        this.cipinDetail = cipinDetail == null ? null : cipinDetail.trim();
    }
}