package ws.personnel.tax.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ws.personnel.tax.entities.TaxCatalog;
import ws.personnel.tax.repository.TaxCatalogRepository;

@Service
public class TaxCatalogService {
	@Autowired
	private TaxCatalogRepository taxCatalogRepository;

	public List<TaxCatalog> findAll()
	{
		return  taxCatalogRepository.findAll();
	}
	public List<TaxCatalog> findAllSortKey()
	{
		return  taxCatalogRepository.findAll(Sort.by(Sort.Direction.ASC, "tax_catalog_id"));
	}

	public Optional<TaxCatalog> findById(String id) 
	{
		return taxCatalogRepository.findById(id);
	}

	public TaxCatalog save(TaxCatalog taxCatalog)
	{
		System.out.println("TaxCatalogService.save() ratetax getNo "+taxCatalog.getTax_catalog_id());
		System.out.println("TaxCatalogService.save() ratetax getStatus "+taxCatalog.getStatus());
		return  taxCatalogRepository.save(taxCatalog);
	}
	public String delete(String tax_catalog_id) 
	{
		System.out.println("TaxCatalogService.delete() tax_catalog_id "+tax_catalog_id);
		taxCatalogRepository.deleteById(tax_catalog_id);
		return "no with tax_catalog_id :"+tax_catalog_id+" is deleted"; 
	}
	public String delete(TaxCatalog taxCatalog) 
	{
		System.out.println("TaxCatalogService.delete() taxCatalog.getTax_catalog_id()  "+taxCatalog.getTax_catalog_id());
		taxCatalogRepository.delete(taxCatalog);
		return "tax_catalog_id :"+taxCatalog.getTax_catalog_id()+" is deleted"; 
	}
	public String deleteAll() 
	{
		System.out.println("TaxCatalogService.deleteAll() ");
	    taxCatalogRepository.deleteAll();
		return "delete all success"; 
	}
}
