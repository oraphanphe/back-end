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
@Table(name = "tax_deduct")
@XmlRootElement
@TypeDef(
	    name = "pgsql_enum",
	    typeClass = PostgreSQLEnumType.class
	)

public class TaxDeduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tax_deduct_id")
    private String taxDeductId;
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
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enu_statu")
    @Type( type = "pgsql_enum" )
    private Status status;
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public TaxDeduct() {
    }

    public TaxDeduct(String taxDeductId) {
        this.taxDeductId = taxDeductId;
    }

    public TaxDeduct(String taxDeductId, String name) {
        this.taxDeductId = taxDeductId;
        this.name = name;
    }

    public String getTaxDeductId() {
        return taxDeductId;
    }

    public void setTaxDeductId(String taxDeductId) {
        this.taxDeductId = taxDeductId;
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
        hash += (taxDeductId != null ? taxDeductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaxDeduct)) {
            return false;
        }
        TaxDeduct other = (TaxDeduct) object;
        if ((this.taxDeductId == null && other.taxDeductId != null) || (this.taxDeductId != null && !this.taxDeductId.equals(other.taxDeductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.TaxDeduct[ taxDeductId=" + taxDeductId + " ]";
    }
    
}
