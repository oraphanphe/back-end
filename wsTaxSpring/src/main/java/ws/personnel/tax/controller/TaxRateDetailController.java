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

import ws.personnel.tax.entities.TaxCatalog;
import ws.personnel.tax.entities.TaxRateDetail;
import ws.personnel.tax.entities.TaxRateDetailGroup;
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
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail getCreateUser "+taxRateDetail.getCreateUser());
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail getMinAmt"+taxRateDetail.getMinAmt());
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail getCreateTime "+taxRateDetail.getCreateTime());
		System.out.println("TaxRateDetailController.addTaxRateDetail() taxRateDetail getUpdateTime "+taxRateDetail.getUpdateTime());
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
			System.out.println("TaxRateDetailController.updateTaxrate() taxRateDetail = "+taxRateDetail);
			TaxRateDetailGroup taxRateDetailGroup = taxRateDetail.getTaxRateDetailGroup();
			System.out.println("TaxRateDetailController.updateTaxrate() taxRateDetailGroup.getTaxRateId() = "+taxRateDetailGroup.getTaxRateId());
			System.out.println("TaxRateDetailController.updateTaxrate() taxRateDetailGroup.getRateNo() = "+taxRateDetailGroup.getRateNo());
			Optional<TaxRateDetail> old = taxRateDetailService.findById(taxRateDetailGroup.getTaxRateId(), taxRateDetailGroup.getRateNo());
			System.out.println("TaxRateDetailController.updateTaxrate() old "+old);
			System.out.println("TaxRateDetailController.updateTaxrate() old.isPresent() "+old.isPresent());
			if (old.isPresent()) 
			{
				taxRateDetail.setCreateTime(old.get().getCreateTime());
				taxRateDetail.setCreateUser(old.get().getCreateUser());
				TaxRateDetail taxRateDetailLast = taxRateDetailService.save(taxRateDetail);
				return new ResponseEntity<TaxRateDetail>(taxRateDetail,HttpStatus.OK);
			}
			else
			{
				System.out.printf("No found with TaxRateId :"+ taxRateDetailGroup.getTaxRateId()+" and RateNo "+taxRateDetailGroup.getRateNo());
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  TaxRateId: "+taxRateDetailGroup.getTaxRateId()+" and RateNo "+taxRateDetailGroup.getRateNo()+" ที่ใช้ในการอัพเดต!");
			}
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	//URL - http://localhost:8061/tax/taxRateDetail/delete?tax_rate_id=1&rate_no=2
	@RequestMapping(value = "/taxRateDetail/delete", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteHistoryTaxrate(@RequestParam("tax_rate_id") String taxRateId,
			@RequestParam("rate_no") String rateNo)
	{
		
		System.out.println("HistoryTaxrateController.deleteHistoryTaxrate() taxRateId "+taxRateId);
		System.out.println("HistoryTaxrateController.deleteHistoryTaxrate() rateNo  "+rateNo);
		try
		{
			System.out.println("HistoryTaxrateController.deleteHistoryTaxrate() tax_rtaxRateIdate_id = "+taxRateId+" : rateNo = "+rateNo);
			taxRateDetailService.delete(taxRateId, rateNo);
			return new ResponseEntity<>("success",HttpStatus.OK);
			
		}
		catch(Exception e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่สามารถลบข้อมูล  taxRateId = "+taxRateId+" : rateNo = "+rateNo+" ได้");
		}
	}


	
	@RequestMapping(value = "/taxRateDetail/searchByKey", method=RequestMethod.POST)///taxRateDetail/searchByKey?tax_rate_id=1&rate_no=2
	public ResponseEntity<List<TaxRateDetail>> searchTaxRateDetailByKey(@RequestParam("tax_rate_id") String taxRateId,
			@RequestParam("rate_no") String rateNo)
	{
		System.out.println("searchTaxRateDetail taxRateId = "+taxRateId);
		System.out.println("searchTaxRateDetail rateNo = "+rateNo);
		try
		{
			List<TaxRateDetail> list = new ArrayList<TaxRateDetail>();
			
				Optional<TaxRateDetail> his = taxRateDetailService.findById(taxRateId, rateNo);
				System.out.println("TaxRateDetailController.searchTaxRateDetail() his "+his);
				System.out.println("TaxRateDetailController.searchTaxRateDetail() his "+his.toString());
				if (his.isPresent()) 
				{
					list.add(his.get());
					for(int i=0;i<list.size();i++)
					{
						TaxRateDetail en = (TaxRateDetail)list.get(i);
						System.out.println("result searchTaxRateDetail : "+en);
					}
					return new ResponseEntity<List<TaxRateDetail>>(list,HttpStatus.OK);
			    } 
				else 
				{
					
			        System.out.printf("no found with  taxRateId = "+taxRateId+" : rateNo = "+rateNo);
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  taxRateId = "+taxRateId+" : rateNo = "+rateNo);
			    }
			
		}
		catch(Exception e)
		{
			System.out.println("Error searchTaxRateDetail : "+e.getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/taxRateDetail/search", method=RequestMethod.POST)///taxRateDetail/search?type=1
	public ResponseEntity<List<TaxRateDetail>> searchTaxRateDetail(@RequestParam("type") String type)
	{
		System.out.println("searchTaxRateDetail type = "+type);
		try
		{
			List<TaxRateDetail> list = new ArrayList<TaxRateDetail>();
			if(type.equals("A"))
			    list = taxRateDetailService.findAll();
			else
			    list = taxRateDetailService.findByTaxRateId(type);
			if(list.size()>0){
				for(int i=0;i<list.size();i++)
				{
					TaxRateDetail en = (TaxRateDetail)list.get(i);
					System.out.println("result searchTaxRateDetail : "+en);
				}
				return new ResponseEntity<List<TaxRateDetail>>(list,HttpStatus.OK);
			} 
			else 
			{
			    System.out.printf("no found with  taxRateId = "+type);
			    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ไม่พบ  taxRateId = "+type);
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
