package ws.personnel.tax.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import ws.personnel.tax.entities.TaxPersonInfo;
import ws.personnel.tax.service.TaxPersonInfoService;

@RestController
@RequestMapping("/tax")
public class TaxPersonInfoController {
	//http://localhost:8062/tax/taxPersonInfo
		@Autowired
		private TaxPersonInfoService taxPersonInfoService;
		
		@Value("${app.message}")
		private String welcomeMessage;
		
		@GetMapping("/taxPersonInfo/welcome")
		public String getDataBaseConnectionDetails() {
			return welcomeMessage;
		}
		
		@RequestMapping(value = "/taxPersonInfo/add", method=RequestMethod.POST)
		public ResponseEntity<TaxPersonInfo> addTaxPersonInfo(@RequestBody TaxPersonInfo taxPersonInfo) 
		{
			System.out.println("TaxPersonInfoController.addTaxPersonInfo() taxPersonInfo = "+taxPersonInfo.toString());
			TaxPersonInfo taxc = null;
			try
			{
//				taxPersonInfo.setStatus(enu_status.active.toString());
				taxc = taxPersonInfoService.save(taxPersonInfo);
				return new ResponseEntity<TaxPersonInfo>(taxc,HttpStatus.OK);
			}
			catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
		@RequestMapping(value = "/taxPersonInfo/update", method=RequestMethod.PUT)
		public ResponseEntity<TaxPersonInfo> updateTaxPersonInfo(@RequestBody TaxPersonInfo taxPersonInfo) throws Exception 
		{
			try
			{
				Optional<TaxPersonInfo> old = taxPersonInfoService.findById(taxPersonInfo.getSalseId());
				
				if (old.isPresent()) 
				{
					taxPersonInfo.setCreateTime(old.get().getCreateTime());
					taxPersonInfo.setCreateUser(old.get().getCreateUser());
					TaxPersonInfo taxPersonInfoLast = taxPersonInfoService.save(taxPersonInfo);
					return new ResponseEntity<TaxPersonInfo>(taxPersonInfoLast,HttpStatus.OK);
				}
				else
				{
					System.out.printf("No found with SalseId :"+ taxPersonInfo.getSalseId());
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ SalseId: "+taxPersonInfo.getSalseId()+" ที่ใช้ในการอัพเดต!");
				}
			}
			catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}

	//http://localhost:8061/tax/taxPersonInfo/delete/1
		@RequestMapping(value = "/taxPersonInfo/delete/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<String> deleteTaxPersonInfo(@PathVariable("id") String id)
		{
			
			try
			{
				System.out.println("deleteTaxPersonInfo========"+id);
				taxPersonInfoService.delete(id);
				return new ResponseEntity<>("success",HttpStatus.OK);
				
			}
			catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล no :"+id+" ได้");
			}
		}
		@RequestMapping(value = "/taxPersonInfo/search", method=RequestMethod.POST)///taxPersonInfo/search?type=A
		public ResponseEntity<List<TaxPersonInfo>> searchTaxPersonInfo(@RequestParam("type") String type) 
		{
			System.out.println("searchTaxPersonInfo type = "+type);
			try
			{
				List<TaxPersonInfo> listEx = new ArrayList<TaxPersonInfo>();
				if(type.equals("A"))
				{
//					List<TaxPersonInfo> list = taxPersonInfoService.findAllSortKey();
					List<TaxPersonInfo> list = taxPersonInfoService.findAll();
					for(int i=0;i<listEx.size();i++)
					{
						TaxPersonInfo tc = (TaxPersonInfo)listEx.get(i);
						System.out.println("result searchTaxPersonInfo : "+tc.toString());
					}
					System.out.println("TaxPersonInfoController.searchTaxPersonInfo() list "+list);
					System.out.println("TaxPersonInfoController.searchTaxPersonInfo() list "+list.toString());
					return new ResponseEntity<List<TaxPersonInfo>>(list,HttpStatus.OK);
				}
				else
				{
					Optional<TaxPersonInfo> tc = taxPersonInfoService.findById(type);
					System.out.println("TaxPersonInfoController.searchTaxPersonInfo() tc "+tc);
					System.out.println("TaxPersonInfoController.searchTaxPersonInfo() tc "+tc.toString());
					if (tc.isPresent()) 
					{
						listEx.add(tc.get());
						for(int i=0;i<listEx.size();i++)
						{
							TaxPersonInfo en = (TaxPersonInfo)listEx.get(i);
							System.out.println("result searchTaxPersonInfo : "+en);
						}
						return new ResponseEntity<List<TaxPersonInfo>>(listEx,HttpStatus.OK);
				    } 
					else 
					{
						
				        System.out.printf("No no found with TaxPersonInfo :"+ type);
				        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  no :"+type);
				    }
				}
			}
			catch(Exception e)
			{
				System.out.println("Error TaxPersonInfo : "+e.getMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
			}
		}
		
//		http://localhost:8061/tax/taxPersonInfo/searchGET
		@RequestMapping(value = "/taxPersonInfo/searchGET", method=RequestMethod.GET)
		public ResponseEntity<List<TaxPersonInfo>> searchTaxPersonInfoGet() 
		{
			System.out.println("searchTaxPersonInfoGet");

//			List<TaxPersonInfo> list = taxPersonInfoService.findAllSortKey();
			List<TaxPersonInfo> list = taxPersonInfoService.findAll();
			return new ResponseEntity<List<TaxPersonInfo>>(list,HttpStatus.OK);
		}
		
}