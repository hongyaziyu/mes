package cn.ssm.po;

public class ProductsBom {
    private Integer productsId;

    private String client;

    private String clientMaterialNo;

    private String materialNo;

    private String productName;

    private String productSpec;

    private String zijianNo;

    private String zijianName;

    private Float ratio1;

    private Float ratio2;

    private Float weight;

    private String unit;

    public Integer getProductsId() {
        return productsId;
    }

    public void setProductsId(Integer productsId) {
        this.productsId = productsId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client == null ? null : client.trim();
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec == null ? null : productSpec.trim();
    }

    public String getZijianNo() {
        return zijianNo;
    }

    public void setZijianNo(String zijianNo) {
        this.zijianNo = zijianNo == null ? null : zijianNo.trim();
    }

    public String getZijianName() {
        return zijianName;
    }

    public void setZijianName(String zijianName) {
        this.zijianName = zijianName == null ? null : zijianName.trim();
    }

    public Float getRatio1() {
        return ratio1;
    }

    public void setRatio1(Float ratio1) {
        this.ratio1 = ratio1;
    }

    public Float getRatio2() {
        return ratio2;
    }

    public void setRatio2(Float ratio2) {
        this.ratio2 = ratio2;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }
}