package ws.personnel.tax.entities;
import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import ws.personnel.tax.util.Status;
/*
CREATE TABLE public.tax_catalog
(
    tax_catalog_id character(2) COLLATE pg_catalog."default" NOT NULL,
    name character varying(90) COLLATE pg_catalog."default" NOT NULL,
    name_th character varying(90) COLLATE pg_catalog."default",
    name_en character varying(90) COLLATE pg_catalog."default",
    description character varying(200) COLLATE pg_catalog."default",
    description_th character varying(200) COLLATE pg_catalog."default",
    description_en character varying(200) COLLATE pg_catalog."default",
    status enu_status NOT NULL,
    effective_date date NOT NULL,
    create_user character(10) COLLATE pg_catalog."default" NOT NULL,
    create_time time without time zone NOT NULL,
    update_user character(10) COLLATE pg_catalog."default" NOT NULL,
    update_time time without time zone NOT NULL,
    CONSTRAINT tax_catalog_pkey PRIMARY KEY (tax_catalog_id)
)
*/
@Entity
@Table(name = "person_info")
public class PersonInfo {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "person_id", unique = true, nullable = false)
    private int personId;
    
    @Column(name = "effective_date", unique = true, nullable = false)
    private Date effectiveDate;
    
	@Column(name = "citizen_id", length = 13)
    private String citizenId;
    
    @Column(name = "tax_id", length = 13)
    private String taxId;
    
    @Column(name = "soc_id", length = 13)
    private String socId;
    
    @Column(name = "pre_name", length = 30)
    private String preName;
    
    @Column(name = "first_name", length = 50)
    private String firstName;
    
    @Column(name = "last_name", length = 50)
    private String lastName;
    
    @Column(name = "address", length = 200)
    private String address;
    
    @Column(name = "tambon", length = 100)
    private String tambon;
    
    @Column(name = "zipcode", length = 4)
    private String zipcode;
    
    @Column(name = "email", length = 200)
    private String email;
    
    @Column(name = "phone", length = 10)
    private String phone;
    
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enu_status")
    @Type( type = "pgsql_enum" )
    private Status status;
    	   
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

    public PersonInfo(){}

    
    
	public PersonInfo(int personId, Date effectiveDate, String citizenId, String taxId, String socId, String preName,
			String firstName, String lastName, String address, String tambon, String zipcode, String email, String phone,
			Status status, String createUser, Timestamp createTime, String updateUser, Timestamp updateTime) {
		this.personId = personId;
		this.effectiveDate = effectiveDate;
		this.citizenId = citizenId;
		this.taxId = taxId;
		this.socId = socId;
		this.preName = preName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.tambon = tambon;
		this.zipcode = zipcode;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getSocId() {
		return socId;
	}

	public void setSocId(String socId) {
		this.socId = socId;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTambon() {
		return tambon;
	}

	public void setTambon(String tambon) {
		this.tambon = tambon;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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
