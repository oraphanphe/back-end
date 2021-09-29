package ws.personnel.tax.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonInfoHistGroup implements Serializable{
		private static final long serialVersionUID = 1L;

		@Column(name = "person_id", unique = true, nullable = false)
	    private int personId;
	    
	    @Column(name = "effective_date", unique = true, nullable = false)
	    private Date effectiveDate;

	    public PersonInfoHistGroup(){}

		public PersonInfoHistGroup(int personId, Date effectiveDate) {
			this.personId = personId;
			this.effectiveDate = effectiveDate;
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
	    
		
	    
	    
}
