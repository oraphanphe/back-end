package ws.personnel.tax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import ws.personnel.tax.configuration.ServiceBase;
import ws.personnel.tax.entities.Menu;
import ws.personnel.tax.entities.object.MenuObj;
import ws.personnel.tax.entities.objectC.MenuObjC;
import ws.personnel.tax.repository.MenuRepository;


@Service
public class MenuService extends ServiceBase {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
	
	public List<Menu> searchDataAllMenu() {
		return menuRepository.searchDataAllMenu();
	}
	
	public Menu menuFindById(String id) {
		return menuRepository.menuFindById(id);
	}
	
	public List<Menu> menuFromUserPermission(String UserId) {
		return menuRepository.menuFromUserPermission(UserId);
	}

}
