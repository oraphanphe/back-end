package ws.personnel.tax.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ws.personnel.tax.entities.TaxRateDetail;
@Repository
public interface TaxRateDetailRepository extends JpaRepository<TaxRateDetail, String>
{
   
}