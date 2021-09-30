package ws.personnel.tax.entities.object;

import java.util.List;

import ws.personnel.tax.configuration.DataObjBase;

public class UserObj extends DataObjBase{
	    private String userName;
	    private String password;
	    private String typeUser;
	    private List<UserRoleObj> listUserRoleObj = null;
	    
	    
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
		public List<UserRoleObj> getListUserRoleObj() {
			return listUserRoleObj;
		}
		public void setListUserRoleObj(List<UserRoleObj> listUserRoleObj) {
			this.listUserRoleObj = listUserRoleObj;
		}
		public String getTypeUser() {
			return typeUser;
		}
		public void setTypeUser(String typeUser) {
			this.typeUser = typeUser;
		}
		
	    
	    
	    
}
