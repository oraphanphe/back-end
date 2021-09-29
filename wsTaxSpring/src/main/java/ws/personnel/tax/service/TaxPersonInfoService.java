package ws.personnel.tax.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ws.personnel.tax.entities.TaxPersonInfo;
import ws.personnel.tax.repository.TaxPersonInfoRepository;

@Service
public class TaxPersonInfoService {
	@Autowired
	private TaxPersonInfoRepository taxPersonInfoRepository;

	public List<TaxPersonInfo> findAll()
	{
		return  taxPersonInfoRepository.findAll();
	}
	public List<TaxPersonInfo> findAllSortKey()
	{
		return  taxPersonInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "salseId"));
	}

	public Optional<TaxPersonInfo> findById(String id) 
	{
		return taxPersonInfoRepository.findById(id);
	}

	public TaxPersonInfo save(TaxPersonInfo taxPersonInfo)
	{
		System.out.println("TaxPersonInfoService.save() ratetax getNo "+taxPersonInfo.getSalseId());
		System.out.println("TaxPersonInfoService.save() ratetax getStatus "+taxPersonInfo.getStatus());
		return  taxPersonInfoRepository.save(taxPersonInfo);
	}
	public String delete(String salseId) 
	{
		System.out.println("TaxPersonInfoService.delete() salseId "+salseId);
		taxPersonInfoRepository.deleteById(salseId);
		return "no with SalseId :"+salseId+" is deleted"; 
	}
	public String delete(TaxPersonInfo taxPersonInfo) 
	{
		System.out.println("TaxPersonInfoService.delete() taxPersonInfo.getSalseId()  "+taxPersonInfo.getSalseId());
		taxPersonInfoRepository.delete(taxPersonInfo);
		return "SalseId :"+taxPersonInfo.getSalseId()+" is deleted"; 
	}
	public String deleteAll() 
	{
		System.out.println("TaxPersonInfoService.deleteAll() ");
	    taxPersonInfoRepository.deleteAll();
		return "delete all success"; 
	}
}