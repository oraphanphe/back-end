package ws.personnel.tax.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ws.personnel.tax.entities.TaxRateDetail;
import ws.personnel.tax.entities.TaxRateDetailGroup;
import java.util.List;
@Repository
public interface TaxRateDetailRepository extends JpaRepository<TaxRateDetail, TaxRateDetailGroup> {

	@Query("FROM TaxRateDetail WHERE tax_rate_id = ?1")
    List<TaxRateDetail> findByTaxRateId(String taxRateId);
}