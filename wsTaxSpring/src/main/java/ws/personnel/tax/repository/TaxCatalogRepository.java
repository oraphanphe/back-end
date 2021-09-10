package ws.personnel.tax.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.personnel.tax.entities.TaxCatalog;
@Repository
public interface TaxCatalogRepository extends JpaRepository<TaxCatalog, String>{

}
