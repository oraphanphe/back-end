package ws.personnel.tax.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.personnel.tax.entities.PersonInfoHist;
import ws.personnel.tax.entities.PersonInfoHistGroup;
import ws.personnel.tax.repository.PersonInfoHistRepository;

@Service
public class PersonInfoHistService {
	@Autowired
	private PersonInfoHistRepository personInfoHistRepository;

	public List<PersonInfoHist> findAll()
	{
		return  personInfoHistRepository.findAll();
	}
	public Optional<PersonInfoHist> findById(int personId, Date effectiveDate) 
	{
		PersonInfoHistGroup personInfoHistGroup = new PersonInfoHistGroup(personId, effectiveDate);
		return personInfoHistRepository.findById(personInfoHistGroup);
	}
	
//	public List<PersonInfoHist> findByTax_rate_id(String tax_rate_id) 
//	{
////		PersonInfoHistGroup personInfoHistGroup = new PersonInfoHistGroup(tax_rate_id, rate_no);
//		return personInfoHistRepository.findByTax_rate_id(tax_rate_id);
//	}
	
	public PersonInfoHist save(PersonInfoHist personInfoHist)
	{
		System.out.println("PersonInfoHistService.save() START");
		System.out.println("PersonInfoHistService.save() personInfoHist getMin_amt "+personInfoHist.getCitizenId());
		System.out.println("PersonInfoHistService.save() personInfoHist getTax_rate_id "+personInfoHist.getPersonInfoHistGroup().getPersonId());
		System.out.println("PersonInfoHistService.save() personInfoHist getRate_no "+personInfoHist.getPersonInfoHistGroup().getEffectiveDate());
		
		return  personInfoHistRepository.save(personInfoHist);
	}
	public String delete(int personId, Date effectiveDate) 
	{
		PersonInfoHistGroup personInfoHistGroup = new PersonInfoHistGroup(personId, effectiveDate);
		personInfoHistRepository.deleteById(personInfoHistGroup);
		return "no with personId :"+personId+" and effectiveDate :"+effectiveDate+" is deleted"; 
	}
	public String delete(PersonInfoHist personInfoHist) 
	{
		personInfoHistRepository.delete(personInfoHist);
		return "no with personId :"+personInfoHist.getPersonInfoHistGroup().getPersonId()+" and effectiveDate :"+personInfoHist.getPersonInfoHistGroup().getEffectiveDate()+" is deleted"; 
	}
	public String deleteAll() 
	{
		System.out.println("PersonInfoHistService.deleteAll() ");
		personInfoHistRepository.deleteAll();
		return "delete all success"; 
	}
}