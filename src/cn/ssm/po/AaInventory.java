package cn.ssm.po;

import java.math.BigDecimal;
import java.util.Date;

public class AaInventory {
    private Integer id;

    private String code;

    private String name;

    private String shorthand;

    private String specification;

    private BigDecimal procurebatch;

    private BigDecimal invscost;

    private BigDecimal latestcostAbandon;

    private BigDecimal avagcostAbandon;

    private Byte islimitedwithdraw;

    private Byte isbatch;

    private Byte isqualityperiod;

    private Byte issale;

    private Byte ismadeself;

    private Byte ispurchase;

    private Byte ismaterial;

    private BigDecimal lowquantity;

    private BigDecimal topquantity;

    private BigDecimal safequantity;

    private String picture;

    private Byte disabled;

    private Byte isqualitycheck;

    private Byte ismaderequest;

    private Byte issingleunit;

    private String updatedby;

    private Byte userfreeitem7;

    private Byte userfreeitem6;

    private Byte userfreeitem2;

    private Byte userfreeitem1;

    private Byte userfreeitem9;

    private Byte userfreeitem0;

    private Byte userfreeitem8;

    private Byte userfreeitem5;

    private Byte userfreeitem4;

    private Byte userfreeitem3;

    private Byte mustinputfreeitem7;

    private Byte mustinputfreeitem2;

    private Byte mustinputfreeitem6;

    private Byte mustinputfreeitem3;

    private Byte mustinputfreeitem5;

    private Byte mustinputfreeitem4;

    private Byte mustinputfreeitem9;

    private Byte mustinputfreeitem1;

    private Byte mustinputfreeitem8;

    private Byte mustinputfreeitem0;

    private BigDecimal producebatch;

    private String imagefile;

    private String priuserdefnvc1;

    private BigDecimal priuserdefdecm1;

    private String priuserdefnvc2;

    private BigDecimal priuserdefdecm2;

    private String priuserdefnvc3;

    private BigDecimal priuserdefdecm3;

    private String priuserdefnvc4;

    private BigDecimal priuserdefdecm4;

    private String priuserdefnvc5;

    private BigDecimal priuserdefdecm5;

    private BigDecimal standardturnoverdays;

    private String haseverchanged;

    private BigDecimal pickbatch;

    private Byte isphantom;

    private Byte controlrangefreeitem0;

    private Byte controlrangefreeitem1;

    private Byte controlrangefreeitem2;

    private Byte controlrangefreeitem3;

    private Byte controlrangefreeitem4;

    private Byte controlrangefreeitem5;

    private Byte controlrangefreeitem6;

    private Byte controlrangefreeitem7;

    private Byte controlrangefreeitem8;

    private Byte controlrangefreeitem9;

    private Byte islaborcost;

    private Long batchrunnumber;

    private Byte isnew;

    private Date maderecorddate;

    private String inventorydescript;

    private Integer renewgoodselldays;

    private Integer renewgoodaheaddays;

    private Byte issuite;

    private Byte isweigh;

    private String defaultbarcode;

    private Integer newproductperiod;

    private Integer expired;

    private Integer idbarcodesolution;

    private Integer idinventoryclass;

    private Integer idinvlocation;

    private Integer idmarketingorgan;

    private Integer idpartner;

    private Integer idunit;

    private Integer idunitbymanufacture;

    private Integer idunitbypurchase;

    private Integer idunitbyretail;

    private Integer idunitbysale;

    private Integer idunitbystock;

    private Integer idunitgroup;

    private Integer idsubunitbyreport;

    private Integer expiredunit;

    private Integer idwarehouse;

    private Integer customerreplenishmentrule;

    private Integer pickbatchmethod;

    private Integer planattribute;

    private Integer productinfo;

    private Integer storereplenishmentrule;

    private Integer taxrate;

    private Integer unittype;

    private Integer valuetype;

    private Date madedate;

    private Date updated;

    private Date createdtime;

    private String creater;

    private String changer;

    private Date changedate;

    private String jinshuicode;

    private Byte ismodifiedcode;

    private Byte withoutbargain;

    private Integer idinventoryfreeitemclass0;

    private Integer idinventoryfreeitemclass1;

    private Integer idinventoryfreeitemclass2;

    private Integer idinventoryfreeitemclass3;

    private Integer idinventoryfreeitemclass4;

    private Integer idinventoryfreeitemclass5;

    private Integer idinventoryfreeitemclass6;

    private Integer idinventoryfreeitemclass7;

    private Integer idinventoryfreeitemclass8;

    private Integer idinventoryfreeitemclass9;

    private BigDecimal instorageuplimit;

    private BigDecimal outstorageuplimit;

    private byte[] ts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand == null ? null : shorthand.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public BigDecimal getProcurebatch() {
        return procurebatch;
    }

    public void setProcurebatch(BigDecimal procurebatch) {
        this.procurebatch = procurebatch;
    }

    public BigDecimal getInvscost() {
        return invscost;
    }

    public void setInvscost(BigDecimal invscost) {
        this.invscost = invscost;
    }

    public BigDecimal getLatestcostAbandon() {
        return latestcostAbandon;
    }

    public void setLatestcostAbandon(BigDecimal latestcostAbandon) {
        this.latestcostAbandon = latestcostAbandon;
    }

    public BigDecimal getAvagcostAbandon() {
        return avagcostAbandon;
    }

    public void setAvagcostAbandon(BigDecimal avagcostAbandon) {
        this.avagcostAbandon = avagcostAbandon;
    }

    public Byte getIslimitedwithdraw() {
        return islimitedwithdraw;
    }

    public void setIslimitedwithdraw(Byte islimitedwithdraw) {
        this.islimitedwithdraw = islimitedwithdraw;
    }

    public Byte getIsbatch() {
        return isbatch;
    }

    public void setIsbatch(Byte isbatch) {
        this.isbatch = isbatch;
    }

    public Byte getIsqualityperiod() {
        return isqualityperiod;
    }

    public void setIsqualityperiod(Byte isqualityperiod) {
        this.isqualityperiod = isqualityperiod;
    }

    public Byte getIssale() {
        return issale;
    }

    public void setIssale(Byte issale) {
        this.issale = issale;
    }

    public Byte getIsmadeself() {
        return ismadeself;
    }

    public void setIsmadeself(Byte ismadeself) {
        this.ismadeself = ismadeself;
    }

    public Byte getIspurchase() {
        return ispurchase;
    }

    public void setIspurchase(Byte ispurchase) {
        this.ispurchase = ispurchase;
    }

    public Byte getIsmaterial() {
        return ismaterial;
    }

    public void setIsmaterial(Byte ismaterial) {
        this.ismaterial = ismaterial;
    }

    public BigDecimal getLowquantity() {
        return lowquantity;
    }

    public void setLowquantity(BigDecimal lowquantity) {
        this.lowquantity = lowquantity;
    }

    public BigDecimal getTopquantity() {
        return topquantity;
    }

    public void setTopquantity(BigDecimal topquantity) {
        this.topquantity = topquantity;
    }

    public BigDecimal getSafequantity() {
        return safequantity;
    }

    public void setSafequantity(BigDecimal safequantity) {
        this.safequantity = safequantity;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    public Byte getIsqualitycheck() {
        return isqualitycheck;
    }

    public void setIsqualitycheck(Byte isqualitycheck) {
        this.isqualitycheck = isqualitycheck;
    }

    public Byte getIsmaderequest() {
        return ismaderequest;
    }

    public void setIsmaderequest(Byte ismaderequest) {
        this.ismaderequest = ismaderequest;
    }

    public Byte getIssingleunit() {
        return issingleunit;
    }

    public void setIssingleunit(Byte issingleunit) {
        this.issingleunit = issingleunit;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby == null ? null : updatedby.trim();
    }

    public Byte getUserfreeitem7() {
        return userfreeitem7;
    }

    public void setUserfreeitem7(Byte userfreeitem7) {
        this.userfreeitem7 = userfreeitem7;
    }

    public Byte getUserfreeitem6() {
        return userfreeitem6;
    }

    public void setUserfreeitem6(Byte userfreeitem6) {
        this.userfreeitem6 = userfreeitem6;
    }

    public Byte getUserfreeitem2() {
        return userfreeitem2;
    }

    public void setUserfreeitem2(Byte userfreeitem2) {
        this.userfreeitem2 = userfreeitem2;
    }

    public Byte getUserfreeitem1() {
        return userfreeitem1;
    }

    public void setUserfreeitem1(Byte userfreeitem1) {
        this.userfreeitem1 = userfreeitem1;
    }

    public Byte getUserfreeitem9() {
        return userfreeitem9;
    }

    public void setUserfreeitem9(Byte userfreeitem9) {
        this.userfreeitem9 = userfreeitem9;
    }

    public Byte getUserfreeitem0() {
        return userfreeitem0;
    }

    public void setUserfreeitem0(Byte userfreeitem0) {
        this.userfreeitem0 = userfreeitem0;
    }

    public Byte getUserfreeitem8() {
        return userfreeitem8;
    }

    public void setUserfreeitem8(Byte userfreeitem8) {
        this.userfreeitem8 = userfreeitem8;
    }

    public Byte getUserfreeitem5() {
        return userfreeitem5;
    }

    public void setUserfreeitem5(Byte userfreeitem5) {
        this.userfreeitem5 = userfreeitem5;
    }

    public Byte getUserfreeitem4() {
        return userfreeitem4;
    }

    public void setUserfreeitem4(Byte userfreeitem4) {
        this.userfreeitem4 = userfreeitem4;
    }

    public Byte getUserfreeitem3() {
        return userfreeitem3;
    }

    public void setUserfreeitem3(Byte userfreeitem3) {
        this.userfreeitem3 = userfreeitem3;
    }

    public Byte getMustinputfreeitem7() {
        return mustinputfreeitem7;
    }

    public void setMustinputfreeitem7(Byte mustinputfreeitem7) {
        this.mustinputfreeitem7 = mustinputfreeitem7;
    }

    public Byte getMustinputfreeitem2() {
        return mustinputfreeitem2;
    }

    public void setMustinputfreeitem2(Byte mustinputfreeitem2) {
        this.mustinputfreeitem2 = mustinputfreeitem2;
    }

    public Byte getMustinputfreeitem6() {
        return mustinputfreeitem6;
    }

    public void setMustinputfreeitem6(Byte mustinputfreeitem6) {
        this.mustinputfreeitem6 = mustinputfreeitem6;
    }

    public Byte getMustinputfreeitem3() {
        return mustinputfreeitem3;
    }

    public void setMustinputfreeitem3(Byte mustinputfreeitem3) {
        this.mustinputfreeitem3 = mustinputfreeitem3;
    }

    public Byte getMustinputfreeitem5() {
        return mustinputfreeitem5;
    }

    public void setMustinputfreeitem5(Byte mustinputfreeitem5) {
        this.mustinputfreeitem5 = mustinputfreeitem5;
    }

    public Byte getMustinputfreeitem4() {
        return mustinputfreeitem4;
    }

    public void setMustinputfreeitem4(Byte mustinputfreeitem4) {
        this.mustinputfreeitem4 = mustinputfreeitem4;
    }

    public Byte getMustinputfreeitem9() {
        return mustinputfreeitem9;
    }

    public void setMustinputfreeitem9(Byte mustinputfreeitem9) {
        this.mustinputfreeitem9 = mustinputfreeitem9;
    }

    public Byte getMustinputfreeitem1() {
        return mustinputfreeitem1;
    }

    public void setMustinputfreeitem1(Byte mustinputfreeitem1) {
        this.mustinputfreeitem1 = mustinputfreeitem1;
    }

    public Byte getMustinputfreeitem8() {
        return mustinputfreeitem8;
    }

    public void setMustinputfreeitem8(Byte mustinputfreeitem8) {
        this.mustinputfreeitem8 = mustinputfreeitem8;
    }

    public Byte getMustinputfreeitem0() {
        return mustinputfreeitem0;
    }

    public void setMustinputfreeitem0(Byte mustinputfreeitem0) {
        this.mustinputfreeitem0 = mustinputfreeitem0;
    }

    public BigDecimal getProducebatch() {
        return producebatch;
    }

    public void setProducebatch(BigDecimal producebatch) {
        this.producebatch = producebatch;
    }

    public String getImagefile() {
        return imagefile;
    }

    public void setImagefile(String imagefile) {
        this.imagefile = imagefile == null ? null : imagefile.trim();
    }

    public String getPriuserdefnvc1() {
        return priuserdefnvc1;
    }

    public void setPriuserdefnvc1(String priuserdefnvc1) {
        this.priuserdefnvc1 = priuserdefnvc1 == null ? null : priuserdefnvc1.trim();
    }

    public BigDecimal getPriuserdefdecm1() {
        return priuserdefdecm1;
    }

    public void setPriuserdefdecm1(BigDecimal priuserdefdecm1) {
        this.priuserdefdecm1 = priuserdefdecm1;
    }

    public String getPriuserdefnvc2() {
        return priuserdefnvc2;
    }

    public void setPriuserdefnvc2(String priuserdefnvc2) {
        this.priuserdefnvc2 = priuserdefnvc2 == null ? null : priuserdefnvc2.trim();
    }

    public BigDecimal getPriuserdefdecm2() {
        return priuserdefdecm2;
    }

    public void setPriuserdefdecm2(BigDecimal priuserdefdecm2) {
        this.priuserdefdecm2 = priuserdefdecm2;
    }

    public String getPriuserdefnvc3() {
        return priuserdefnvc3;
    }

    public void setPriuserdefnvc3(String priuserdefnvc3) {
        this.priuserdefnvc3 = priuserdefnvc3 == null ? null : priuserdefnvc3.trim();
    }

    public BigDecimal getPriuserdefdecm3() {
        return priuserdefdecm3;
    }

    public void setPriuserdefdecm3(BigDecimal priuserdefdecm3) {
        this.priuserdefdecm3 = priuserdefdecm3;
    }

    public String getPriuserdefnvc4() {
        return priuserdefnvc4;
    }

    public void setPriuserdefnvc4(String priuserdefnvc4) {
        this.priuserdefnvc4 = priuserdefnvc4 == null ? null : priuserdefnvc4.trim();
    }

    public BigDecimal getPriuserdefdecm4() {
        return priuserdefdecm4;
    }

    public void setPriuserdefdecm4(BigDecimal priuserdefdecm4) {
        this.priuserdefdecm4 = priuserdefdecm4;
    }

    public String getPriuserdefnvc5() {
        return priuserdefnvc5;
    }

    public void setPriuserdefnvc5(String priuserdefnvc5) {
        this.priuserdefnvc5 = priuserdefnvc5 == null ? null : priuserdefnvc5.trim();
    }

    public BigDecimal getPriuserdefdecm5() {
        return priuserdefdecm5;
    }

    public void setPriuserdefdecm5(BigDecimal priuserdefdecm5) {
        this.priuserdefdecm5 = priuserdefdecm5;
    }

    public BigDecimal getStandardturnoverdays() {
        return standardturnoverdays;
    }

    public void setStandardturnoverdays(BigDecimal standardturnoverdays) {
        this.standardturnoverdays = standardturnoverdays;
    }

    public String getHaseverchanged() {
        return haseverchanged;
    }

    public void setHaseverchanged(String haseverchanged) {
        this.haseverchanged = haseverchanged == null ? null : haseverchanged.trim();
    }

    public BigDecimal getPickbatch() {
        return pickbatch;
    }

    public void setPickbatch(BigDecimal pickbatch) {
        this.pickbatch = pickbatch;
    }

    public Byte getIsphantom() {
        return isphantom;
    }

    public void setIsphantom(Byte isphantom) {
        this.isphantom = isphantom;
    }

    public Byte getControlrangefreeitem0() {
        return controlrangefreeitem0;
    }

    public void setControlrangefreeitem0(Byte controlrangefreeitem0) {
        this.controlrangefreeitem0 = controlrangefreeitem0;
    }

    public Byte getControlrangefreeitem1() {
        return controlrangefreeitem1;
    }

    public void setControlrangefreeitem1(Byte controlrangefreeitem1) {
        this.controlrangefreeitem1 = controlrangefreeitem1;
    }

    public Byte getControlrangefreeitem2() {
        return controlrangefreeitem2;
    }

    public void setControlrangefreeitem2(Byte controlrangefreeitem2) {
        this.controlrangefreeitem2 = controlrangefreeitem2;
    }

    public Byte getControlrangefreeitem3() {
        return controlrangefreeitem3;
    }

    public void setControlrangefreeitem3(Byte controlrangefreeitem3) {
        this.controlrangefreeitem3 = controlrangefreeitem3;
    }

    public Byte getControlrangefreeitem4() {
        return controlrangefreeitem4;
    }

    public void setControlrangefreeitem4(Byte controlrangefreeitem4) {
        this.controlrangefreeitem4 = controlrangefreeitem4;
    }

    public Byte getControlrangefreeitem5() {
        return controlrangefreeitem5;
    }

    public void setControlrangefreeitem5(Byte controlrangefreeitem5) {
        this.controlrangefreeitem5 = controlrangefreeitem5;
    }

    public Byte getControlrangefreeitem6() {
        return controlrangefreeitem6;
    }

    public void setControlrangefreeitem6(Byte controlrangefreeitem6) {
        this.controlrangefreeitem6 = controlrangefreeitem6;
    }

    public Byte getControlrangefreeitem7() {
        return controlrangefreeitem7;
    }

    public void setControlrangefreeitem7(Byte controlrangefreeitem7) {
        this.controlrangefreeitem7 = controlrangefreeitem7;
    }

    public Byte getControlrangefreeitem8() {
        return controlrangefreeitem8;
    }

    public void setControlrangefreeitem8(Byte controlrangefreeitem8) {
        this.controlrangefreeitem8 = controlrangefreeitem8;
    }

    public Byte getControlrangefreeitem9() {
        return controlrangefreeitem9;
    }

    public void setControlrangefreeitem9(Byte controlrangefreeitem9) {
        this.controlrangefreeitem9 = controlrangefreeitem9;
    }

    public Byte getIslaborcost() {
        return islaborcost;
    }

    public void setIslaborcost(Byte islaborcost) {
        this.islaborcost = islaborcost;
    }

    public Long getBatchrunnumber() {
        return batchrunnumber;
    }

    public void setBatchrunnumber(Long batchrunnumber) {
        this.batchrunnumber = batchrunnumber;
    }

    public Byte getIsnew() {
        return isnew;
    }

    public void setIsnew(Byte isnew) {
        this.isnew = isnew;
    }

    public Date getMaderecorddate() {
        return maderecorddate;
    }

    public void setMaderecorddate(Date maderecorddate) {
        this.maderecorddate = maderecorddate;
    }

    public String getInventorydescript() {
        return inventorydescript;
    }

    public void setInventorydescript(String inventorydescript) {
        this.inventorydescript = inventorydescript == null ? null : inventorydescript.trim();
    }

    public Integer getRenewgoodselldays() {
        return renewgoodselldays;
    }

    public void setRenewgoodselldays(Integer renewgoodselldays) {
        this.renewgoodselldays = renewgoodselldays;
    }

    public Integer getRenewgoodaheaddays() {
        return renewgoodaheaddays;
    }

    public void setRenewgoodaheaddays(Integer renewgoodaheaddays) {
        this.renewgoodaheaddays = renewgoodaheaddays;
    }

    public Byte getIssuite() {
        return issuite;
    }

    public void setIssuite(Byte issuite) {
        this.issuite = issuite;
    }

    public Byte getIsweigh() {
        return isweigh;
    }

    public void setIsweigh(Byte isweigh) {
        this.isweigh = isweigh;
    }

    public String getDefaultbarcode() {
        return defaultbarcode;
    }

    public void setDefaultbarcode(String defaultbarcode) {
        this.defaultbarcode = defaultbarcode == null ? null : defaultbarcode.trim();
    }

    public Integer getNewproductperiod() {
        return newproductperiod;
    }

    public void setNewproductperiod(Integer newproductperiod) {
        this.newproductperiod = newproductperiod;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public Integer getIdbarcodesolution() {
        return idbarcodesolution;
    }

    public void setIdbarcodesolution(Integer idbarcodesolution) {
        this.idbarcodesolution = idbarcodesolution;
    }

    public Integer getIdinventoryclass() {
        return idinventoryclass;
    }

    public void setIdinventoryclass(Integer idinventoryclass) {
        this.idinventoryclass = idinventoryclass;
    }

    public Integer getIdinvlocation() {
        return idinvlocation;
    }

    public void setIdinvlocation(Integer idinvlocation) {
        this.idinvlocation = idinvlocation;
    }

    public Integer getIdmarketingorgan() {
        return idmarketingorgan;
    }

    public void setIdmarketingorgan(Integer idmarketingorgan) {
        this.idmarketingorgan = idmarketingorgan;
    }

    public Integer getIdpartner() {
        return idpartner;
    }

    public void setIdpartner(Integer idpartner) {
        this.idpartner = idpartner;
    }

    public Integer getIdunit() {
        return idunit;
    }

    public void setIdunit(Integer idunit) {
        this.idunit = idunit;
    }

    public Integer getIdunitbymanufacture() {
        return idunitbymanufacture;
    }

    public void setIdunitbymanufacture(Integer idunitbymanufacture) {
        this.idunitbymanufacture = idunitbymanufacture;
    }

    public Integer getIdunitbypurchase() {
        return idunitbypurchase;
    }

    public void setIdunitbypurchase(Integer idunitbypurchase) {
        this.idunitbypurchase = idunitbypurchase;
    }

    public Integer getIdunitbyretail() {
        return idunitbyretail;
    }

    public void setIdunitbyretail(Integer idunitbyretail) {
        this.idunitbyretail = idunitbyretail;
    }

    public Integer getIdunitbysale() {
        return idunitbysale;
    }

    public void setIdunitbysale(Integer idunitbysale) {
        this.idunitbysale = idunitbysale;
    }

    public Integer getIdunitbystock() {
        return idunitbystock;
    }

    public void setIdunitbystock(Integer idunitbystock) {
        this.idunitbystock = idunitbystock;
    }

    public Integer getIdunitgroup() {
        return idunitgroup;
    }

    public void setIdunitgroup(Integer idunitgroup) {
        this.idunitgroup = idunitgroup;
    }

    public Integer getIdsubunitbyreport() {
        return idsubunitbyreport;
    }

    public void setIdsubunitbyreport(Integer idsubunitbyreport) {
        this.idsubunitbyreport = idsubunitbyreport;
    }

    public Integer getExpiredunit() {
        return expiredunit;
    }

    public void setExpiredunit(Integer expiredunit) {
        this.expiredunit = expiredunit;
    }

    public Integer getIdwarehouse() {
        return idwarehouse;
    }

    public void setIdwarehouse(Integer idwarehouse) {
        this.idwarehouse = idwarehouse;
    }

    public Integer getCustomerreplenishmentrule() {
        return customerreplenishmentrule;
    }

    public void setCustomerreplenishmentrule(Integer customerreplenishmentrule) {
        this.customerreplenishmentrule = customerreplenishmentrule;
    }

    public Integer getPickbatchmethod() {
        return pickbatchmethod;
    }

    public void setPickbatchmethod(Integer pickbatchmethod) {
        this.pickbatchmethod = pickbatchmethod;
    }

    public Integer getPlanattribute() {
        return planattribute;
    }

    public void setPlanattribute(Integer planattribute) {
        this.planattribute = planattribute;
    }

    public Integer getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(Integer productinfo) {
        this.productinfo = productinfo;
    }

    public Integer getStorereplenishmentrule() {
        return storereplenishmentrule;
    }

    public void setStorereplenishmentrule(Integer storereplenishmentrule) {
        this.storereplenishmentrule = storereplenishmentrule;
    }

    public Integer getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Integer taxrate) {
        this.taxrate = taxrate;
    }

    public Integer getUnittype() {
        return unittype;
    }

    public void setUnittype(Integer unittype) {
        this.unittype = unittype;
    }

    public Integer getValuetype() {
        return valuetype;
    }

    public void setValuetype(Integer valuetype) {
        this.valuetype = valuetype;
    }

    public Date getMadedate() {
        return madedate;
    }

    public void setMadedate(Date madedate) {
        this.madedate = madedate;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getChanger() {
        return changer;
    }

    public void setChanger(String changer) {
        this.changer = changer == null ? null : changer.trim();
    }

    public Date getChangedate() {
        return changedate;
    }

    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    public String getJinshuicode() {
        return jinshuicode;
    }

    public void setJinshuicode(String jinshuicode) {
        this.jinshuicode = jinshuicode == null ? null : jinshuicode.trim();
    }

    public Byte getIsmodifiedcode() {
        return ismodifiedcode;
    }

    public void setIsmodifiedcode(Byte ismodifiedcode) {
        this.ismodifiedcode = ismodifiedcode;
    }

    public Byte getWithoutbargain() {
        return withoutbargain;
    }

    public void setWithoutbargain(Byte withoutbargain) {
        this.withoutbargain = withoutbargain;
    }

    public Integer getIdinventoryfreeitemclass0() {
        return idinventoryfreeitemclass0;
    }

    public void setIdinventoryfreeitemclass0(Integer idinventoryfreeitemclass0) {
        this.idinventoryfreeitemclass0 = idinventoryfreeitemclass0;
    }

    public Integer getIdinventoryfreeitemclass1() {
        return idinventoryfreeitemclass1;
    }

    public void setIdinventoryfreeitemclass1(Integer idinventoryfreeitemclass1) {
        this.idinventoryfreeitemclass1 = idinventoryfreeitemclass1;
    }

    public Integer getIdinventoryfreeitemclass2() {
        return idinventoryfreeitemclass2;
    }

    public void setIdinventoryfreeitemclass2(Integer idinventoryfreeitemclass2) {
        this.idinventoryfreeitemclass2 = idinventoryfreeitemclass2;
    }

    public Integer getIdinventoryfreeitemclass3() {
        return idinventoryfreeitemclass3;
    }

    public void setIdinventoryfreeitemclass3(Integer idinventoryfreeitemclass3) {
        this.idinventoryfreeitemclass3 = idinventoryfreeitemclass3;
    }

    public Integer getIdinventoryfreeitemclass4() {
        return idinventoryfreeitemclass4;
    }

    public void setIdinventoryfreeitemclass4(Integer idinventoryfreeitemclass4) {
        this.idinventoryfreeitemclass4 = idinventoryfreeitemclass4;
    }

    public Integer getIdinventoryfreeitemclass5() {
        return idinventoryfreeitemclass5;
    }

    public void setIdinventoryfreeitemclass5(Integer idinventoryfreeitemclass5) {
        this.idinventoryfreeitemclass5 = idinventoryfreeitemclass5;
    }

    public Integer getIdinventoryfreeitemclass6() {
        return idinventoryfreeitemclass6;
    }

    public void setIdinventoryfreeitemclass6(Integer idinventoryfreeitemclass6) {
        this.idinventoryfreeitemclass6 = idinventoryfreeitemclass6;
    }

    public Integer getIdinventoryfreeitemclass7() {
        return idinventoryfreeitemclass7;
    }

    public void setIdinventoryfreeitemclass7(Integer idinventoryfreeitemclass7) {
        this.idinventoryfreeitemclass7 = idinventoryfreeitemclass7;
    }

    public Integer getIdinventoryfreeitemclass8() {
        return idinventoryfreeitemclass8;
    }

    public void setIdinventoryfreeitemclass8(Integer idinventoryfreeitemclass8) {
        this.idinventoryfreeitemclass8 = idinventoryfreeitemclass8;
    }

    public Integer getIdinventoryfreeitemclass9() {
        return idinventoryfreeitemclass9;
    }

    public void setIdinventoryfreeitemclass9(Integer idinventoryfreeitemclass9) {
        this.idinventoryfreeitemclass9 = idinventoryfreeitemclass9;
    }

    public BigDecimal getInstorageuplimit() {
        return instorageuplimit;
    }

    public void setInstorageuplimit(BigDecimal instorageuplimit) {
        this.instorageuplimit = instorageuplimit;
    }

    public BigDecimal getOutstorageuplimit() {
        return outstorageuplimit;
    }

    public void setOutstorageuplimit(BigDecimal outstorageuplimit) {
        this.outstorageuplimit = outstorageuplimit;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}