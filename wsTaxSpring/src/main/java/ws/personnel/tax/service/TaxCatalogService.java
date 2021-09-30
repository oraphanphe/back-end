package ws.personnel.tax.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ws.personnel.tax.entities.object.TaxCatalogObj;
import ws.personnel.tax.entities.objectC.TaxCatalogObjC;

import ws.personnel.tax.entities.TaxCatalog;
import ws.personnel.tax.repository.TaxCatalogRepository;

@Service
public class TaxCatalogService {
	@Autowired
	private TaxCatalogRepository taxCatalogRepository;
	
	@Autowired
	public TaxCatalogService(TaxCatalogRepository taxCatalogRepository) {
		this.taxCatalogRepository = taxCatalogRepository;
	}

	public TaxCatalogObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxCatalogObjC taxCatalogObjC = new TaxCatalogObjC();
		List<TaxCatalogObj> listTaxCatalogObj = new ArrayList<>();
		List<TaxCatalog> listTaxCatalogEntity = taxCatalogRepository.searchDataAll();
		for (int i = 0; i < listTaxCatalogEntity.size(); i++) {
			TaxCatalogObj taxCatalogObj = modelMapper.map(listTaxCatalogEntity.get(i), TaxCatalogObj.class);
			listTaxCatalogObj.add(taxCatalogObj);
		}
		taxCatalogObjC.setListTaxCatalogObj(listTaxCatalogObj);
		return taxCatalogObjC;
	}

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
		System.out.println("TaxCatalogService.save() ratetax getNo "+taxCatalog.getTaxCatalogId());
		System.out.println("TaxCatalogService.save() ratetax getStatus "+taxCatalog.getStatus());
		return  taxCatalogRepository.save(taxCatalog);
	}
	public String delete(String taxCatalogId) 
	{
		System.out.println("TaxCatalogService.delete() tax_catalog_id "+taxCatalogId);
		taxCatalogRepository.deleteById(taxCatalogId);
		return "no with taxCatalogId :"+taxCatalogId+" is deleted"; 
	}
	public String delete(TaxCatalog taxCatalog) 
	{
		System.out.println("TaxCatalogService.delete() taxCatalog.getTaxCatalogId()  "+taxCatalog.getTaxCatalogId());
		taxCatalogRepository.delete(taxCatalog);
		return "taxCatalogId :"+taxCatalog.getTaxCatalogId()+" is deleted"; 
	}
	public String deleteAll() 
	{
		System.out.println("TaxCatalogService.deleteAll() ");
	    taxCatalogRepository.deleteAll();
		return "delete all success"; 
	}
}
