package ws.personnel.tax.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GenerationType;
@Entity
@Table(name = "history_taxrate")
public class HistoryTaxrate 
{
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    
//    @Column(name = "version", unique = true, nullable = false)
//    private int version; 
//    
//	@Column(name = "no", unique = true, nullable = false)
//    private String no;  
    
	@EmbeddedId
	private HistoryTaxrateVersion HistoryTaxrateVersion;
	
    @Column(name = "min", precision=10, scale=2)
    private BigDecimal min;
    
    @Column(name = "max", precision=10, scale=2)
    private BigDecimal max;
    
    @Column(name = "rate", precision=5, scale=2)
    private BigDecimal rate;
    
    @Column(name = "surplus", precision=10, scale=2)
    private BigDecimal surplus;
    
    @Column(name = "tax", precision=10, scale=2)
    private BigDecimal tax;
    
    @Column(name = "sumtax", precision=10, scale=2)
    private BigDecimal sumtax;
    
    @Column(name = "reserve", length = 1)
    private String reserve;

    @Column(name = "create_user_code", length = 7)
    private String create_user_code;
    
    @CreationTimestamp
	@Column(name = "create_time")
	private Timestamp create_time;
    
    @Column(name = "update_user_code", length = 7)
    private String update_user_code;
    
    @UpdateTimestamp
	@Column(name = "update_time")
	private Timestamp update_time;

    
    public HistoryTaxrate(){}


	public HistoryTaxrate(ws.personnel.tax.entities.HistoryTaxrateVersion historyTaxrateVersion, BigDecimal min,
			BigDecimal max, BigDecimal rate, BigDecimal surplus, BigDecimal tax, BigDecimal sumtax, String reserve,
			String create_user_code, Timestamp create_time, String update_user_code, Timestamp update_time) {
		this.HistoryTaxrateVersion = historyTaxrateVersion;
		this.min = min;
		this.max = max;
		this.rate = rate;
		this.surplus = surplus;
		this.tax = tax;
		this.sumtax = sumtax;
		this.reserve = reserve;
		this.create_user_code = create_user_code;
		this.create_time = create_time;
		this.update_user_code = update_user_code;
		this.update_time = update_time;
	}


	public HistoryTaxrateVersion getHistoryTaxrateVersion() {
		return HistoryTaxrateVersion;
	}


	public void setHistoryTaxrateVersion(HistoryTaxrateVersion historyTaxrateVersion) {
		HistoryTaxrateVersion = historyTaxrateVersion;
	}


	public BigDecimal getMin() {
		return min;
	}


	public void setMin(BigDecimal min) {
		this.min = min;
	}


	public BigDecimal getMax() {
		return max;
	}


	public void setMax(BigDecimal max) {
		this.max = max;
	}


	public BigDecimal getRate() {
		return rate;
	}


	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}


	public BigDecimal getSurplus() {
		return surplus;
	}


	public void setSurplus(BigDecimal surplus) {
		this.surplus = surplus;
	}


	public BigDecimal getTax() {
		return tax;
	}


	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}


	public BigDecimal getSumtax() {
		return sumtax;
	}


	public void setSumtax(BigDecimal sumtax) {
		this.sumtax = sumtax;
	}


	public String getReserve() {
		return reserve;
	}


	public void setReserve(String reserve) {
		this.reserve = reserve;
	}


	public String getCreate_user_code() {
		return create_user_code;
	}


	public void setCreate_user_code(String create_user_code) {
		this.create_user_code = create_user_code;
	}


	public Timestamp getCreate_time() {
		return create_time;
	}


	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}


	public String getUpdate_user_code() {
		return update_user_code;
	}


	public void setUpdate_user_code(String update_user_code) {
		this.update_user_code = update_user_code;
	}


	public Timestamp getUpdate_time() {
		return update_time;
	}


	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	
}
