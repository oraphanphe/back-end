package ws.personnel.tax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ws.personnel.tax.entities.PersonInfo;

@Repository
public interface PersonInfoRepository extends JpaRepository<PersonInfo, Integer>{

}