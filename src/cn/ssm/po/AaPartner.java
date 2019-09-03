package cn.ssm.po;

import java.math.BigDecimal;
import java.util.Date;

public class AaPartner {
    private Integer id;

    private String code;

    private String name;

    private String partnerabbname;

    private String shorthand;

    private String representative;

    private String bankaccount;

    private String taxregcode;

    private BigDecimal salecreditline;

    private BigDecimal salecreditdays;

    private BigDecimal purchasecreditdays;

    private Integer salespacemonth;

    private Integer salecheckmonth;

    private Integer salecheckdate;

    private Integer purchasespacemonth;

    private Integer purchasecheckmonth;

    private Integer purchasecheckdate;

    private String customeraddressphone;

    private BigDecimal arbalanceAbandon;

    private BigDecimal apbalanceAbandon;

    private Byte disabled;

    private String updatedby;

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

    private Byte iscontaintaxonpurchase;

    private String haseverchanged;

    private String codesettlementpartner;

    private BigDecimal advrbalanceAbandon;

    private BigDecimal advpbalanceAbandon;

    private String addressjc;

    private String shipmentaddress;

    private String contact;

    private String mobilephone;

    private String telephoneno;

    private String fax;

    private String emailaddr;

    private String qqno;

    private String msnaddress;

    private String uuno;

    private BigDecimal creditbalance;

    private BigDecimal extendedaccounts;

    private Byte sellcustomer;

    private Date maderecorddate;

    private String position;

    private Byte runshop;

    private String checkaddress;

    private String customeraddress;

    private Date birthday;

    private Byte autocreatemember;

    private Integer idsaledepartment;

    private Integer iddistrict;

    private Integer idmarketingorgan;

    private Integer idpmarketingorgan;

    private Integer idsettlementpartner;

    private Integer idpartnerclass;

    private Integer idsaleman;

    private Integer idmembertype;

    private Integer accbank;

    private Integer customertype;

    private Integer partnertype;

    private Integer pricegrade;

    private Integer purchasesettlestyle;

    private Integer salesettlestyle;

    private Integer taxrate;

    private Date salestartdate;

    private Date purchasestartdate;

    private Date madedate;

    private Date updated;

    private Date createdtime;

    private String eaccount;

    private Byte ismodifiedcode;

    private Integer electronicinvoicereceivemode;

    private String electronicinvoicereceiveemail;

    private String electronicinvoicereceivemobilephone;

    private Integer defaultinvoicetypeofreceive;

    private Integer iddefaultsettlestyleofreceive;

    private Integer iddefaultbankaccountofreceive;

    private Integer defaultinvoicetypeofpayment;

    private Integer iddefaultsettlestyleofpayment;

    private Integer iddefaultbankaccountofpayment;

    private Byte isautocancel;

    private Byte issystem;

    private String visitaddress;

    private String coordinate;

    private String longitude;

    private String latitude;

    private BigDecimal checkinscope;

    private BigDecimal visitfrequency;

    private Byte isneedcheckinphoto;

    private Byte isneedlyhuophoto;

    private Byte isneedpromotionphoto;

    private Byte isneedcompetitorphoto;

    private Integer printcount;

    private String creater;

    private String changer;

    private Date changedate;

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

    public String getPartnerabbname() {
        return partnerabbname;
    }

    public void setPartnerabbname(String partnerabbname) {
        this.partnerabbname = partnerabbname == null ? null : partnerabbname.trim();
    }

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand == null ? null : shorthand.trim();
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative == null ? null : representative.trim();
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount == null ? null : bankaccount.trim();
    }

    public String getTaxregcode() {
        return taxregcode;
    }

    public void setTaxregcode(String taxregcode) {
        this.taxregcode = taxregcode == null ? null : taxregcode.trim();
    }

    public BigDecimal getSalecreditline() {
        return salecreditline;
    }

    public void setSalecreditline(BigDecimal salecreditline) {
        this.salecreditline = salecreditline;
    }

    public BigDecimal getSalecreditdays() {
        return salecreditdays;
    }

    public void setSalecreditdays(BigDecimal salecreditdays) {
        this.salecreditdays = salecreditdays;
    }

    public BigDecimal getPurchasecreditdays() {
        return purchasecreditdays;
    }

    public void setPurchasecreditdays(BigDecimal purchasecreditdays) {
        this.purchasecreditdays = purchasecreditdays;
    }

    public Integer getSalespacemonth() {
        return salespacemonth;
    }

    public void setSalespacemonth(Integer salespacemonth) {
        this.salespacemonth = salespacemonth;
    }

    public Integer getSalecheckmonth() {
        return salecheckmonth;
    }

    public void setSalecheckmonth(Integer salecheckmonth) {
        this.salecheckmonth = salecheckmonth;
    }

    public Integer getSalecheckdate() {
        return salecheckdate;
    }

    public void setSalecheckdate(Integer salecheckdate) {
        this.salecheckdate = salecheckdate;
    }

    public Integer getPurchasespacemonth() {
        return purchasespacemonth;
    }

    public void setPurchasespacemonth(Integer purchasespacemonth) {
        this.purchasespacemonth = purchasespacemonth;
    }

    public Integer getPurchasecheckmonth() {
        return purchasecheckmonth;
    }

    public void setPurchasecheckmonth(Integer purchasecheckmonth) {
        this.purchasecheckmonth = purchasecheckmonth;
    }

    public Integer getPurchasecheckdate() {
        return purchasecheckdate;
    }

    public void setPurchasecheckdate(Integer purchasecheckdate) {
        this.purchasecheckdate = purchasecheckdate;
    }

    public String getCustomeraddressphone() {
        return customeraddressphone;
    }

    public void setCustomeraddressphone(String customeraddressphone) {
        this.customeraddressphone = customeraddressphone == null ? null : customeraddressphone.trim();
    }

    public BigDecimal getArbalanceAbandon() {
        return arbalanceAbandon;
    }

    public void setArbalanceAbandon(BigDecimal arbalanceAbandon) {
        this.arbalanceAbandon = arbalanceAbandon;
    }

    public BigDecimal getApbalanceAbandon() {
        return apbalanceAbandon;
    }

    public void setApbalanceAbandon(BigDecimal apbalanceAbandon) {
        this.apbalanceAbandon = apbalanceAbandon;
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby == null ? null : updatedby.trim();
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

    public Byte getIscontaintaxonpurchase() {
        return iscontaintaxonpurchase;
    }

    public void setIscontaintaxonpurchase(Byte iscontaintaxonpurchase) {
        this.iscontaintaxonpurchase = iscontaintaxonpurchase;
    }

    public String getHaseverchanged() {
        return haseverchanged;
    }

    public void setHaseverchanged(String haseverchanged) {
        this.haseverchanged = haseverchanged == null ? null : haseverchanged.trim();
    }

    public String getCodesettlementpartner() {
        return codesettlementpartner;
    }

    public void setCodesettlementpartner(String codesettlementpartner) {
        this.codesettlementpartner = codesettlementpartner == null ? null : codesettlementpartner.trim();
    }

    public BigDecimal getAdvrbalanceAbandon() {
        return advrbalanceAbandon;
    }

    public void setAdvrbalanceAbandon(BigDecimal advrbalanceAbandon) {
        this.advrbalanceAbandon = advrbalanceAbandon;
    }

    public BigDecimal getAdvpbalanceAbandon() {
        return advpbalanceAbandon;
    }

    public void setAdvpbalanceAbandon(BigDecimal advpbalanceAbandon) {
        this.advpbalanceAbandon = advpbalanceAbandon;
    }

    public String getAddressjc() {
        return addressjc;
    }

    public void setAddressjc(String addressjc) {
        this.addressjc = addressjc == null ? null : addressjc.trim();
    }

    public String getShipmentaddress() {
        return shipmentaddress;
    }

    public void setShipmentaddress(String shipmentaddress) {
        this.shipmentaddress = shipmentaddress == null ? null : shipmentaddress.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
    }

    public String getTelephoneno() {
        return telephoneno;
    }

    public void setTelephoneno(String telephoneno) {
        this.telephoneno = telephoneno == null ? null : telephoneno.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmailaddr() {
        return emailaddr;
    }

    public void setEmailaddr(String emailaddr) {
        this.emailaddr = emailaddr == null ? null : emailaddr.trim();
    }

    public String getQqno() {
        return qqno;
    }

    public void setQqno(String qqno) {
        this.qqno = qqno == null ? null : qqno.trim();
    }

    public String getMsnaddress() {
        return msnaddress;
    }

    public void setMsnaddress(String msnaddress) {
        this.msnaddress = msnaddress == null ? null : msnaddress.trim();
    }

    public String getUuno() {
        return uuno;
    }

    public void setUuno(String uuno) {
        this.uuno = uuno == null ? null : uuno.trim();
    }

    public BigDecimal getCreditbalance() {
        return creditbalance;
    }

    public void setCreditbalance(BigDecimal creditbalance) {
        this.creditbalance = creditbalance;
    }

    public BigDecimal getExtendedaccounts() {
        return extendedaccounts;
    }

    public void setExtendedaccounts(BigDecimal extendedaccounts) {
        this.extendedaccounts = extendedaccounts;
    }

    public Byte getSellcustomer() {
        return sellcustomer;
    }

    public void setSellcustomer(Byte sellcustomer) {
        this.sellcustomer = sellcustomer;
    }

    public Date getMaderecorddate() {
        return maderecorddate;
    }

    public void setMaderecorddate(Date maderecorddate) {
        this.maderecorddate = maderecorddate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Byte getRunshop() {
        return runshop;
    }

    public void setRunshop(Byte runshop) {
        this.runshop = runshop;
    }

    public String getCheckaddress() {
        return checkaddress;
    }

    public void setCheckaddress(String checkaddress) {
        this.checkaddress = checkaddress == null ? null : checkaddress.trim();
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress == null ? null : customeraddress.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Byte getAutocreatemember() {
        return autocreatemember;
    }

    public void setAutocreatemember(Byte autocreatemember) {
        this.autocreatemember = autocreatemember;
    }

    public Integer getIdsaledepartment() {
        return idsaledepartment;
    }

    public void setIdsaledepartment(Integer idsaledepartment) {
        this.idsaledepartment = idsaledepartment;
    }

    public Integer getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(Integer iddistrict) {
        this.iddistrict = iddistrict;
    }

    public Integer getIdmarketingorgan() {
        return idmarketingorgan;
    }

    public void setIdmarketingorgan(Integer idmarketingorgan) {
        this.idmarketingorgan = idmarketingorgan;
    }

    public Integer getIdpmarketingorgan() {
        return idpmarketingorgan;
    }

    public void setIdpmarketingorgan(Integer idpmarketingorgan) {
        this.idpmarketingorgan = idpmarketingorgan;
    }

    public Integer getIdsettlementpartner() {
        return idsettlementpartner;
    }

    public void setIdsettlementpartner(Integer idsettlementpartner) {
        this.idsettlementpartner = idsettlementpartner;
    }

    public Integer getIdpartnerclass() {
        return idpartnerclass;
    }

    public void setIdpartnerclass(Integer idpartnerclass) {
        this.idpartnerclass = idpartnerclass;
    }

    public Integer getIdsaleman() {
        return idsaleman;
    }

    public void setIdsaleman(Integer idsaleman) {
        this.idsaleman = idsaleman;
    }

    public Integer getIdmembertype() {
        return idmembertype;
    }

    public void setIdmembertype(Integer idmembertype) {
        this.idmembertype = idmembertype;
    }

    public Integer getAccbank() {
        return accbank;
    }

    public void setAccbank(Integer accbank) {
        this.accbank = accbank;
    }

    public Integer getCustomertype() {
        return customertype;
    }

    public void setCustomertype(Integer customertype) {
        this.customertype = customertype;
    }

    public Integer getPartnertype() {
        return partnertype;
    }

    public void setPartnertype(Integer partnertype) {
        this.partnertype = partnertype;
    }

    public Integer getPricegrade() {
        return pricegrade;
    }

    public void setPricegrade(Integer pricegrade) {
        this.pricegrade = pricegrade;
    }

    public Integer getPurchasesettlestyle() {
        return purchasesettlestyle;
    }

    public void setPurchasesettlestyle(Integer purchasesettlestyle) {
        this.purchasesettlestyle = purchasesettlestyle;
    }

    public Integer getSalesettlestyle() {
        return salesettlestyle;
    }

    public void setSalesettlestyle(Integer salesettlestyle) {
        this.salesettlestyle = salesettlestyle;
    }

    public Integer getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Integer taxrate) {
        this.taxrate = taxrate;
    }

    public Date getSalestartdate() {
        return salestartdate;
    }

    public void setSalestartdate(Date salestartdate) {
        this.salestartdate = salestartdate;
    }

    public Date getPurchasestartdate() {
        return purchasestartdate;
    }

    public void setPurchasestartdate(Date purchasestartdate) {
        this.purchasestartdate = purchasestartdate;
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

    public String getEaccount() {
        return eaccount;
    }

    public void setEaccount(String eaccount) {
        this.eaccount = eaccount == null ? null : eaccount.trim();
    }

    public Byte getIsmodifiedcode() {
        return ismodifiedcode;
    }

    public void setIsmodifiedcode(Byte ismodifiedcode) {
        this.ismodifiedcode = ismodifiedcode;
    }

    public Integer getElectronicinvoicereceivemode() {
        return electronicinvoicereceivemode;
    }

    public void setElectronicinvoicereceivemode(Integer electronicinvoicereceivemode) {
        this.electronicinvoicereceivemode = electronicinvoicereceivemode;
    }

    public String getElectronicinvoicereceiveemail() {
        return electronicinvoicereceiveemail;
    }

    public void setElectronicinvoicereceiveemail(String electronicinvoicereceiveemail) {
        this.electronicinvoicereceiveemail = electronicinvoicereceiveemail == null ? null : electronicinvoicereceiveemail.trim();
    }

    public String getElectronicinvoicereceivemobilephone() {
        return electronicinvoicereceivemobilephone;
    }

    public void setElectronicinvoicereceivemobilephone(String electronicinvoicereceivemobilephone) {
        this.electronicinvoicereceivemobilephone = electronicinvoicereceivemobilephone == null ? null : electronicinvoicereceivemobilephone.trim();
    }

    public Integer getDefaultinvoicetypeofreceive() {
        return defaultinvoicetypeofreceive;
    }

    public void setDefaultinvoicetypeofreceive(Integer defaultinvoicetypeofreceive) {
        this.defaultinvoicetypeofreceive = defaultinvoicetypeofreceive;
    }

    public Integer getIddefaultsettlestyleofreceive() {
        return iddefaultsettlestyleofreceive;
    }

    public void setIddefaultsettlestyleofreceive(Integer iddefaultsettlestyleofreceive) {
        this.iddefaultsettlestyleofreceive = iddefaultsettlestyleofreceive;
    }

    public Integer getIddefaultbankaccountofreceive() {
        return iddefaultbankaccountofreceive;
    }

    public void setIddefaultbankaccountofreceive(Integer iddefaultbankaccountofreceive) {
        this.iddefaultbankaccountofreceive = iddefaultbankaccountofreceive;
    }

    public Integer getDefaultinvoicetypeofpayment() {
        return defaultinvoicetypeofpayment;
    }

    public void setDefaultinvoicetypeofpayment(Integer defaultinvoicetypeofpayment) {
        this.defaultinvoicetypeofpayment = defaultinvoicetypeofpayment;
    }

    public Integer getIddefaultsettlestyleofpayment() {
        return iddefaultsettlestyleofpayment;
    }

    public void setIddefaultsettlestyleofpayment(Integer iddefaultsettlestyleofpayment) {
        this.iddefaultsettlestyleofpayment = iddefaultsettlestyleofpayment;
    }

    public Integer getIddefaultbankaccountofpayment() {
        return iddefaultbankaccountofpayment;
    }

    public void setIddefaultbankaccountofpayment(Integer iddefaultbankaccountofpayment) {
        this.iddefaultbankaccountofpayment = iddefaultbankaccountofpayment;
    }

    public Byte getIsautocancel() {
        return isautocancel;
    }

    public void setIsautocancel(Byte isautocancel) {
        this.isautocancel = isautocancel;
    }

    public Byte getIssystem() {
        return issystem;
    }

    public void setIssystem(Byte issystem) {
        this.issystem = issystem;
    }

    public String getVisitaddress() {
        return visitaddress;
    }

    public void setVisitaddress(String visitaddress) {
        this.visitaddress = visitaddress == null ? null : visitaddress.trim();
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public BigDecimal getCheckinscope() {
        return checkinscope;
    }

    public void setCheckinscope(BigDecimal checkinscope) {
        this.checkinscope = checkinscope;
    }

    public BigDecimal getVisitfrequency() {
        return visitfrequency;
    }

    public void setVisitfrequency(BigDecimal visitfrequency) {
        this.visitfrequency = visitfrequency;
    }

    public Byte getIsneedcheckinphoto() {
        return isneedcheckinphoto;
    }

    public void setIsneedcheckinphoto(Byte isneedcheckinphoto) {
        this.isneedcheckinphoto = isneedcheckinphoto;
    }

    public Byte getIsneedlyhuophoto() {
        return isneedlyhuophoto;
    }

    public void setIsneedlyhuophoto(Byte isneedlyhuophoto) {
        this.isneedlyhuophoto = isneedlyhuophoto;
    }

    public Byte getIsneedpromotionphoto() {
        return isneedpromotionphoto;
    }

    public void setIsneedpromotionphoto(Byte isneedpromotionphoto) {
        this.isneedpromotionphoto = isneedpromotionphoto;
    }

    public Byte getIsneedcompetitorphoto() {
        return isneedcompetitorphoto;
    }

    public void setIsneedcompetitorphoto(Byte isneedcompetitorphoto) {
        this.isneedcompetitorphoto = isneedcompetitorphoto;
    }

    public Integer getPrintcount() {
        return printcount;
    }

    public void setPrintcount(Integer printcount) {
        this.printcount = printcount;
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

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}