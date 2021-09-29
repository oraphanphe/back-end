package ws.personnel.tax.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.personnel.tax.entities.TaxPersonInfo;
@Repository
public interface TaxPersonInfoRepository extends JpaRepository<TaxPersonInfo, String>{

}
