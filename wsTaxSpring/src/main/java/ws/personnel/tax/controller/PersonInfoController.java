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

import ws.personnel.tax.entities.PersonInfo;
import ws.personnel.tax.entities.PersonInfoHist;
import ws.personnel.tax.entities.PersonInfoHistGroup;
import ws.personnel.tax.service.PersonInfoHistService;
import ws.personnel.tax.service.PersonInfoService;

@RestController
@RequestMapping("/tax")
public class PersonInfoController {
	//http://localhost:8062/tax/personInfo
		@Autowired
		private PersonInfoService personInfoService;
		@Autowired
		private PersonInfoHistService personInfoHistService;
		
		@Value("${app.message}")
		private String welcomeMessage;
		
		@GetMapping("/personInfo/welcome")
		public String getDataBaseConnectionDetails() {
			return welcomeMessage;
		}
		
		@RequestMapping(value = "/personInfo/add", method=RequestMethod.POST)
		public ResponseEntity<PersonInfo> addPersonInfo(@RequestBody PersonInfo personInfo) 
		{
			System.out.println("PersonInfoController.addPersonInfo() START");
			System.out.println("PersonInfoController.addPersonInfo() personInfo "+personInfo.getEffectiveDate());
			System.out.println("PersonInfoController.addPersonInfo() personInfo = "+personInfo.toString());
			PersonInfo taxc = null;
			try
			{
				
				personInfo.toString();
//				personInfo.setStatus(enu_status.active.toString());
				taxc = personInfoService.save(personInfo);
				return new ResponseEntity<PersonInfo>(taxc,HttpStatus.OK);
			}
			catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
		@RequestMapping(value = "/personInfo/update", method=RequestMethod.PUT)
		public ResponseEntity<PersonInfo> updatePersonInfo(@RequestBody PersonInfo personInfo) throws Exception 
		{
			try
			{
				System.out.println("PersonInfoController.updatePersonInfo() personInfo.getEffectiveDate() "+personInfo.getEffectiveDate());
				System.out.println("PersonInfoController.updatePersonInfo() personInfo.getPersonId() "+personInfo.getPersonId());
				Optional<PersonInfo> old = personInfoService.findById(personInfo.getPersonId());
				System.out.println("PersonInfoController.updatePersonInfo() old.isPresent() "+old.isPresent());
				if (old.isPresent()) 
				{
					PersonInfoHistGroup personInfoHistGroup = new PersonInfoHistGroup(old.get().getPersonId(), old.get().getEffectiveDate());
					PersonInfoHist personInfoHistOld = new PersonInfoHist(personInfoHistGroup, old.get().getCitizenId(), old.get().getTaxId(), 
							old.get().getSocId(), old.get().getPreName(), old.get().getFirstName(), old.get().getLastName(), old.get().getAddress(), 
							old.get().getTambon(), old.get().getZipcode(), old.get().getEmail(), old.get().getPhone(), old.get().getStatus(), 
							old.get().getCreateUser(), old.get().getCreateTime(), old.get().getUpdateUser(), old.get().getUpdateTime());
					System.out.println("PersonInfoController.updatePersonInfo() (old.get().getPersonId() = "+old.get().getPersonId());
					System.out.println("PersonInfoController.updatePersonInfo() (old.get().getEffectiveDate() = "+old.get().getEffectiveDate());
					PersonInfoHist personInfoHistNew = personInfoHistService.save(personInfoHistOld);
					System.out.println("PersonInfoController.updatePersonInfo() (old.get().getCreateTime() = "+old.get().getCreateTime());
					personInfo.setCreateTime(old.get().getCreateTime());
					personInfo.setCreateUser(old.get().getCreateUser());
					PersonInfo personInfoLast = personInfoService.save(personInfo);
					return new ResponseEntity<PersonInfo>(personInfoLast,HttpStatus.OK);
				}
				else
				{
					System.out.printf("No found with PersonId :"+ personInfo.getPersonId());
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  personId: "+personInfo.getPersonId()+" ที่ใช้ในการอัพเดต!");
				}
			}
			catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}

	//http://localhost:8061/tax/personInfo/delete/1
		@RequestMapping(value = "/personInfo/delete/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<String> deletePersonInfo(@PathVariable("id") int id)
		{
			
			try
			{
				System.out.println("deletePersonInfo========"+id);
				personInfoService.delete(id);
				return new ResponseEntity<>("success",HttpStatus.OK);
				
			}
			catch(Exception e)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล no :"+id+" ได้");
			}
		}
		@RequestMapping(value = "/personInfo/search", method=RequestMethod.POST)///personInfo/search?type=A
		public ResponseEntity<List<PersonInfo>> searchPersonInfo(@RequestParam("type") String type) 
		{
			System.out.println("searchPersonInfo type = "+type);
			try
			{
				List<PersonInfo> listEx = new ArrayList<PersonInfo>();
				if(type.equals("A"))
				{
//					List<PersonInfo> list = personInfoService.findAllSortKey();
					List<PersonInfo> list = personInfoService.findAll();
					for(int i=0;i<listEx.size();i++)
					{
						PersonInfo tc = (PersonInfo)listEx.get(i);
						System.out.println("result searchPersonInfo : "+tc.toString());
					}
					System.out.println("PersonInfoController.searchPersonInfo() list "+list);
					System.out.println("PersonInfoController.searchPersonInfo() list "+list.toString());
					return new ResponseEntity<List<PersonInfo>>(list,HttpStatus.OK);
				}
				else
				{
					Optional<PersonInfo> tc = personInfoService.findById(Integer.parseInt(type));
					System.out.println("PersonInfoController.searchPersonInfo() tc "+tc);
					System.out.println("PersonInfoController.searchPersonInfo() tc "+tc.toString());
					if (tc.isPresent()) 
					{
						listEx.add(tc.get());
						for(int i=0;i<listEx.size();i++)
						{
							PersonInfo en = (PersonInfo)listEx.get(i);
							System.out.println("result searchPersonInfo : "+en);
						}
						return new ResponseEntity<List<PersonInfo>>(listEx,HttpStatus.OK);
				    } 
					else 
					{
						
				        System.out.printf("No PersonId found with PersonInfo :"+ type);
				        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  PersonId :"+type);
				    }
				}
			}
			catch(Exception e)
			{
				System.out.println("Error PersonInfo : "+e.getMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
			}
		}
		
//		http://localhost:8061/tax/personInfo/searchGET
		@RequestMapping(value = "/personInfo/searchGET", method=RequestMethod.GET)
		public ResponseEntity<List<PersonInfo>> searchPersonInfoGet() 
		{
			System.out.println("searchPersonInfoGet");

//			List<PersonInfo> list = personInfoService.findAllSortKey();
			List<PersonInfo> list = personInfoService.findAll();
			return new ResponseEntity<List<PersonInfo>>(list,HttpStatus.OK);
		}
		
}