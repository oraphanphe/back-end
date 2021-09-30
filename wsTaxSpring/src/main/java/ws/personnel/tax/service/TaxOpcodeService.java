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
import ws.personnel.tax.entities.TaxOpcode;
import ws.personnel.tax.entities.object.TaxOpcodeObj;
import ws.personnel.tax.entities.objectC.TaxOpcodeObjC;
import ws.personnel.tax.repository.TaxOpcodeCustomRepository;
import ws.personnel.tax.repository.TaxOpcodeRepository;
import ws.personnel.tax.utils.SecurityUtils;
import ws.personnel.tax.utils.Status;

@Service
public class TaxOpcodeService extends ServiceBase {

	@Autowired
	private TaxOpcodeRepository taxOpcodeRepository;

	@Autowired
	private TaxOpcodeCustomRepository taxOpcodeCustomRepository;

	private EntityManager entityManager;

	@Autowired
	public TaxOpcodeService(TaxOpcodeRepository taxOpcodeRepository) {
		this.taxOpcodeRepository = taxOpcodeRepository;
	}

	public TaxOpcodeObjC searchDataAll() {
		ModelMapper modelMapper = new ModelMapper();
		TaxOpcodeObjC taxOpcodeObjC = new TaxOpcodeObjC();
		List<TaxOpcodeObj> listTaxOpcodeObj = new ArrayList<>();
		List<TaxOpcode> listTaxOpcodeEntity = taxOpcodeRepository.searchDataAll();
		for (int i = 0; i < listTaxOpcodeEntity.size(); i++) {
			TaxOpcodeObj taxOpcodeObj = modelMapper.map(listTaxOpcodeEntity.get(i), TaxOpcodeObj.class);
			listTaxOpcodeObj.add(taxOpcodeObj);
		}
		taxOpcodeObjC.setListTaxOpcodeObj(listTaxOpcodeObj);
		return taxOpcodeObjC;
	}

	public String addTaxOpcode(TaxOpcodeObj taxOpcodeObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start add TaxOpcode .........");
			ModelMapper modelMapper = new ModelMapper();
			result = checkDup(taxOpcodeObj,ApplicationConstant.ADD);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				TaxOpcode taxOpcodeEntity = modelMapper.map(taxOpcodeObj, TaxOpcode.class);
				taxOpcodeEntity.setCreateUser(SecurityUtils.getUserName());
				taxOpcodeEntity.setCreateTime(new Date());
				taxOpcodeEntity.setStatus(Status.active);
				taxOpcodeEntity = taxOpcodeCustomRepository.saveEntity(taxOpcodeEntity);
			}
			logger.info("End add TaxOpcode .........");
		} catch (Exception e) {
			logger.error("add TaxOpcode Error", e);
		}
		return result;
	}

	public String updateTaxOpcode(TaxOpcodeObj taxOpcodeObj) {
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start update TaxOpcode .........");

			result = checkDup(taxOpcodeObj,ApplicationConstant.EDIT);
			if (!result.equals(ApplicationConstant.DUPLICATE)) {
				ModelMapper modelMapper = new ModelMapper();
				TaxOpcode taxOpcodeEntity = modelMapper.map(taxOpcodeObj, TaxOpcode.class);
				taxOpcodeEntity.setUpdateUser(SecurityUtils.getUserName());
				taxOpcodeEntity.setUpdateTime(new Date());
				taxOpcodeEntity = taxOpcodeCustomRepository.updateEntity(taxOpcodeEntity);

			}
			logger.info("End update TaxOpcode .........");
		} catch (Exception e) {
			logger.error("update TaxOpcode Error", e);
		}
		return result;
	}

	public TaxOpcodeObj findById(TaxOpcodeObj taxOpcodeObj) {
		try {
			ModelMapper modelMapper = new ModelMapper();
			TaxOpcode taxOpcodeEntity = new TaxOpcode();
			taxOpcodeEntity = taxOpcodeRepository.searchDataById(taxOpcodeObj.getOpcode());
			if(taxOpcodeEntity.getOpcode() !=null){
				taxOpcodeObj = modelMapper.map(taxOpcodeEntity, TaxOpcodeObj.class);
			}
		} catch (Exception e) {
			logger.error("findById TaxOpcode Error", e);
		}
		return taxOpcodeObj;

	}

	public TaxOpcodeObjC searchTaxOpcode(TaxOpcodeObjC taxOpcodeObjC) {
		ModelMapper modelMapper = new ModelMapper();
		List<TaxOpcode> listTaxOpcodeEntity = new ArrayList<TaxOpcode>();
		List<TaxOpcodeObj> listTaxOpcodeObj = new ArrayList<>();
		String status = "";
		try {
			if(("all").equals(taxOpcodeObjC.getStatus().name())){

				listTaxOpcodeEntity = taxOpcodeRepository.searchDataByNameAndStatusAll(taxOpcodeObjC.getName());
			}else{

				listTaxOpcodeEntity = taxOpcodeRepository.searchDataByNameAndStatus(taxOpcodeObjC.getName(), taxOpcodeObjC.getStatus());
			}
			
			for (int i = 0; i < listTaxOpcodeEntity.size(); i++) {
				TaxOpcodeObj taxOpcodeObj = modelMapper.map(listTaxOpcodeEntity.get(i), TaxOpcodeObj.class);
				listTaxOpcodeObj.add(taxOpcodeObj);
			}
			taxOpcodeObjC.setListTaxOpcodeObj(listTaxOpcodeObj);
		} catch (Exception e) {
			logger.error("search TaxOpcode Error", e);
		}
		return taxOpcodeObjC;

	}

	public String deleteTaxOpcode(TaxOpcodeObjC taxOpcodeObjC) {
		TaxOpcode taxOpcodeEntity = null;
		String result = ApplicationConstant.SUCCESS;
		try {
			logger.info("Start delete TaxOpcode .........");
			for (int d = 0; d < taxOpcodeObjC.getListTaxOpcodeObj().size(); d++) {
				taxOpcodeEntity = taxOpcodeRepository.searchDataById(taxOpcodeObjC.getListTaxOpcodeObj().get(d).getOpcode());
				taxOpcodeEntity.setStatus(Status.inactive);
				taxOpcodeEntity = taxOpcodeCustomRepository.updateEntity(taxOpcodeEntity);
			}

			logger.info("End delete TaxOpcode .........");
		} catch (Exception e) {
			logger.error("delete TaxOpcode Error", e);
		}
		return result;
	}

	public String checkDup(TaxOpcodeObj taxOpcodeObj,String action) {
		List<TaxOpcode> listTaxOpcodeEntity = new ArrayList<TaxOpcode>();
		String result = ApplicationConstant.DUPLICATE;

		try {
			if(ApplicationConstant.ADD.equals(action)){
				listTaxOpcodeEntity = taxOpcodeRepository.checkDupDataByNameAndStatus(taxOpcodeObj.getName());
				if (listTaxOpcodeEntity.size() == 0) {
					result = ApplicationConstant.SUCCESS;
				}
			}else if(ApplicationConstant.EDIT.equals(action)){
				listTaxOpcodeEntity = taxOpcodeRepository.checkDupDataByNameAndStatus(taxOpcodeObj.getName());
				if (listTaxOpcodeEntity.size() > 0) {
					if(taxOpcodeObj.getOpcode().equals(listTaxOpcodeEntity.get(0).getOpcode())){
						result = ApplicationConstant.SUCCESS;
					}
				}else{
						result = ApplicationConstant.SUCCESS;
				}
			}
			
		} catch (Exception e) {
			logger.error("TaxOpcode checkDup error", e);
		}
		return result;
	}

}
