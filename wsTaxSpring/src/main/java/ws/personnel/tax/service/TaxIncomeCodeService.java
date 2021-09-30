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
import ws.personnel.tax.entities.TaxIncomeCode;
import ws.personnel.tax.entities.object.TaxIncomeCodeObj;
import ws.personnel.tax.entities.objectC.TaxIncomeCodeObjC;
import ws.personnel.tax.repository.TaxIncomeCodeCustomRepository;
import ws.personnel.tax.repository.TaxIncomeCodeRepository;
import ws.personnel.tax.utils.SecurityUtils;
import ws.personnel.tax.utils.Status;

@Service
public class TaxIncomeCodeService extends ServiceBase {

	@Autowired
	private TaxIncomeCodeRepository taxIncomeCodeRepository;

	@Autowired
	private TaxIncomeCodeCustomRepository taxIncomeCodeCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxIncomeCodeService(TaxIncomeCodeRepository taxIncomeCodeRepository) {
		this.taxIncomeCodeRepository = taxIncomeCodeRepository;
	}

	public TaxIncomeCodeObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxIncomeCodeObjC taxIncomeCodeObjC = new TaxIncomeCodeObjC();
		List<TaxIncomeCodeObj> listTaxIncomeCodeObj = new ArrayList<>();
		List<TaxIncomeCode> listTaxIncomeCodeEntity = taxIncomeCodeRepository.searchDataAll();
		for (int i = 0; i < listTaxIncomeCodeEntity.size(); i++) {
			TaxIncomeCodeObj taxIncomeCodeObj = modelMapper.map(listTaxIncomeCodeEntity.get(i), TaxIncomeCodeObj.class);
			listTaxIncomeCodeObj.add(taxIncomeCodeObj);
		}
		taxIncomeCodeObjC.setListTaxIncomeObj(listTaxIncomeCodeObj);
		return taxIncomeCodeObjC;
	}

	public String addTaxIncomeCode(TaxIncomeCodeObj taxIncomeCodeObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxIncomeCode .........");
			ModelMapper modelMapper = new ModelMapper();
			result = checkDup(taxIncomeCodeObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				taxIncomeCodeObj.setIncomeCatalogId(taxIncomeCodeObj.getIncomeCatalogId());
				TaxIncomeCode taxIncomeCodeEntity = modelMapper.map(taxIncomeCodeObj, TaxIncomeCode.class);
				taxIncomeCodeEntity.setUpdateUser(SecurityUtils.getUserName());
				taxIncomeCodeEntity.setUpdateTime(new Date());
				taxIncomeCodeEntity.setStatus(Status.active);
				taxIncomeCodeEntity = taxIncomeCodeCustomRepository.saveEntity(taxIncomeCodeEntity);
			}
			logger.info("End add TaxIncomeCode .........");
		} catch (Exception e) {
			logger.error("add TaxIncomeCode Error", e);
		}
		return result;
	}

	public String updateTaxIncomeCode(TaxIncomeCodeObj taxIncomeCodeObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxIncomeCode .........");

			result = checkDup(taxIncomeCodeObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxIncomeCode taxIncomeCodeEntity = modelMapper.map(taxIncomeCodeObj, TaxIncomeCode.class);
				taxIncomeCodeEntity.setUpdateUser(SecurityUtils.getUserName());
				taxIncomeCodeEntity.setUpdateTime(new Date());
				taxIncomeCodeEntity.setStatus(Status.active);
				taxIncomeCodeEntity = taxIncomeCodeCustomRepository.updateEntity(taxIncomeCodeEntity);

			}
			logger.info("End update TaxIncomeCode .........");
		} catch (Exception e) {
			logger.error("update TaxIncomeCode Error", e);
		}
		return result;
	}

	public TaxIncomeCodeObj findById(TaxIncomeCodeObj taxIncomeCodeObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxIncomeCode taxIncomeCodeEntity = new TaxIncomeCode();
			taxIncomeCodeEntity = taxIncomeCodeRepository.searchDataById(taxIncomeCodeObj.getIncomeCatalogId());
			if(taxIncomeCodeEntity.getIncomeCatalogId() !=null){
				taxIncomeCodeObj = modelMapper.map(taxIncomeCodeEntity, TaxIncomeCodeObj.class);
			}
		} catch (Exception e) {
			logger.error("findById TaxIncomeCode Error", e);
		}
		return taxIncomeCodeObj;

	}

	public TaxIncomeCodeObjC searchTaxIncomeCode(TaxIncomeCodeObjC taxIncomeCodeObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<TaxIncomeCode> listTaxIncomeCodeEntity = new ArrayList<TaxIncomeCode>();
		List<TaxIncomeCodeObj> listTaxIncomeCodeObj = new ArrayList<>();
		String status = "";
		try {
			if(("all").equals(taxIncomeCodeObjC.getStatus().name())){
				listTaxIncomeCodeEntity = taxIncomeCodeRepository.searchDataByNameAndStatusAll(taxIncomeCodeObjC.getName());
			}else{
				listTaxIncomeCodeEntity = taxIncomeCodeRepository.searchDataByNameAndStatus(taxIncomeCodeObjC.getName(), taxIncomeCodeObjC.getStatus());
			}
			
			for (int i = 0; i < listTaxIncomeCodeEntity.size(); i++) {
				TaxIncomeCodeObj taxIncomeCodeObj = modelMapper.map(listTaxIncomeCodeEntity.get(i), TaxIncomeCodeObj.class);
				listTaxIncomeCodeObj.add(taxIncomeCodeObj);
			}
			taxIncomeCodeObjC.setListTaxIncomeObj(listTaxIncomeCodeObj);
		} catch (Exception e) {
			logger.error("search TaxIncomeCode Error", e);
		}
		return taxIncomeCodeObjC;

	}

	public String deleteTaxIncomeCode(TaxIncomeCodeObjC taxIncomeCodeObjC) {
		TaxIncomeCode taxIncomeCodeEntity = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start delete TaxIncomeCode .........");
			for (int d = 0; d < taxIncomeCodeObjC.getListTaxIncomeObj().size(); d++) {
				taxIncomeCodeEntity = taxIncomeCodeRepository.searchDataById(taxIncomeCodeObjC.getListTaxIncomeObj().get(d).getIncomeCatalogId());
				taxIncomeCodeEntity.setStatus(Status.inactive);
				taxIncomeCodeEntity = taxIncomeCodeCustomRepository.updateEntity(taxIncomeCodeEntity);
			}

			logger.info("End delete TaxIncomeCode .........");
		} catch (Exception e) {
			logger.error("delete TaxIncomeCode Error", e);
		}
		return result;
	}

	public String checkDup(TaxIncomeCodeObj taxIncomeCodeObj,String action) {
		List<TaxIncomeCode> listTaxIncomeCodeEntity = new ArrayList<TaxIncomeCode>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listTaxIncomeCodeEntity = taxIncomeCodeRepository.checkDupDataByNameAndStatus(taxIncomeCodeObj.getName());
				if (listTaxIncomeCodeEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listTaxIncomeCodeEntity = taxIncomeCodeRepository.checkDupDataByNameAndStatus(taxIncomeCodeObj.getName());
				if (listTaxIncomeCodeEntity.size() > 0) {
					if(taxIncomeCodeObj.getIncomeCatalogId().equals(listTaxIncomeCodeEntity.get(0).getIncomeCatalogId())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("TaxIncomeCode checkDup error", e);
		}
		return result;
	}

}
