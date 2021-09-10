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
import ws.personnel.tax.entities.HistoryTaxrate;
import ws.personnel.tax.entities.HistoryTaxrateVersion;
import ws.personnel.tax.service.HistoryTaxrateService;

@RestController
@RequestMapping("/tax")
public class HistoryTaxrateController 
{
	//http://localhost:8062/tax/historyTaxrate
	@Autowired
	private HistoryTaxrateService historyTaxrateService;
	
	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/historyTaxrate/welcome")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}
	
	@RequestMapping(value = "/historyTaxrate/add", method=RequestMethod.POST)
	public ResponseEntity<HistoryTaxrate> addHistoryTaxrate(@RequestBody HistoryTaxrate  historyTaxrate) 
	{
		HistoryTaxrate historyrate = null;
		try
		{
			historyrate = historyTaxrateService.save(historyTaxrate);
			System.out.println(historyrate);
			return new ResponseEntity<HistoryTaxrate>(historyrate,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/historyTaxrate/update", method=RequestMethod.PUT)
	public ResponseEntity<HistoryTaxrate> updateTaxrate(@RequestBody HistoryTaxrate historyTaxrate) throws Exception 
	{
		try
		{
			HistoryTaxrateVersion historyTaxrateVersion = historyTaxrate.getHistoryTaxrateVersion();
			Optional<HistoryTaxrate> old = historyTaxrateService.findByKey(historyTaxrateVersion.getVersion(), historyTaxrateVersion.getNo());
			
			if (old.isPresent()) 
			{
				historyTaxrate.setCreate_time(old.get().getCreate_time());
				historyTaxrate.setCreate_user_code(old.get().getCreate_user_code());
				HistoryTaxrate historyTaxrateLast = historyTaxrateService.save(historyTaxrate);
				return new ResponseEntity<HistoryTaxrate>(historyTaxrateLast,HttpStatus.OK);
			}
			else
			{
				System.out.printf("Version found with version :"+ historyTaxrateVersion.getVersion()+" no : "+historyTaxrateVersion.getNo());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  version :"+ historyTaxrateVersion.getVersion()+" no : "+historyTaxrateVersion.getNo()+" ที่ใช้ในการอัพเดต!");
			}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	
//	-----------------------------@PathVariable------------------------
//	http://localhost:8061/tax/historyTaxrate/delete/1/2
//	@RequestMapping(value = "/historyTaxrate/delete/{version}/{no}", method=RequestMethod.DELETE)
//	public ResponseEntity<String> deleteHistoryTaxrate(@PathVariable("version") int version,
//			@PathVariable("no") String no) 
//	-----------------------------@Param----------------------
	//URL - http://localhost:8061/tax/historyTaxrate/delete?version=1&no=2
	@RequestMapping(value = "/historyTaxrate/delete", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteHistoryTaxrate(@RequestParam("version") int version,
			@RequestParam("no") String no)
	{
		
		System.out.println("HistoryTaxrateController.deleteHistoryTaxrate() version "+version);
		System.out.println("HistoryTaxrateController.deleteHistoryTaxrate() no  "+no);
		try
		{
			System.out.println("HistoryTaxrateController.deleteHistoryTaxrate() version = "+version+" : no = "+no);
			historyTaxrateService.delete(version, no);
			return new ResponseEntity<>("success",HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล  version = "+version+" : no = "+no+" ได้");
		}
	}
	@RequestMapping(value = "/historyTaxrate/search", method=RequestMethod.POST)///historyTaxrate/search?version=1&no=2
	public ResponseEntity<List<HistoryTaxrate>> searchHistoryTaxrate(@RequestParam("version") int version,
			@RequestParam("no") String no)
	{
		System.out.println("searchHistoryTaxrate version = "+version);
		System.out.println("searchHistoryTaxrate no = "+no);
		try
		{
			List<HistoryTaxrate> listHis = new ArrayList<HistoryTaxrate>();
			
				Optional<HistoryTaxrate> his = historyTaxrateService.findByKey(version, no);
				System.out.println("TaxrateController.searchHistoryTaxrate() his "+his);
				System.out.println("TaxrateController.searchHistoryTaxrate() his "+his.toString());
				if (his.isPresent()) 
				{
					listHis.add(his.get());
					for(int i=0;i<listHis.size();i++)
					{
						HistoryTaxrate en = (HistoryTaxrate)listHis.get(i);
						System.out.println("result searchHistoryTaxrate : "+en);
					}
					return new ResponseEntity<List<HistoryTaxrate>>(listHis,HttpStatus.OK);
			    } 
				else 
				{
					
			        System.out.printf("version no found with  version = "+version+" : no = "+no);
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  version = "+version+" : no = "+no);
			    }
			
		}
		catch(Exception e)
		{
			System.out.println("Error searchHistoryTaxrate : "+e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/historyTaxrate/searchGET", method=RequestMethod.GET)
	public ResponseEntity<List<HistoryTaxrate>> searchHistoryTaxrateGet() 
	{
		System.out.println("searchHistoryTaxrateGet");

		List<HistoryTaxrate> list = historyTaxrateService.findAll();
		return new ResponseEntity<List<HistoryTaxrate>>(list,HttpStatus.OK);
	}
	
}