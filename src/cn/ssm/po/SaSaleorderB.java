package cn.ssm.po;

import java.math.BigDecimal;
import java.util.Date;

public class SaSaleorderB {
    private Integer id;

    private String code;

    private BigDecimal quantity;

    private BigDecimal quantity2;

    private BigDecimal basequantity;

    private BigDecimal subquantity;

    private String compositionquantity;

    private BigDecimal origprice;

    private BigDecimal price;

    private BigDecimal baseprice;

    private BigDecimal discountrate;

    private BigDecimal origdiscountprice;

    private BigDecimal discountprice;

    private BigDecimal basediscountprice;

    private BigDecimal taxrate;

    private BigDecimal origtaxprice;

    private BigDecimal taxprice;

    private BigDecimal basetaxprice;

    private BigDecimal origdiscountamount;

    private BigDecimal discountamount;

    private BigDecimal origtax;

    private BigDecimal tax;

    private BigDecimal origtaxamount;

    private BigDecimal taxamount;

    private Byte ispresent;

    private BigDecimal manufacturequantity;

    private BigDecimal manufacturequantity2;

    private BigDecimal purchasequantity;

    private BigDecimal purchasequantity2;

    private BigDecimal deliveryquantity;

    private BigDecimal deliveryquantity2;

    private BigDecimal saleoutquantity;

    private BigDecimal saleoutquantity2;

    private BigDecimal executedquantity;

    private BigDecimal executedquantity2;

    private BigDecimal unitexchangerate;

    private BigDecimal origdiscount;

    private BigDecimal discount;

    private Byte taxflag;

    private String inventorybarcode;

    private String partnerinventorycode;

    private String updatedby;

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

    private String priuserdefnvc1;

    private BigDecimal priuserdefdecm1;

    private String priuserdefnvc2;

    private BigDecimal priuserdefdecm2;

    private String priuserdefnvc3;

    private BigDecimal priuserdefdecm3;

    private String priuserdefnvc4;

    private BigDecimal priuserdefdecm4;

    private String pubuserdefnvc1;

    private BigDecimal pubuserdefdecm1;

    private String pubuserdefnvc2;

    private BigDecimal pubuserdefdecm2;

    private String pubuserdefnvc3;

    private BigDecimal pubuserdefdecm3;

    private String pubuserdefnvc4;

    private BigDecimal pubuserdefdecm4;

    private String partnerinventoryname;

    private Byte haspra;

    private Integer prarequiretimes;

    private Byte hasorderbom;

    private String isnomodify;

    private String sourcevouchercode;

    private String detailvoucherstate;

    private BigDecimal retailprice;

    private BigDecimal distributionquantity;

    private BigDecimal distributionquantity2;

    private String pricestrategytypename;

    private String pricestrategyschemeids;

    private String pricestrategyschemenames;

    private String promotionvouchercodes;

    private String promotionvoucherids;

    private Byte ismemberintegral;

    private Byte ispromotionpresent;

    private String promotionpresentvouchercode;

    private String promotionsinglevouchercode;

    private String promotionpresentgroupid;

    private String promotionsinglegroupid;

    private String detailmemo;

    private Integer idbom;

    private Integer idinventory;

    private Integer idproject;

    private Integer idbaseunit;

    private Integer idsubunit;

    private Integer idunit;

    private Integer idunit2;

    private Integer idwarehouse;

    private Integer cashbackway;

    private Integer pricestrategytypeid;

    private Integer promotionpresenttypeid;

    private Integer promotionsingletypeid;

    private Integer promotionpresentvoucherid;

    private Integer promotionsinglevoucherid;

    private Integer idsaleorderdto;

    private Integer sourcevoucherid;

    private Integer sourcevoucherdetailid;

    private Integer iddestvouchertype;

    private Integer idsourcevouchertype;

    private Date deliverydate;

    private Date updated;

    private BigDecimal latestcost;

    private BigDecimal latestporigtaxprice;

    private BigDecimal latestsaleorigtaxprice;

    private BigDecimal lowestsaleprice;

    private BigDecimal singleinvgrossprofit;

    private BigDecimal grossprofit;

    private BigDecimal grossprofitrate;

    private Integer datasource;

    private Byte isclose;

    private Byte ismodifiedprice;

    private String closer;

    private Date closedate;

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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(BigDecimal quantity2) {
        this.quantity2 = quantity2;
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

    public String getCompositionquantity() {
        return compositionquantity;
    }

    public void setCompositionquantity(String compositionquantity) {
        this.compositionquantity = compositionquantity == null ? null : compositionquantity.trim();
    }

    public BigDecimal getOrigprice() {
        return origprice;
    }

    public void setOrigprice(BigDecimal origprice) {
        this.origprice = origprice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(BigDecimal baseprice) {
        this.baseprice = baseprice;
    }

    public BigDecimal getDiscountrate() {
        return discountrate;
    }

    public void setDiscountrate(BigDecimal discountrate) {
        this.discountrate = discountrate;
    }

    public BigDecimal getOrigdiscountprice() {
        return origdiscountprice;
    }

    public void setOrigdiscountprice(BigDecimal origdiscountprice) {
        this.origdiscountprice = origdiscountprice;
    }

    public BigDecimal getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(BigDecimal discountprice) {
        this.discountprice = discountprice;
    }

    public BigDecimal getBasediscountprice() {
        return basediscountprice;
    }

    public void setBasediscountprice(BigDecimal basediscountprice) {
        this.basediscountprice = basediscountprice;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public BigDecimal getOrigtaxprice() {
        return origtaxprice;
    }

    public void setOrigtaxprice(BigDecimal origtaxprice) {
        this.origtaxprice = origtaxprice;
    }

    public BigDecimal getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(BigDecimal taxprice) {
        this.taxprice = taxprice;
    }

    public BigDecimal getBasetaxprice() {
        return basetaxprice;
    }

    public void setBasetaxprice(BigDecimal basetaxprice) {
        this.basetaxprice = basetaxprice;
    }

    public BigDecimal getOrigdiscountamount() {
        return origdiscountamount;
    }

    public void setOrigdiscountamount(BigDecimal origdiscountamount) {
        this.origdiscountamount = origdiscountamount;
    }

    public BigDecimal getDiscountamount() {
        return discountamount;
    }

    public void setDiscountamount(BigDecimal discountamount) {
        this.discountamount = discountamount;
    }

    public BigDecimal getOrigtax() {
        return origtax;
    }

    public void setOrigtax(BigDecimal origtax) {
        this.origtax = origtax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getOrigtaxamount() {
        return origtaxamount;
    }

    public void setOrigtaxamount(BigDecimal origtaxamount) {
        this.origtaxamount = origtaxamount;
    }

    public BigDecimal getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(BigDecimal taxamount) {
        this.taxamount = taxamount;
    }

    public Byte getIspresent() {
        return ispresent;
    }

    public void setIspresent(Byte ispresent) {
        this.ispresent = ispresent;
    }

    public BigDecimal getManufacturequantity() {
        return manufacturequantity;
    }

    public void setManufacturequantity(BigDecimal manufacturequantity) {
        this.manufacturequantity = manufacturequantity;
    }

    public BigDecimal getManufacturequantity2() {
        return manufacturequantity2;
    }

    public void setManufacturequantity2(BigDecimal manufacturequantity2) {
        this.manufacturequantity2 = manufacturequantity2;
    }

    public BigDecimal getPurchasequantity() {
        return purchasequantity;
    }

    public void setPurchasequantity(BigDecimal purchasequantity) {
        this.purchasequantity = purchasequantity;
    }

    public BigDecimal getPurchasequantity2() {
        return purchasequantity2;
    }

    public void setPurchasequantity2(BigDecimal purchasequantity2) {
        this.purchasequantity2 = purchasequantity2;
    }

    public BigDecimal getDeliveryquantity() {
        return deliveryquantity;
    }

    public void setDeliveryquantity(BigDecimal deliveryquantity) {
        this.deliveryquantity = deliveryquantity;
    }

    public BigDecimal getDeliveryquantity2() {
        return deliveryquantity2;
    }

    public void setDeliveryquantity2(BigDecimal deliveryquantity2) {
        this.deliveryquantity2 = deliveryquantity2;
    }

    public BigDecimal getSaleoutquantity() {
        return saleoutquantity;
    }

    public void setSaleoutquantity(BigDecimal saleoutquantity) {
        this.saleoutquantity = saleoutquantity;
    }

    public BigDecimal getSaleoutquantity2() {
        return saleoutquantity2;
    }

    public void setSaleoutquantity2(BigDecimal saleoutquantity2) {
        this.saleoutquantity2 = saleoutquantity2;
    }

    public BigDecimal getExecutedquantity() {
        return executedquantity;
    }

    public void setExecutedquantity(BigDecimal executedquantity) {
        this.executedquantity = executedquantity;
    }

    public BigDecimal getExecutedquantity2() {
        return executedquantity2;
    }

    public void setExecutedquantity2(BigDecimal executedquantity2) {
        this.executedquantity2 = executedquantity2;
    }

    public BigDecimal getUnitexchangerate() {
        return unitexchangerate;
    }

    public void setUnitexchangerate(BigDecimal unitexchangerate) {
        this.unitexchangerate = unitexchangerate;
    }

    public BigDecimal getOrigdiscount() {
        return origdiscount;
    }

    public void setOrigdiscount(BigDecimal origdiscount) {
        this.origdiscount = origdiscount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Byte getTaxflag() {
        return taxflag;
    }

    public void setTaxflag(Byte taxflag) {
        this.taxflag = taxflag;
    }

    public String getInventorybarcode() {
        return inventorybarcode;
    }

    public void setInventorybarcode(String inventorybarcode) {
        this.inventorybarcode = inventorybarcode == null ? null : inventorybarcode.trim();
    }

    public String getPartnerinventorycode() {
        return partnerinventorycode;
    }

    public void setPartnerinventorycode(String partnerinventorycode) {
        this.partnerinventorycode = partnerinventorycode == null ? null : partnerinventorycode.trim();
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby == null ? null : updatedby.trim();
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

    public String getPubuserdefnvc1() {
        return pubuserdefnvc1;
    }

    public void setPubuserdefnvc1(String pubuserdefnvc1) {
        this.pubuserdefnvc1 = pubuserdefnvc1 == null ? null : pubuserdefnvc1.trim();
    }

    public BigDecimal getPubuserdefdecm1() {
        return pubuserdefdecm1;
    }

    public void setPubuserdefdecm1(BigDecimal pubuserdefdecm1) {
        this.pubuserdefdecm1 = pubuserdefdecm1;
    }

    public String getPubuserdefnvc2() {
        return pubuserdefnvc2;
    }

    public void setPubuserdefnvc2(String pubuserdefnvc2) {
        this.pubuserdefnvc2 = pubuserdefnvc2 == null ? null : pubuserdefnvc2.trim();
    }

    public BigDecimal getPubuserdefdecm2() {
        return pubuserdefdecm2;
    }

    public void setPubuserdefdecm2(BigDecimal pubuserdefdecm2) {
        this.pubuserdefdecm2 = pubuserdefdecm2;
    }

    public String getPubuserdefnvc3() {
        return pubuserdefnvc3;
    }

    public void setPubuserdefnvc3(String pubuserdefnvc3) {
        this.pubuserdefnvc3 = pubuserdefnvc3 == null ? null : pubuserdefnvc3.trim();
    }

    public BigDecimal getPubuserdefdecm3() {
        return pubuserdefdecm3;
    }

    public void setPubuserdefdecm3(BigDecimal pubuserdefdecm3) {
        this.pubuserdefdecm3 = pubuserdefdecm3;
    }

    public String getPubuserdefnvc4() {
        return pubuserdefnvc4;
    }

    public void setPubuserdefnvc4(String pubuserdefnvc4) {
        this.pubuserdefnvc4 = pubuserdefnvc4 == null ? null : pubuserdefnvc4.trim();
    }

    public BigDecimal getPubuserdefdecm4() {
        return pubuserdefdecm4;
    }

    public void setPubuserdefdecm4(BigDecimal pubuserdefdecm4) {
        this.pubuserdefdecm4 = pubuserdefdecm4;
    }

    public String getPartnerinventoryname() {
        return partnerinventoryname;
    }

    public void setPartnerinventoryname(String partnerinventoryname) {
        this.partnerinventoryname = partnerinventoryname == null ? null : partnerinventoryname.trim();
    }

    public Byte getHaspra() {
        return haspra;
    }

    public void setHaspra(Byte haspra) {
        this.haspra = haspra;
    }

    public Integer getPrarequiretimes() {
        return prarequiretimes;
    }

    public void setPrarequiretimes(Integer prarequiretimes) {
        this.prarequiretimes = prarequiretimes;
    }

    public Byte getHasorderbom() {
        return hasorderbom;
    }

    public void setHasorderbom(Byte hasorderbom) {
        this.hasorderbom = hasorderbom;
    }

    public String getIsnomodify() {
        return isnomodify;
    }

    public void setIsnomodify(String isnomodify) {
        this.isnomodify = isnomodify == null ? null : isnomodify.trim();
    }

    public String getSourcevouchercode() {
        return sourcevouchercode;
    }

    public void setSourcevouchercode(String sourcevouchercode) {
        this.sourcevouchercode = sourcevouchercode == null ? null : sourcevouchercode.trim();
    }

    public String getDetailvoucherstate() {
        return detailvoucherstate;
    }

    public void setDetailvoucherstate(String detailvoucherstate) {
        this.detailvoucherstate = detailvoucherstate == null ? null : detailvoucherstate.trim();
    }

    public BigDecimal getRetailprice() {
        return retailprice;
    }

    public void setRetailprice(BigDecimal retailprice) {
        this.retailprice = retailprice;
    }

    public BigDecimal getDistributionquantity() {
        return distributionquantity;
    }

    public void setDistributionquantity(BigDecimal distributionquantity) {
        this.distributionquantity = distributionquantity;
    }

    public BigDecimal getDistributionquantity2() {
        return distributionquantity2;
    }

    public void setDistributionquantity2(BigDecimal distributionquantity2) {
        this.distributionquantity2 = distributionquantity2;
    }

    public String getPricestrategytypename() {
        return pricestrategytypename;
    }

    public void setPricestrategytypename(String pricestrategytypename) {
        this.pricestrategytypename = pricestrategytypename == null ? null : pricestrategytypename.trim();
    }

    public String getPricestrategyschemeids() {
        return pricestrategyschemeids;
    }

    public void setPricestrategyschemeids(String pricestrategyschemeids) {
        this.pricestrategyschemeids = pricestrategyschemeids == null ? null : pricestrategyschemeids.trim();
    }

    public String getPricestrategyschemenames() {
        return pricestrategyschemenames;
    }

    public void setPricestrategyschemenames(String pricestrategyschemenames) {
        this.pricestrategyschemenames = pricestrategyschemenames == null ? null : pricestrategyschemenames.trim();
    }

    public String getPromotionvouchercodes() {
        return promotionvouchercodes;
    }

    public void setPromotionvouchercodes(String promotionvouchercodes) {
        this.promotionvouchercodes = promotionvouchercodes == null ? null : promotionvouchercodes.trim();
    }

    public String getPromotionvoucherids() {
        return promotionvoucherids;
    }

    public void setPromotionvoucherids(String promotionvoucherids) {
        this.promotionvoucherids = promotionvoucherids == null ? null : promotionvoucherids.trim();
    }

    public Byte getIsmemberintegral() {
        return ismemberintegral;
    }

    public void setIsmemberintegral(Byte ismemberintegral) {
        this.ismemberintegral = ismemberintegral;
    }

    public Byte getIspromotionpresent() {
        return ispromotionpresent;
    }

    public void setIspromotionpresent(Byte ispromotionpresent) {
        this.ispromotionpresent = ispromotionpresent;
    }

    public String getPromotionpresentvouchercode() {
        return promotionpresentvouchercode;
    }

    public void setPromotionpresentvouchercode(String promotionpresentvouchercode) {
        this.promotionpresentvouchercode = promotionpresentvouchercode == null ? null : promotionpresentvouchercode.trim();
    }

    public String getPromotionsinglevouchercode() {
        return promotionsinglevouchercode;
    }

    public void setPromotionsinglevouchercode(String promotionsinglevouchercode) {
        this.promotionsinglevouchercode = promotionsinglevouchercode == null ? null : promotionsinglevouchercode.trim();
    }

    public String getPromotionpresentgroupid() {
        return promotionpresentgroupid;
    }

    public void setPromotionpresentgroupid(String promotionpresentgroupid) {
        this.promotionpresentgroupid = promotionpresentgroupid == null ? null : promotionpresentgroupid.trim();
    }

    public String getPromotionsinglegroupid() {
        return promotionsinglegroupid;
    }

    public void setPromotionsinglegroupid(String promotionsinglegroupid) {
        this.promotionsinglegroupid = promotionsinglegroupid == null ? null : promotionsinglegroupid.trim();
    }

    public String getDetailmemo() {
        return detailmemo;
    }

    public void setDetailmemo(String detailmemo) {
        this.detailmemo = detailmemo == null ? null : detailmemo.trim();
    }

    public Integer getIdbom() {
        return idbom;
    }

    public void setIdbom(Integer idbom) {
        this.idbom = idbom;
    }

    public Integer getIdinventory() {
        return idinventory;
    }

    public void setIdinventory(Integer idinventory) {
        this.idinventory = idinventory;
    }

    public Integer getIdproject() {
        return idproject;
    }

    public void setIdproject(Integer idproject) {
        this.idproject = idproject;
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

    public Integer getIdunit() {
        return idunit;
    }

    public void setIdunit(Integer idunit) {
        this.idunit = idunit;
    }

    public Integer getIdunit2() {
        return idunit2;
    }

    public void setIdunit2(Integer idunit2) {
        this.idunit2 = idunit2;
    }

    public Integer getIdwarehouse() {
        return idwarehouse;
    }

    public void setIdwarehouse(Integer idwarehouse) {
        this.idwarehouse = idwarehouse;
    }

    public Integer getCashbackway() {
        return cashbackway;
    }

    public void setCashbackway(Integer cashbackway) {
        this.cashbackway = cashbackway;
    }

    public Integer getPricestrategytypeid() {
        return pricestrategytypeid;
    }

    public void setPricestrategytypeid(Integer pricestrategytypeid) {
        this.pricestrategytypeid = pricestrategytypeid;
    }

    public Integer getPromotionpresenttypeid() {
        return promotionpresenttypeid;
    }

    public void setPromotionpresenttypeid(Integer promotionpresenttypeid) {
        this.promotionpresenttypeid = promotionpresenttypeid;
    }

    public Integer getPromotionsingletypeid() {
        return promotionsingletypeid;
    }

    public void setPromotionsingletypeid(Integer promotionsingletypeid) {
        this.promotionsingletypeid = promotionsingletypeid;
    }

    public Integer getPromotionpresentvoucherid() {
        return promotionpresentvoucherid;
    }

    public void setPromotionpresentvoucherid(Integer promotionpresentvoucherid) {
        this.promotionpresentvoucherid = promotionpresentvoucherid;
    }

    public Integer getPromotionsinglevoucherid() {
        return promotionsinglevoucherid;
    }

    public void setPromotionsinglevoucherid(Integer promotionsinglevoucherid) {
        this.promotionsinglevoucherid = promotionsinglevoucherid;
    }

    public Integer getIdsaleorderdto() {
        return idsaleorderdto;
    }

    public void setIdsaleorderdto(Integer idsaleorderdto) {
        this.idsaleorderdto = idsaleorderdto;
    }

    public Integer getSourcevoucherid() {
        return sourcevoucherid;
    }

    public void setSourcevoucherid(Integer sourcevoucherid) {
        this.sourcevoucherid = sourcevoucherid;
    }

    public Integer getSourcevoucherdetailid() {
        return sourcevoucherdetailid;
    }

    public void setSourcevoucherdetailid(Integer sourcevoucherdetailid) {
        this.sourcevoucherdetailid = sourcevoucherdetailid;
    }

    public Integer getIddestvouchertype() {
        return iddestvouchertype;
    }

    public void setIddestvouchertype(Integer iddestvouchertype) {
        this.iddestvouchertype = iddestvouchertype;
    }

    public Integer getIdsourcevouchertype() {
        return idsourcevouchertype;
    }

    public void setIdsourcevouchertype(Integer idsourcevouchertype) {
        this.idsourcevouchertype = idsourcevouchertype;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public BigDecimal getLatestcost() {
        return latestcost;
    }

    public void setLatestcost(BigDecimal latestcost) {
        this.latestcost = latestcost;
    }

    public BigDecimal getLatestporigtaxprice() {
        return latestporigtaxprice;
    }

    public void setLatestporigtaxprice(BigDecimal latestporigtaxprice) {
        this.latestporigtaxprice = latestporigtaxprice;
    }

    public BigDecimal getLatestsaleorigtaxprice() {
        return latestsaleorigtaxprice;
    }

    public void setLatestsaleorigtaxprice(BigDecimal latestsaleorigtaxprice) {
        this.latestsaleorigtaxprice = latestsaleorigtaxprice;
    }

    public BigDecimal getLowestsaleprice() {
        return lowestsaleprice;
    }

    public void setLowestsaleprice(BigDecimal lowestsaleprice) {
        this.lowestsaleprice = lowestsaleprice;
    }

    public BigDecimal getSingleinvgrossprofit() {
        return singleinvgrossprofit;
    }

    public void setSingleinvgrossprofit(BigDecimal singleinvgrossprofit) {
        this.singleinvgrossprofit = singleinvgrossprofit;
    }

    public BigDecimal getGrossprofit() {
        return grossprofit;
    }

    public void setGrossprofit(BigDecimal grossprofit) {
        this.grossprofit = grossprofit;
    }

    public BigDecimal getGrossprofitrate() {
        return grossprofitrate;
    }

    public void setGrossprofitrate(BigDecimal grossprofitrate) {
        this.grossprofitrate = grossprofitrate;
    }

    public Integer getDatasource() {
        return datasource;
    }

    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
    }

    public Byte getIsclose() {
        return isclose;
    }

    public void setIsclose(Byte isclose) {
        this.isclose = isclose;
    }

    public Byte getIsmodifiedprice() {
        return ismodifiedprice;
    }

    public void setIsmodifiedprice(Byte ismodifiedprice) {
        this.ismodifiedprice = ismodifiedprice;
    }

    public String getCloser() {
        return closer;
    }

    public void setCloser(String closer) {
        this.closer = closer == null ? null : closer.trim();
    }

    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}