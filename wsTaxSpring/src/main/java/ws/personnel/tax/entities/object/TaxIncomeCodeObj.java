package ws.personnel.tax.entities.object;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ws.personnel.tax.configuration.DataObjBaseTax;
import ws.personnel.tax.utils.Status;

public class TaxIncomeCodeObj extends DataObjBaseTax {
	
    private String incomeCatalogId;
    private String name;
    private String nameTh;
    private String nameEn;
    private String description;
    private String descriptionTh;
    private String descriptionEn;
    private String taxCatalog;
    private String taxRate;
    private Date effectiveDate;
	
	public String getIncomeCatalogId() {
		return incomeCatalogId;
	}
	public void setIncomeCatalogId(String incomeCatalogId) {
		this.incomeCatalogId = incomeCatalogId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameTh() {
		return nameTh;
	}
	public void setNameTh(String nameTh) {
		this.nameTh = nameTh;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionTh() {
		return descriptionTh;
	}
	public void setDescriptionTh(String descriptionTh) {
		this.descriptionTh = descriptionTh;
	}
	public String getDescriptionEn() {
		return descriptionEn;
	}
	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}
	public String getTaxCatalog() {
		return taxCatalog;
	}
	public void setTaxCatalog(String taxCatalog) {
		this.taxCatalog = taxCatalog;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
    
	
    
}
