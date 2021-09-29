package ws.personnel.tax.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "person_info_hist")
public class PersonInfoHist {
	@EmbeddedId
	private PersonInfoHistGroup personInfoHistGroup;
	
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
	    
	    PersonInfoHist(){}

		public PersonInfoHist(PersonInfoHistGroup personInfoHistGroup, String citizenId, String taxId, String socId,
				String preName, String firstName, String lastName, String address, String tambon, String zipcode,
				String email, String phone, String status, String createUser, Timestamp createTime, String updateUser,
				Timestamp updateTime) {
			this.personInfoHistGroup = personInfoHistGroup;
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

		public PersonInfoHistGroup getPersonInfoHistGroup() {
			return personInfoHistGroup;
		}

		public void setPersonInfoHistGroup(PersonInfoHistGroup personInfoHistGroup) {
			this.personInfoHistGroup = personInfoHistGroup;
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
