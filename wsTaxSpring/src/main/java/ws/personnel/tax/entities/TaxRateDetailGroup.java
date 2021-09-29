package ws.personnel.tax.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TaxRateDetailGroup implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "tax_rate_id", unique = true, nullable = false)
	private String taxRateId;
	
	@Column(name = "rate_no", unique = true, nullable = false)
	private String rateNo;
	
	public TaxRateDetailGroup(){}
	
	public TaxRateDetailGroup(String taxRateId, String rateNo) {
		this.taxRateId = taxRateId;
		this.rateNo = rateNo;
	}

	public String getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(String taxRateId) {
		this.taxRateId = taxRateId;
	}

	public String getRateNo() {
		return rateNo;
	}

	public void setRateNo(String rateNo) {
		this.rateNo = rateNo;
	}

	
}