/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.personnel.tax.repository;

import ws.personnel.tax.entities.Menu;
import ws.personnel.tax.entities.UserRole;
import ws.personnel.tax.entities.object.MenuObj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
/**
 *
 * @author Parach
 */
@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, String>, JpaSpecificationExecutor<UserRole> {

	@Query("SELECT u FROM UserRole u where u.status='ST001'")
    public List<UserRole> searchDataAll();
	
	@Query("SELECT u FROM UserRole u where u.id =:id and u.status='ST001'")
    public UserRole findByUserRoleId(@Param("id") String id);
	
	@Query("SELECT u FROM UserRole u where u.groupId =:groupId")
    public List<UserRole> findByGroupIdUserRole(@Param("groupId") String groupId);
	
	@Query("SELECT ur FROM UserRole ur JOIN User u on u.id = ur.userId where u.userName =:userName")
    public List<UserRole> findByUserName(@Param("userName") String userName);
	
	@Query("SELECT ur FROM UserRole ur JOIN User u on u.id = ur.userId where u.userName =:userName and ur.status='ST001'")
    public List<UserRole> findByUserRoleByUserName(@Param("userName") String userName);
	
	@Query("SELECT ur FROM UserRole ur JOIN User u on u.id = ur.userId JOIN Role r on r.id = ur.roleId where ((u.userName LIKE CONCAT(:userName,'%')) and (r.roleName LIKE CONCAT(:roleName,'%'))) and ur.status LIKE CONCAT(:status,'%')")
    public List<UserRole> searchUserNameAndRoleName(@Param("userName") String userName,@Param("roleName") String roleName,@Param("status") String status);
	
	@Query("SELECT ur FROM UserRole ur JOIN User u on u.id = ur.userId JOIN Role r on r.id = ur.roleId where u.userName LIKE CONCAT(:userName,'%') and ur.status LIKE CONCAT(:status,'%')")
    public List<UserRole> searchUserName(@Param("userName") String userName,@Param("status") String status);
	
	@Query("SELECT ur FROM UserRole ur JOIN User u on u.id = ur.userId JOIN Role r on r.id = ur.roleId where r.roleName LIKE CONCAT(:roleName,'%') and ur.status LIKE CONCAT(:status,'%')")
    public List<UserRole> searchRoleName(@Param("roleName") String roleName,@Param("status") String status);
	
	@Query("SELECT ur FROM UserRole ur where ur.roleId =:roleId")
    public void checkUserUseRole(@Param("roleId") String roleId);
	
	
	
//	@Modifying
//    @Query("UPDATE EdcDataProcessingControl e SET e.processState = :processState , e.status = :status , e.updatedBy = :updatedBy , e.updatedDate = :updatedDate WHERE e.id = :id")
//    @Transactional
//    public void updateDataProcessingControl(@Param("id") String id,@Param("processState") String processState,@Param("status") String status,@Param("updatedBy") String updatedBy,@Param("updatedDate") Date updatedDate);

	
	
}
