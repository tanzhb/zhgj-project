package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;
import com.congmai.zhgj.web.model.MaterielSelectExample;
import com.congmai.zhgj.web.model.User;

/**
 * 
 * @ClassName MaterielService
 * @Description 物料Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface MaterielService extends GenericService<Materiel, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<Materiel> selectList(MaterielExample m);
    
    List<Materiel> selectList(MaterielSelectExample m);

	public void deleteMateriels(String ids);

	void updateVersion(Materiel materiel);

	Materiel getMaterielInfoByMaterielId(String materielId);

	List<Materiel> chooseMateriel(String ids);
	
	List<Materiel> selectMaterielByOrderSerial (String orderSerial,String invoiceSerial,String deliverSerial, String customsFormType );
	
	String  getCurrentCount (String serialNum );//获取自建库存物料数量
}
