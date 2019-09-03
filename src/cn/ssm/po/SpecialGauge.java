package cn.ssm.po;

public class SpecialGauge {
    private Integer id;

    private String inspectionProduction;

    private String type;

    private String gaugeName;

    private String gaugeNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInspectionProduction() {
        return inspectionProduction;
    }

    public void setInspectionProduction(String inspectionProduction) {
        this.inspectionProduction = inspectionProduction == null ? null : inspectionProduction.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getGaugeName() {
        return gaugeName;
    }

    public void setGaugeName(String gaugeName) {
        this.gaugeName = gaugeName == null ? null : gaugeName.trim();
    }

    public String getGaugeNo() {
        return gaugeNo;
    }

    public void setGaugeNo(String gaugeNo) {
        this.gaugeNo = gaugeNo == null ? null : gaugeNo.trim();
    }
}