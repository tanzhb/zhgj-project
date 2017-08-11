package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.DemandPlanMateriel;

public interface DemandPlanMaterielService extends GenericService<DemandPlanMateriel, String>{

	void deleteBatch(List<String> serialNumArray);
}
