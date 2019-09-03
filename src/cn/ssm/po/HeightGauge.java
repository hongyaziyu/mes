package cn.ssm.po;

public class HeightGauge {
    private Integer id;

    private String gaugeNo;

    private String productSpecification;

    private String toleranceRange;

    private String num;

    private String declarationDate;

    private String purpose;

    private String completionDate;

    private String collarWorkers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGaugeNo() {
        return gaugeNo;
    }

    public void setGaugeNo(String gaugeNo) {
        this.gaugeNo = gaugeNo == null ? null : gaugeNo.trim();
    }

    public String getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification == null ? null : productSpecification.trim();
    }

    public String getToleranceRange() {
        return toleranceRange;
    }

    public void setToleranceRange(String toleranceRange) {
        this.toleranceRange = toleranceRange == null ? null : toleranceRange.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(String declarationDate) {
        this.declarationDate = declarationDate == null ? null : declarationDate.trim();
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose == null ? null : purpose.trim();
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate == null ? null : completionDate.trim();
    }

    public String getCollarWorkers() {
        return collarWorkers;
    }

    public void setCollarWorkers(String collarWorkers) {
        this.collarWorkers = collarWorkers == null ? null : collarWorkers.trim();
    }
}