package ws.personnel.tax.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ws.personnel.tax.utils.Status;

import ws.personnel.tax.entities.TaxCatalog;
@Repository
public interface TaxCatalogRepository extends JpaRepository<TaxCatalog, String>, JpaSpecificationExecutor<TaxCatalog>{
	
	@Query("SELECT t FROM TaxCatalog t where t.status='active'")
    public List<TaxCatalog> searchDataAll(); 
	
	@Query("SELECT t FROM TaxCatalog t where t.taxCatalogId=:taxCatalogId")
    public TaxCatalog searchDataById(@Param("taxCatalogId") String taxCatalogId); 
	
	@Query("SELECT t FROM TaxCatalog t where t.name LIKE CONCAT(:name,'%') and t.status='active'")
    public TaxCatalog searchDataByName(@Param("name") String name); 
	
	@Query("SELECT t FROM TaxCatalog t where t.name LIKE CONCAT(:name,'%') and t.status=:status")
    public List<TaxCatalog> searchDataByNameAndStatus(@Param("name") String name,@Param("status") Status status); 
	
	@Query("SELECT t FROM TaxCatalog t where t.name LIKE CONCAT(:name,'%') and t.status IN ('active','inactive')")
    public List<TaxCatalog> searchDataByNameAndStatusAll(@Param("name") String name); 
	
	@Query("SELECT t FROM TaxCatalog t where t.name=:name and t.status='active'")
    public List<TaxCatalog> checkDupDataByNameAndStatus(@Param("name") String name); 
}
