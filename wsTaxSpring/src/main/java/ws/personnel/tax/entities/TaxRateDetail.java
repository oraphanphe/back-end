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
@Table(name = "tax_rate_detail")
public class TaxRateDetail 
{
	@EmbeddedId
	private TaxRateDetailGroup taxRateDetailGroup;
	
    @Column(name = "min_amt", precision=10, scale=2)
    private BigDecimal minAmt;
    
    @Column(name = "max_amt", precision=10, scale=2)
    private BigDecimal maxAmt;
    
    @Column(name = "rate", precision=5, scale=2)
    private BigDecimal rate;
    
    @Column(name = "amount", precision=10, scale=2)
    private BigDecimal amount;
    
    @Column(name = "tax_amt", precision=10, scale=2)
    private BigDecimal taxAmt;
    
    @Column(name = "tax_sum", precision=10, scale=2)
    private BigDecimal taxSum;
    
    @Column(name = "create_user", length = 10)
    private String createUser;
    
    @CreationTimestamp
	@Column(name = "create_time")
	private Timestamp createTime;
    
    @Column(name = "update_user", length = 10)
    private String updateUser;
    
    @UpdateTimestamp
	@Column(name = "update_time")
	private Timestamp updateTime;

	public TaxRateDetail() {
	}

	public TaxRateDetail(TaxRateDetailGroup taxRateDetailGroup, BigDecimal minAmt, BigDecimal maxAmt, BigDecimal rate,
			BigDecimal amount, BigDecimal taxAmt, BigDecimal taxSum, String createUser, Timestamp createTime,
			String updateUser, Timestamp updateTime) {
		this.taxRateDetailGroup = taxRateDetailGroup;
		this.minAmt = minAmt;
		this.maxAmt = maxAmt;
		this.rate = rate;
		this.amount = amount;
		this.taxAmt = taxAmt;
		this.taxSum = taxSum;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	public TaxRateDetailGroup getTaxRateDetailGroup() {
		return taxRateDetailGroup;
	}

	public void setTaxRateDetailGroup(TaxRateDetailGroup taxRateDetailGroup) {
		this.taxRateDetailGroup = taxRateDetailGroup;
	}

	public BigDecimal getMinAmt() {
		return minAmt;
	}

	public void setMinAmt(BigDecimal minAmt) {
		this.minAmt = minAmt;
	}

	public BigDecimal getMaxAmt() {
		return maxAmt;
	}

	public void setMaxAmt(BigDecimal maxAmt) {
		this.maxAmt = maxAmt;
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

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getTaxSum() {
		return taxSum;
	}

	public void setTaxSum(BigDecimal taxSum) {
		this.taxSum = taxSum;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	
    
    
}