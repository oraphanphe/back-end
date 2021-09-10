package ws.personnel.tax.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//import ws.personnel.tax.entities.HistoryTaxrate;
//import ws.personnel.tax.entities.HistoryTaxrateVersion;
//import ws.personnel.tax.entities.Taxrate;
//import ws.personnel.tax.service.HistoryTaxrateService;
//import ws.personnel.tax.service.TaxrateService;

//@RestController
//@RequestMapping("/tax")
public class VersionTaxrateController 
{
	//http://localhost:8062/tax/versionTaxrate
//	@Autowired
//	private TaxrateService taxrateService;
//	
//	@Autowired
//	private HistoryTaxrateService historyTaxrateService;
//	
//	@Value("${app.message}")
//	private String welcomeMessage;
//	
//	@GetMapping("/versionTaxrate/welcome")
//	public String getDataBaseConnectionDetails() {
//		return welcomeMessage;
//	}
//	
//	@RequestMapping(value = "/versionTaxrate/updateVersion", method=RequestMethod.POST)
//	public ResponseEntity<List> addHistoryTaxrate() 
//	{
//		HistoryTaxrate historyrate = null;
//		try
//		{
//			List<Taxrate> list = taxrateService.findAll();
//			System.out.println("VersionTaxrateController.addHistoryTaxrate() list "+list.size());
//			List<HistoryTaxrate> listHistoryTaxrate =  new ArrayList<HistoryTaxrate>();
//			List<HistoryTaxrate> listHistoryTax = null;
//			if(list.size()>0){
//				int newVersion = getNewVersion();
//				System.out.println("VersionTaxrateController.addHistoryTaxrate() newVersion = "+newVersion); 
//				Taxrate taxrate = null;
//				HistoryTaxrate historyTaxrate = null;
//				HistoryTaxrateVersion historyTaxrateVersion = null;
//				for (int i = 0; i < list.size(); i++) {
//					taxrate = (Taxrate)list.get(i);
//					System.out.println("VersionTaxrateController.addHistoryTaxrate() taxrate "+taxrate);
//					historyTaxrateVersion = new HistoryTaxrateVersion(newVersion, taxrate.getNo());
//					historyTaxrate = new HistoryTaxrate(historyTaxrateVersion, taxrate.getMin(), taxrate.getMax(), taxrate.getRate(),
//							taxrate.getSurplus(), taxrate.getTax(), taxrate.getSumtax(), taxrate.getReserve(), taxrate.getCreate_user_code(), 
//							taxrate.getCreate_time(), taxrate.getUpdate_user_code(), taxrate.getUpdate_time());
//					System.out.println("VersionTaxrateController.addHistoryTaxrate() historyTaxrate "+historyTaxrate);
//					listHistoryTaxrate.add(historyTaxrate);
//					System.out.println("VersionTaxrateController.addHistoryTaxrate()1 listHistoryTaxrate.size() "+listHistoryTaxrate.size());
//				}
//				System.out.println("VersionTaxrateController.addHistoryTaxrate() listHistoryTaxrate.size() "+listHistoryTaxrate.size());
//				listHistoryTax = historyTaxrateService.saveAll(listHistoryTaxrate);
//			    System.out.println(listHistoryTax);
//			    if(listHistoryTax!=null){
//			    	taxrateService.deleteAll();
//			    }
//			}
//			return new ResponseEntity<List>(listHistoryTax,HttpStatus.OK);
//		}
//		catch(Exception e)
//		{
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//		}
//	}
//	private int getNewVersion(){
//		List<HistoryTaxrate> listHistoryTaxrate = historyTaxrateService.findAll();
//		System.out.println("VersionTaxrateController.getNewVersion() listHistoryTaxrate.size = "+listHistoryTaxrate.size());
//		if(listHistoryTaxrate.size()>0){
//			HistoryTaxrate historyTaxrate = (HistoryTaxrate)listHistoryTaxrate.get(listHistoryTaxrate.size()-1);
//	        HistoryTaxrateVersion historyTaxrateVersion = historyTaxrate.getHistoryTaxrateVersion();
//	        return historyTaxrateVersion.getVersion()+1;
//		}
//		return 1;
//	}
	
}