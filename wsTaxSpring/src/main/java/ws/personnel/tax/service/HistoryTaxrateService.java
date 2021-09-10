package ws.personnel.tax.service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ws.personnel.tax.entities.HistoryTaxrate;
import ws.personnel.tax.entities.HistoryTaxrateVersion;
import ws.personnel.tax.entities.TaxRateDetail;
import ws.personnel.tax.repository.HistoryTaxrateRepository;

@Service
public class HistoryTaxrateService {
	@Autowired
	private HistoryTaxrateRepository historyTaxrateRepository;
	

	public List<HistoryTaxrate> findAll()
	{
		return  historyTaxrateRepository.findAll();
	}
//	public List<HistoryTaxrate> findAllSortKeyDESC()
//	{
//		return  historyTaxrateRepository.findAll(Sort.by(Sort.Direction.DESC, "create_time"));
//	}

	public Optional<HistoryTaxrate> findByKey(int version, String no) {
        HistoryTaxrateVersion historyTaxrateVersion = new HistoryTaxrateVersion(version, no);
        return historyTaxrateRepository.findById(historyTaxrateVersion);
    }

	public HistoryTaxrate save(HistoryTaxrate historyTaxrate)
	{
		return  historyTaxrateRepository.save(historyTaxrate);
	}
	public List<HistoryTaxrate> saveAll(List<HistoryTaxrate> listHistoryTaxrate)
	{
		return  historyTaxrateRepository.saveAll(listHistoryTaxrate);
	}
	public String delete(int version, String no) 
	{
		HistoryTaxrateVersion historyTaxrateVersion = new HistoryTaxrateVersion(version, no);
		historyTaxrateRepository.deleteById(historyTaxrateVersion);
		return "version with version :"+version+" and no :"+no+" is deleted"; 
	}
}
