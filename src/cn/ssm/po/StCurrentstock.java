package cn.ssm.po;

import java.math.BigDecimal;
import java.util.Date;

public class StCurrentstock {
    private Integer idwarehouse;

    private Integer idinventory;

    private String batch;

    private Integer idmarketingorgan;

    private String receivevouchercode;

    private Date productiondate;

    private Date expirydate;

    private Integer receivevoucherid;

    private Integer receivevoucherdetailid;

    private Date updated;

    private Integer idbaseunit;

    private Integer idsubunit;

    private Integer id;

    private Byte iscarriedforwardout;

    private Byte iscarriedforwardin;

    private BigDecimal basequantity;

    private BigDecimal subquantity;

    private BigDecimal canusebasequantity;

    private BigDecimal onwaybasequantity;

    private BigDecimal fordispatchbasequantity;

    private BigDecimal canusesubquantity;

    private BigDecimal onwaysubquantity;

    private BigDecimal fordispatchsubquantity;

    private String freeitem0;

    private String freeitem1;

    private String freeitem2;

    private String freeitem3;

    private String freeitem4;

    private String freeitem5;

    private String freeitem6;

    private String freeitem7;

    private String freeitem8;

    private String freeitem9;

    private BigDecimal purchaseorderonwaybasequantity;

    private BigDecimal purchasearrivalbasequantity;

    private BigDecimal onproducingbasequantity;

    private BigDecimal producefordispatchbasequantity;

    private BigDecimal delegateorderforproductbasequantity;

    private BigDecimal delegateorderformaterialbasequantity;

    private BigDecimal otheronwaybasequantity;

    private BigDecimal forsaleorderbasequantity;

    private BigDecimal purchaseforreceivebasequantity;

    private BigDecimal productforreceivebasequantity;

    private BigDecimal delegateforreceivebasequantity;

    private BigDecimal transonwaybasequantity;

    private BigDecimal transfordispatchbasequantity;

    private BigDecimal saledeliverybasequantity;

    private BigDecimal forsaledispatchbasequantity;

    private BigDecimal materialforsendbasequantity;

    private BigDecimal delegatefordispatchbasequantity;

    private BigDecimal otherfordispatchbasequantity;

    private BigDecimal stockrequestbasequantity;

    private BigDecimal purchaseorderonwaysubquantity;

    private BigDecimal purchasearrivalsubquantity;

    private BigDecimal onproducingsubquantity;

    private BigDecimal producefordispatchsubquantity;

    private BigDecimal delegateorderforproductsubquantity;

    private BigDecimal delegateorderformaterialsubquantity;

    private BigDecimal otheronwaysubquantity;

    private BigDecimal forsaleordersubquantity;

    private BigDecimal purchaseforreceivesubquantity;

    private BigDecimal productforreceivesubquantity;

    private BigDecimal delegateforreceivesubquantity;

    private BigDecimal transonwaysubquantity;

    private BigDecimal transfordispatchsubquantity;

    private BigDecimal saledeliverysubquantity;

    private BigDecimal forsaledispatchsubquantity;

    private BigDecimal materialforsendsubquantity;

    private BigDecimal delegatefordispatchsubquantity;

    private BigDecimal otherfordispatchsubquantity;

    private BigDecimal stockrequestsubquantity;

    private Integer changerate;

    private Integer idbatchdispatchdto;

    private Integer idvoucherunit;

    private Integer idvoucherunit2;

    private Integer lowquantity;

    private Integer prebasequantity;

    private Integer presubquantity;

    private Integer topquantity;

    private Integer updatedby;

    private Integer voucherquantity;

    private Integer voucherquantity2;

    private Date createdtime;

    private byte[] ts;

    public Integer getIdwarehouse() {
        return idwarehouse;
    }

    public void setIdwarehouse(Integer idwarehouse) {
        this.idwarehouse = idwarehouse;
    }

    public Integer getIdinventory() {
        return idinventory;
    }

    public void setIdinventory(Integer idinventory) {
        this.idinventory = idinventory;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch == null ? null : batch.trim();
    }

    public Integer getIdmarketingorgan() {
        return idmarketingorgan;
    }

    public void setIdmarketingorgan(Integer idmarketingorgan) {
        this.idmarketingorgan = idmarketingorgan;
    }

    public String getReceivevouchercode() {
        return receivevouchercode;
    }

    public void setReceivevouchercode(String receivevouchercode) {
        this.receivevouchercode = receivevouchercode == null ? null : receivevouchercode.trim();
    }

    public Date getProductiondate() {
        return productiondate;
    }

    public void setProductiondate(Date productiondate) {
        this.productiondate = productiondate;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public Integer getReceivevoucherid() {
        return receivevoucherid;
    }

    public void setReceivevoucherid(Integer receivevoucherid) {
        this.receivevoucherid = receivevoucherid;
    }

    public Integer getReceivevoucherdetailid() {
        return receivevoucherdetailid;
    }

    public void setReceivevoucherdetailid(Integer receivevoucherdetailid) {
        this.receivevoucherdetailid = receivevoucherdetailid;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getIdbaseunit() {
        return idbaseunit;
    }

    public void setIdbaseunit(Integer idbaseunit) {
        this.idbaseunit = idbaseunit;
    }

    public Integer getIdsubunit() {
        return idsubunit;
    }

    public void setIdsubunit(Integer idsubunit) {
        this.idsubunit = idsubunit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getIscarriedforwardout() {
        return iscarriedforwardout;
    }

    public void setIscarriedforwardout(Byte iscarriedforwardout) {
        this.iscarriedforwardout = iscarriedforwardout;
    }

    public Byte getIscarriedforwardin() {
        return iscarriedforwardin;
    }

    public void setIscarriedforwardin(Byte iscarriedforwardin) {
        this.iscarriedforwardin = iscarriedforwardin;
    }

    public BigDecimal getBasequantity() {
        return basequantity;
    }

    public void setBasequantity(BigDecimal basequantity) {
        this.basequantity = basequantity;
    }

    public BigDecimal getSubquantity() {
        return subquantity;
    }

    public void setSubquantity(BigDecimal subquantity) {
        this.subquantity = subquantity;
    }

    public BigDecimal getCanusebasequantity() {
        return canusebasequantity;
    }

    public void setCanusebasequantity(BigDecimal canusebasequantity) {
        this.canusebasequantity = canusebasequantity;
    }

    public BigDecimal getOnwaybasequantity() {
        return onwaybasequantity;
    }

    public void setOnwaybasequantity(BigDecimal onwaybasequantity) {
        this.onwaybasequantity = onwaybasequantity;
    }

    public BigDecimal getFordispatchbasequantity() {
        return fordispatchbasequantity;
    }

    public void setFordispatchbasequantity(BigDecimal fordispatchbasequantity) {
        this.fordispatchbasequantity = fordispatchbasequantity;
    }

    public BigDecimal getCanusesubquantity() {
        return canusesubquantity;
    }

    public void setCanusesubquantity(BigDecimal canusesubquantity) {
        this.canusesubquantity = canusesubquantity;
    }

    public BigDecimal getOnwaysubquantity() {
        return onwaysubquantity;
    }

    public void setOnwaysubquantity(BigDecimal onwaysubquantity) {
        this.onwaysubquantity = onwaysubquantity;
    }

    public BigDecimal getFordispatchsubquantity() {
        return fordispatchsubquantity;
    }

    public void setFordispatchsubquantity(BigDecimal fordispatchsubquantity) {
        this.fordispatchsubquantity = fordispatchsubquantity;
    }

    public String getFreeitem0() {
        return freeitem0;
    }

    public void setFreeitem0(String freeitem0) {
        this.freeitem0 = freeitem0 == null ? null : freeitem0.trim();
    }

    public String getFreeitem1() {
        return freeitem1;
    }

    public void setFreeitem1(String freeitem1) {
        this.freeitem1 = freeitem1 == null ? null : freeitem1.trim();
    }

    public String getFreeitem2() {
        return freeitem2;
    }

    public void setFreeitem2(String freeitem2) {
        this.freeitem2 = freeitem2 == null ? null : freeitem2.trim();
    }

    public String getFreeitem3() {
        return freeitem3;
    }

    public void setFreeitem3(String freeitem3) {
        this.freeitem3 = freeitem3 == null ? null : freeitem3.trim();
    }

    public String getFreeitem4() {
        return freeitem4;
    }

    public void setFreeitem4(String freeitem4) {
        this.freeitem4 = freeitem4 == null ? null : freeitem4.trim();
    }

    public String getFreeitem5() {
        return freeitem5;
    }

    public void setFreeitem5(String freeitem5) {
        this.freeitem5 = freeitem5 == null ? null : freeitem5.trim();
    }

    public String getFreeitem6() {
        return freeitem6;
    }

    public void setFreeitem6(String freeitem6) {
        this.freeitem6 = freeitem6 == null ? null : freeitem6.trim();
    }

    public String getFreeitem7() {
        return freeitem7;
    }

    public void setFreeitem7(String freeitem7) {
        this.freeitem7 = freeitem7 == null ? null : freeitem7.trim();
    }

    public String getFreeitem8() {
        return freeitem8;
    }

    public void setFreeitem8(String freeitem8) {
        this.freeitem8 = freeitem8 == null ? null : freeitem8.trim();
    }

    public String getFreeitem9() {
        return freeitem9;
    }

    public void setFreeitem9(String freeitem9) {
        this.freeitem9 = freeitem9 == null ? null : freeitem9.trim();
    }

    public BigDecimal getPurchaseorderonwaybasequantity() {
        return purchaseorderonwaybasequantity;
    }

    public void setPurchaseorderonwaybasequantity(BigDecimal purchaseorderonwaybasequantity) {
        this.purchaseorderonwaybasequantity = purchaseorderonwaybasequantity;
    }

    public BigDecimal getPurchasearrivalbasequantity() {
        return purchasearrivalbasequantity;
    }

    public void setPurchasearrivalbasequantity(BigDecimal purchasearrivalbasequantity) {
        this.purchasearrivalbasequantity = purchasearrivalbasequantity;
    }

    public BigDecimal getOnproducingbasequantity() {
        return onproducingbasequantity;
    }

    public void setOnproducingbasequantity(BigDecimal onproducingbasequantity) {
        this.onproducingbasequantity = onproducingbasequantity;
    }

    public BigDecimal getProducefordispatchbasequantity() {
        return producefordispatchbasequantity;
    }

    public void setProducefordispatchbasequantity(BigDecimal producefordispatchbasequantity) {
        this.producefordispatchbasequantity = producefordispatchbasequantity;
    }

    public BigDecimal getDelegateorderforproductbasequantity() {
        return delegateorderforproductbasequantity;
    }

    public void setDelegateorderforproductbasequantity(BigDecimal delegateorderforproductbasequantity) {
        this.delegateorderforproductbasequantity = delegateorderforproductbasequantity;
    }

    public BigDecimal getDelegateorderformaterialbasequantity() {
        return delegateorderformaterialbasequantity;
    }

    public void setDelegateorderformaterialbasequantity(BigDecimal delegateorderformaterialbasequantity) {
        this.delegateorderformaterialbasequantity = delegateorderformaterialbasequantity;
    }

    public BigDecimal getOtheronwaybasequantity() {
        return otheronwaybasequantity;
    }

    public void setOtheronwaybasequantity(BigDecimal otheronwaybasequantity) {
        this.otheronwaybasequantity = otheronwaybasequantity;
    }

    public BigDecimal getForsaleorderbasequantity() {
        return forsaleorderbasequantity;
    }

    public void setForsaleorderbasequantity(BigDecimal forsaleorderbasequantity) {
        this.forsaleorderbasequantity = forsaleorderbasequantity;
    }

    public BigDecimal getPurchaseforreceivebasequantity() {
        return purchaseforreceivebasequantity;
    }

    public void setPurchaseforreceivebasequantity(BigDecimal purchaseforreceivebasequantity) {
        this.purchaseforreceivebasequantity = purchaseforreceivebasequantity;
    }

    public BigDecimal getProductforreceivebasequantity() {
        return productforreceivebasequantity;
    }

    public void setProductforreceivebasequantity(BigDecimal productforreceivebasequantity) {
        this.productforreceivebasequantity = productforreceivebasequantity;
    }

    public BigDecimal getDelegateforreceivebasequantity() {
        return delegateforreceivebasequantity;
    }

    public void setDelegateforreceivebasequantity(BigDecimal delegateforreceivebasequantity) {
        this.delegateforreceivebasequantity = delegateforreceivebasequantity;
    }

    public BigDecimal getTransonwaybasequantity() {
        return transonwaybasequantity;
    }

    public void setTransonwaybasequantity(BigDecimal transonwaybasequantity) {
        this.transonwaybasequantity = transonwaybasequantity;
    }

    public BigDecimal getTransfordispatchbasequantity() {
        return transfordispatchbasequantity;
    }

    public void setTransfordispatchbasequantity(BigDecimal transfordispatchbasequantity) {
        this.transfordispatchbasequantity = transfordispatchbasequantity;
    }

    public BigDecimal getSaledeliverybasequantity() {
        return saledeliverybasequantity;
    }

    public void setSaledeliverybasequantity(BigDecimal saledeliverybasequantity) {
        this.saledeliverybasequantity = saledeliverybasequantity;
    }

    public BigDecimal getForsaledispatchbasequantity() {
        return forsaledispatchbasequantity;
    }

    public void setForsaledispatchbasequantity(BigDecimal forsaledispatchbasequantity) {
        this.forsaledispatchbasequantity = forsaledispatchbasequantity;
    }

    public BigDecimal getMaterialforsendbasequantity() {
        return materialforsendbasequantity;
    }

    public void setMaterialforsendbasequantity(BigDecimal materialforsendbasequantity) {
        this.materialforsendbasequantity = materialforsendbasequantity;
    }

    public BigDecimal getDelegatefordispatchbasequantity() {
        return delegatefordispatchbasequantity;
    }

    public void setDelegatefordispatchbasequantity(BigDecimal delegatefordispatchbasequantity) {
        this.delegatefordispatchbasequantity = delegatefordispatchbasequantity;
    }

    public BigDecimal getOtherfordispatchbasequantity() {
        return otherfordispatchbasequantity;
    }

    public void setOtherfordispatchbasequantity(BigDecimal otherfordispatchbasequantity) {
        this.otherfordispatchbasequantity = otherfordispatchbasequantity;
    }

    public BigDecimal getStockrequestbasequantity() {
        return stockrequestbasequantity;
    }

    public void setStockrequestbasequantity(BigDecimal stockrequestbasequantity) {
        this.stockrequestbasequantity = stockrequestbasequantity;
    }

    public BigDecimal getPurchaseorderonwaysubquantity() {
        return purchaseorderonwaysubquantity;
    }

    public void setPurchaseorderonwaysubquantity(BigDecimal purchaseorderonwaysubquantity) {
        this.purchaseorderonwaysubquantity = purchaseorderonwaysubquantity;
    }

    public BigDecimal getPurchasearrivalsubquantity() {
        return purchasearrivalsubquantity;
    }

    public void setPurchasearrivalsubquantity(BigDecimal purchasearrivalsubquantity) {
        this.purchasearrivalsubquantity = purchasearrivalsubquantity;
    }

    public BigDecimal getOnproducingsubquantity() {
        return onproducingsubquantity;
    }

    public void setOnproducingsubquantity(BigDecimal onproducingsubquantity) {
        this.onproducingsubquantity = onproducingsubquantity;
    }

    public BigDecimal getProducefordispatchsubquantity() {
        return producefordispatchsubquantity;
    }

    public void setProducefordispatchsubquantity(BigDecimal producefordispatchsubquantity) {
        this.producefordispatchsubquantity = producefordispatchsubquantity;
    }

    public BigDecimal getDelegateorderforproductsubquantity() {
        return delegateorderforproductsubquantity;
    }

    public void setDelegateorderforproductsubquantity(BigDecimal delegateorderforproductsubquantity) {
        this.delegateorderforproductsubquantity = delegateorderforproductsubquantity;
    }

    public BigDecimal getDelegateorderformaterialsubquantity() {
        return delegateorderformaterialsubquantity;
    }

    public void setDelegateorderformaterialsubquantity(BigDecimal delegateorderformaterialsubquantity) {
        this.delegateorderformaterialsubquantity = delegateorderformaterialsubquantity;
    }

    public BigDecimal getOtheronwaysubquantity() {
        return otheronwaysubquantity;
    }

    public void setOtheronwaysubquantity(BigDecimal otheronwaysubquantity) {
        this.otheronwaysubquantity = otheronwaysubquantity;
    }

    public BigDecimal getForsaleordersubquantity() {
        return forsaleordersubquantity;
    }

    public void setForsaleordersubquantity(BigDecimal forsaleordersubquantity) {
        this.forsaleordersubquantity = forsaleordersubquantity;
    }

    public BigDecimal getPurchaseforreceivesubquantity() {
        return purchaseforreceivesubquantity;
    }

    public void setPurchaseforreceivesubquantity(BigDecimal purchaseforreceivesubquantity) {
        this.purchaseforreceivesubquantity = purchaseforreceivesubquantity;
    }

    public BigDecimal getProductforreceivesubquantity() {
        return productforreceivesubquantity;
    }

    public void setProductforreceivesubquantity(BigDecimal productforreceivesubquantity) {
        this.productforreceivesubquantity = productforreceivesubquantity;
    }

    public BigDecimal getDelegateforreceivesubquantity() {
        return delegateforreceivesubquantity;
    }

    public void setDelegateforreceivesubquantity(BigDecimal delegateforreceivesubquantity) {
        this.delegateforreceivesubquantity = delegateforreceivesubquantity;
    }

    public BigDecimal getTransonwaysubquantity() {
        return transonwaysubquantity;
    }

    public void setTransonwaysubquantity(BigDecimal transonwaysubquantity) {
        this.transonwaysubquantity = transonwaysubquantity;
    }

    public BigDecimal getTransfordispatchsubquantity() {
        return transfordispatchsubquantity;
    }

    public void setTransfordispatchsubquantity(BigDecimal transfordispatchsubquantity) {
        this.transfordispatchsubquantity = transfordispatchsubquantity;
    }

    public BigDecimal getSaledeliverysubquantity() {
        return saledeliverysubquantity;
    }

    public void setSaledeliverysubquantity(BigDecimal saledeliverysubquantity) {
        this.saledeliverysubquantity = saledeliverysubquantity;
    }

    public BigDecimal getForsaledispatchsubquantity() {
        return forsaledispatchsubquantity;
    }

    public void setForsaledispatchsubquantity(BigDecimal forsaledispatchsubquantity) {
        this.forsaledispatchsubquantity = forsaledispatchsubquantity;
    }

    public BigDecimal getMaterialforsendsubquantity() {
        return materialforsendsubquantity;
    }

    public void setMaterialforsendsubquantity(BigDecimal materialforsendsubquantity) {
        this.materialforsendsubquantity = materialforsendsubquantity;
    }

    public BigDecimal getDelegatefordispatchsubquantity() {
        return delegatefordispatchsubquantity;
    }

    public void setDelegatefordispatchsubquantity(BigDecimal delegatefordispatchsubquantity) {
        this.delegatefordispatchsubquantity = delegatefordispatchsubquantity;
    }

    public BigDecimal getOtherfordispatchsubquantity() {
        return otherfordispatchsubquantity;
    }

    public void setOtherfordispatchsubquantity(BigDecimal otherfordispatchsubquantity) {
        this.otherfordispatchsubquantity = otherfordispatchsubquantity;
    }

    public BigDecimal getStockrequestsubquantity() {
        return stockrequestsubquantity;
    }

    public void setStockrequestsubquantity(BigDecimal stockrequestsubquantity) {
        this.stockrequestsubquantity = stockrequestsubquantity;
    }

    public Integer getChangerate() {
        return changerate;
    }

    public void setChangerate(Integer changerate) {
        this.changerate = changerate;
    }

    public Integer getIdbatchdispatchdto() {
        return idbatchdispatchdto;
    }

    public void setIdbatchdispatchdto(Integer idbatchdispatchdto) {
        this.idbatchdispatchdto = idbatchdispatchdto;
    }

    public Integer getIdvoucherunit() {
        return idvoucherunit;
    }

    public void setIdvoucherunit(Integer idvoucherunit) {
        this.idvoucherunit = idvoucherunit;
    }

    public Integer getIdvoucherunit2() {
        return idvoucherunit2;
    }

    public void setIdvoucherunit2(Integer idvoucherunit2) {
        this.idvoucherunit2 = idvoucherunit2;
    }

    public Integer getLowquantity() {
        return lowquantity;
    }

    public void setLowquantity(Integer lowquantity) {
        this.lowquantity = lowquantity;
    }

    public Integer getPrebasequantity() {
        return prebasequantity;
    }

    public void setPrebasequantity(Integer prebasequantity) {
        this.prebasequantity = prebasequantity;
    }

    public Integer getPresubquantity() {
        return presubquantity;
    }

    public void setPresubquantity(Integer presubquantity) {
        this.presubquantity = presubquantity;
    }

    public Integer getTopquantity() {
        return topquantity;
    }

    public void setTopquantity(Integer topquantity) {
        this.topquantity = topquantity;
    }

    public Integer getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(Integer updatedby) {
        this.updatedby = updatedby;
    }

    public Integer getVoucherquantity() {
        return voucherquantity;
    }

    public void setVoucherquantity(Integer voucherquantity) {
        this.voucherquantity = voucherquantity;
    }

    public Integer getVoucherquantity2() {
        return voucherquantity2;
    }

    public void setVoucherquantity2(Integer voucherquantity2) {
        this.voucherquantity2 = voucherquantity2;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}