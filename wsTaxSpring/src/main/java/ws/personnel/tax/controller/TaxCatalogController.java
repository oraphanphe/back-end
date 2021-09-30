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

import ws.personnel.tax.entities.TaxCatalog;
import ws.personnel.tax.service.TaxCatalogService;

@RestController
@RequestMapping("/tax")
public class TaxCatalogController {
	//http://localhost:8062/tax/taxCatalog
		@Autowired
		private TaxCatalogService taxCatalogService;
		
		@Value("${app.message}")
		private String welcomeMessage;
		
		@GetMapping("/taxCatalog/welcome")
		public String getDataBaseConnectionDetails() {
			return welcomeMessage;
		}
		
		@RequestMapping(value = "/taxCatalog/add", method=RequestMethod.POST)
		public ResponseEntity<TaxCatalog> addTaxCatalog(@RequestBody TaxCatalog taxCatalog) 
		{
			System.out.println("TaxCatalogController.addTaxCatalog() taxCatalog = "+taxCatalog.toString());
			TaxCatalog taxc = null;
			try
			{
//				taxCatalog.setStatus(enu_status.active.toString());
				taxc = taxCatalogService.save(taxCatalog);
				return new ResponseEntity<TaxCatalog>(taxc,HttpStatus.OK);
			}
			catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
		@RequestMapping(value = "/taxCatalog/update", method=RequestMethod.PUT)
		public ResponseEntity<TaxCatalog> updateTaxCatalog(@RequestBody TaxCatalog taxCatalog) throws Exception 
		{
			try
			{
				Optional<TaxCatalog> old = taxCatalogService.findById(taxCatalog.getTaxCatalogId());
				
				if (old.isPresent()) 
				{
					taxCatalog.setCreateTime(old.get().getCreateTime());
					taxCatalog.setCreateUser(old.get().getCreateUser());
					TaxCatalog taxCatalogLast = taxCatalogService.save(taxCatalog);
					return new ResponseEntity<TaxCatalog>(taxCatalogLast,HttpStatus.OK);
				}
				else
				{
					System.out.printf("No found with TaxCatalogId :"+ taxCatalog.getTaxCatalogId());
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ TaxCatalogId: "+taxCatalog.getTaxCatalogId()+" ที่ใช้ในการอัพเดต!");
				}
			}
			catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}

	//http://localhost:8061/tax/taxCatalog/delete/1
		@RequestMapping(value = "/taxCatalog/delete/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<String> deleteTaxCatalog(@PathVariable("id") String id)
		{
			
			try
			{
				System.out.println("deleteTaxCatalog========"+id);
				taxCatalogService.delete(id);
				return new ResponseEntity<>("success",HttpStatus.OK);
				
			}
			catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล no :"+id+" ได้");
			}
		}
		@RequestMapping(value = "/taxCatalog/search", method=RequestMethod.POST)///taxCatalog/search?type=A
		public ResponseEntity<List<TaxCatalog>> searchTaxCatalog(@RequestParam("type") String type) 
		{
			System.out.println("searchTaxCatalog type = "+type);
			try
			{
				List<TaxCatalog> listEx = new ArrayList<TaxCatalog>();
				if(type.equals("A"))
				{
//					List<TaxCatalog> list = taxCatalogService.findAllSortKey();
					List<TaxCatalog> list = taxCatalogService.findAll();
					for(int i=0;i<listEx.size();i++)
					{
						TaxCatalog tc = (TaxCatalog)listEx.get(i);
						System.out.println("result searchTaxCatalog : "+tc.toString());
					}
					System.out.println("TaxCatalogController.searchTaxCatalog() list "+list);
					System.out.println("TaxCatalogController.searchTaxCatalog() list "+list.toString());
					return new ResponseEntity<List<TaxCatalog>>(list,HttpStatus.OK);
				}
				else
				{
					Optional<TaxCatalog> tc = taxCatalogService.findById(type);
					System.out.println("TaxCatalogController.searchTaxCatalog() tc "+tc);
					System.out.println("TaxCatalogController.searchTaxCatalog() tc "+tc.toString());
					if (tc.isPresent()) 
					{
						listEx.add(tc.get());
						for(int i=0;i<listEx.size();i++)
						{
							TaxCatalog en = (TaxCatalog)listEx.get(i);
							System.out.println("result searchTaxCatalog : "+en);
						}
						return new ResponseEntity<List<TaxCatalog>>(listEx,HttpStatus.OK);
				    } 
					else 
					{
						
				        System.out.printf("No no found with TaxCatalog :"+ type);
				        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  no :"+type);
				    }
				}
			}
			catch(Exception e)
			{
				System.out.println("Error TaxCatalog : "+e.getMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
			}
		}
		
//		http://localhost:8061/tax/taxCatalog/searchGET
		@RequestMapping(value = "/taxCatalog/searchGET", method=RequestMethod.GET)
		public ResponseEntity<List<TaxCatalog>> searchTaxCatalogGet() 
		{
			System.out.println("searchTaxCatalogGet");

//			List<TaxCatalog> list = taxCatalogService.findAllSortKey();
			List<TaxCatalog> list = taxCatalogService.findAll();
			return new ResponseEntity<List<TaxCatalog>>(list,HttpStatus.OK);
		}
		
}
