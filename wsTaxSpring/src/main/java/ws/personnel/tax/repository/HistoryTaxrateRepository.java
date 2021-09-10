package ws.personnel.tax.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.personnel.tax.entities.HistoryTaxrate;
import ws.personnel.tax.entities.HistoryTaxrateVersion;
//@Repository
//public interface HistoryTaxrateRepository extends JpaRepository<HistoryTaxrate, Integer> 
//{
//
//}
@Repository
public interface HistoryTaxrateRepository extends JpaRepository<HistoryTaxrate, HistoryTaxrateVersion> 
{

}
//@Repository
//public interface BookRepository extends CrudRepository<Book, BookIdentity> {
//
//    //spring jpa will automatically parse the method name
//    //and create a query from it.
//    Optional<Book> findByIdentityIsbnId(String isbnId);
//
//    List<Book> findByIdentityId(int id);
//}
