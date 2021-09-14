package ws.personnel.tax.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GenerationType;
@Entity
@Table(name = "tax_rate_detail")
public class TaxRateDetail 
{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "tax_rate_id", unique = true, nullable = false, length = 2)
    private String tax_rate_id ;
	
//	@Id
//	@Column(name = "rate_no", unique = true, nullable = false, length = 2)
//    private String rate_no ;
	 @Column(name = "rate_no", length = 2)
	    private String rate_no;
	
    @Column(name = "min_amt", precision=10, scale=2)
    private BigDecimal min_amt;
    
    @Column(name = "max_amt", precision=10, scale=2)
    private BigDecimal max_amt;
    
    @Column(name = "rate", precision=5, scale=2)
    private BigDecimal rate;
    
    @Column(name = "amount", precision=10, scale=2)
    private BigDecimal amount;
    
    @Column(name = "tax_amt", precision=10, scale=2)
    private BigDecimal tax_amt;
    
    @Column(name = "tax_sum", precision=10, scale=2)
    private BigDecimal tax_sum;
    
    @Column(name = "create_user", length = 10)
    private String create_user;
    
    @CreationTimestamp
	@Column(name = "create_time")
	private Timestamp create_time;
    
    @Column(name = "update_user", length = 10)
    private String update_user;
    
    @UpdateTimestamp
	@Column(name = "update_time")
	private Timestamp update_time;

	public TaxRateDetail() {
	}

	public TaxRateDetail(String tax_rate_id, String rate_no, BigDecimal min_amt, BigDecimal max_amt, BigDecimal rate,
			BigDecimal amount, BigDecimal tax_amt, BigDecimal tax_sum, String create_user, Timestamp create_time,
			String update_user, Timestamp update_time) {
		super();
		this.tax_rate_id = tax_rate_id;
		this.rate_no = rate_no;
		this.min_amt = min_amt;
		this.max_amt = max_amt;
		this.rate = rate;
		this.amount = amount;
		this.tax_amt = tax_amt;
		this.tax_sum = tax_sum;
		this.create_user = create_user;
		this.create_time = create_time;
		this.update_user = update_user;
		this.update_time = update_time;
	}

	public String getTax_rate_id() {
		return tax_rate_id;
	}

	public void setTax_rate_id(String tax_rate_id) {
		this.tax_rate_id = tax_rate_id;
	}

	public String getRate_no() {
		return rate_no;
	}

	public void setRate_no(String rate_no) {
		this.rate_no = rate_no;
	}

	public BigDecimal getMin_amt() {
		return min_amt;
	}

	public void setMin_amt(BigDecimal min_amt) {
		this.min_amt = min_amt;
	}

	public BigDecimal getMax_amt() {
		return max_amt;
	}

	public void setMax_amt(BigDecimal max_amt) {
		this.max_amt = max_amt;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTax_amt() {
		return tax_amt;
	}

	public void setTax_amt(BigDecimal tax_amt) {
		this.tax_amt = tax_amt;
	}

	public BigDecimal getTax_sum() {
		return tax_sum;
	}

	public void setTax_sum(BigDecimal tax_sum) {
		this.tax_sum = tax_sum;
	}

	public String getCreate_user() {
		return create_user;
	}

	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
    
    
}