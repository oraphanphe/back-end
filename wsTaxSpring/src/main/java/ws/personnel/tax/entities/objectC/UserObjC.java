package ws.personnel.tax.entities.objectC;

import java.util.List;

import ws.personnel.tax.configuration.DataObjBase;
import ws.personnel.tax.entities.object.UserObj;

public class UserObjC extends DataObjBase{
	    private String userName;
	    private String password;
	    private String typeUser;
	    private List<UserObj> listUserObj = null;
	    
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public List<UserObj> getListUserObj() {
			return listUserObj;
		}
		public void setListUserObj(List<UserObj> listUserObj) {
			this.listUserObj = listUserObj;
		}
		public String getTypeUser() {
			return typeUser;
		}
		public void setTypeUser(String typeUser) {
			this.typeUser = typeUser;
		}
		
	    
	    
	    
}
