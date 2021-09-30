package ws.personnel.tax.entities.object;

import java.util.Date;

import ws.personnel.tax.configuration.DataObjBaseTax;

public class TaxCatalogObj extends DataObjBaseTax{
	
    private String taxCatalogId;
    private String name;
    private String nameTh;
    private String nameEn;
    private String description;
    private String descriptionTh;
    private String descriptionEn;
    private Date effectiveDate;
    
	public String getTaxCatalogId() {
		return taxCatalogId;
	}
	public void setTaxCatalogId(String taxCatalogId) {
		this.taxCatalogId = taxCatalogId;
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
    
    

}
