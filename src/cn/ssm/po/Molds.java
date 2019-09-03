package cn.ssm.po;

public class Molds {
    private Integer moldId;

    private String client;

    private String figureNo;

    private String moldName;

    private String moldNo;

    private String libraryNo;

    private String sideNo;

    private String remark;

    private String rfid;

    public Integer getMoldId() {
        return moldId;
    }

    public void setMoldId(Integer moldId) {
        this.moldId = moldId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client == null ? null : client.trim();
    }

    public String getFigureNo() {
        return figureNo;
    }

    public void setFigureNo(String figureNo) {
        this.figureNo = figureNo == null ? null : figureNo.trim();
    }

    public String getMoldName() {
        return moldName;
    }

    public void setMoldName(String moldName) {
        this.moldName = moldName == null ? null : moldName.trim();
    }

    public String getMoldNo() {
        return moldNo;
    }

    public void setMoldNo(String moldNo) {
        this.moldNo = moldNo == null ? null : moldNo.trim();
    }

    public String getLibraryNo() {
        return libraryNo;
    }

    public void setLibraryNo(String libraryNo) {
        this.libraryNo = libraryNo == null ? null : libraryNo.trim();
    }

    public String getSideNo() {
        return sideNo;
    }

    public void setSideNo(String sideNo) {
        this.sideNo = sideNo == null ? null : sideNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }
}