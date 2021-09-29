package ws.personnel.tax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ws.personnel.tax.entities.PersonInfoHist;
import ws.personnel.tax.entities.PersonInfoHistGroup;

@Repository
public interface PersonInfoHistRepository extends JpaRepository< PersonInfoHist,  PersonInfoHistGroup> {

}