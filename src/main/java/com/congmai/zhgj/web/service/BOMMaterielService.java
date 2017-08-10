package com.congmai.zhgj.web.service;

import java.util.List;

import com.congmai.zhgj.core.generic.GenericService;
import com.congmai.zhgj.web.model.BOMMateriel;
import com.congmai.zhgj.web.model.BOMMaterielExample;
import com.congmai.zhgj.web.model.Materiel;
import com.congmai.zhgj.web.model.MaterielExample;

/**
 * 
 * @ClassName BOMMaterielService
 * @Description bom物料Service
 * @author qfzhao
 * @Date 2017年7月28日 下午3:04:17
 * @version 1.0.0
 */
public interface BOMMaterielService extends GenericService<BOMMateriel, String> {
	/**
     * 查询多个对象
     *
     * @return 对象集合
     */
    List<BOMMateriel> selectList(BOMMaterielExample m);

	public void deleteBOMMateriels(String ids);

	void betchInsertBOM(Materiel materiel, List<BOMMateriel> bOM);


}
