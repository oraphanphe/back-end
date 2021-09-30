package ws.personnel.tax.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import ws.personnel.tax.utils.PostgreSQLEnumType;
import ws.personnel.tax.utils.Status;

/**
 *
 * @author parach
 */
@Entity
@Table(name = "tax_income_code")
@XmlRootElement
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
	)
public class TaxIncomeCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "income_catalog_id")
    private String incomeCatalogId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "name_th")
    private String nameTh;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "description")
    private String description;
    @Column(name = "description_th")
    private String descriptionTh;
    @Column(name = "description_en")
    private String descriptionEn;
    @Basic(optional = false)
    @Column(name = "tax_catalog")
    private String taxCatalog;
    @Basic(optional = false)
    @Column(name = "tax_rate")
    private String taxRate;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enu_statu")
    @Type( type = "pgsql_enum" )
    private Status status;
    @Basic(optional = false)
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public TaxIncomeCode() {
    }

    public TaxIncomeCode(String incomeCatalogId) {
        this.incomeCatalogId = incomeCatalogId;
    }

    public TaxIncomeCode(String incomeCatalogId, String name, String taxCatalog, String taxRate, Status status, Date effectiveDate) {
        this.incomeCatalogId = incomeCatalogId;
        this.name = name;
        this.taxCatalog = taxCatalog;
        this.taxRate = taxRate;
        this.status = status;
        this.effectiveDate = effectiveDate;
    }

 
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

    public String getTaxCatalog() {
        return taxCatalog;
    }

    public void setTaxCatalog(String taxCatalog) {
        this.taxCatalog = taxCatalog;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incomeCatalogId != null ? incomeCatalogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxIncomeCode)) {
            return false;
        }
        TaxIncomeCode other = (TaxIncomeCode) object;
        if ((this.incomeCatalogId == null && other.incomeCatalogId != null) || (this.incomeCatalogId != null && !this.incomeCatalogId.equals(other.incomeCatalogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.TaxIncomeCode[ id=" + incomeCatalogId + " ]";
    }
    
}