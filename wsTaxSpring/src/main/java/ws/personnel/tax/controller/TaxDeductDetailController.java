package ws.personnel.tax.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ws.personnel.tax.entities.object.TaxDeductDetailObj;
import ws.personnel.tax.entities.objectC.TaxDeductDetailObjC;
import ws.personnel.tax.service.TaxDeductDetailService;

@RestController
@CrossOrigin(origins = "/**", allowedHeaders = "/**")
@RequestMapping("/taxDeductDetail")
public class TaxDeductDetailController {

	@Autowired
	private TaxDeductDetailService taxDeductDetailService;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@PostMapping(value = "/addTaxDeductDetail", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> addTaxDeductDetail(@RequestBody TaxDeductDetailObjC taxDeductDetailObjC) {
		String result = "fail";
		logger.info("name is ....."+taxDeductDetailObjC.getListTaxDeductDetailObj().size());
		try {
			result = taxDeductDetailService.addTaxDeductDetail(taxDeductDetailObjC);
		} catch (Exception e) {
			result = "Add TaxDeductDetail Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editTaxDeductDetail", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> editTaxDeductDetail(@RequestBody TaxDeductDetailObj taxDeductDetailObj) {
		String result = "fail";
		logger.info("name is ....."+taxDeductDetailObj.getYear());
		try {
			result =taxDeductDetailService.updateTaxDeductDetail(taxDeductDetailObj);
		} catch (Exception e) {
			result = "Update TaxDeductDetail Error";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findById", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TaxDeductDetailObjC> findById(@RequestParam("year") String year) {
		logger.info("findById find by id TaxDeductDetail Start .....");
		TaxDeductDetailObjC taxDeductDetailObjC = new TaxDeductDetailObjC();
		try {
			taxDeductDetailObjC.setYear(year);
			taxDeductDetailObjC = taxDeductDetailService.findById(taxDeductDetailObjC);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxDeductDetail Error ...",e);
		}
		return new ResponseEntity<>(taxDeductDetailObjC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findDataMoreCurrentDate", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> findDataMoreCurrentDate(@RequestParam("year") String year) {
		logger.info("findById find by id TaxDeductDetail Start .....");
		String result = "fail";
		TaxDeductDetailObjC taxDeductDetailObjC = new TaxDeductDetailObjC();
		try {
			taxDeductDetailObjC.setYear(year);
			result = taxDeductDetailService.findDataMoreCurrentDate(taxDeductDetailObjC);
			
		} catch (Exception e) {
			logger.error("findById find by id TaxDeductDetail Error ...",e);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	public long startTime() {
		long start = System.currentTimeMillis();
		start = System.currentTimeMillis();
		return start;
	}

	public long endTime() {
		long end = System.currentTimeMillis();
		end = System.currentTimeMillis();
		return end;
	}

}
