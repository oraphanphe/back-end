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

import ws.personnel.tax.entities.TaxRateDetail;
import ws.personnel.tax.service.TaxRateDetailService;

@RestController
@RequestMapping("/tax")
public class TaxRateDetailController 
{
	//http://localhost:8062/tax/taxRateDetail
	@Autowired
	private TaxRateDetailService taxRateDetailService;
	
	@Value("${app.message}")
	private String welcomeMessage;
	
	@GetMapping("/taxRateDetail/welcome")
	public String getDataBaseConnectionDetails() {
		return welcomeMessage;
	}
	
	@RequestMapping(value = "/taxRateDetail/add", method=RequestMethod.POST)
	public ResponseEntity<TaxRateDetail> addTaxRateDetail(@RequestBody TaxRateDetail taxRateDetail) 
	{
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail = "+taxRateDetail.toString());
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail getNo "+taxRateDetail.getTax_rate_id());
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail getCreate_user_code "+taxRateDetail.getCreate_user());
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail getMin"+taxRateDetail.getMin_amt());
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail getCreate_time "+taxRateDetail.getCreate_time());
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail getCreate_time "+taxRateDetail.getUpdate_time());
		TaxRateDetail rateDetail = null;
		try
		{
			rateDetail = taxRateDetailService.save(taxRateDetail);
			System.out.println("TaxrateController.addTaxrate() rateDetail "+rateDetail);
			System.out.println("TaxrateController.addTaxrate() rateDetail toString "+rateDetail.toString());
			return new ResponseEntity<TaxRateDetail>(rateDetail,HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	@RequestMapping(value = "/taxRateDetail/update", method=RequestMethod.PUT)
	public ResponseEntity<TaxRateDetail> updateTaxrate(@RequestBody TaxRateDetail taxRateDetail) throws Exception 
	{
		try
		{
			Optional<TaxRateDetail> old = taxRateDetailService.findById(taxRateDetail.getTax_rate_id());
			
			if (old.isPresent()) 
			{
				taxRateDetail.setCreate_time(old.get().getCreate_time());
				taxRateDetail.setCreate_user(old.get().getCreate_user());
				TaxRateDetail taxRateDetailLast = taxRateDetailService.save(taxRateDetail);
				return new ResponseEntity<TaxRateDetail>(taxRateDetail,HttpStatus.OK);
			}
			else
			{
				System.out.printf("No found with no :"+ taxRateDetail.getTax_rate_id());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ no: "+taxRateDetail.getTax_rate_id()+" ที่ใช้ในการอัพเดต!");
			}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

//http://localhost:8061/tax/taxRateDetail/delete/1
	@RequestMapping(value = "/taxRateDetail/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteTaxRateDetail(@PathVariable("id") String id)
	{
		
		try
		{
//			Optional<Taxrate> ex = taxrateService.findById(id);
//			if (ex.isPresent()) 
//			{
//				List<Taxrate> listEx = new ArrayList<Taxrate>();
//				listEx.add(ex.get());
//				for(int i=0;i<listEx.size();i++)
//				{
//					Taxrate en = (Taxrate)listEx.get(i);
//					System.out.println("result searchTaxrate : "+en);
//					taxrateService.delete(id);
//				}
//				return new ResponseEntity<>("success",HttpStatus.OK);
//		    } 
//			else 
//			{
//				
//		        System.out.printf("No no found with id :"+ id);
//		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  no :"+id);
//		    }
			System.out.println("deleteTaxRateDetail========"+id);
			taxRateDetailService.delete(id);
			return new ResponseEntity<>("success",HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล no :"+id+" ได้");
		}
	}
	@RequestMapping(value = "/taxRateDetail/search", method=RequestMethod.POST)///taxrate/search?type=A
	public ResponseEntity<List<TaxRateDetail>> searchTaxRateDetail(@RequestParam("type") String type) 
	{
		System.out.println("searchTaxRateDetail type = "+type);
		try
		{
			List<TaxRateDetail> listEx = new ArrayList<TaxRateDetail>();
			if(type.equals("A"))
			{
				List<TaxRateDetail> list = taxRateDetailService.findAll();
				for(int i=0;i<listEx.size();i++)
				{
					TaxRateDetail en = (TaxRateDetail)listEx.get(i);
					System.out.println("result searchTaxRateDetail getNo : "+en.getRate_no());
					System.out.println("result searchTaxRateDetail getMin : "+en.getMin_amt());
					System.out.println("result searchTaxRateDetail : "+en.toString());
				}
				System.out.println("TaxRateDetailController.searchTaxRateDetail() list "+list);
				System.out.println("TaxRateDetailController.searchTaxRateDetail() list "+list.toString());
				return new ResponseEntity<List<TaxRateDetail>>(list,HttpStatus.OK);
			}
			else
			{
				Optional<TaxRateDetail> ex = taxRateDetailService.findById(type);
				System.out.println("TaxRateDetailController.searchTaxRateDetail() ex "+ex);
				System.out.println("TaxRateDetailController.searchTaxRateDetail() ex "+ex.toString());
				if (ex.isPresent()) 
				{
					listEx.add(ex.get());
					for(int i=0;i<listEx.size();i++)
					{
						TaxRateDetail en = (TaxRateDetail)listEx.get(i);
						System.out.println("result searchTaxRateDetail : "+en);
					}
					return new ResponseEntity<List<TaxRateDetail>>(listEx,HttpStatus.OK);
			    } 
				else 
				{
					
			        System.out.printf("No no found with id :"+ type);
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  no :"+type);
			    }
			}
		}
		catch(Exception e)
		{
			System.out.println("Error searchTaxRateDetail : "+e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
//	http://localhost:8061/tax/taxRateDetail/searchGET
	@RequestMapping(value = "/taxRateDetail/searchGET", method=RequestMethod.GET)
	public ResponseEntity<List<TaxRateDetail>> searchTaxRateDetailGet() 
	{
		System.out.println("searchTaxRateDetailGet");

//		List<TaxRateDetail> list = taxRateDetailService.findAllSortKey();
		List<TaxRateDetail> list = taxRateDetailService.findAll();
		return new ResponseEntity<List<TaxRateDetail>>(list,HttpStatus.OK);
	}
	
}
