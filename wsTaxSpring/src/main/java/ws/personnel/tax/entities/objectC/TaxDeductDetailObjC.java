package ws.personnel.tax.entities.objectC;

import java.util.ArrayList;
import java.util.List;

import ws.personnel.tax.configuration.DataObjBaseTax;
import ws.personnel.tax.entities.object.TaxDeductDetailObj;

public class TaxDeductDetailObjC extends DataObjBaseTax {
	
	private String year;
	private String taxDeductId;
	private List<TaxDeductDetailObj> listTaxDeductDetailObj= new ArrayList<TaxDeductDetailObj>();
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTaxDeductId() {
		return taxDeductId;
	}
	public void setTaxDeductId(String taxDeductId) {
		this.taxDeductId = taxDeductId;
	}
	public List<TaxDeductDetailObj> getListTaxDeductDetailObj() {
		return listTaxDeductDetailObj;
	}
	public void setListTaxDeductDetailObj(List<TaxDeductDetailObj> listTaxDeductDetailObj) {
		this.listTaxDeductDetailObj = listTaxDeductDetailObj;
	}
	

}
