package ws.personnel.tax.entities.object;

import java.util.List;

import ws.personnel.tax.configuration.DataObjBase;
import ws.personnel.tax.entities.object.MenuObj;

public class UserLoginObj extends DataObjBase{
	private List<UserRoleObj> listRole;
	
	    
	public List<UserRoleObj> getListRole() {
		return listRole;
	}
	public void setListRole(List<UserRoleObj> listRole) {
		this.listRole = listRole;
	}
    
	    
	    
}
