package ws.personnel.tax.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ws.personnel.tax.configuration.ApplicationConstant;
import ws.personnel.tax.configuration.ServiceBase;
import ws.personnel.tax.entities.TaxDeduct;
import ws.personnel.tax.entities.object.TaxDeductObj;
import ws.personnel.tax.entities.objectC.TaxDeductObjC;
import ws.personnel.tax.repository.TaxDeductCustomRepository;
import ws.personnel.tax.repository.TaxDeductRepository;
import ws.personnel.tax.utils.SecurityUtils;
import ws.personnel.tax.utils.Status;

@Service
public class TaxDeductService extends ServiceBase {

	@Autowired
	private TaxDeductRepository taxDeductRepository;

	@Autowired
	private TaxDeductCustomRepository taxDeductCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxDeductService(TaxDeductRepository taxDeductRepository) {
		this.taxDeductRepository = taxDeductRepository;
	}

	public TaxDeductObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxDeductObjC taxDeductObjC = new TaxDeductObjC();
		List<TaxDeductObj> listTaxDeductObj = new ArrayList<>();
		List<TaxDeduct> listTaxDeductEntity = taxDeductRepository.searchDataAll();
		for (int i = 0; i < listTaxDeductEntity.size(); i++) {
			TaxDeductObj taxDeductObj = modelMapper.map(listTaxDeductEntity.get(i), TaxDeductObj.class);
			listTaxDeductObj.add(taxDeductObj);
		}
		taxDeductObjC.setListTaxDeductObj(listTaxDeductObj);
		return taxDeductObjC;
	}

	public String addTaxDeduct(TaxDeductObj taxDeductObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxDeduct .........");
			ModelMapper modelMapper = new ModelMapper();
			result = checkDup(taxDeductObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				TaxDeduct taxDeductEntity = modelMapper.map(taxDeductObj, TaxDeduct.class);
				taxDeductEntity.setUpdateUser(SecurityUtils.getUserName());
				taxDeductEntity.setUpdateTime(new Date());
				taxDeductEntity.setStatus(Status.active);
				taxDeductEntity = taxDeductCustomRepository.saveEntity(taxDeductEntity);
			}
			logger.info("End add TaxDeduct .........");
		} catch (Exception e) {
			logger.error("add TaxDeduct Error", e);
		}
		return result;
	}

	public String updateTaxDeduct(TaxDeductObj taxDeductObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxDeduct .........");

			result = checkDup(taxDeductObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxDeduct taxDeductEntity = modelMapper.map(taxDeductObj, TaxDeduct.class);
				taxDeductEntity.setUpdateUser(SecurityUtils.getUserName());
				taxDeductEntity.setUpdateTime(new Date());
				taxDeductEntity = taxDeductCustomRepository.updateEntity(taxDeductEntity);

			}
			logger.info("End update TaxDeduct .........");
		} catch (Exception e) {
			logger.error("update TaxDeduct Error", e);
		}
		return result;
	}

	public TaxDeductObj findById(TaxDeductObj taxDeductObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxDeduct taxDeductEntity = new TaxDeduct();
			taxDeductEntity = taxDeductRepository.searchDataById(taxDeductObj.getTaxDeductId());
			if(taxDeductEntity.getTaxDeductId() !=null){
				taxDeductObj = modelMapper.map(taxDeductEntity, TaxDeductObj.class);
			}
		} catch (Exception e) {
			logger.error("findById TaxDeduct Error", e);
		}
		return taxDeductObj;

	}

	public TaxDeductObjC searchTaxDeduct(TaxDeductObjC taxDeductObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<TaxDeduct> listTaxDeductEntity = new ArrayList<TaxDeduct>();
		List<TaxDeductObj> listTaxDeductObj = new ArrayList<>();
		String status = "";
		try {
			if(("all").equals(taxDeductObjC.getStatus().name())){

				listTaxDeductEntity = taxDeductRepository.searchDataByNameAndStatusAll(taxDeductObjC.getName());
			}else{

				listTaxDeductEntity = taxDeductRepository.searchDataByNameAndStatus(taxDeductObjC.getName(), taxDeductObjC.getStatus());
			}
			
			for (int i = 0; i < listTaxDeductEntity.size(); i++) {
				TaxDeductObj taxDeductObj = modelMapper.map(listTaxDeductEntity.get(i), TaxDeductObj.class);
				listTaxDeductObj.add(taxDeductObj);
			}
			taxDeductObjC.setListTaxDeductObj(listTaxDeductObj);
		} catch (Exception e) {
			logger.error("search TaxDeduct Error", e);
		}
		return taxDeductObjC;

	}

	public String deleteTaxDeduct(TaxDeductObjC taxDeductObjC) {
		TaxDeduct taxDeductEntity = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start delete TaxDeduct .........");
			for (int d = 0; d < taxDeductObjC.getListTaxDeductObj().size(); d++) {
				taxDeductEntity = taxDeductRepository.searchDataById(taxDeductObjC.getListTaxDeductObj().get(d).getTaxDeductId());
				taxDeductEntity.setStatus(Status.inactive);
				taxDeductEntity = taxDeductCustomRepository.updateEntity(taxDeductEntity);
			}

			logger.info("End delete TaxDeduct .........");
		} catch (Exception e) {
			logger.error("delete TaxDeduct Error", e);
		}
		return result;
	}

	public String checkDup(TaxDeductObj taxDeductObj,String action) {
		List<TaxDeduct> listTaxDeductEntity = new ArrayList<TaxDeduct>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listTaxDeductEntity = taxDeductRepository.checkDupDataByNameAndStatus(taxDeductObj.getName());
				if (listTaxDeductEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listTaxDeductEntity = taxDeductRepository.checkDupDataByNameAndStatus(taxDeductObj.getName());
				if (listTaxDeductEntity.size() > 0) {
					if(taxDeductObj.getTaxDeductId().equals(listTaxDeductEntity.get(0).getTaxDeductId())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("TaxDeduct checkDup error", e);
		}
		return result;
	}

}
