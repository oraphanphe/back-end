package ws.personnel.tax.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ws.personnel.tax.entities.PersonInfoHist;
import ws.personnel.tax.entities.PersonInfoHistGroup;
import ws.personnel.tax.service.PersonInfoHistService;

@RestController
@RequestMapping("/tax")
public class PersonInfoHistController 
{
	//http://localhost:8062/tax/personInfoHist
	@Autowired
	private PersonInfoHistService personInfoHistService;
	
	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/personInfoHist/welcome")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}
	
	@RequestMapping(value = "/personInfoHist/add", method=RequestMethod.POST)
	public ResponseEntity<PersonInfoHist> addPersonInfoHist(@RequestBody PersonInfoHist personInfoHist) 
	{
//		System.out.println("PersonInfoHistController.addPersonInfoHist() personInfoHist = "+personInfoHist.toString());
//		System.out.println("PersonInfoHistController.addPersonInfoHist() personInfoHist getNo "+personInfoHist.getTax_rate_id());
		System.out.println("PersonInfoHistController.addPersonInfoHist() personInfoHist getCreateUser "+personInfoHist.getCreateUser());
		System.out.println("PersonInfoHistController.addPersonInfoHist() personInfoHist getCitizenId"+personInfoHist.getCitizenId());
		System.out.println("PersonInfoHistController.addPersonInfoHist() personInfoHist getCreateTime "+personInfoHist.getCreateTime());
		System.out.println("PersonInfoHistController.addPersonInfoHist() personInfoHist getUpdateTime "+personInfoHist.getUpdateTime());
		PersonInfoHist rateDetail = null;
		try
		{
			rateDetail = personInfoHistService.save(personInfoHist);
			System.out.println("TaxrateController.addTaxrate() rateDetail "+rateDetail);
			System.out.println("TaxrateController.addTaxrate() rateDetail toString "+rateDetail.toString());
			return new ResponseEntity<PersonInfoHist>(rateDetail,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/personInfoHist/update", method=RequestMethod.PUT)
	public ResponseEntity<PersonInfoHist> updateTaxrate(@RequestBody PersonInfoHist personInfoHist) throws Exception 
	{
		try
		{
			System.out.println("PersonInfoHistController.updateTaxrate() personInfoHist = "+personInfoHist);
			PersonInfoHistGroup personInfoHistGroup = personInfoHist.getPersonInfoHistGroup();
			System.out.println("PersonInfoHistController.updateTaxrate() personInfoHistGroup.getPersonId() = "+personInfoHistGroup.getPersonId());
			System.out.println("PersonInfoHistController.updateTaxrate() personInfoHistGroup.getEffectiveDate() = "+personInfoHistGroup.getEffectiveDate());
			Optional<PersonInfoHist> old = personInfoHistService.findById(personInfoHistGroup.getPersonId(), personInfoHistGroup.getEffectiveDate());
			System.out.println("PersonInfoHistController.updateTaxrate() old "+old);
			System.out.println("PersonInfoHistController.updateTaxrate() old.isPresent() "+old.isPresent());
			if (old.isPresent()) 
			{
				personInfoHist.setCreateTime(old.get().getCreateTime());
				personInfoHist.setCreateUser(old.get().getCreateUser());
				PersonInfoHist personInfoHistLast = personInfoHistService.save(personInfoHist);
				return new ResponseEntity<PersonInfoHist>(personInfoHist,HttpStatus.OK);
			}
			else
			{
				System.out.printf("No found with PersonId :"+ personInfoHistGroup.getPersonId()+" and EffectiveDate "+personInfoHistGroup.getEffectiveDate());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  PersonId: "+personInfoHistGroup.getPersonId()+" and EffectiveDate "+personInfoHistGroup.getEffectiveDate()+" ที่ใช้ในการอัพเดต!");
			}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
//	//URL - http://localhost:8061/tax/personInfoHist/delete?person_id=1&effective_date=2
//	@RequestMapping(value = "/personInfoHist/delete", method=RequestMethod.DELETE)
//	public ResponseEntity<String> deleteHistoryTaxrate(@RequestParam("person_id") int personId,
//			@RequestParam("effective_date") String effectiveDate)
//	{
//		
//		System.out.println("HistoryTaxrateController.deleteHistoryTaxrate() personId "+personId);
//		System.out.println("HistoryTaxrateController.deleteHistoryTaxrate() effectiveDate  "+effectiveDate);
//		try
//		{
//			System.out.println("HistoryTaxrateController.deleteHistoryTaxrate() personId = "+personId+" : effectiveDate = "+effectiveDate);
//			personInfoHistService.delete(personId, effectiveDate);
//			return new ResponseEntity<>("success",HttpStatus.OK);
//			
//		}
//		catch(Exception e)
//		{
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล  personId = "+personId+" : effectiveDate = "+effectiveDate+" ได้");
//		}
//	}


	
//	@RequestMapping(value = "/personInfoHist/searchByKey", method=RequestMethod.POST)///personInfoHist/searchByKey?personId=1&effectiveDate=2
//	public ResponseEntity<List<PersonInfoHist>> searchPersonInfoHistByKey(@RequestParam("person_id") int personId,
//			@RequestParam("effective_date") String effectiveDate)
//	{
//		System.out.println("searchPersonInfoHist personId = "+personId);
//		System.out.println("searchPersonInfoHist effectiveDate = "+effectiveDate);
//		try
//		{
//			List<PersonInfoHist> list = new ArrayList<PersonInfoHist>();
//			
//				Optional<PersonInfoHist> his = personInfoHistService.findById(personId, effectiveDate);
//				System.out.println("PersonInfoHistController.searchPersonInfoHist() his "+his);
//				System.out.println("PersonInfoHistController.searchPersonInfoHist() his "+his.toString());
//				if (his.isPresent()) 
//				{
//					list.add(his.get());
//					for(int i=0;i<list.size();i++)
//					{
//						PersonInfoHist en = (PersonInfoHist)list.get(i);
//						System.out.println("result searchPersonInfoHist : "+en);
//					}
//					return new ResponseEntity<List<PersonInfoHist>>(list,HttpStatus.OK);
//			    } 
//				else 
//				{
//					
//			        System.out.printf("no found with  personId = "+personId+" : effectiveDate = "+effectiveDate);
//			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  personId = "+personId+" : effectiveDate = "+effectiveDate);
//			    }
//			
//		}
//		catch(Exception e)
//		{
//			System.out.println("Error searchPersonInfoHist : "+e.getMessage());
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
//		}
//	}
	
	@RequestMapping(value = "/personInfoHist/search", method=RequestMethod.POST)///personInfoHist/search?type=A
	public ResponseEntity<List<PersonInfoHist>> searchPersonInfoHist(@RequestParam("type") String type)
	{
		System.out.println("searchPersonInfoHist type = "+type);
		try
		{
			List<PersonInfoHist> list = new ArrayList<PersonInfoHist>();
			if(type.equals("A"))
			    list = personInfoHistService.findAll();
			if(list.size()>0){
				for(int i=0;i<list.size();i++)
				{
					PersonInfoHist en = (PersonInfoHist)list.get(i);
					System.out.println("result searchPersonInfoHist : "+en);
				}
				return new ResponseEntity<List<PersonInfoHist>>(list,HttpStatus.OK);
			} 
			else 
			{
			    System.out.printf("no found with  type = "+type);
			    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  type = "+type);
			}			
		}
		catch(Exception e)
		{
			System.out.println("Error searchPersonInfoHist : "+e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	
//	http://localhost:8061/tax/personInfoHist/searchGET
	@RequestMapping(value = "/personInfoHist/searchGET", method=RequestMethod.GET)
	public ResponseEntity<List<PersonInfoHist>> searchPersonInfoHistGet() 
	{
		System.out.println("searchPersonInfoHistGet");

//		List<PersonInfoHist> list = personInfoHistService.findAllSortKey();
		List<PersonInfoHist> list = personInfoHistService.findAll();
		return new ResponseEntity<List<PersonInfoHist>>(list,HttpStatus.OK);
	}
	
}