package cn.ssm.po;

public class GetSecDetail {
    private Integer detailId;

    private Integer getMaterialsId;

    private String reshopName;

    private String secMaterialNo;

    private String secMaterialName;

    private String type;

    private String num;

    private String unit;

    private String returner;

    private String receiver;

    private String time;

    private String remark;

    private Integer isReturn;

    private Integer isRecorded;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getGetMaterialsId() {
        return getMaterialsId;
    }

    public void setGetMaterialsId(Integer getMaterialsId) {
        this.getMaterialsId = getMaterialsId;
    }

    public String getReshopName() {
        return reshopName;
    }

    public void setReshopName(String reshopName) {
        this.reshopName = reshopName == null ? null : reshopName.trim();
    }

    public String getSecMaterialNo() {
        return secMaterialNo;
    }

    public void setSecMaterialNo(String secMaterialNo) {
        this.secMaterialNo = secMaterialNo == null ? null : secMaterialNo.trim();
    }

    public String getSecMaterialName() {
        return secMaterialName;
    }

    public void setSecMaterialName(String secMaterialName) {
        this.secMaterialName = secMaterialName == null ? null : secMaterialName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getReturner() {
        return returner;
    }

    public void setReturner(String returner) {
        this.returner = returner == null ? null : returner.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
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

    public Integer getIsRecorded() {
        return isRecorded;
    }

    public void setIsRecorded(Integer isRecorded) {
        this.isRecorded = isRecorded;
    }
}