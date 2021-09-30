package ws.personnel.tax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.personnel.tax.configuration.ServiceBase;
import ws.personnel.tax.entities.GroupMenu;
import ws.personnel.tax.repository.GroupMenuRepository;


@Service
public class GroupMenuService extends ServiceBase {
	
	@Autowired
	private GroupMenuRepository groupMenuRepository;
	
	@Autowired
	public GroupMenuService(GroupMenuRepository groupMenuRepository) {
		this.groupMenuRepository = groupMenuRepository;
	}
	
	public List<GroupMenu> searchGroupMenuAll() {
		return groupMenuRepository.searchGroupMenuAll();
	}

}
