package ws.personnel.tax.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ws.personnel.tax.configuration.ServiceBase;
import ws.personnel.tax.entities.User;
import ws.personnel.tax.entities.object.UserObj;
import ws.personnel.tax.entities.objectC.UserObjC;
import ws.personnel.tax.repository.UserRepository;


@Service
public class UserService extends ServiceBase {
	
	@Autowired
	private UserRepository userRepository;
	
	 private EntityManager entityManager;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		UserObjC userObjC = new UserObjC();
		List<UserObj> listUserObj = new ArrayList<>();
		List<User> listUserEntity = userRepository.searchDataAll();
		for(int i=0 ; i < listUserEntity.size();i++){
			UserObj userObj = modelMapper.map(listUserEntity.get(i),UserObj.class);
			listUserObj.add(userObj);
		}
		userObjC.setListUserObj(listUserObj);
		return userObjC;
	}
	
	   @Transactional
	    public User saveEntity(User entity) {
	        entityManager.unwrap(Session.class).save(entity);
	        entityManager.unwrap(Session.class).flush();
	        return entity;
	    }

	    @Transactional
	    public List<User> saveEntityList(List<User> entityList) {
	        int i = 0;
//	        int batchSize = rdMftConfigProperties.getRepositoryBatchSize();
	        for (User item : entityList) {
	            i++;
	            entityManager.unwrap(Session.class).save(item);

//	            if (i % batchSize == 0)
//	                entityManager.flush();
	        }
	        entityManager.unwrap(Session.class).flush();
	        return entityList;

	    }
	

}
