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
    private String tax_catalog_id;
    
    @Column(name = "name", length = 90)
    private String name;
    
    @Column(name = "name_th", length = 90)
    private String name_th;
    
    @Column(name = "name_en", length = 90)
    private String name_en;
    
    @Column(name = "description", length = 200)
    private String description;
    
    @Column(name = "description_th", length = 200)
    private String description_th;
    
    @Column(name = "description_en", length = 200)
    private String description_en;
    
//    @Enumerated(EnumType.STRING)
//    @Column(name = "status")
//    private enu_status status;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "effective_date")
    private Date effective_date;
    
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
    
    public TaxCatalog(){}
    
    public TaxCatalog(String tax_catalog_id, String name, String name_th, String name_en, String description,
			String description_th, String description_en, String status, Date effective_date, String create_user,
			Timestamp create_time, String update_user, Timestamp update_time) {
//	public TaxCatalog(String tax_catalog_id, String name, String name_th, String name_en, String description,
//			String description_th, String description_en, enu_status status, Date effective_date, String create_user,
//			Timestamp create_time, String update_user, Timestamp update_time) {
		super();
		this.tax_catalog_id = tax_catalog_id;
		this.name = name;
		this.name_th = name_th;
		this.name_en = name_en;
		this.description = description;
		this.description_th = description_th;
		this.description_en = description_en;
		this.status = status; 
		System.out.println("TaxCatalog.TaxCatalog() status = "+status);
		this.effective_date = effective_date;
		this.create_user = create_user;
		this.create_time = create_time;
		this.update_user = update_user;
		this.update_time = update_time;
	}

	public String getTax_catalog_id() {
		return tax_catalog_id;
	}

	public void setTax_catalog_id(String tax_catalog_id) {
		this.tax_catalog_id = tax_catalog_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_th() {
		return name_th;
	}

	public void setName_th(String name_th) {
		this.name_th = name_th;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription_th() {
		return description_th;
	}

	public void setDescription_th(String description_th) {
		this.description_th = description_th;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

//	public enu_status getStatus() {
//		System.out.println("TaxCatalog.getStatus() status = "+status);
//		return status;
//	}
//
//	public void setStatus(enu_status status) {
//		System.out.println("TaxCatalog.setStatus() status = "+status);
//		this.status = status;
//	}
	
	public String getStatus() {
		System.out.println("TaxCatalog.getStatus() status = "+status);
		return status;
	}

	public void setStatus(String status) {
		System.out.println("TaxCatalog.setStatus() status = "+status);
		this.status = status;
	}
	
	public Date getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(Date effective_date) {
		this.effective_date = effective_date;
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
