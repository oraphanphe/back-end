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
import ws.personnel.tax.entities.TaxDeductGroup;
import ws.personnel.tax.entities.object.TaxDeductGroupObj;
import ws.personnel.tax.entities.objectC.TaxDeductGroupObjC;
import ws.personnel.tax.repository.TaxDeductGroupCustomRepository;
import ws.personnel.tax.repository.TaxDeductGroupRepository;
import ws.personnel.tax.utils.SecurityUtils;
import ws.personnel.tax.utils.Status;

@Service
public class TaxDeductGroupService extends ServiceBase {

	@Autowired
	private TaxDeductGroupRepository taxDeductRepository;

	@Autowired
	private TaxDeductGroupCustomRepository taxDeductCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxDeductGroupService(TaxDeductGroupRepository taxDeductRepository) {
		this.taxDeductRepository = taxDeductRepository;
	}

	public TaxDeductGroupObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxDeductGroupObjC taxDeductObjC = new TaxDeductGroupObjC();
		List<TaxDeductGroupObj> listTaxDeductGroupObj = new ArrayList<>();
		List<TaxDeductGroup> listTaxDeductGroupEntity = taxDeductRepository.searchDataAll();
		for (int i = 0; i < listTaxDeductGroupEntity.size(); i++) {
			TaxDeductGroupObj taxDeductObj = modelMapper.map(listTaxDeductGroupEntity.get(i), TaxDeductGroupObj.class);
			listTaxDeductGroupObj.add(taxDeductObj);
		}
		taxDeductObjC.setListTaxDeductGroupObj(listTaxDeductGroupObj);
		return taxDeductObjC;
	}

	public String addTaxDeductGroup(TaxDeductGroupObj taxDeductObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxDeductGroup .........");
			ModelMapper modelMapper = new ModelMapper();
			result = checkDup(taxDeductObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				TaxDeductGroup taxDeductEntity = modelMapper.map(taxDeductObj, TaxDeductGroup.class);
				taxDeductEntity.setCreateUser(SecurityUtils.getUserName());
				taxDeductEntity.setCreateTime(new Date());
				taxDeductEntity.setStatus(Status.active);
				taxDeductEntity = taxDeductCustomRepository.saveEntity(taxDeductEntity);
			}
			logger.info("End add TaxDeductGroup .........");
		} catch (Exception e) {
			logger.error("add TaxDeductGroup Error", e);
		}
		return result;
	}

	public String updateTaxDeductGroup(TaxDeductGroupObj taxDeductObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxDeductGroup .........");

			result = checkDup(taxDeductObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxDeductGroup taxDeductEntity = modelMapper.map(taxDeductObj, TaxDeductGroup.class);
				taxDeductEntity.setUpdateUser(SecurityUtils.getUserName());
				taxDeductEntity.setUpdateTime(new Date());
				taxDeductEntity = taxDeductCustomRepository.updateEntity(taxDeductEntity);

			}
			logger.info("End update TaxDeductGroup .........");
		} catch (Exception e) {
			logger.error("update TaxDeductGroup Error", e);
		}
		return result;
	}

	public TaxDeductGroupObj findById(TaxDeductGroupObj taxDeductObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxDeductGroup taxDeductEntity = new TaxDeductGroup();
			taxDeductEntity = taxDeductRepository.searchDataById(taxDeductObj.getDeductGroupId());
			if(taxDeductEntity.getDeductGroupId() !=null){
				taxDeductObj = modelMapper.map(taxDeductEntity, TaxDeductGroupObj.class);
			}
		} catch (Exception e) {
			logger.error("findById TaxDeductGroup Error", e);
		}
		return taxDeductObj;

	}

	public TaxDeductGroupObjC searchTaxDeductGroup(TaxDeductGroupObjC taxDeductObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<TaxDeductGroup> listTaxDeductGroupEntity = new ArrayList<TaxDeductGroup>();
		List<TaxDeductGroupObj> listTaxDeductGroupObj = new ArrayList<>();
		String status = "";
		try {
			if(("all").equals(taxDeductObjC.getStatus().name())){

				listTaxDeductGroupEntity = taxDeductRepository.searchDataByNameAndStatusAll(taxDeductObjC.getName());
			}else{

				listTaxDeductGroupEntity = taxDeductRepository.searchDataByNameAndStatus(taxDeductObjC.getName(), taxDeductObjC.getStatus());
			}
			for (int i = 0; i < listTaxDeductGroupEntity.size(); i++) {
				TaxDeductGroupObj taxDeductObj = modelMapper.map(listTaxDeductGroupEntity.get(i), TaxDeductGroupObj.class);
				listTaxDeductGroupObj.add(taxDeductObj);
			}
			taxDeductObjC.setListTaxDeductGroupObj(listTaxDeductGroupObj);
		} catch (Exception e) {
			logger.error("search TaxDeductGroup Error", e);
		}
		return taxDeductObjC;

	}

	public String deleteTaxDeductGroup(TaxDeductGroupObjC taxDeductObjC) {
		TaxDeductGroup taxDeductEntity = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start delete TaxDeductGroup .........");
			for (int d = 0; d < taxDeductObjC.getListTaxDeductGroupObj().size(); d++) {
				taxDeductEntity = taxDeductRepository.searchDataById(taxDeductObjC.getListTaxDeductGroupObj().get(d).getDeductGroupId());
				taxDeductEntity.setStatus(Status.inactive);
				taxDeductEntity = taxDeductCustomRepository.updateEntity(taxDeductEntity);
			}

			logger.info("End delete TaxDeductGroup .........");
		} catch (Exception e) {
			logger.error("delete TaxDeductGroup Error", e);
		}
		return result;
	}

	public String checkDup(TaxDeductGroupObj taxDeductObj,String action) {
		List<TaxDeductGroup> listTaxDeductGroupEntity = new ArrayList<TaxDeductGroup>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listTaxDeductGroupEntity = taxDeductRepository.checkDupDataByNameAndStatus(taxDeductObj.getName());
				if (listTaxDeductGroupEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listTaxDeductGroupEntity = taxDeductRepository.checkDupDataByNameAndStatus(taxDeductObj.getName());
				if (listTaxDeductGroupEntity.size() > 0) {
					if(taxDeductObj.getDeductGroupId().equals(listTaxDeductGroupEntity.get(0).getDeductGroupId())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("TaxDeductGroup checkDup error", e);
		}
		return result;
	}

}
