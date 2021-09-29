package ws.personnel.tax.entities;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name = "tax_person_info")
public class TaxPersonInfo {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "salse_id", unique = true, nullable = false, length = 10)
    private String salseId;
    
    @Column(name = "employee_type", length = 1)
    private String employeeType;
    
    @Column(name = "income_catalog_id", length = 2)
    private String incomeCatalogId;
    
    @Column(name = "rd_request", length = 1)
    private String rdRequest;
    
    @Column(name = "rd_date")
    private Date rdDate;
    
    @Column(name = "receive_cer", length = 10)
    private String receiveCer;
    
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private enu_status status;
    
    @Column(name = "status")
    private String status;
    
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

    public TaxPersonInfo(){}
    
	public TaxPersonInfo(String salseId, String employeeType, String incomeCatalogId, String rdRequest, Date rdDate,
			String receiveCer, String status, String createUser, Timestamp createTime, String updateUser,
			Timestamp updateTime) {
		this.salseId = salseId;
		this.employeeType = employeeType;
		this.incomeCatalogId = incomeCatalogId;
		this.rdRequest = rdRequest;
		this.rdDate = rdDate;
		this.receiveCer = receiveCer;
		this.status = status;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	public String getSalseId() {
		return salseId;
	}

	public void setSalseId(String salseId) {
		this.salseId = salseId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getIncomeCatalogId() {
		return incomeCatalogId;
	}

	public void setIncomeCatalogId(String incomeCatalogId) {
		this.incomeCatalogId = incomeCatalogId;
	}

	public String getRdRequest() {
		return rdRequest;
	}

	public void setRdRequest(String rdRequest) {
		this.rdRequest = rdRequest;
	}

	public Date getRdDate() {
		return rdDate;
	}

	public void setRdDate(Date rdDate) {
		this.rdDate = rdDate;
	}

	public String getReceiveCer() {
		return receiveCer;
	}

	public void setReceiveCer(String receiveCer) {
		this.receiveCer = receiveCer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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