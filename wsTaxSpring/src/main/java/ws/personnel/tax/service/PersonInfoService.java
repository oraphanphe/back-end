package ws.personnel.tax.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ws.personnel.tax.entities.PersonInfo;
import ws.personnel.tax.repository.PersonInfoRepository;

@Service
public class PersonInfoService {
	@Autowired
	private PersonInfoRepository personInfoRepository;

	public List<PersonInfo> findAll()
	{
		return  personInfoRepository.findAll();
	}
	public List<PersonInfo> findAllSortKey()
	{
		return  personInfoRepository.findAll(Sort.by(Sort.Direction.ASC, "personId"));
	}

	public Optional<PersonInfo> findById(int personId) 
	{
		return personInfoRepository.findById(personId);
	}

	public PersonInfo save(PersonInfo personInfo)
	{
		System.out.println("PersonInfoService.save() personInfo getPersonId "+personInfo.getPersonId());
		System.out.println("PersonInfoService.save() personInfo getStatus "+personInfo.getStatus());
		return  personInfoRepository.save(personInfo);
	}
	public String delete(int personId) 
	{
		System.out.println("PersonInfoService.delete() personId "+personId);
		personInfoRepository.deleteById(personId);
		return "no with personId :"+personId+" is deleted"; 
	}
	public String delete(PersonInfo personInfo) 
	{
		System.out.println("PersonInfoService.delete() personInfo.getTax_catalog_id()  "+personInfo.getPersonId());
		personInfoRepository.delete(personInfo);
		return "tax_catalog_id :"+personInfo.getPersonId()+" is deleted"; 
	}
	public String deleteAll() 
	{
		System.out.println("PersonInfoService.deleteAll() ");
	    personInfoRepository.deleteAll();
		return "delete all success"; 
	}
}
