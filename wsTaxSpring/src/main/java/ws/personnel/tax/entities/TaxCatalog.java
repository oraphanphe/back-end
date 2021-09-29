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
import org.hibernate.annotations.UpdateTimestamp;
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
@Table(name = "tax_catalog")
public class TaxCatalog {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "tax_catalog_id", unique = true, nullable = false, length = 2)
    private String taxCatalogId;
    
    @Column(name = "name", length = 90)
    private String name;
    
    @Column(name = "name_th", length = 90)
    private String nameTh;
    
    @Column(name = "name_en", length = 90)
    private String nameEn;
    
    @Column(name = "description", length = 200)
    private String description;
    
    @Column(name = "description_th", length = 200)
    private String descriptionTh;
    
    @Column(name = "description_en", length = 200)
    private String descriptionEn;
    
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private enu_status status;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "effective_date")
    private Date effectiveDate;
    
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
    
    public TaxCatalog(){}

    
    
	public TaxCatalog(String taxCatalogId, String name, String nameTh, String nameEn, String description,
			String descriptionTh, String descriptionEn, String status, Date effectiveDate, String createUser,
			Timestamp createTime, String updateUser, Timestamp updateTime) {
		this.taxCatalogId = taxCatalogId;
		this.name = name;
		this.nameTh = nameTh;
		this.nameEn = nameEn;
		this.description = description;
		this.descriptionTh = descriptionTh;
		this.descriptionEn = descriptionEn;
		this.status = status;
		this.effectiveDate = effectiveDate;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}



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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
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
