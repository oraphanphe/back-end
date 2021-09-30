package ws.personnel.tax.entities.objectC;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ws.personnel.tax.configuration.DataObjBaseTax;
import ws.personnel.tax.entities.object.TaxIncomeCodeObj;

public class TaxIncomeCodeObjC extends DataObjBaseTax {
	
    private String incomeCatalogId;
    private String name;
    private String nameTh;
    private String nameEn;
    private List<TaxIncomeCodeObj> listTaxIncomeObj= new ArrayList<TaxIncomeCodeObj>();
    
	
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
	public List<TaxIncomeCodeObj> getListTaxIncomeObj() {
		return listTaxIncomeObj;
	}
	public void setListTaxIncomeObj(List<TaxIncomeCodeObj> listTaxIncomeObj) {
		this.listTaxIncomeObj = listTaxIncomeObj;
	}
    
}
