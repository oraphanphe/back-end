package ws.personnel.tax.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws.personnel.tax.entities.TaxRateDetail;
import ws.personnel.tax.entities.TaxRateDetailGroup;
import ws.personnel.tax.repository.TaxRateDetailRepository;

@Service
public class TaxRateDetailService {
	@Autowired
	private TaxRateDetailRepository taxRateDetailRepository;

	public List<TaxRateDetail> findAll()
	{
		return  taxRateDetailRepository.findAll();
	}
	public Optional<TaxRateDetail> findById(String taxRateId, String rateNo) 
	{
		TaxRateDetailGroup taxRateDetailGroup = new TaxRateDetailGroup(taxRateId, rateNo);
		return taxRateDetailRepository.findById(taxRateDetailGroup);
	}
	
	public List<TaxRateDetail> findByTaxRateId(String taxRateId) 
	{
		return taxRateDetailRepository.findByTaxRateId(taxRateId);
	}
	
	public TaxRateDetail save(TaxRateDetail taxRateDetail)
	{
		System.out.println("TaxRateDetailService.save() taxRateDetail getTaxRateId "+taxRateDetail.getTaxRateDetailGroup().getTaxRateId());
		System.out.println("TaxRateDetailService.save() taxRateDetail getRateNo "+taxRateDetail.getTaxRateDetailGroup().getRateNo());
		System.out.println("TaxRateDetailService.save() taxRateDetail getMinAmt "+taxRateDetail.getMinAmt());
		return  taxRateDetailRepository.save(taxRateDetail);
	}
	public String delete(String taxRateId, String rateNo) 
	{
		TaxRateDetailGroup taxRateDetailGroup = new TaxRateDetailGroup(taxRateId, rateNo);
		taxRateDetailRepository.deleteById(taxRateDetailGroup);
		return "no with taxRateId :"+taxRateId+" and rateNo :"+rateNo+" is deleted"; 
	}
	public String delete(TaxRateDetail taxRateDetail) 
	{
		taxRateDetailRepository.delete(taxRateDetail);
		return "no with taxRateId :"+taxRateDetail.getTaxRateDetailGroup().getTaxRateId()+" and rateNo :"+taxRateDetail.getTaxRateDetailGroup().getRateNo()+" is deleted"; 
	}
	public String deleteAll() 
	{
		System.out.println("TaxRateDetailService.deleteAll() ");
		taxRateDetailRepository.deleteAll();
		return "delete all success"; 
	}
}
