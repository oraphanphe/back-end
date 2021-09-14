package ws.personnel.tax.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ws.personnel.tax.entities.TaxRateDetail;
import ws.personnel.tax.repository.TaxRateDetailRepository;

@Service
public class TaxRateDetailService {
	@Autowired
	private TaxRateDetailRepository taxRateDetailRepository;

	public List<TaxRateDetail> findAll()
	{
		return  taxRateDetailRepository.findAll();
	}
	public List<TaxRateDetail> findAllSortKey()
	{
		return  taxRateDetailRepository.findAll(Sort.by(Sort.Direction.ASC, "no"));
	}

	public Optional<TaxRateDetail> findById(String id) 
	{
		return taxRateDetailRepository.findById(id);
	}

	public TaxRateDetail save(TaxRateDetail taxRateDetail)
	{
		System.out.println("TaxRateDetaileService.save() ratetax getNo "+taxRateDetail.getTax_rate_id());
		return  taxRateDetailRepository.save(taxRateDetail);
	}
	public String delete(String id) 
	{
		System.out.println("TaxRateDetailService.delete() id "+id);
		taxRateDetailRepository.deleteById(id);
		return "no with ID :"+id+" is deleted"; 
	}
	public String delete(TaxRateDetail taxRateDetail) 
	{
		System.out.println("TaxRateDetailService.delete() ratetax.getNo()  "+taxRateDetail.getTax_rate_id());
		taxRateDetailRepository.delete(taxRateDetail);
		return "no with ID :"+taxRateDetail.getTax_rate_id()+" is deleted"; 
	}
	public String deleteAll() 
	{
		System.out.println("TaxRateDetailService.deleteAll() ");
		taxRateDetailRepository.deleteAll();
		return "delete all success"; 
	}
}
